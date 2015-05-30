package model;

/**
 *
 * @author PtitBlond
 */
public class Cell
{
    protected Grid grid;
    
    protected boolean discovered;
    
    protected boolean trapped;
    
    protected boolean flagged;
    
    public Cell(Grid grid)
    {
        this.grid = grid;
        this.discovered = this.trapped = this.flagged = false;
    }
    
    public boolean isDiscovered()
    {
        return this.discovered;
    }
    
    public void setDiscovered(boolean discovered)
    {
        this.discovered = discovered;
    }
    
    public boolean isTrapped()
    {
        return this.trapped;
    }
    
    public void setTrapped(boolean trapped)
    {
        this.trapped = trapped;
    }
    
    public boolean isFlagged()
    {
        return this.flagged;
    }
    
    public void setFlag(boolean flagged)
    {
        this.flagged = flagged;
    }
    
    
}
