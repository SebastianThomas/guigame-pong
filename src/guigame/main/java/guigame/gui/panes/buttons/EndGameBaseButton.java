package guigame.gui.panes.buttons;

import guigame.logic.event.askers.LeaveGameAsker;

import javax.swing.*;

/**
 * An implementation of {@code BaseButton}.
 * Specifically done for ending the Game.
 * Create {@code LeaveGameAsker} on click.
 * Has a font-size of 20 and a dotted border.
 *
 * @see LeaveGameAsker
 */
public class EndGameBaseButton extends BaseButton {
    /**
     * Create a new EndGameBaseButton.
     * This button has a label of "Beenden".
     * On click, it will create a new {@code LeaveGameAsker}.
     *
     * @param owner The {@code JFrame} to be blocked by the {@code LeaveGameAsker}.
     */
    public EndGameBaseButton(JFrame owner) {
        super("Beenden");
        this.addActionListener(l -> new LeaveGameAsker(owner));
    }
}
