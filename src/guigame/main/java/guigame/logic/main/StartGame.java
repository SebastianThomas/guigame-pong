package guigame.logic.main;

import javax.swing.*;

public class StartGame {
    public static int speed = 1;
    public static int height = 500;
    public static int width = 500;

    public static void main(String... args) {
        System.out.println("Playing game: Pong");

        createMainGame();
    }

    public static void createMainGame() {
        SwingUtilities.invokeLater(() -> new MainGame(height, width));
    }
}
