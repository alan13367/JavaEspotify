package presentation.views;

import javax.swing.*;
import java.awt.*;

public class SignInView extends JFrame {

    public SignInView() {
        setLayout(new BorderLayout());
        configureView();
        configureWindow();
    }

    private void configureWindow() {
        setTitle("ESPOTIFAY");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void configureView() {
        //Background color
        JPanel bgPanel = new JPanel();
        bgPanel.setBackground(Color.black);
        bgPanel.setSize(1000, 600);
        bgPanel.setLayout(new BorderLayout());

        //Content
        JPanel content = new JPanel();

    }
}
