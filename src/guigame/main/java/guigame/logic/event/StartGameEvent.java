package guigame.logic.event;

/**
 * Implementation of {@code Event} which should be fired when a new game should be started (from anywhere).
 *
 * @see StartGameEventListener
 */
public class StartGameEvent implements Event {
    private StartGameEventListener l;

    /**
     * Initialize a new {@code PlayersChangedEvent}.
     * You must not fire the event immediately by calling the {@code action}-method, only when the game is started.
     *
     * @param l A listener to report the action to
     * @see PlayersChangedEventListener
     * @see PlayersChangedEvent#action()
     */
    public StartGameEvent(StartGameEventListener l) {
        this.l = l;
    }

    /**
     * Fire the event --> report the press to the listener (from constructor-parameter).
     *
     * @see StartGameEventListener#actionPerformed(Event)
     */
    @Override
    public void action() {
        l.actionPerformed(this);
    }
}
