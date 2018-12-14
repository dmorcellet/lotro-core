package delta.games.lotro.character.classes.io.xml;

/**
 * Constants for tags and attribute names used in the
 * XML persistence of class descriptions.
 * @author DAM
 */
public class ClassDescriptionXMLConstants
{
  /**
   * Tag 'classes'.
   */
  public static final String CLASSES_TAG="classes";
  /**
   * Tag 'class'.
   */
  public static final String CLASS_TAG="class";
  /**
   * Tag 'class', attribute 'key'.
   */
  public static final String CLASS_KEY_ATTR="key";
  /**
   * Tag 'class', attribute 'iconId'.
   */
  public static final String CLASS_ICON_ID_ATTR="iconId";
  /**
   * Tag 'class', attribute 'smallIconId'.
   */
  public static final String CLASS_SMALL_ICON_ID_ATTR="smallIconId";
  /**
   * Tag 'classTrait'.
   */
  public static final String CLASS_TRAIT_TAG="classTrait";
  /**
   * Tag 'classTrait', attribute 'minLevel'.
   */
  public static final String CLASS_TRAIT_MIN_LEVEL_ATTR="minLevel";
  /**
   * Tag 'classTrait', attribute 'traitId'.
   */
  public static final String CLASS_TRAIT_ID_ATTR="traitId";
  /**
   * Tag 'traitTree'.
   */
  public static final String TRAIT_TREE_TAG="traitTree";
  /**
   * Tag 'traitTreeBranch'.
   */
  public static final String TRAIT_TREE_BRANCH_TAG="traitTreeBranch";
  /**
   * Tag 'traitTreeBranch', attribute 'name'.
   */
  public static final String TRAIT_TREE_BRANCH_NAME_ATTR="name";
  /**
   * Tag 'progression'.
   */
  public static final String PROGRESSION_TAG="progression";
  /**
   * Tag 'step'.
   */
  public static final String STEP_TAG="step";
  /**
   * Tag 'step', attribute 'nbPoints'.
   */
  public static final String STEP_REQUIRED_POINTS_ATTR="nbPoints";
  /**
   * Tag 'step', attribute 'traitId'.
   */
  public static final String STEP_TRAIT_ID_ATTR="traitId";
  /**
   * Tag 'cells'.
   */
  public static final String CELLS_TAG="cells";
  /**
   * Tag 'cell'.
   */
  public static final String CELL_TAG="cell";
  /**
   * Tag 'cell', attribute 'id'.
   */
  public static final String CELL_ID_ATTR="id";
  /**
   * Tag 'cell', attribute 'traitId'.
   */
  public static final String CELL_TRAIT_ID_ATTR="traitId";
}