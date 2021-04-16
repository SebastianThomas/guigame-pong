package guigame.gui.panes;

import guigame.logic.Constants;

import javax.swing.*;
import java.awt.*;

public class GUILabel extends JLabel {
    private final int fontSize;

    /**
     * Creates a label with black background and white foreground.
     *
     * @param text     Text to display
     * @param fontSize Font size for the label
     */
    public GUILabel(String text, int fontSize) {
        super(text, SwingConstants.CENTER);
        this.fontSize = fontSize;

        this.initColors();
    }

    private void initColors() {
        this.setBackground(Constants.bgColor);
        this.setForeground(Constants.fgColor);

        this.setBorder(BorderFactory.createDashedBorder(Constants.fgColor));
        this.setFont(new Font(this.getFont().getName(), this.getFont().getStyle(), this.fontSize));
    }
}
