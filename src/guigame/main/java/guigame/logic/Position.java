package guigame.logic;

import guigame.logic.main.Coordinates;
import guigame.logic.main.Directions;

/**
 * A class implementing some logic for Positions. Does not affect the GUI directly!
 */
public class Position {
    /**
     * The current horizontal direction; either left or right.
     *
     * @see Directions#LEFT
     * @see Directions#RIGHT
     */
    public Directions xMovingDirection;
    /**
     * The current horizontal direction; either up or down.
     *
     * @see Directions#UP
     * @see Directions#DOWN
     */
    public Directions yMovingDirection;

    /**
     * The initial (x,y)-pair for the starting point (for the {@code GUIBall}: center of the screen)
     *
     * @see Position#resetCoordinates()
     */
    private int[] initialPositions;

    /**
     * The current position's horizontal velocity
     */
    private float xvelocity;
    /**
     * The current position's vertical velocity
     */
    private float yvelocity;

    /**
     * The current position's coordinates: (x,y)-value pair
     */
    private Coordinates coordinates;

    /**
     * Create a new Position with the velocities = 0 and
     *
     * @param x The initial horizontal coordinate
     * @param y The initial vertical coordinate
     */
    public Position(int x, int y) {
        this(x, y, 0, 0);
    }

    /**
     * Create a new Position with
     *
     * @param x         The initial horizontal coordinate
     * @param y         The initial vertical coordinate
     * @param xvelocity The initial horizontal velocity
     * @param yvelocity The initial vertical velocity
     */
    public Position(int x, int y, int xvelocity, int yvelocity) {
        this.initialPositions = new int[]{x, y};

        this.coordinates = new Coordinates(x, y);
        this.xvelocity = xvelocity;
        this.yvelocity = yvelocity;

        this.setMovingDirections(xvelocity, yvelocity);
    }

    /**
     * Sets the initial velocities, there where the ball is reseted to.
     *
     * @param x the initial x(horizontal)-position
     * @param y the initial y(vertical)-position
     */
    public void setInitialPositions(int x, int y) {
        this.initialPositions = new int[]{x, y};
    }

    /**
     * @return the current Ball's coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Set the coordinates (from a completely new object).
     *
     * @param coordinates The new {@code Coordinates}-object
     * @see Position#getXvelocity()
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return the current y-velocity.
     * @see Position#setXvelocity(float)
     */
    public float getXvelocity() {
        return xvelocity;
    }

    /**
     * Set the x-velocity and x-moving-direction.
     *
     * @param xvelocity the updated x-velocity
     * @see Position#getXvelocity()
     */
    public void setXvelocity(float xvelocity) {
        this.xvelocity = xvelocity;

        this.setXMovingDirection(this.xvelocity);
    }

    /**
     * @return the current y-velocity.
     * @see Position#setYvelocity(float)
     */
    public float getYvelocity() {
        return yvelocity;
    }

    /**
     * Set the y-velocity and y-moving-direction.
     *
     * @param yvelocity the updated y-velocity
     * @see Position#getYvelocity()
     */
    public void setYvelocity(float yvelocity) {
        this.yvelocity = yvelocity;

        this.setYMovingDirection(this.yvelocity);
    }

    /**
     * Sets the moving directions based on
     *
     * @param xvelocity The current x-velocity
     * @param yvelocity The current y-velocity
     */
    public void setMovingDirections(float xvelocity, float yvelocity) {
        this.setXMovingDirection(xvelocity);
        this.setYMovingDirection(yvelocity);
    }

    /**
     * Calculate the moving direction for
     *
     * @param xvelocity x-velocity to calculate from.
     */
    public void setXMovingDirection(float xvelocity) {
        if (xvelocity > 0) this.xMovingDirection = Directions.RIGHT;
        if (xvelocity < 0) this.xMovingDirection = Directions.LEFT;
        if (xvelocity == 0) this.xMovingDirection = Directions.NONE;
    }

    /**
     * Calculate the moving direction for
     *
     * @param yvelocity x-velocity to calculate from.
     */
    public void setYMovingDirection(float yvelocity) {
        if (yvelocity > 0) this.yMovingDirection = Directions.DOWN;
        if (yvelocity < 0) this.yMovingDirection = Directions.UP;
        if (yvelocity == 0) this.yMovingDirection = Directions.NONE;
    }

    /**
     * Initialize speed with y = half of the speed (+ or - random). And x: half of the speed.
     *
     * @param right Whether the x-speed should be positive (object goes to the right) or negative (object goes to the left).
     * @param speed The speed for the ball
     */
    public void initSpeed(boolean right, float speed) {
        this.xvelocity = right ? speed : -speed;
        this.yvelocity = Math.random() > 0.5 ? speed / 2 : -speed / 2;

        this.setMovingDirections(this.xvelocity, this.yvelocity);
    }

    /**
     * Update the {@code Position}'s x-velocity (horizontal).
     *
     * @param toAdd the horizontal velocity to add
     */
    public void updateXSpeed(float toAdd) {
        if (this.xvelocity < 0) {
            this.xvelocity -= toAdd;
            return;
        }
        this.xvelocity += toAdd;
    }

    /**
     * Inverse the horizontal speed and set the moving direction.
     */
    public void inverseXSpeed() {
        this.xvelocity = -this.xvelocity;
        this.setXMovingDirection(this.xvelocity);
    }

    /**
     * Inverse the horizontal speed and set the moving direction.
     */
    public void inverseYSpeed() {
        this.yvelocity = -this.yvelocity;
        this.setYMovingDirection(this.yvelocity);
    }

    /**
     * Resets the coordinates to the initial values (from constructor).
     * Does not reset velocities!
     */
    public void resetCoordinates() {
        this.coordinates = new Coordinates(this.initialPositions[0], this.initialPositions[1]);
        this.setMovingDirections(this.initialPositions[0], this.initialPositions[0]);
    }
}
