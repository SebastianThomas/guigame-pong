package guigame.logic.event;

import guigame.gui.menu.submenus.GUISelectUsersSubmenu;

public class PlayersChangedEventListener implements EventListener {
    private final GUISelectUsersSubmenu subMenu;

    public PlayersChangedEventListener(GUISelectUsersSubmenu subMenu) {
        this.subMenu = subMenu;
    }

    @Override
    public void actionPerformed(Event e) {
        PlayersChangedEvent event = (PlayersChangedEvent) e;
        this.subMenu.playersChanged(event.getNrOfHumans());
    }
}
