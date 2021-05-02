package guigame.logic.objects;

import guigame.gui.objects.GUIBall;
import guigame.logic.Constants;
import guigame.logic.Position;

/**
 * A {@code Ball} has a fixed {@code width} and {@code height}.
 * Furthermore, it has a {@code Position}.
 * A {@code GUIBall} belongs to it, which is shown on the {@code GUIGameBoard}.
 *
 * @see Position
 * @see GUIBall
 */
public class Ball {
    /**
     * Ball's height
     */
    private final int height;
    /**
     * Ball's width
     */
    private final int width;
    /**
     * Ball's position
     */
    private final Position position;
    /**
     * GUI-counterpart for this Ball
     */
    public GUIBall guiBall;

    /**
     * Creates a new Ball, and a GUI-Ball that belongs to it.
     *
     * @param dimensions Width and height of the Ball
     */
    public Ball(int dimensions) {
        this(dimensions, dimensions);
    }

    /**
     * Creates a new Ball, and a GUI-Ball that belongs to it.
     *
     * @param width  Width of the new Ball
     * @param height Height of the new Ball
     */
    public Ball(int width, int height) {
        this.height = height;
        this.width = width;
        this.position = new Position(0, 0);

        this.guiBall = new GUIBall(this);
    }

    /**
     * Sets the initial position of the ball.
     *
     * @param x the initial x-coordinate
     * @param y the initial y-coordinate
     */
    public void setInitialPositions(int x, int y) {
        this.position.setInitialPositions(x, y);
    }

    /**
     * Uninitialize the ball:
     * set the velocities and the coordinates back to 0.
     */
    public void uninitialize() {
        this.position.setXvelocity(0);
        this.position.setYvelocity(0);

        this.position.resetCoordinates();
    }

    /**
     * @return the ball's height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * @return the ball's width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @return the ball's position
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Initialize speed with y = 0. And x:
     *
     * @param right Whether the x-speed should be positive (object goes to the right) or negative (object goes to the left).
     */
    public void initSpeed(boolean right) {
        this.position.initSpeed(right, Constants.BALL_X_SPEED);
    }

    /**
     * Slightly updates the x-speed of the ball.
     *
     * @see Constants#BALL_X_SPEED_UPDATE
     */
    public void updateXSpeedSlightly() {
        this.position.updateXSpeed(Constants.BALL_X_SPEED_UPDATE);
    }

    /**
     * Inverses the x-velocity of the speed (- --> + and + --> -).
     */
    public void inverseXSpeed() {
        this.position.inverseXSpeed();
    }

    /**
     * Sets the new y-velocity for the ball.
     *
     * @param newYSpeed The new y-velocity
     */
    public void setYSpeed(float newYSpeed) {
        this.getPosition().setYvelocity(newYSpeed);
    }
}
