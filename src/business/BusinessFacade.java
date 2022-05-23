package business;

import business.entities.Playlist;
import business.entities.Song;

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
    void pausePlayer();
    void playSong(String songTitle,String songAuthor);
    void playNextSong();
    void playPrevSong();
    void playRandomSong();
    List<Song> getSongsFromPlaylist(String name, String owner);
    void deleteSongFromPlaylist(String playlistName,String songName,String songAuthor);
    void addPlaylistToQueue(List<Song> playlist);
    boolean isPlaying();
    void playNextInLoop();
    Song getCurrentSong();
}
