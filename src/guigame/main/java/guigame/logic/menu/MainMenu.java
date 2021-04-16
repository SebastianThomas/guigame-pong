package guigame.logic.menu;

import guigame.gui.menu.GUIMainMenu;
import guigame.logic.event.StartGameEvent;
import guigame.logic.event.StartGameEventListener;
import guigame.logic.players.Players;

public class MainMenu extends Menu {
    private final Players players;
    private final StartGameEvent startGameEvent;
    private GUIMainMenu guiMenu;

    /**
     * @param y The y-offset for both players, where they are spawned, mostly {@code height / 2}
     */
    public MainMenu(int y, StartGameEventListener l) {
        this.players = new Players(y);
        this.startGameEvent = new StartGameEvent(l);

        this.createGUIMenu();
    }

    private void createGUIMenu() {
        this.guiMenu = new GUIMainMenu(this, this.players, this.startGameEvent);
    }

    public Players getPlayers() {
        return players;
    }

    /**
     * @param player The index for the player to be replaced
     * @param human  Whether the generated Player should be a HumanPlayer (true) or AI-Player (false)
     */
    public void setPlayer(int player, boolean human) {
        this.players.setPlayer(player, human);
    }

    public GUIMainMenu getGuiMenu() {
        return guiMenu;
    }

    /**
     * @return Whether one human or no humans are selected
     */
    public boolean oneHuman() {
        return this.players.getPlayers()[0].isAI() || this.players.getPlayers()[1].isAI();
    }
}
