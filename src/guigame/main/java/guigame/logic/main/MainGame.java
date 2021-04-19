package guigame.logic.main;

import guigame.gui.main.GameWindow;
import guigame.logic.Constants;
import guigame.logic.event.KeyboardPressedEventListener;
import guigame.logic.event.StartGameEventListener;
import guigame.logic.menu.GameOverMenu;
import guigame.logic.menu.MainMenu;
import guigame.logic.players.Players;

import javax.swing.*;

public class MainGame {
    private final int height;
    private final int width;
    private StartGameEventListener startGameEventListener;
    private Players players;

    private GameWindow window;
    private GameBoard gameBoard;
    private MainMenu mainMenu;
    private GameOverMenu gameOverMenu;

    private KeyboardPressedEventListener keyboardPressedEventListener;

    private GameState state;

    /**
     * FOR TESTING ONLY!
     * <p>
     * Creates a MainGame only for testing. Must not be used!
     * </p>
     */
    private MainGame() {
        this.height = Constants.GAME_BOARD_HEIGHT;
        this.width = Constants.GAME_BOARD_WIDTH;

        this.createGameBoardDirectly(true);
    }

    public MainGame(int height, int width) {
        this.height = height;
        this.width = width;

        this.create();
    }

    private static void showGameWindow(JFrame window, boolean b) {
        window.setVisible(b);
    }

    private void create() {
        // Create Main Menu
        this.createMainMenu();
        this.createGameOverMenu();

        // Set event listeners
        this.createKeyboardEventListener();
        this.createStartGameEventListener();

        // Create main window
        this.createGameWindow();

        this.showMainMenu();
    }

    /**
     * For testing purposes; doesn't create main menu but directly starts the game.
     *
     * @param human Whether one player should be a human or both players are AIs
     */
    private void createGameBoardDirectly(boolean human) {
        this.createGameOverMenu();

        this.createKeyboardEventListener();

        this.createGameWindow();

        this.createGameBoard(human);
        this.showGameBoard();
    }

    private void createMainMenu() {
        if (this.startGameEventListener == null)
            this.createStartGameEventListener();

        this.mainMenu = new MainMenu(this.height / 2, this.startGameEventListener);
        this.players = mainMenu.getPlayers();
    }

    private void createGameOverMenu() {
        this.gameOverMenu = new GameOverMenu(this.window);
    }

    /**
     * Creates a new GameBoard and sets it.
     *
     * @param human Whether there will be one human player or not
     */
    private void createGameBoard(boolean human) {
        this.gameBoard = new GameBoard(this.width, this.height, this, human);
    }

    private void createGameWindow() {
        this.window = new GameWindow(this.width, this.height, this.keyboardPressedEventListener);
        showGameWindow(this.window, true);
    }

    private void createStartGameEventListener() {
        this.startGameEventListener = new StartGameEventListener(this);
    }

    private void createKeyboardEventListener() {
        this.keyboardPressedEventListener = new KeyboardPressedEventListener(this);
    }

    /**
     * Prints play to the console.
     * Create game board with settings from Main Menu, then destroy main menu and show the game board (starts game)
     */
    public void startGame() {
        System.out.println("Play!!!");

        this.createGameBoard(this.mainMenu.oneHuman());
        this.destroyMainMenu();

        this.showGameBoard();
    }

    /**
     * Destroy GameBoard and init / show GameOverMenu.
     *
     * @param winner Index of winner (0 or 1).
     */
    public void endGame(int winner) {
        System.out.println("Game Over");

        // Destroy Game Board and init GameOverMenu
        this.destroyGameBoard();

        this.createGameOverMenu();
        this.showGameOverMenu(winner);
    }

    public void changeState(GameState newState) {
        this.state = newState;
    }

    /**
     * Add the (PREVIOUSLY SET) main menu to window and set game state to MAIN_MENU
     */
    private void showMainMenu() {
        this.window.setMainMenu(this.mainMenu);
        this.state = GameState.MAIN_MENU;
    }

    /**
     * Destroys the main menu and it's GUI
     */
    private void destroyMainMenu() {
        this.window.removeAll();

        this.mainMenu.getGuiMenu().removeAll();

        this.mainMenu = null;

        this.window.revalidate();
        this.window.repaint();
    }

    /**
     * Set the GameState to MAIN_MENU, and add the GameOverMenu to own GameWindow.
     * Set the params for GameOverMenu.
     *
     * @param winner Index of winner (0 or 1) that'll be shown.
     */
    private void showGameOverMenu(int winner) {
        this.window.setGameOverMenu(this.gameOverMenu);
        this.gameOverMenu.setParams(this.startGameEventListener, this.players, winner);
        this.state = GameState.MAIN_MENU;
    }

    /**
     * Destroys the game over menu and it's GUI
     */
    private void destroyGameOverMenu() {
        this.window.removeAll();

        this.gameOverMenu = null;
    }

    private void showGameBoard() {
        this.window.removeAll();

        this.window.setGameBoard(this.gameBoard);
        this.state = GameState.BETWEEN_POINTS;
    }

    private void destroyGameBoard() {
        this.window.removeAll();
        this.gameBoard = null;
    }

    public void escapePressed() {
        System.out.println(this.state);

        switch (this.state) {
            case RUNNING -> this.gameBoard.pauseGame();
            case PAUSED -> this.gameBoard.resumeGame();
            case BETWEEN_POINTS -> this.gameBoard.showPauseBetweenPoints();
            case PAUSED_BETWEEN_POINTS -> this.gameBoard.resumeBetweenPoints();

            case MAIN_MENU -> this.mainMenu.askLeaveGame(this.window);
            case GAME_OVER_MENU -> this.gameOverMenu.askLeaveGame(this.window);
        }
    }

    /**
     * @return The players playing against each other.
     */
    public Players getPlayers() {
        return this.players;
    }

    public void leftArrowPressed() {
        System.out.println("LEFT ARROW");
    }

    public void rightArrowPressed() {
        System.out.println("RIGHT ARROW");
    }

    public void topArrowPressed() {
        System.out.println("TOP ARROW");
    }

    public void bottomArrowPressed() {
        System.out.println("BOTTOM ARROW");
    }

    public void otherKeyPressed(int keyCode) {
        // TODO: Key not to react to?
        System.out.println("Key pressed, do not react: " + keyCode);
    }
}
