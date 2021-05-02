package guigame.logic.event.askers;

import guigame.gui.panes.GUIOptionPane;
import guigame.logic.event.ButtonEvent;
import guigame.logic.event.Event;
import guigame.logic.event.EventListener;

import javax.swing.*;

/**
 * Asks the user with a new {@code GUIOptionPane} whether to leave the game or not.
 */
public class LeaveGameAsker implements EventListener {
    /**
     * The label for "yes"-button
     */
    private static final String yesLeaveLabelString = "Ja";
    /**
     * The option pane the asker creates and displays.
     */
    private final GUIOptionPane p;

    /**
     * Creates a {@code OptionPane} to verify whether the user wants to leave the game or not
     *
     * @param owner The owner to block from further inputs before the question has been answered
     */
    public LeaveGameAsker(JFrame owner) {
        this.p = new GUIOptionPane(owner, this, "Verlassen", "Möchten Sie das Spiel wirklich verlassen?", yesLeaveLabelString, "Nein");
    }

    /**
     * Invoked when the button is pressed.
     * Quits the game if the button {@code yesLeaveLabelString} has been pressed.
     *
     * @param e the {@code Event} (casted to {@code ButtonEvent}) containing the information which button has been pressed
     * @see guigame.logic.event.askers.LeaveGameAsker#yesLeaveLabelString
     */
    @Override
    public void actionPerformed(Event e) {
        // Cast Event to ButtonEvent to get its full functionality
        ButtonEvent event = (ButtonEvent) e;

        // If this is true, leave the game
        if (event.getButton().equals(yesLeaveLabelString)) System.exit(0);
        this.p.dispose();
    }
}
