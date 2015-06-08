package deminer.model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public class Grid
{
    /**
     * Grid's columns number.
     */
    protected final int cols;
    
    /**
     * Grid's rows number.
     */
    protected final int rows;
    
    /**
     * Grid itself.
     */
    protected Cell[][] cells;
    
    /**
     * Map used to get the cell's position from the cell itself.
     */
    protected Map<Cell, Point> cellsMap;
    
    /**
     * Creates a new grid model.
     * 
     * @param cells
     * @param cols Grid's columns number.
     * @param rows Grid's rows number.
     * @param cellsMap
     */
    public Grid(Cell[][] cells, int cols, int rows, Map<Cell, Point> cellsMap)
    {
        this.cols = cols;
        this.rows = rows;
        this.cells = cells;
        this.cellsMap = cellsMap;
        for(int y = 0; y < rows; y++)
        {
            for(int x = 0; x < cols; x++)
            {
                this.cells[x][y].setGrid(this);
            }
        }
    }
    
    /**
     * Gets a cell's list of neighbours.
     * 
     * @param cell Reference to the cell.
     * @return Cell's list of neighbours.
     */
    public Map<String, Cell> getNeighbours(Cell cell)
    {
        Map<String, Cell> neighbours = new HashMap<>();
        Point point = this.cellsMap.get(cell);
        
        if(point.getY() > 0)
            neighbours.put("north", this.cells[(int) point.getX()][(int) point.getY() - 1]);
        
        if(point.getY() < this.rows - 1)
            neighbours.put("south", this.cells[(int) point.getX()][(int) point.getY() + 1]);
        
        if(point.getX() > 0)
            neighbours.put("west", this.cells[(int) point.getX() - 1][(int) point.getY()]);
        
        if(point.getX() < this.cols - 1)
            neighbours.put("east", this.cells[(int) point.getX() + 1][(int) point.getY()]);
        
        return neighbours;
    }
}
