package presentation.views;

import business.entities.User;
import business.managers.UserManager;
import presentation.views.GUIassets.MyHintPwdField;
import presentation.views.GUIassets.MyHintTextField;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SignIn_SignUpView extends JPanel{

    Font arialFont = new Font("Arial", Font.PLAIN, 25);
    Font alegreyaFont = new Font("Alegreya Sans SC", Font.BOLD, 30);
    Font smallerArialFont = new Font("Arial", Font.ITALIC, 15);

    JPanel signUpView = new JPanel();
    JPanel signInView = new JPanel();

    JPanel jpMain;

    private final CardLayout mainPanelManager;

    private JButton signUpButton_bottom;
    private JButton signInButton_north;
    private JButton signInButton_bottom;
    private JButton signUpButton;

    public static final String SIGN_IN_BTN_SIGN_IN = "SIGN_IN_BTN_SIGN_IN";
    public static final String SIGN_IN_BTN_SIGN_UP = "SIGN_IN_BTN_SIGN_UP";
    public static final String BTN_SIGN_UP = "BTN_SIGN_UP";
    public static final String BTN_SIGN_IN = "BTN_SIGN_IN";


    private MyHintTextField.RoundedMyHintTextField userTextField;
    private MyHintTextField.RoundedMyHintTextField emailField;
    private MyHintPwdField.RoundedMyHintPwdField passwordField;
    private MyHintPwdField.RoundedMyHintPwdField confirmPasswordField;
    private MyHintTextField.RoundedMyHintTextField userTextField1;
    private MyHintPwdField.RoundedMyHintPwdField passwordField1;

    public static final String CARD_SIGN_IN = "CARD_SIGN_IN";
    public static final String CARD_SIGN_UP = "CARD_SIGN_UP";

    public UserManager userManager = new UserManager();

    public SignIn_SignUpView(){
        setLayout(new BorderLayout());
        mainPanelManager = new CardLayout();
        configureView();
    }

    private void configureView(){
        jpMain = new JPanel(mainPanelManager);
        add(jpMain,BorderLayout.CENTER);
        configureSignUpView();
        configureSignInView();
        configureSignInCard();
        configureSignUpCard();
    }

    private void configureSignUpView() {
        Color myBlack = new Color(18, 18, 18);
        signUpView.setLayout(new GridBagLayout());
        signUpView.setBackground(myBlack);

        GridBagConstraints constraints = new GridBagConstraints();

        userTextField=new MyHintTextField.RoundedMyHintTextField("username                                     ");
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

        emailField=new MyHintTextField.RoundedMyHintTextField("email                                            ");
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

        passwordField=new MyHintPwdField.RoundedMyHintPwdField("password                                      ");
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

        confirmPasswordField=new MyHintPwdField.RoundedMyHintPwdField("confirm password                          ");
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

        signUpButton_bottom=new JButton("SIGN UP");
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

        signInButton_north=new JButton("SIGN IN");
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


    public void showSignInCard(){
        mainPanelManager.show(jpMain,CARD_SIGN_IN);
    }
    public void showSignUpCard(){
        mainPanelManager.show(jpMain,CARD_SIGN_UP);
    }
    private void configureSignInCard(){
        jpMain.add(signInView,CARD_SIGN_IN);
    }

    private void configureSignUpCard(){
        jpMain.add(signUpView,CARD_SIGN_UP);
    }

    private void configureSignInView() {
        Color myBlack = new Color(18, 18, 18);
        signInView.setLayout(new GridBagLayout());
        signInView.setBackground(myBlack);

        GridBagConstraints constraints = new GridBagConstraints();

        userTextField1 = new MyHintTextField.RoundedMyHintTextField("username                         ");
        userTextField1.setToolTipText("Enter username");
        userTextField1.setFont(arialFont);
        userTextField1.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signInView.add(userTextField1, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        passwordField1=new MyHintPwdField.RoundedMyHintPwdField("password                          ");
        passwordField1.setToolTipText("Enter password");
        passwordField1.setFont(arialFont);
        passwordField1.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signInView.add(passwordField1, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        signInButton_bottom=new JButton("SIGN IN");
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

        signUpButton=new JButton("SIGN UP");
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

    public void registerController(ActionListener controller) {
        signUpButton_bottom.addActionListener(controller);
        signInButton_north.addActionListener(controller);
        signInButton_bottom.addActionListener(controller);
        signUpButton.addActionListener(controller);
    }

    public void pop_up_dialog(String message, String title) {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public boolean caseSignUp() {
        if(!userManager.checkEmailFormat(emailField.getText())) {
            pop_up_dialog("email format is wrong. Please check it!", "Wrong Email format");
            return false;
        }
        if(!userManager.checkPasswordFormat(String.valueOf(passwordField.getPassword()))) {
            pop_up_dialog("password format is wrong. Please check it!", "Wrong Password format");
            return false;
        }
        if(!String.valueOf(passwordField.getPassword()).equals(String.valueOf(confirmPasswordField.getPassword()))) {
            pop_up_dialog("this field does not match the password field", "Password not matching");
            return false;
        }
        User user = new User(userTextField.getText(), emailField.getText(), String.valueOf(passwordField.getPassword()));
        userManager.createUser(user);
        return true;
    }

    public boolean caseSignIn() {
        return userManager.logIn(userTextField1.getText(), String.valueOf(passwordField1.getPassword()));
    }
}
