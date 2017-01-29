package delta.games.lotro.character.stats.buffs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import delta.games.lotro.character.CharacterData;
import delta.games.lotro.character.stats.buffs.comparators.BuffNameComparator;
import delta.games.lotro.common.CharacterClass;
import delta.games.lotro.common.Race;

/**
 * Registry for buffs.
 * @author DAM
 */
public class BuffRegistry
{
  private static final BuffRegistry _instance=new BuffRegistry();
  private HashMap<String,Buff> _buffMap;

  /**
   * Get the global buffs registry.
   * @return the global buffs registry.
   */
  public static BuffRegistry getInstance()
  {
    return _instance;
  }

  /**
   * Constructor.
   */
  private BuffRegistry()
  {
    _buffMap=new HashMap<String,Buff>();
    BuffInitializer init=new BuffInitializer();
    init.initBuffs(this);
  }

  /**
   * Register a new buff.
   * @param buff Buff to register.
   */
  public void registerBuff(Buff buff)
  {
    String id=buff.getId();
    _buffMap.put(id,buff);
  }

  /**
   * Get all buffs.
   * @return a list of all buffs.
   */
  public List<Buff> getAllBuffs()
  {
    return new ArrayList<Buff>(_buffMap.values());
  }

  /**
   * Build a selection of buffs.
   * @param c Targeted character.
   * @param buffs Buffs to skip.
   * @return A list of buffs.
   */
  public List<Buff> buildBuffSelection(CharacterData c, BuffsManager buffs)
  {
    List<Buff> ret=new ArrayList<Buff>();
    Set<String> ids=buffs.getBuffId();
    BuffRegistry buffsRegistry=BuffRegistry.getInstance();
    List<Buff> allBuffs=buffsRegistry.getAllBuffs();
    CharacterClass cClass=c.getCharacterClass();
    Race race=c.getRace();
    for(Buff buff : allBuffs)
    {
      CharacterClass requiredClass=buff.getRequiredClass();
      if ((requiredClass==null) || (requiredClass==cClass))
      {
        Race requiredRace=buff.getRequiredRace();
        if ((requiredRace==null) || (requiredRace==race))
        {
          String buffId=buff.getId();
          if (!ids.contains(buffId))
          {
            ret.add(buff);
          }
        }
      }
    }
    Collections.sort(ret,new BuffNameComparator());
    return ret;
  }

  private Buff getBuffById(String buffId)
  {
    return _buffMap.get(buffId);
  }

  /**
   * Build a new buff instance.
   * @param buffId Buff identifier.
   * @return A new buff or <code>null</code> if buff not found.
   */
  public BuffInstance newBuffInstance(String buffId)
  {
    BuffInstance ret=null;
    Buff buff=getBuffById(buffId);
    if (buff!=null)
    {
      ret=new BuffInstance(buff);
    }
    return ret;
  }
}