package guigame.logic.test;

import guigame.logic.main.MainGame;
import guigame.logic.main.StartGame;

import javax.swing.*;

public class Tests {
    public static void main(String[] args) throws InterruptedException {
        run(args);
    }

    static void test(String... args) {
        SwingUtilities.invokeLater(MainGame::new);
    }

    static void newTest() {

    }

    static void run(String... args) {
        StartGame.main(args);
    }
}
