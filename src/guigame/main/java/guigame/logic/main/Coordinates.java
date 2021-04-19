package guigame.logic.main;

public class Coordinates {
    public float x;
    public float y;

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

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
