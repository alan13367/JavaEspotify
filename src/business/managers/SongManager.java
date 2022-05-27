package business.managers;

import business.SongLyricsAPI;
import business.entities.Song;
import persistence.SQL.SQLSongDAO;
import persistence.SongDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SongManager {
    private List<Song> songs;
    private SongDAO songDAO;
    private SongLyricsAPI songLyricsAPI;

    /**
     * song manager constructor
     */
    public SongManager() {
        this.songLyricsAPI = new SongLyricsAPI();
        this.songDAO = new SQLSongDAO();
        songs = songDAO.loadSongs();
    }

    /**
     * get all songs
     * @return list of songs
     */
    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     * add new song to the database
     * @param title song name
     * @param album album containing the song
     * @param genre song genre
     * @param author song author
     * @param filepath filepath where the mp3 file is stored
     * @param duration duration in milliseconds
     * @param owner owner of the song (user who added the song)
     */
    public void addSong(String title, String album, String genre, String author, String filepath, long duration, String owner){
        Song song = new Song(title,  album,  genre,  author, filepath, duration,  owner);
        songDAO.addSong(song);
        songs.add(song);
    }

    /**
     * delete song
     * @param song song to delete
     */
    public void deleteSong(Song song){
        songDAO.deleteSong(song);
        songs.remove(song);
    }

    /**
     * delete all songs added by a user
     * @param username name of user owning songs
     */
    public void deleteSongsFromUser(String username){
        for(Song song:songs){
            if (song.getOwner().equals(username)){
                songDAO.deleteSong(song);
            }
        }

    }

    /**
     * get a song
     * @param title title of song
     * @param author author of the song
     * @return found song with entered title and author
     */
    public Song getSong(String title, String author) {
        Song song = null;
        for (Song song1:songs){
            if (song1.getTitle().equals(title) && song1.getAuthor().equals(author)){
                song = song1;
            }
        }
        return song;
    }

    /**
     * get lyrics of the song
     * @param artist author of the song
     * @param title name of the song
     * @return lyrics from API
     */
    public String getSongLyrics(String artist,String title) {
        return songLyricsAPI.getLyricsJson(artist,title);
    }

    /**
     * return statistics of songs
     * @return count of genres from all songs
     */
    public HashMap<String, Integer> getSongStats() {
        return songDAO.getGenreCount();
    }

}
