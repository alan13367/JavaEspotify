package presentation.views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SignUpView extends JPanel {

    public SignUpView() {
        setLayout(new GridBagLayout());
        configureView();
    }

    private void configureView() {
        //fonts used are the same as in sign in to look clean
        Font arialFont = new Font("Arial", Font.PLAIN, 25);
        Font alegreyaFont = new Font("Alegreya Sans SC", Font.BOLD, 30);
        Font smallerArialFont = new Font("Arial", Font.ITALIC, 15);

        Color myBlack = new Color(18, 18, 18);
        setBackground(myBlack);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;

        for(int i=0;i<=10;i++) add(Box.createGlue(), setEmptyCells(constraints, 0,i));
        for(int i=0;i<=10;i++) add(Box.createGlue(), setEmptyCells(constraints, 1,i));
        for(int i=0;i<=10;i++) add(Box.createGlue(), setEmptyCells(constraints, 4,i));
        for(int i=0;i<=10;i++) add(Box.createGlue(), setEmptyCells(constraints, 5, i));
        for(int i=0;i<=4;i++) add(Box.createGlue(), setEmptyCells(constraints, i, 11));

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
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        add(logo, constraints);

        JButton signUPBtn_bottom= setBtns("SIGN UP", alegreyaFont, Color.green, Color.white, 10, 10, 10, 10, constraints, 2, 11, 2, 1);
        add(signUPBtn_bottom, constraints);

        JButton signUPBtn_north=setBtns("SIGN UP", alegreyaFont, myBlack, Color.green, 0, 0, 0, 0, constraints, 3, 1, 1, 1);
        constraints.anchor = GridBagConstraints.WEST;
        add(signUPBtn_north, constraints);

        JButton signInButton=setBtns("SIGN IN", alegreyaFont, myBlack, Color.white, 0, 0, 0, 0, constraints, 2, 1, 1, 1);
        constraints.anchor = GridBagConstraints.EAST;
        add(signInButton, constraints);

        SongsView.MyHintTextField userTextField= setTextFields("username\t\t", "Enter username", arialFont, constraints, 2, 3, 2, 1);
        add(userTextField, constraints);


        SongsView.MyHintTextField emailTextField= setTextFields("email\t\t", "Enter email", arialFont, constraints, 2, 5, 2, 1);
        add(emailTextField, constraints);

        SongsView.MyHintTextField passwordField= setTextFields("password\t\t", "Enter password", arialFont, constraints, 2, 7, 2, 1);
        add(passwordField, constraints);

        SongsView.MyHintTextField confirmPwdField=setTextFields("confirm password\t\t", "Confirm password", arialFont, constraints, 2, 9, 2, 1);
        add(confirmPwdField, constraints);


    }

    private GridBagConstraints setEmptyCells(GridBagConstraints g,int x, int y) {
        g.gridx = x;
        g.gridy = y;
        return g;
    }

    private JButton setBtns(String button_text, Font font, Color background, Color foreground, int top, int left, int bottom, int right,
                            GridBagConstraints constraints, int grid_x, int grid_y, int grid_width, int grid_height) {
        JButton button = new JButton(button_text);
        button.setOpaque(true);
        button.setFocusPainted(true);
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        button.setBorder(BorderFactory.createEmptyBorder(top,left,bottom,right)); // Especially important
        button.setFont(font);
        button.setBackground(background);
        button.setForeground(foreground);
        constraints.gridx = grid_x;
        constraints.gridy = grid_y;
        constraints.gridwidth = grid_width;
        constraints.gridheight = grid_height;
        return button;
    }

    private SongsView.MyHintTextField setTextFields(String hintTF, String toolTipText, Font font, GridBagConstraints constraints, int x, int y, int w, int h) {
        SongsView.MyHintTextField textField = new SongsView.MyHintTextField(hintTF);
        textField.setToolTipText(toolTipText);
        textField.setFont(font);
        textField.setForeground(Color.black);
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        return textField;
    }

}
