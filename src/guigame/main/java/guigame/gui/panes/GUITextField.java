package guigame.gui.panes;

import guigame.logic.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Implementation of {@code JTextField} with the right colors.
 * The user can type in text which will fire Text updates.
 */
public class GUITextField extends JTextField {
    /**
     * Create a new {@code GUITextField} with an initial text.
     *
     * @param initialText     the text to display initially (to be modified)
     * @param descriptionText the description text displayed if no text is put in | TODO: NOT WORKING YET
     * @param fontSize        the font size for the text field
     */
    public GUITextField(String initialText, String descriptionText, int fontSize) {
        // Create Text Field
        super(initialText, 20);

        // Set font size
        this.setFont(new Font(this.getFont().getName(), Font.PLAIN, fontSize));

        // Set right colors
        this.setColors();
    }

    /**
     * Set the right colors for
     * background,
     * foreground and
     * caret (cursor)
     *
     * @see Constants#bgColor
     * @see Constants#fgColor
     * @see Constants#fgHoverColor
     */
    private void setColors() {
        this.setBackground(Constants.bgColor);
        this.setForeground(Constants.fgColor);
        this.setCaretColor(Constants.fgHoverColor);
    }
}
