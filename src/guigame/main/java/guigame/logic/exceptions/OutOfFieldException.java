package guigame.logic.exceptions;

import guigame.logic.main.Directions;

/**
 * This {@code Exception} must be caught. It indicates that a {@code GameObject} is out of the game field.
 */
public class OutOfFieldException extends Exception {
    /**
     * {@code Direction} this exception was thrown for
     */
    Directions direction;

    /**
     * Create a new {@code OutOfFieldException} with
     *
     * @param d the direction the {@code GameObject} went out of bounds.
     */
    public OutOfFieldException(Directions d) {
        // Create exception with a msg
        super("Das Spielgerät ist außerhalb des Spielfelds");

        // Set direction
        this.direction = d;
    }

    /**
     * @return the direction the {@code GameObject} went out of bounds.
     */
    public Directions getDirection() {
        return direction;
    }
}
