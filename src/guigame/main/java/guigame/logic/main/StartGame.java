package guigame.logic.main;

import guigame.logic.Constants;

import javax.swing.*;

/**
 * Main class: start game by invoking {@code main}.
 * <p>
 * Or if app is already running: {@code createMainGame}.
 * </p>
 *
 * @see StartGame#main(String...)
 * @see StartGame#createMainGame()
 */
public class StartGame {
    /**
     * Starts the main menu.
     *
     * @param args the runtime arguments. Must not contain any additional information.
     */
    public static void main(String... args) {
        createMainGame();
    }

    /**
     * Starts the main menu in EDT (Event Dispatch Thread) for GUIs.
     */
    public static void createMainGame() {
        SwingUtilities.invokeLater(() -> new MainGame(Constants.GAME_BOARD_HEIGHT, Constants.GAME_BOARD_WIDTH));
    }
}
