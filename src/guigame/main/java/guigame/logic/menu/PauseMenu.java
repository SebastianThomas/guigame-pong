package guigame.logic.menu;

/**
 * Implementation of {@code Menu} for the menu when the game is played.
 * TODO: Not used yet
 *
 * @see guigame.gui.menu.GUIPauseMenu
 * @see guigame.gui.main.GUIPauseMenuWindow
 */
public class PauseMenu extends Menu {
    /**
     * Height of the Overlay
     */
    private int width;
    private int height;

    /**
     * Initialize a Pause Menu with a fixed width and height.
     */
    public PauseMenu() {
        this.width = 200;
        this.height = 150;
    }
}
