package guigame.logic.event;

import guigame.logic.Constants;
import guigame.logic.main.MainGame;

/**
 * This {@code EventListener} is triggered when a new game should be started.
 *
 * @see MainGame
 * @see MainGame#startGame()
 */
public class StartGameEventListener implements EventListener {
    /**
     * The {@code MainGame} on which the game should be started.
     */
    private MainGame game;

    /**
     * Create an empty {@code StartGameEventListener}. {@code actionPerformed} will then create a new MainGame.
     *
     * @see #StartGameEventListener(MainGame)
     * @see StartGameEventListener#actionPerformed(Event)
     */
    public StartGameEventListener() {
    }

    /**
     * Create a {@code StartGameEventListener}.
     *
     * @param game The {@code MainGame} to start.
     * @see #StartGameEventListener()
     */
    public StartGameEventListener(MainGame game) {
        this.game = game;
    }

    /**
     * Start a new game either on the already initialized {@code game} or
     * on a newly initialized {@code game} with predefined width and height (from {@code Constants}).
     *
     * @see Constants#GAME_BOARD_HEIGHT
     * @see Constants#GAME_BOARD_WIDTH
     * @see MainGame
     * @see MainGame#startGame()
     */
    @Override
    public void actionPerformed(Event e) {
        if (this.game == null) this.game = new MainGame(Constants.GAME_BOARD_HEIGHT, Constants.GAME_BOARD_WIDTH);
        this.game.startGame();
    }
}
