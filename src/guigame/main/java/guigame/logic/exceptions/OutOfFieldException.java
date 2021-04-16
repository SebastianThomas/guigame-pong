package guigame.logic.exceptions;

import guigame.logic.main.Directions;

public class OutOfFieldException extends Exception {
    Directions direction;

    public OutOfFieldException(Directions d) {
        super("Das Spielgerät ist außerhalb des Spielfelds");

        this.direction = d;
    }

    public Directions getDirection() {
        return direction;
    }
}
