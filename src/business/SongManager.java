package business;

import business.entities.Song;
import persistence.SongDAO;

import java.net.PortUnreachableException;
import java.util.ArrayList;

public class SongManager {
    private ArrayList<Song> songs;
    private SongDAO songDAO;

    public void addSong(Song song){
        songs.add(song);
    }

    public void deleteSong(Song song){
        songs.remove(song);
    }
}
