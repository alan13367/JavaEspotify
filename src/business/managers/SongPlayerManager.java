package business.managers;

import business.entities.Song;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SongPlayerManager {

    // get a song's id and get its file
    public void playSong(Song song) throws JavaLayerException, FileNotFoundException {
        if(song != null){
            InputStream is = new FileInputStream(song.getFilepath());
            Player musicPlayer = new Player(is);
            musicPlayer.play();
        }
    }

    public void pauseSong(Song song){
        
    }

    public void resumeSong(Song song){

    }


}
