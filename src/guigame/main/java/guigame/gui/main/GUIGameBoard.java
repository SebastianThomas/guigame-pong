package guigame.gui.main;

import guigame.gui.objects.GUIUserPaddle;
import guigame.gui.panes.GUIPanel;
import guigame.logic.Constants;
import guigame.logic.Position;
import guigame.logic.event.KeyboardButtonAdapter;
import guigame.logic.event.KeyboardPressedEvent;
import guigame.logic.event.KeyboardPressedEventListener;
import guigame.logic.event.StartGameEventListener;
import guigame.logic.main.Coordinates;
import guigame.logic.main.Directions;
import guigame.logic.main.GameBoard;
import guigame.logic.main.GameState;
import guigame.logic.menu.GameOverMenu;
import guigame.logic.objects.Ball;
import guigame.logic.objects.UserPaddle;
import guigame.logic.players.Players;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A {@code GUIPanel} containing the whole {@code GameBoard}. It should be shown on a {@code GUIGameBoardWindow}.
 * It creates the separate {@code Thread} which runs the {@code Ball}-{@code Position}-update-loop it.
 *
 * @see GameBoard
 * @see GUIGameBoardWindow
 */
public class GUIGameBoard extends GUIPanel implements KeyListener, KeyboardButtonAdapter {
    private final KeyboardPressedEventListener keyboardPressedEventListener;

    private final GameBoard gameBoard;

    /**
     * Width and height for the {@code GameBoard}.
     */
    private final int width;
    private final int height;

    /**
     * Labels to display the current score
     */
    private final JLabel[] pointLabels;

    /**
     * Whether the game loop should be invoked or not.
     */
    public boolean loop;

    /**
     * The game ball
     */
    private Ball ball;
    /**
     * The {@code UserPaddle}s (right and left).
     */
    private UserPaddle[] paddles;

    /**
     * The parent window
     */
    private GUIGameBoardWindow parentWindow;

    /**
     * Runnable the Thread should run, contains the game loop.
     *
     * @see GUIGameBoard#ballThread
     */
    private Runnable ballRunnable;
    /**
     * Thread invoking
     *
     * @see GUIGameBoard#ballRunnable
     */
    private Thread ballThread;

    /**
     * Create a {@code GUIPanel} with black background.
     * It will automatically contain a Ball and two Paddles and have the right size.
     * <p>
     * Call {@code setParentWindow} immediately after this constructor
     * or at least before starting the game to avoid {@code NullPointerException} in {@code endBallThread}!
     * </p>
     *
     * @param gameBoard Game Board to show and to play on
     * @see GUIGameBoard#setParentWindow
     * @see NullPointerException
     * @see GUIGameBoard#pointOverEndBallThread
     */
    public GUIGameBoard(GameBoard gameBoard) {
        super();

        // Initialize variables
        this.gameBoard = gameBoard;
        this.width = gameBoard.getWidth();
        this.height = gameBoard.getHeight();

        this.keyboardPressedEventListener = new KeyboardPressedEventListener(this);

        // Remove layout
        this.setLayout(null);

        // Set size and location
        this.setBounds(0, 0, this.width, this.height);

        // Create point labels
        this.pointLabels = new JLabel[2];
        // Create objects
        this.createBoardObjects();
    }

    /**
     * @param parentWindow Window to alert if a point is over and a new point should be started
     */
    public void setParentWindow(GUIGameBoardWindow parentWindow) {
        this.parentWindow = parentWindow;
    }

    /**
     * Paints the whole game board.
     * Invokes super.paintComponent, which paints all previously added.
     * Then paints the line in the center.
     *
     * @param g The graphics
     * @see GUIGameBoard#add(Component)
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Paint content
        super.paintComponent(g);
        // Paint line in the center
        this.createMidLine(g, 2);
    }

    /**
     * Start the game or next point.
     *
     * @param right    Whether the x-speed should be positive (the ball goes to the right) or negative (object goes to the left).
     * @param toRemove Component to remove (potentially a button which starts this game)
     */
    public void startPoint(boolean right, Component toRemove) {
        // Create thread
        Thread startPointThread = new Thread(() -> {
            this.parentWindow.remove(toRemove);
            this.revalidate();
            this.repaint();

            if (this.getState() != GameState.RUNNING) {
                this.start(right);
            }
        });
        // Run thread
        startPointThread.start();
    }

