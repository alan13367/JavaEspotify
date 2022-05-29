package persistence;

import business.entities.Playlist;
import business.entities.Song;

import java.util.LinkedList;
import java.util.List;

/**
 * Interface to persist Playlist data in the database
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 12/4/2022
 *
 */

public interface PlaylistDAO {
    /**
     * adds a new playlist in the database
     * @param playlist playlist to add
     */
    void addPlaylist(Playlist playlist);

    /**
     * deletes an existent playlist from the database
     * @param playlist playlist to delete
     */
    void deletePlayList(Playlist playlist);

    /**
     * loads all stored playlists
     * @return list of playlists
     */
    List<Playlist> loadPlaylists();

    /**
     * adds a song to a playlist
     * @param song song to add
     * @param playlist playlist where the song is added
     */
    void addSongToPlaylist(Song song, Playlist playlist);

    /**
     * deletes a song from a playlist
     * @param song song to delete
     * @param playlist playlist where the song is deleted
     */
    void deleteSongFromPlaylist(Song song, Playlist playlist);

    /**
     * gets all songs from a playlist
     * @param name name of the playlist
     * @param owner owner of the playlist
     * @return list of songs
     */
    LinkedList<Song> getSongsFromPlaylists(String name, String owner);
}
