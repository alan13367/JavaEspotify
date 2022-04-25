import business.BusinessFacade;
import business.ModelFacade;
import presentation.MainView;
import presentation.controllers.HomeController;
import presentation.controllers.SignInSignUpController;
import presentation.controllers.SongsController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BusinessFacade businessFacade = new ModelFacade();
            MainView mainView = new MainView();
            HomeController homeController = new HomeController(mainView.getHomeView(), businessFacade);
            SongsController songsController = new SongsController(mainView.getHomeView().getSongsView(), businessFacade);
            SignInSignUpController signInSignUpController = new SignInSignUpController(mainView.getRegisterView(),businessFacade,mainView);
            mainView.registerControllers(homeController, songsController, signInSignUpController);
            mainView.start();
        });
    }
}
