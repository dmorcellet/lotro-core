package delta.games.lotro.character.stats.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import delta.games.lotro.character.stats.BasicStatsSet;
import delta.games.lotro.character.stats.STAT;
import delta.games.lotro.character.stats.contribs.StatsContribution;
import delta.games.lotro.character.stats.contribs.StatsContributionsManager;
import delta.games.lotro.common.CharacterClass;
import delta.games.lotro.utils.FixedDecimalsInteger;

/**
 * Manager for derived statistics contributions.
 * @author DAM
 */
public final class DerivedStatsContributionsMgr
{
  /**
   * Contribution for a derived stat.
   * @author DAM
   */
  public static class DerivedStatContribution
  {
    private STAT _contributedStat;
    private FixedDecimalsInteger _factor;

    /**
     * Get the target stat.
     * @return the target stat.
     */
    public STAT getTargetStat()
    {
      return _contributedStat;
    }

    /**
     * Get the factor.
     * @return the factor.
     */
    public FixedDecimalsInteger getFactor()
    {
      return _factor;
    }
  }

  /**
   * Contributions for a single source stat.
   * @author DAM
   */
  public static final class StatContributions
  {
    private List<DerivedStatContribution> _factors;

    private StatContributions()
    {
      _factors=new ArrayList<DerivedStatContribution>();
    }

    private void addStatContribution(STAT contributedStat, FixedDecimalsInteger factor)
    {
      DerivedStatContribution contrib=new DerivedStatContribution();
      contrib._contributedStat=contributedStat;
      contrib._factor=factor;
      _factors.add(contrib);
    }

    /**
     * Get contributions.
     * @return a list of contributions.
     */
    public List<DerivedStatContribution> getFactors()
    {
      return _factors;
    }
  }

  /**
   * Derived stats contributions for a single class.
   * @author DAM
   */
  public static final class ClassDerivedStats
  {
    private HashMap<STAT,StatContributions> _contributions;
    private ClassDerivedStats()
    {
      _contributions=new HashMap<STAT,StatContributions>();
    }

    private void addStatContribution(STAT sourceStat, STAT contributedStat, FixedDecimalsInteger factor)
    {
      StatContributions contribs=_contributions.get(sourceStat);
      if (contribs==null)
      {
        contribs=new StatContributions();
        _contributions.put(sourceStat,contribs);
      }
      contribs.addStatContribution(contributedStat,factor);
    }

    /**
     * Get a list of source stats.
     * @return a list of source stats.
     */
    public List<STAT> getSourceStats()
    {
      List<STAT> stats=new ArrayList<STAT>(_contributions.keySet());
      Collections.sort(stats);
      return stats;
    }

    /**
     * Get the contributions for a single source stat.
     * @param sourceStat Source stat.
     * @return Stat contributions.
     */
    public StatContributions getContribsForStat(STAT sourceStat)
    {
      return _contributions.get(sourceStat);
    }
  }

  private HashMap<CharacterClass,ClassDerivedStats> _allContribs;

  /**
   * Constructor.
   */
  public DerivedStatsContributionsMgr()
  {
    _allContribs=new HashMap<CharacterClass,ClassDerivedStats>();
  }

  /**
   * Get the derived stats contributions for a single class.
   * @param characterClass Targeted class.
   * @return some derived stats contributions.
   */
  public ClassDerivedStats getDerivatedStats(CharacterClass characterClass)
  {
    return _allContribs.get(characterClass);
  }

  /**
   * Set factor.
   * @param primaryStat Primary stat.
   * @param contributedStat Contributed stat.
   * @param cClass Character class;
   * @param factor Factor.
   */
  public void setFactor(STAT primaryStat, STAT contributedStat, CharacterClass cClass, FixedDecimalsInteger factor)
  {
    ClassDerivedStats derivedStats=_allContribs.get(cClass);
    if (derivedStats==null)
    {
      derivedStats=new ClassDerivedStats();
      _allContribs.put(cClass,derivedStats);
    }
    derivedStats.addStatContribution(primaryStat,contributedStat,factor);
  }

  /**
   * Get stats contribution for a set of stats.
   * @param cClass Targeted character class.
   * @param set Set of raw stats.
   * @return A set of stat contributions.
   */
  public BasicStatsSet getContribution(CharacterClass cClass, BasicStatsSet set)
  {
    return getContribution(cClass,set,null);
  }

  /**
   * Get stats contribution for a set of stats.
   * @param cClass Targeted character class.
   * @param set Set of raw stats.
   * @param contribsMgr Optional contributions manager to store computed contributions.
   * @return A set of stat contributions.
   */
  public BasicStatsSet getContribution(CharacterClass cClass, BasicStatsSet set, StatsContributionsManager contribsMgr)
  {
    ClassDerivedStats classDerivedStats=_allContribs.get(cClass);
    BasicStatsSet result=new BasicStatsSet();
    for(STAT stat : STAT.values())
    {
      FixedDecimalsInteger statValue=set.getStat(stat);
      if (statValue!=null)
      {
        StatContributions contrib=classDerivedStats.getContribsForStat(stat);
        if (contrib!=null)
        {
          BasicStatsSet derivedStats=new BasicStatsSet();
          for(DerivedStatContribution factor : contrib.getFactors())
          {
            FixedDecimalsInteger toAdd=new FixedDecimalsInteger(statValue);
            toAdd.multiply(factor._factor);
            result.addStat(factor._contributedStat,toAdd);
            derivedStats.addStat(factor._contributedStat,toAdd);
            if (contribsMgr!=null)
            {
              BasicStatsSet stats=new BasicStatsSet();
              stats.addStat(factor._contributedStat,toAdd);
              StatsContribution statContrib=StatsContribution.getStatContrib(stat,factor._factor,stats);
              contribsMgr.addContrib(statContrib);
            }
          }
        }
      }
    }
    return result;
  }
}