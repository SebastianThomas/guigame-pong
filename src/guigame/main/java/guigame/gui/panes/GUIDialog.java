package guigame.gui.panes;

import guigame.logic.Constants;

import javax.swing.*;

public class GUIDialog extends JDialog {
    /**
     * Creates an empty dialog without extra options
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
     */
    public GUIDialog(JFrame owner, String title, boolean modal) {
        super(owner, title, modal);

        this.initColors();
    }

    private void initColors() {
        this.setBackground(Constants.bgColor);
        this.setForeground(Constants.fgColor);
    }
}
