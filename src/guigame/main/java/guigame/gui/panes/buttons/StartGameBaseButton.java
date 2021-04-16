package guigame.gui.panes.buttons;

import guigame.logic.Constants;
import guigame.logic.event.StartGameEvent;

public class StartGameBaseButton extends BaseButton {
    public StartGameBaseButton(StartGameEvent startGameEvent) {
        super("Start", 20, true, Constants.bgColor);
        this.addActionListener(l -> startGameEvent.action());
    }
}
