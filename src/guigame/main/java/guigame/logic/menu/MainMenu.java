package guigame.logic.menu;

import guigame.gui.menu.GUIMainMenu;
import guigame.logic.event.StartGameEvent;
import guigame.logic.event.StartGameEventListener;
import guigame.logic.players.Players;

/**
 * Implementation of {@code Menu}: Shown when the game is initialized.
 * From here, the game can be started and settings can be changed.
 */
public class MainMenu extends Menu {
    private final Players players;
    private final StartGameEvent startGameEvent;
    private GUIMainMenu guiMenu;

    /**
     * Initialize a new {@code MainMenu}.
     *
     * @param y The y-offset for both players, where they are spawned, mostly {@code height / 2}
     * @param l The {@code StartGameEventListener} to be invoked on "Start Game"
     */
    public MainMenu(int y, StartGameEventListener l) {
        // Initialize players
        this.players = new Players(y);
        this.startGameEvent = new StartGameEvent(l);

        // Create GUIMenu
        this.createGUIMenu();
    }

    /**
     * Create a new {@code GUIMainMenu}.
     *
     * @see GUIMainMenu
     */
    private void createGUIMenu() {
        this.guiMenu = new GUIMainMenu(this, this.players, this.startGameEvent);
    }

    /**
     * @return the current players
     */
    public Players getPlayers() {
        return players;
    }

    /**
     * Replace the player at the index {@code player} with either an {@code ArtificialIntelligencePlayer} or a {@code HumanPlayer}.
     *
     * @param player The index for the player to be replaced
     * @param human  Whether the generated Player should be a HumanPlayer (true) or AI-Player (false)
     */
    public void setPlayer(int player, boolean human) {
        this.players.setPlayer(player, human);
    }

    /**
     * @return the {@code GUIMainMenu}
     */
    public GUIMainMenu getGuiMenu() {
        return guiMenu;
    }

    /**
     * @return Whether one human or no humans are selected
     */
    public boolean oneHuman() {
        return !this.players.getPlayersArray()[0].isAI() || !this.players.getPlayersArray()[1].isAI();
    }
}
