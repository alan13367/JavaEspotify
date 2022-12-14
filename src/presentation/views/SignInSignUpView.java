package presentation.views;

import presentation.views.GUIassets.MyHintPwdField;
import presentation.views.GUIassets.MyHintTextField;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * the GUI of signIn panel and signUP panel
 * @author Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 10/04/2022
 */
public class SignInSignUpView extends JPanel{

    private JPanel signUpView;
    private JPanel signInView;
    private JPanel jpMain;

    private final CardLayout mainPanelManager;

    private JButton signUpButton_bottom;
    private JButton signInButton_north;
    private JButton signInButton_bottom;
    private JButton signUpButton;

    public static final String BTN_SIGN_UP_SWITCH = "BTN_SIGN_UP_SWITCH";
    public static final String BTN_SIGN_IN_SWITCH = "BTN_SIGN_IN_SWITCH";
    public static final String BTN_SIGN_UP = "BTN_SIGN_UP";
    public static final String BTN_SIGN_IN = "BTN_SIGN_IN";


    private MyHintTextField.RoundedMyHintTextField userTextFieldSignUp;
    private MyHintTextField.RoundedMyHintTextField emailField;
    private MyHintPwdField.RoundedMyHintPwdField pwdFieldSignUp;
    private MyHintPwdField.RoundedMyHintPwdField pwdFieldConfirmSignUp;
    private MyHintTextField.RoundedMyHintTextField userFieldSignIn;
    private MyHintPwdField.RoundedMyHintPwdField pwdFieldSignIn;

    public static final String CARD_SIGN_IN = "CARD_SIGN_IN";
    public static final String CARD_SIGN_UP = "CARD_SIGN_UP";

    private static final Font arialFont = new Font("Arial", Font.PLAIN, 25);
    private static final Font alegreyaFont = new Font("Alegreya Sans SC", Font.BOLD, 30);
    private static final Font smallerArialFont = new Font("Arial", Font.ITALIC, 15);


