package guigame.logic.main;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Add the params to the current x and y values.
     *
     * @param addx The x value to add
     * @param addy The y value to add
     */
    public void add(int addx, int addy) {
        this.x += addx;
        this.y += addy;
    }

    public Coordinates simulateAdd(int addx, int addy) {
        return new Coordinates(this.x + addx, this.y + addy);
    }

    public boolean xIsNegative() {
        return x < 0;
    }

    public boolean yIsNegative() {
        return y < 0;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
