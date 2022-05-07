package presentation;

import presentation.controllers.HomeController;
import presentation.controllers.PlaylistsController;
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
        setSize(900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void showRegisterView() {
        setSize(900,700);
        setLocationRelativeTo(null);
        registerView.clearFields();
        viewManager.show(getContentPane(), REGISTER_VIEW_CARD);
    }

    public void showHomeView(String username){
        setSize(1500,720);
        setLocationRelativeTo(null);
        homeView.setUsername(username);
        viewManager.show(getContentPane(),HOMEVIEW_CARD);
    }

    public void start(){
        setVisible(true);
    }


    public void registerControllers(HomeController homeController, SongsController songsController, SignInSignUpController signInSignUpController, PlaylistsController playlistsController){
        registerView.registerController(signInSignUpController);
        homeView.registerController(homeController);
        homeView.getSongsView().registerController(songsController);
        homeView.getPlaylistsView().registerController(playlistsController);
    }

    public HomeView getHomeView() {
        return homeView;
    }

    public SignInSignUpView getRegisterView() {
        return registerView;
    }
}
