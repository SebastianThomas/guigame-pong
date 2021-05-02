package guigame.logic.players;

/**
 * Abstract of a player. Contains all functionality that is shared by {@code HumanPlayer}s AND {@code ArtificialIntelligencePlayer}s.
 *
 * @see HumanPlayer
 * @see ArtificialIntelligencePlayer
 */
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
     */
    public static String pullingAwayRegex = "%s zieht mit %d Punkten davon!";

    /**
     * Player's name.
     */
    protected String name;
    /**
     * Players score points.
     */
    protected int points;

    /**
     * Player's (paddle) y-offset
     */
    protected int y;

    /**
     * Create a new player with a name and an initial y-offset. Its beginning score (points) is 0.
     *
     * @param name the initial name for the player
     * @param y    the initial y-offset for the player('s paddle)
     */
    public Player(String name, int y) {
        this.name = name;
        this.points = 0;
        this.y = y;
    }

    /**
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the player's name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the score points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Give the player one more point.
     */
    public void addPoint() {
        this.addPoints(1);
    }

    /**
     * Give the player {@code add} points additionally.
     *
     * @param add the number of points to add
     */
    public void addPoints(int add) throws IllegalArgumentException {
        if (add < 1) {
            throw new IllegalArgumentException("Points to add must be at least 1!");
        }
        this.points += add;
    }

    /**
     * @return Whether the {@code Player} is an AI or not (so a human)
     */
    public abstract boolean isAI();

    /**
     * @param player the player who lost the last point
     * @return a nice sounding message to tell the Players which one of them has won the last point.
     */
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
