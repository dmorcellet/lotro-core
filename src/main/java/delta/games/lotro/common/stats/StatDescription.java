package delta.games.lotro.common.stats;

import delta.games.lotro.common.Identifiable;

/**
 * Stat description.
 * @author DAM
 */
public class StatDescription implements Identifiable
{
  private int _id;
  private Integer _index;
  private String _key;
  private String _legacyKey;
  private String _internalName;
  private String _legacyName;
  private boolean _isPercentage;

  /**
   * Constructor.
   * @param id
   */
  public StatDescription(int id)
  {
    _id=id;
    _index=null;
    _key=null;
    _legacyKey=null;
    _internalName="";
    _legacyName=null;
    _isPercentage=false;
  }

  /**
   * Get the stat identifier.
   * @return an identifier.
   */
  public int getIdentifier()
  {
    return _id;
  }

  /**
   * Get the stat index.
   * @return an index or <code>null</code> if none.
   */
  public Integer getIndex()
  {
    return _index;
  }

  /**
   * Set the index for this stat.
   * @param index the index to set.
   */
  public void setIndex(Integer index)
  {
    _index=index;
  }

  /**
   * Get the stat key.
   * @return a string key or <code>null</code> if none.
   */
  public String getKey()
  {
    return _key;
  }

  /**
   * Set the stat string key.
   * @param key the key to set.
   */
  public void setKey(String key)
  {
    _key=key;
  }

  /**
   * Get the legacy key for this stat.
   * @return a string key or <code>null</code> if none.
   */
  public String getLegacyKey()
  {
    return _legacyKey;
  }

  /**
   * Set the legacy key for this stat.
   * @param legacyKey the legacy key to set (may be <code>null</code>).
   */
  public void setLegacyKey(String legacyKey)
  {
    _legacyKey=legacyKey;
  }

  /**
   * Get the persistence key for this stat.
   * @return a persistence key string.
   */
  public String getPersistenceKey()
  {
    return (_legacyKey!=null)?_legacyKey:_key;
  }

  /**
   * Get the internal name for this stat.
   * @return a name.
   */
  public String getInternalName()
  {
    return _internalName;
  }

  /**
   * Set the internal name of this stat.
   * @param name the name to set.
   */
  public void setInternalName(String name)
  {
    if (name==null) name="";
    _internalName=name;
  }

  /**
   * Get the displayable legacy name for this stat.
   * @return a legacy name or <code>null</code> if none.
   */
  public String getLegacyName()
  {
    return _legacyName;
  }

  /**
   * Set the legacy name of this stat.
   * @param legacyName the legacy name to set (may be <code>null</code>).
   */
  public void setLegacyName(String legacyName)
  {
    _legacyName=legacyName;
  }

  /**
   * Get a displayable name for this stat.
   * @return a displayable name.
   */
  public String getName()
  {
    return (_legacyName!=null)?_legacyName:_internalName;
  }

  /**
   * Indicates if this stat represents a percentage or not.
   * @return <code>true</code> if it does, <code>false</code> otherwise.
   */
  public boolean isPercentage()
  {
    return _isPercentage;
  }

  /**
   * Set the 'is percentage' flag.
   * @param isPercentage Value to set.
   */
  public void setPercentage(boolean isPercentage)
  {
    _isPercentage=isPercentage;
  }

  @Override
  public String toString()
  {
    StringBuilder sb=new StringBuilder("STAT: id=");
    sb.append(_id);
    if (_key!=null)
    {
      sb.append(", key=").append(_key);
    }
    if (_legacyKey!=null)
    {
      sb.append(", legacyKey=").append(_legacyKey);
    }
    if (_legacyName!=null)
    {
      sb.append(", legacyName=").append(_legacyName);
    }
    if (_index!=null)
    {
      sb.append(", index=").append(_index);
    }
    sb.append(", name=").append(_internalName);
    sb.append(", display name=").append(getName());
    return sb.toString();
  }
}