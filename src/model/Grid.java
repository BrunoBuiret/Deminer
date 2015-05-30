package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PtitBlond
 */
public class Grid
{
    protected final int cols;
    
    protected final int rows;
    
    protected Cell[][] cells;
    
    protected Map<Cell, Point> cellsMap;
    
    public Grid(int cols, int rows)
    {
        this.cols = cols;
        this.rows = rows;
        this.cells = new Cell[cols][rows];
        this.cellsMap = new HashMap<Cell, Point>();
        
        for(int y = 0; y < rows; y++)
        {
            for(int x = 0; x < cols; x++)
            {
                this.cells[x][y] = new Cell(this);
                cellsMap.put(this.cells[x][y], new Point(x,y));
            }
        }
    }
    
    public List<Cell> getNeighbours(Cell cell)
    {
        List<Cell> neighbours = new ArrayList<>();
        Point point = this.cellsMap.get(cell);
        
        for(int y = (int) (point.getY() - 1); y <= point.getY() + 1; y++)
        {
            for(int x = (int) (point.getX() - 1); y <= point.getX() + 1; y++)
            {
                if(x >= 0 && y >= 0 && x < this.cols && y < this.rows)
                    if(x != point.getX() && y != point.getY())
                        neighbours.add(this.cells[x][y]);
            }
        }
        return neighbours;
    }
}
