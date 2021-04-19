package guigame.gui.objects;

import guigame.logic.Constants;
import guigame.logic.Position;
import guigame.logic.objects.Ball;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class GUIBall extends JComponent {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Ball ball;
    private final int height;
    private final int width;

    private final Position position;

    public GUIBall(Ball ball) {
        super();

        this.ball = ball;

        this.height = ball.getHeight();
        this.width = ball.getWidth();

        this.position = ball.getPosition();

        this.setLocation(Math.round(this.position.getCoordinates().x), Math.round(this.position.getCoordinates().y));
        this.setSize(this.width, this.height);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Constants.fgColor);

        g.fillRoundRect(0, 0, this.width, this.height, 30, 30);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(this.width, this.height);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(this.width, this.height);
    }

    /**
     * @return the GUIBall's width.
     * @see javax.swing.JComponent#getPreferredSize
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
}
