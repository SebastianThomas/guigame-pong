package guigame.logic.players;

public abstract class Player {
    /**
     * Current player has less points than other player.
     * <p>
     * NAME_AKTUELLER_SPIELER holt mit PUNKTE_AKTUELLER_SPIELER Punkten auf!
     * </p>
     *
     * <p>
     * Params:
     * <p style="padding: 0;">
     * 1. name of current player
     * </p>
     * <p style="padding: 0;">
     * 2. points of current player
     * </p>
     * </p>
     */
    public static String catchUpRegex = "%s holt mit %d Punkten auf!";
    /**
     * Both players have the same points.
     * <p>
     * NAME_AKTUELLER_SPIELER hat ausgeglichen, es steht PUNKTE_AKTUELLER_SPIELER:PUNKTE_AKTUELLER_SPIELER!
     * </p>
     *
     * <p>
     * Params:
     * <p style="padding: 0;">
     * 1. name of current player
     * </p>
     * <p style="padding: 0;">
     * 2. points of current player
     * </p>
     * <p style="padding: 0;">
     * 3. points of current / opponents player (equal)
     * </p>
     * </p>
     */
    public static String catchedUpRegex = "%s hat ausgeglichen, es steht %d:%d!";
    /**
     * Current player has more points than other player.
     * <p>
     * NAME_AKTUELLER_SPIELER zieht mit PUNKTE_AKTUELLER_SPIELER Punkten davon!
     * </p>
     *
     * <p>
     * Params:
     * <p style="padding: 0;">
     * 1. name of current player
     * </p>
     * <p style="padding: 0;">
     * 2. points of current player
     * </p>
     * </p>
     */
    public static String pullingAwayRegex = "%s zieht mit %d Punkten davon!";

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

    public String getPointWinningTextAgainst(Player player) {
        if (this.points < player.getPoints()) {
            // This player has less points than his opponent
            return String.format(catchUpRegex, this.name, this.points);
        }
        if (this.points > player.getPoints()) {
            // This player has more points than his opponent
            return String.format(pullingAwayRegex, this.name, this.points);
        }
        return String.format(catchedUpRegex, this.name, this.points, this.points);
    }
}
