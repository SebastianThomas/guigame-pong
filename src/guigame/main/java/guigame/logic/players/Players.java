package guigame.logic.players;

/**
 * Represents two players. They have an initial y-offset for the GUI.
 *
 * @see Players#getPlayersArray()
 */
public class Players {
    /**
     * Contains both players
     */
    private final Player[] players;

    /**
     * The initial y-offset (for both players the same), should be almost centered
     */
    private final int y;

    /**
     * Create a new {@code Players}-object with one human and an initial y-offset (for GUI).
     *
     * @param y The y-offset for both players, where they are spawned
     * @see Players#Players(int, int)
     */
    public Players(int y) {
        this(1, y);
    }

    /**
     * Create a new {@code Players}-object with a number of humans and an initial y-offset (for GUI).
     *
     * @param humans Nr of humans to generate, (2 - {@code humans}) AI players will be generated
     * @param y      The y-offset for both players, where they are spawned
     * @see Players#Players(int)
     */
    public Players(int humans, int y) {
        this.y = y;
        this.players = new Player[2];

        for (int i = 0; i < humans; i++) {
            players[players.length - 1 - i] = new HumanPlayer(String.format("Spieler (Mensch) %d", i), y);
        }
        if (players[1] == null) {
            players[1] = new ArtificialIntelligencePlayer(1, y);
        }
        if (players[0] == null) {
            players[0] = new ArtificialIntelligencePlayer(0, y);
        }
    }

    /**
     * @return Both players in a {@code Player}-Array
     */
    public Player[] getPlayersArray() {
        return players;
    }

    /**
     * Set nr of human players.
     * <p>
     * Leaves the first player as AI. Set the second player as human if
     *
     * @param nrOfHumans is equal to 1.
     */
    public void setPlayers(int nrOfHumans) {
        if (nrOfHumans == 1) {
            // If there should be a player, initialize the right player as HumanPlayer with the previously set name (which might not make sense any more)
            this.players[1] = new HumanPlayer(this.players[1].getName(), this.y);
            return;
        }
        // Otherwise, set the right player as ArtificialIntelligencePlayer with the previously set name
        this.players[1] = new ArtificialIntelligencePlayer(this.players[1].getName(), this.y);
    }

    /**
     * Change the player type at some index. Should NOT be used with {@code index=0} and {@code human=true}
     *
     * @param index The index at which player will be changed
     * @param human Whether the generated Player should be a HumanPlayer (true) or AI-Player (false)
     */
    public void setPlayer(int index, boolean human) {
        // Set player at specific index either as human or not
        this.players[index] = human ?
                new HumanPlayer(String.format("Human %d", index), this.y) :
                new ArtificialIntelligencePlayer(index, this.y);
    }
}
