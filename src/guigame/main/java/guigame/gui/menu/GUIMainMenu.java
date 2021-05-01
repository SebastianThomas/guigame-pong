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

/**
 * An implementation of {@code GUIMenu} for the {@code MainMenu}:
 * Set settings for the game and start it from this menu.
 *
 * @see MainMenu
 */
public class GUIMainMenu extends GUIMenu {
    /**
     * {@code Players} to modify
     */
    private final Players players;
    /**
     * Logic-part
     */
    private final MainMenu mainMenu;

    /**
     * Create a new MainMenu-GUI.
     *
     * @param mainMenu       the {@code MainMenu} to get settings from
     * @param players        the {@code Players} to edit from menu
     * @param startGameEvent the {@code StartGameEvent} to invoke when game should be started
     */
    public GUIMainMenu(MainMenu mainMenu, Players players, StartGameEvent startGameEvent) {
        // Set params
        this.mainMenu = mainMenu;
        this.players = players;

        // Set background color
        this.setBackground(Constants.bgColor);

        // Set layout and constraints
        GridBagLayout l = new GridBagLayout();
        this.constraints = new GridBagConstraints();

        this.setLayout(l);

        // Create user interface
        this.createLabel();
        this.createButtons(startGameEvent);
        this.createSliders();
    }

    /**
     * Create the title-label ("PONG") and add it to the layout.
     */
    private void createLabel() {
        // Create new GUILabel
        GUILabel guiLabel = new GUILabel("PONG", 40);

        // Add label
        this.addComponent(guiLabel, 0, 0, 2, 1, 10);
    }

    /**
     * Create buttons for Start game and select users.
     *
     * @param startGameEvent the {@code StartGameEvent} to invoke when game should be started
     */
    private void createButtons(StartGameEvent startGameEvent) {
        // Create start game button and show it
        StartGameBaseButton startButton = new StartGameBaseButton(startGameEvent);
        this.addComponent(startButton, 0, 1, 1, 1, 10);

        // Create a select user submenu and show it
        GUISelectUsersSubmenu guiSelectUsersSubmenu = new GUISelectUsersSubmenu(this.players);
        // Add name inputs
        this.addComponent(guiSelectUsersSubmenu.getTextFieldPanel(), 0, 2, 1, 2, 2);
        // Add sliders
        this.addComponent(guiSelectUsersSubmenu, 0, 3, 1, 2, 2);
    }

    /**
     * Create sliders for AI-Difficulty and Ball-speed.
     */
    private void createSliders() {
        // AI difficulty
        // Slider for the maximum AI-speed
        GUISlider aiDifficulty = new GUISlider(1, 4, 1, Constants.MAX_AI_PADDLE_SPEED, "Ganz einfach (1)", "Einfach (2)", "Mittel (3)", "Schwer (4)");
        aiDifficulty.addChangeListener(e -> Constants.MAX_AI_PADDLE_SPEED = aiDifficulty.getValue());

        // Label for ball speed, with font-size = 16 and no border
        GUILabel aiDiffLabel = new GUILabel("Schwierigkeit des KI-Gegners", 16, false);

        // Panel as container for label and slider (ai-difficulty)
        GridLayout aiDiffGridLayout = new GridLayout(2, 1);
        JPanel aiDiffPanel = new JPanel(aiDiffGridLayout);
        // Set panel colors
        aiDiffPanel.setForeground(Constants.fgColor);
        aiDiffPanel.setBackground(Constants.bgColor);
        // Add label and slider to panel
        aiDiffPanel.add(aiDiffLabel);
        aiDiffPanel.add(aiDifficulty);

        // Add panel to menu
        this.addComponent(aiDiffPanel, 0, 4, 2, 1, 5);

        // Initial ball speed
        // Slider for the initial ball speed
        GUISlider ballSpeed = new GUISlider(1, 5, 1, Math.round(Constants.BALL_X_SPEED), "Sehr langsam (1)", "Langsam (2)", "Mittel (3)", "Schnell (4)", "Sehr schnell (5)");
        ballSpeed.addChangeListener(e -> Constants.BALL_X_SPEED = ballSpeed.getValue());

        // Label for ball speed, with font-size = 16 and no border
        GUILabel ballSpeedLabel = new GUILabel("Ball-Startgeschwindigkeit", 16, false);

        // Panel as container for label and slider (ball speed)
        GridLayout ballSpeedGridLayout = new GridLayout(2, 1);
        JPanel ballSpeedPanel = new JPanel(ballSpeedGridLayout);
        // Set panel colors
        ballSpeedPanel.setForeground(Constants.fgColor);
        ballSpeedPanel.setBackground(Constants.bgColor);
        // Add label and slider to panel
        ballSpeedPanel.add(ballSpeedLabel);
        ballSpeedPanel.add(ballSpeed);

        // Add panel to menu
        this.addComponent(ballSpeedPanel, 0, 5, 2, 1, 10);
    }

    /**
     * Add a component at a specific (x,y)-position.
     *
     * @param component component to add
     * @param x         x-position for the new component
     * @param y         y-position for the new component
     * @param weightx   weightx of the component
     * @param weighty   weighty of the component
     * @param pady      y-padding around the component
     */
    private void addComponent(JComponent component, int x, int y, int weighty, int weightx, int pady) {
        // Set params
        this.constraints.gridx = x;
        this.constraints.gridy = y;

        // Insets
        this.constraints.insets = new Insets(pady, 20, pady, 20);

        // x-direction weight
        this.constraints.weightx = weightx;

        // Fill horizontally and center
        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        this.constraints.anchor = GridBagConstraints.CENTER;

        this.constraints.weighty = weighty;

        // Add component
        this.add(component, this.constraints);
    }
}
