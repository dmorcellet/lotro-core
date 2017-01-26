package delta.games.lotro.character.stats.buffs;

import delta.games.lotro.common.CharacterClass;
import delta.games.lotro.common.Race;

/**
 * Buff.
 * @author DAM
 */
public class Buff
{
  private String _id;
  private String _label;
  private String _icon;
  private CharacterClass _requiredClass;
  private Race _requiredRace;
  private BuffComputer _computer;

  /**
   * Constructor.
   * @param id Buff identifier.
   * @param label Displayed label.
   */
  public Buff(String id, String label)
  {
    _id=id;
    _label=label;
  }

  /**
   * Get the buff identifier.
   * @return a string identifier.
   */
  public String getId()
  {
    return _id;
  }

  /**
   * Get the buff label.
   * @return a label.
   */
  public String getLabel()
  {
    return _label;
  }

  /**
   * Get the associated icon filename.
   * @return an icon filename.
   */
  public String getIcon()
  {
    return _icon;
  }

  /**
   * Set the icon filename.
   * @param icon filename to set.
   */
  public void setIcon(String icon)
  {
    _icon=icon;
  }

  /**
   * Get the class requirement for this buff.
   * @return A class or <code>null</code> if no class restriction.
   */
  public CharacterClass getRequiredClass()
  {
    return _requiredClass;
  }

  /**
   * Set the class requirement for this buff.
   * @param requiredClass Required class or <code>null</code>.
   */
  public void setRequiredClass(CharacterClass requiredClass)
  {
    _requiredClass=requiredClass;
  }

  /**
   * Get the race requirement for this buff.
   * @return A race or <code>null</code> if no race restriction.
   */
  public Race getRequiredRace()
  {
    return _requiredRace;
  }

  /**
   * Set the race requirement for this buff.
   * @param requiredRace Required race or <code>null</code>.
   */
  public void setRequiredRace(Race requiredRace)
  {
    _requiredRace=requiredRace;
  }

  /**
   * Get the computer for this buff.
   * @return the computer for this buff.
   */
  public BuffComputer getComputer()
  {
    return _computer;
  }

  /**
   * @param computer the computer to set
   */
  public void setComputer(BuffComputer computer)
  {
    _computer=computer;
  }

  @Override
  public String toString()
  {
    StringBuilder sb=new StringBuilder("Buff: ");
    sb.append(_id);
    sb.append(": ").append(_label);
    sb.append(" (").append(_icon).append(')');
    if (_requiredRace!=null)
    {
      sb.append(" ; race=").append(_requiredRace);
    }
    if (_requiredClass!=null)
    {
      sb.append(" ; class=").append(_requiredClass);
    }
    return sb.toString();
  }
}
