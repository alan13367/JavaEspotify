package presentation.views.GUIassets;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

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
