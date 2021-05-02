package guigame.logic.event;

/**
 * This interface has the important function for an {@code Event}.
 * The action could trigger an {@code EventListener}.
 *
 * @see EventListener
 */
@FunctionalInterface
public interface Event {
    /**
     * Trigger the event.
     */
    void action();
}
