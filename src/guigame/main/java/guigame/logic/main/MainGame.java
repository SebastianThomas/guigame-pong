package guigame.logic.main;

import guigame.gui.main.GUIGameWindow;
import guigame.logic.event.KeyboardButtonAdapter;
import guigame.logic.event.KeyboardPressedEventListener;
import guigame.logic.event.StartGameEventListener;
import guigame.logic.menu.MainMenu;
import guigame.logic.players.Players;

import javax.swing.*;

/**
 * Logic part to {@code GUIMainGame}.
 * Shows the {@code GUIGameWindow} and from here, the game is started (with GUI).
 */
public class MainGame implements KeyboardButtonAdapter {
    /**
     * Game height.
     */
    private final int height;
    /**
     * Game width.
     */
    private final int width;
    /**
     * {@code EventListener} to trigger when game should be started.
     */
    private StartGameEventListener startGameEventListener;
    /**
     * Players (to be configured)
     */
    private Players players;

    /**
     * Window inside which the {@code MainGame} is shown.
     */
    private GUIGameWindow window;
    /**
     * {@code MainMenu} to show at the beginning
     */
    private MainMenu mainMenu;
    /**
     * {@code GameBoard} to create window for when game is started.
     */
    private GameBoard gameBoard;

    /**
     * Listener to report keyboard presses to.
     */
    private KeyboardPressedEventListener keyboardPressedEventListener;

    /**
     * The current {@code GameState}.
     *
     * @see MainGame#changeState(GameState)
     */
    private GameState state;

    /**
     * Creates a new {@code MainGame}. It starts the main menu, from where a game ({@code GameBoard}) can be started.
     *
     * @param height Height of the window
     * @param width  Width of the window
     */
    public MainGame(int height, int width) {
        this.height = height;
        this.width = width;

        System.out.println("MainGame created");

        this.create();
    }

    /**
     * Sets a JFrame to visible.
     *
     * @param window The {@code JFrame}
     * @param b      Whether the JFrame should be set to visible or invisible
     */
    private static void showGameWindow(JFrame window, boolean b) {
        window.setVisible(b);
    }

    /**
     * Initializes the {@code MainGame}.
     * Create the listeners.
     * Create the {@code GUIGameWindow}.
     * Show the main menu.
     */
    private void create() {
        // Create Main Menu
        this.createMainMenu();

        // Set event listeners
        this.createKeyboardEventListener();
        this.createStartGameEventListener();

        // Create main window
        this.createGameWindow();

        this.showMainMenu();
    }

    /**
     * Creates an {@code MainMenu}.
     * Initializes the {@code players}.
     */
    private void createMainMenu() {
        if (this.startGameEventListener == null)
            this.createStartGameEventListener();

        this.mainMenu = new MainMenu(this.height / 2, this.startGameEventListener);
        this.players = mainMenu.getPlayers();
    }

    /**
     * Creates a new GameBoard and sets it.
     *
     * @param human Whether there will be one human player or not
     */
    private void createGameBoard(boolean human) {
        this.gameBoard = new GameBoard(this.width, this.height, this, human);
    }

    /**
     * Create a {@code GUIGameWindow}, then show it.
     *
     * @see GUIGameWindow
     */
    private void createGameWindow() {
        this.window = new GUIGameWindow(this.width, this.height, this.keyboardPressedEventListener);
        showGameWindow(this.window, true);
    }

    /**
     * Create a {@code StartGameEventListener}.
     *
     * @see StartGameEventListener
     */
    private void createStartGameEventListener() {
        this.startGameEventListener = new StartGameEventListener(this);
    }

    /**
     * Create a {@code KeyboardPressedEventListener}.
     *
     * @see KeyboardPressedEventListener
     */
    private void createKeyboardEventListener() {
        this.keyboardPressedEventListener = new KeyboardPressedEventListener(this);
    }

