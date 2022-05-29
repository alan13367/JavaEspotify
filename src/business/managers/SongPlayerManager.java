package business.managers;

import business.entities.Player;
import business.entities.Song;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * manager of the playlists, in charge of creating, deleting and editting playlists
 *  @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 *  @version 1.0
 *  @since 12/4/2022
 */

public class SongPlayerManager {

   // private Player player = new Player(); // tengo dos players, he de usar solo uno!!!!
    private boolean isShuffle;
    private boolean isLoopSong;
    private boolean isLoopPlaylist;
    private boolean playingPlaylist;
    private boolean isPlaying;
    private Player player;
    private int framePosition;
    private Song song;
    private LinkedList<Song> songQueue; // al terminar song, la quitas y va a played songs
    private List<Song> playedSongs;
    int currentSongId =0;

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


    public void setLoopPlaylist(boolean loopPlaylist) {
        isLoopPlaylist = loopPlaylist;
    }

    public boolean isLoopPlaylist() {
        return isLoopPlaylist;
    }

    /**
     * checks user is playing a playlist or an individual song
     * @param playingPlaylist indicator of a user playing a playlist or a single song
     */
    public void setPlayingPlaylist(boolean playingPlaylist) {
        this.playingPlaylist = playingPlaylist;
    }

    /**
     * fill songQueue with a playlist
     * @param playlist playlist to add to queue
     */
    public void addPlaylistToQueue(LinkedList<Song> playlist){
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
    public boolean isPlayingPlaylist() {
        return playingPlaylist;
    }




    /**
     * method to play the next song from a playlist queue
     */
    public void playNextSong() throws FileNotFoundException {

        playedSongs.add(songQueue.poll());
        if(!songQueue.isEmpty()){
            this.song = songQueue.peek();
            framePosition = 0;
            player = new Player(framePosition,song);
            isPlaying = true;
        }else if(isLoopPlaylist) {
            songQueue = new LinkedList<>(playedSongs);
            playedSongs.clear();
            this.song = songQueue.peek();
            framePosition = 0;
            player = new Player(framePosition,song);
            isPlaying = true;
        }else {
            playedSongs.clear();
            isPlaying = false;
            playingPlaylist = false;
        }


    }

    /**
     * method to play the previous song from a playlist queue
     */
    public void playPrevSong() throws FileNotFoundException {
        if(!isPlaying){
            isPlaying = true;
            currentSongId--;
            if(currentSongId >= 0){
                framePosition =0;
                this.song = songQueue.get(currentSongId);
                player = new Player(framePosition, song);
            } else {
                framePosition =0;
                this.song = songQueue.get(0);
                player = new Player(framePosition, song);
                currentSongId = 0;
            }
        }

    }

    /**
     * plays a song
     * @param song song to be played
     */
    public void playSong(Song song) throws FileNotFoundException {
        if(!isPlaying && this.song != song){
            this.song= song;
            framePosition = 0;
            player = new Player(framePosition,song);

        }
        else if (!isPlaying){
            player = new Player(framePosition,song);
        }else if(song != this.song){
            player.pauseSong();
            this.song= song;
            framePosition = 0;
            player = new Player(framePosition,song);
        }

        if(player != null){
            isPlaying = true;
        }

    }

    /**
     * resumes a currently playing song starting where it was paused
     */
    public void resumeSong() throws FileNotFoundException {
        isPlaying = true;
        player = new Player(framePosition,song);
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
    public void playNextInLoop(Song song) throws FileNotFoundException {
        if(!isPlaying){
            isPlaying = true;
            framePosition = 0;
            player = new Player(framePosition,song);
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
        framePosition += player.pauseSong();
        isPlaying = false;
    }

    public boolean queueIsEmpty(){
        return songQueue.isEmpty();
    }

    public void stopPlayer(){
        player.pauseSong();
        this.song = null;
    }


}



