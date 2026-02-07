package presentation.views.GUIassets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Modern styled button with hover effects, rounded corners, and smooth transitions.
 * Supports both text and icon buttons.
 *
 * @author Alan Beltrán
 * @version 2.0
 * @since 06/02/2026
 */
public class StyledButton extends JButton {

    private Color normalBackground;
    private Color hoverBackground;
    private Color pressedBackground;
    private Color disabledBackground;
    private boolean isHovered = false;
    private boolean isPressed = false;

    /**
     * Creates a primary action button with Spotify green styling
     * @param text Button text
     */
    public StyledButton(String text) {
        this(text, null, ButtonType.PRIMARY);
    }

    /**
     * Creates a button with icon and text
     * @param text Button text
     * @param iconPath Path to icon image
     */
    public StyledButton(String text, String iconPath) {
        this(text, iconPath, ButtonType.PRIMARY);
    }

    /**
     * Creates a styled button with specified type
     * @param text Button text
     * @param iconPath Path to icon image (can be null)
     * @param type Button type (PRIMARY, SECONDARY, ICON)
     */
    public StyledButton(String text, String iconPath, ButtonType type) {
        super(text);
        setupButton(type, iconPath);
        setupHoverEffects();
    }

    private void setupButton(ButtonType type, String iconPath) {
        // Set colors based on type
        switch (type) {
            case PRIMARY:
                normalBackground = ThemeColors.ACCENT_PRIMARY;
                hoverBackground = ThemeColors.ACCENT_HOVER;
                pressedBackground = ThemeColors.ACCENT_PRESSED;
                disabledBackground = ThemeColors.ACCENT_DISABLED;
                setForeground(ThemeColors.TEXT_PRIMARY);
                break;
            case SECONDARY:
                normalBackground = ThemeColors.BACKGROUND_ELEVATED;
                hoverBackground = new Color(45, 45, 45);
                pressedBackground = new Color(55, 55, 55);
                disabledBackground = ThemeColors.BACKGROUND_INPUT;
                setForeground(ThemeColors.TEXT_PRIMARY);
                break;
            case ICON:
                normalBackground = null;
                hoverBackground = new Color(255, 255, 255, 30);
                pressedBackground = new Color(255, 255, 255, 50);
                disabledBackground = null;
                setForeground(ThemeColors.TEXT_PRIMARY);
                break;
        }

        // Set icon if provided
        if (iconPath != null) {
            setIcon(new ImageIcon(iconPath));
        }

        // Common styling
        setFont(ThemeFonts.BUTTON);
        setBackground(normalBackground);
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Padding
        if (type == ButtonType.ICON) {
            setBorder(new EmptyBorder(8, 8, 8, 8));
        } else {
            setBorder(new EmptyBorder(
                ThemeDimensions.BUTTON_PADDING_VERTICAL,
                ThemeDimensions.BUTTON_PADDING_HORIZONTAL,
                ThemeDimensions.BUTTON_PADDING_VERTICAL,
                ThemeDimensions.BUTTON_PADDING_HORIZONTAL
            ));
        }
    }

    private void setupHoverEffects() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                updateBackground();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                isPressed = false;
                updateBackground();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                updateBackground();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                updateBackground();
            }
        });
    }

    private void updateBackground() {
        if (!isEnabled()) {
            setBackground(disabledBackground);
        } else if (isPressed) {
            setBackground(pressedBackground);
        } else if (isHovered) {
            setBackground(hoverBackground);
        } else {
            setBackground(normalBackground);
        }
        repaint();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        updateBackground();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded background
        if (getBackground() != null) {
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 
                ThemeDimensions.BORDER_RADIUS, ThemeDimensions.BORDER_RADIUS);
        }

        g2d.dispose();
        super.paintComponent(g);
    }

    /**
     * Button type enumeration
     */
    public enum ButtonType {
        PRIMARY,    // Green action buttons
        SECONDARY,  // Gray buttons
        ICON        // Icon-only buttons
    }
}
