package guigame.gui.menu;

import guigame.gui.panes.GUILabel;
import guigame.gui.panes.buttons.EndGameBaseButton;
import guigame.gui.panes.buttons.StartGameBaseButton;
import guigame.logic.event.StartGameEvent;
import guigame.logic.menu.GameOverMenu;
import guigame.logic.players.Players;

import javax.swing.*;
import java.awt.*;

public class GUIGameOverMenu extends GUIMenu {
    private Players players;
    private GameOverMenu gameOverMenu;
    private StartGameEvent startGameEvent;

    private String msg;

    private int columns = 2;
    private int rows;

    public GUIGameOverMenu(GameOverMenu gameOverMenu, Players players, String msg, StartGameEvent event) {
        this.gameOverMenu = gameOverMenu;
        this.players = players;
        this.startGameEvent = event;

        this.msg = msg;

        this.layout = new GridBagLayout();
        this.constraints = new GridBagConstraints();

        this.constraints.fill = GridBagConstraints.BOTH;

        this.createGameOverLabel();
        this.addButtons(this.startGameEvent);

        this.setVisible(true);
    }

    private void updatePlayer() {
    }

    private void createGameOverLabel() {
        GUILabel msgLabel = new GUILabel(this.msg, 24);

        this.addComponent(msgLabel, 0, 0, this.columns, 2);
    }

    private void addButtons(StartGameEvent startGameEvent) {
        StartGameBaseButton startGameButton = new StartGameBaseButton(startGameEvent);
        EndGameBaseButton endGameButton = new EndGameBaseButton(this.gameOverMenu.getGUIOwner());

        this.addComponent(startGameButton, 0, 1, 1, 1);
        this.addComponent(endGameButton, 1, 1, 1, 1);
    }

    private void addComponent(JComponent c, int x, int y, int width, int height) {
        this.constraints.gridx = x;
        this.constraints.gridy = y;

        this.constraints.gridwidth = width;
        this.constraints.gridheight = height;

        this.add(c, this.constraints);
    }
}
