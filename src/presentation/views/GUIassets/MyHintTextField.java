package presentation.views.GUIassets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

/**
 * MyHintTextField Class that extes a {@link JTextField} and Implements an {@link FocusListener} interface in order to
 * be able to see a hint in the password field
 * @author Alan Beltrán
 * @version 1.0
 * @since 25/4/2022
 */
public class MyHintTextField extends JTextField implements FocusListener {

    private final String hint;
    private boolean showingHint;

    /**
     * Default Constructor for the MyHintTextField class
     * @param hint Text for the hint
     */
    public MyHintTextField(final String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(this.getText().isEmpty()) {
            setForeground(ThemeColors.TEXT_PRIMARY);
            super.setText("");
            showingHint = false;
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()) {
            setForeground(ThemeColors.TEXT_HINT);
            super.setText(hint);
            showingHint = true;
        }
    }

    @Override
    public void setText(String t) {
        if (t == null || t.isEmpty()) {
            super.setText(hint);
            showingHint = true;
            setForeground(ThemeColors.TEXT_HINT);
        } else {
            super.setText(t);
            showingHint = false;
            setForeground(ThemeColors.TEXT_PRIMARY);
        }
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }

    /**
     * Inner class that modifies the {@link MyHintTextField} in order to make it rounded
     *
     * @author Alan Beltrán
     * @version 2.0
     * @since 25/4/2022
     */
    public static class RoundedMyHintTextField extends MyHintTextField {
        private Shape shape;
        private boolean isFocused = false;

        /**
         * Default Constructor class that will match the superclass one.
         * @param hint Text for the hint
         */
        public RoundedMyHintTextField(String hint) {
            super(hint);
            this.setOpaque(false);
            setupFocusListener();
        }

        private void setupFocusListener() {
            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    isFocused = true;
                    repaint();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    isFocused = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw background
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(),
                ThemeDimensions.BORDER_RADIUS, ThemeDimensions.BORDER_RADIUS);

            g2d.dispose();
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw border with accent color when focused
            if (isFocused) {
                g2d.setColor(ThemeColors.ACCENT_PRIMARY);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2,
                    ThemeDimensions.BORDER_RADIUS, ThemeDimensions.BORDER_RADIUS);
            } else {
                g2d.setColor(ThemeColors.BORDER_COLOR);
                g2d.setStroke(new BasicStroke(1));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1,
                    ThemeDimensions.BORDER_RADIUS, ThemeDimensions.BORDER_RADIUS);
            }

            g2d.dispose();
        }

        @Override
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(),
                    ThemeDimensions.BORDER_RADIUS, ThemeDimensions.BORDER_RADIUS);
            }
            return shape.contains(x, y);
        }
    }
}