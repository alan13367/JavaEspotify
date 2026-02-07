package presentation.views.GUIassets;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

/**
 * Modern styled slider with custom track and thumb painting.
 * Uses ThemeColors for consistent styling.
 *
 * @author Alan Beltrán
 * @version 1.0
 * @since 07/02/2026
 */
public class MySliderUI extends BasicSliderUI {

    private static final int TRACK_HEIGHT = 4;
    private static final int THUMB_SIZE = 12;

    public MySliderUI(JSlider b) {
        super(b);
    }

    @Override
    public void paintFocus(Graphics g) {
        // Do not paint focus ring
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(THUMB_SIZE, THUMB_SIZE);
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Rectangle trackBounds = trackRect;
        
        // Calculate vertical center
        int cy = (trackBounds.height / 2) - (TRACK_HEIGHT / 2);
        int trackY = trackBounds.y + cy;

        // Draw background (unfilled) track
        g2d.setColor(ThemeColors.PROGRESS_BAR_BACKGROUND);
        g2d.fillRoundRect(trackBounds.x, trackY, trackBounds.width, TRACK_HEIGHT, TRACK_HEIGHT, TRACK_HEIGHT);

        // Draw filled track
        int fillWidth;
        if (slider.getOrientation() == JSlider.HORIZONTAL) {
            int thumbPos = thumbRect.x + (thumbRect.width / 2);
            fillWidth = thumbPos - trackBounds.x;
        } else {
             // For vertical sliders (volume sometimes), we calculate from bottom
             int thumbPos = thumbRect.y + (thumbRect.height / 2);
             fillWidth = trackBounds.height - (thumbPos - trackBounds.y);
             // Vertical implementation would be different, assuming horizontal for main player bar
             // But if we want to support both, we should check orientation. 
             // However, Espotify seems to use Horizontal sliders for both progress and volume (Volume is often horizontal too).
             // Let's assume horizontal for simplicity as per common Spotify-clones, or implement basic Vertical logic if needed.
             // Checking PlayerView: volumeSlider = new JSlider(0, 100, 50); defaults to horizontal.
        }

        g2d.setColor(ThemeColors.PROGRESS_BAR_FILL);
        g2d.fillRoundRect(trackBounds.x, trackY, fillWidth, TRACK_HEIGHT, TRACK_HEIGHT, TRACK_HEIGHT);

        g2d.dispose();
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(ThemeColors.PROGRESS_BAR_FILL); // White thumb
        g2d.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);

        g2d.dispose();
    }
}
