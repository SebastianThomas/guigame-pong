package guigame.logic;

import java.awt.*;

public class Constants {
    /**
     * The time to wait before showing start-next-point-button [in milliseconds].
     */
    public static final int DELAY_END_OF_POINT = 2000;

    /**
     * The maximum y-speed for the ball
     */
    public static final int MAX_BALL_Y_SPEED = 4;

    /**
     * The update value for the ball's x-speed after each paddle collision
     */
    public static final float BALL_X_SPEED_UPDATE = (float) 0.2;

    /**
     * The height of the {@code GameBoard}
     */
    public static final int GAME_BOARD_HEIGHT = 500;
    /**
     * The width of the {@code GameBoard}
     */
    public static final int GAME_BOARD_WIDTH = 750;
    /**
     * The height and width for the {@code Ball}.
     */
    public static final int BALL_DIMENSION_SIZE = 30;

    // --------------------- COLORS ---------------------
    public static final Color bgColor = new Color(0);
    // Foreground colors
    public static final Color fgColor = new Color(255, 255, 255);
    public static final Color fgHoverColor = new Color(150, 150, 150);
    // Foreground selected colors
    public static final Color fgSelectedColor = new Color(0, 255, 0);
    public static final Color fgHoverSelectedColor = new Color(100, 255, 100);
    // Slider color
    public static final Color sliderMarkedColor = new Color(100, 100, 100);
    // ---------------------        ---------------------

    /**
     * End game at a max score of 10 points for one person
     */
    public static final int winningScore = 10;

    // TODO: Set this speed from Main Menu
    /**
     * The max speed of an AI player.
     */
    public static int MAX_AI_PADDLE_SPEED = 3;

    // TODO: Set this initial speed from Main Menu
    /**
     * The ball's current x-speed, initial value: 3.
     */
    public static float BALL_X_SPEED = 3;
}
