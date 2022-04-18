package business.managers;

import business.entities.Song;
import persistence.SQL.SQLSongDAO;
import persistence.SongDAO;

import java.util.List;

public class SongManager {
    private List<Song> songs;
    private SongDAO songDAO;

    public SongManager() {
        this.songDAO = new SQLSongDAO();
        songs = songDAO.loadSongs();
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song){
        songs.add(song);
    }

    public void deleteSong(Song song){
        songs.remove(song);
    }

    public Song getSong(String name, String author) {
        Song song = null;
        for (Song song1:songs){
            if (song1.getTitle().equals(name) && song1.getAuthor().equals(author)){
                song = song1;
            }
        }
        return song;
    }
}
