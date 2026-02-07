package business.managers;

import business.audio.SimpleVolumeController;
import business.audio.VolumeChangeListener;
import business.audio.VolumeController;
import business.entities.Player;
import business.entities.Song;

import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * manager of the playlists, in charge of creating, deleting and editting playlists
 *  @author Alan Beltrán
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
    private final VolumeController volumeController;

    /**
     * manager of the player
     */
    public SongPlayerManager() {
        playedSongs = new LinkedList<>();
        isPlaying = false;
        this.volumeController = new SimpleVolumeController();
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
            player.setVolume(volumeController.getEffectiveVolume());
            isPlaying = true;
        }else if(isLoopPlaylist) {
            songQueue = new LinkedList<>(playedSongs);
            playedSongs.clear();
            this.song = songQueue.peek();
            framePosition = 0;
            player = new Player(framePosition,song);
            player.setVolume(volumeController.getEffectiveVolume());
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
        player.setVolume(volumeController.getEffectiveVolume());
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
            player.setVolume(volumeController.getEffectiveVolume());

        }
        else if (!isPlaying){
            player = new Player(framePosition,song);
            player.setVolume(volumeController.getEffectiveVolume());
        }else if(song != this.song){
            player.pauseSong();
            this.song= song;
            framePosition = 0;
            player = new Player(framePosition,song);
            player.setVolume(volumeController.getEffectiveVolume());
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
        player.setVolume(volumeController.getEffectiveVolume());
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

    /**
     * Seeks to a specific position in the current song
     * @param seconds position in seconds to seek to
     * @throws FileNotFoundException if song file not found
     */
    public void seekTo(int seconds) throws FileNotFoundException {
        if (song != null && isPlaying) {
            // Stop current playback
            player.pauseSong();

            // Convert seconds to frames (approximate: 38.46 fps = 1000/26)
            framePosition = seconds * 38;

            // Restart playback from new position
            player = new Player(framePosition, song);
            player.setVolume(volumeController.getEffectiveVolume());
            isPlaying = true;
        }
    }

    /**
     * Sets the volume level.
     * @param volume Volume level between 0.0 (silent) and 1.0 (maximum)
     */
    public void setVolume(float volume) {
        volumeController.setVolume(volume);
        if (player != null) {
            player.setVolume(volumeController.getEffectiveVolume());
        }
    }

    /**
     * Gets the current volume level.
     * @return Current volume level between 0.0 and 1.0
     */
    public float getVolume() {
        return volumeController.getVolume();
    }

    /**
     * Mutes the audio output.
     */
    public void mute() {
        volumeController.mute();
        if (player != null) {
            player.setVolume(volumeController.getEffectiveVolume());
        }
    }

    /**
     * Unmutes the audio output.
     */
    public void unmute() {
        volumeController.unmute();
        if (player != null) {
            player.setVolume(volumeController.getEffectiveVolume());
        }
    }

    /**
     * Checks if the audio is currently muted.
     * @return true if muted, false otherwise
     */
    public boolean isMuted() {
        return volumeController.isMuted();
    }

    /**
     * Adds a listener to be notified of volume changes.
     * @param listener The listener to add
     */
    public void addVolumeChangeListener(VolumeChangeListener listener) {
        volumeController.addVolumeChangeListener(listener);
    }

    /**
     * Removes a volume change listener.
     * @param listener The listener to remove
     */
    public void removeVolumeChangeListener(VolumeChangeListener listener) {
        volumeController.removeVolumeChangeListener(listener);
    }


}