    /**
     * The view constructor, sets the layout of the panel and configures all the views, also calls a new card layout that will manage which panel it shows
     */
    public SignInSignUpView(){
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
        signUpView = new JPanel();
        Color myBlack = new Color(18, 18, 18);
        signUpView.setLayout(new GridBagLayout());
        signUpView.setBackground(myBlack);

        GridBagConstraints constraints = new GridBagConstraints();

        userTextFieldSignUp =new MyHintTextField.RoundedMyHintTextField("Username");
        userTextFieldSignUp.setToolTipText("Enter username");
        userTextFieldSignUp.setFont(arialFont);
        userTextFieldSignUp.setForeground(Color.gray);
        userTextFieldSignUp.setPreferredSize(new Dimension(400,50));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signUpView.add(userTextFieldSignUp, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        emailField=new MyHintTextField.RoundedMyHintTextField("Email");
        emailField.setToolTipText("Enter email");
        emailField.setFont(arialFont);
        emailField.setForeground(Color.gray);
        emailField.setPreferredSize(new Dimension(400,50));
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signUpView.add(emailField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        pwdFieldSignUp =new MyHintPwdField.RoundedMyHintPwdField("Password");
        pwdFieldSignUp.setToolTipText("Enter password");
        pwdFieldSignUp.setFont(arialFont);
        pwdFieldSignUp.setForeground(Color.gray);
        pwdFieldSignUp.setPreferredSize(new Dimension(400,50));
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signUpView.add(pwdFieldSignUp, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        pwdFieldConfirmSignUp =new MyHintPwdField.RoundedMyHintPwdField("confirm password                          ");
        pwdFieldConfirmSignUp.setToolTipText("confirm password");
        pwdFieldConfirmSignUp.setFont(arialFont);
        pwdFieldConfirmSignUp.setForeground(Color.gray);
        pwdFieldConfirmSignUp.setPreferredSize(new Dimension(400,50));
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signUpView.add(pwdFieldConfirmSignUp, constraints);
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
        signInButton_north.setActionCommand(BTN_SIGN_IN_SWITCH);
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

    /**
     *  the mainPanel which has card layout shows the sign in panel
     */
    public void showSignInCard(){
        mainPanelManager.show(jpMain,CARD_SIGN_IN);
    }
    /**
     *  the mainPanel which has card layout shows the sign up panel
     */
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
        signInView = new JPanel();
        Color myBlack = new Color(18, 18, 18);
        signInView.setLayout(new GridBagLayout());
        signInView.setBackground(myBlack);

        GridBagConstraints constraints = new GridBagConstraints();

        userFieldSignIn = new MyHintTextField.RoundedMyHintTextField("username                         ");
        userFieldSignIn.setToolTipText("Enter username");
        userFieldSignIn.setFont(arialFont);
        userFieldSignIn.setForeground(Color.gray);
        userFieldSignIn.setPreferredSize(new Dimension(400,50));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signInView.add(userFieldSignIn, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        pwdFieldSignIn =new MyHintPwdField.RoundedMyHintPwdField("password                          ");
        pwdFieldSignIn.setToolTipText("Enter password");
        pwdFieldSignIn.setFont(arialFont);
        pwdFieldSignIn.setForeground(Color.gray);
        pwdFieldSignIn.setPreferredSize(new Dimension(400,50));
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        signInView.add(pwdFieldSignIn, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        signInButton_bottom=new JButton("SIGN IN");
        signInButton_bottom.setActionCommand(BTN_SIGN_IN);
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
        signUpButton.setActionCommand(BTN_SIGN_UP_SWITCH);
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

    /**
     * adding action listeners to all the buttons of both panels that have to make a change when clicked
     * @param controller handler of all action events
     */
    public void registerController(ActionListener controller) {
        signUpButton_bottom.addActionListener(controller);
        signInButton_north.addActionListener(controller);
        signInButton_bottom.addActionListener(controller);
        signUpButton.addActionListener(controller);
    }

    /**
     * pops up an error message to the user
     * @param message the text that will appear in the middle of the pop-up dialog
     * @param title the main idea of why the dialog popped up.
     */
    public void pop_up_ErrorDialog(String message, String title) {
            JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * pops up an information message to the user showing that an action was successful
     * @param message the text that will appear in the middle of the pop-up dialog
     * @param title the main idea of why the dialog popped up.
     */
    public void pop_up_SuccessDialog(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * gets the email the user has signed up with
     * @return whatever the user has entered in the email text field in the sign-up view
     */
    public String getEmailFieldSignUp(){
        return emailField.getText();
    }

    /**
     * gets the password the user has signed up with
     * @return whatever the user has entered in the password text field in the sign-up view
     */
    public String getPwdFieldSignUp(){
        return String.valueOf(pwdFieldSignUp.getPassword());
    }

    /**
     * gets the replica password the user has signed up with
     * @return whatever the user has entered in confirm password text field in the sign-up view
     */
    public String getConfirmationPwdFieldSignUp(){
        return String.valueOf(pwdFieldConfirmSignUp.getPassword());
    }

    /**
     * gets the username the user has signed up with
     * @return whatever the user has entered in the username text field in the sign-up view
     */
    public String getUserFieldSignUp(){
        return userTextFieldSignUp.getText();
    }

    /**
     * gets the username the user has signed in with
     * @return whatever the user has entered in the username text field in the sign-in view
     */
    public String getUserFieldSignIn(){
        return userFieldSignIn.getText();
    }

    /**
     * gets the password the user has signed in with
     * @return whatever the user has entered in the password text field in the sign-in view
     */
    public String getPwdFieldSignIn(){
        return String.valueOf(pwdFieldSignIn.getPassword());
    }

    /**
     * checks if any of the text fields in the sign in panel is empty.
     * @return true in case one of the text fields is empty or both are empty, false otherwise
     */
    public boolean signInFieldsEmpty(){
        return getUserFieldSignIn().isEmpty() || getPwdFieldSignIn().isEmpty();
    }

    /**
     * checks if any of the text fields in the sign up panel is empty.
     * @return true in case at least one of the text fields is empty , false otherwise
     */
    public boolean signUpFieldsEmpty(){
        return getEmailFieldSignUp().isEmpty() || getUserFieldSignUp().isEmpty() || getPwdFieldSignUp().isEmpty()
                || getConfirmationPwdFieldSignUp().isEmpty();
    }

    /**
     * clear all the text fields to be empty
     */
    public void clearFields() {
        userFieldSignIn.setText("");
        pwdFieldSignIn.setText("");
        userTextFieldSignUp.setText("");
        emailField.setText("");
        pwdFieldSignUp.setText("");
        pwdFieldConfirmSignUp.setText("");

    }
}
