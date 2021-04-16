package guigame.gui.objects;

import guigame.logic.Constants;
import guigame.logic.Position;
import guigame.logic.objects.UserPaddle;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class GUIUserPaddle extends JComponent {
    @Serial
    private static final long serialVersionUID = 1L;

    private final UserPaddle userPaddle;
    private final int height;
    private final int width;

    private final Position position;

    /**
     * Creates a GUI Paddle for a specified {@code UserPaddle}.
     *
     * @param userPaddle The {@code UserPaddle} the GUI-element belongs to.
     */
    public GUIUserPaddle(UserPaddle userPaddle) {
        super();

        this.userPaddle = userPaddle;

        this.height = this.userPaddle.getHeight();
        this.width = this.userPaddle.getWidth();

        this.position = this.userPaddle.getPosition();

        this.setLocation(this.position.getCoordinates().x, this.position.getCoordinates().y);
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
        int newOffset = paddleLeftYOffset - UserPaddle.HEIGHT / 2;
        if (newOffset < 0) {
            newOffset = 0;
        }
        if (newOffset + UserPaddle.HEIGHT > wholeHeight) {
            newOffset = wholeHeight - UserPaddle.HEIGHT;
        }
        return newOffset;
    }

    /**
     * Paints the component, invoked by Swing-API.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Constants.fgColor);

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
