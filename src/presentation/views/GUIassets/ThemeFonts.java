package presentation.views.GUIassets;

import java.awt.Font;

/**
 * Centralized typography system for the Espotify application.
 * Provides consistent fonts across all UI components.
 *
 * @author Alan Beltrán
 * @version 2.0
 * @since 06/02/2026
 */
public final class ThemeFonts {

    private ThemeFonts() {
        // Prevent instantiation
    }

    private static final String FONT_FAMILY = "Arial";

    // Headers
    public static final Font HEADER_LARGE = new Font(FONT_FAMILY, Font.BOLD, 36);
    public static final Font HEADER_MEDIUM = new Font(FONT_FAMILY, Font.BOLD, 28);
    public static final Font HEADER_SMALL = new Font(FONT_FAMILY, Font.BOLD, 20);

    // Body Text
    public static final Font BODY_LARGE = new Font(FONT_FAMILY, Font.PLAIN, 18);
    public static final Font BODY = new Font(FONT_FAMILY, Font.PLAIN, 16);
    public static final Font BODY_SMALL = new Font(FONT_FAMILY, Font.PLAIN, 14);

    // Buttons
    public static final Font BUTTON_LARGE = new Font(FONT_FAMILY, Font.BOLD, 18);
    public static final Font BUTTON = new Font(FONT_FAMILY, Font.BOLD, 16);
    public static final Font BUTTON_SMALL = new Font(FONT_FAMILY, Font.BOLD, 14);

    // Labels
    public static final Font LABEL_LARGE = new Font(FONT_FAMILY, Font.BOLD, 24);
    public static final Font LABEL = new Font(FONT_FAMILY, Font.BOLD, 18);
    public static final Font LABEL_SMALL = new Font(FONT_FAMILY, Font.BOLD, 14);

    // Table
    public static final Font TABLE_HEADER = new Font(FONT_FAMILY, Font.BOLD, 16);
    public static final Font TABLE_CELL = new Font(FONT_FAMILY, Font.PLAIN, 15);

    // Input
    public static final Font INPUT = new Font(FONT_FAMILY, Font.PLAIN, 16);
    public static final Font INPUT_HINT = new Font(FONT_FAMILY, Font.ITALIC, 14);

    // Player
    public static final Font PLAYER_TITLE = new Font(FONT_FAMILY, Font.BOLD, 18);
    public static final Font PLAYER_ARTIST = new Font(FONT_FAMILY, Font.PLAIN, 14);
    public static final Font PLAYER_TIME = new Font(FONT_FAMILY, Font.PLAIN, 12);

    // Sidebar
    public static final Font SIDEBAR_BUTTON = new Font(FONT_FAMILY, Font.BOLD, 18);
    public static final Font SIDEBAR_USER = new Font(FONT_FAMILY, Font.PLAIN, 20);
}
