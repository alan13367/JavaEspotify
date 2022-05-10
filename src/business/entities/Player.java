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
    private Thread thread;

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void setSong(Song song) {
        this.song = song;
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

    public void startPlayerThread(Thread playerThread){
            playerThread = new Thread(this);
            playerThread.start();
    }

    public void playSong(Song song, Thread thread) throws FileNotFoundException, JavaLayerException {
        InputStream is = new FileInputStream(song.getFilepath());
        player = new AdvancedPlayer(is);
        player.play();
        System.out.println(song.getTitle()+" playing");
    }

    public void resumeSong(Thread playerThread) throws JavaLayerException {
       player.play();
    }

    public void pauseSong(){
        player.stop();
        player.setPlayBackListener(new PlaybackListener() {
            @Override
            public void playbackFinished(PlaybackEvent event) {
                pausedOnFrame = event.getFrame();
            }
        });
    }

    @Override
    public void run() {
        try {
            while(programInit){
                startPlayerThread(thread);
                if(playSong){
                    playSong(song, thread);
                }
                if(pauseSong){
                    pauseSong();
                }
            }
            //player.play();
        } catch (JavaLayerException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
