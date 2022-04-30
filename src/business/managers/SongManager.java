package business.managers;

import business.SongLyricsAPI;
import business.entities.Song;
import persistence.SQL.SQLSongDAO;
import persistence.SongDAO;

import java.util.ArrayList;
import java.util.List;

public class SongManager {
    private List<Song> songs;
    private SongDAO songDAO;
    private SongLyricsAPI songLyricsAPI;

    public SongManager() {
        this.songLyricsAPI = new SongLyricsAPI();
        this.songDAO = new SQLSongDAO();
        songs = songDAO.loadSongs();
    }

    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public void addSong(Song song){
        songs.add(song);
    }

    public void deleteSong(Song song){
        songs.remove(song);
        songDAO.deleteSong(song);
    }

    public Song getSong(String title, String author) {
        Song song = null;
        for (Song song1:songs){
            if (song1.getTitle().equals(title) && song1.getAuthor().equals(author)){
                song = song1;
            }
        }
        return song;
    }

    public String getSongLyrics(String artist,String title) {
        return songLyricsAPI.getLyricsJson(artist,title);
    }
}
