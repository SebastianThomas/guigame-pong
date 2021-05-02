package guigame.logic.main;

/**
 * Holds an x- and a y-coordinate for objects on the GUI.
 */
public class Coordinates {
    /**
     * The horizontal coordinate
     */
    public float x;
    /**
     * The vertical coordinate
     */
    public float y;

    /**
     * Create a new {@code Coordinates}-object with
     *
     * @param x The initial x-position
     * @param y The initial y-position
     */
    public Coordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Add the params to the current x and y values.
     *
     * @param addx The x value to add
     * @param addy The y value to add
     */
    public void add(float addx, float addy) {
        this.x += addx;
        this.y += addy;
    }

    /**
     * @return A string representation of the {@code Coordinates}.
     * @see Coordinates#x
     * @see Coordinates#y
     */
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
