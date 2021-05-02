package guigame.logic.event;

import guigame.gui.menu.submenus.GUISelectUsersSubmenu;

/**
 * This event will be triggered when a player-change (on the subMenu) is invoked.
 *
 * @see PlayersChangedEvent
 * @see GUISelectUsersSubmenu
 */
public class PlayersChangedEventListener implements EventListener {
    /**
     * SubMenu to notify on player change
     */
    private final GUISelectUsersSubmenu subMenu;

    /**
     * Create a {@code PlayersChangedEventListener}. {@code actionPerformed} will then invoke {@code playersChanged} on a {@code GUISelectUsersSubmenu}.
     *
     * @param subMenu the {@code GUISelectUsersSubmenu} to set users from (one or two players and their names)
     * @see PlayersChangedEventListener#actionPerformed(Event)
     * @see GUISelectUsersSubmenu#playersChanged(int)
     */
    public PlayersChangedEventListener(GUISelectUsersSubmenu subMenu) {
        this.subMenu = subMenu;
    }

    /**
     * Invoke when a change with the number of humans occures.
     *
     * @param e A {@code PlayersChangedEvent} with the new number of humans
     * @see PlayersChangedEvent
     */
    @Override
    public void actionPerformed(Event e) {
        // Data type conversion to get the number of humans
        PlayersChangedEvent event = (PlayersChangedEvent) e;
        if (event.getNrOfHumans() != -1) {
            // Invoke the event on submenu with the number of humans
            this.subMenu.playersChanged(event.getNrOfHumans());
            return;
        }

        // Invoke the event on submenu with the player's new name
        this.subMenu.playersChanged(event.getIndex(), event.getNewName());
    }
}
