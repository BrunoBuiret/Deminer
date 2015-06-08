package deminer.controller;

import deminer.view.MainWindow;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
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
    
    protected Map<deminer.view.Cell, deminer.model.Cell> cellsCorrespondence;
    
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
        Deminer.SETTINGS[Deminer.HARD][0] = 30;
        Deminer.SETTINGS[Deminer.HARD][1] = 16;
        Deminer.SETTINGS[Deminer.HARD][2] = 99;
    }
    
    /**
     * 
     */
    public Deminer()
    {
        Deminer that = this;
        
        // ...
        this.mainWindow = new MainWindow(this);
        this.mainWindow.setVisible(true);
        
        SwingUtilities.invokeLater(() ->
        {
            that.createNewGame(Deminer.SETTINGS[Deminer.HARD][0], Deminer.SETTINGS[Deminer.HARD][1], Deminer.SETTINGS[Deminer.HARD][2]);
        });
    }
    
    /**
     * Creates a new custom game.
     * 
     * @param cols
     * @param rows
     * @param minesNumber 
     */
    public void createNewGame(int cols, int rows, int minesNumber)
    {
        // Create the model
        deminer.model.Cell[][] cellsModel = new deminer.model.Cell[cols][rows];
        Map<deminer.model.Cell, Point> cellsMap = new HashMap<>();
        
        for(int y = 0; y < rows; y++)
        {
            for(int x = 0; x < cols; x++)
            {
                cellsModel[x][y] = new deminer.model.Cell();
                cellsMap.put(cellsModel[x][y], new Point(x, y));
            }
        }
        
        deminer.model.Grid gridModel = new deminer.model.Grid(cellsModel, cols, rows, cellsMap);
        
        // Create the view
        deminer.view.Cell[][] cellsView = new deminer.view.Cell[cols][rows];
        this.cellsCorrespondence = new HashMap<>();
        
        for(int y = 0; y < rows; y++)
        {
            for(int x = 0; x < cols; x++)
            {
                cellsView[x][y] = new deminer.view.Cell();
                // Link the model 
                cellsView[x][y].addMouseListener(this);
                cellsModel[x][y].addObserver(cellsView[x][y]);
                // Establish the correspondence between the view and the model
                cellsCorrespondence.put(cellsView[x][y], cellsModel[x][y]);
            }
        }
        
        // Put the mines on the grid
        int n = 0, x, y;
        Random r = new Random();
        
        do
        {
            do
            {
                x = r.nextInt(cols);
                y = r.nextInt(rows);
            }
            while(cellsModel[x][y].isTrapped());
            
            cellsModel[x][y].setTrapped(true);
            
            //notify the neighbour cells
            for(int row = y - 1; row <= y + 1; row++)
            {
                for(int column = x - 1; column <= x + 1; column++)
                {
                    if(column >= 0 && column < cols && row >= 0 && row < rows && !cellsModel[column][row].isTrapped())
                    {
                       cellsModel[column][row].setMinesNumber(cellsModel[column][row].getMinesNumber() + 1);
                    }
                }
            }
            n++;
        }
        while(n < minesNumber);
        
        this.mainWindow.setCells(cellsView, cols, rows);
    }
    
    protected void propagateDiscovery(deminer.model.Cell firstCell)
    {
        if(!firstCell.isDiscovered())
        {
            Stack<deminer.model.Cell> stack = new Stack<>();
            stack.push(firstCell);
            
            while(!stack.empty())
            {
                deminer.model.Cell currentCell = stack.pop();
                Map<String, deminer.model.Cell> neighboursMap = currentCell.getGrid().getNeighbours(currentCell);
                
                currentCell.setDiscovered(true);

               
                if(neighboursMap.containsKey("north") && !neighboursMap.get("north").isDiscovered())
                {
                    stack.push(neighboursMap.get("north"));
                }
                
                if(neighboursMap.containsKey("south") && !neighboursMap.get("south").isDiscovered())
                {
                    stack.push(neighboursMap.get("south"));
                }
                
                if(neighboursMap.containsKey("east") && !neighboursMap.get("east").isDiscovered())
                {
                    stack.push(neighboursMap.get("east"));
                }
                
                if(neighboursMap.containsKey("west") && !neighboursMap.get("west").isDiscovered())
                {
                    stack.push(neighboursMap.get("west"));
                }
            }
        }
    }
    
    /**
     * Called when the user clicks on a cell.
     * 
     * @param e Reference to the mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        deminer.view.Cell cellView = (deminer.view.Cell) e.getSource();
        deminer.model.Cell cellModel = this.cellsCorrespondence.get(cellView);
        
        if(e.getButton() == MouseEvent.BUTTON1 && !cellModel.isFlagged() && !cellModel.isDiscovered())
        {
            this.propagateDiscovery(cellModel);
            
        }
        else if(e.getButton() == MouseEvent.BUTTON3)
        {
            cellModel.toggleFlag();
        }
    }
    
    /**
     * Called when the user's mouse enters a cell.
     * 
     * @param e Reference to the mouse event.
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {
        deminer.view.Cell cellView = (deminer.view.Cell) e.getSource();
        deminer.model.Cell cellModel = this.cellsCorrespondence.get(cellView);
        
        if(!cellModel.isDiscovered())
        {
            cellView.setBackground(deminer.view.Cell.HOVER_COLOR);
        }
    }
    
    /**
     * Called when the user's mouse exits a cell.
     * 
     * @param e Reference to the mouse event.
     */
    @Override
    public void mouseExited(MouseEvent e)
    {
        deminer.view.Cell cellView = (deminer.view.Cell) e.getSource();
        deminer.model.Cell cellModel = this.cellsCorrespondence.get(cellView);
        
        if(!cellModel.isDiscovered())
        {
            cellView.setBackground(null);
        }
    }
}
