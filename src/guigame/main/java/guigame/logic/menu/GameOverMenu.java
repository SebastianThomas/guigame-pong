package guigame.logic.menu;

import guigame.gui.main.GUIGameOverWindow;
import guigame.gui.menu.GUIGameOverMenu;
import guigame.logic.event.StartGameEvent;
import guigame.logic.event.StartGameEventListener;
import guigame.logic.players.Players;

import javax.swing.*;

/**
 * Logic for {@code GUIGameOverMenu}.
 * Calculate a nice winner message.
 * <p>
 * Can either be constructed with parameters directly, or the parameters are set later before it is shown.
 * </p>
 *
 * @see GUIGameOverMenu
 */
public class GameOverMenu extends Menu {
    private Players players;
    private int winner;

    private StartGameEvent event;
    private GUIGameOverMenu guiMenu;

    private boolean initialized;

    private JFrame guiOwner;

    /**
     * Create a new GameOverMenu. {@code setParams} must be invoked before
     *
     * @see GameOverMenu#setParams(StartGameEventListener, Players, int)
     * @see GameOverMenu#setWindow()
     */
    public GameOverMenu() {
        // Make sure initialized is false
        this.initialized = false;

        // Default value for int would be 0, but 0 is a valid array-index
        this.winner = -1;
        // All other parameters are null and wait till they are initialized
    }

    /**
     * Create a new GameOverMenu and set the parameters. Then show the window.
     * The all-in-one solution for:
     * <p></p>
     * <p>
     * {@code GameOverMenu g = new GameOverMenu();}
     * </p>
     * <p>
     * g.setParams(l,p,w);
     * </p>
     * <p>
     * g.setWindow();
     * </p>
     *
     * @param l       The listener for start game events
     * @param players The players playing against each other
     * @param winner  The winner's index (left = 0; right = 1)
     * @see GameOverMenu#setParams(StartGameEventListener, Players, int)
     */
    public GameOverMenu(StartGameEventListener l, Players players, int winner) {
        this();
        this.setParams(l, players, winner);
        this.setWindow();
    }

    public void setParams(StartGameEventListener l, Players players, int winner) {
        this.initialized = true;

        this.event = new StartGameEvent(l);

        this.players = players;
        this.winner = winner;

        this.guiMenu = new GUIGameOverMenu(this, this.players, this.getWinnerMessage(), this.event);
    }

    /**
     * Shows a new {@code GUIGameOverWindow}.
     *
     * @throws NullPointerException If any param has not been set yet.
     * @see GameOverMenu#setParams(StartGameEventListener, Players, int)
     */
    public void setWindow() throws NullPointerException {
        // Message for the NPE
        String nullPointerMsg = "GameOverMenu's params have not been set yet!";

        // If this is not initialized yet any parameter is still null / default (for winner == -1 see constructor)
        if (!this.initialized ||
                this.event == null ||
                this.guiMenu == null ||
                this.winner == -1 ||
                this.players == null)
            throw new NullPointerException(nullPointerMsg);

        // Create a new GUIGameOverWindow (JFrame) to show the GameOverMenu
        this.guiOwner = new GUIGameOverWindow(this.guiMenu);
        // Since the guiOwner is set yet, the Menu is initialized with Buttons and Labels
        this.guiMenu.initButtonsAndLabel();
    }

    /**
     * @return the window the current GameOverWindow is shown on.
     * @throws NullPointerException If the window has not been set yet
     * @see GameOverMenu#setWindow()
     */
    public JFrame getWindow() throws NullPointerException {
        if (this.guiOwner == null) throw new NullPointerException("The GUIOwner has not been set yet!");
        return this.guiOwner;
    }

    /**
     * Format a {@code String} to show which player has won.
     *
     * @see GameOverMenu#winner
     */
    public String getWinnerMessage() {
        return String.format("Spieler %d (%s) hat gewonnen!", this.winner + 1, this.players.getPlayersArray()[winner].getName());
    }

    /**
     * @return the {@code GUIGameOverMenu}
     */
    public GUIGameOverMenu getGuiMenu() {
        return this.guiMenu;
    }
}
