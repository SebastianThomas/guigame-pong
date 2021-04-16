package guigame.gui.main;

import guigame.gui.objects.GUIUserPaddle;
import guigame.gui.panes.GUIPanel;
import guigame.logic.Constants;
import guigame.logic.main.Coordinates;
import guigame.logic.main.Directions;
import guigame.logic.main.GameBoard;
import guigame.logic.main.GameState;
import guigame.logic.objects.Ball;
import guigame.logic.objects.UserPaddle;

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
        new Thread(() -> {
            this.parentWindow.remove(toRemove);
            this.revalidate();
            this.repaint();

            if (this.getState() != GameState.RUNNING) {
                this.start(right);
            }
        }).start();
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
        this.ball = new Ball(30);

        // Set the GUI-Ball's position
        Dimension guiBallPreferredSize = this.ball.guiBall.getPreferredSize();
        int xOffset = (width - guiBallPreferredSize.width) / 2;
        int yOffset = (height - guiBallPreferredSize.width) / 2;
        this.ball.guiBall.setBounds(xOffset, yOffset, guiBallPreferredSize.width, guiBallPreferredSize.height);

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
        this.ballRunnable = () -> {
            // TODO: finish thread
            long lastLoopTime;
            final int TARGET_FPS = 30;
            final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

            if (!this.ball.isInitialized())
                this.ball.initSpeed(right);

            Directions pointEndingDirection = Directions.NONE;
            Directions lastRoundDirection = Directions.NONE;

            // If condition becomes false, loop will break automatically, but for clarity, loop condition is not true but this expression
            while (pointEndingDirection == Directions.NONE || pointEndingDirection != lastRoundDirection) {
                // Get time at the beginning of the loop to determine how long to sleep before doing next frame
                lastLoopTime = System.nanoTime();

                // Calculate new Rectangle for guiBall
                Rectangle size = this.ball.guiBall.getBounds();
                size.x += this.ball.getPosition().getXvelocity();
                size.y += this.ball.getPosition().getYvelocity();

                // Tell the logic part which new Rectangle the ball is inside of
                this.ball.getPosition().getCoordinates().add(this.ball.getPosition().getXvelocity(), this.ball.getPosition().getYvelocity());

                // Do the painting and GUI-part in the EDT (Event Dispatcher Thread)
                // With invokeLater (which works asynchronously) because there may be a paddle update at the same time too
                SwingUtilities.invokeLater(() -> {
                    this.ball.guiBall.setBounds(size);
                    this.paintImmediately(size.x - 2 * Ball.SPEED, size.y - 2 * Ball.SPEED, size.x + 2 * Ball.SPEED, size.y + 2 * Ball.SPEED);
                    // Max width of center line = 5
                    int width = 5;
                    Rectangle centerLineRepaintRect = new Rectangle((this.width - width) / 2, 0, width, this.height);
                    this.paintImmediately(centerLineRepaintRect);
                });

                pointEndingDirection = this.checkForPointEnd();

                // If there is an indication for an end of the point and this has already been the second loop-index with the same direction
                if (pointEndingDirection != Directions.NONE && pointEndingDirection == lastRoundDirection) {
                    System.out.println(pointEndingDirection);
                    break;
                }
                lastRoundDirection = pointEndingDirection;

                // Otherwise check for not breaking collisions and wait for the next frame
                this.checkForCollision();
                try {
                    long waitTime = lastLoopTime - System.nanoTime() + OPTIMAL_TIME;
                    System.out.println("Wait: " + waitTime);
                    if (waitTime > 0)
                        Thread.sleep((waitTime) / 1000000);
                } catch (InterruptedException e) {
                }
            }

            this.endBallThread(pointEndingDirection);
        };

        // TODO: Run here?
        this.ballRunnable.run();
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
        // TODO: Interrupt thread

        // Uninitialize the ball
        this.ball.uninitialize();

        // Reset game state
        this.gameBoard.setState(GameState.BETWEEN_POINTS);
        // Add a point visual as well as in the logic
        this.gameBoard.addPoint(offScreen);

        // Direction for the next point: player who won the last point
        // If the right player won (offScreen == LEFT), the ball should go into his direction, otherwise to the left
        this.parentWindow.initNextPoint(offScreen == Directions.LEFT);
    }

    /**
     * Checks if the ball goes out of bounds (right or left)
     */
    private Directions checkForPointEnd() {
        // Ball goes out of bounds left
        // (padding + paddle width == 10)
        int paddleLeftPaddingWidth = 5 + this.paddles[0].getWidth();
        int paddleRightPaddingWidth = this.width - (5 + this.paddles[1].getWidth());

        // left edge of the ball is simply its x-coordinate, right edge of the ball is its x-coordinate plus its width
        int ballLeftX = this.ball.getPosition().getCoordinates().x;
        int ballRightX = this.ball.getPosition().getCoordinates().x + this.ball.getWidth();

        System.out.println(ballRightX);
        System.out.println(paddleRightPaddingWidth);
        if (ballLeftX < paddleLeftPaddingWidth)
            return Directions.LEFT;
        if (ballRightX > paddleRightPaddingWidth)
            return Directions.RIGHT;

        return Directions.NONE;
    }

    /**
     * Check if the ball collides either with top or bottom or with one of the paddles.
     * And react to the potential collision.
     */
    private void checkForCollision() {
    }

    /**
     * Update the left paddle's y-offset.
     *
     * @param paddleLeftYOffset The new y-direction offset of the RIGHT paddle (from mouse listener or AI).
     */
    public void updateLeftPaddle(int paddleLeftYOffset) {
        System.out.println("Update left paddle: " + paddleLeftYOffset);
    }

    /**
     * Update the right paddle's y-offset. This is the user's one if there is a human player.
     *
     * @param paddleRightYOffset The new y-direction offset of the RIGHT paddle (from mouse listener or AI).
     */
    public void updateRightPaddle(int paddleRightYOffset) {
        Rectangle size = this.paddles[1].guiPaddle.getBounds();

        size.y = GUIUserPaddle.normalizeYOffset(paddleRightYOffset, this.height);

        this.paddles[1].guiPaddle.setBounds(size);
    }

    public GameState getState() {
        return this.gameBoard.getState();
    }
}
