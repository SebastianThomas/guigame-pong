package guigame.gui.main;

import guigame.gui.panes.GUIFrame;
import guigame.logic.Constants;
import guigame.logic.event.KeyboardPressedEvent;
import guigame.logic.event.KeyboardPressedEventListener;
import guigame.logic.main.GameBoard;
import guigame.logic.menu.MainMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Frame to show the {@code GUIMainMenu}.
 *
 * @see guigame.gui.menu.GUIMainMenu
 */
public class GameWindow extends GUIFrame implements KeyListener {
    private final int[] size;
    private final KeyboardPressedEventListener keyboardPressedEventListener;

    /**
     * Creates a {@code GameWindow}. It shows a {@code MainMenu} and starts the game by a click on the {@code MainMenu}'s {@code StartGameBaseButton}.
     *
     * @see GameWindow#setMainMenu(MainMenu)
     * @see GameWindow#setGameBoard(GameBoard)
     * @see guigame.gui.panes.buttons.StartGameBaseButton
     * @see guigame.logic.event.StartGameEventListener
     */
    public GameWindow(int width, int height, KeyboardPressedEventListener keyboardPressedEventListener) {
        super("Pont - Java GUI");
        this.size = new int[]{width, height};

        this.keyboardPressedEventListener = keyboardPressedEventListener;

        this.prepareWindow();
        runWindow(this);
    }

    /**
     * Sets frame to visible.
     *
     * @param frame The frame to show
     */
    public static void runWindow(JFrame frame) {
        frame.setVisible(true);
    }

    /**
     * Initializes the window.
     */
    private void prepareWindow() {
        // Set title
        this.setTitle("Pong - Java GUI");
        // Set size
        this.setSize(size[0], size[1]);
        // Center (of the screen)
        this.setLocationRelativeTo(null);

        // Default close operation
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add this as key listener
        this.addKeyListener(this);

        // Set colors
        this.setBackground(Constants.bgColor);
        this.setForeground(Constants.fgColor);
    }

    /**
     * Shows the GUI of the
     *
     * @param mainMenu {@code MainMenu} to show
     */
    public void setMainMenu(MainMenu mainMenu) {
        this.add(mainMenu.getGuiMenu());
    }

    /**
     * Sets the {@code GameBoard} and creates a {@code GUIGameBoardWindow} which is shown.
     *
     * @code gameBoard The {@code GameBoard} to show.
     */
    public void setGameBoard(GameBoard gameBoard) {
        // Hide
        this.setVisible(false);

        // Create a new GameBoardWindow
        new GUIGameBoardWindow(gameBoard.getGuiBoard());

        // Destroy this
        SwingUtilities.invokeLater(this::dispose);
    }

    /**
     * Invoke {@code KeyboardPressedEvent}
     *
     * @see KeyboardPressedEvent
     * @see KeyboardPressedEventListener
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        // Invoke KeyboardPressedEvent
        new KeyboardPressedEvent(this.keyboardPressedEventListener, e.getKeyCode()).action();
    }

    /**
     * EMPTY!!!
     * Must be overridden since class implements KeyListener, using only {@code keyTyped}
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * EMPTY!!!
     * Must be overridden since class implements KeyListener, using only {@code keyTyped}
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Removes all children and then repaints and revalidates the Window.
     */
    @Override
    public void removeAll() {
        super.removeAll();

        if (!this.isValid()) {
            this.revalidate();
            this.repaint();
        }
    }
}
