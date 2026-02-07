package presentation.views.GUIassets;

import java.awt.Color;

/**
 * Centralized color palette for the Espotify application.
 * Provides consistent colors across all UI components.
 *
 * @author Alan Beltrán
 * @version 2.0
 * @since 06/02/2026
 */
public final class ThemeColors {

    private ThemeColors() {
        // Prevent instantiation
    }

    // Background Colors
    public static final Color BACKGROUND_PRIMARY = new Color(18, 18, 18);
    public static final Color BACKGROUND_SECONDARY = new Color(24, 24, 24);
    public static final Color BACKGROUND_INPUT = new Color(40, 40, 40);
    public static final Color BACKGROUND_ELEVATED = new Color(30, 30, 30);
    public static final Color BACKGROUND_CARD = new Color(28, 28, 28);

    // Accent Colors (Spotify Green)
    public static final Color ACCENT_PRIMARY = new Color(0, 204, 0);
    public static final Color ACCENT_HOVER = new Color(26, 214, 26);
    public static final Color ACCENT_PRESSED = new Color(0, 184, 0);
    public static final Color ACCENT_DISABLED = new Color(0, 102, 0);

    // Text Colors
    public static final Color TEXT_PRIMARY = Color.WHITE;
    public static final Color TEXT_SECONDARY = new Color(179, 179, 179);
    public static final Color TEXT_DISABLED = new Color(120, 120, 120);
    public static final Color TEXT_HINT = new Color(128, 128, 128);

    // Border Colors
    public static final Color BORDER_COLOR = new Color(40, 40, 40);
    public static final Color BORDER_FOCUS = ACCENT_PRIMARY;
    public static final Color BORDER_HOVER = new Color(60, 60, 60);

    // Table Colors
    public static final Color TABLE_BACKGROUND = BACKGROUND_SECONDARY;
    public static final Color TABLE_HEADER_BACKGROUND = new Color(32, 32, 32);
    public static final Color TABLE_ROW_HOVER = new Color(35, 35, 35);
    public static final Color TABLE_ROW_ALTERNATE = new Color(22, 22, 22);
    public static final Color TABLE_GRID = new Color(50, 50, 50);

    // Scrollbar Colors
    public static final Color SCROLLBAR_THUMB = new Color(100, 100, 100);
    public static final Color SCROLLBAR_THUMB_HOVER = new Color(130, 130, 130);
    public static final Color SCROLLBAR_TRACK = BACKGROUND_PRIMARY;

    // Player Colors
    public static final Color PLAYER_BACKGROUND = new Color(24, 24, 24);
    public static final Color PROGRESS_BAR_BACKGROUND = new Color(64, 64, 64);
    public static final Color PROGRESS_BAR_FILL = ACCENT_PRIMARY;

    // Status Colors
    public static final Color ERROR = new Color(255, 80, 80);
    public static final Color SUCCESS = ACCENT_PRIMARY;
    public static final Color WARNING = new Color(255, 200, 0);
}
