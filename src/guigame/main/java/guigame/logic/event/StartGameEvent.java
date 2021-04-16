package guigame.logic.event;

public class StartGameEvent implements Event {
    private StartGameEventListener l;

    public StartGameEvent(StartGameEventListener l) {
        this.l = l;
    }

    @Override
    public void action() {
        l.actionPerformed(this);
    }
}
