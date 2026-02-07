package presentation.views.GUIassets;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * Modern styled scrollbar with rounded thumb and hover effects.
 * Extends the default {@link BasicScrollBarUI} implementation.
 *
 * @author Alan Beltrán
 * @version 2.0
 * @since 25/4/2022
 */
public class MyScrollBarUI extends BasicScrollBarUI {

    private boolean isThumbHovered = false;

    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = ThemeColors.SCROLLBAR_THUMB;
        this.scrollbar.setBackground(ThemeColors.SCROLLBAR_TRACK);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton button = new BasicArrowButton(orientation,
                ThemeColors.SCROLLBAR_TRACK,
                ThemeColors.SCROLLBAR_TRACK,
                ThemeColors.TEXT_SECONDARY,
                ThemeColors.SCROLLBAR_TRACK);
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton button = new BasicArrowButton(orientation,
                ThemeColors.SCROLLBAR_TRACK,
                ThemeColors.SCROLLBAR_TRACK,
                ThemeColors.TEXT_SECONDARY,
                ThemeColors.SCROLLBAR_TRACK);
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }

    @Override
    protected void installListeners() {
        super.installListeners();

        scrollbar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (getThumbBounds().contains(e.getPoint())) {
                    isThumbHovered = true;
                    scrollbar.repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isThumbHovered = false;
                scrollbar.repaint();
            }
        });

        scrollbar.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                boolean wasHovered = isThumbHovered;
                isThumbHovered = getThumbBounds().contains(e.getPoint());
                if (wasHovered != isThumbHovered) {
                    scrollbar.repaint();
                }
            }
        });
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Choose color based on hover state
        Color thumbColor = isThumbHovered ? ThemeColors.SCROLLBAR_THUMB_HOVER : ThemeColors.SCROLLBAR_THUMB;
        g2d.setColor(thumbColor);

        // Draw rounded rectangle for thumb
        int arc = 8;
        g2d.fillRoundRect(thumbBounds.x + 2, thumbBounds.y + 2,
                thumbBounds.width - 4, thumbBounds.height - 4, arc, arc);

        g2d.dispose();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(ThemeColors.SCROLLBAR_TRACK);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(12, 40);
    }
}
