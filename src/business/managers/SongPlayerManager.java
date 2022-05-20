package business.managers;

import business.entities.Player;
import business.entities.Song;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SongPlayerManager {

   // private Player player = new Player(); // tengo dos players, he de usar solo uno!!!!
    private boolean isShuffle;
    private boolean isLoopSong;
    private boolean isLoopPlaylist;
    private boolean playNext;
    private boolean playPrev;
    private boolean isPlaying;
    Player player;
    List<Song> songQueue; // al terminar song, la quitas y va a played songs
    List<Song> playedSongs;
    int currentSong =0;

    public SongPlayerManager() {
        this.player = new Player();
        playedSongs = new LinkedList<>();
        isPlaying = false;
    }

    public void setShuffle(boolean shuffle) {
        isShuffle = shuffle;
    }
    public void setLoop(boolean loop) {
        isLoopSong = loop;
    }
    public void setNext(boolean isNext){playNext=isNext; }
    public void setPrev(boolean isPrev){playPrev = isPrev; }

    // fill songQueue with a playlist
    public void addPlaylistToQueue(List<Song> playlist){
        songQueue = playlist;
    }

    public boolean isPlaying(){
        return isPlaying;
    }

    public List<Song> getSongQueue() {
        return songQueue;
    }

    public List<Song> getPlayedSongs() {
        return playedSongs;
    }

    public int getCurrentSong() {
        return currentSong;
    }

    public void playNextSong(){
        try {
            player.playSong(songQueue.get(currentSong++));
        } catch (FileNotFoundException | JavaLayerException e) {
            throw new RuntimeException(e);
        }

    }

    public void playSong(Song song){
        if(!isPlaying){
            isPlaying = true;
            try {
                player.playSong(song);
            } catch (FileNotFoundException | JavaLayerException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public Song getRandomSong(){
        return songQueue.get(new Random().nextInt(songQueue.size()));
    }

    public void pauseCurrentSong() {
        player.pauseSong();
        isPlaying = false;
    }

}



