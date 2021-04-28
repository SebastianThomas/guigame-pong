package guigame.gui.panes.buttons;

import guigame.gui.borders.Borders;
import guigame.logic.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * An implementation of {@code JButton}.
 * Has a specific fore- and background.
 */
public class BaseButton extends JButton implements MouseListener {
    private boolean selected;
    private Font font;

    /**
     * The background color for the button.
     */
    private Color backgroundColor;

    /**
     * Creates a new button.
     * Add an action listener by invoking {@code addActionListener}.
     * The button has a background and foreground specified in the class Constants.
     * Its border is dashed and it has a padding of
     * <p>
     * - 10 to top and bottom and
     * </p>
     * <p>
     * - 20 to right and left.
     * </p>
     * The font size defaults to 20, and there will be a border around this button.
     *
     * @param msg String to display on the button
     */
    public BaseButton(String msg) {
        this(msg, 16, true, Constants.bgColor);
    }

    /**
     * Creates a new button.
     * Add an action listener by invoking {@code addActionListener}.
     * The button has a background and foreground specified in the class Constants.
     * It has a default padding of
     * <p>
     * - 10 to top and bottom and
     * </p>
     * <p>
     * - 20 to right and left.
     * </p>
     *
     * @param msg      String to display on the button.
     * @param fontSize Font size for this button.
     * @param border   Create a dashed border if {@code border} is true.
     */
    public BaseButton(String msg, int fontSize, boolean border, Color backgroundColor) {
        super(msg);
        this.backgroundColor = backgroundColor;
        this.font = new Font(this.getFont().getName(), Font.BOLD, fontSize);
        this.setFont(this.font);

        this.setBackground(backgroundColor);
        this.setForeground(Constants.fgColor);

        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.addMouseListener(this);

        if (border) {
            // Set button's border to a compound border of white dashed and then kind of padding (empty, same color as background)
            this.setBorder(BorderFactory.createCompoundBorder(Borders.createDashedBorder(), Borders.createEmptyBorder()));
        }
    }

    public void setButtonSelected(boolean selected) {
        this.selected = selected;

        this.setForeground(selected ? Constants.fgSelectedColor : Constants.fgColor);
    }

    /**
     * EMPTY!
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * EMPTY!
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * EMPTY!
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setForeground(this.selected ? Constants.fgHoverSelectedColor : Constants.fgHoverColor);
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.setForeground(this.selected ? Constants.fgSelectedColor : Constants.fgColor);
    }
}
