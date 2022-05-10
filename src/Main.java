import business.BusinessFacade;
import business.ModelFacade;
import business.entities.Song;
import persistence.SQL.SQLSongDAO;
import presentation.MainView;
import presentation.controllers.*;
import presentation.views.PlayerView;
import presentation.views.SongsView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BusinessFacade businessFacade = new ModelFacade();
            MainView mainView = new MainView();
            SignInSignUpController signInSignUpController = new SignInSignUpController(mainView.getRegisterView(),businessFacade,mainView);
            HomeController homeController = new HomeController(mainView,mainView.getHomeView(), businessFacade);
            SongsController songsController = new SongsController(mainView.getHomeView().getSongsView(), businessFacade,mainView.getHomeView().getPlayerView());
            PlaylistsController playlistsController = new PlaylistsController(mainView.getHomeView().getPlaylistsView(),businessFacade);
            PlayerController playerController = new PlayerController(mainView.getHomeView().getPlayerView(),businessFacade);
            AddSongsController addSongsController = new AddSongsController(mainView.getHomeView().getAddSongsView(),mainView.getHomeView(),businessFacade);
            mainView.registerControllers(homeController, songsController,signInSignUpController,playlistsController
                    ,playerController,addSongsController);
            mainView.start();
        });

    }
}
