package guigame.logic.objects;

import guigame.gui.objects.GUIBall;
import guigame.logic.Constants;
import guigame.logic.Position;

public class Ball {
    private final int height;
    private final int width;
    private final Position position;
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
     * Sets the initial velocities, there where the ball is reseted to.
     */
    public void setInitialPositions(int x, int y) {
        this.position.setInitialPositions(x, y);
    }

    /**
     * Uninitialize the ball, set the velocities back to 0.
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
     *
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
