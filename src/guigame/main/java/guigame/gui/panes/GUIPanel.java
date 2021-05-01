package guigame.gui.panes;

import guigame.logic.Constants;

import javax.swing.*;

/**
 * A {@code JPanel} with predefined colors and an updated {@code removeAll}-method.
 *
 * @see Constants#fgColor
 * @see Constants#bgColor
 */
public class GUIPanel extends JPanel {
    /**
     * Create a new {@code GUIPanel} with the predefined colors.
     */
    public GUIPanel() {
        // Init JPanel
        super();

        // Set the right colors
        this.initColors();
    }

    /**
     * Initializes the color scheme for the panel.
     *
     * @see Constants#fgColor
     * @see Constants#bgColor
     */
    private void initColors() {
        this.setBackground(Constants.bgColor);
        this.setForeground(Constants.fgColor);
    }

    /**
     * Removes all children
     * and then repaints and revalidates the Panel.
     */
    @Override
    public void removeAll() {
        super.removeAll();

        this.repaint();
        this.revalidate();
    }
}
