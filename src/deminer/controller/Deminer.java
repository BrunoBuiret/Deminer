package deminer.controller;

import deminer.utilities.DeminerUtilities;
import deminer.view.MainWindow;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public class Deminer extends MouseAdapter
{
    /**
     * Reference to the main window view.
     */
    protected MainWindow mainWindow;
    
    protected deminer.model.Grid gridModel;
    
    protected Map<deminer.view.Cell, deminer.model.Cell> cellsCorrespondence;
    
    protected int undiscoveredCells;
    
    protected int flagsNumber;
    
    protected int minesNumber;
    
    /**
     * 
     */
    public Deminer()
    {
        Deminer that = this;
        
        // Initialize the window
        this.mainWindow = new MainWindow(this);
        this.mainWindow.setVisible(true);
        
        // Initialize the game components
        this.gridModel = null;
        this.undiscoveredCells = 0;
        this.minesNumber = 0;
        this.flagsNumber = 0;
        
        // Launch an easy game when possible
        SwingUtilities.invokeLater(() ->
        {
            that.createNewGame(
                DeminerUtilities.SETTINGS[DeminerUtilities.EASY][DeminerUtilities.COLS],
                DeminerUtilities.SETTINGS[DeminerUtilities.EASY][DeminerUtilities.ROWS],
                DeminerUtilities.SETTINGS[DeminerUtilities.EASY][DeminerUtilities.MINES]
            );
        });
    }
    
    /**
     * Creates a new game.
     * 
     * @param cols Number of columns.
     * @param rows Number of rows.
     * @param minesNumber Number of mines.
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
                // Create the cell
                cellsModel[x][y] = new deminer.model.Cell();
                // Memorize its position in the grid
                cellsMap.put(cellsModel[x][y], new Point(x, y));
            }
        }
        
        this.gridModel = new deminer.model.Grid(cellsModel, cols, rows, cellsMap);
        
        // Create the view
        deminer.view.Cell[][] cellsView = new deminer.view.Cell[cols][rows];
        this.cellsCorrespondence = new HashMap<>();
        
        for(int y = 0; y < rows; y++)
        {
            for(int x = 0; x < cols; x++)
            {
                // Create the cell
                cellsView[x][y] = new deminer.view.Cell();
                // Link to the model 
                cellsView[x][y].addMouseListener(this);
                cellsModel[x][y].addObserver(cellsView[x][y]);
                // Establish the correspondence between the view and the model
                this.cellsCorrespondence.put(cellsView[x][y], cellsModel[x][y]);
            }
        }
        
        // Put the mines on the grid randomly
        int n = 0, x, y;
        Random r = new Random();
        
        do
        {
            // Determine a position which isn't already trapped
            do
            {
                x = r.nextInt(cols);
                y = r.nextInt(rows);
            }
            while(cellsModel[x][y].isTrapped());
            
            // Trap this new position
            cellsModel[x][y].setTrapped(true);
            
            // Notify the neighbour cells there's a new bomb around
            for(int row = y - 1; row <= y + 1; row++)
                for(int column = x - 1; column <= x + 1; column++)
                    if(column >= 0 && column < cols && row >= 0 && row < rows && !cellsModel[column][row].isTrapped())
                       cellsModel[column][row].setMinesNumber(cellsModel[column][row].getMinesNumber() + 1);
            
            n++;
        }
        while(n < minesNumber);
        
        // Notity the window of the game
        this.mainWindow.setCells(cellsView, cols, rows);
        this.mainWindow.setRemainingFlags(minesNumber);
        
        // Initialize the game components
        this.undiscoveredCells = cols * rows;
        this.minesNumber = minesNumber;
        this.flagsNumber = 0;
    }
    
    /**
     * Propagates the discovery around <code>firstCell</code> if needed.
     * 
     * @param firstCell Reference to the cell the user clicked on.
     */
    protected void propagateDiscovery(deminer.model.Cell firstCell)
    {
        if(!firstCell.isDiscovered())
        {
            Stack<deminer.model.Cell> stack = new Stack<>();
            stack.push(firstCell);
            
            while(!stack.empty())
            {
                deminer.model.Cell currentCell = stack.pop();
                
                // Avoid notifying the same multiple times by testing if it's already discovered
                if(!currentCell.isDiscovered())
                {
                    List<deminer.model.Cell> neighboursList = currentCell.getGrid().getNeighbours(currentCell);
                    currentCell.setDiscovered(true);
                    this.undiscoveredCells--;
                
                    for(deminer.model.Cell neighbourCell : neighboursList)
                        if(!neighbourCell.isDiscovered() && !neighbourCell.isFlagged() && currentCell.getMinesNumber() == 0)
                            stack.push(neighbourCell);
                }
            }
        }
    }
    
    /**
     * Discovers the whole board.
     */
    public void discoverAll()
    {
        deminer.model.Cell[][] cells = this.gridModel.getCells();
        
        for(deminer.model.Cell[] x : cells)
            for(deminer.model.Cell y : x)
                if(!y.isDiscovered())
                    y.setDiscovered(true);
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
            Deminer that = this;
            
            if(!cellModel.isTrapped())
            {
                this.propagateDiscovery(cellModel);
                
                if(this.undiscoveredCells == this.minesNumber)
                {
                    SwingUtilities.invokeLater(() -> 
                    {
                        that.discoverAll();
                    });
                    
                    JOptionPane.showMessageDialog(this.mainWindow, "Vous avez gagné.", "Victoire", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                SwingUtilities.invokeLater(() -> 
                {
                    that.discoverAll();
                });
                
                JOptionPane.showMessageDialog(this.mainWindow, "Vous avez cliqué sur une mine.", "Défaite", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
        else if(e.getButton() == MouseEvent.BUTTON3 && !cellModel.isDiscovered())
        {
            cellModel.toggleFlag();
            this.flagsNumber += cellModel.isFlagged() ? 1: -1;
            this.mainWindow.setRemainingFlags(this.minesNumber - this.flagsNumber);
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
