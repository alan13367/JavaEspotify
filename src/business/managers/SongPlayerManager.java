package business.managers;

import business.entities.Song;

import javax.print.attribute.standard.Media;
import javax.sound.midi.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;


public class SongPlayerManager {

    private Clip clip;
    private String status;
    private AudioInputStream audioInputStream;
    private Sequencer player;
    private Media media;
   // private MediaPlayer



    // get a song's id and get its file
    public void playSong(Song song){
        if(song != null){


        }


    }

}
