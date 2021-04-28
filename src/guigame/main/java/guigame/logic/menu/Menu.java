package guigame.logic.menu;

import guigame.logic.event.askers.LeaveGameAsker;

import javax.swing.*;

/**
 * Basic abstract for a {@code Menu}. Has the ability to create a new {@code LeaveGameAsker} and marks a {@code Menu} or Submenu.
 */
public abstract class Menu {
    /**
     * Create a new {@code LeaveGameAsker} blocking the owner-{@code JFrame}.
     *
     * @param owner the {@code JFrame} to block with the LeaveGameAsker.
     * @see LeaveGameAsker
     */
    public void askLeaveGame(JFrame owner) {
        new LeaveGameAsker(owner);
    }
}
