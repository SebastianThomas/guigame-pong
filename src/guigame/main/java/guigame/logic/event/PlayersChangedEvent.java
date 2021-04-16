package guigame.logic.event;

public class PlayersChangedEvent implements Event {
    private final int nrOfHumans;
    private final PlayersChangedEventListener l;

    public PlayersChangedEvent(PlayersChangedEventListener l, int nrOfHumans) {
        this.l = l;
        this.nrOfHumans = nrOfHumans;
    }

    @Override
    public void action() {
        l.actionPerformed(this);
    }

    public int getNrOfHumans() {
        return this.nrOfHumans;
    }
}
