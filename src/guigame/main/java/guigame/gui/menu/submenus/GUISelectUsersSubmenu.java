package guigame.gui.menu.submenus;

import guigame.gui.menu.GUIMenu;
import guigame.gui.panes.GUIPanel;
import guigame.gui.panes.GUITextField;
import guigame.gui.panes.buttons.BaseButton;
import guigame.logic.event.PlayersChangedEvent;
import guigame.logic.event.PlayersChangedEventListener;
import guigame.logic.event.PlayersNameChangedListener;
import guigame.logic.players.Player;
import guigame.logic.players.Players;

import java.awt.*;

/**
 * An implementation of {@code GUIMenu} which lets the user select whether
 * they want to play as one player against an artificial intelligence or
 * let two AIs play against each other.
 * <p>
 * IMPORTANT: To the panel, only the buttons will be added. Add the name inputs manually.
 * </p>
 *
 * @see GUISelectUsersSubmenu#getTextFieldPanel()
 */
public class GUISelectUsersSubmenu extends GUIMenu {
    /**
     * The current players
     */
    private final Players players;
    /**
     * The Listener to notify with events
     */
    private final PlayersChangedEventListener playersChangedEventListener;

    /**
     * Button to select no humans
     */
    private final BaseButton twoAI;
    /**
     * Button to select one human and one AI
     */
    private final BaseButton oneAI;

    /**
     * Text input field for the left player's name
     */
    private final GUITextField firstPlayerInput;
    /**
     * Text input field for the right player's name
     */
    private final GUITextField secondPlayerInput;

    /**
     * Panel for the buttons
     */
    private GUIPanel buttonPanel;

    /**
     * Panel for the buttons
     */
    private GUIPanel inputPanel;

    /**
     * Width of the button panel
     */
    private int buttonPanelWidth;
    /**
     * Height of the button panel
     */
    private int buttonPanelHeight;

    /**
     * Width of the button panel
     */
    private int inputPanelWidth;
    /**
     * Height of the button panel
     */
    private int inputPanelHeight;

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

        // Set text fields
        this.firstPlayerInput = new GUITextField(this.players.getPlayersArray()[0].getName(), "Name Spieler 1", 16);
        this.secondPlayerInput = new GUITextField(this.players.getPlayersArray()[1].getName(), "Name Spieler 2", 16);

        // Set buttons
        this.twoAI = new BaseButton("2 KIs gegeneinander");
        this.oneAI = new BaseButton("Sie gegen eine KI");

        // add action listeners to the button
        this.addActionListeners(this.playersChangedEventListener);

        // Calculate button panel width and height
        this.buttonPanelWidth = this.twoAI.getWidth() + this.oneAI.getWidth() + 10;
        this.buttonPanelHeight = this.twoAI.getHeight();

        // Calculate input (TextField) panel width and height
        this.inputPanelWidth = this.firstPlayerInput.getWidth() + this.secondPlayerInput.getWidth() + 10;
        this.inputPanelHeight = this.firstPlayerInput.getHeight();

        // Prepare TextFields and set them visible
        this.addTextFields();
        // Prepare buttons and set them visible
        this.addButtons();

        Dimension newSize = this.getSize();
        newSize.height = this.buttonPanelHeight + this.inputPanelHeight;
        this.setSize(newSize);
        System.out.println(this.getSize());
    }

    /**
     * Add action listeners to buttons.
     *
     * @param playersChangedEventListener The listener for {@code PlayersChangedEvent}s.
     */
    private void addActionListeners(PlayersChangedEventListener playersChangedEventListener) {
        // Add action listeners to buttons which create new PlayersChangedEvents and fire them
        this.twoAI.addActionListener(l -> new PlayersChangedEvent(playersChangedEventListener, 0).action());
        this.oneAI.addActionListener(l -> new PlayersChangedEvent(playersChangedEventListener, 1).action());
        // Add action listeners to text input fields which create new PlayersChangedEvents and fire them
        this.firstPlayerInput.getDocument().addDocumentListener(new PlayersNameChangedListener(this.playersChangedEventListener, 0));
        this.secondPlayerInput.getDocument().addDocumentListener(new PlayersNameChangedListener(this.playersChangedEventListener, 1));
    }

    /**
     * Add the text fields to panel.
     * Get this panel and add it to the main menu manually.
     *
     * @see GUISelectUsersSubmenu#getTextFieldPanel()
     * @see GUISelectUsersSubmenu#firstPlayerInput
     * @see GUISelectUsersSubmenu#secondPlayerInput
     */
    private void addTextFields() {
        // Create Panel
        this.inputPanel = new GUIPanel();
        this.inputPanel.setSize(this.inputPanelWidth, this.inputPanelHeight);
        // Add text fields to panel
        this.inputPanel.add(this.firstPlayerInput);
        this.inputPanel.add(this.secondPlayerInput);
    }

    public GUIPanel getTextFieldPanel() {
        return this.inputPanel;
    }

    /**
     * Add buttons to panel and mark the right one as selected.
     *
     * @see GUISelectUsersSubmenu#twoAI
     * @see GUISelectUsersSubmenu#oneAI
     */
    private void addButtons() {
        // Mark the right button as selected
        this.setButtonsSelected();

        // Create Panel
        this.buttonPanel = new GUIPanel();
        this.buttonPanel.setSize(this.buttonPanelWidth, this.buttonPanelHeight);
        // Add buttons to panel
        this.buttonPanel.add(this.twoAI);
        this.buttonPanel.add(this.oneAI);
        // Add button panel
        this.add(this.buttonPanel);
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
     * @param nrOfHumans the new number of humans (among the players)
     * @see GUISelectUsersSubmenu#playersChanged(int, String)
     * @see GUISelectUsersSubmenu#setButtonsSelected()
     */
    public void playersChanged(int nrOfHumans) {
        // Create the player if it is human and should not be or is not human and should be
        this.players.setPlayers(nrOfHumans);

        // Set the right number of buttons as selected
        this.setButtonsSelected();
    }

    /**
     * The name of one player is changed; this name is applied.
     *
     * @see GUISelectUsersSubmenu#playersChanged(int)
     * @see GUISelectUsersSubmenu#setButtonsSelected()
     */
    public void playersChanged(int index, String newName) {
        // Create the player if it is human and should not be or is not human and should be
        this.players.getPlayersArray()[index].setName(newName);
    }
}
