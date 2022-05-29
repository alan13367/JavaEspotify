package presentation;

import presentation.controllers.*;
import presentation.views.HomeView;
import presentation.views.SignInSignUpView;

import javax.swing.*;
import java.awt.*;

/**
 * the GUI of the whole frame
 * @author Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 05/04/2022
 */
public class MainView extends JFrame {


    private static final String REGISTER_VIEW_CARD ="REGISTER_VIEW_CARD";

    private final CardLayout viewManager;

    private HomeView homeView;

    private SignInSignUpView registerView;

    private static final String HOMEVIEW_CARD ="HOMEVIEW_CARD";

    /**
     * The main view constructor, sets the layout of the frame and adds all the views and configures the window
     */
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

    /**
     * shows the sign in and sign up view
     */
    public void showRegisterView() {
        setSize(900,700);
        setLocationRelativeTo(null);
        registerView.clearFields();
        viewManager.show(getContentPane(), REGISTER_VIEW_CARD);
    }

    /**
     * shows the home view (songs - playlist - stats - add song - etc.)
     * @param username the username credentials
     */
    public void showHomeView(String username){
        setSize(1500,720);
        setLocationRelativeTo(null);
        homeView.setUsername(username);
        homeView.getPlaylistsView().loadUserPlaylists(username);
        homeView.showSongsCard();
        viewManager.show(getContentPane(),HOMEVIEW_CARD);
    }

    /**
     * set the frame to visible
     */
    public void start(){
        setVisible(true);
    }

    /**
     * register all the controllers we have in the system
     * @param homeController homeController
     * @param songsController songsController
     * @param signInSignUpController signInSignUpController
     * @param playlistsController playlistsController
     * @param playerController playerController
     * @param addSongsController addSongsController
     * @param statisticsController statisticsController
     */
    public void registerControllers(HomeController homeController, SongsController songsController
            , SignInSignUpController signInSignUpController, PlaylistsController playlistsController
            , PlayerController playerController,AddSongsController addSongsController, StatisticsController statisticsController){
        registerView.registerController(signInSignUpController);
        homeView.registerController(homeController);
        homeView.getSongsView().registerController(songsController);
        homeView.getPlaylistsView().registerController(playlistsController);
        homeView.getPlayerView().registerController(playerController);
        homeView.getAddSongsView().registerController(addSongsController);
        homeView.getStatisticsView().registerController(statisticsController);
    }

    /**
     * getter of home view
     * @return the home view
     */
    public HomeView getHomeView() {
        return homeView;
    }

    /**
     * getter of SignInSignUpView
     * @return the signIn and Signup view
     */
    public SignInSignUpView getRegisterView() {
        return registerView;
    }
}
