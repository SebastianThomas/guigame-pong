package guigame.logic.players;

public abstract class Player {
    protected String name;
    protected int points;

    protected int y;

    public Player(String name, int y) {
        this.name = name;
        this.points = 0;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoint() {
        this.addPoints(1);
    }

    public void addPoints(int add) throws IllegalArgumentException {
        if (add < 1) {
            throw new IllegalArgumentException("Points to add must be at least 1!");
        }
        this.points += add;
    }

    public abstract boolean isAI();
}
