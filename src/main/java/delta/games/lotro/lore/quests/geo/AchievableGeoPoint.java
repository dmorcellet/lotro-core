package delta.games.lotro.lore.quests.geo;

import java.awt.geom.Point2D;

/**
 * Point of an achievable.
 * @author DAM
 */
public class AchievableGeoPoint
{
  private int _did;
  private String _key;
  private int _mapId;
  private Point2D.Float _lonLat;

  /**
   * Constructor.
   * @param did Data identifier.
   * @param key Associated key.
   * @param mapId Map identifier.
   * @param lonLat Position.
   */
  public AchievableGeoPoint(int did, String key, int mapId, Point2D.Float lonLat)
  {
    _did=did;
    _key=key;
    _mapId=mapId;
    _lonLat=lonLat;
  }

  /**
   * Get the data identifier.
   * @return a data identifier.
   */
  public int getDataId()
  {
    return _did;
  }

  /**
   * Get the associated key.
   * @return A string key.
   */
  public String getKey()
  {
    return _key;
  }

  /**
   * Get the identifier of the map to use.
   * @return A map identifier.
   */
  public int getMapId()
  {
    return _mapId;
  }

  /**
   * Get the position as lon/lat.
   * @return a position.
   */
  public Point2D.Float getLonLat()
  {
    return _lonLat;
  }

  @Override
  public String toString()
  {
    StringBuilder sb=new StringBuilder();
    sb.append("Achievable point: position=");
    sb.append("lon:").append(_lonLat.x);
    sb.append(",lat:").append(_lonLat.y);
    if (_did!=0)
    {
      sb.append(", DID=").append(_did);
    }
    if (_key!=null)
    {
      sb.append(", key=").append(_key);
    }
    sb.append(", mapId=").append(_mapId);
    return sb.toString();
  }
}
