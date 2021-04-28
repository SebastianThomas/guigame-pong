package guigame.logic.event;

/**
 * Implementation of the {@code Event} which should be fired when a button is pressed.
 */
public class ButtonEvent implements Event {
    private final String button;

    /**
     * Create a new {@code ButtonEvent} with a name.
     *
     * @param button name of the button
     */
    public ButtonEvent(String button) {
        this.button = button;
    }

    /**
     * TODO: call anything?
     */
    @Override
    public void action() {
    }

    /**
     * @return the button name
     */
    public String getButton() {
        return this.button;
    }
}
