package guigame.gui.main;

import guigame.gui.panes.GUIFrame;
import guigame.logic.Constants;
import guigame.logic.event.KeyboardPressedEvent;
import guigame.logic.event.KeyboardPressedEventListener;
import guigame.logic.main.GameBoard;
import guigame.logic.menu.GameOverMenu;
import guigame.logic.menu.MainMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends GUIFrame implements KeyListener {
    private final int[] size;
    private final KeyboardPressedEventListener keyboardPressedEventListener;

    private MainMenu mainMenu;
    private GameOverMenu gameOverMenu;

    private GameBoard gameBoard;

    public GameWindow(int width, int height, KeyboardPressedEventListener keyboardPressedEventListener) {
        super();
        this.size = new int[]{width, height};

        this.keyboardPressedEventListener = keyboardPressedEventListener;

        this.prepareWindow();
        runWindow(this);
    }

    public static void runWindow(JFrame frame) {
        System.out.println("Window is visible");
        frame.setVisible(true);
    }

    private void prepareWindow() {
        this.setTitle("Pong - Java GUI");
        this.setSize(size[0], size[1]);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.addKeyListener(this);

        this.setBackground(Constants.bgColor);
        this.setForeground(Constants.fgColor);
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        this.add(mainMenu.getGuiMenu());
    }

    public void setGameOverMenu(GameOverMenu gameOverMenu) {
        this.gameOverMenu = gameOverMenu;
        this.add(gameOverMenu.getGuiMenu());
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.setVisible(false);

        new GUIGameBoardWindow(gameBoard.getGuiBoard());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        new KeyboardPressedEvent(this.keyboardPressedEventListener, e.getKeyCode()).action();
    }

    /**
     * Closes the window and
     *
     * @param endProgram Whether to end the program or just dispose the window
     */
    public void closeWindow(boolean endProgram) {
        this.setVisible(false);
        this.dispose();
        if (endProgram)
            System.exit(0);
    }

    /**
     * Must be overridden since class implements KeyListener, using only {@code keyTyped}
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
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
