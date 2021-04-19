package guigame.logic.test;

import guigame.logic.main.StartGame;

public class Tests {
    public static void main(String[] args) throws InterruptedException {
        run(args);
    }

    static void run(String... args) {
        StartGame.main(args);
    }

    static void test(String... args) {
        // Constructor for tests is private
        // SwingUtilities.invokeLater(MainGame::new);
    }

    static void newTest() {
    }
}
