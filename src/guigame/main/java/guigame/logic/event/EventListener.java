package guigame.logic.event;

/**
 * This interface has the important function for an {@code EventListener}.
 * The actionPerformed is triggered by an {@code EventListener.action()}.
 *
 * @see Event#action()
 */
@FunctionalInterface
public interface EventListener {
    /**
     * Invoke when a fitting {@code Event} was triggered.
     *
     * @param e the {@code Event} to get options from (should be a subclass of {@code Event})
     */
    void actionPerformed(Event e);
}
