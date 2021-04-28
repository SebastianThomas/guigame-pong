package guigame.logic.event;

/**
 * An event listener for Keyboard-events.
 *
 * @see KeyboardPressedEvent
 */
public class KeyboardPressedEventListener implements EventListener {
    /**
     * The {@code KeyboardButtonAdapter} to invoke keyboard presses on.
     */
    private final KeyboardButtonAdapter adapter;

    /**
     * The adapter to invoke events on.
     *
     * @param adapter The {@code KeyboardButtonAdapter} to invoke keyboard presses on.
     * @see KeyboardButtonAdapter
     */
    public KeyboardPressedEventListener(KeyboardButtonAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * When a {@code KeyboardPressedEvent}'s {@code action} method was triggered.
     *
     * @see KeyboardPressedEvent
     * @see KeyboardPressedEvent#action()
     */
    @Override
    public void actionPerformed(Event e) {
        // Parse event to KeyboardPressedEvent to get the button from it.
        KeyboardPressedEvent event = (KeyboardPressedEvent) e;

        // Switch for the button's possible values
        switch (event.getButton()) {
            case 27 -> this.adapter.escapePressed();
            case 37 -> this.adapter.leftArrowPressed();
            case 38 -> this.adapter.topArrowPressed();
            case 39 -> this.adapter.rightArrowPressed();
            case 40 -> this.adapter.bottomArrowPressed();
            default -> {
                // Button has no specified adapter-method
                this.adapter.otherKeyPressed(event.getButton());
                System.out.println("KEY: " + event.getButton());
            }
        }
    }
}
