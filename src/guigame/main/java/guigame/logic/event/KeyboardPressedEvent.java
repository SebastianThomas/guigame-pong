package guigame.logic.event;

/**
 * Implementation of the {@code Event} which should be fired when a key on the keyboard is pressed.
 */
public class KeyboardPressedEvent implements Event {
    /**
     * Listener to notify when the Event is fired
     */
    private final KeyboardPressedEventListener l;
    /**
     * The pressed button to be transmitted to {@code KeyboardPressedEventListener}.
     *
     * @see KeyboardPressedEventListener
     */
    private final int button;

    /**
     * Create a new {@code KeyboardPressedEvent}.
     * You should fire the event immediately by calling the {@code action}-method.
     *
     * @param l      A listener to report the action to
     * @param button The pressed button
     * @see KeyboardPressedEventListener
     * @see KeyboardPressedEvent#action()
     */
    public KeyboardPressedEvent(KeyboardPressedEventListener l, int button) {
        this.l = l;
        this.button = button;
    }

    /**
     * Fire the event --> report the press to the listener (from constructor-parameter).
     *
     * @see KeyboardPressedEventListener#actionPerformed(Event)
     */
    @Override
    public void action() {
        l.actionPerformed(this);
    }

    /**
     * @return the pressed button (from constructor-parameter).
     */
    public int getButton() {
        return this.button;
    }
}
