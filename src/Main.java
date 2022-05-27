import business.BusinessFacade;
import business.ModelFacade;
import presentation.MainView;
import presentation.controllers.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            BusinessFacade businessFacade = new ModelFacade();
            MainView mainView = new MainView();
            SignInSignUpController signInSignUpController = new SignInSignUpController(mainView,businessFacade);
            HomeController homeController = new HomeController(mainView, businessFacade);
            SongsController songsController = new SongsController(mainView.getHomeView(), businessFacade);
            PlaylistsController playlistsController = new PlaylistsController(mainView.getHomeView(),businessFacade);
            PlayerController playerController = new PlayerController(mainView.getHomeView(),businessFacade);
            StatisticsController statisticsController = new StatisticsController(mainView.getHomeView(), businessFacade);
            AddSongsController addSongsController = new AddSongsController(mainView.getHomeView(),businessFacade);
            mainView.registerControllers(homeController, songsController,signInSignUpController,playlistsController
                    ,playerController,addSongsController, statisticsController);
            mainView.start();
        });

    }
}
