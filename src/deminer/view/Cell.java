package deminer.view;

import deminer.utilities.StyleUtilities;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public class Cell extends JLabel implements Observer
{
    
    
    /**
     * Creates a new view of a cell.
     */
    public Cell()
    {
        this.setMinimumSize(new Dimension(10, 10));
        this.setPreferredSize(new Dimension(25, 25));
        this.setMaximumSize(new Dimension(40, 40));
        this.setBorder(StyleUtilities.NON_DISCOVERED_BORDER);
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
                this.setBorder(StyleUtilities.DISCOVERED_BORDER);
                this.setBackground(null);
                
                if(model.isTrapped())
                {
                    this.setIcon(new ImageIcon(this.getClass().getResource("/deminer/resources/bomb.png")));
                }
                else
                {
                    if(model.isFlagged())
                    {
                        this.setIcon(new ImageIcon(this.getClass().getResource("/deminer/resources/flag-black.png")));
                    }
                    else if(model.getMinesNumber() > 0)
                    {
                        this.setForeground(StyleUtilities.COLORS[model.getMinesNumber() - 1]);
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
