package guigame.logic.players;

/**
 * An AI-player. Overrides {@code isAI} to return true.
 *
 * @see ArtificialIntelligencePlayer#isAI()
 */
public class ArtificialIntelligencePlayer extends Player {
    /**
     * Creates a new AI-player.
     *
     * @param index the index of the AI-player
     * @param y     The initial y-offset
     * @see #ArtificialIntelligencePlayer(String, int)
     */
    public ArtificialIntelligencePlayer(int index, int y) {
        super(String.format("KI %d", index + 1), y);
    }

    /**
     * Creates a new AI-player.
     *
     * @param name The initial name of the AI-player
     * @param y    The initial y-offset
     * @see #ArtificialIntelligencePlayer(int, int)
     */
    public ArtificialIntelligencePlayer(String name, int y) {
        super(name, y);
    }

    @Override
    public boolean isAI() {
        return true;
    }
}
