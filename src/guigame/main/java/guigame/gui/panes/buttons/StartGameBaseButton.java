package guigame.gui.panes.buttons;

import guigame.logic.Constants;
import guigame.logic.event.StartGameEvent;

/**
 * An implementation of {@code BaseButton}.
 * Specifically done for starting a new Game.
 * Has a font-size of 20 and a dotted border.
 *
 * @see StartGameEvent#action()
 */
public class StartGameBaseButton extends BaseButton {
    /**
     * Initialize a new StartGameBaseButton with a text of "Start" and a font-size of 20.
     * Then adds the action listener to fire the {@code action} method of the {@code StartGameEvent} on click.
     *
     * @see StartGameEvent
     */
    public StartGameBaseButton(StartGameEvent startGameEvent) {
        super("Start", 20, true, Constants.bgColor);
        this.addActionListener(l -> startGameEvent.action());
    }
}
