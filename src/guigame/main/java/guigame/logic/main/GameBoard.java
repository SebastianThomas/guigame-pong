package guigame.logic.main;

import guigame.gui.main.GUIGameBoard;
import guigame.logic.Constants;
import guigame.logic.players.Players;

/**
 * Class to represent the Game Board in the game. Only when state == RUNNING, PAUSE or BETWEEN_POINTS
 */
public class GameBoard {
    /**
     * {@code GameBoard}'s width
     */
    private final int width;
    /**
     * {@code GameBoard}'s height
     */
    private final int height;

    /**
     * Parent main game
     */
    private final MainGame mainGame;

    /**
     * Whether a human is playing or not
     */
    private final boolean human;
    /**
     * The current score:
     * [0]: left player
     * [1]: right player
     */
    private final int[] score;

    /**
     * GUI-part
     */
    private GUIGameBoard guiBoard;
    /**
     * Current state of the game
     */
    private GameState gameState;

    // TODO: Implement further checkout for loop which is only assigned but not used
    private boolean loop;

    /**
     * @param width    Width of the GameBoard
     * @param height   Height of the GameBoard
     * @param mainGame The MainGame to invoke GameState changes on
     * @param human    Whether there is one human player or not
     */
    public GameBoard(int width, int height, MainGame mainGame, boolean human) {
        this.width = width;
        this.height = height;

        this.mainGame = mainGame;
        this.gameState = GameState.BETWEEN_POINTS;

        this.human = human;

        this.score = new int[]{0, 0};

        this.setGUIBoard();
    }

    /**
     * Create a new GUIBoard.
     */
    private void setGUIBoard() {
        this.guiBoard = new GUIGameBoard(this);
    }

    /**
     * One point is over. Adds a point to one of the player and calculates whether one player has won the match.
     *
     * @param directionLoser The direction into which the ball went off the field
     * @return Whether the game is over now
     * @throws IllegalArgumentException if the {@code directionLoser} is neither LEFT nor RIGHT
     */
    public boolean addPoint(Directions directionLoser) throws IllegalArgumentException {
        if (directionLoser == Directions.LEFT) {
            // The left player has lost a point
            return this.playerWonPoint(true);
        }
        if (directionLoser == Directions.RIGHT) {
            // The right player has lost a point
            return this.playerWonPoint(false);
        }

        // Specified Player does not match with any known player
        throw new IllegalArgumentException("Specified direction does not match with a player.");
    }

    /**
     * Do calculations for the winning player.
     *
     * @param right Whether the right player has won the point or not
     * @return Whether the game is over now
     */
    private boolean playerWonPoint(boolean right) {
        // Index = right player won? Then add one point to the right player's score. Otherwise add one point to the left player's point
        int index = right ? 1 : 0;
        this.score[index] += 1;

        // Update points
        this.guiBoard.updatePointLabels(index);

        // If one player has won
        if (this.score[index] >= Constants.WINNING_SCORE) {
            // Set GameState
            this.setState(GameState.GAME_OVER_MENU);
            return true;
        }
        return false;
    }

    /**
     * @return the current game state
     */
    public GameState getState() {
        return this.gameState;
    }

    /**
     * Sets the new game state. Prefer the methods below over {@code setState}.
     *
     * @see GameBoard#pauseGame()
     * @see GameBoard#resumeGame()
     * @see GameBoard#resumeBetweenPoints()
     * @see GameBoard#showPauseBetweenPoints()
     */
    public void setState(GameState newState) {
        this.gameState = newState;
        this.mainGame.changeState(newState);
    }

    /**
     * @return Whether there is a human playing or two AIs
     */
    public boolean rightIsHuman() {
        return this.human;
    }

    /**
     * Pauses the game: Set the state to PAUSED and do not loop.
     */
    public void pauseGame() {
        this.setState(GameState.PAUSED);
        System.out.println("State changed to:" + this.gameState);

        this.loop = false;
    }

    /**
     * Resume the game: Set the state to RUNNING and do loop.
     */
    public void resumeGame() {
        this.setState(GameState.RUNNING);
        System.out.println("State changed to:" + this.gameState);

        this.loop = true;
    }

    /**
     * Pauses the game: Set the state to PAUSED_BETWEEN_POINTS and do not loop.
     */
    public void showPauseBetweenPoints() {
        this.setState(GameState.PAUSED_BETWEEN_POINTS);
        System.out.println("State changed to:" + this.gameState);

        this.loop = false;
    }

    /**
     * Pauses the game: Set the state to BETWEEN_POINTS and do not loop.
     */
    public void resumeBetweenPoints() {
        this.setState(GameState.BETWEEN_POINTS);
        System.out.println("State changed to:" + this.gameState);

        this.loop = false;
    }

    /**
     * @return the GUI-board
     */
    public GUIGameBoard getGuiBoard() {
        return this.guiBoard;
    }

    /**
     * @return The players playing against each other.
     */
    public Players getPlayers() {
        return this.mainGame.getPlayers();
    }

    /**
     * @return the game board's height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * @return the game board's width
     */
    public int getWidth() {
        return this.width;
    }
}
