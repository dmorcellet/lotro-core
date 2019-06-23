package delta.games.lotro.lore.quests.objectives.io.xml;

import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import delta.common.utils.xml.DOMParsingTools;
import delta.games.lotro.character.skills.SkillDescription;
import delta.games.lotro.lore.geo.LandmarkDescription;
import delta.games.lotro.lore.items.Item;
import delta.games.lotro.lore.npc.NpcDescription;
import delta.games.lotro.lore.quests.Achievable;
import delta.games.lotro.lore.quests.objectives.ConditionType;
import delta.games.lotro.lore.quests.objectives.DefaultObjectiveCondition;
import delta.games.lotro.lore.quests.objectives.FactionLevelCondition;
import delta.games.lotro.lore.quests.objectives.InventoryItemCondition;
import delta.games.lotro.lore.quests.objectives.ItemCondition;
import delta.games.lotro.lore.quests.objectives.ItemUsedCondition;
import delta.games.lotro.lore.quests.objectives.LandmarkDetectionCondition;
import delta.games.lotro.lore.quests.objectives.MonsterDiedCondition;
import delta.games.lotro.lore.quests.objectives.NpcTalkCondition;
import delta.games.lotro.lore.quests.objectives.SkillUsedCondition;
import delta.games.lotro.lore.quests.objectives.MonsterDiedCondition.MobSelection;
import delta.games.lotro.lore.quests.objectives.Objective;
import delta.games.lotro.lore.quests.objectives.ObjectiveCondition;
import delta.games.lotro.lore.quests.objectives.ObjectivesManager;
import delta.games.lotro.lore.quests.objectives.QuestCompleteCondition;
import delta.games.lotro.lore.reputation.Faction;
import delta.games.lotro.utils.Proxy;

/**
 * Parser for quests/deeds objectives stored in XML.
 * @author DAM
 */
public class ObjectivesXMLParser
{
  /**
   * Load objectives from XML.
   * @param root Objectives description tag.
   * @param objectives Storage for loaded data. 
   */
  public static void loadObjectives(Element root, ObjectivesManager objectives)
  {
    Element objectivesTag=DOMParsingTools.getChildTagByName(root,ObjectivesXMLConstants.OBJECTIVES_TAG);
    if (objectivesTag!=null)
    {
      List<Element> objectiveTags=DOMParsingTools.getChildTagsByName(objectivesTag,ObjectivesXMLConstants.OBJECTIVE_TAG);
      for(Element objectiveTag : objectiveTags)
      {
        parseObjectiveTag(objectives, objectiveTag);
      }
    }
  }

  private static void parseObjectiveTag(ObjectivesManager objectives, Element objectiveTag)
  {
    Objective objective=new Objective();
    NamedNodeMap attrs=objectiveTag.getAttributes();
    // Index
    int objectiveIndex=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.OBJECTIVE_INDEX_ATTR,1);
    objective.setIndex(objectiveIndex);
    // Text
    String text=DOMParsingTools.getStringAttribute(attrs,ObjectivesXMLConstants.OBJECTIVE_TEXT_ATTR,"");
    objective.setText(text);

