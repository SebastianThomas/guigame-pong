package guigame.gui.panes;

import guigame.logic.Constants;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.util.Hashtable;

public class GUISlider extends JSlider {
    /**
     * Creates a {@code GUISlider} in {@code HORIZONTAL} orientation.
     * It will have a {@code GUISliderUI} as UI.
     *
     * @see GUISliderUI
     */
    public GUISlider(int min, int max, int value, int majorTickSpacing, String... labels) {
        super(SwingConstants.HORIZONTAL, min, max, value);

        this.setUI(new GUISliderUI(this));

        this.setMajorTickSpacing(majorTickSpacing);
        this.setPaintTicks(true);
        this.setSnapToTicks(false);

        Hashtable<Integer, JLabel> sliderTable = this.createSliderLabelTable(labels);
        this.setLabelTable(sliderTable);
        this.setPaintLabels(true);
    }

    /**
     * Creates a {@code HashTable} for all the labels.
     *
     * @param labels All labels for the Slider.
     * @return Hashtable with all tick-labels
     */
    private Hashtable<Integer, JLabel> createSliderLabelTable(String... labels) {
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);

            label.setForeground(Constants.fgColor);
            label.setBackground(Constants.bgColor);

            labelTable.put(i + 1, label);
        }

        return labelTable;
    }
}

/**
 * This class takes care of the style of a {@code GUISlider}.
 */
class GUISliderUI extends BasicSliderUI {
    private final Color thumbColor;
    private final Color trackColor;

    /**
     * Constructs a {@code GUISliderUI} for {@code JSlider} in {@code HORIZONTAL} orientation.
     *
     * @param slider The slider to affect
     */
    public GUISliderUI(JSlider slider) {
        super(slider);
        this.slider = slider;

        this.thumbColor = Constants.sliderMarkedColor;
        this.trackColor = Constants.sliderMarkedColor;

        this.slider.setForeground(Constants.fgColor);
        this.slider.setBackground(Constants.bgColor);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        this.recalculateIfInsetsChanged();
        this.recalculateIfOrientationChanged();
        Rectangle clip = g.getClipBounds();

        if (clip.intersects(this.trackRect)) {
            this.paintTrack(g);
        }
        if (clip.intersects(this.tickRect)) {
            this.paintTicks(g);
        }
        if (clip.intersects(this.labelRect)) {
            this.paintLabels(g);
        }
        if (this.slider.hasFocus() && clip.intersects(this.focusRect)) {
            this.paintFocus(g);
        }
        if (clip.intersects(thumbRect)) {
            Color savedColor = this.slider.getBackground();
            this.slider.setBackground(this.thumbColor);
            this.paintThumb(g);

            this.slider.setBackground(savedColor);
        }
    }

    /**
     * Paints the track (outline), with a round rect.
     * GUISlider must be {@code HORIZONTAL}!
     */
    @Override
    public void paintTrack(Graphics g) {
//        System.out.println(this.trackRect.x);
//        System.out.println(this.thumbRect.x);
        g.setColor(this.trackColor);
        g.fillRoundRect(this.trackRect.x, this.trackRect.y + this.trackRect.height / 2,
                this.trackRect.width - this.thumbRect.x, this.trackRect.height / 2, 8, 8);
        g.drawRoundRect(this.trackRect.x + this.thumbRect.x, this.trackRect.y + this.trackRect.height / 2,
                Math.abs(this.thumbRect.x - this.trackRect.width), this.trackRect.height / 2, 8, 8);
    }
}
