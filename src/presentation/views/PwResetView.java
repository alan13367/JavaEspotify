package presentation.views;

import presentation.views.GUIassets.MyHintTextField;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PwResetView extends JPanel {

    public PwResetView() throws IOException {
        setLayout(new GridBagLayout());
        configureView();
    }

    private void configureView() throws IOException {
        Font arialFont = new Font("Arial", Font.PLAIN, 25);
        Font alegreyaFont = new Font("Alegreya Sans SC", Font.BOLD, 30);
        Font smallerArialFont = new Font("Arial", Font.ITALIC, 15);

        Color myBlack = new Color(18, 18, 18);
        setBackground(myBlack);

        GridBagConstraints constraints = new GridBagConstraints();

        JTextArea textArea = new JTextArea("PASSWORD RESET");
        textArea.setFont(alegreyaFont);
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.white);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 2;
        constraints.weighty = 1.0;
        add (textArea, constraints);
        constraints.weighty = 0.0;

        JTextArea textArea1 = new JTextArea("Enter your username or email that you signed up with, you will receive an email with the new password");
        textArea1.setFont(smallerArialFont);
        textArea1.setBackground(Color.black);
        textArea1.setForeground(Color.white);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.gridheight = 2;
        constraints.weighty = 1.0;
        add (textArea1, constraints);
        constraints.weighty = 0.0;

        MyHintTextField userTextField=new MyHintTextField("username or email                         ");
        userTextField.setToolTipText("Username or password");
        userTextField.setFont(arialFont);
        userTextField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(userTextField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JButton sendButton_bottom=new JButton("    SEND    ");
        sendButton_bottom.setOpaque(true);
        sendButton_bottom.setFocusPainted(true);
        sendButton_bottom.setBorderPainted(true);
        sendButton_bottom.setContentAreaFilled(true);
        sendButton_bottom.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Especially important
        sendButton_bottom.setFont(alegreyaFont);
        sendButton_bottom.setBackground(Color.green);
        sendButton_bottom.setForeground(Color.white);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(sendButton_bottom, constraints);
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

