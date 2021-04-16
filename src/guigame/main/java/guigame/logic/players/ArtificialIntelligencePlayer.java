package guigame.logic.players;

public class ArtificialIntelligencePlayer extends Player {
    public ArtificialIntelligencePlayer(int index, int y) {
        super(String.format("KI %d", index + 1), y);
    }

    public ArtificialIntelligencePlayer(String name, int y) {
        super(name, y);
    }

    // TODO: Implement go step by step
    private static int stepByStep(int step, int before) {
        return step + before;
    }

    public void goToHeight(int y, int speed) {
        if (this.y < y) {
            this.goToHeightUp(y, speed);
            return;
        }
        if (this.y > y) {
            this.goToHeightDown(y, speed);
        }
    }

    private void goToHeightDown(int y, int speed) {
        while (this.y > y) {
            y = stepByStep(y, -speed);
        }
    }

    private void goToHeightUp(int y, int speed) {
        while (this.y < y) {
            y = stepByStep(y, speed);
        }
    }

    @Override
    public boolean isAI() {
        return true;
    }
}
