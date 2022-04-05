package presentation;

import presentation.controllers.HomeController;
import presentation.views.HomeView;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

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
        homeView = new HomeView();
        HomeController controller = new HomeController(homeView);
        homeView.registerController(controller);
        add(homeView,HOMEVIEW_CARD);
    }

    private void configureWindow(){
        setTitle("Espotifai");
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    public void showHomeView(){
        viewManager.show(homeView,HOMEVIEW_CARD);
    }

    public void showFrame(){
        setVisible(true);
    }

}
