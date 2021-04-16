package guigame.logic.event.askers;

import guigame.gui.panes.GUIOptionPane;
import guigame.logic.event.ButtonEvent;
import guigame.logic.event.Event;
import guigame.logic.event.EventListener;

import javax.swing.*;

public class LeaveGameAsker implements EventListener {
    private final GUIOptionPane p;

    /**
     * Creates a {@code OptionPane} to verify whether the user wants to leave the game or not
     */
    public LeaveGameAsker(JFrame owner) {
        this.p = new GUIOptionPane(owner, this, "Verlassen", "Möchten Sie das Spiel wirklich verlassen?", "Ja", "Nein");
    }

    @Override
    public void actionPerformed(Event e) {
        ButtonEvent event = (ButtonEvent) e;
        if (event.getButton().equals("Ja")) System.exit(0);
        this.p.dispose();
    }
}
