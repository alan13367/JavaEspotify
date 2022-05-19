package business.entities;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

// plays a song, pauses or resumes it.

public class Player implements Runnable {
    private AdvancedPlayer player;
    private int pausedOnFrame;
    private boolean programInit;
    private boolean playSong;
    private boolean pauseSong;
    private Song song;
    private int songIndex;
    //private final Thread playerThread = new Thread();


    public void setSong(Song song) {
        this.song = song;
    }

    public void playSong(Song song) throws FileNotFoundException, JavaLayerException {
        InputStream is = new FileInputStream(song.getFilepath());
        player = new AdvancedPlayer(is);
        Thread playerThread = new Thread(this);
        playerThread.start();
        System.out.println(song.getTitle()+" playing");
        player.setPlayBackListener(new PlaybackListener() {
            @Override
            public void playbackFinished(PlaybackEvent event) {
                pausedOnFrame = event.getFrame();
            }
        });
    }

    public void pauseSong(){
        player.stop();
    }

    public void setProgramInit(boolean programInit) {
        this.programInit = programInit;
    }


    public void setPlaySong(boolean playSong) {
        this.playSong = playSong;
    }


    public void setPauseSong(boolean pauseSong) {
        this.pauseSong = pauseSong;
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
