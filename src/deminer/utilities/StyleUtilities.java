package deminer.utilities;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public abstract class StyleUtilities
{
    /**
     * Reference to the border to use for a non discovered cell.
     */
    public static final Border NON_DISCOVERED_BORDER = BorderFactory.createBevelBorder(BevelBorder.RAISED);
    
    /**
     * Reference to the border to use for a discovered cell.
     */
    public static final Border DISCOVERED_BORDER = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
    
    /**
     * Background cell color when the user hovers over a cell.
     */
    public static final Color HOVER_COLOR = Color.GRAY;
    
    /**
     * Array of text colors.
     */
    public static final Color[] COLORS = new Color[8];
    
    /**
     * Initializes the array of text colors.
     */
    static
    {
        StyleUtilities.COLORS[0] = Color.GREEN;
        StyleUtilities.COLORS[1] = Color.BLUE;
        StyleUtilities.COLORS[2] = Color.MAGENTA;
        StyleUtilities.COLORS[3] = Color.CYAN;
        StyleUtilities.COLORS[4] = Color.ORANGE;
        StyleUtilities.COLORS[5] = Color.RED;
        StyleUtilities.COLORS[6] = Color.PINK;
        StyleUtilities.COLORS[7] = Color.BLACK;
    }
}
