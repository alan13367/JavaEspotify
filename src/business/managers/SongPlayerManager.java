package business.managers;

import business.entities.Player;
import business.entities.Song;

import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * manager of the playlists, in charge of creating, deleting and editting playlists
 *  @author Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 *  @version 1.0
 *  @since 12/4/2022
 */

public class SongPlayerManager {

    private boolean isLoopSong;
    private boolean isLoopPlaylist;
    private boolean playingPlaylist;
    private boolean isPlaying;
    private Player player;
    private int framePosition;
    private Song song;
    private LinkedList<Song> songQueue;
    private final LinkedList<Song> playedSongs;

    /**
     * manager of the player
     */
    public SongPlayerManager() {
        playedSongs = new LinkedList<>();
        isPlaying = false;
    }

    /**
     * sets loop mode off or on for a song
     * @param loopingSong on/off indicator
     */
    public void setLoopingSong(boolean loopingSong) {
        isLoopSong = loopingSong;
    }

    /**
     * gets loop mode off or on for a song
     * @return if song is looping or not
     */
    public boolean isLoopingSong(){return isLoopSong;}

    /**
     * sets loop mode off or on for a playlist
     * @param loopPlaylist on/off indicator
     */
    public void setLoopPlaylist(boolean loopPlaylist) {
        isLoopPlaylist = loopPlaylist;
    }

    /**
     * gets loop mode off or on for a playlist
     * @return if playlist is looping or not
     */
    public boolean isLoopPlaylist() {
        return isLoopPlaylist;
    }

    /**
     * sets that user is playing a playlist
     * @param playingPlaylist indicator of a user playing a playlist or not
     */
    public void setPlayingPlaylist(boolean playingPlaylist) {
        this.playingPlaylist = playingPlaylist;
        isLoopSong = false;
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
     * indicates if a song is in a playlist
     * @return indicator that a song is in a playlist
     */
    public boolean isPlayingPlaylist() {
        return playingPlaylist;
    }

    /**
     * Will return whether the queue is empty or not
     * @return whether if the queue is empty
     */
    public boolean queueIsEmpty(){
        return songQueue.isEmpty();
    }

    /**
     * Method that will return if the playedSongs list is empty or not
     * @return whether if the playedSongsList is empty or not
     */
    public boolean playedSongsIsEmpty(){
        return playedSongs.isEmpty();
    }

    /**
     * gets currently playing song
     * @return song playing
     */
    public Song getCurrentSong() {
        return this.song;
    }

    /**
     * Clears the current song in the player and sets it to null
     */
    public void clearCurrentSong(){
        this.song = null;
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
        songQueue.addFirst(playedSongs.pollLast());
        framePosition = 0;
        this.song = songQueue.peek();
        player = new Player(framePosition,song);
        isPlaying = true;
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
    }


    /**
     * pauses currently playing song
     */
    public void pauseCurrentSong() {
        framePosition += player.pauseSong();
        isPlaying = false;
    }



    /**
     * Method that will totally stop the player
     */
    public void stopPlayer(){
        player.pauseSong();
        this.song = null;
        isPlaying = false;
    }


}



