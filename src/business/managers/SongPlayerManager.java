package business.managers;

import business.entities.Player;
import business.entities.Playlist;
import business.entities.Song;
import javazoom.jl.decoder.JavaLayerException;
import persistence.SQL.SQLSongDAO;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * manager of the playlists, in charge of creating, deleting and editting playlists
 * @author: Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 *  @version 1.0
 *  @since 12/4/2022
 */

public class SongPlayerManager {

   // private Player player = new Player(); // tengo dos players, he de usar solo uno!!!!
    private boolean isShuffle;
    private boolean isLoopSong;
    private boolean isLoopPlaylist;
    private boolean playNext;
    private boolean playPrev;
    private boolean inPlaylist;
    private boolean isPlaying;
    private Player player;
    private int position;
    private Song song;
    private List<Song> songQueue; // al terminar song, la quitas y va a played songs
    private List<Song> playedSongs;
    int currentSongId =0;
    Thread sliderThread;
    Song currentSong;

    /**
     * manager of the player
     */
    public SongPlayerManager() {
        playedSongs = new LinkedList<>();
        isPlaying = false;
    }

    /**
     * sets shuffle mode off or on
     * @param shuffle on/off indicator
     */
    public void setShuffle(boolean shuffle) {
        isShuffle = shuffle;
    }

    /**
     * sets loop mode off or on
     * @param loop on/off indicator
     */
    public void setLoop(boolean loop) {
        isLoopSong = loop;
    }

    /**
     * sets next on or off, plays next song if on
     * @param isNext on/off indicator
     */
    public void setNext(boolean isNext){playNext=isNext; }

    /**
     * sets next on or off, plays prev song if on
     * @param isPrev on/off indicator
     */
    public void setPrev(boolean isPrev){playPrev = isPrev; }

    /**
     * checks user is playing a playlist or an individual song
     * @param inPlaylist indicator of a user playing a playlist or a single song
     */
    public void setInPlaylist(boolean inPlaylist) {
        this.inPlaylist = inPlaylist;
    }

    /**
     * fill songQueue with a playlist
     * @param playlist playlist to add to queue
     */
    public void addPlaylistToQueue(List<Song> playlist){
        songQueue = playlist;
    }

    /**
     * indicator that a song is currently playing
     * @return indicator that a song is being played
     */
    public boolean isPlaying(){
        return isPlaying;
    }

    /**
     * get queue with songs to play
     * @return song queue
     */
    public List<Song> getSongQueue() {
        return songQueue;
    }

    /**
     * gets list of songs that have already been played
     * @return list of songs
     */
    public List<Song> getPlayedSongs() {
        return playedSongs;
    }

    /**
     * indicates if a song is in a playlist
     * @return indicator that a song is in a playlist
     */
    public boolean isInPlaylist() {
        return inPlaylist;
    }

    /**
     * method to play the next song from a playlist queue
     */
    public void playNextSong(){
        if(!isPlaying){
            isPlaying = true;
            if(currentSongId >= 0 && currentSongId < songQueue.size()-1){
                currentSongId++;
                //player.playSong(songQueue.get(currentSongId));
                position =0;
                this.song = songQueue.get(currentSongId);
                player = new Player(position, song);
                System.out.println("current song id: "+ currentSongId);
            } else if (currentSongId == songQueue.size()-1 ) {
                System.out.println("stop please");
                position =0;
                this.song = songQueue.get(0);
                player = new Player(position, song);
                currentSongId = 0;
            }
        }
    }

    /**
     * method to play the previous song from a playlist queue
     */
    public void playPrevSong(){
        if(!isPlaying){
            isPlaying = true;
            currentSongId--;
            if(currentSongId >= 0){
                position =0;
                this.song = songQueue.get(currentSongId);
                player = new Player(position, song);
            } else {
                position =0;
                this.song = songQueue.get(0);
                player = new Player(position, song);
                currentSongId = 0;
            }
        }

    }

    /**
     * plays a song
     * @param song song to be played
     */
    public void playSong(Song song){
        if(!isPlaying && this.song != song){
            this.song= song;
            position = 0;
            player = new Player(position,song);
            isPlaying = true;
        }
        else if (!isPlaying){
            player = new Player(position,song);
            isPlaying = true;
        }else if(song != this.song){
            player.pauseSong();
            this.song= song;
            position = 0;
            player = new Player(position,song);
            isPlaying = true;
        }

    }

    /**
     * resumes a currently playing song starting where it was paused
     */
    public void resumeSong(){
        isPlaying = true;
        player = new Player(position,song);
        if(!isPlaying){
            isPlaying = true;
            try {
                player.playSong(song);
               // player.setCurrentSong(song);
            } catch (FileNotFoundException | JavaLayerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * gets currently playing song
     * @return song playing
     */
    public Song getCurrentSong() {
        return this.song;
    }

    /**
     * plays currently song, used when loop mode is ON
     * @param song
     */
    public void playNextInLoop(Song song){
        if(!isPlaying){
            isPlaying = true;
            position = 0;
            player = new Player(position,song);
            isPlaying = true;
        }
    }

    /**
     * plays random song from song queue, used when shuffle mode is ON
     * @return
     */
    public Song getRandomSong(){
        return songQueue.get(new Random().nextInt(songQueue.size()));
    }

    /**
     * pauses currently playing song
     */
    public void pauseCurrentSong() {
        position += player.pauseSong();
        isPlaying = false;
    }


}



