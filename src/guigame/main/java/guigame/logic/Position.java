package guigame.logic;

import guigame.logic.main.Coordinates;

public class Position {
    private Coordinates coordinates;

    private int xvelocity;
    private int yvelocity;

    private int[] initialPositions;

    /**
     * Create a new position with the velocity = 0 and
     *
     * @param x The horizontal
     * @param y The vertival
     */
    public Position(int x, int y) {
        this(x, y, 0, 0);
    }

    public Position(int x, int y, int xvelocity, int yvelocity) {
        this.initialPositions = new int[]{x, y};

        this.coordinates = new Coordinates(x, y);
        this.xvelocity = xvelocity;
        this.yvelocity = yvelocity;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getXvelocity() {
        return xvelocity;
    }

    public void setXvelocity(int xvelocity) {
        this.xvelocity = xvelocity;
    }

    public int getYvelocity() {
        return yvelocity;
    }

    public void setYvelocity(int yvelocity) {
        this.yvelocity = yvelocity;
    }

    public void update() {
        this.coordinates.x += xvelocity;
        this.coordinates.y += yvelocity;
    }

    /**
     * Sets the x and y speed based on the {@code xSpeed}. There is a fixed {@code SPEED} which is the maximum added speed for both speeds
     *
     * @param xSpeed   Speed for the x-axis
     * @param maxSpeed Maximum speed (for both x and y together)
     * @see Position#createSpeedsFromY
     */
    public void createSpeedsFromX(int xSpeed, int maxSpeed) {
        this.xvelocity = xSpeed;
        this.yvelocity = maxSpeed - xSpeed;
    }

    /**
     * Sets the x and y speed based on the given {@code ySpeed}. There is a fixed {@code SPEED} which is the maximum added speed for both speeds
     *
     * @param ySpeed   Speed for the x-axis
     * @param maxSpeed Maximum speed (for both x and y together)
     * @see Position#createSpeedsFromX
     */
    public void createSpeedsFromY(int ySpeed, int maxSpeed) {
        this.xvelocity = maxSpeed - ySpeed;
        this.yvelocity = ySpeed;
    }

    /**
     * Initialize speed with y = 0. And x:
     *
     * @param right Whether the x-speed should be positive (object goes to the right) or negative (object goes to the left).
     */
    public void initSpeed(boolean right, int speed) {
        this.xvelocity = right ? speed : -speed;
        this.yvelocity = 0;
    }

    /**
     * Inverse the horizontal speed.
     */
    public void inverseXSpeed() {
        this.xvelocity = -this.xvelocity;
    }

    public void resetCoordinates() {
        this.coordinates = new Coordinates(this.initialPositions[0], this.initialPositions[1]);
    }
}
