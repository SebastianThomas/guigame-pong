package guigame.logic.main;

/**
 * The possible states for the Game.
 */
public enum GameState {
    /**
     * When the main menu is shown.
     *
     * @see guigame.logic.menu.MainMenu
     */
    MAIN_MENU,
    /**
     * When the game is over and the game over menu is shown.
     *
     * @see guigame.logic.menu.GameOverMenu
     */
    GAME_OVER_MENU,

    /**
     * When the game (a point) is currently running.
     */
    RUNNING,
    /**
     * When the game is currently paused.
     */
    PAUSED,
    /**
     * When a point is just over, the next one hasn't been started yet and the {@code GameOverMenu} is not shown.
     */
    BETWEEN_POINTS,
    /**
     * When the game is paused while
     *
     * @see GameState#BETWEEN_POINTS
     */
    PAUSED_BETWEEN_POINTS
}
