package guigame.logic.event;

import guigame.logic.main.MainGame;

public class KeyboardPressedEventListener implements EventListener {
    private final MainGame mainGame;

    public KeyboardPressedEventListener(MainGame g) {
        this.mainGame = g;
    }

    @Override
    public void actionPerformed(Event e) {
        KeyboardPressedEvent event = ((KeyboardPressedEvent) e);

        // TODO: Other keys
        switch (event.getButton()) {
            case 27 -> this.mainGame.escapePressed();
            case 37 -> this.mainGame.leftArrowPressed();
            case 38 -> this.mainGame.topArrowPressed();
            case 39 -> this.mainGame.rightArrowPressed();
            case 40 -> this.mainGame.bottomArrowPressed();
            default -> {
                this.mainGame.otherKeyPressed(event.getButton());
                System.out.println("KEY: " + event.getButton());
            }
        }
    }
}
