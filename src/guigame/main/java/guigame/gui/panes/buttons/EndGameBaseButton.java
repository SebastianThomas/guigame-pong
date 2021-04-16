package guigame.gui.panes.buttons;

import guigame.logic.event.askers.LeaveGameAsker;

import javax.swing.*;

public class EndGameBaseButton extends BaseButton {
    public EndGameBaseButton(JFrame owner) {
        super("Beenden");
        this.addActionListener(l -> new LeaveGameAsker(owner));
    }
}
