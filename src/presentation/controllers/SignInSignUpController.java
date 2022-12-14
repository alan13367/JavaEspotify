package presentation.controllers;

import business.BusinessFacade;
import presentation.MainView;
import presentation.views.SignInSignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SignInSignUpController class manages the behaviour of the {@link SignInSignUpView} by implementing the {@link  ActionListener}
 * interface.
 *
 * @author Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 25/4/2022
 */
public class SignInSignUpController implements ActionListener {
    private final SignInSignUpView view;
    private final MainView mainView;
    private final BusinessFacade businessFacade;

    /**
     * Default SignInSignUpController Constructor that will link the views needed with {@link MainView} and the business
     * logic with the {@link BusinessFacade} interface.
     * @param mainView MainView object
     * @param businessFacade link to the logic of the program
     */
    public SignInSignUpController(MainView mainView, BusinessFacade businessFacade) {
        this.view = mainView.getRegisterView();
        this.businessFacade = businessFacade;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case (SignInSignUpView.BTN_SIGN_IN_SWITCH) -> {
                view.showSignInCard();

            }
            case (SignInSignUpView.BTN_SIGN_UP_SWITCH) -> {
                view.showSignUpCard();
            }
            case (SignInSignUpView.BTN_SIGN_UP) -> {
                if(!view.signUpFieldsEmpty()){
                    if(caseSignUp()) {
                        view.pop_up_SuccessDialog("You have Succesfully Signed Up","Sign Up Success");
                        mainView.showHomeView(view.getUserFieldSignUp());
                    }
                }
            }
            case (SignInSignUpView.BTN_SIGN_IN) -> {
                if(!view.signInFieldsEmpty()){
                    if(!caseSignIn()) {
                        view.pop_up_ErrorDialog("Wrong credentials", "Sign in Error");
                    }
                    if(caseSignIn()) {
                        mainView.showHomeView(view.getUserFieldSignIn());
                        //businessFacade.startPlayerThread();
                    }
                }
            }
        }
    }

    /**
     * Logic to check the signUp
     * @return true if correct false otherwise
     */
    private boolean caseSignUp() {
        String email = view.getEmailFieldSignUp();
        String password = view.getPwdFieldSignUp();
        if(!businessFacade.checkEmailFormat(email)) {
            view.pop_up_ErrorDialog("email format is wrong. Please check it!", "Wrong Email format");
            return false;
        }
        if(!businessFacade.checkPasswordFormat(password)) {
            view.pop_up_ErrorDialog("password format is wrong. Please check it!", "Wrong Password format");
            return false;
        }
        if(!password.equals(view.getConfirmationPwdFieldSignUp())) {
            view.pop_up_ErrorDialog("this field does not match the password field", "Password not matching");
            return false;
        }
        businessFacade.createUser(view.getUserFieldSignUp(), email,password);
        return true;
    }

    /**
     * Logic for the login to the program
     * @return true if correct false otherwise
     */
    private boolean caseSignIn() {
        return businessFacade.login(view.getUserFieldSignIn(), view.getPwdFieldSignIn());
    }

}
