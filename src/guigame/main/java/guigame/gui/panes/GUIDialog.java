package guigame.gui.panes;

import guigame.logic.Constants;

import javax.swing.*;

/**
 * Implementation of {@code JDialog} with specified colors.
 *
 * @see Constants#fgColor
 * @see Constants#bgColor
 */
public class GUIDialog extends JDialog {
    /**
     * Creates an empty dialog without extra options (except the colors).
     *
     * @see Constants#fgColor
     * @see Constants#bgColor
     */
    public GUIDialog() {
        super();

        this.initColors();
    }

    /**
     * Creates a Dialog (empty) with right colors.
     *
     * @param owner The dialog's owner (JFrame)
     * @param title Title of the JFrame
     * @param modal Whether to block the {@code owner} or not
     * @see Constants#fgColor
     * @see Constants#bgColor
     */
    public GUIDialog(JFrame owner, String title, boolean modal) {
        super(owner, title, modal);

        this.initColors();
    }

    /**
     * Set the right colors.
     *
     * @see Constants#fgColor
     * @see Constants#bgColor
     */
    private void initColors() {
        this.setBackground(Constants.bgColor);
        this.setForeground(Constants.fgColor);
    }
}
