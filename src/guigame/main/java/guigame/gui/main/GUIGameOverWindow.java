package guigame.gui.main;

import guigame.gui.menu.GUIGameOverMenu;
import guigame.gui.panes.GUIFrame;

import javax.swing.*;

/**
 * Implementation of {@code JFrame}: a Frame which is shown when the game is over.
 * It displays the message "Game Over" and a message to show which player has won.
 * Furthermore, there are three buttons:
 * <p>
 * -Restart the game,
 * </p>
 * <p>
 * - exit the game and
 * </p>
 * <p>
 * - back to main menu
 * </p>
 */
public class GUIGameOverWindow extends GUIFrame {
    private final GUIGameOverMenu guiGameOverMenu;
    private int width = 750;
    private int height = 300;

    /**
     * Create a new {@code GUIGameOverWindow}.
     *
     * @param guiGameOverMenu The {@code GUIGameOverMenu} to show.
     */
    public GUIGameOverWindow(GUIGameOverMenu guiGameOverMenu) {
        super("Game Over");

        // Set menu
        this.guiGameOverMenu = guiGameOverMenu;

        // Initialize window
        this.init();
    }

    /**
     * Initialize the window and show it.
     */
    private void init() {
        // Set window size
        this.setSize(this.width, this.height);
        // Place in the middle
        this.setLocationRelativeTo(null);

        // Add the GUI-menu
        this.add(this.guiGameOverMenu);

        // Set visible
        SwingUtilities.invokeLater(() -> this.setVisible(true));
    }
}
