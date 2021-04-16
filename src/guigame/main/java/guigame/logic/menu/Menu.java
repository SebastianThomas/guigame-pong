package guigame.logic.menu;

import guigame.logic.event.askers.LeaveGameAsker;

import javax.swing.*;

public abstract class Menu {
    public void askLeaveGame(JFrame owner) {
        new LeaveGameAsker(owner);
    }
}
