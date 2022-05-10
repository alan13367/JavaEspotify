package business.managers;

import business.entities.Player;
import business.entities.Playlist;
import business.entities.Song;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;

public class SongPlayerManager {

    private Player player;
    private Thread thread;
    private boolean isShuffle;
    private boolean isLoop;

    public void setShuffle(boolean shuffle) {
        isShuffle = shuffle;
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
    }

    public void playSong(Song song) throws FileNotFoundException, JavaLayerException {
        if(isLoop){
            player.playSong(song,thread);
            playSong(song);
        }
        if(isShuffle){

        }
    }

    public void playPlaylist(Playlist playlist){
        if(isLoop){

        }
        if(isShuffle){

        }
    }
}



