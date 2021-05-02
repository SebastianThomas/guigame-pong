package guigame.gui.panes;

import guigame.gui.panes.buttons.BaseButton;
import guigame.logic.Constants;
import guigame.logic.event.ButtonEvent;
import guigame.logic.event.EventListener;

import javax.swing.*;
import java.awt.*;

/**
 * Implementation of {@code GUIDialog}: Option pane to ask the user a question.
 */
public class GUIOptionPane extends GUIDialog {
    /**
     * Layout to add components to.
     */
    private final GridBagLayout layout;
    /**
     * Constraints to pass for each add.
     * 
     * @see GUIOptionPane#addComponent(JComponent, int, int, int, int) 
     * */
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

        GUILabel questionLabel = new GUILabel(question, 32, false);
        this.addComponent(questionLabel, 0, 0, buttons.length, 2);

        // Add button for every button in buttons
        for (int i = 0; i < buttons.length; i++) {
            this.addButton(buttons[i], l, i, 2);
        }

        // Finally, pack Frame and set its visibility to visible
        this.pack();

        showOptionPane(this);
    }

    /**
     * Runs the OptionPane in current thread.
     * <p>
     * Best: swing-Thread (Thread-secure {@code paint})!
     * </p>
     *
     * @param p The {@code GUIOptionPane} to set visible
     */
    private static void showOptionPane(GUIOptionPane p) {
        p.setVisible(true);
    }

    /**
     * Add a new button.
     *
     * @param buttonLabel    label (msg, text) one the button
     * @param buttonListener listener to add to the new button
     * @param x              The x-position ({@code GridBagConstraints constraints}) on {@code layout}
     * @param y              The y-position ({@code GridBagConstraints constraints}) on {@code layout}
     * @see GUIOptionPane#layout
     * @see GUIOptionPane#constraints
     */
    private void addButton(String buttonLabel, EventListener buttonListener, int x, int y) {
        BaseButton baseButton = new BaseButton(buttonLabel);
        baseButton.addActionListener(l -> buttonListener.actionPerformed(new ButtonEvent(buttonLabel)));
        this.addComponent(baseButton, x, y, 1, 1);
    }

    /**
     * Add a component at a specific (x,y)-position.
     *
     * @param c      component to add
     * @param x      x-position for the new component
     * @param y      y-position for the new component
     * @param width  width of the component
     * @param height height of the component
     */
    private void addComponent(JComponent c, int x, int y, int width, int height) {
        // Set x and y position
        this.constraints.gridx = x;
        this.constraints.gridy = y;

        // Set width and height
        this.constraints.gridwidth = width;
        this.constraints.gridheight = height;

        // Add component with specified constraints
        this.add(c, this.constraints);
    }
}
