package guigame.gui.panes;

import guigame.logic.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * A Label which has a fore- and background from {@code Constants}.
 * It is possible to specify font size as well as a border.
 *
 * @see Constants#fgColor
 * @see Constants#bgColor
 */
public class GUILabel extends JLabel {
    /**
     * The font size of the current label.
     */
    private final int fontSize;

    /**
     * Creates a label with black background, white foreground and a dotted border.
     *
     * @param text     Text to display
     * @param fontSize Font size for the label
     */
    public GUILabel(String text, int fontSize) {
        this(text, fontSize, true);
    }

    /**
     * Creates a label with black background and white foreground.
     *
     * @param text     Text to display
     * @param fontSize Font size for the label
     * @param border   Whether there should be a border around the label
     */
    public GUILabel(String text, int fontSize, boolean border) {
        super(text, SwingConstants.CENTER);
        this.fontSize = fontSize;

        this.initColors(border);
    }

    /**
     * Initializes the colors from {@code Constants}.
     *
     * @param border Whether a dotted border should be shown
     */
    private void initColors(boolean border) {
        this.setBackground(Constants.bgColor);
        this.setForeground(Constants.fgColor);
        this.setFont(new Font(this.getFont().getName(), this.getFont().getStyle(), this.fontSize));

        if (border)
            this.setBorder(BorderFactory.createDashedBorder(Constants.fgColor));
    }
}
