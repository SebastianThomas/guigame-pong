package guigame.logic.event;

import guigame.logic.main.MainGame;

public class StartGameEventListener implements EventListener {
    private final MainGame game;

    public StartGameEventListener(MainGame game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(Event e) {
        this.game.startGame();
    }
}
