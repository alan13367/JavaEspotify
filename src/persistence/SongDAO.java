package persistence;

import business.entities.Song;

import java.util.HashMap;
import java.util.List;

public interface SongDAO {
    List<Song> loadSongs();
    void addSong(Song song);
    HashMap<String, Integer> getGenreCount();
    void deleteSong(Song song);
}
