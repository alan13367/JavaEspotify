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

/**
 *  Player entity:
 * plays a song, pauses or resumes it inside a Thread.
 * @author: Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 12/4/2022
 */

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

    /**
     * player constructor
     * @param position frame of the song
     * @param song song to play
     */
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

    /**
     * set a song to play
     * @param song song to play
     */
    public void setSong(Song song) {
        this.song = song;
    }

    /**
     * set current playing song
     * @param currentSong current playing song
     */
    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    // get song currently playing
    public Song getCurrentSong() {
        return currentSong;
    }

    /**
     * set a song to be played
     * @param song song object
     * @throws FileNotFoundException file not found
     * @throws JavaLayerException javalayer exception
     */
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

    /**
     * pauses a currently playing song and stores the frame where it has been paused.
     * @return position where the song is paused
     */
    public int pauseSong(){
        int position = audioDevice.getPosition() / 26;
        isPlaying = false;
        player.stop();
        return position;
    }

    /**
     * Runnable implementation. It makes the audio file sound inside a thread,
     *    allowing the rest of the program to work simultaneously.
     */
    @Override
    public void run() {
        try {
            player.play(position, (int) song.getDuration());

        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }

    }
}
