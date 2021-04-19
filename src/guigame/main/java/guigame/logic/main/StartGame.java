package guigame.logic.main;

import guigame.logic.Constants;

import javax.swing.*;

public class StartGame {
    public static void main(String... args) {
        System.out.println("Playing game: Pong");

        createMainGame();
    }

    public static void createMainGame() {
        SwingUtilities.invokeLater(() -> new MainGame(Constants.GAME_BOARD_HEIGHT, Constants.GAME_BOARD_WIDTH));
    }
}
