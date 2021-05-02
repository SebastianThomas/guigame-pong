package guigame.logic.event;

/**
 * Implementation of {@code Event} which should be fired when a player is changed on the {@code MainMenu}.
 *
 * @see PlayersChangedEventListener
 * @see guigame.logic.menu.MainMenu
 */
public class PlayersChangedEvent implements Event {
    /**
     * The (potentially new) number of humans playing.
     */
    private final int nrOfHumans;
    /**
     * Index of the player whose name was changed.
     *
     * @see PlayersChangedEvent#name
     */
    private final int index;
    /**
     * New name of the player.
     *
     * @see PlayersChangedEvent#index
     */
    private final String name;
    /**
     * Listener to notify that a change occurs.
     */
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

        this.index = -1;
        this.name = null;
    }

    /**
     * Initialize a new {@code PlayersChangedEvent}.
     * You should fire the event immediately by calling the {@code action}-method.
     *
     * @param l     A listener to report the action to
     * @param index the index of the player to change the name
     * @param name  the new name for the player at index
     * @see PlayersChangedEventListener
     * @see PlayersChangedEvent#action()
     */
    public PlayersChangedEvent(PlayersChangedEventListener l, int index, String name) {
        this.l = l;

        this.nrOfHumans = -1;

        this.index = index;
        this.name = name;
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

    /**
     * @return the index for the player whose name changed
     * @see PlayersChangedEvent#getNewName()
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * @return the new name for the player at the given index
     * @see PlayersChangedEvent#getIndex()
     */
    public String getNewName() {
        return this.name;
    }
}
