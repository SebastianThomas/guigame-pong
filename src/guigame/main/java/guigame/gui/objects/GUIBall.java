package guigame.gui.objects;

import guigame.logic.Constants;
import guigame.logic.Position;
import guigame.logic.objects.Ball;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/**
 * An implementation of {@code JComponent} representing a Ball in Pong-Game.
 * It has a position, width and height as well as a {@code Ball} it belongs to.
 */
public class GUIBall extends JComponent {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Ball to get position etc. from
     */
    private final Ball ball;
    /**
     * The ball's height
     */
    private final int height;
    /**
     * The ball's width
     */
    private final int width;

    /**
     * Where the {@code GUIBall} is on the gameBoard
     * and which velocities it has.
     */
    private final Position position;

    /**
     * Initialize a new GUIBall with a fixed width, height and position (from {@code Ball}).
     *
     * @param ball the ball to retrieve the parameters ({@code Position}, height, width etc.) from
     * @see Ball
     */
    public GUIBall(Ball ball) {
        super();

        // Set ball
        this.ball = ball;

        // Set width and height
        this.height = ball.getHeight();
        this.width = ball.getWidth();

        // Set position
        this.position = ball.getPosition();

        // Set Swing-Location and size
        this.setLocation(Math.round(this.position.getCoordinates().x), Math.round(this.position.getCoordinates().y));
        this.setSize(this.width, this.height);
    }

    /**
     * Paint the component.
     * Sets the color to {fgColor} and fill a round rect (circle).
     *
     * @see Constants#fgColor
     * @see Constants#BALL_DIMENSION_SIZE
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Constants.fgColor);

        g.fillRoundRect(0, 0, this.width, this.height, 30, 30);
    }

    /**
     * @return the GUIBall's width and height.
     * @see javax.swing.JComponent#getPreferredSize
     * @see GUIBall#getMinimumSize()
     * @see GUIBall#getPreferredSize()
     */
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(this.width, this.height);
    }

    /**
     * @return the GUIBall's width and height.
     * @see javax.swing.JComponent#getPreferredSize
     * @see GUIBall#getMaximumSize()
     * @see GUIBall#getPreferredSize()
     */
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(this.width, this.height);
    }

    /**
     * @return the GUIBall's width and height.
     * @see javax.swing.JComponent#getPreferredSize
     * @see GUIBall#getMinimumSize()
     * @see GUIBall#getMaximumSize()
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
}
