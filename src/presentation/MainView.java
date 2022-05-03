package presentation;

import presentation.controllers.HomeController;
import presentation.controllers.SignInSignUpController;
import presentation.controllers.SongsController;
import presentation.views.HomeView;
import presentation.views.SignInSignUpView;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {


    private static final String REGISTER_VIEW_CARD ="REGISTER_VIEW_CARD";

    private final CardLayout viewManager;

    private HomeView homeView;

    private SignInSignUpView registerView;

    private static final String HOMEVIEW_CARD ="HOMEVIEW_CARD";


    public MainView(){
        viewManager = new CardLayout();
        getContentPane().setLayout(viewManager);
        addViews();
        configureWindow();
    }

    private void addViews() {
        registerView = new SignInSignUpView();
        homeView = new HomeView();
        add(registerView, REGISTER_VIEW_CARD);
        add(homeView,HOMEVIEW_CARD);
    }

    private void configureWindow(){
        setTitle("Espotify");
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    public void showRegisterView() {
        registerView.clearFields();
        viewManager.show(getContentPane(), REGISTER_VIEW_CARD);
    }

    public void showHomeView(String username){
        homeView.setUsername(username);
        viewManager.show(getContentPane(),HOMEVIEW_CARD);
    }

    public void start(){
        setVisible(true);
    }


    public void registerControllers(HomeController homeController, SongsController songsController, SignInSignUpController signInSignUpController){
        homeView.registerController(homeController);
        homeView.getSongsView().registerController(songsController);
        registerView.registerController(signInSignUpController);
    }

    public HomeView getHomeView() {
        return homeView;
    }

    public SignInSignUpView getRegisterView() {
        return registerView;
    }
}
