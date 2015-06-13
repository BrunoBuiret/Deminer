package deminer.utilities;

import deminer.controller.Deminer;

/**
 *
 * @author PtitBlond
 */
public abstract class DeminerUtilities
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
        DeminerUtilities.SETTINGS[DeminerUtilities.EASY][DeminerUtilities.COLS] = DeminerUtilities.SETTINGS[DeminerUtilities.EASY][DeminerUtilities.ROWS] = 9;
        DeminerUtilities.SETTINGS[DeminerUtilities.EASY][DeminerUtilities.MINES] = 10;
        
        // Medium game setings
        DeminerUtilities.SETTINGS[DeminerUtilities.MEDIUM][DeminerUtilities.COLS] = DeminerUtilities.SETTINGS[DeminerUtilities.MEDIUM][DeminerUtilities.ROWS] = 16;
        DeminerUtilities.SETTINGS[DeminerUtilities.MEDIUM][DeminerUtilities.MINES] = 40;
        
        // Hard game settings
        DeminerUtilities.SETTINGS[DeminerUtilities.HARD][DeminerUtilities.COLS] = 30;
        DeminerUtilities.SETTINGS[DeminerUtilities.HARD][DeminerUtilities.ROWS] = 16;
        DeminerUtilities.SETTINGS[DeminerUtilities.HARD][DeminerUtilities.MINES] = 99;
    }
}
