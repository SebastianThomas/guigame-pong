package guigame.logic.players;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, int y) {
        super(name, y);
    }

    @Override
    public boolean isAI() {
        return false;
    }
}
