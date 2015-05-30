package model;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public class Cell
{
    /**
     * Reference to the grid.
     */
    protected Grid grid;
    
    /**
     * Is the cell discovered ?
     */
    protected boolean discovered;
    
    /**
     * Is the cell trapped ?
     */
    protected boolean trapped;
    
    /**
     * Is the cell flagged ?
     */
    protected boolean flagged;
    
    /**
     * Creates a new cell.
     * 
     * @param grid Reference to the grid.
     */
    public Cell(Grid grid)
    {
        this.grid = grid;
        this.discovered = this.trapped = this.flagged = false;
    }
    
    /**
     * Tests if the cell is discovered.
     * 
     * @return <code>true</code> if the cell is discovered, <code>false</code>
     * otherwise.
     */
    public boolean isDiscovered()
    {
        return this.discovered;
    }
    
    /**
     * Sets if the cell is discovered.
     * 
     * @param discovered <code>true</code> if the cell is discovered, <code>false</code>
     * otherwise.
     */
    public void setDiscovered(boolean discovered)
    {
        this.discovered = discovered;
    }
    
    /**
     * Tests if the cell is trapped.
     * 
     * @return <code>true</code> if the cell is trapped, <code>false</code>
     * otherwise.
     */
    public boolean isTrapped()
    {
        return this.trapped;
    }
    
    /**
     * Sets if the cell is trapped.
     * 
     * @param trapped <code>true</code> if the cell is trapped, <code>false</code>
     * otherwise.
     */
    public void setTrapped(boolean trapped)
    {
        this.trapped = trapped;
    }
    
    /**
     * Tests if the cell is flagged.
     * 
     * @return <code>true</code> if the cell is flagged, <code>false</code>
     * otherwise.
     */
    public boolean isFlagged()
    {
        return this.flagged;
    }
    
    /**
     * Sets if the cell is flagged.
     * 
     * @param flagged <code>true</code> if the cell is flagged, <code>false</code>
     * otherwise.
     */
    public void setFlag(boolean flagged)
    {
        this.flagged = flagged;
    }
}
