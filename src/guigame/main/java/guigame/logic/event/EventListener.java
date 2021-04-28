package guigame.logic.event;

/**
 * This interface has the important function for an {@code EventListener}.
 * The actionPerformed is triggered by an {@code EventListener.action()}.
 *
 * @see Event#action()
 */
@FunctionalInterface
public interface EventListener {
    void actionPerformed(Event e);
}
