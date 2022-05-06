import business.BusinessFacade;
import business.ModelFacade;
import business.entities.Song;
import com.google.gson.JsonObject;
import persistence.SQL.SQLSongDAO;
import persistence.SongDAO;
import presentation.MainView;
import presentation.controllers.HomeController;
import presentation.controllers.PlaylistsController;
import presentation.controllers.SignInSignUpController;
import presentation.controllers.SongsController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BusinessFacade businessFacade = new ModelFacade();
            MainView mainView = new MainView();
            SignInSignUpController signInSignUpController = new SignInSignUpController(mainView.getRegisterView(),businessFacade,mainView);
            HomeController homeController = new HomeController(mainView,mainView.getHomeView(), businessFacade);
            SongsController songsController = new SongsController(mainView.getHomeView().getSongsView(), businessFacade);
            PlaylistsController playlistsController = new PlaylistsController(mainView.getHomeView().getPlaylistsView(),businessFacade);
            mainView.registerControllers(homeController, songsController,signInSignUpController,playlistsController);
            mainView.start();
        });

    }
}
