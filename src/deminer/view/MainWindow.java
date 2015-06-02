package deminer.view;

import deminer.controller.Deminer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public class MainWindow extends JFrame
{
    protected Grid grid;
    
    public MainWindow(Deminer deminer)
    {
        // Window parameters
        this.setTitle("Démineur");
        this.setIconImage((new ImageIcon(this.getClass().getResource("/deminer/resources/window-icon.png"))).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Build the game menu
        JMenu gameMenu = new JMenu("Partie");
        
        JMenu newGameMenu = new JMenu("Nouvelle partie");
        
        JMenuItem easyGameItem = new JMenuItem("Facile");
        JMenuItem mediumGameItem = new JMenuItem("Moyen");
        JMenuItem hardGameItem = new JMenuItem("Difficile");
        JMenuItem customGameItem = new JMenuItem("Personnalisée");
        
        JMenuItem statisticsItem = new JMenuItem("Statistiques");
        statisticsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        statisticsItem.setEnabled(false);
        
        JMenuItem exitItem = new JMenuItem("Quitter");
        
        newGameMenu.add(easyGameItem);
        newGameMenu.add(mediumGameItem);
        newGameMenu.add(hardGameItem);
        newGameMenu.addSeparator();
        newGameMenu.add(customGameItem);
        gameMenu.add(newGameMenu);
        gameMenu.addSeparator();
        gameMenu.add(statisticsItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);
        
        // Build the help menu
        JMenu helpMenu = new JMenu("?");
        
        JMenuItem helpItem = new JMenuItem("Aide");
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        
        JMenuItem aboutItem = new JMenuItem("A propos");
        
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);
        
        // Build the menubar
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);
        
        // Build the window's body
        this.add(this.grid = new Grid());
        
        // Update the window's size
        this.pack();
        
        easyGameItem.addActionListener((ActionEvent e) -> {
            deminer.createNewGame(Deminer.SETTINGS[Deminer.EASY][0], Deminer.SETTINGS[Deminer.EASY][1], Deminer.SETTINGS[Deminer.EASY][2]);
        });
        
        mediumGameItem.addActionListener((ActionEvent e) -> {
            deminer.createNewGame(Deminer.SETTINGS[Deminer.MEDIUM][0], Deminer.SETTINGS[Deminer.MEDIUM][1], Deminer.SETTINGS[Deminer.MEDIUM][2]);
        });
        
        hardGameItem.addActionListener((ActionEvent e) -> {
            deminer.createNewGame(Deminer.SETTINGS[Deminer.HARD][0], Deminer.SETTINGS[Deminer.HARD][1], Deminer.SETTINGS[Deminer.HARD][2]);
        });
        
        exitItem.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }
    
    public void setCells(Cell[][] cells, int cols, int rows)
    {
        this.grid.setCells(cells, cols, rows);
        this.pack();
    }
}
