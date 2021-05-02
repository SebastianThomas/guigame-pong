package guigame.gui.panes;

import guigame.logic.Constants;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.util.Hashtable;

/**
 * Implementation of {@code JSlider} with the game's default colors and a ball as slider-thumb (knob).
 */
public class GUISlider extends JSlider {
    /**
     * Creates a {@code GUISlider} in {@code HORIZONTAL} orientation.
     * It will have a {@code GUISliderUI} as UI.
     *
     * @param min              Minimum value for the slider
     * @param max              Maximum value for the slider
     * @param value            Initial value
     * @param majorTickSpacing Spacing between major ticks
     * @param labels           All labels for the slider. Should have the same length as (max - min)
     * @see GUISliderUI
     * @see GUISlider#HORIZONTAL
     * @see JSlider#majorTickSpacing
     */
    public GUISlider(int min, int max, int value, int majorTickSpacing, String... labels) {
        // Init a slider
        super(SwingConstants.HORIZONTAL, min, max, value);

        // Set custom UI
        this.setUI(new GUISliderUI(this));

        // Set ticks
        this.setMajorTickSpacing(majorTickSpacing);
        this.setPaintTicks(true);
        this.setSnapToTicks(false);

        // Set labels and paint them
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
        // Create new Table for labels
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();

        // Fill Hashtable
        for (int i = 0; i < labels.length; i++) {
            // Init label
            JLabel label = new JLabel(labels[i]);

            // Set colors
            label.setForeground(Constants.fgColor);
            label.setBackground(Constants.bgColor);

            // Put label into Hashtable
            labelTable.put(i + 1, label);
        }

        // Return filled Hashtable
        return labelTable;
    }
}

/**
 * Implementation of {@code BasicSliderUI}: style of a {@code GUISlider}.
 */
class GUISliderUI extends BasicSliderUI {
    /**
     * Color of the thumb
     */
    private final Color thumbColor;
    /**
     * Color of the track
     */
    private final Color trackColor;

    /**
     * The arc for the track.
     */
    private final int trackArc = 8;

    /**
     * Constructs a {@code GUISliderUI} for {@code JSlider} in {@code HORIZONTAL} orientation.
     *
     * @param slider The slider to affect
     */
    public GUISliderUI(JSlider slider) {
        // Init slider UI
        super(slider);
        this.slider = slider;

        // Set colors
        this.thumbColor = Constants.fgColor;
        this.trackColor = Constants.sliderMarkedColor;

        // Set slider colors
        this.slider.setForeground(Constants.fgColor);
        this.slider.setBackground(Constants.bgColor);
    }

    /**
     * Paint the UI.
     */
    @Override
    public void paint(Graphics g, JComponent c) {
        // Repaint and recalculate insets
        super.paint(g, c);
        this.recalculateIfInsetsChanged();
        this.recalculateIfOrientationChanged();
        Rectangle clip = g.getClipBounds();

        // Paint
        if (clip.intersects(this.trackRect)) {
            // Paint custom track
            this.paintTrack(g);
        }
        if (clip.intersects(this.tickRect)) {
            // Paint ticks
            this.paintTicks(g);
        }
        if (clip.intersects(this.labelRect)) {
            // Paint labels
            this.paintLabels(g);
        }
        if (this.slider.hasFocus() && clip.intersects(this.focusRect)) {
            this.paintFocus(g);
        }
        if (clip.intersects(thumbRect)) {
            // Draw thumb
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
        // Calculate track widths (heights are the same)
        int emptyTrackWidth = (this.trackRect.width + this.trackRect.x) - this.thumbRect.x;
        int filledTrackWidth = this.trackRect.width - emptyTrackWidth;

        // Set color
        g.setColor(this.trackColor);
        // Fill track: filled (left of the thumb) and unfilled (right of the thumb)
        g.fillRoundRect(this.trackRect.x, this.trackRect.y + this.trackRect.height / 2,
                filledTrackWidth, this.trackRect.height / 2, this.trackArc, this.trackArc);
        g.drawRoundRect(this.thumbRect.x, this.trackRect.y + this.trackRect.height / 2,
                emptyTrackWidth, this.trackRect.height / 2, this.trackArc, this.trackArc);
    }

    /**
     * Paints the thumb (ball).
     *
     * @param g The graphics to paint on
     */
    @Override
    public void paintThumb(Graphics g) {
        // Calculate width and height
        int thumbWidth = this.trackArc * 2;
        int thumbHeight = this.trackArc * 2;

        // Set the right color
        g.setColor(this.thumbColor);
        // Paint the thumb
        g.fillOval(this.thumbRect.x - thumbWidth / 2, this.thumbRect.y + (thumbHeight / 2 - 1), thumbWidth, thumbHeight);
    }
}
