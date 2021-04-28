package guigame.logic.objects;

import guigame.logic.Position;

public abstract class GameObject {
    /**
     * The {@code GameObject}'s position
     */
    protected Position position;

    /**
     * The {@code GameObject}'s position
     */
    protected int height;
    /**
     * The {@code GameObject}'s position
     */
    protected int width;

    /**
     * Create a new {@code GameObject}.
     *
     * @see Position
     */
    public GameObject(int x, int y, int height, int width) {
        this.position = new Position(x, y);

        this.height = height;
        this.width = width;
    }

    /**
     * @return the {@code GameObject}'s {@code Position}
     * @see Position
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * @return the {@code GameObject}'s {@code height}
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the {@code GameObject}'s {@code height}.
     *
     * @param height New width of the {@code GameObject}
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the {@code GameObject}'s {@code height}
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set the {@code GameObject}'s {@code width}
     *
     * @param width New width of the {@code GameObject}
     */
    public void setWidth(int width) {
        this.width = width;
    }
}
