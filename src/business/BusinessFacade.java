package business;

import business.entities.Song;

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
    void addSongToPlaylist(String playlistName,Song song);
    //void startPlayerThread(Song song);
   // void resumePlayer();
   // void pausePlayer();


}
