package business.managers;

import business.entities.Player;
import business.entities.Playlist;
import business.entities.Song;
import javazoom.jl.decoder.JavaLayerException;
import persistence.SQL.SQLSongDAO;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class SongPlayerManager {

   // private Player player = new Player(); // tengo dos players, he de usar solo uno!!!!
    private boolean isShuffle;
    private boolean isLoopSong;
    private boolean isLoopPlaylist;
    private boolean playNext;
    private boolean playPrev;
    Player player;
    PlaylistManager manager;
    LinkedList<Song> songQueue; // al terminar song, la quitas y va a played songs
    List<Song> playedSongs = new LinkedList<>();
    int currentSong =0;

    public void setShuffle(boolean shuffle) {
        isShuffle = shuffle;
    }
    public void setLoop(boolean loop) {
        isLoopSong = loop;
    }
    public void setNext(boolean isNext){playNext=isNext; }
    public void setPrev(boolean isPrev){playPrev = isPrev; }

    // fill songQueue with a playlist
    public void addPlaylistToQueue(String name, String owner){
        songQueue = manager.getSongsFromPlaylist(name,owner);
    }

    public void playPlaylist(Playlist playlist)   {


    }


    public void playSong(Song song){
        try {
            player.playSong(song);
            songQueue.remove(song);
            playedSongs.add(song);
        } catch (FileNotFoundException | JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }


    public Song getRandomSong(Playlist playlist){
        Random random = new Random();
        LinkedList<Song>songs = manager.getSongsFromPlaylist(playlist.getName(),playlist.getOwner());
        int next;
        next = random.nextInt(songs.size());
        return songs.get(next);
    }


    // when a next/prev arrow is pressed
    /*public void playNextSong(Song song, Thread thread) throws FileNotFoundException, JavaLayerException {
        if(isShuffle){
            player.playSong(getRandomSong());
        } else if (isLoopSong) {
            player.playSong(song);
        }
    }*/
}



