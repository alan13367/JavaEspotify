package presentation.views.GUIassets;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * Class that will contain our own implementation of the Scrollbar that will be used through the whole program it extends the
 * default {@link BasicScrollBarUI} implementation.
 *
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 25/4/2022
 */
public class MyScrollBarUI extends BasicScrollBarUI {
    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(80,80,80);
        this.scrollbar.setBackground(new Color(16,16,16));
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new BasicArrowButton(orientation,new Color(16,16,16),new Color(16,16,16)
                ,Color.white,new Color(16,16,16));
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new BasicArrowButton(orientation,new Color(16,16,16),new Color(16,16,16)
                ,Color.white,new Color(16,16,16));
    }
}
