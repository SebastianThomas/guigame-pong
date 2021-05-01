package guigame.gui.panes;

import guigame.logic.Constants;

import javax.swing.*;

/**
 * Implementation of a {@code JFrame} for specific colors and eventually other parameters.
 *
 * @see Constants#fgColor
 * @see Constants#bgColor
 */
public class GUIFrame extends JFrame {
    /**
     * Initialize a new {@code GUIFrame} with a specific title and set the right colors.
     * Does not show the frame; invoke {@code setVisible(true)} to show it.
     *
     * @param title the window title (in the title bar on the left-hand-side)
     * @see JFrame#setVisible(boolean)
     * @see Constants#fgColor
     * @see Constants#bgColor
     */
    public GUIFrame(String title) {
        super(title);

        this.initialize();
    }

    private void initialize() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initColors();
    }

    /**
     * Sets the right foreground- and background-colors.
     *
     * @see Constants#fgColor
     * @see Constants#bgColor
     */
    private void initColors() {
        this.setBackground(Constants.bgColor);
        this.setForeground(Constants.fgColor);
    }
}
