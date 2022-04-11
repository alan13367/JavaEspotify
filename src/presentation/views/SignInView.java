package presentation.views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SignInView extends JPanel{

    public SignInView(){
        setLayout(new GridBagLayout());
        configureView();
    }

    private void configureView(){
        Font arialFont = new Font("Arial", Font.PLAIN, 25);
        Font alegreyaFont = new Font("Alegreya Sans SC", Font.BOLD, 30);
        Font smallerArialFont = new Font("Arial", Font.ITALIC, 15);

        Color myBlack = new Color(18, 18, 18);
        setBackground(myBlack);

        GridBagConstraints constraints = new GridBagConstraints();

        SongsView.MyHintTextField userTextField=new SongsView.MyHintTextField("username                         ");
        userTextField.setToolTipText("Enter username");
        userTextField.setFont(arialFont);
        userTextField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(userTextField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        SongsView.MyHintTextField passwordField=new SongsView.MyHintTextField("password                          ");
        passwordField.setToolTipText("Enter password");
        passwordField.setFont(arialFont);
        passwordField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(passwordField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JButton signInButton_bottom=new JButton("SIGN IN");
        signInButton_bottom.setOpaque(true);
        signInButton_bottom.setFocusPainted(true);
        signInButton_bottom.setBorderPainted(true);
        signInButton_bottom.setContentAreaFilled(true);
        signInButton_bottom.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Especially important
        signInButton_bottom.setFont(alegreyaFont);
        signInButton_bottom.setBackground(Color.green);
        signInButton_bottom.setForeground(Color.white);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(signInButton_bottom, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JButton signInButton_north=new JButton("SIGN IN");
        signInButton_north.setFont(alegreyaFont);
        signInButton_north.setOpaque(true);
        signInButton_north.setFocusPainted(true);
        signInButton_north.setBorderPainted(true);
        signInButton_north.setContentAreaFilled(true);
        signInButton_north.setBorderPainted(false);
        signInButton_north.setBackground(myBlack);
        signInButton_north.setForeground(Color.green);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.EAST;
        add(signInButton_north, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JButton signUpButton=new JButton("SIGN UP");
        signUpButton.setFont(alegreyaFont);
        signUpButton.setOpaque(true);
        signUpButton.setFocusPainted(true);
        signUpButton.setBorderPainted(false);
        signUpButton.setContentAreaFilled(true);
        signUpButton.setBackground(myBlack);
        signUpButton.setForeground(Color.white);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.WEST;
        add(signUpButton, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JButton forgotPasswordButton=new JButton("Forgot password? Click here!");
        forgotPasswordButton.setForeground(Color.white);
        forgotPasswordButton.setFont(smallerArialFont);
        forgotPasswordButton.setBackground(myBlack);
        signInButton_bottom.setOpaque(true);
        signInButton_bottom.setFocusPainted(true);
        signInButton_bottom.setBorderPainted(false);
        signInButton_bottom.setContentAreaFilled(true);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.CENTER;
        add(forgotPasswordButton, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JLabel logo = new JLabel();
        //Read the image as a BufferedImage (to resize)*/
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("assets/ESPOTIFY LOGO.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Resize the BufferedImage
        Image dimg = img.getScaledInstance(300, 145,
                Image.SCALE_SMOOTH);

        logo.setIcon(new ImageIcon(dimg));
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.CENTER;
        add(logo, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
    }
}
