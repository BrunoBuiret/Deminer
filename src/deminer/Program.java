package deminer;

import deminer.controller.Deminer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public abstract class Program
{
    /**
     * Main entry point of the program.
     * 
     * @param args Reference to the command line arguments.
     */
    public static void main(String[] args)
    {
        // Get the OS style and apply it
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException e)
        {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        
        // Launch the controller
        Deminer d = new Deminer();
    }
}
