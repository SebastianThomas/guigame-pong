/**
 * Defines the {@code Pong}-game's logic and GUI. Use {@code StartGame.main}-method to run the game.
 * <p>
 * They may partly be used from outside of the project but most classes are specificly done for a work only util inside the game.
 * </p>
 *
 * @see guigame.logic.main.StartGame
 */
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

    exports guigame.gui.borders;
    exports guigame.gui.main;
    exports guigame.gui.menu;
    exports guigame.gui.menu.submenus;
    exports guigame.gui.objects;
    exports guigame.gui.panes;
    exports guigame.gui.panes.buttons;
}
