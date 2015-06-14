package deminer.utilities;

import deminer.view.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
class TimerData implements ActionListener
{
    /**
     * References to the main window.
     */
    protected MainWindow mainWindow;
    
    /**
     * Holds the number of elapsed seconds since the beginning of the
     * timer.
     */
    protected Integer elapsedTime;
    
    /**
     * Creates a new timer data holding the time.
     * 
     * @param mainWindow Reference to the main window.
     */
    public TimerData(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
        this.elapsedTime = 0;
    }
    
    /**
     * This method is called every second by Swing's timer so that it can
     * update the number of elapsed seconds.
     * 
     * @param e Reference to the action event.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        synchronized(this.elapsedTime)
        {
            this.elapsedTime++;
            this.mainWindow.setTimer(this.elapsedTime);
        }
    }
    
    /**
     * Resets the timer.
     */
    public void reset()
    {
        synchronized(this.elapsedTime)
        {
            this.elapsedTime = 0;
            this.mainWindow.setTimer(this.elapsedTime);
        }
    }
}

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public class Timer extends javax.swing.Timer
{
    /**
     * Reference to the timer data itself.
     */
    protected TimerData timerData;
    
    /**
     * Creates a new custom Swing timer. It is customized to compute
     * a game's duration.
     * 
     * @param mainWindow Reference to the main window.
     */
    public Timer(MainWindow mainWindow)
    {
        super(1000, new TimerData(mainWindow));
        this.setInitialDelay(1000);
        this.timerData = (TimerData) this.getActionListeners()[0];
    }
    
    /**
     * Resets the timer.
     */
    public void reset()
    {
        this.timerData.reset();
    }
}