    /**
     * Start the ball thread.
     *
     * @param right Whether the x-speed should be positive (the ball goes to the right) or negative (object goes to the left).
     */
    private void start(boolean right) {
        this.startBallThread(right);
    }

    /**
     * Create
     * <p style="padding: 0">- The point labels,</p>
     * <p style="padding: 0">- The ball and</p>
     * <p style="padding: 0">- the paddles for the users.</p>
     */
    private void createBoardObjects() {
        this.createPointLabels();
        this.createBall();
        this.createPaddles();
    }

    /**
     * Create mid line of the game field.
     *
     * @param g     Graphics to paint on.
     * @param width Width of mid line.
     */
    private void createMidLine(Graphics g, int width) {
        // Begin not in the middle, but at the middle minus half of the rect (or line)
        g.fillRect((this.width - width) / 2, 0, width, this.height);
    }

    /**
     * Create the labels for initial score (0 : 0) and add them to the Game Board GUI.
     * Font size = 20. Y-Offset is 20.
     */
    private void createPointLabels() {
        this.pointLabels[0] = new JLabel("0");
        this.pointLabels[1] = new JLabel("0");

        // Offsets and size of the labels
        int halfWidth = this.width / 2;
        int yOffset = 20;
        int labelWidth = 50;
        int labelHeight = 20;

        // Set both labels with the right size, padding, font etc.
        // TODO: Set as GUILabels instead of JLabels and remove set color and font
        for (int i = 0; i < this.pointLabels.length; i++) {
            JLabel label = this.pointLabels[i];
            label.setForeground(Constants.fgColor);

            label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 20));

