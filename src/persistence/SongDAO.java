package persistence;

import business.entities.Song;

import java.util.HashMap;
import java.util.List;

/**
 * Interface to persist Song data in the database
 * @author Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 12/4/2022
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

    /**
     * Deletes a song from all the playlists existing
     * @param song song to be deleted
     */
    void deleteSongFromAllPlaylists(Song song);
}
