package deminer.controller;

import deminer.view.MainWindow;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public class Deminer extends MouseAdapter
{
    /**
     * 
     */
    public static final int EASY = 0;
    
    /**
     * 
     */
    public static final int MEDIUM = 1;
    
    /**
     * 
     */
    public static final int HARD = 2;
    
    /**
     * Game settings, first index is the difficulty and the second one is the
     * settings :<br/>
     *  * 0 : columns number<br/>
     *  * 1 : rows number<br/>
     *  * 2 : mines number
     */
    public static final int[][] SETTINGS = new int[3][3];
    
    /**
     * Reference to the main window view.
     */
    protected MainWindow mainWindow;
    
    /**
     * 
     */
    static
    {
        // Easy game SETTINGS
        Deminer.SETTINGS[Deminer.EASY][0] = Deminer.SETTINGS[Deminer.EASY][1] = 9;
        Deminer.SETTINGS[Deminer.EASY][2] = 10;
        
        // Easy game SETTINGS
        Deminer.SETTINGS[Deminer.MEDIUM][0] = Deminer.SETTINGS[Deminer.MEDIUM][1] = 16;
        Deminer.SETTINGS[Deminer.MEDIUM][2] = 40;
        
        // Easy game SETTINGS
        Deminer.SETTINGS[Deminer.HARD][0] = 16;
        Deminer.SETTINGS[Deminer.HARD][1] = 30;
        Deminer.SETTINGS[Deminer.HARD][2] = 99;
    }
    
    /**
     * 
     */
    public Deminer()
    {
        Deminer that = this;
        
        SwingUtilities.invokeLater(() ->
        {
            that.createNewGame(0);
        });
        
        // ...
        this.mainWindow = new MainWindow();
        this.mainWindow.setVisible(true);
    }
    
    protected void createNewGame(int difficulty)
    {
        // Create the model
        deminer.model.Grid gridModel = new deminer.model.Grid(Deminer.SETTINGS[difficulty][0], Deminer.SETTINGS[difficulty][1]);
        deminer.model.Cell[][] cellsModel = new deminer.model.Cell[Deminer.SETTINGS[difficulty][0]][Deminer.SETTINGS[difficulty][1]];
        Map<deminer.model.Cell, Point> cellsMap = new HashMap<>();
        
        for(int y = 0; y < Deminer.SETTINGS[difficulty][1]; y++)
        {
            for(int x = 0; x < Deminer.SETTINGS[difficulty][0]; x++)
            {
                cellsModel[x][y] = new deminer.model.Cell();
                cellsMap.put(cellsModel[x][y], new Point(x, y));
            }
        }
        
        // Create the view
        deminer.view.Cell[][] cellsView = new deminer.view.Cell[Deminer.SETTINGS[difficulty][0]][Deminer.SETTINGS[difficulty][1]];
        
        for(int y = 0; y < Deminer.SETTINGS[difficulty][1]; y++)
        {
            for(int x = 0; x < Deminer.SETTINGS[difficulty][0]; x++)
            {
                cellsView[x][y] = new deminer.view.Cell();
                // Link the model 
                cellsView[x][y].addMouseListener(this);
                cellsModel[x][y].addObserver(cellsView[x][y]);
            }
        }
    }
    
    protected void createNewGame(int cols, int rows, int minesNumber)
    {
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        deminer.view.Cell cellView = (deminer.view.Cell) e.getSource();
    }
    
    @Override
    public void mouseEntered(MouseEvent e)
    {
        
    }
    
    @Override
    public void mouseExited(MouseEvent e)
    {
        
    }
}
