package deminer.view;

import java.awt.GridLayout;
import javax.swing.JComponent;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public class Grid extends JComponent
{
    public void setCells(Cell[][] cells, int cols, int rows)
    {
        // First, erase the current cells
        this.removeAll();
        
        // Then, create the new grid
        this.setLayout(new GridLayout(rows, cols));
        
        for(int y = 0; y < rows; y++)
        {
            for(int x = 0; x < cols; x++)
            {
                this.add(cells[x][y]);
            }
        }
    }
}
