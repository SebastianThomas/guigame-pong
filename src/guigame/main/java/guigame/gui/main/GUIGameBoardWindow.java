package guigame.gui.main;

import guigame.gui.panes.buttons.BaseButton;
import guigame.logic.Constants;
import guigame.logic.main.GameState;
import guigame.logic.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Window for the {@code GUIGameBoard}.
 * Implements {@code MouseMotionListener} to move the paddles.
 *
 * @see GUIGameBoard
 */
public class GUIGameBoardWindow extends JWindow implements MouseMotionListener {
    /**
     * The {@code GUIGameBoard} to show on this window.
     */
    GUIGameBoard guiGameBoard;
    /**
     * Whether the game board is shown or not.
     */
    private boolean guiBoardShown;

    /**
     * Button to start the game with.
     */
    private BaseButton startGameButton;

    /**
     * Label to show when the point is over.
     * TODO: Not showing up yet.
     */
    private JLabel pointWinningLabel;
    /**
     * Whether the label is currently in use or not.
     */
    private boolean useLabel = false;

    /**
     * Create a new {@code GUIGameBoardWindow},
     * initialize it and
     * set it visible.
     *
     * @param guiGameBoard the {@code GUIGameBoard to show}
     * @see GUIGameBoard
     */
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
        int delay = wait ? Constants.DELAY_END_OF_POINT : 0;

        // If a point has already taken place and the label is not shown yet, show the nice msg
        if (wait && !this.useLabel) {
            this.showPointWinningLabel(right);
        }

        // Sleep 5 seconds, then remove game board GUI as well as the label and show start new Point button
        Timer timer = new Timer(delay, l -> {
            this.removePointWinningLabel();
            if (this.guiBoardShown) {
                SwingUtilities.invokeLater(() -> {
                    // Remove game board from window
                    this.remove(this.guiGameBoard);
                    this.guiBoardShown = false;
                    this.revalidate();
                    this.repaint();
                });
            }
            // Then show the start game button
            this.createStartNextPointButton(right);
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

    /**
     * Start the game since the StartGame button was triggered.
     *
     * @param right whether the first point should start with the right player or not (so left player)
     */
    private void startGameButtonActionListener(boolean right) {
        this.add(this.guiGameBoard);
        this.guiBoardShown = true;

        this.guiGameBoard.startPoint(right, this.startGameButton);
    }

    /**
     * Create a label displaying a nice message to the person who won the last point and show it.
     *
     * @param right whether the right player has won the last point or not
     * @see GUIGameBoardWindow#removePointWinningLabel()
     */
    public void showPointWinningLabel(boolean right) {
        // -------------------- Logic for the label --------------------
        // Get players
        Player[] players = this.guiGameBoard.getPlayers().getPlayersArray();
        Player winningPlayer = players[right ? 1 : 0];
        // Get text for label
        String pointWinningText = winningPlayer.getPointWinningTextAgainst(players[right ? 0 : 1]);
        // --------------------                     --------------------

        // -------------------- GUI for the label --------------------
        // Initialize label, set its foreground color as specified in Constants.fgColor and font size to 20
        this.pointWinningLabel = new JLabel(pointWinningText, SwingConstants.CENTER);
        this.pointWinningLabel.setFont(new Font(this.pointWinningLabel.getFont().getName(), Font.PLAIN, 20));
        this.pointWinningLabel.setForeground(Constants.fgColor);

        // Label will have an offset of 50 from the top
        int yOffsetLabel = 50;
        this.pointWinningLabel.setBounds(0, yOffsetLabel, this.getWidth(), 20);
        // --------------------                   --------------------

        this.useLabel = true;
        // Show the label
        SwingUtilities.invokeLater(() -> {
            // TODO: Not working
            this.add(this.pointWinningLabel);
        });
    }

    /**
     * Remove label from window if it is shown.
     *
     * @see GUIGameBoardWindow#showPointWinningLabel(boolean)
     */
    private void removePointWinningLabel() {
        if (this.useLabel) {
            SwingUtilities.invokeLater(() -> {
                this.remove(this.pointWinningLabel);
                System.out.println("Removed");
            });
            this.useLabel = false;
        }
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
     * <p>
     * Updates the paddle if the game is running and the right player is marked as human.
     * </p>
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        // Move the right paddle if:
        // - game is running AND
        // - the right player is marked as human
        if (this.guiGameBoard.getState() == GameState.RUNNING && this.guiGameBoard.rightIsHuman()) {
            this.guiGameBoard.updateRightPaddle(e.getY());
        }
    }
}
