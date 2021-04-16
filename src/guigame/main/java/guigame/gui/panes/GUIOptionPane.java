package guigame.gui.panes;

import guigame.gui.panes.buttons.BaseButton;
import guigame.logic.Constants;
import guigame.logic.event.ButtonEvent;
import guigame.logic.event.EventListener;

import javax.swing.*;
import java.awt.*;

public class GUIOptionPane extends GUIDialog {
    private final GridBagLayout layout;
    private final GridBagConstraints constraints;

    /**
     * Generates a new OptionPane and shows it, blocking the {@code owner}.
     *
     * @param owner    JFrame to block
     * @param l        EventListener for a selection
     * @param title    Title for the window
     * @param question Question to ask the user | just a message to give the user
     * @param buttons  Strings for the buttons (i.e. "OK", "Cancel" etc.)
     */
    public GUIOptionPane(JFrame owner, EventListener l, String title, String question, String... buttons) {
        // Init Frame
        super(owner, title, true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.setMinimumSize(new Dimension(400, 100));
        this.setLocationRelativeTo(null);

        // Set layout
        this.layout = new GridBagLayout();
        this.setLayout(this.layout);
        this.constraints = new GridBagConstraints();

        this.setBackground(Constants.bgColor);
        this.setForeground(Constants.fgColor);

        this.constraints.fill = GridBagConstraints.HORIZONTAL;

        GUILabel questionLabel = new GUILabel(question, 32);
        this.addComponent(questionLabel, 0, 0, buttons.length, 2);

        // Add button for every button in buttons
        for (int i = 0; i < buttons.length; i++) {
            this.addButton(buttons[i], l, i, 2);
        }

        // Finally, pack Frame and set its visibility to visible
        this.pack();

        System.out.println("GUIOptionPane created");

        showOptionPane(this);
    }

    /**
     * Runs the OptionPane in current thread.
     * <p>
     * Best: swing-Thread (Thread-secure) if
     * </p>
     */
    private static void showOptionPane(GUIOptionPane p) {
        System.out.println(Thread.currentThread().getName());
//        if (Thread.currentThread().getName())
        p.setVisible(true);
    }

    private void addButton(String buttonLabel, EventListener buttonListener, int x, int y) {
        BaseButton baseButton = new BaseButton(buttonLabel);
        baseButton.addActionListener(l -> buttonListener.actionPerformed(new ButtonEvent(buttonLabel)));
        this.addComponent(baseButton, x, y, 1, 1);
    }

    private void addComponent(JComponent c, int x, int y, int width, int height) {
        this.constraints.gridx = x;
        this.constraints.gridy = y;

        this.constraints.gridwidth = width;
        this.constraints.gridheight = height;

        this.add(c, this.constraints);
    }
}
