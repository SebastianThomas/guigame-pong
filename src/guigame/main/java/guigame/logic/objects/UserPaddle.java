package guigame.logic.objects;

import guigame.gui.objects.GUIUserPaddle;
import guigame.logic.Constants;

public class UserPaddle extends GameObject {
    /**
     * All user paddle's height.
     */
    public static final int HEIGHT = 50;
    /**
     * All user paddle's width.
     */
    public static final int WIDTH = 5;

    /**
     * The GUIUserPaddle belonging to this UserPaddle.
     */
    public GUIUserPaddle guiPaddle;

    /**
     * Whether the current {@code UserPaddle} is moving to the ball or to the center of the {@code GameBoard}.
     */
    private boolean toBall;
    /**
     * The y-offset the current {@code UserPaddle} should be moving to. Will be initialized automatically.
     */
    private int moveToY = -1;
    /**
     * The random part of the y-offset.
     */
    private float random;

    /**
     * Creates a new UserPaddle at
     *
     * @param x the x-coordinate
     * @param y the y-coordinate.
     */
    public UserPaddle(int x, int y) {
        super(x, y, HEIGHT, WIDTH);

        this.createGUIPaddle();
    }

    /**
     * Creates the GUI paddle for the current user paddle.
     */
    private void createGUIPaddle() {
        this.guiPaddle = new GUIUserPaddle(this);
    }

    /**
     * Move the current paddle towards the ball.
     *
     * @param ball Ball the move the paddle to.
     * @see UserPaddle#moveTowardsCenter(int)
     * @see UserPaddle#movePaddleTo(int)
     */
    public void moveTowardsBall(Ball ball) {
        if (!this.toBall) {
            this.random = (float) Math.random();
            this.toBall = true;
        }
        float ballY = ball.getPosition().getCoordinates().y;
        float heightToMoveTo = ballY - (this.height / (float) 2.0);

        // Randomize the height a little bit
        heightToMoveTo += this.random * (this.height - 2);
        this.moveToY = Math.round(heightToMoveTo);

        this.movePaddleTo(this.moveToY);
    }

    /**
     * Initializes {@code moveToY}
     * Move the current paddle towards the center.
     *
     * @param height Height of the GameBoard
     * @see UserPaddle#moveTowardsBall(Ball)
     * @see UserPaddle#movePaddleTo(int)
     */
    public void moveTowardsCenter(int height) {
        if (this.toBall || this.moveToY == -1) {
            this.moveToY = (height / 2) - (this.height / 2);
            this.toBall = false;
        }
        this.movePaddleTo(this.moveToY);
    }

    /**
     * Move the current paddle's y-coordinate towards
     *
     * @param heightToMoveTo The height to move this paddle to.
     */
    private void movePaddleTo(int heightToMoveTo) {
        if (this.getYCoordinate() < heightToMoveTo)
            this.getPosition().getCoordinates().y += diffOrMaxSpeed(Math.round(heightToMoveTo - this.getYCoordinate()), Constants.MAX_AI_PADDLE_SPEED);
        if (this.getYCoordinate() > heightToMoveTo)
            this.getPosition().getCoordinates().y -= diffOrMaxSpeed(Math.round(this.getYCoordinate() - heightToMoveTo), Constants.MAX_AI_PADDLE_SPEED);
    }

    /**
     * @return The speed the coordinates should be subtracted or added to.
     */
    private int diffOrMaxSpeed(int difference, int maxSpeed) {
        return Math.min(difference, maxSpeed);
    }

    /**
     * @return the current UserPaddle's y-coordinate
     */
    public float getYCoordinate() {
        return this.position.getCoordinates().y;
    }

    /**
     * @return Whether the current UserPaddle contains the ball's y-coordinates or not.
     */
    public boolean containsYBall(float y, int ballHeight) {
        // If  the bottom of the ball is greater than the top    of the paddle
        // AND the top    of the ball is smaller than the bottom of the paddle
        return y + ballHeight > this.getYCoordinate() && y < this.getYCoordinate() + this.height;
    }

    /**
     * @return A String representation for this UserPaddle
     */
    @Override
    public String toString() {
        return "UserPaddle{" +
                "xy=" + this.position.getCoordinates() +
                ", height=" + this.height +
                ", width=" + this.width +
                ", xvelocity=" + this.position.getXvelocity() +
                ", yvelocity=" + this.position.getYvelocity() +
                '}';
    }
}
