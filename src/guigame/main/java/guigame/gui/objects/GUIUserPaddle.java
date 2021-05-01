package guigame.gui.objects;

import guigame.logic.Constants;
import guigame.logic.Position;
import guigame.logic.objects.UserPaddle;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/**
 * Implementation of {@code JComponent}: For user paddles.
 * There will be two paddles: right and left.
 *
 * @see UserPaddle
 */
public class GUIUserPaddle extends JComponent {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Logic-{@code UserPaddle} to get parameters from.
     */
    private final UserPaddle userPaddle;
    /**
     * Paddle height
     */
    private final int height;
    /**
     * Paddle width
     */
    private final int width;

    /**
     * Current position
     */
    private final Position position;

    /**
     * Creates a GUI Paddle for a specified {@code UserPaddle}.
     *
     * @param userPaddle The {@code UserPaddle} the GUI-element belongs to.
     */
    public GUIUserPaddle(UserPaddle userPaddle) {
        // Init Component
        super();

        // Set logic-paddle
        this.userPaddle = userPaddle;

        // Set width, height and position
        this.height = this.userPaddle.getHeight();
        this.width = this.userPaddle.getWidth();

        // Set position (from Logic-UserPaddle)
        this.position = this.userPaddle.getPosition();

        // Set GUI-location and -size
        this.setLocation(Math.round(this.position.getCoordinates().x), Math.round(this.position.getCoordinates().y));
        this.setSize(this.width, this.height);
    }

    /**
     * Normalize the y-offset for a User Paddle.
     * <p>
     * --> Make sure it is not smaller than 0 and not bigger than paddle's height
     * </p>
     *
     * @param paddleLeftYOffset Offset to normalize
     * @param wholeHeight       Height of the container
     * @return The normalized offset.
     */
    public static int normalizeYOffset(int paddleLeftYOffset, int wholeHeight) {
        // Calculate new offset
        int newOffset = paddleLeftYOffset - UserPaddle.HEIGHT / 2;
        // If it should be higher than 0
        if (newOffset < 0) {
            // Set it to 0
            newOffset = 0;
        }
        // If it is out of the window:
        if (newOffset + UserPaddle.HEIGHT > wholeHeight) {
            // Set it to the highest possible point
            newOffset = wholeHeight - UserPaddle.HEIGHT;
        }
        // Return the normalized offset
        return newOffset;
    }

    /**
     * Moves the GUI to the y-offset (from the logic in {@code UserPaddle}).
     */
    public void moveToCoordinates() {
        // Get current bounds
        Rectangle size = this.getBounds();
        // Set new y-offset
        size.y = Math.round(this.userPaddle.getYCoordinate());

        // Set new bounds
        this.setBounds(size);
    }

    /**
     * Paints the component, invoked by Swing-API.
     */
    @Override
    public void paintComponent(Graphics g) {
        // Set the right color
        g.setColor(Constants.fgColor);

        // Draw paddle rect
        g.fillRect(0, 0, this.width, this.height);
    }

    /**
     * @return the GUIBall's width.
     * @see javax.swing.JComponent#getMaximumSize
     */
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(this.width, this.height);
    }

    /**
     * @return the GUIBall's width.
     * @see javax.swing.JComponent#getMinimumSize
     */
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(this.width, this.height);
    }

    /**
     * @return the GUIBall's width.
     * @see javax.swing.JComponent#getPreferredSize
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
}
