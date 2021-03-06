package delta.games.lotro.lore.items.legendary.non_imbued;

import delta.games.lotro.common.effects.Effect;
import delta.games.lotro.common.stats.StatDescription;
import delta.games.lotro.common.stats.StatsProvider;
import delta.games.lotro.lore.items.legendary.LegacyType;

/**
 * Default non-imbued legacy.
 * @author DAM
 */
public class DefaultNonImbuedLegacy extends AbstractNonImbuedLegacy
{
  private Effect _effect;

  /**
   * Constructor.
   */
  public DefaultNonImbuedLegacy()
  {
    // Nothing!
  }

  @Override
  public StatDescription getStat()
  {
    if (_effect!=null)
    {
      StatsProvider statsProvider=_effect.getStatsProvider();
      return statsProvider.getFirstStat();
    }
    return null;
  }

  /**
   * Get the associated effect.
   * @return the associated effect.
   */
  public Effect getEffect()
  {
    return _effect;
  }

  /**
   * Set the associated effect.
   * @param effect Effect to set.
   */
  public void setEffect(Effect effect)
  {
    _effect=effect;
  }

  @Override
  public String toString()
  {
    StringBuilder sb=new StringBuilder();
    String name=(_effect!=null)?_effect.getLabel():"?";
    sb.append("Default non imbued: ").append(name);
    int id=(_effect!=null)?_effect.getIdentifier():0;
    if (id!=0)
    {
      sb.append(" (id=").append(id).append(')');
    }
    LegacyType type=getType();
    if (type!=null)
    {
      sb.append(" (").append(type).append(')');
    }
    return sb.toString();
  }
}
