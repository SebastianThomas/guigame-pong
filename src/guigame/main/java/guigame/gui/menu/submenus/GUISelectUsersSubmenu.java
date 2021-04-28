package guigame.gui.menu.submenus;

import guigame.gui.menu.GUIMenu;
import guigame.gui.panes.buttons.BaseButton;
import guigame.logic.event.PlayersChangedEvent;
import guigame.logic.event.PlayersChangedEventListener;
import guigame.logic.players.Player;
import guigame.logic.players.Players;

/**
 * An implementation of {@code GUIMenu} which lets the user select whether
 * they want to play as one player against an artificial intelligence or
 * let two AIs play against each other.
 * <p>
 * TODO: Furthermore they can change the player's names
 */
public class GUISelectUsersSubmenu extends GUIMenu {
    private final Players players;
    private final PlayersChangedEventListener playersChangedEventListener;

    private final BaseButton twoAI;
    private final BaseButton oneAI;

    /**
     * Create a new Submenu for the player selection.
     *
     * @param players the currently selected / defined players to be eventually changed
     */
    public GUISelectUsersSubmenu(Players players) {
        super();
        // Set players
        this.players = players;

        // Set players changed listener
        this.playersChangedEventListener = new PlayersChangedEventListener(this);

        // Set buttons
        this.twoAI = new BaseButton("2 KIs gegeneinander");
        this.oneAI = new BaseButton("Sie gegen eine KI");

        // add action listeners to the button
        this.addActionListeners(this.playersChangedEventListener);

        // Prepare buttons and set them visible
        this.addButtons();
    }

    /**
     * Add action listeners to buttons.
     *
     * @param playersChangedEventListener The listener for {@code PlayersChangedEvent}s.
     */
    private void addActionListeners(PlayersChangedEventListener playersChangedEventListener) {
        // Add action listener to buttons
        this.twoAI.addActionListener(l -> new PlayersChangedEvent(playersChangedEventListener, 0).action());
        this.oneAI.addActionListener(l -> new PlayersChangedEvent(playersChangedEventListener, 1).action());
    }

    /**
     * Add buttons to panel and mark the right one as selected.
     */
    private void addButtons() {
        // Mark the right button as selected
        this.setButtonsSelected();

        // Add buttons to panel
        this.add(this.twoAI);
        this.add(this.oneAI);
    }

    /**
     * Set the right button as selected.
     * Based on the second (right) player's {@code isAI}-method.
     *
     * @see Player#isAI()
     */
    private void setButtonsSelected() {
        // If the second player is an AI, both players must be an AI.
        // Otherwise, only the first player is an AI.
        boolean twoAISelected = this.players.getPlayersArray()[1].isAI();

        // Mark the buttons as selected / not selected
        this.twoAI.setButtonSelected(twoAISelected);
        this.oneAI.setButtonSelected(!twoAISelected);
    }

    /**
     * The number of humans was changed; so the players are reset.
     * Then the button of the selected number is selected.
     *
     * @see GUISelectUsersSubmenu#setButtonsSelected()
     */
    public void playersChanged(int nrOfHumans) {
        // Create the player if it is human and should not be or is not human and should be
        this.players.setPlayers(nrOfHumans);

        // Set the right number of buttons as selected
        this.setButtonsSelected();
    }
}
