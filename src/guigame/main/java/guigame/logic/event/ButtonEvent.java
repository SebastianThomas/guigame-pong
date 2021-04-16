package guigame.logic.event;

public class ButtonEvent implements Event {
    private final String button;

    public ButtonEvent(String button) {
        this.button = button;
    }

    @Override
    public void action() {
    }

    public String getButton() {
        return this.button;
    }
}
