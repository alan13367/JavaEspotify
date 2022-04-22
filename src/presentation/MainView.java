package presentation;

import presentation.controllers.HomeController;
import presentation.controllers.RegisterController;
import presentation.controllers.SongsController;
import presentation.views.HomeView;
import presentation.views.SignInView;
import presentation.views.SignIn_SignUpView;
import presentation.views.SignUpView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private SignIn_SignUpView registerView;
    private static final String REGISTER_VIEW_CARD ="REGISTER_VIEW_CARD";

    private final CardLayout viewManager;

    private HomeView homeView;

    private static final String HOMEVIEW_CARD ="HOMEVIEW_CARD";


    public MainView(){
        viewManager = new CardLayout();
        getContentPane().setLayout(viewManager);
        addViews();
        configureWindow();
    }

    private void addViews() {
        registerView = new SignIn_SignUpView();
        homeView = new HomeView();
        //add(registerView, REGISTER_VIEW_CARD);
        add(homeView,HOMEVIEW_CARD);
    }

    private void configureWindow(){
        setTitle("Espotifai");
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    public void showRegisterView() {viewManager.show(registerView, REGISTER_VIEW_CARD);}

    public void showHomeView(){
        viewManager.show(homeView,HOMEVIEW_CARD);
    }

    public void start(){
        setVisible(true);
    }


    public void registerControllers(HomeController homeController, SongsController songsController, RegisterController registerController){
        homeView.registerController(homeController);
        homeView.getSongsView().registerController(songsController);
        registerView.registerController(registerController);
    }

    public HomeView getHomeView() {
        return homeView;
    }

    public SignIn_SignUpView getRegisterView() {
        return registerView;
    }
}
