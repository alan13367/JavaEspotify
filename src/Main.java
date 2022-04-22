import business.BusinessFacade;
import business.ModelFacade;
import business.SongLyricsAPI;
import business.entities.Song;
import com.mysql.cj.log.Log;
import persistence.SQL.SQLSongDAO;
import presentation.MainView;
import presentation.controllers.HomeController;
import presentation.controllers.SongsController;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BusinessFacade businessFacade = new ModelFacade();
            MainView mainView = new MainView();
            HomeController homeController = new HomeController(mainView.getHomeView(), businessFacade);
            SongsController songsController = new SongsController(mainView.getHomeView().getSongsView(), businessFacade);
            mainView.registerControllers(homeController, songsController);
            mainView.start();
        });

        SongLyricsAPI songLyricsAPI = new SongLyricsAPI();
        System.out.println(songLyricsAPI.getLyricsJson("Bad+Bunny","Yonaguni"));
    }
}
