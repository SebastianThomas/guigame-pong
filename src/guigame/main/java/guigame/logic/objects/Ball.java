package guigame.logic.objects;

import guigame.gui.objects.GUIBall;
import guigame.logic.Position;

public class Ball {
    public static int SPEED = 3;

    private final int height;
    private final int width;
    private final Position position;
    public GUIBall guiBall;
    private boolean initialized = false;

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
     * @return Whether the ball has been initialized
     * @see Ball#uninitialize
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * Uninitialize the ball, set the velocities back to 0.
     *
     * @see Ball#isInitialized
     */
    public void uninitialize() {
        if (initialized) {
            this.position.setXvelocity(0);
            this.position.setYvelocity(0);
            this.initialized = false;

            this.position.resetCoordinates();
        }
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Position getPosition() {
        return this.position;
    }

    /**
     * Initialize speed with y = 0. And x:
     *
     * @param right Whether the x-speed should be positive (object goes to the right) or negative (object goes to the left).
     */
    public void initSpeed(boolean right) {
        this.position.initSpeed(right, SPEED);
    }

    public void inverseXSpeed() {
        this.position.inverseXSpeed();
    }
}
