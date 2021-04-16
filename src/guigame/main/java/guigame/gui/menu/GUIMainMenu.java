package guigame.gui.menu;

import guigame.gui.menu.submenus.GUISelectUsersSubmenu;
import guigame.gui.panes.GUILabel;
import guigame.gui.panes.buttons.StartGameBaseButton;
import guigame.logic.Constants;
import guigame.logic.event.StartGameEvent;
import guigame.logic.menu.MainMenu;
import guigame.logic.players.Players;

import javax.swing.*;
import java.awt.*;

public class GUIMainMenu extends GUIMenu {
    private final Players players;
    private final MainMenu mainMenu;

    private final GridBagConstraints constraints;

    public GUIMainMenu(MainMenu mainMenu, Players players, StartGameEvent startGameEvent) {
        this.mainMenu = mainMenu;
        this.players = players;

        this.setBackground(Constants.bgColor);

        GridBagLayout l = new GridBagLayout();
        this.constraints = new GridBagConstraints();

        this.setLayout(l);

        this.createLabel();
        this.createButtons(startGameEvent);
    }

    private void createLabel() {
        GUILabel guiLabel = new GUILabel("PONG", 40);
        guiLabel.setForeground(Constants.fgColor);

        this.addComponent(guiLabel, 0, 0, 2, 10);
    }

    // Create buttons: Start game and select users
    private void createButtons(StartGameEvent startGameEvent) {
        StartGameBaseButton startButton = new StartGameBaseButton(startGameEvent);
        this.addComponent(startButton, 0, 1, 1, 10);

        GUISelectUsersSubmenu guiSelectUsersSubmenu = new GUISelectUsersSubmenu(this.players);
        this.addComponent(guiSelectUsersSubmenu, 0, 2, 2, 10);
    }

    private void addComponent(JComponent component, int x, int y, int weightx, int pady) {
        this.constraints.gridx = x;
        this.constraints.gridy = y;

        this.constraints.insets = new Insets(pady, 20, pady, 20);

        this.constraints.weightx = weightx;

        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        this.constraints.anchor = GridBagConstraints.CENTER;

        this.add(component, this.constraints);
    }
}
