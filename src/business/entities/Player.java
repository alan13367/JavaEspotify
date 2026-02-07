package business.entities;

import business.utils.VolumeAwareAudioDevice;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Player entity:
 * plays a song, pauses or resumes it inside a Thread.
 * @author Alan Beltrán
 * @version 1.0
 * @since 12/4/2022
 */

public class Player extends Thread {
    private final VolumeAwareAudioDevice audioDevice;
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
        // FactoryRegistry r = FactoryRegistry.systemRegistry();
        InputStream is = null;
        try {
            audioDevice = new VolumeAwareAudioDevice();
            is = new BufferedInputStream(new FileInputStream(song.getFilepath()));
            player = new AdvancedPlayer(is, audioDevice);
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
        this.start();
    }

    /**
     * Sets the volume for the current playback.
     * @param volume Volume level from 0.0 to 1.0
     */
    public void setVolume(float volume) {
        if (audioDevice != null) {
            audioDevice.setVolume(volume);
        }
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
