package business.managers;

import business.entities.Song;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SongPlayerManager implements Runnable{
    private int pausedOnFrame =0;
    private AdvancedPlayer player;
   // private  Thread playerThread;

    public void startPlayer(Song song, Thread playerThread) throws JavaLayerException, FileNotFoundException{
        if(song != null){

            InputStream is = new FileInputStream(song.getFilepath());
            player = new AdvancedPlayer(is);
            playerThread = new Thread(this);
            playerThread.start();
            System.out.println(song.getTitle()+" playing");
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent event) {
                    pausedOnFrame = event.getFrame();
                }
            });
        }
    }

    public void pauseSong()  {
        //playerThread.stop();
        player.stop();
    }

    public void playSong(Song song){

    }

    public void resumeSong() throws JavaLayerException {
        player.play(pausedOnFrame,Integer.MAX_VALUE);
    }


    @Override
    public void run() {
        try {
            player.play();

        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }
}