    List<Element> conditionTags=DOMParsingTools.getChildTags(objectiveTag);
    for(Element conditionTag : conditionTags)
    {
      ObjectiveCondition condition=parseConditionTag(conditionTag);
      objective.addCondition(condition);
    }
    objectives.addObjective(objective);
  }

  private static ObjectiveCondition parseConditionTag(Element conditionTag)
  {
    ObjectiveCondition ret=null;
    String tagName=conditionTag.getTagName();
    NamedNodeMap attrs=conditionTag.getAttributes();
    // Index
    int index=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.CONDITION_INDEX_ATTR,0);
    // Lore info
    String loreInfo=DOMParsingTools.getStringAttribute(attrs,ObjectivesXMLConstants.CONDITION_LORE_INFO_ATTR,null);
    // Progress override
    String progressOverride=DOMParsingTools.getStringAttribute(attrs,ObjectivesXMLConstants.CONDITION_PROGRESS_OVERRIDE_ATTR,null);

    // Specifics
    if (ObjectivesXMLConstants.QUEST_COMPLETE_TAG.equals(tagName))
    {
      ret=parseQuestCompleteCondition(attrs,conditionTag);
    }
    else if (ObjectivesXMLConstants.MONSTER_DIED_TAG.equals(tagName))
    {
      ret=parseMonsterDiedCondition(attrs,conditionTag);
    }
    else if (ObjectivesXMLConstants.LANDMARK_DETECTION_TAG.equals(tagName))
    {
      ret=parseLandmarkDetectionCondition(attrs,conditionTag);
    }
    else if (ObjectivesXMLConstants.INVENTORY_ITEM_TAG.equals(tagName))
    {
      ret=parseInventoryItemCondition(attrs,conditionTag);
    }
    else if (ObjectivesXMLConstants.ITEM_USED_TAG.equals(tagName))
    {
      ret=parseItemUsedCondition(attrs,conditionTag);
    }
    else if (ObjectivesXMLConstants.FACTION_LEVEL_TAG.equals(tagName))
    {
      ret=parseFactionLevelCondition(attrs,conditionTag);
    }
    else if (ObjectivesXMLConstants.SKILL_USED_TAG.equals(tagName))
    {
      ret=parseSkillUsedCondition(attrs,conditionTag);
    }
    else if (ObjectivesXMLConstants.NPC_TALK_TAG.equals(tagName))
    {
      ret=parseNpcTalkCondition(attrs,conditionTag);
    }
    else
    {
      ret=parseDefaultCondition(attrs,conditionTag);
    }
    if (ret!=null)
    {
      ret.setIndex(index);
      ret.setLoreInfo(loreInfo);
      ret.setProgressOverride(progressOverride);
    }
    return ret;
  }

  private static QuestCompleteCondition parseQuestCompleteCondition(NamedNodeMap attrs, Element conditionTag)
  {
    QuestCompleteCondition condition=new QuestCompleteCondition();
    // Achievable
    int achievableId=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.QUEST_COMPLETE_ACHIEVABLE_ID_ATTR,0);
    if (achievableId>0)
    {
      Proxy<Achievable> proxy=new Proxy<Achievable>();
      proxy.setId(achievableId);
      condition.setProxy(proxy);
    }
    // Quest category
    String questCategory=DOMParsingTools.getStringAttribute(attrs,ObjectivesXMLConstants.QUEST_COMPLETE_QUEST_CATEGORY_ATTR,null);
    condition.setQuestCategory(questCategory);
    // Count
    int count=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.QUEST_COMPLETE_COUNT_ATTR,1);
    condition.setCompletionCount(count);
    return condition;
  }

  private static MonsterDiedCondition parseMonsterDiedCondition(NamedNodeMap attrs, Element conditionTag)
  {
    MonsterDiedCondition condition=new MonsterDiedCondition();
    // Mob ID
    int mobId=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.MONSTER_DIE_MOB_ID_ATTR,0);
    if (mobId>0)
    {
      condition.setMobId(Integer.valueOf(mobId));
    }
    // Mob name
    String mobName=DOMParsingTools.getStringAttribute(attrs,ObjectivesXMLConstants.MONSTER_DIE_MOB_NAME_ATTR,null);
    condition.setMobName(mobName);
    // Count
    int count=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.MONSTER_DIE_COUNT_ATTR,1);
    condition.setCount(count);
    // Mob selections
    List<Element> monsterSelectionTags=DOMParsingTools.getChildTagsByName(conditionTag,ObjectivesXMLConstants.MONSTER_SELECTION_TAG);
    for(Element monsterSelectionTag : monsterSelectionTags)
    {
      NamedNodeMap selectionAttrs=monsterSelectionTag.getAttributes();
      MobSelection selection=new MobSelection();
      // Where
      String where=DOMParsingTools.getStringAttribute(selectionAttrs,ObjectivesXMLConstants.MONSTER_SELECTION_WHERE_ATTR,null);
      selection.setWhere(where);
      // What
      String what=DOMParsingTools.getStringAttribute(selectionAttrs,ObjectivesXMLConstants.MONSTER_SELECTION_WHAT_ATTR,null);
      selection.setWhat(what);
      condition.getMobSelections().add(selection);
    }
    return condition;
  }

  private static LandmarkDetectionCondition parseLandmarkDetectionCondition(NamedNodeMap attrs, Element conditionTag)
  {
    LandmarkDetectionCondition condition=new LandmarkDetectionCondition();
    // Landmark proxy
    // - id
    int landmarkId=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.LANDMARK_DETECTION_ID_ATTR,0);
    // - name
    String landmarkName=DOMParsingTools.getStringAttribute(attrs,ObjectivesXMLConstants.LANDMARK_DETECTION_NAME_ATTR,"?");
    Proxy<LandmarkDescription> proxy=new Proxy<LandmarkDescription>();
    proxy.setId(landmarkId);
    proxy.setName(landmarkName);
    condition.setLandmarkProxy(proxy);
    return condition;
  }

  private static InventoryItemCondition parseInventoryItemCondition(NamedNodeMap attrs, Element conditionTag)
  {
    InventoryItemCondition condition=new InventoryItemCondition();
    parseItemCondition(condition,attrs,conditionTag);
    return condition;
  }

  private static ItemUsedCondition parseItemUsedCondition(NamedNodeMap attrs, Element conditionTag)
  {
    ItemUsedCondition condition=new ItemUsedCondition();
    parseItemCondition(condition,attrs,conditionTag);
    return condition;
  }

  private static void parseItemCondition(ItemCondition condition, NamedNodeMap attrs, Element conditionTag)
  {
    // Item proxy
    // - id
    int itemId=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.ITEM_ID_ATTR,0);
    // - name
    String itemName=DOMParsingTools.getStringAttribute(attrs,ObjectivesXMLConstants.ITEM_NAME_ATTR,"?");
    Proxy<Item> proxy=new Proxy<Item>();
    proxy.setId(itemId);
    proxy.setName(itemName);
    condition.setProxy(proxy);
    // Count
    int count=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.ITEM_COUNT_ATTR,1);
    condition.setCount(count);
  }

  private static FactionLevelCondition parseFactionLevelCondition(NamedNodeMap attrs, Element conditionTag)
  {
    FactionLevelCondition condition=new FactionLevelCondition();
    // Faction proxy
    // - id
    int factionId=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.FACTION_LEVEL_ID_ATTR,0);
    // - name
    String factionName=DOMParsingTools.getStringAttribute(attrs,ObjectivesXMLConstants.FACTION_LEVEL_NAME_ATTR,"?");
    Proxy<Faction> proxy=new Proxy<Faction>();
    proxy.setId(factionId);
    proxy.setName(factionName);
    condition.setProxy(proxy);
    // Tier
    int tier=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.FACTION_LEVEL_TIER_ATTR,1);
    condition.setTier(tier);
    return condition;
  }

  private static SkillUsedCondition parseSkillUsedCondition(NamedNodeMap attrs, Element conditionTag)
  {
    SkillUsedCondition condition=new SkillUsedCondition();
    // Skill proxy
    // - id
    int skillId=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.SKILL_USED_SKILL_ID_ATTR,0);
    // - name
    String skillName=DOMParsingTools.getStringAttribute(attrs,ObjectivesXMLConstants.SKILL_USED_SKILL_NAME_ATTR,"?");
    Proxy<SkillDescription> proxy=new Proxy<SkillDescription>();
    proxy.setId(skillId);
    proxy.setName(skillName);
    condition.setProxy(proxy);
    // Count
    int count=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.SKILL_USED_COUNT_ATTR,1);
    condition.setCount(count);
    // Max per day
    int maxPerDay=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.SKILL_USED_MAX_PER_DAY_ATTR,-1);
    if (maxPerDay!=-1)
    {
      condition.setMaxPerDay(Integer.valueOf(maxPerDay));
    }
    return condition;
  }

  private static NpcTalkCondition parseNpcTalkCondition(NamedNodeMap attrs, Element conditionTag)
  {
    NpcTalkCondition condition=new NpcTalkCondition();
    // NPC proxy
    // - id
    int npcId=DOMParsingTools.getIntAttribute(attrs,ObjectivesXMLConstants.NPC_TALK_NPC_ID_ATTR,0);
    // - name
    String npcName=DOMParsingTools.getStringAttribute(attrs,ObjectivesXMLConstants.NPC_TALK_NPC_NAME_ATTR,"?");
    Proxy<NpcDescription> proxy=new Proxy<NpcDescription>();
    proxy.setId(npcId);
    proxy.setName(npcName);
    condition.setProxy(proxy);
    return condition;
  }

  private static DefaultObjectiveCondition parseDefaultCondition(NamedNodeMap attrs, Element conditionTag)
  {
    // Type
    String typeStr=DOMParsingTools.getStringAttribute(attrs,ObjectivesXMLConstants.CONDITION_TYPE_ATTR,null);
    ConditionType type=null;
    if (typeStr!=null)
    {
      type=ConditionType.valueOf(typeStr);
    }
    DefaultObjectiveCondition condition=new DefaultObjectiveCondition(type);
    return condition;
  }
}