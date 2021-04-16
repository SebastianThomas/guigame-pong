package guigame.logic.event;

public class KeyboardPressedEvent implements Event {
    private final KeyboardPressedEventListener l;
    private final int button;

    public KeyboardPressedEvent(KeyboardPressedEventListener l, int button) {
        this.l = l;
        this.button = button;
    }

    @Override
    public void action() {
        l.actionPerformed(this);
    }

    public int getButton() {
        return this.button;
    }
}
