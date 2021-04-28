package guigame.gui.panes.buttons;

import guigame.logic.Constants;
import guigame.logic.main.StartGame;

import javax.swing.*;

/**
 * An implementation of {@code BaseButton}.
 * Specifically done for going back to the {@code MainMenu} ("Hauptmenü").
 * Dispose the parent {@code JFrame} and creates a new {@code MainGame} on click.
 * Has a font-size of 20 and a dotted border.
 *
 * @see StartGame
 */
public class BackToMainMenuButton extends BaseButton {
    /**
     * Create a new {@code BackToMainMenuButton}.
     * On click, it disposes the {@code owner} and creates a new MainGame.
     *
     * @param owner The {@code JFrame} to dispose on click
     * @see JFrame#dispose()
     * @see StartGame#createMainGame()
     * @see Constants#bgColor
     */
    public BackToMainMenuButton(JFrame owner) {
        super("Hauptmenü", 20, true, Constants.bgColor);
        this.addActionListener(e -> {
            owner.dispose();

            StartGame.createMainGame();
        });
    }
}
