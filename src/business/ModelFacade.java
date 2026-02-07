package business;

import business.audio.VolumeChangeListener;
import business.entities.Playlist;
import business.entities.Song;
import business.managers.PlaylistManager;
import business.managers.SongManager;
import business.managers.SongPlayerManager;
import business.managers.UserManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Class used to Implement our {@link BusinessFacade} interface and related with all the needed managers in the program
 *
 * @author Alan Beltrán
 * @version 1.0
 * @since 10-04-2022
 */
public class ModelFacade implements BusinessFacade {
    private final SongManager songManager;
    private final UserManager userManager;
    private final PlaylistManager playlistManager;
    private final SongPlayerManager songPlayerManager;


    /**
     * Constructor of the class that will initialize all the needed managers
     */
    public ModelFacade() {
        this.songManager = new SongManager();
        this.userManager = new UserManager();
        this.playlistManager = new PlaylistManager();
        this.songPlayerManager = new SongPlayerManager();
    }


    @Override
    public List<Song> getSongs() {
        return songManager.getSongs();
    }

    @Override
    public void deleteAccount() {
        playlistManager.deletePlaylistsFromUser(getCurrentUser());
        songManager.deleteSongsFromUser(getCurrentUser());
        userManager.deleteAccount();
    }

    public void createUser(String username,String email,String password){
        userManager.createUser(username,email,password);
    }

    @Override
    public boolean login(String username, String password) {
        return userManager.logIn(username,password);
    }

    @Override
    public boolean checkEmailFormat(String email) {
        return userManager.checkEmailFormat(email);
    }

    @Override
    public boolean checkPasswordFormat(String password) {
        return userManager.checkPasswordFormat(password);
    }

    @Override
    public Song getSong(String title, String author) {
        return songManager.getSong(title,author);
    }

    @Override
    public void addSong(String title, String album, String genre, String author, String filepath, long duration) {
        songManager.addSong(title, album, genre, author,filepath, duration, getCurrentUser());
    }

    @Override
    public void deleteSong(String title, String author) {
        songManager.deleteSong(getSong(title,author));
    }

    @Override
    public String getCurrentUser() {
        return userManager.getUsername();
    }

    @Override
    public String getLyrics(String author, String title) {
        String s = songManager.getSongLyrics(author,title);
        if(s!=null){
            JsonObject object = JsonParser.parseString(s).getAsJsonObject();
            return object.get("lyrics").getAsString();
        }
        return null;
    }

    @Override
    public void createPlaylist(String name) {
        playlistManager.createPlaylist(name,getCurrentUser());
    }

    @Override
    public String[] getUserPlaylistsNames() {
        ArrayList<String> playlistsNames= new ArrayList<>();
        for (Playlist playlist:playlistManager.getUserPlaylists(getCurrentUser())){
            playlistsNames.add(playlist.getName());
        }
        return playlistsNames.toArray(new String[0]);
    }

    @Override
    public List<Playlist> getPlaylists() {
        return playlistManager.getPlaylists();
    }


    @Override
    public void addSongToPlaylist(String playlistName, Song song) {
        playlistManager.addSongToPlaylist(playlistName,getCurrentUser(),song);
    }

    @Override
    public void deletePlaylist(String name) {
        playlistManager.deletePlaylist(name,getCurrentUser());
    }

    @Override
    public ArrayList<String> getStatsGenres() {
        HashMap<String, Integer> map;
        map = songManager.getSongStats();
        Set<String> keySet = map.keySet();
        return new ArrayList<>(keySet);
    }

    @Override
    public ArrayList<Integer> getStatsValues() {
        HashMap<String, Integer> map;
        map = songManager.getSongStats();
        Collection<Integer> values = map.values();
        return new ArrayList<>(values);
    }

    @Override
    public void setLoopingPlaylist(boolean isLoop) {
        songPlayerManager.setLoopPlaylist(isLoop);
    }

    @Override
    public boolean isLoopingPlaylist() {
        return songPlayerManager.isLoopPlaylist();
    }

    @Override
    public void setLoopingSong(boolean loopingSong) {
        songPlayerManager.setLoopingSong(loopingSong);
    }

    @Override
    public boolean isLoopingSong() {
        return songPlayerManager.isLoopingSong();
    }

    @Override
    public void clearCurrentSong() {
        songPlayerManager.clearCurrentSong();
    }

    @Override
    public void seekTo(int seconds) throws FileNotFoundException {
        songPlayerManager.seekTo(seconds);
    }

    @Override
    public void pausePlayer() {
       songPlayerManager.pauseCurrentSong();
    }

    @Override
    public void playSong(String songTitle,String songAuthor) throws FileNotFoundException {
        Song song = getSong(songTitle, songAuthor);
        songPlayerManager.playSong(song);
    }

    @Override
    public void playNextSong() throws FileNotFoundException {
        songPlayerManager.playNextSong();
    }

    @Override
    public void playPrevSong() throws FileNotFoundException {
        songPlayerManager.playPrevSong();
    }

    @Override
    public List<Song> getSongsFromPlaylist(String name, String owner) {
       return playlistManager.getSongsFromPlaylist(name,owner);
    }

    @Override
    public void deleteSongFromPlaylist(String playlistName, String songName, String songAuthor) {
        playlistManager.deleteSongFromPlaylist(playlistName,getCurrentUser(),getSong(songName,songAuthor));
    }

    @Override
    public void addPlaylistToQueue(LinkedList<Song> playlist){
        songPlayerManager.addPlaylistToQueue(playlist);
    }

    @Override
    public void resumeSong() throws FileNotFoundException {
        songPlayerManager.resumeSong();
    }

    @Override
    public boolean isPlaying() {
        return songPlayerManager.isPlaying();
    }

    @Override
    public Song getCurrentSong(){
        return songPlayerManager.getCurrentSong();
    }

    @Override
    public void setPlayingPlaylist(boolean inPlaylist){
        songPlayerManager.setPlayingPlaylist(inPlaylist);
    }

    @Override
    public boolean queueIsEmpty() {
        return songPlayerManager.queueIsEmpty();
    }

    @Override
    public boolean playedSongsIsEmpty() {
        return songPlayerManager.playedSongsIsEmpty();
    }

    @Override
    public boolean isPlayingPlaylist(){
        return songPlayerManager.isPlayingPlaylist();
    }

    @Override
    public void logOut() {
        userManager.logOut();
    }

    @Override
    public void stopPlayer() {
        songPlayerManager.stopPlayer();
    }

    @Override
    public void setVolume(float volume) {
        songPlayerManager.setVolume(volume);
    }

    @Override
    public float getVolume() {
        return songPlayerManager.getVolume();
    }

    @Override
    public void mute() {
        songPlayerManager.mute();
    }

    @Override
    public void unmute() {
        songPlayerManager.unmute();
    }

    @Override
    public boolean isMuted() {
        return songPlayerManager.isMuted();
    }

    @Override
    public void addVolumeChangeListener(VolumeChangeListener listener) {
        songPlayerManager.addVolumeChangeListener(listener);
    }

    @Override
    public void removeVolumeChangeListener(VolumeChangeListener listener) {
        songPlayerManager.removeVolumeChangeListener(listener);
    }
}
