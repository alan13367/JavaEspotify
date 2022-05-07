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

    public void addSong(String title, String author, String genre, String album, String filepath,long duration, String owner){
        Song song = new Song(title,  author,  genre,  album, filepath, duration,  owner);
        songDAO.addSong(song);
        songs.add(song);
    }


    public void deleteSong(Song song){
        songDAO.deleteSong(song);
        songs.remove(song);
    }

    public void deleteSongsFromUser(String username){
        for(Song song:songs){
            if (song.getOwner().equals(username)){
                songDAO.deleteSong(song);
            }
        }

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
