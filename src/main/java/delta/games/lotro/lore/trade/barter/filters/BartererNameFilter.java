package delta.games.lotro.lore.trade.barter.filters;

import delta.common.utils.collections.filters.Filter;
import delta.common.utils.text.MatchType;
import delta.common.utils.text.StringFilter;
import delta.games.lotro.lore.trade.barter.BarterNpc;

/**
 * Filter on barterer name.
 * @author DAM
 */
public class BartererNameFilter implements Filter<BarterNpc>
{
  private StringFilter _filter;
  private String _pattern;

  /**
   * Constructor.
   */
  public BartererNameFilter()
  {
    this("");
  }

  /**
   * Constructor.
   * @param pattern String filter for name.
   */
  public BartererNameFilter(String pattern)
  {
    _filter=new StringFilter("",MatchType.CONTAINS,true);
    _pattern=pattern;
  }

  /**
   * Get the pattern to use to filter name.
   * @return A pattern string.
   */
  public String getPattern()
  {
    return _pattern;
  }

  /**
   * Set the string pattern.
   * @param pattern Pattern to set.
   */
  public void setPattern(String pattern)
  {
    if (pattern==null)
    {
      pattern="";
    }
    _pattern=pattern;
    _filter=new StringFilter(pattern,MatchType.CONTAINS,true);
  }

  @Override
  public boolean accept(BarterNpc vendor)
  {
    String name=vendor.getNpc().getName();
    if (name!=null)
    {
      return _filter.accept(name);
    }
    return false;
  }
}
