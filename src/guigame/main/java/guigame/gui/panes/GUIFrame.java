package guigame.gui.panes;

import guigame.logic.Constants;

import javax.swing.*;

public class GUIFrame extends JFrame {
    public GUIFrame() {
        super();

        this.initColors();
    }

    public GUIFrame(String title) {
        super(title);

        this.initColors();
    }

    private void initColors() {
        this.setBackground(Constants.bgColor);
        this.setForeground(Constants.fgColor);
    }
}
