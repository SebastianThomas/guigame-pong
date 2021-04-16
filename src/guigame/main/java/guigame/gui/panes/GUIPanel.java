package guigame.gui.panes;

import guigame.logic.Constants;

import javax.swing.*;

public class GUIPanel extends JPanel {
    public GUIPanel() {
        super();

        this.initColors();
    }

    private void initColors() {
        this.setBackground(Constants.bgColor);
        this.setForeground(Constants.fgColor);
    }

    /**
     * Removes all children and then repaints and revalidates the Panel.
     */
    @Override
    public void removeAll() {
        super.removeAll();

        this.repaint();
        this.revalidate();
    }
}
