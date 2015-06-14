package deminer.view;

import deminer.controller.Deminer;
import deminer.utilities.SettingsUtilities;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
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
import javax.swing.SwingConstants;

/**
 * @author Bruno Buiret, Thomas Arnaud
 */
public class MainWindow extends JFrame
{
    /**
     * Reference to the grid view.
     */
    protected Grid grid;
    
    /**
     * Reference to the label displaying the time.
     */
    protected JLabel timer;
    
    /**
     * Reference to the label displaying the number of flags left.
     */
    protected JLabel remainingFlags;
    
    /**
     * Creates a new main window.
     * 
     * @param deminer Reference to the deminer controller.
     */
    public MainWindow(Deminer deminer)
    {
        // Window parameters
        this.setTitle("Démineur");
        this.setIconImage((new ImageIcon(this.getClass().getResource("/deminer/resources/window-icon.png"))).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
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
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gridBagConstraints;
        
        layout.columnWidths = new int[]{0, 5, 0, 5, 0, 5, 0};
        layout.rowHeights = new int[]{0, 0};
        this.getContentPane().setLayout(layout);
        
        JLabel scoreIcon = new JLabel();
        scoreIcon.setHorizontalAlignment(SwingConstants.CENTER);
        scoreIcon.setIcon(new ImageIcon(this.getClass().getResource("/deminer/resources/game.png")));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 0);
        this.getContentPane().add(scoreIcon, gridBagConstraints);
        
        this.remainingFlags = new JLabel();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        this.getContentPane().add(this.remainingFlags, gridBagConstraints);
        
        this.timer = new JLabel();
        this.timer.setText("0 s");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        this.getContentPane().add(this.timer, gridBagConstraints);
        
        JLabel timerIcon = new JLabel();
        timerIcon.setHorizontalAlignment(SwingConstants.CENTER);
        timerIcon.setIcon(new ImageIcon(this.getClass().getResource("/deminer/resources/clock.png")));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new Insets(5, 0, 5, 5);
        this.getContentPane().add(timerIcon, gridBagConstraints);
        
        this.grid = new Grid();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        this.getContentPane().add(this.grid, gridBagConstraints);
        
        // Update the window's size
        this.pack();
        
        // Define the menu items' action
        easyGameItem.addActionListener((ActionEvent e) ->
        {
            deminer.createNewGame(SettingsUtilities.SETTINGS[SettingsUtilities.EASY][SettingsUtilities.COLS],
                SettingsUtilities.SETTINGS[SettingsUtilities.EASY][SettingsUtilities.ROWS],
                SettingsUtilities.SETTINGS[SettingsUtilities.EASY][SettingsUtilities.MINES]
            );
        });
        
        mediumGameItem.addActionListener((ActionEvent e) ->
        {
            deminer.createNewGame(SettingsUtilities.SETTINGS[SettingsUtilities.MEDIUM][SettingsUtilities.COLS],
                SettingsUtilities.SETTINGS[SettingsUtilities.MEDIUM][SettingsUtilities.ROWS],
                SettingsUtilities.SETTINGS[SettingsUtilities.MEDIUM][SettingsUtilities.MINES]
            );
        });
        
        hardGameItem.addActionListener((ActionEvent e) ->
        {
            deminer.createNewGame(SettingsUtilities.SETTINGS[SettingsUtilities.HARD][SettingsUtilities.COLS],
                SettingsUtilities.SETTINGS[SettingsUtilities.HARD][SettingsUtilities.ROWS],
                SettingsUtilities.SETTINGS[SettingsUtilities.HARD][SettingsUtilities.MINES]
            );
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
        
        helpItem.addActionListener((ActionEvent e) ->
        {
            JOptionPane.showMessageDialog(
                that,
                "Le but du jeu est de révéler l'ensemble du plateau sans cliquer" +
                " sur une mine.\nUn clic gauche permet de révéler une case et" +
                " un clic droit permet de marquer une case comme étant piégée." +
                " Si un nombre est présent dans une case alors il indique le nombre" +
                " de bombes adjacentes à celle-ci.",
                "Aide",
                JOptionPane.PLAIN_MESSAGE
            );
        });
        
        aboutItem.addActionListener((ActionEvent e) ->
        {
            JOptionPane.showMessageDialog(
                that,
                "Démineur créé dans le cadre du projet informatique encadré de la" +
                " troisième année d'informatique à Polytech Lyon par Bruno Buiret" +
                " et Thomas Arnaud.",
                "A propos",
                JOptionPane.PLAIN_MESSAGE
            );
        });
    }
    
    /**
     * Assigns a new matrix of cells to the window.
     * 
     * @param cells The cells themselves.
     * @param cols Number of columns.
     * @param rows Number of rows.
     */
    public void setCells(Cell[][] cells, int cols, int rows)
    {
        this.grid.setCells(cells, cols, rows);
        this.pack();
    }
    
    /**
     * Writes the number of remaining flags on the window.
     * 
     * @param remainingFlags Number of remaining flags.
     */
    public void setRemainingFlags(int remainingFlags)
    {
        this.remainingFlags.setText(Integer.toString(remainingFlags));
    }
    
    /**
     * 
     * @param elapsedTime
     */
    public void setTimer(int elapsedTime)
    {
        StringBuilder timerText = new StringBuilder();
        
        if(elapsedTime > 3600)
        {
            timerText.append((int) elapsedTime / 3600).append("h");
            elapsedTime -= 3600 * ((int) elapsedTime / 3600);
        }
        
        if(elapsedTime > 60)
        {
            timerText.append(String.format("%02d", (int) elapsedTime / 60)).append("m");
            elapsedTime -= 60 * ((int) elapsedTime / 60);
        }
        
        timerText.append(String.format("%02d", elapsedTime)).append("s");
        
        this.timer.setText(timerText.toString());
    }
}
