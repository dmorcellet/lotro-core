package delta.games.lotro.lore.items.legendary.relics;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages all relics for a given category.
 * @author DAM
 */
public class RelicsCategory
{
  private String _name;
  private List<Relic> _relics;

  /**
   * Constructor.
   * @param name Category name.
   */
  public RelicsCategory(String name)
  {
    _name=name;
    _relics=new ArrayList<Relic>();
  }

  /**
   * Get the name of this category.
   * @return a category name.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Get a relic by its name.
   * @param name Name of the relic to get.
   * @return A relic or <code>null</code> if not found.
   */
  public Relic getByName(String name)
  {
    for(Relic relic : _relics)
    {
      if (relic.getName().equals(name))
      {
        return relic;
      }
    }
    return null;
  }

  /**
   * Add a relic.
   * @param relic Relic to add.
   */
  public void addRelic(Relic relic)
  {
    _relics.add(relic);
  }

  /**
   * Get a list of all relics included in this category.
   * @return a list of all managed relics.
   */
  public List<Relic> getAllRelics()
  {
    return new ArrayList<Relic>(_relics);
  }
}