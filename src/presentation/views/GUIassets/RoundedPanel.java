package presentation.views.GUIassets;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

/**
 * Panel with rounded corners and optional shadow effect.
 * Can be used as a container for modern card-based layouts.
 *
 * @author Alan Beltrán
 * @version 2.0
 * @since 06/02/2026
 */
public class RoundedPanel extends JPanel {

    private int cornerRadius;
    private Color borderColor;
    private int borderWidth;
    private boolean drawShadow;
    private int shadowOffset;
    private int shadowBlur;

    /**
     * Creates a rounded panel with default styling
     */
    public RoundedPanel() {
        this(ThemeDimensions.BORDER_RADIUS);
    }

    /**
     * Creates a rounded panel with specified corner radius
     * @param cornerRadius Radius of the corners in pixels
     */
    public RoundedPanel(int cornerRadius) {
        this(cornerRadius, ThemeColors.BACKGROUND_CARD);
    }

    /**
     * Creates a rounded panel with specified radius and background
     * @param cornerRadius Radius of the corners in pixels
     * @param background Background color
     */
    public RoundedPanel(int cornerRadius, Color background) {
        this.cornerRadius = cornerRadius;
        this.borderColor = null;
        this.borderWidth = 0;
        this.drawShadow = false;
        this.shadowOffset = ThemeDimensions.SHADOW_OFFSET;
        this.shadowBlur = ThemeDimensions.SHADOW_BLUR;

        setBackground(background);
        setOpaque(false);
    }

    /**
     * Sets the border color and width
     * @param color Border color
     * @param width Border width in pixels
     */
    public void setBorder(Color color, int width) {
        this.borderColor = color;
        this.borderWidth = width;
        repaint();
    }

    /**
     * Enables or disables shadow effect
     * @param enabled true to enable shadow
     */
    public void setShadowEnabled(boolean enabled) {
        this.drawShadow = enabled;
        repaint();
    }

    /**
     * Sets shadow properties
     * @param offset Shadow offset
     * @param blur Shadow blur radius
     */
    public void setShadowProperties(int offset, int blur) {
        this.shadowOffset = offset;
        this.shadowBlur = blur;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Draw shadow if enabled
        if (drawShadow) {
            g2d.setColor(new Color(0, 0, 0, ThemeDimensions.SHADOW_OPACITY));
            for (int i = 0; i < shadowBlur; i++) {
                float alpha = (float) (ThemeDimensions.SHADOW_OPACITY * (1.0 - (double) i / shadowBlur));
                g2d.setColor(new Color(0, 0, 0, (int) alpha));
                g2d.fillRoundRect(
                    shadowOffset + i,
                    shadowOffset + i,
                    width - (shadowOffset + i) * 2,
                    height - (shadowOffset + i) * 2,
                    cornerRadius,
                    cornerRadius
                );
            }
        }

        // Draw background
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

        // Draw border if set
        if (borderColor != null && borderWidth > 0) {
            g2d.setColor(borderColor);
            g2d.setStroke(new BasicStroke(borderWidth));
            g2d.drawRoundRect(
                borderWidth / 2,
                borderWidth / 2,
                width - borderWidth,
                height - borderWidth,
                cornerRadius,
                cornerRadius
            );
        }

        g2d.dispose();
        super.paintComponent(g);
    }

    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        return shape.contains(x, y);
    }

    /**
     * Sets the corner radius
     * @param radius New corner radius
     */
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    /**
     * Gets the current corner radius
     * @return Corner radius in pixels
     */
    public int getCornerRadius() {
        return cornerRadius;
    }
}
