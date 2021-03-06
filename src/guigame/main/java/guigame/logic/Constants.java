package guigame.logic;

import java.awt.*;

/**
 * Maintains important constants which are accessed throughout the code.
 * Cannot be instantiated.
 */
public abstract class Constants {
    /**
     * The time to wait before showing start-next-point-button [in milliseconds].
     */
    public static final int DELAY_END_OF_POINT = 2000;

    /**
     * The maximum y-speed for the ball
     */
    public static final int MAX_BALL_Y_SPEED = 7;

    /**
     * The update value for the ball's x-speed after each paddle collision
     */
    public static final float BALL_X_SPEED_UPDATE = (float) 0.2;

    /**
     * The height of the {@code GameBoard}
     */
    public static final int GAME_BOARD_HEIGHT = 550;
    /**
     * The width of the {@code GameBoard}
     */
    public static final int GAME_BOARD_WIDTH = 900;
    /**
     * The height and width for the {@code Ball}.
     */
    public static final int BALL_DIMENSION_SIZE = 30;

    // --------------------- COLORS ---------------------
    /**
     * {@code Color} for the background.
     */
    public static final Color bgColor = new Color(0);
    /**
     * {@code Color} for unselected elements in the foreground.
     *
     * @see Constants#fgHoverColor
     */
    public static final Color fgColor = new Color(255, 255, 255);
    /**
     * {@code Color} for unselected elements in the foreground when they are hovered over.
     *
     * @see Constants#fgColor
     */
    public static final Color fgHoverColor = new Color(150, 150, 150);
    /**
     * {@code Color} for selected elements (buttons) in the foreground.
     *
     * @see Constants#fgHoverSelectedColor
     */
    public static final Color fgSelectedColor = new Color(0, 255, 0);
    /**
     * {@code Color} for selected elements (buttons) in the foreground when they are hovered over.
     *
     * @see Constants#fgSelectedColor
     */
    public static final Color fgHoverSelectedColor = new Color(100, 255, 100);
    /**
     * Color for the marked part (left of the thumb) of the sliders.
     */
    public static final Color sliderMarkedColor = new Color(100, 100, 100);
    // ---------------------        ---------------------

    /**
     * End game at a max score of 5 points for one person
     */
    public static int WINNING_SCORE = 5;

    /**
     * The max speed of an AI player.
     */
    public static int MAX_AI_PADDLE_SPEED = 3;

    /**
     * The ball's current x-speed, initial value: 3.
     */
    public static float BALL_X_SPEED = 3;

    /**
     * Utility class:
     * must not be instantiated!
     */
    private Constants() {
    }
}