    /**
     * Prints play to the console.
     * Create game board with settings from Main Menu, then show the {@code GameBoard} (starts game) destroy the parent window since it is not used anymore.
     *
     * @see GameBoard
     */
    public void startGame() {
        // Create a new GameBoard
        this.createGameBoard(this.mainMenu.oneHuman());

        // Show this GameBoard. Will automatically destroy the parent window since it is not used anymore.
        this.showGameBoard();
    }

    /**
     * Changes the current MainGame's {@code GameState}.
     *
     * @param newState new game state for the game
     * @see GameState
     */
    public void changeState(GameState newState) {
        this.state = newState;
    }

    /**
     * Add the (PREVIOUSLY SET) main menu to window and set game state to MAIN_MENU.
     */
    private void showMainMenu() {
        this.changeState(GameState.MAIN_MENU);
        this.window.setMainMenu(this.mainMenu);
    }

    /**
     * Hides the parent window.
     * Shows a {@code GUIGameBoardWindow} and initializes playing.
     * Disposes (destroys) the parent window.
     */
    private void showGameBoard() {
        // Hide the window
        this.window.setVisible(false);

        // Create a separate window for the GameBoard
        this.window.setGameBoard(this.gameBoard);

        // Destroy the previous window
        this.window.dispose();
        System.out.println(this.window);

        // Change GameState TODO: Nötig?
        this.state = GameState.BETWEEN_POINTS;
    }

    /**
     * @return The players playing against each other.
     */
    public Players getPlayers() {
        return this.players;
    }

    /**
     * Should be invoked when escape button is pressed.
     * <p>
     * From {@code KeyboardButtonAdapter}.
     * Invoked when escape is pressed.
     * </p>
     * TODO: Not working?!
     */
    @Override
    public void escapePressed() {
        System.out.println(this.state);

        switch (this.state) {
            case RUNNING -> this.gameBoard.pauseGame();
            case PAUSED -> this.gameBoard.resumeGame();
            case BETWEEN_POINTS -> this.gameBoard.showPauseBetweenPoints();
            case PAUSED_BETWEEN_POINTS -> this.gameBoard.resumeBetweenPoints();

            case MAIN_MENU -> this.mainMenu.askLeaveGame(this.window);
            case GAME_OVER_MENU -> System.out.println("Shouldn't be here...");
        }
    }

    /**
     * EMPTY!!!
     * From {@code KeyboardButtonAdapter}.
     * Invoked when the arrow to the left is pressed.
     */
    @Override
    public void leftArrowPressed() {
        System.out.println("LEFT ARROW");
    }

    /**
     * EMPTY!!!
     * From {@code KeyboardButtonAdapter}.
     * Invoked when the arrow to the top is pressed.
     */
    @Override
    public void topArrowPressed() {
        System.out.println("TOP ARROW");
    }

    /**
     * EMPTY!!!
     * From {@code KeyboardButtonAdapter}.
     * Invoked when the arrow to the right is pressed.
     */
    @Override
    public void rightArrowPressed() {
        System.out.println("RIGHT ARROW");
    }

    /**
     * EMPTY!!!
     * From {@code KeyboardButtonAdapter}.
     * Invoked when the arrow to the bottom is pressed.
     */
    @Override
    public void bottomArrowPressed() {
        System.out.println("BOTTOM ARROW");
    }

    /**
     * EMPTY!!!
     * From {@code KeyboardButtonAdapter}.
     * Invoked when any other key is pressed.
     *
     * @param keyCode The key which is pressed
     * @see KeyboardButtonAdapter#escapePressed()
     * @see KeyboardButtonAdapter#leftArrowPressed()
     * @see KeyboardButtonAdapter#topArrowPressed()
     * @see KeyboardButtonAdapter#rightArrowPressed()
     * @see KeyboardButtonAdapter#bottomArrowPressed()
     */
    @Override
    public void otherKeyPressed(int keyCode) {
        System.out.println("Key pressed, do not react: " + keyCode);
    }
}
