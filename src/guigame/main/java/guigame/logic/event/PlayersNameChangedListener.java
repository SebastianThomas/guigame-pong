package guigame.logic.event;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 * Listener to fire when a player name change occurs.
 * Should be added in the {@code GUIMainMenu} for the name inputs.
 *
 * @see guigame.gui.menu.GUIMainMenu
 */
public class PlayersNameChangedListener implements DocumentListener {
    /**
     * index for the player whose name has changed (0,1)
     */
    private final int playerIndex;
    /**
     * Listener to fire when a name-change occurs
     */
    private final PlayersChangedEventListener playersChangedEventListener;

    /**
     * Create a new Listener.
     *
     * @param playersChangedEventListener Listener to fire when a name-change occurs
     * @param playerIndex                 the index for the player whose name has changed (0,1)
     */
    public PlayersNameChangedListener(PlayersChangedEventListener playersChangedEventListener, int playerIndex) {
        this.playerIndex = playerIndex;
        this.playersChangedEventListener = playersChangedEventListener;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        this.changed(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        this.changed(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        this.changed(e);
    }

    /**
     * Invoke when an update to the text field occurred.
     * Gets the current text in the text field and fire a new {@code PlayersChangedEvent}
     *
     * @param e A DocumentEvent that is triggered when a change occurs
     * @see PlayersNameChangedListener#playersChangedEventListener
     * @see PlayersChangedEventListener#actionPerformed(Event)
     */
    public void changed(DocumentEvent e) {
        try {
            String text = e.getDocument().getText(0, e.getDocument().getLength());
            new PlayersChangedEvent(
                    this.playersChangedEventListener,
                    this.playerIndex,
                    text
            ).action();
        } catch (BadLocationException ex) {
            // Do nothing, this can't happen
        }
    }
}

