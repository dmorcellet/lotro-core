package delta.games.lotro.character.classes;

import delta.games.lotro.character.traits.TraitDescription;

/**
 * Trait for a class.
 * @author DAM
 */
public class ClassTrait
{
  private int _requiredLevel;
  private TraitDescription _trait;

  /**
   * Constructor.
   * @param requiredLevel Required level.
   * @param trait Trait.
   */
  public ClassTrait(int requiredLevel, TraitDescription trait)
  {
    _requiredLevel=requiredLevel;
    _trait=trait;
  }

  /**
   * Get the character level required for this trait.
   * @return a character level.
   */
  public int getRequiredLevel()
  {
    return _requiredLevel;
  }

  /**
   * Get the managed trait.
   * @return a trait.
   */
  public TraitDescription getTrait()
  {
    return _trait;
  }
}
