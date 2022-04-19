import business.BusinessFacade;
import business.ModelFacade;
import persistence.SQL.SQLSongDAO;
import presentation.MainView;
import presentation.controllers.HomeController;
import presentation.controllers.SongsController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BusinessFacade businessFacade = new ModelFacade();
            MainView mainView = new MainView();
            HomeController homeController = new HomeController(mainView.getHomeView(),businessFacade);
            SongsController songsController = new SongsController(mainView.getHomeView().getSongsView(),businessFacade);
            mainView.registerControllers(homeController,songsController);
            mainView.start();
        });
    }
}
