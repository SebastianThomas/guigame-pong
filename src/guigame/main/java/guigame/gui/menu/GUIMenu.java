package guigame.gui.menu;

import guigame.gui.panes.GUIPanel;

import java.awt.*;

/**
 * An implementation of {@code GUIPanel} which has a specific {@code GridBagLayout} with {@code GridBagConstraints}.
 *
 * @see GUIMenu#layout
 * @see GUIMenu#constraints
 */
public abstract class GUIMenu extends GUIPanel {
    /**
     * The menu's layout
     */
    protected GridBagLayout layout;
    /**
     * The menu's constraints
     */
    protected GridBagConstraints constraints;
}
