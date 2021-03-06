package delta.games.lotro.lore.quests.objectives;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import delta.common.utils.text.EndOfLine;
import delta.games.lotro.lore.quests.dialogs.DialogElement;

/**
 * Quest/deed objective.
 * @author DAM
 */
public class Objective
{
  private int _index;
  private String _description;
  private String _loreOverride;
  private String _progressOverride;
  private String _billboardOverride;
  private List<DialogElement> _dialogs;
  private List<ObjectiveCondition> _conditions;

  /**
   * Constructor.
   */
  public Objective()
  {
    _index=1;
    _description="";
    _loreOverride="";
    _progressOverride="";
    _dialogs=new ArrayList<DialogElement>();
    _conditions=new ArrayList<ObjectiveCondition>();
  }

  /**
   * Get the objective index, starting at 1.
   * @return An index.
   */
  public int getIndex()
  {
    return _index;
  }

  /**
   * Set the objective index.
   * @param index Index to set.
   */
  public void setIndex(int index)
  {
    _index=index;
  }

  /**
   * Get the displayable description for this objective.
   * @return A displayable description (can be multiline).
   */
  public String getDescription()
  {
    return _description;
  }

  /**
   * Set the description for this objective.
   * @param description Description to set.
   */
  public void setDescription(String description)
  {
    if (description==null) description="";
    _description=description;
  }

  /**
   * Get the lore override.
   * @return A possibly empty string, but never <code>null</code>.
   */
  public String getLoreOverride()
  {
    return _loreOverride;
  }

  /**
   * Set the lore override.
   * @param loreOverride Value to set.
   */
  public void setLoreOverride(String loreOverride)
  {
    if (loreOverride==null) loreOverride="";
    _loreOverride=loreOverride;
  }

  /**
   * Get the progress override.
   * @return A possibly empty string, but never <code>null</code>.
   */
  public String getProgressOverride()
  {
    return _progressOverride;
  }

  /**
   * Set the progress override.
   * @param progressOverride Value to set.
   */
  public void setProgressOverride(String progressOverride)
  {
    if (progressOverride==null) progressOverride="";
    _progressOverride=progressOverride;
  }

  /**
   * Get the billboard override.
   * @return A possibly empty string, but never <code>null</code>.
   */
  public String getBillboardOverride()
  {
    return _billboardOverride;
  }

  /**
   * Set the billboard override.
   * @param billboardOverride Value to set.
   */
  public void setBillboardOverride(String billboardOverride)
  {
    if (billboardOverride==null) billboardOverride="";
    _billboardOverride=billboardOverride;
  }

  /**
   * Add a dialog.
   * @param dialog Dialog to add.
   */
  public void addDialog(DialogElement dialog)
  {
    _dialogs.add(dialog);
  }

  /**
   * Get all the dialogs of the objective.
   * @return A list of dialogs.
   */
  public List<DialogElement> getDialogs()
  {
    return _dialogs;
  }

  /**
   * Add an objective condition.
   * @param condition Condition to add.
   */
  public void addCondition(ObjectiveCondition condition)
  {
    _conditions.add(condition);
  }

  /**
   * Get all the conditions of the objective.
   * @return A list of conditions.
   */
  public List<ObjectiveCondition> getConditions()
  {
    return _conditions;
  }

  /**
   * Access to an objective condition using its index.
   * @param index Index to use.
   * @return An objective condition or <code>null</code> if not found.
   */
  public ObjectiveCondition getConditionByIndex(int index)
  {
    for(ObjectiveCondition condition : _conditions)
    {
      if (condition.getIndex()==index)
      {
        return condition;
      }
    }
    return null;
  }

  /**
   * Sort conditions.
   */
  public void sort()
  {
    Collections.sort(_conditions,new ObjectiveConditionComparator());
  }

  /**
   * Indicates if this objective has geo data.
   * @return <code>true</code> if it has geo data.
   */
  public boolean hasGeoData()
  {
    for(ObjectiveCondition condition : _conditions)
    {
      if (condition.hasGeoData())
      {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString()
  {
    StringBuilder sb=new StringBuilder();
    sb.append("Objective #").append(_index);
    sb.append(": ").append(_description).append(EndOfLine.NATIVE_EOL);
    if (_loreOverride.length()>0)
    {
      sb.append("Lore override: ").append(_loreOverride).append(EndOfLine.NATIVE_EOL);
    }
    if (_progressOverride.length()>0)
    {
      sb.append("Progress override: ").append(_progressOverride).append(EndOfLine.NATIVE_EOL);
    }
    if (_billboardOverride.length()>0)
    {
      sb.append("Billboard override: ").append(_billboardOverride).append(EndOfLine.NATIVE_EOL);
    }
    sb.append("Dialogs:").append(EndOfLine.NATIVE_EOL);
    for(DialogElement dialog : _dialogs)
    {
      sb.append('\t').append(dialog).append(EndOfLine.NATIVE_EOL);
    }
    sb.append("Conditions:").append(EndOfLine.NATIVE_EOL);
    for(ObjectiveCondition condition : _conditions)
    {
      sb.append('\t').append(condition).append(EndOfLine.NATIVE_EOL);
    }
    return sb.toString();
  }
}
