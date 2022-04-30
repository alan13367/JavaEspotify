package business.managers;

import business.entities.Song;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javax.sound.midi.*;
import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.Clip;
import java.io.File;

public class SongPlayerManager {

    private String status;
    private AudioInputStream audioInputStream;
    private Sequencer player;
    private Media media;
    private MediaPlayer mediaPlayer;

    // get a song's id and get its file
    public void playSong(Song song){
        if(song != null){
            media = new Media(new File(song.getFilepath()).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
    }

}
