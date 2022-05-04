package business.managers;

import business.entities.Song;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SongPlayerManager {
    private int pausedOnFrame =0;
    // get a song's id and get its file
    public void playSong(Song song) throws JavaLayerException, FileNotFoundException {
        if(song != null){
            InputStream is = new FileInputStream(song.getFilepath());
            AdvancedPlayer player = new AdvancedPlayer(is);
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent event) {
                    pausedOnFrame = event.getFrame();
                }
            });
            player.play();

        }
    }

    public void pauseSong(Song song){
        
    }

    public void resumeSong(Song song){

    }


}
