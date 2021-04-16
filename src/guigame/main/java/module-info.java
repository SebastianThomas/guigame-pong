module guigame {
    requires java.desktop;

    exports guigame.logic;
    exports guigame.logic.event;
    exports guigame.logic.event.askers;
    exports guigame.logic.exceptions;
    exports guigame.logic.main;
    exports guigame.logic.menu;
    exports guigame.logic.objects;
    exports guigame.logic.players;

    exports guigame.gui.main;
    exports guigame.gui.menu;
    exports guigame.gui.objects;
    exports guigame.gui.panes;
    exports guigame.gui.panes.buttons;
}
