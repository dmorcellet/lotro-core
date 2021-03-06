package delta.games.lotro.lore.items;

import delta.common.utils.text.EndOfLine;

/**
 * Armour description.
 * @author DAM
 */
public class Armour extends Item
{
  private ArmourType _type;

  /**
   * Constructor.
   */
  public Armour()
  {
    super();
  }

  @Override
  public ItemCategory getCategory()
  {
    return ItemCategory.ARMOUR;
  }

  /**
   * Get armour type.
   * @return an armour type.
   */
  public ArmourType getArmourType()
  {
    return _type;
  }

  /**
   * Set armour type.
   * @param type Armour type to set.
   */
  public void setArmourType(ArmourType type)
  {
    _type=type;
  }

  /**
   * Dump the contents of this armour as a string.
   * @return A readable string.
   */
  @Override
  public String dump()
  {
    StringBuilder sb=new StringBuilder();
    String itemDump=super.dump();
    sb.append(itemDump);
    sb.append(EndOfLine.NATIVE_EOL);
    sb.append("Armour type=");
    sb.append(_type);
    return sb.toString();
  }
}
