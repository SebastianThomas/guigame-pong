package guigame.logic.players;

public class Players {
    private final Player[] players;

    private final int y;

    public Players(int y) {
        this(1, y);
    }

    /**
     * @param humans Nr of humans to generate, (2 - {@code humans}) AI players will be generated
     * @param y      The y-offset for both players, where they are spawned
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
     * @return Both players
     */
    public Player[] getPlayers() {
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
            this.players[1] = new HumanPlayer(this.players[1].getName(), this.y);
            return;
        }
        this.players[1] = new ArtificialIntelligencePlayer(this.players[1].getName(), this.y);
    }

    /**
     * Change the player at some index
     *
     * @param index The index at which player will be changed
     * @param human Whether the generated Player should be a HumanPlayer (true) or AI-Player (false)
     */
    public void setPlayer(int index, boolean human) {
        this.players[index] = human ?
                new HumanPlayer(String.format("Human %d", index), this.y) :
                new ArtificialIntelligencePlayer(index, this.y);
    }
}
