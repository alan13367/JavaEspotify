package presentation.controllers;

import business.BusinessFacade;
import business.managers.UserManager;
import presentation.views.HomeView;
import presentation.views.SignIn_SignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener {
    private final SignIn_SignUpView view;
    private final HomeView homeView;
    private final BusinessFacade businessFacade;

    public RegisterController(SignIn_SignUpView view, BusinessFacade businessFacade, HomeView homeView) {
        this.view = view;
        this.businessFacade = businessFacade;
        this.homeView = homeView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case (SignIn_SignUpView.BTN_SIGN_IN) -> {
                System.out.println("sign in clicked in sign up");
                view.showSignInCard();
            }
            case (SignIn_SignUpView.BTN_SIGN_UP) -> {
                if(view.caseSignUp()) {
                    System.out.println("sign up clicked in sign up");
                    view.showSignInCard();
                }
            }
            case (SignIn_SignUpView.SIGN_IN_BTN_SIGN_IN) -> {
                System.out.println("sign in clicked in sign in");
                if(!view.caseSignIn()) {
                    view.pop_up_dialog("Wrong credentials", "Sign in Error");
                }
                if(view.caseSignIn()) {
                    view.pop_up_dialog("YOHOOOOO", "Sign in Success");
                }
            }
            case (SignIn_SignUpView.SIGN_IN_BTN_SIGN_UP) -> {
                System.out.println("sign up clicked in sign in");
                view.showSignUpCard();
            }
        }
    }

}
