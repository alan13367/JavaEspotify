package business;

import business.entities.Playlist;
import business.entities.Song;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public interface BusinessFacade {
    //Methods to be implemented in ModelFacade
    boolean login(String username,String password);
    void createUser(String username,String email,String password);
    void logOut();
    void deleteAccount();
    boolean checkEmailFormat(String email);
    boolean checkPasswordFormat(String password);
    String getCurrentUser();
    List<Song> getSongs();
    Song getSong(String title,String author);
    void addSong(String title, String album, String genre, String author, String filepath, long duration);
    void deleteSong(String title,String author);
    String getLyrics(String author,String title);
    void createPlaylist(String name);
    String[] getUserPlaylistsNames();
    List<Playlist> getPlaylists();
    void addSongToPlaylist(String playlistName,Song song);
    void deletePlaylist(String name,String owner);
    ArrayList<String> getStatsGenres();
    ArrayList<Integer> getStatsValues();
    void setShuffle(boolean isShuffle);
    void setLoop(boolean isLoop);

    /**
     * pauses the player and keeps the frame where it is paused
     */
    void pausePlayer();

    /**
     * plays a song
     * @param songTitle title of the song
     * @param songAuthor author of the song
     */
    void playSong(String songTitle,String songAuthor);

    /**
     * play next song from queue
     */
    void playNextSong();

    /**
     * play previous song from queue
     */
    void playPrevSong();

    /**
     * play random song from queue
     */
    void playRandomSong();

    /**
     * gets all songs from a playlist
     * @param name playlist name
     * @param owner playlist owner
     * @return list of songs
     */
    List<Song> getSongsFromPlaylist(String name, String owner);

    /**
     * deletes a song from playlist
     * @param playlistName name of playlist
     * @param songName name of song to delete
     * @param songAuthor name of song author
     */
    void deleteSongFromPlaylist(String playlistName,String songName,String songAuthor);

    /**
     * adds all the songs from a playlist to the queue
     * @param playlist playlist to add to queue
     */
    void addPlaylistToQueue(List<Song> playlist);

    /**
     * resumes a song from the frame where it was paused
     */
    void resumeSong();

    /**
     * checks that a song is playing
     * @return indicates that a song is playing
     */
    boolean isPlaying();

    /**
     * plays next song in loop
     * @param song song to play
     */
    void playNextInLoop(Song song);

    /**
     * gets current playing song
     * @return song
     */
    Song getCurrentSong();

    /**
     * check if the user is playing a song in a playlist
     * @return indicator that the user is playing a song in a playlist
     */
    boolean getInPlaylist();

    /**
     * set true if the user is playing a song inside a playlist
     * @param inPlaylist indicator that the user is playing a song in a playlist
     */
    void setInPlaylist(boolean inPlaylist);

}
