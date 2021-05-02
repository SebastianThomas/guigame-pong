package guigame.logic.main;

/**
 * The directions represent the four possible directions and NONE that all {@code GameObjects} can represent.
 *
 * @see guigame.logic.objects.GameObject
 * @see guigame.logic.objects.Ball
 * @see guigame.logic.objects.UserPaddle
 * @see guigame.gui.main.GUIGameBoard
 */
public enum Directions {
    /**
     * Negatively into y-direction
     */
    UP,
    /**
     * Positively into y-direction
     */
    DOWN,
    /**
     * Negatively into x-direction
     */
    LEFT,
    /**
     * Positively into x-direction
     */
    RIGHT,

    /**
     * None of the above specified (in the current context)
     */
    NONE
}
