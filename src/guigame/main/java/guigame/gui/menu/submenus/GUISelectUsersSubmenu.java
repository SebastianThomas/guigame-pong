package guigame.gui.menu.submenus;

import guigame.gui.menu.GUIMenu;
import guigame.gui.panes.buttons.BaseButton;
import guigame.logic.event.PlayersChangedEvent;
import guigame.logic.event.PlayersChangedEventListener;
import guigame.logic.players.Players;

public class GUISelectUsersSubmenu extends GUIMenu {
    private final Players players;
    private final PlayersChangedEventListener playersChangedEventListener;

    private final BaseButton twoAI;
    private final BaseButton oneAI;

    public GUISelectUsersSubmenu(Players players) {
        super();
        this.players = players;

        this.playersChangedEventListener = new PlayersChangedEventListener(this);

        this.twoAI = new BaseButton("2 KIs gegeneinander");
        this.oneAI = new BaseButton("Sie gegen eine KI");

        this.addActionListeners(this.playersChangedEventListener);

        this.addButtons();
    }

    /**
     * Add action listeners to buttons.
     *
     * @param playersChangedEventListener The listener for {@code PlayersChangedEvent}s.
     */
    private void addActionListeners(PlayersChangedEventListener playersChangedEventListener) {
        this.twoAI.addActionListener(l -> new PlayersChangedEvent(playersChangedEventListener, 0).action());
        this.oneAI.addActionListener(l -> new PlayersChangedEvent(playersChangedEventListener, 1).action());
    }

    /**
     * Add buttons to panel and mark the right one as selected.
     */
    private void addButtons() {
        this.setButtonsSelected();

        this.add(this.twoAI);
        this.add(this.oneAI);
    }

    private void setButtonsSelected() {
        // If the second player is an AI, both players must be an AI.
        // Otherwise, only the first player is an AI.
        boolean twoAISelected = this.players.getPlayers()[1].isAI();

        this.twoAI.setButtonSelected(twoAISelected);
        this.oneAI.setButtonSelected(!twoAISelected);
    }

    public void playersChanged(int nrOfHumans) {
        System.out.println("Nr of Humans: " + nrOfHumans);

        this.players.setPlayers(nrOfHumans);

        this.setButtonsSelected();
    }
}
