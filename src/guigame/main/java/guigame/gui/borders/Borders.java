package guigame.gui.borders;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Utility class to generate borders.
 *
 * @see Border
 */
public abstract class Borders {
    /**
     * Creates a basic dashed border.
     *
     * @return A new dashed border
     */
    public static Border createDashedBorder() {
        return BorderFactory.createStrokeBorder(
                new BasicStroke(1.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f,
                        new float[]{10.0f},
                        0.0f));
    }

    /**
     * Creates a basic empty border with
     * a top and bottom of 10 and
     * a left and right of 20.
     *
     * @return A new empty border
     */
    public static Border createEmptyBorder() {
        return BorderFactory.createEmptyBorder(10, 20, 10, 20);
    }
}
