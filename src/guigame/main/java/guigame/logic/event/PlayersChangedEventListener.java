package guigame.logic.event;

import guigame.gui.menu.submenus.GUISelectUsersSubmenu;

/**
 * This event will be triggered when a player-change (on the subMenu) is invoked.
 *
 * @see PlayersChangedEvent
 * @see GUISelectUsersSubmenu
 */
public class PlayersChangedEventListener implements EventListener {
    private final GUISelectUsersSubmenu subMenu;

    /**
     * Create a {@code PlayersChangedEventListener}. {@code actionPerformed} will then invoke {@code playersChanged} on a {@code GUISelectUsersSubmenu}.
     *
     * @see PlayersChangedEventListener#actionPerformed(Event)
     * @see GUISelectUsersSubmenu#playersChanged(int)
     */
    public PlayersChangedEventListener(GUISelectUsersSubmenu subMenu) {
        this.subMenu = subMenu;
    }

    @Override
    public void actionPerformed(Event e) {
        PlayersChangedEvent event = (PlayersChangedEvent) e;
        this.subMenu.playersChanged(event.getNrOfHumans());
    }
}
