package persistence;

import business.entities.Song;

import java.util.List;

public interface SongDAO {
    List<Song> loadSongs();
    Song addSong(Song song);
    Song deleteSong(Song song);
}
