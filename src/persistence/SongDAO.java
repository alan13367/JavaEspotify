package persistence;

import business.entities.Song;

import java.util.List;

public interface SongDAO {
    List<Song> loadSongs();
    void addSong(Song song);
    void deleteSong();
}
