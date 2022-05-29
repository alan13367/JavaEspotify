package business;

import business.entities.Playlist;
import business.entities.Song;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *  The BusinessFacade Interface manages all of the Business layer systems to provide a reasonable
 *  interface to the rest of the program, particularly to layers above the business.
 *
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 10-04-2022
 */
public interface BusinessFacade {
    //Methods to be implemented in ModelFacade

    /**
     * Method to login to the program, will return true if successfully logged in and false if login failed.
     * @param username username
     * @param password password
     * @return true if successful, false otherwise
     */
    boolean login(String username,String password);

    /**
     * Method that after the corresponding validations will create a new User in the system.
     * @param username username of the user
     * @param email email of the user
     * @param password password for the user
     */
    void createUser(String username,String email,String password);

    /**
     * Method that will log out of the program
     */
    void logOut();

    /**
     * Method that will delete the account and also remove everything related to that user
     */
    void deleteAccount();

    /**
     * Method that will check if the email format enetered by the user is correct.
     * @param email email to be checked the format of
     * @return true if the format is right and false if is wrong
     */
    boolean checkEmailFormat(String email);

    /**
     * Method that will check the format of the password entered by the user is correct.
     * @param password password to check the format of
     * @return true if format is correct false if is wrong
     */
    boolean checkPasswordFormat(String password);

    /**
     * Method that will return the user that is currently logged in the system.
     * @return username of the user logged in the system
     */
    String getCurrentUser();

    /**
     * Method that will get the list of songs in the system.
     * @return List with all the songs currently in the system
     */
    List<Song> getSongs();

    /**
     * Method that will get the Song by its title and author
     * @param title title of the song
     * @param author author of the song
     * @return Song with the entered paramenters
     */
    Song getSong(String title,String author);

    /**
     * Method that will add a Song to the system
     * @param title title of the song
     * @param album name of the album the song belongs to
     * @param genre genre of the song
     * @param author author of the song
     * @param filepath filepath of the song
     * @param duration duration of the song
     */
    void addSong(String title, String album, String genre, String author, String filepath, long duration);

    /**
     * Method that will delete the song with the title and the author given
     * @param title title of the song to delete
     * @param author author of the song to delete
     */
    void deleteSong(String title,String author);

    /**
     * Method that will get the lyrics from the song with the given title and author given.
     * @param author author of the song
     * @param title title of the song
     * @return Lyrics of the song
     */
    String getLyrics(String author,String title);

    /**
     * Method that will create a Playlist for the user logged in the system.
     * @param name name of the playlist to create
     */
    void createPlaylist(String name);

    /**
     * Method that will return a String Array with the names of the playlists created by the user
     * @return Array of Strings with the names of the playlists
     */
    String[] getUserPlaylistsNames();

    /**
     * Method will get all the playlists of they systems
     * @return List of all the Playlists
     */
    List<Playlist> getPlaylists();

    /**
     * Method that will add a certain song to a playlist of the currently logged in user
     * @param playlistName name of the playlist to add the song to
     * @param song song to be added
     */
    void addSongToPlaylist(String playlistName,Song song);

    /**
     * Method that will delete a playlist of the logged-in user
     * @param name of the playlist to delete
     */
    void deletePlaylist(String name);

    /**
     * Method that will get an ArrayList of the genres of the songs
     * @return List with the names of Genres
     */
    ArrayList<String> getStatsGenres();

    /**
     * Method that will get the values of each genre
     * @return List with the values of each genre
     */
    ArrayList<Integer> getStatsValues();


    void setShuffle(boolean isShuffle);


    void setLoopingPlaylist(boolean isLoop);

    /**
     * pauses the player and keeps the frame where it is paused
     */
    void pausePlayer();

    /**
     * plays a song
     * @param songTitle title of the song
     * @param songAuthor author of the song
     */
    void playSong(String songTitle,String songAuthor) throws FileNotFoundException;

    /**
     * play next song from queue
     * @throws FileNotFoundException if the file was not found
     */
    void playNextSong() throws FileNotFoundException;

    /**
     * play previous song from queue
     * @throws FileNotFoundException if the file was not found
     */
    void playPrevSong() throws FileNotFoundException;

    /**
     * play random song from queue
     * @throws FileNotFoundException if the file was not found
     */
    void playRandomSong() throws FileNotFoundException;

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
    void addPlaylistToQueue(LinkedList<Song> playlist);

    /**
     * resumes a song from the frame where it was paused
     * @throws FileNotFoundException if the file was not found
     */
    void resumeSong() throws FileNotFoundException;

    /**
     * checks that a song is playing
     * @return indicates that a song is playing
     */
    boolean isPlaying();

    /**
     * plays next song in loop
     * @param song song to play
     * @throws FileNotFoundException if the file was not found
     */
    void playNextInLoop(Song song) throws FileNotFoundException;

    /**
     * gets current playing song
     * @return song
     */
    Song getCurrentSong();

    /**
     * check if the user is playing a song in a playlist
     * @return indicator that the user is playing a song in a playlist
     */
    boolean isPlayingPlaylist();

    /**
     * set true if the user is playing a song inside a playlist
     * @param inPlaylist indicator that the user is playing a song in a playlist
     */
    void setPlayingPlaylist(boolean inPlaylist);

    boolean queueIsEmpty();

    void stopPlayer();

    boolean isLoopingPlaylist();
}
