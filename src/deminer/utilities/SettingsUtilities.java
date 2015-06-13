package deminer.utilities;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public abstract class SettingsUtilities
{
    /**
     * References the easy difficulty.
     */
    public static final int EASY = 0;
    
    /**
     * References the medium difficulty.
     */
    public static final int MEDIUM = 1;
    
    /**
     * References the hard difficulty.
     */
    public static final int HARD = 2;
    
    /**
     * References the columns number in the settings.
     */
    public static final int COLS = 0;
    
    /**
     * References the rows number in the settings.
     */
    public static final int ROWS = 1;
    
    /**
     * References the mines number in the settings.
     */
    public static final int MINES = 2;
    
    /**
     * Game settings, first index is the difficulty and the second one is the
     * setting itself.
     */
    public static final int[][] SETTINGS = new int[3][3];
    
    /**
     * Initializes the settings of the game.
     */
    static
    {
        // Easy game settings
        SettingsUtilities.SETTINGS[SettingsUtilities.EASY][SettingsUtilities.COLS] = SettingsUtilities.SETTINGS[SettingsUtilities.EASY][SettingsUtilities.ROWS] = 9;
        SettingsUtilities.SETTINGS[SettingsUtilities.EASY][SettingsUtilities.MINES] = 10;
        
        // Medium game setings
        SettingsUtilities.SETTINGS[SettingsUtilities.MEDIUM][SettingsUtilities.COLS] = SettingsUtilities.SETTINGS[SettingsUtilities.MEDIUM][SettingsUtilities.ROWS] = 16;
        SettingsUtilities.SETTINGS[SettingsUtilities.MEDIUM][SettingsUtilities.MINES] = 40;
        
        // Hard game settings
        SettingsUtilities.SETTINGS[SettingsUtilities.HARD][SettingsUtilities.COLS] = 30;
        SettingsUtilities.SETTINGS[SettingsUtilities.HARD][SettingsUtilities.ROWS] = 16;
        SettingsUtilities.SETTINGS[SettingsUtilities.HARD][SettingsUtilities.MINES] = 99;
    }
}
