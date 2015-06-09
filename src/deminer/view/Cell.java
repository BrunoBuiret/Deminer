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
    protected static final Color[] COLORS = new Color[8];
    
    static
    {
        Cell.COLORS[0] = Color.GREEN;
        Cell.COLORS[1] = Color.BLUE;
        Cell.COLORS[2] = Color.MAGENTA;
        Cell.COLORS[3] = Color.CYAN;
        Cell.COLORS[4] = Color.ORANGE;
        Cell.COLORS[5] = Color.RED;
        Cell.COLORS[6] = Color.pink;
        Cell.COLORS[7] = Color.BLACK;
    }
    
    
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
                this.setBackground(null);
                
                if(model.isTrapped())
                {
                    this.setIcon(new ImageIcon(this.getClass().getResource("/deminer/resources/bomb.png")));
                }
                else
                {
                    if(model.getMinesNumber() > 0)
                    {
                        this.setForeground(Cell.COLORS[model.getMinesNumber() - 1]);
                        this.setText(Integer.toString(model.getMinesNumber()));
                    }
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
