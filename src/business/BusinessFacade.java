package business;

import business.entities.Playlist;
import business.entities.Song;

import java.util.ArrayList;
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
    ArrayList<String> getStatsGenres();
    ArrayList<Integer> getStatsValues();
    void startPlayer(Song song);
    void resumePlayer();
    void pausePlayer();
    void playSong(Song song);

}
