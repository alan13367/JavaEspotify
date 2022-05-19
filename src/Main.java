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
            SignInSignUpController signInSignUpController = new SignInSignUpController(mainView.getRegisterView()
                    ,businessFacade,mainView);
            HomeController homeController = new HomeController(mainView,mainView.getHomeView(), businessFacade);
            SongsController songsController = new SongsController(mainView.getHomeView().getSongsView(), businessFacade
                    , mainView.getHomeView().getStatisticsView());
            PlaylistsController playlistsController = new PlaylistsController(mainView.getHomeView().getPlaylistsView()
                    ,mainView.getHomeView().getPlayerView(),businessFacade);
            PlayerController playerController = new PlayerController(mainView.getHomeView().getPlayerView(),businessFacade);
            StatisticsController statisticsController = new StatisticsController(mainView.getHomeView().getStatisticsView(), businessFacade);
            AddSongsController addSongsController = new AddSongsController(mainView.getHomeView().getAddSongsView()
                    ,mainView.getHomeView(),businessFacade, mainView.getHomeView().getStatisticsView());
            mainView.registerControllers(homeController, songsController,signInSignUpController,playlistsController
                    ,playerController,addSongsController, statisticsController);
            mainView.start();
        });

    }
}