            // Set bounds of labels
            // x = half the width minus OR plus (depending of the player) the label's width and an extra margin of 10
            int x = halfWidth + (i == 0 ? -(labelWidth + 10) : (labelWidth + 10));
            label.setBounds(x, yOffset, labelWidth, labelHeight);
        }

        // Add labels
        this.add(this.pointLabels[0]);
        this.add(this.pointLabels[1]);
    }

    /**
     * Add a point to the GUI for the player who has won the latest point
     *
     * @param winningPointPlayer Player which has won the last point
     * @return The new score of both players in an array (to check for a winner??)
     * @throws IllegalArgumentException if the index for the player is out of bounds (0,1)
     */
    public int[] updatePointLabels(int winningPointPlayer) throws IllegalArgumentException {
        if (this.pointLabels[0] == null || this.pointLabels[1] == null) {
            // If the labels do not exit, create them and return [0,0]
            this.createPointLabels();
            return this.getPointLabels();
        }
        // Catch wrong argument
        if (this.pointLabels[winningPointPlayer] == null)
            throw new IllegalArgumentException("Player index " + winningPointPlayer + " does not exist!");

        // Get old score
        int oldScore = Integer.parseInt(this.pointLabels[winningPointPlayer].getText());

        // Update the winning player's label's text
        this.pointLabels[winningPointPlayer].setText(Integer.toString(oldScore + 1));

        this.parentWindow.showPointWinningLabel(winningPointPlayer == 1);

        return this.getPointLabels();
    }

    /**
     * @return the point labels' texts (so the points)
     */
    private int[] getPointLabels() {
        return new int[]{
                Integer.parseInt(this.pointLabels[0].getText()),
                Integer.parseInt(this.pointLabels[1].getText())
        };
    }

    /**
     * Creates the ball with a fixed size.
     * Spawns the {@code GUIBall} in the middle of the screen.
     *
     * @see Constants#BALL_DIMENSION_SIZE
     */
    private void createBall() {
        // Initialize a new Ball (which initializes a GUI-Ball too)
        this.ball = new Ball(Constants.BALL_DIMENSION_SIZE);

        // Set the GUI-Ball's position
        Dimension guiBallPreferredSize = this.ball.guiBall.getPreferredSize();
        int xOffset = (this.width - guiBallPreferredSize.width) / 2;
        int yOffset = (this.height - guiBallPreferredSize.width) / 2;
        this.ball.guiBall.setBounds(xOffset, yOffset, guiBallPreferredSize.width, guiBallPreferredSize.height);
        this.ball.setInitialPositions(xOffset, yOffset);

        this.ball.guiBall.setForeground(Constants.fgColor);
        this.add(this.ball.guiBall);

        // Initialize the logic-part of the ball with the right coordinates
        this.ball.getPosition().setCoordinates(new Coordinates(xOffset, yOffset));
    }

    /**
     * Initialize paddles with beginning values (in the center).
     */
    private void createPaddles() {
        this.paddles = new UserPaddle[2];
        this.paddles[0] = new UserPaddle(5, this.height / 2);
        this.paddles[1] = new UserPaddle(this.width - (UserPaddle.WIDTH + 5), this.height / 2);

        this.add(this.paddles[0].guiPaddle);
        this.add(this.paddles[1].guiPaddle);
    }

    /**
     * Starts a separate thread for the ball (to move it).
     * This thread is interrupted as soon as the ball exceeds the limit of the game board two times in a row (into the same direction).
     *
     * @param right Whether the x-speed should be positive (object goes to the right) or negative (object goes to the left).
     */
    private void startBallThread(boolean right) {
        // Set state to running
        this.gameBoard.resumeGame();

        // Initialize runnable
        this.ballRunnable = () -> {
            long lastLoopTime;
            final int TARGET_FPS = 60;
            final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

            // Initialize the ball's speed
            this.ball.initSpeed(right);

            // The pointEndingDirection is being initialized with the CURRENT round's potential out-of-bounds-rule-break
            Directions pointEndingDirection;
            // The lastRoundDirection is being initialized with the previous round's potential out-of-bounds-rule-break
            Directions lastRoundDirection = Directions.NONE;

            // If both pointEndingDirection and lastRoundDirection are the same AND not equal to NONE, the loop will automatically break:
            while (true) {
                // Get time at the beginning of the loop to determine how long to sleep before doing next frame
                lastLoopTime = System.nanoTime();

                // If not loop, wait for next frame directly without updating Ball
                if (this.loop) {
                    // Calculate new Rectangle for guiBall
                    final Rectangle size = this.ball.guiBall.getBounds();
                    float newXCoordinate = this.ball.getPosition().getCoordinates().x + this.ball.getPosition().getXvelocity();
                    float newYCoordinate = this.ball.getPosition().getCoordinates().y + this.ball.getPosition().getYvelocity();

                    size.x = Math.round(newXCoordinate);
                    size.y = Math.round(newYCoordinate);

                    // Tell the logic part which new Rectangle the ball is inside of
                    this.ball.getPosition().setCoordinates(new Coordinates(newXCoordinate, newYCoordinate));

                    // Move the AI paddle(s)
                    this.moveAIPaddles();

                    // Do the painting and GUI-part in the EDT (Event Dispatcher Thread)
                    // With invokeLater (which works asynchronously) because there may be a paddle update at the same time too
                    SwingUtilities.invokeLater(() -> {
                        int twiceXVelocity = 2 * ((int) Math.round(Math.ceil(this.ball.getPosition().getXvelocity())));

                        // Repaint immediately Rectangle around the ball, bigger than it must be, but otherwise there may be fragments hanging around
                        this.ball.guiBall.setBounds(size);
                        this.paintImmediately(size.x - twiceXVelocity, size.y - twiceXVelocity, size.x + twiceXVelocity, size.y + twiceXVelocity);
                    });

                    pointEndingDirection = this.checkForPointEnd();

                    // If there is an indication for an end of the point and this has already been the second loop-index with the same direction
                    if (pointEndingDirection != Directions.NONE && pointEndingDirection == lastRoundDirection) {
                        System.out.println(pointEndingDirection);
                        break;
                    }
                    lastRoundDirection = pointEndingDirection;

                    // Check for not game-breaking collisions
                    this.checkForCollision(pointEndingDirection);
                }

                // Wait for the next frame
                try {
                    long waitTime = lastLoopTime - System.nanoTime() + OPTIMAL_TIME;
                    // Wait if some time is left before continuing with next frame
                    // The current thread can sleep; it takes only care of the ball.
                    // The paddle can be moved in this time, too due to its other thread.
                    if (waitTime > 0)
                        Thread.sleep((waitTime) / 1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            this.pointOverEndBallThread(pointEndingDirection);
        };

        // Create Thread, start Thread
        this.loop = true;
        this.ballThread = new Thread(this.ballRunnable);
        this.ballThread.start();
    }

    /**
     * Stop the separate thread,
     * uninitialize the ball,
     * set the state to {@code BETWEEN_POINTS} and
     * add a point for the person who won the point.
     *
     * @param offScreen Into which direction the ball went into the off (why the game was stopped)
     * @throws NullPointerException if the {@code parentWindow} is not set yet
     * @see GUIGameBoard#ballThread
     * @see GUIGameBoard#loop
     */
    private void pointOverEndBallThread(Directions offScreen) throws NullPointerException {
        // Interrupt the thread
        this.ballThread.interrupt();
        this.loop = false;

        // Uninitialize the ball
        this.ball.uninitialize();

        // Reset game state
        this.gameBoard.setState(GameState.BETWEEN_POINTS);
        // Add a point visual as well as in the logic
        boolean gameOver = this.gameBoard.addPoint(offScreen);

        if (!gameOver) {
            // Direction for the next point: player who won the last point
            // If the right player won (offScreen == LEFT), the ball should go into his direction, otherwise to the left
            // true as second param to wait 5 seconds
            this.parentWindow.initNextPoint(offScreen == Directions.LEFT, true);
            // Nothing to do more
            return;
        }

        // If the left player won, the winner index is 0, if the right player won, 1.
        int winner = offScreen == Directions.LEFT ? 0 : 1;

        // Show the GameOverMenu
        this.showGameOverMenu(winner);
    }

    /**
     * Checks if the ball goes out of bounds (right or left).
     *
     * @return The Directions into which the ball goes out of bounds.
     * @see Directions
     * @see Directions#NONE
     * @see Directions#LEFT
     * @see Directions#RIGHT
     */
    private Directions checkForPointEnd() {
        // Ball goes out of bounds left
        // (padding + paddle width == 10)
        int paddleLeftPaddingWidth = 5 + this.paddles[0].getWidth();
        int paddleRightPaddingWidth = this.width - (5 + this.paddles[1].getWidth());

        // left edge of the ball is simply its x-coordinate, right edge of the ball is its x-coordinate plus its width
        float ballLeftX = this.ball.getPosition().getCoordinates().x;
        float ballRightX = this.ball.getPosition().getCoordinates().x + this.ball.getWidth();

        // If the ball is still moving to the left and it is out of bounds (left)
        if (ballLeftX < paddleLeftPaddingWidth && this.ball.getPosition().xMovingDirection == Directions.LEFT)
            return Directions.LEFT;
        // If the ball is still moving to the right and it is out of bounds (right)
        if (ballRightX > paddleRightPaddingWidth && this.ball.getPosition().xMovingDirection == Directions.RIGHT)
            return Directions.RIGHT;

        return Directions.NONE;
    }

    /**
     * Check if the ball collides either with top or bottom or with one of the paddles.
     * And react to the potential collision.
     *
     * @param outOfBoundsX Direction from {@code checkForPointEnd}.
     * @see GUIGameBoard#checkForPointEnd()
     */
    private void checkForCollision(Directions outOfBoundsX) {
        // Get the ball position and the ball's y coordinate
        Position ballPosition = this.ball.getPosition();
        float ballYCoordinate = ballPosition.getCoordinates().y;

        // Check for top / bottom collisions
        if (ballPosition.getCoordinates().y < Constants.MAX_BALL_Y_SPEED - 1) {
            ballPosition.inverseYSpeed();
        }
        if (ballPosition.getCoordinates().y + this.ball.getHeight() > this.height - (Constants.MAX_BALL_Y_SPEED - 1) && this.ball.getPosition().yMovingDirection == Directions.DOWN) {
            ballPosition.inverseYSpeed();
        }

        // Check for paddle collisions
        // Left paddle
        if (outOfBoundsX == Directions.LEFT && this.ball.getPosition().xMovingDirection != Directions.RIGHT
                && this.paddles[0].containsYBall(ballYCoordinate, this.ball.getHeight())) {
            this.updatePaddleSpeeds(0);
        }
        // Right paddle
        if (outOfBoundsX == Directions.RIGHT && this.ball.getPosition().xMovingDirection != Directions.LEFT
                && this.paddles[1].containsYBall(ballYCoordinate, this.ball.getHeight())) {
            this.updatePaddleSpeeds(1);
        }
    }

    /**
     * Update the x- and y-speeds for the paddle at the given index.
     *
     * @param index the (0,1) index of the paddle (right or left)
     */
    public void updatePaddleSpeeds(int index) {
        // Update the y-speed
        this.ball.setYSpeed(this.calculateYBallSpeed(index));

        // Update the x-velocity and add to it.
        this.ball.inverseXSpeed();
        this.ball.updateXSpeedSlightly();
    }

    /**
     * Moves the paddles automatically if they are artificial intelligence players.
     *
     * @see GUIGameBoard#moveLeftAIPaddle()
     * @see GUIGameBoard#moveRightAIPaddle()
     */
    private void moveAIPaddles() {
        // Move the right player
        this.moveLeftAIPaddle();
        // Move the left player
        this.moveRightAIPaddle();
    }

    /**
     * Moves the left paddle since it should be and AI player. Checks for this anyways.
     *
     * @see GUIGameBoard#moveAIPaddles()
     * @see GUIGameBoard#moveRightAIPaddle()
     * @see guigame.logic.players.Player#isAI()
     */
    private void moveLeftAIPaddle() {
        // Check if the left player is an AI
        if (!this.getPlayers().getPlayersArray()[0].isAI()) return;

        if (this.ball.getPosition().xMovingDirection == Directions.RIGHT) {
            // The ball is moving away from the paddle
            this.paddles[0].moveTowardsCenter(this.height);
        }
        if (this.ball.getPosition().xMovingDirection == Directions.LEFT) {
            // The ball is moving towards the paddle
            this.paddles[0].moveTowardsBall(this.ball);
        }

        // Move the gui paddle to the coordinates
        this.paddles[0].guiPaddle.moveToCoordinates();
    }

    /**
     * Moves the right paddle if it is and AI player, returns immediately otherwise.
     *
     * @see GUIGameBoard#moveAIPaddles()
     * @see GUIGameBoard#moveLeftAIPaddle()
     * @see guigame.logic.players.Player#isAI()
     */
    private void moveRightAIPaddle() {
        // Check if the left player is an AI
        if (!this.getPlayers().getPlayersArray()[1].isAI()) return;

        if (this.ball.getPosition().xMovingDirection == Directions.LEFT) {
            // The ball is moving away from the paddle
            this.paddles[1].moveTowardsCenter(this.height);
        }
        if (this.ball.getPosition().xMovingDirection == Directions.RIGHT) {
            // The ball is moving towards the paddle
            this.paddles[1].moveTowardsBall(this.ball);
        }

        // Move the gui paddle to the coordinates
        this.paddles[1].guiPaddle.moveToCoordinates();
    }

    /**
     * @param paddle Index for the paddle with which the ball collides
     */
    private float calculateYBallSpeed(int paddle) {
        float ballHeight = this.ball.getHeight();
        float ballycenter = this.ball.getPosition().getCoordinates().y + (ballHeight / 2);
        float paddleHeight = UserPaddle.HEIGHT;
        float paddleyCoord = this.paddles[paddle].getYCoordinate();
        float paddleycenter = paddleyCoord + (paddleHeight / 2);

        // It is inversed since the positive difference should lead to a negative ball direction
        float ballPaddleDifference = -1 * (paddleycenter - ballycenter);

        // Difference between negative max ball y-speed and positive max ball y-speed
        return Constants.MAX_BALL_Y_SPEED * ballPaddleDifference / ((paddleHeight + ballHeight) / 2);
    }

    /**
     * Dispose the parent window and show a {@code GameOverMenu}.
     *
     * @param winner (0-1) The winner's index (left = 0; right = 1)
     * @see GameOverMenu
     * @see guigame.gui.menu.GUIGameOverMenu
     * @see GUIGameOverWindow
     */
    private void showGameOverMenu(int winner) {
        // Parent window not needed anymore, new one will be created anyway
        // Must happen in EDT, otherwise there will be an InterruptedException
        SwingUtilities.invokeLater(() -> {
            this.parentWindow.dispose();
            System.out.println("Disposed");
        });

        // Now for the game over menu:
        // Create a new StartGameEventListener
        StartGameEventListener startGameEventListener = new StartGameEventListener();

        // Create a logic-GameOverMenu, a window for it will pop up.
        new GameOverMenu(startGameEventListener, this.getPlayers(), winner);
    }

    /**
     * @return whether there is one human playing
     */
    public boolean rightIsHuman() {
        return this.gameBoard.rightIsHuman();
    }

    /**
     * @return The players playing against each other.
     */
    public Players getPlayers() {
        return this.gameBoard.getPlayers();
    }

    /**
     * Update the left paddle's y-offset, in the GUI as well as in the logic part.
     *
     * @param paddleLeftYOffset The new y-direction offset of the LEFT paddle (AI).
     * @see GUIGameBoard#updateRightPaddle(int)
     */
    public void updateLeftPaddle(int paddleLeftYOffset) {
        // Get current bounds
        Rectangle size = this.paddles[0].guiPaddle.getBounds();

        // Set the GUI bounds
        size.y = GUIUserPaddle.normalizeYOffset(paddleLeftYOffset, this.height);
        this.paddles[0].guiPaddle.setBounds(size);

        // Set the logic y-coordinate
        this.paddles[0].getPosition().getCoordinates().y = size.y;
    }

    /**
     * Update the right paddle's y-offset, in the GUI as well as in the logic part.
     * This is the user's one if there is a human player.
     *
     * @param paddleRightYOffset The new y-direction offset of the RIGHT paddle (from mouse listener or AI).
     * @see GUIGameBoard#updateLeftPaddle(int)
     */
    public void updateRightPaddle(int paddleRightYOffset) {
        // Get current bounds
        Rectangle size = this.paddles[1].guiPaddle.getBounds();

        // Set the GUI bounds
        size.y = GUIUserPaddle.normalizeYOffset(paddleRightYOffset, this.height);
        this.paddles[1].guiPaddle.setBounds(size);

        // Set the logic y-coordinate
        this.paddles[1].getPosition().getCoordinates().y = size.y;
    }

    /**
     * @return the current {@code GameState}
     */
    public GameState getState() {
        return this.gameBoard.getState();
    }

    /**
     * From {@code KeyboardButtonAdapter}.
     * Invoked when escape is pressed.
     */
    @Override
    public void escapePressed() {
        // TODO: Open pause menu
        switch (this.getState()) {
            case RUNNING -> this.gameBoard.pauseGame();
            case PAUSED -> this.gameBoard.resumeGame();
            case BETWEEN_POINTS -> this.gameBoard.showPauseBetweenPoints();
            case PAUSED_BETWEEN_POINTS -> this.gameBoard.resumeBetweenPoints();
        }
    }

    /**
     * EMPTY!!!
     * From {@code KeyboardButtonAdapter}.
     * Invoked when the arrow to the left is pressed.
     */
    @Override
    public void leftArrowPressed() {
    }

    /**
     * EMPTY!!!
     * From {@code KeyboardButtonAdapter}.
     * Invoked when the arrow to the top is pressed.
     */
    @Override
    public void topArrowPressed() {
    }

    /**
     * EMPTY!!!
     * From {@code KeyboardButtonAdapter}.
     * Invoked when the arrow to the right is pressed.
     */
    @Override
    public void rightArrowPressed() {
    }

    /**
     * EMPTY!!!
     * From {@code KeyboardButtonAdapter}.
     * Invoked when the arrow to the bottom is pressed.
     */
    @Override
    public void bottomArrowPressed() {
    }

    /**
     * EMPTY!!!
     * From {@code KeyboardButtonAdapter}.
     * Invoked when any other key is pressed.
     *
     * @param key The key which is pressed
     * @see KeyboardButtonAdapter#escapePressed()
     * @see KeyboardButtonAdapter#leftArrowPressed()
     * @see KeyboardButtonAdapter#topArrowPressed()
     * @see KeyboardButtonAdapter#rightArrowPressed()
     * @see KeyboardButtonAdapter#bottomArrowPressed()
     */
    @Override
    public void otherKeyPressed(int key) {
    }

    /**
     * EMPTY!!!
     * From {@code KeyListener}.
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key-event in GUIGameBoard");
        new KeyboardPressedEvent(this.keyboardPressedEventListener, e.getKeyCode()).action();
    }

    /**
     * From {@code KeyListener}.
     * EMPTY!!!
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * EMPTY!!!
     * From {@code KeyListener}.
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
