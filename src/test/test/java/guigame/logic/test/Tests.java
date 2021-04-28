package guigame.logic.test;

import guigame.logic.main.StartGame;

/**
 * A test class to run tests from.
 */
public class Tests {
    /**
     * Invoke the test code
     */
    public static void main(String[] args) throws InterruptedException {
        // Set this to false to run other tests
        boolean run = true;

        if (run) run(args);
        else test(args);
    }

    /**
     * Use default start game method to run a new game.
     *
     * @see StartGame#main(String...)
     */
    static void run(String... args) {
        StartGame.main(args);
    }

    /**
     * Another method to run any other test from
     */
    static void test(String... args) {
    }
}
