package deminer.model;

import java.util.Observable;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public class Cell extends Observable
{
    /**
     * Neighbour mines number
     */
    protected int minesNumber;
    
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
     * reference to the grid
     */
    protected Grid grid;
    
    /**
     * Creates a new cell.
     */
    public Cell()
    {
        this.discovered = this.trapped = this.flagged = false;
        this.grid = null;
        this.minesNumber = 0;
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
        this.notifyObservers();
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
        this.notifyObservers();
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
        this.notifyObservers();
    }
    
    /**
     * Toggles the flag on the cell.
     */
    public void toggleFlag()
    {
        this.flagged = !this.flagged;
        this.notifyObservers();
    }
    
    /**
     * Gets a reference to the grid to which this cell belongs.
     * 
     * @return Grid's reference.
     */
    public Grid getGrid()
    {
        return this.grid;
    }
    
    /**
     * Sets the reference to the grid to which this cell belongs.
     * 
     * @param grid Grid's reference.
     */
    public void setGrid(Grid grid)
    {
        this.grid = grid;
    }
    
    /**
     * Gets the number of mines around this cell.
     * 
     * @return Number of mines.
     */
    public int getMinesNumber()
    {
        return this.minesNumber;
    }
    
    /**
     * Sets the number of mines around this cell.
     * 
     * @param minesNumber Number of mines.
     */
    public void setMinesNumber(int minesNumber)
    {
        this.minesNumber = minesNumber;
    }
    
    /**
     * Overrides {@link java.util.Observable#notifyObservers()} to avoid repeating
     * the {@link java.util.Observable#setChanged()} method.
     */
    @Override
    public void notifyObservers()
    {
        this.setChanged();
        super.notifyObservers();
    }
    
    /**
     * Overrides {@link java.util.Observable#notifyObservers(java.lang.Object)} to avoid repeating
     * the {@link java.util.Observable#setChanged()} method.
     */
    @Override
    public void notifyObservers(Object arg)
    {
        this.setChanged();
        super.notifyObservers(arg);
    }
}
