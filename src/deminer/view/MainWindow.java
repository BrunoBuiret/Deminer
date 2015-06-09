package deminer.view;

import deminer.controller.Deminer;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public class MainWindow extends JFrame
{
    protected Grid grid;
    
    protected JLabel timer;
    
    protected JLabel remainingFlags;
    
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
        
        JMenuItem exitItem = new JMenuItem("Quitter");
        
        newGameMenu.add(easyGameItem);
        newGameMenu.add(mediumGameItem);
        newGameMenu.add(hardGameItem);
        newGameMenu.addSeparator();
        newGameMenu.add(customGameItem);
        gameMenu.add(newGameMenu);
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
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
        
        JPanel headerPanel = new JPanel();
        GridBagConstraints gridBagConstraints;
        GridBagLayout headerLayout = new GridBagLayout();
        headerLayout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        headerLayout.rowHeights = new int[] {0};
        headerPanel.setLayout(headerLayout);

        JLabel scoreIcon = new JLabel();
        scoreIcon.setIcon(new ImageIcon(this.getClass().getResource("/deminer/resources/game.png")));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        headerPanel.add(scoreIcon, gridBagConstraints);

        this.remainingFlags = new JLabel();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        headerPanel.add(remainingFlags, gridBagConstraints);

        this.timer = new JLabel();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        headerPanel.add(timer, gridBagConstraints);

        JLabel timerIcon = new JLabel();
        timerIcon.setIcon(new ImageIcon(this.getClass().getResource("/deminer/resources/clock.png")));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        headerPanel.add(timerIcon, gridBagConstraints);
        
        this.add(headerPanel);
        this.add(this.grid = new Grid());
        
        // Update the window's size
        this.pack();
        
        easyGameItem.addActionListener((ActionEvent e) ->
        {
            deminer.createNewGame(Deminer.SETTINGS[Deminer.EASY][0], Deminer.SETTINGS[Deminer.EASY][1], Deminer.SETTINGS[Deminer.EASY][2]);
        });
        
        mediumGameItem.addActionListener((ActionEvent e) ->
        {
            deminer.createNewGame(Deminer.SETTINGS[Deminer.MEDIUM][0], Deminer.SETTINGS[Deminer.MEDIUM][1], Deminer.SETTINGS[Deminer.MEDIUM][2]);
        });
        
        hardGameItem.addActionListener((ActionEvent e) ->
        {
            deminer.createNewGame(Deminer.SETTINGS[Deminer.HARD][0], Deminer.SETTINGS[Deminer.HARD][1], Deminer.SETTINGS[Deminer.HARD][2]);
        });
        
        MainWindow that = this;
        
        customGameItem.addActionListener((ActionEvent e) ->
        {
            JLabel colsNumberLabel = new JLabel("Nombre de colonnes");
            JSpinner colsNumber = new JSpinner(new SpinnerNumberModel(10, 1, 50, 1));
            JLabel rowsNumberLabel = new JLabel("Nombre de lignes");
            JSpinner rowsNumber = new JSpinner(new SpinnerNumberModel(10, 1, 50, 1));
            JLabel minesNumberLabel = new JLabel("Nombre de mines");
            JSpinner minesNumber = new JSpinner(new SpinnerNumberModel(10, 1, 2500, 1));
            
            JPanel panePanel = new JPanel(new GridLayout(3, 2));
            
            panePanel.add(colsNumberLabel);
            panePanel.add(colsNumber);
            panePanel.add(rowsNumberLabel);
            panePanel.add(rowsNumber);
            panePanel.add(minesNumberLabel);
            panePanel.add(minesNumber);
            
            int result = JOptionPane.showConfirmDialog(that, panePanel, "Partie personnalisée", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) 
            {
                if((int) minesNumber.getValue() < (int) colsNumber.getValue() * (int) rowsNumber.getValue())
                {
                    deminer.createNewGame((int) colsNumber.getValue(), (int) rowsNumber.getValue(), (int) minesNumber.getValue());
                }
                else
                {
                    JOptionPane.showMessageDialog(that, "Ce terrain ne peut pas contenir autant de mines.", "Paramètre invalide", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        exitItem.addActionListener((ActionEvent e) ->
        {
            System.exit(0);
        });
    }
    
    public void setCells(Cell[][] cells, int cols, int rows)
    {
        this.grid.setCells(cells, cols, rows);
        this.pack();
    }
    
    public void setRemainingFlags(int remainingFlags)
    {
        this.remainingFlags.setText(Integer.toString(remainingFlags));
    }
}
