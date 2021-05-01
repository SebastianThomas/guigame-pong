package guigame.gui.menu;

import guigame.gui.panes.GUILabel;
import guigame.gui.panes.GUIPanel;
import guigame.gui.panes.buttons.BackToMainMenuButton;
import guigame.gui.panes.buttons.EndGameBaseButton;
import guigame.gui.panes.buttons.StartGameBaseButton;
import guigame.logic.event.StartGameEvent;
import guigame.logic.menu.GameOverMenu;
import guigame.logic.players.Players;

import java.awt.*;

/**
 * Implementation of {@code GUIMenu}: GUI-interface for the menu which is shown when the game is over.
 * "Belongs" to a {@code GameOverMenu} and takes its values (for {@code msg} etc.).
 * This {@code GUIMenu} uses a {@code GridLayout} instead of the normal {@code GridBagLayout}.
 *
 * @see GameOverMenu
 */
public class GUIGameOverMenu extends GUIMenu {
    private Players players;
    private final GameOverMenu gameOverMenu;
    private final StartGameEvent startGameEvent;

    private final String msg;

    /**
     * Uses a "normal" {@code GridLayout} instead of the {@code GridBagLayout} from {@code GUIMenu}.
     *
     * @see GUIMenu#layout
     */
    private GridLayout gridLayout;

    /**
     * Create a new GUIGameOverMenu. Should not be constructed manually; use {@code GameOverMenu}'s constructor instead.
     *
     * @param gameOverMenu {@code GameOverMenu} this GUI belongs to
     * @param players      {@code Players} who played against each other
     * @param msg          message to display (should be calculated in {@code GameOverMenu})
     * @param event        {@code StartGameEvent} to be invoked by {@code StartGameBaseButton}
     * @see GameOverMenu
     * @see GameOverMenu#getWinnerMessage()
     */
    public GUIGameOverMenu(GameOverMenu gameOverMenu, Players players, String msg, StartGameEvent event) {
        this.gameOverMenu = gameOverMenu;
        this.players = players;
        this.startGameEvent = event;

        this.msg = msg;

        this.gridLayout = new GridLayout(2, 1);

        this.setLayout(this.gridLayout);
    }

    /**
     * Create a Label for the message.
     * Initialize the buttons including listeners.
     */
    public void initButtonsAndLabel() {
        this.createGameOverLabel();
        this.addButtons();
    }

    /**
     * Create a label to display the message.
     *
     * @see GUIGameOverMenu#msg
     */
    private void createGameOverLabel() {
        // Create Label
        GUILabel msgLabel = new GUILabel(this.msg, 24, false);

        // Show label
        this.add(msgLabel);
    }

    /**
     * Creates the buttons for:
     * <p>
     * - start game
     * </p>
     * <p>
     * - exit game
     * </p>
     * <p>
     * - back to main menu
     * </p>
     * and display them.
     *
     * @throws NullPointerException from {@code GameOverMenu.getWindow()}
     * @see StartGameBaseButton
     * @see EndGameBaseButton
     * @see GameOverMenu#getWindow()
     */
    private void addButtons() {
        // Init panel
        GUIPanel panel = new GUIPanel();

        // Create / initialize the buttons
        StartGameBaseButton startGameButton = new StartGameBaseButton(this.startGameEvent);
        EndGameBaseButton endGameButton = new EndGameBaseButton(this.gameOverMenu.getWindow());
        BackToMainMenuButton backToMainButton = new BackToMainMenuButton(this.gameOverMenu.getWindow());

        // Add buttons to panel
        panel.add(startGameButton);
        panel.add(endGameButton);
        panel.add(backToMainButton);

        // Add panel
        this.add(panel);
    }
}
