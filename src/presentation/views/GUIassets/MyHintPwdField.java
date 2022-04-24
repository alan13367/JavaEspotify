package presentation.views.GUIassets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class MyHintPwdField extends JPasswordField implements FocusListener {
    private final String hint;
    private boolean showingHint;

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
            super.setEchoChar((char) 0x2022);
            super.setText("");
            showingHint = false;
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getPassword() == null) {
            super.setEchoChar((char) 0);
            super.setText(hint);
            showingHint = true;
        }
    }

    @Override
    public char[] getPassword() {
        return showingHint ? null : super.getPassword();
    }

    public static class RoundedMyHintPwdField extends MyHintPwdField {
        private Shape shape;

        public RoundedMyHintPwdField(String hint) {
            super(hint);
            this.setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            }
            return shape.contains(x, y);
        }
    }
}
