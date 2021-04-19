package guigame.gui.menu;

import guigame.gui.menu.submenus.GUISelectUsersSubmenu;
import guigame.gui.panes.GUILabel;
import guigame.gui.panes.GUISlider;
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
        this.createSliders();
    }

    private void createLabel() {
        GUILabel guiLabel = new GUILabel("PONG", 40);
        guiLabel.setForeground(Constants.fgColor);

        this.addComponent(guiLabel, 0, 0, 2, 10);
    }

    /**
     * Create buttons for Start game and select users
     */
    private void createButtons(StartGameEvent startGameEvent) {
        StartGameBaseButton startButton = new StartGameBaseButton(startGameEvent);
        this.addComponent(startButton, 0, 1, 1, 10);

        GUISelectUsersSubmenu guiSelectUsersSubmenu = new GUISelectUsersSubmenu(this.players);
        this.addComponent(guiSelectUsersSubmenu, 0, 2, 2, 10);
    }

    private void createSliders() {
        // AI difficulty
        // Slider for the maximum AI-speed
        GUISlider aiDifficulty = new GUISlider(1, 4, 1, Constants.MAX_AI_PADDLE_SPEED, "Ganz einfach (1)", "Einfach (2)", "Mittel (3)", "Schwer (4)");
        aiDifficulty.addChangeListener(e -> Constants.MAX_AI_PADDLE_SPEED = aiDifficulty.getValue());

        // Label for ball speed, with font-size = 16 and no border
        GUILabel aiDiffLabel = new GUILabel("Schwierigkeit des KI-Gegners", 16, false);

        // Panel as container for label and slider (ball speed)
        GridLayout aiDiffGridLayout = new GridLayout(2, 1);
        JPanel aiDiffPanel = new JPanel(aiDiffGridLayout);
        aiDiffPanel.setForeground(Constants.fgColor);
        aiDiffPanel.setBackground(Constants.bgColor);
        aiDiffPanel.add(aiDiffLabel);
        aiDiffPanel.add(aiDifficulty);

        // Add panel to menu
        this.addComponent(aiDiffPanel, 0, 3, 2, 5);

        // Initial ball speed
        // Slider for the initial ball speed
        GUISlider ballSpeed = new GUISlider(1, 5, 1, Math.round(Constants.BALL_X_SPEED), "Sehr langsam (1)", "Langsam (2)", "Mittel (3)", "Schnell (4)", "Sehr schnell (5)");
        ballSpeed.addChangeListener(e -> Constants.BALL_X_SPEED = ballSpeed.getValue());

        // Label for ball speed, with font-size = 16 and no border
        GUILabel ballSpeedLabel = new GUILabel("Ball-Startgeschwindigkeit", 16, false);

        // Panel as container for label and slider (ball speed)
        GridLayout ballSpeedGridLayout = new GridLayout(2, 1);
        JPanel ballSpeedPanel = new JPanel(ballSpeedGridLayout);
        ballSpeedPanel.setForeground(Constants.fgColor);
        ballSpeedPanel.setBackground(Constants.bgColor);
        ballSpeedPanel.add(ballSpeedLabel);
        ballSpeedPanel.add(ballSpeed);

        // Add panel to menu
        this.addComponent(ballSpeedPanel, 0, 4, 2, 20);
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
