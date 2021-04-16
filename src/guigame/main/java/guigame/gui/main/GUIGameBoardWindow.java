package guigame.gui.main;

import guigame.gui.panes.buttons.BaseButton;
import guigame.logic.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUIGameBoardWindow extends JWindow implements MouseListener, MouseMotionListener {
    GUIGameBoard guiGameBoard;
    private boolean guiBoardShown;

    private BaseButton startGameButton;

    public GUIGameBoardWindow(GUIGameBoard guiGameBoard) {
        // Init basic JWindow
        super();

        // Set variables
        this.guiGameBoard = guiGameBoard;
        this.guiBoardShown = false;
        this.guiGameBoard.setParentWindow(this);

        // Initialize window
        this.init();

        // Add Mouse Motion Listener
        this.addMouseMotionListener(this);

        // Set visible
        this.setVisible(true);
    }

    /**
     * Initializes the frame and calls {@code initNextPoint}.
     */
    private void init() {
        this.setSize(this.guiGameBoard.getWidth(), this.guiGameBoard.getHeight());
        this.setLocationRelativeTo(null);
        this.initNextPoint(true, false);
    }

    /**
     * Potentially wait 5 seconds.
     * Hide the game board if it is currently shown and shows the button to start new point.
     *
     * @param right Whether the next point should start with the right player or not.
     * @param wait  Whether to wait before the execution (when a point is over and the current board should stay displayed for 5 more seconds without moving anything
     */
    public void initNextPoint(boolean right, boolean wait) {
        int delay = wait ? 5000 : 0;
        // Sleep 5 seconds, then remove game board GUI and show start new Point button
        Timer timer = new Timer(delay, l -> {
            SwingUtilities.invokeLater(() -> {
                        if (this.guiBoardShown) {
                            // Remove game board from window
                            this.remove(this.guiGameBoard);
                            this.guiBoardShown = false;
                            this.revalidate();
                            this.repaint();
                        }
                        // Then show the start game button
                        this.createStartNextPointButton(right);
                    }
            );
        });

        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Creates a button (empty background) which starts the ball (for the next point).
     * <p>
     * On click, the game board is mounted.
     * </p>
     *
     * @param right Whether the x-speed should be positive (the ball goes to the right) or negative (object goes to the left).
     */
    private void createStartNextPointButton(boolean right) {
        Color bgColor = new Color(Constants.bgColor.getRed(), Constants.bgColor.getGreen(), Constants.bgColor.getBlue(), (float) 0.4);

        this.startGameButton = new BaseButton("Klicken, um zu starten", 40, false, bgColor);
        this.startGameButton.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(this.startGameButton);
        this.revalidate();
        this.repaint();

        this.startGameButton.addActionListener(e -> this.startGameButtonActionListener(right));
    }

    private void startGameButtonActionListener(boolean right) {
        this.add(this.guiGameBoard);
        this.guiBoardShown = true;

        this.guiGameBoard.startPoint(right, this.startGameButton);
    }

    /**
     * Add a point to the GUI for the player who has won the latest point
     *
     * @param winningPointPlayer Player which has won the last point
     * @return The new score of both players in an array (to check for a winner??)
     * @throws IllegalArgumentException if the index for the player is out of bounds (0, 1)
     */
    public int[] updatePointLabels(int winningPointPlayer) throws IllegalArgumentException {
        return this.guiGameBoard.updatePointLabels(winningPointPlayer);
    }

    /**
     * EMPTY!!!
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        this.guiGameBoard.updateRightPaddle(e.getY());
    }

    /**
     * EMPTY!!!
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked");
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed in GUIGameBoardWindow, could also invoke start next point from here");
    }

    /**
     * EMPTY!!!
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released");
    }

    /**
     * EMPTY!!!
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Entered");
    }

    /**
     * EMPTY!!!
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Exited");
    }
}
