package persistence;

import business.entities.Song;

import java.util.HashMap;
import java.util.List;

/**
 * Interface to persist Song data in the database
 */

public interface SongDAO {
    /**
     * returns all songs stored in the database
     * @return list of songs
     */
    List<Song> loadSongs();

    /**
     * adds a new song to the database
     * @param song song to add
     */
    void addSong(Song song);

    /**
     * returns count of genres
     * @return count of genres
     */
    HashMap<String, Integer> getGenreCount();

    /**
     * deletes a song
     * @param song song to delete
     */
    void deleteSong(Song song);
}
