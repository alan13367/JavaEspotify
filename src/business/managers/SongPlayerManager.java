package business.managers;

import business.entities.Player;
import business.entities.Playlist;
import business.entities.Song;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;

public class SongPlayerManager {

    private Player player = new Player();
    private boolean isShuffle;
    private boolean isLoop;

    public void setShuffle(boolean shuffle) {
        isShuffle = shuffle;
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
    }

    public void playSong(Song song) throws FileNotFoundException, JavaLayerException {

        player.setPlaySong(true);
        // meter funciones para get next song dependiendo de si hay sguffle o loop
    }

    public void playPlaylist(Playlist playlist){
        // meter funciones para get next song dependiendo de si hay sguffle o loop
    }
}



