package guigame.logic.objects;

import guigame.logic.Position;

public abstract class GameObject {
    protected Position position;

    protected int height;
    protected int width;


    public GameObject(int x, int y, int height, int width) {
        this.position = new Position(x, y);

        this.height = height;
        this.width = width;
    }

    public Position getPosition() {
        return this.position;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
