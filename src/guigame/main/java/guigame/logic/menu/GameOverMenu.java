package guigame.logic.menu;

import guigame.gui.menu.GUIGameOverMenu;
import guigame.logic.event.StartGameEvent;
import guigame.logic.event.StartGameEventListener;
import guigame.logic.players.Players;

import javax.swing.*;

public class GameOverMenu extends Menu {
    private final JFrame guiOwner;
    private Players players;
    private int winner;

    private StartGameEvent event;
    private GUIGameOverMenu guiMenu;

    public GameOverMenu(JFrame guiOwner) {
        this.guiOwner = guiOwner;
    }

    public GameOverMenu(JFrame guiOwner, StartGameEventListener l, Players players, int winner) {
        this(guiOwner);
        this.setParams(l, players, winner);
    }

    public void setParams(StartGameEventListener l, Players players, int winner) {
        this.event = new StartGameEvent(l);

        this.players = players;
        this.winner = winner;

        this.guiMenu = new GUIGameOverMenu(this, this.players, this.getWinnerMessage(), this.event);
    }

    public String getWinnerMessage() {
        return String.format("Spieler %d (%s) hat gewonnen!", this.winner + 1, this.players.getPlayers()[winner].getName());
    }

    public JFrame getGUIOwner() {
        return this.guiOwner;
    }

    public GUIGameOverMenu getGuiMenu() {
        return this.guiMenu;
    }
}
