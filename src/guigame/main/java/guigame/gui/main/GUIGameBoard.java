package guigame.gui.main;

import guigame.gui.objects.GUIUserPaddle;
import guigame.gui.panes.GUIPanel;
import guigame.logic.Constants;
import guigame.logic.Position;
import guigame.logic.main.Coordinates;
import guigame.logic.main.Directions;
import guigame.logic.main.GameBoard;
import guigame.logic.main.GameState;
import guigame.logic.objects.Ball;
import guigame.logic.objects.UserPaddle;
import guigame.logic.players.Players;

import javax.swing.*;
import java.awt.*;

public class GUIGameBoard extends GUIPanel {
    private final GameBoard gameBoard;

    private final int width;
    private final int height;

    private final JLabel[] pointLabels;
    private Ball ball;
    private UserPaddle[] paddles;

    private GUIGameBoardWindow parentWindow;

    private Runnable ballRunnable;
    private Thread ballThread;

    /**
     * Create a {@code GUIPanel} with black background. It will automatically contain a Ball and two Paddles and have the right size.
     * <p>
     * Call {@code setParentWindow} immediately after this constructor
     * or at least before starting the game to avoid {@code NullPointerException} in {@code endBallThread}!
     * </p>
     *
     * @param gameBoard Game Board to show and to play on
     * @see GUIGameBoard#setParentWindow
     * @see NullPointerException
     * @see GUIGameBoard#endBallThread
     */
    public GUIGameBoard(GameBoard gameBoard) {
        super();

        this.gameBoard = gameBoard;
        this.width = gameBoard.getWidth();
        this.height = gameBoard.getHeight();

        this.setLayout(null);

        this.setBounds(0, 0, this.width, this.height);

        this.pointLabels = new JLabel[2];
        this.createBoardObjects();
    }

    /**
     * @param parentWindow Window to alert if a point is over and a new point should be started
     */
    public void setParentWindow(GUIGameBoardWindow parentWindow) {
        this.parentWindow = parentWindow;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.createMidLine(g, 2);
    }

    /**
     * Start the game or next point.
     *
     * @param right    Whether the x-speed should be positive (the ball goes to the right) or negative (object goes to the left).
     * @param toRemove Component to remove (potentially a button which starts this game)
     */
    public void startPoint(boolean right, Component toRemove) {
        Thread startPointThread = new Thread(() -> {
            this.parentWindow.remove(toRemove);
            this.revalidate();
            this.repaint();

            if (this.getState() != GameState.RUNNING) {
                this.start(right);
            }
        });
        startPointThread.start();
    }

    /**
     * Start the ball thread
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

        for (int i = 0; i < this.pointLabels.length; i++) {
            JLabel label = this.pointLabels[i];
            label.setForeground(Constants.fgColor);

            label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 20));

            // Set bounds of labels
            // x = half the width minus OR plus (depending of the player) the label's width and an extra margin of 10
            int x = halfWidth + (i == 0 ? -(labelWidth + 10) : (labelWidth + 10));
            label.setBounds(x, yOffset, labelWidth, labelHeight);
        }

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
            this.createPointLabels();
            return this.getPointLabels();
        }
        if (this.pointLabels[winningPointPlayer] == null)
            throw new IllegalArgumentException("Player index " + winningPointPlayer + " does not exist!");

        int oldScore = Integer.parseInt(this.pointLabels[winningPointPlayer].getText());

        this.pointLabels[winningPointPlayer].setText(Integer.toString(oldScore + 1));

        this.parentWindow.showPointWinningLabel(winningPointPlayer == 1);

        return this.getPointLabels();
    }

    private int[] getPointLabels() {
        return new int[]{
                Integer.parseInt(this.pointLabels[0].getText()),
                Integer.parseInt(this.pointLabels[1].getText())
        };
    }

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
     * Initialize paddles with beginning values.
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
        this.gameBoard.resumeGame();

        this.ballRunnable = () -> {
            long lastLoopTime;
            final int TARGET_FPS = 60;
            final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

            this.ball.initSpeed(right);

            // The pointEndingDirection is being initialized with the CURRENT round's potential out-of-bounds-rule-break
            Directions pointEndingDirection;
            // The lastRoundDirection is being initialized with the previous round's potential out-of-bounds-rule-break
            Directions lastRoundDirection = Directions.NONE;

            // If both pointEndingDirection and lastRoundDirection are the same AND not equal to NONE, the loop will automatically break:
            while (true) {
                // Get time at the beginning of the loop to determine how long to sleep before doing next frame
                lastLoopTime = System.nanoTime();

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

                // Check for not game-breaking collisions and wait for the next frame
                this.checkForCollision(pointEndingDirection);
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

            this.endBallThread(pointEndingDirection);
        };

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
     */
    private void endBallThread(Directions offScreen) throws NullPointerException {
        this.ballThread.interrupt();

        // Uninitialize the ball
        this.ball.uninitialize();

        // Reset game state
        this.gameBoard.setState(GameState.BETWEEN_POINTS);
        // Add a point visual as well as in the logic
        this.gameBoard.addPoint(offScreen);

        // Direction for the next point: player who won the last point
        // If the right player won (offScreen == LEFT), the ball should go into his direction, otherwise to the left
        // true as second param to wait 5 seconds
        this.parentWindow.initNextPoint(offScreen == Directions.LEFT, true);
    }

    /**
     * Checks if the ball goes out of bounds (right or left).
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
     * Update the x- and y-speeds for
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
        if (!this.getPlayers().getPlayers()[0].isAI()) return;

        if (this.ball.getPosition().xMovingDirection == Directions.RIGHT) {
            // The ball is moving away from the paddle
            this.paddles[0].moveTowardsCenter(this.height);
        }
        if (this.ball.getPosition().xMovingDirection == Directions.LEFT) {
            // The ball is moving towards the paddle
            this.paddles[0].moveTowardsBall(this.ball);
        }

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
        if (!this.getPlayers().getPlayers()[1].isAI()) return;

        if (this.ball.getPosition().xMovingDirection == Directions.LEFT) {
            // The ball is moving away from the paddle
            this.paddles[1].moveTowardsCenter(this.height);
        }
        if (this.ball.getPosition().xMovingDirection == Directions.RIGHT) {
            // The ball is moving towards the paddle
            this.paddles[1].moveTowardsBall(this.ball);
        }

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

    public GameState getState() {
        return this.gameBoard.getState();
    }
}
