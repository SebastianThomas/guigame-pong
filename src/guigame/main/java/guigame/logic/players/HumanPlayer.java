package guigame.logic.players;

/**
 * Implementation of Player-class for human players (Mouse-Listeners).
 *
 * @see Player
 */
public class HumanPlayer extends Player {
    /**
     * Create a new {@code Player} with
     *
     * @param name the name of the player
     * @param y    the initial y-offset for the player
     */
    public HumanPlayer(String name, int y) {
        super(name, y);
    }

    /**
     * @return {@code false} since this is not an AI-player
     */
    @Override
    public boolean isAI() {
        return false;
    }
}
