package presentation.views;

import javax.swing.*;
import java.awt.*;

public class SignInView extends JPanel{

    JButton signInButton_south=new JButton("SIGN IN");
    JButton signInButton_north=new JButton("SIGN IN");
    JButton signUpButton=new JButton("SIGN UP");
    JButton forgotPasswordButton=new JButton("Forgot password? Click here!");

    public SignInView() {
        setLayout(new BorderLayout());
        configureView();
    }

    private void configureView() {
        JPanel centerPanel = new JPanel(new GridBagLayout());

        JLabel userLabel=new JLabel("username");
        JLabel passwordLabel=new JLabel("password");
        JTextField userTextField=new JTextField();
        JPasswordField passwordField=new JPasswordField();

        //Configure center Panel
        centerPanel.setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.PINK);
        centerPanel.add(userTextField, Component.CENTER_ALIGNMENT);
        centerPanel.add(passwordField, Component.CENTER_ALIGNMENT);
        centerPanel.add(userLabel, Component.CENTER_ALIGNMENT);
        centerPanel.add(passwordLabel, Component.CENTER_ALIGNMENT);
        this.add(centerPanel, BorderLayout.CENTER);

    }
}
