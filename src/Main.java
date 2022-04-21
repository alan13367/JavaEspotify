import business.BusinessFacade;
import business.ModelFacade;
import business.entities.Song;
import persistence.SQL.SQLSongDAO;
import presentation.MainView;
import presentation.controllers.HomeController;
import presentation.controllers.SongsController;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SQLSongDAO sqlSongDAO = new SQLSongDAO();
            sqlSongDAO.addSong(new Song("Highway to Hell","Highway to Hell","Rock","AC/DC","",210000,"alvarofeher"));
            BusinessFacade businessFacade = new ModelFacade();
            MainView mainView = new MainView();
            HomeController homeController = new HomeController(mainView.getHomeView(),businessFacade);
            SongsController songsController = new SongsController(mainView.getHomeView().getSongsView(),businessFacade);
            mainView.registerControllers(homeController,songsController);
            mainView.start();
        });

    }
}
