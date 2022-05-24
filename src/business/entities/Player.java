package business.entities;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.swing.*;
import javax.lang.model.element.ElementVisitor;
import java.io.*;

// plays a song, pauses or resumes it.

public class Player extends Thread {
    private AudioDevice audioDevice;
    private AdvancedPlayer player;
    private int pausedOnFrame;
    private int position;
    private boolean programInit;
    private boolean isPlayingSong;
    private boolean pauseSong;
    private Song song;
    private Song currentSong;
    private int songIndex;
    boolean isPlaying = false;
    //private final Thread playerThread = new Thread();


    public Player(int position, Song song) {
        this.position = position;
        this.song = song;
        FactoryRegistry r = FactoryRegistry.systemRegistry();
        InputStream is;
        try {
            audioDevice = r.createAudioDevice();
            is = new BufferedInputStream(new FileInputStream(song.getFilepath()));
            player = new AdvancedPlayer(is,audioDevice);
        } catch (JavaLayerException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        player.setPlayBackListener(new PlaybackListener() {
            @Override
            public void playbackStarted(PlaybackEvent evt) {

            }

            @Override
            public void playbackFinished(PlaybackEvent evt) {

            }
        });
        this.start();
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void playSong(Song song) throws FileNotFoundException, JavaLayerException {
        setCurrentSong(song);
        isPlaying = true;

        System.out.println(song.getTitle()+" playing");
        player.setPlayBackListener(new PlaybackListener() {
            @Override
            public void playbackFinished(PlaybackEvent event) {
                pausedOnFrame = event.getFrame();
            }
        });
    }

    // iterar todos los frames, cuando de error es q ha acabado

    public int pauseSong(){
        int position = audioDevice.getPosition() / 26;
        isPlaying = false;
        player.stop();
        return position;
    }

    public void moveSlider(JSlider slider) {
        int nValue = slider.getValue();
        int nMaxValue = slider.getMaximum();
        do {
            nValue++;
            slider.setValue(nValue);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            slider.setValue(nValue);
            System.out.println("Value is " + nValue);
            System.out.println("Max value is " + nMaxValue);
        }while(nValue<nMaxValue);
    }


    public void setProgramInit(boolean programInit) {
        this.programInit = programInit;
    }
    public void setIsPlayingTrue() {
        this.isPlayingSong = true;
    }
    public void setPauseSong(boolean pauseSong) {
        this.pauseSong = pauseSong;
    }


    @Override
    public void run() {
        try {
            player.play(position, (int) song.getDuration());

        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }

    }
}
