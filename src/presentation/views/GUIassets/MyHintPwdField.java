package presentation.views.GUIassets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

/**
 * MyHintPwdField Class that extes a {@link JPasswordField} and Implements an {@link FocusListener} interface in order to
 * be able to see a hint in the password field
 * @author Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 28/4/2022
 */
public class MyHintPwdField extends JPasswordField implements FocusListener {
    private final String hint;
    private boolean showingHint;

    /**
     * Default Constructor for the MyHintPwdField class
     * @param hint Text for the hint
     */
    public MyHintPwdField(final String hint) {
        super(hint);
        super.setEchoChar((char) 0);
        this.hint = hint;
        this.showingHint = true;
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(this.getPassword() == null) {
            setForeground(Color.BLACK);
            super.setEchoChar((char) 0x2022);
            super.setText("");
            showingHint = false;
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getPassword() == null) {
            setForeground(Color.gray);
            super.setEchoChar((char) 0);
            super.setText(hint);
            showingHint = true;
        }
    }

    @Override
    public char[] getPassword() {
        return showingHint ? null : super.getPassword();
    }

    /**
     * Inner class that modifies the {@link MyHintPwdField} in order to make it rounded
     * @author Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
     * @version 1.0
     * @since 25/4/2022
     */
    public static class RoundedMyHintPwdField extends MyHintPwdField {
        private Shape shape;

        /**
         * Default Constructor class that will match the superclass one.
         * @param hint Text for the hint
         */
        public RoundedMyHintPwdField(String hint) {
            super(hint);
            this.setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            super.paintComponent(g);
        }
        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        @Override
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            }
            return shape.contains(x, y);
        }
    }
}
