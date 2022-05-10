package business.managers;

import persistence.SQL.SQLSongDAO;
import persistence.SongDAO;

import java.util.HashMap;
import java.util.List;

public class StatisticsManager {
    private SongDAO songDAO;


    public StatisticsManager() {
        this.songDAO = new SQLSongDAO();
        songDAO.getGenreCount();
    }

    public HashMap<String, Integer> getSongStats() {
        return songDAO.getGenreCount();
    }


}
