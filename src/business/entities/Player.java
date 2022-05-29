package business.entities;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.*;

/**
 * Player entity:
 * plays a song, pauses or resumes it inside a Thread.
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 12/4/2022
 */

public class Player extends Thread {
    private final AudioDevice audioDevice;
    private final AdvancedPlayer player;
    private final int position;
    private final Song song;

    /**
     * player constructor
     * @param position frame of the song
     * @param song song to play
     */
    public Player(int position, Song song) throws FileNotFoundException {
        this.position = position;
        this.song = song;
        FactoryRegistry r = FactoryRegistry.systemRegistry();
        InputStream is;
        try {
            audioDevice = r.createAudioDevice();
            is = new BufferedInputStream(new FileInputStream(song.getFilepath()));
            player = new AdvancedPlayer(is,audioDevice);
        } catch (JavaLayerException e) {
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
     * pauses a currently playing song and stores the frame where it has been paused.
     * @return position where the song is paused
     */
    public int pauseSong(){
        int position = 0;
        if(audioDevice != null){
            position = audioDevice.getPosition() / 26;
            player.close();
        }
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
