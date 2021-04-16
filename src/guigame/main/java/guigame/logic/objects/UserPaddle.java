package guigame.logic.objects;

import guigame.gui.objects.GUIUserPaddle;

public class UserPaddle extends GameObject {
    public static final int HEIGHT = 50;
    public static final int WIDTH = 5;

    public GUIUserPaddle guiPaddle;

    public UserPaddle(int x, int y) {
        super(x, y, HEIGHT, WIDTH);

        this.createGUIPaddle();
    }

    private void createGUIPaddle() {
        this.guiPaddle = new GUIUserPaddle(this);
    }

    @Override
    public String toString() {
        return "UserPaddle{" +
                "xy=" + this.position.getCoordinates() +
                ", height=" + this.height +
                ", width=" + this.width +
                ", xvelocity=" + this.position.getXvelocity() +
                ", yvelocity=" + this.position.getYvelocity() +
                '}';
    }
}
