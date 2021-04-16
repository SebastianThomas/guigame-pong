package guigame.gui.borders;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class Borders {
    /**
     * Creates a basic dashed border.
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
     * Creates a basic empty border.
     */
    public static Border createEmptyBorder() {
        return BorderFactory.createEmptyBorder(10, 20, 10, 20);
    }
}
