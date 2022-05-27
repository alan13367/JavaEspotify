package presentation.controllers;

import business.BusinessFacade;
import presentation.MainView;
import presentation.views.SignInSignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInSignUpController implements ActionListener {
    private final SignInSignUpView view;
    private final MainView mainView;
    private final BusinessFacade businessFacade;

    public SignInSignUpController(MainView mainView, BusinessFacade businessFacade) {
        this.view = mainView.getRegisterView();
        this.businessFacade = businessFacade;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case (SignInSignUpView.BTN_SIGN_IN_SWITCH) -> {
                System.out.println("sign in clicked in sign up");
                view.showSignInCard();

            }
            case (SignInSignUpView.BTN_SIGN_UP_SWITCH) -> {
                System.out.println("sign up clicked in sign in");
                view.showSignUpCard();
            }
            case (SignInSignUpView.BTN_SIGN_UP) -> {
                if(!view.signUpFieldsEmpty()){
                    if(caseSignUp()) {
                        System.out.println("sign up clicked in sign up");
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

    public boolean caseSignUp() {
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

    public boolean caseSignIn() {
        return businessFacade.login(view.getUserFieldSignIn(), view.getPwdFieldSignIn());
    }

}
