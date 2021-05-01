package guigame.logic.event;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class PlayersNameChangedListener implements DocumentListener {
    private final int playerIndex;
    private final PlayersChangedEventListener playersChangedEventListener;

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
     * Invoke when an update to the text field occured
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

