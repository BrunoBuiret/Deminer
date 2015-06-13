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
    public static final Color[] TEXT_COLORS = new Color[8];
    
    /**
     * Initializes the array of text colors.
     */
    static
    {
        StyleUtilities.TEXT_COLORS[0] = Color.GREEN;
        StyleUtilities.TEXT_COLORS[1] = Color.BLUE;
        StyleUtilities.TEXT_COLORS[2] = Color.MAGENTA;
        StyleUtilities.TEXT_COLORS[3] = Color.CYAN;
        StyleUtilities.TEXT_COLORS[4] = Color.ORANGE;
        StyleUtilities.TEXT_COLORS[5] = Color.RED;
        StyleUtilities.TEXT_COLORS[6] = Color.PINK;
        StyleUtilities.TEXT_COLORS[7] = Color.BLACK;
    }
}
