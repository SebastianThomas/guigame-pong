package guigame.logic.event;

/**
 * Implementation of {@code Event} which should be fired when a player is changed on the {@code MainMenu}.
 *
 * @see PlayersChangedEventListener
 * @see guigame.logic.menu.MainMenu
 */
public class PlayersChangedEvent implements Event {
    private final int nrOfHumans;
    private final PlayersChangedEventListener l;

    /**
     * Initialize a new {@code PlayersChangedEvent}.
     * You should fire the event immediately by calling the {@code action}-method.
     *
     * @param l          A listener to report the action to
     * @param nrOfHumans Nr of Human players selected (0 or 1)
     * @see PlayersChangedEventListener
     * @see PlayersChangedEvent#action()
     */
    public PlayersChangedEvent(PlayersChangedEventListener l, int nrOfHumans) {
        this.l = l;
        this.nrOfHumans = nrOfHumans;
    }

    /**
     * Fire the event --> report the press to the listener (from constructor-parameter).
     *
     * @see PlayersChangedEventListener#actionPerformed(Event)
     */
    @Override
    public void action() {
        l.actionPerformed(this);
    }

    /**
     * @return the number of selected humans (0 or 1) (from constructor-parameter).
     */
    public int getNrOfHumans() {
        return this.nrOfHumans;
    }
}
