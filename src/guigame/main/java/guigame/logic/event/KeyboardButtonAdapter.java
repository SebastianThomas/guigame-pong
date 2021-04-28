package guigame.logic.event;

public interface KeyboardButtonAdapter {
    /**
     * From {@code KeyboardButtonAdapter}.
     * Invoked when escape is pressed.
     */
    void escapePressed();

    /**
     * From {@code KeyboardButtonAdapter}.
     * Invoked when the arrow to the left is pressed.
     */
    void leftArrowPressed();

    /**
     * From {@code KeyboardButtonAdapter}.
     * Invoked when the arrow to the top is pressed.
     */
    void topArrowPressed();

    /**
     * From {@code KeyboardButtonAdapter}.
     * Invoked when the arrow to the right is pressed.
     */
    void rightArrowPressed();

    /**
     * From {@code KeyboardButtonAdapter}.
     * Invoked when the arrow to the bottom is pressed.
     */
    void bottomArrowPressed();

    /**
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
    void otherKeyPressed(int key);
}
