package presentation.views;

import presentation.views.GUIassets.MyHintTextField;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SignIn_SignUpView extends JPanel{

    Font arialFont = new Font("Arial", Font.PLAIN, 25);
    Font alegreyaFont = new Font("Alegreya Sans SC", Font.BOLD, 30);
    Font smallerArialFont = new Font("Arial", Font.ITALIC, 15);
    JPanel signUpView = new JPanel();
    JPanel signInView = new JPanel();


    public static final String SIGN_IN_BTN_SIGN_IN = "SIGN_IN_BTN_SIGN_IN";
    public static final String SIGN_IN_BTN_SIGN_UP = "SIGN_IN_BTN_SIGN_UP";
    public static final String BTN_SIGN_UP = "BTN_SIGN_UP";
    public static final String BTN_SIGN_IN = "BTN_SIGN_IN";

    public SignIn_SignUpView(){
        configureView();
    }

    private void configureView(){
        configureSignInView();
        configureSignUpView();
        setLayout(new BorderLayout()); //tried with setLayout(new BorderLayout()); still signUpView didn't look good
        add(signUpView, BorderLayout.CENTER); //Modify this to add(signUpView) to see the other panel
    }

    private void configureSignUpView() {
        Color myBlack = new Color(18, 18, 18);
        signUpView.setLayout(new GridBagLayout());
        signUpView.setBackground(myBlack);

        GridBagConstraints constraints = new GridBagConstraints();

        MyHintTextField userTextField=new MyHintTextField("username                                     ");
        userTextField.setToolTipText("Enter username");
        userTextField.setFont(arialFont);
        userTextField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signUpView.add(userTextField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        MyHintTextField emailField=new MyHintTextField("email                                            ");
        emailField.setToolTipText("Enter email");
        emailField.setFont(arialFont);
        emailField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signUpView.add(emailField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        MyHintTextField passwordField=new MyHintTextField("password                                      ");
        passwordField.setToolTipText("Enter password");
        passwordField.setFont(arialFont);
        passwordField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signUpView.add(passwordField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        MyHintTextField confirmPasswordField=new MyHintTextField("confirm password                          ");
        confirmPasswordField.setToolTipText("confirm password");
        confirmPasswordField.setFont(arialFont);
        confirmPasswordField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signUpView.add(confirmPasswordField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JButton signUpButton_bottom=new JButton("SIGN UP");
        signUpButton_bottom.setActionCommand(BTN_SIGN_UP);
        signUpButton_bottom.setOpaque(true);
        signUpButton_bottom.setFocusPainted(true);
        signUpButton_bottom.setBorderPainted(true);
        signUpButton_bottom.setContentAreaFilled(true);
        signUpButton_bottom.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Especially important
        signUpButton_bottom.setFont(alegreyaFont);
        signUpButton_bottom.setBackground(Color.green);
        signUpButton_bottom.setForeground(Color.white);
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signUpView.add(signUpButton_bottom, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JButton signInButton_north=new JButton("SIGN IN");
        signInButton_north.setActionCommand(BTN_SIGN_IN);
        signInButton_north.setFont(alegreyaFont);
        signInButton_north.setOpaque(true);
        signInButton_north.setFocusPainted(true);
        signInButton_north.setBorderPainted(true);
        signInButton_north.setContentAreaFilled(true);
        signInButton_north.setBorderPainted(false);
        signInButton_north.setBackground(myBlack);
        signInButton_north.setForeground(Color.WHITE);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.EAST;
        signUpView.add(signInButton_north, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JButton signUpButton=new JButton("SIGN UP");
        signUpButton.setFont(alegreyaFont);
        signUpButton.setOpaque(true);
        signUpButton.setFocusPainted(true);
        signUpButton.setBorderPainted(false);
        signUpButton.setContentAreaFilled(true);
        signUpButton.setBackground(myBlack);
        signUpButton.setForeground(Color.GREEN);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.WEST;
        signUpView.add(signUpButton, constraints);
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
        signUpView.add(logo, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
    }

    /*private void configureSignUpView() {
        Color myBlack = new Color(18, 18, 18);
        signUpView.setLayout(new GridBagLayout());
        signUpView.setBackground(myBlack);

        GridBagConstraints constraints = new GridBagConstraints();

        //for(int i=0;i<=10;i++) signUpView.add(Box.createGlue(), setEmptyCells(constraints, 0,i));
        //for(int i=0;i<=10;i++) signUpView.add(Box.createGlue(), setEmptyCells(constraints, 1,i));
        //for(int i=0;i<=10;i++) signUpView.add(Box.createGlue(), setEmptyCells(constraints, 4,i));
        //for(int i=0;i<=10;i++) signUpView.add(Box.createGlue(), setEmptyCells(constraints, 5, i));
        //for(int i=0;i<=4;i++) signUpView.add(Box.createGlue(), setEmptyCells(constraints, i, 11));

        MyHintTextField userTextField= setTextFields("username\t\t", "Enter username", arialFont, constraints, 1, 3, 2, 1);
        //userTextField.setMargin(new Insets(5, 5, 5, 5));
        signUpView.add(userTextField, constraints);

        MyHintTextField emailTextField= setTextFields("email\t\t", "Enter email", arialFont, constraints, 1, 5, 2, 1);
        signUpView.add(emailTextField, constraints);

        MyHintTextField passwordField= setTextFields("password\t\t", "Enter password", arialFont, constraints, 1, 7, 2, 1);
        signUpView.add(passwordField, constraints);

        MyHintTextField confirmPwdField=setTextFields("confirm password\t\t", "Confirm password", arialFont, constraints, 1, 9, 2, 1);
        signUpView.add(confirmPwdField, constraints);


        JButton signUPBtn_bottom= setBtns("SIGN UP", alegreyaFont, Color.green, Color.white, 10, 10, 10, 10, constraints, 1, 11, 2, 1);
        signUPBtn_bottom.setActionCommand(BTN_SIGN_UP);
        signUpView.add(signUPBtn_bottom, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JButton signUPBtn_north=setBtns("SIGN UP", alegreyaFont, myBlack, Color.green, 0, 0, 0, 0, constraints, 2, 1, 1, 1);
        signUPBtn_north.setMargin(new Insets(0, 5, 0, 5));
        constraints.anchor = GridBagConstraints.WEST;
        signUpView.add(signUPBtn_north, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JButton signInButton=setBtns("SIGN IN", alegreyaFont, myBlack, Color.white, 0, 0, 0, 0, constraints, 1, 1, 1, 1);
        signInButton.setActionCommand(BTN_SIGN_IN);
        signInButton.setMargin(new Insets(0, 5, 0, 5));
        constraints.anchor = GridBagConstraints.EAST;
        signUpView.add(signInButton, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;


        JLabel logo = new JLabel();
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
        constraints.anchor = GridBagConstraints.CENTER;
        signUpView.add(logo, constraints);

    }

*/
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
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        return button;
    }

    private MyHintTextField setTextFields(String hintTF, String toolTipText, Font font, GridBagConstraints constraints, int x, int y, int w, int h) {
        MyHintTextField textField = new MyHintTextField(hintTF);
        textField.setToolTipText(toolTipText);
        textField.setFont(font);
        textField.setForeground(Color.black);
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        return textField;
    }

    private void configureSignInView() {
        Color myBlack = new Color(18, 18, 18);
        signInView.setLayout(new GridBagLayout());
        signInView.setBackground(myBlack);

        GridBagConstraints constraints = new GridBagConstraints();

        MyHintTextField userTextField=new MyHintTextField("username                         ");
        userTextField.setToolTipText("Enter username");
        userTextField.setFont(arialFont);
        userTextField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signInView.add(userTextField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        MyHintTextField passwordField=new MyHintTextField("password                          ");
        passwordField.setToolTipText("Enter password");
        passwordField.setFont(arialFont);
        passwordField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signInView.add(passwordField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JButton signInButton_bottom=new JButton("SIGN IN");
        signInButton_bottom.setActionCommand(SIGN_IN_BTN_SIGN_IN);
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
        signInView.add(signInButton_bottom, constraints);
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
        signInView.add(signInButton_north, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JButton signUpButton=new JButton("SIGN UP");
        signUpButton.setActionCommand(SIGN_IN_BTN_SIGN_UP);
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
        signInView.add(signUpButton, constraints);
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
        signInView.add(forgotPasswordButton, constraints);
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
        signInView.add(logo, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
    }
}
