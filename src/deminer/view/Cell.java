package deminer.view;

import java.awt.Dimension;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public class Cell extends JLabel implements Observer
{
    protected static final Dimension MINIMUM_DIMENSION = new Dimension(10, 10);
    protected static final Dimension PREFERRED_DIMENSION = new Dimension(25, 25);
    protected static final Dimension MAXIMUM_DIMENSION = new Dimension(40, 40);
    
    protected static final Border NON_DISCOVERED_BORDER = BorderFactory.createBevelBorder(BevelBorder.RAISED);
    protected static final Border DISCOVERED_BORDER = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
    
    public static final Color HOVER_COLOR = Color.GRAY;
    
    /**
     * Creates a new view of a cell.
     */
    public Cell()
    {
        this.setMinimumSize(Cell.MINIMUM_DIMENSION);
        this.setPreferredSize(Cell.PREFERRED_DIMENSION);
        this.setMaximumSize(Cell.MAXIMUM_DIMENSION);
        this.setBorder(Cell.NON_DISCOVERED_BORDER);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Updates the appareance of the cell according to the associated model cell.
     * 
     * @param o Reference to the updated object.
     * @param arg Reference to any accompanying argument.
     */
    @Override
    public void update(Observable o, Object arg)
    {
        if(o instanceof deminer.model.Cell)
        {
            deminer.model.Cell model = (deminer.model.Cell) o;
            
            if(model.isDiscovered())
            {
                this.setBorder(Cell.DISCOVERED_BORDER);
                
                if(model.isTrapped())
                {
                    this.setIcon(new ImageIcon(this.getClass().getResource("/deminer/resources/bomb.png")));
                }
                else
                {
                    if(arg instanceof Integer)
                        this.setText(((Integer) arg).toString());
                }
            }
            else
            {
                if(model.isFlagged())
                {
                    this.setIcon(new ImageIcon(this.getClass().getResource("/deminer/resources/flag.png")));
                }
                else
                {
                    this.setIcon(null);
                }
            }
        }
    }
}
