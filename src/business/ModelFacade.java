package business;

import business.entities.Player;
import business.entities.Playlist;
import business.entities.Song;
import business.managers.*;
import com.google.gson.*;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;
import java.util.*;

public class ModelFacade implements BusinessFacade {
    private final SongManager songManager;
    private final UserManager userManager;
    private final PlaylistManager playlistManager;
    private final SongPlayerManager songPlayerManager;
    private Thread playerThread;
    private Player player;
    //Managers

    public ModelFacade() {
        this.songManager = new SongManager();
        this.userManager = new UserManager();
        this.playlistManager = new PlaylistManager();
        this.songPlayerManager = new SongPlayerManager();
        this.player = new Player();
    }

    @Override
    public List<Song> getSongs() {
        return songManager.getSongs();
    }

    @Override
    public void deleteAccount() {
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
        songManager.addSong(title, author, genre, album,filepath, duration, getCurrentUser());
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
        JsonParser jsonParser = new JsonParser();
        String s = songManager.getSongLyrics(author,title);
        if(s!=null){
            JsonObject object = jsonParser.parse(s).getAsJsonObject();
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
        //Missing implementation of adding song to a user playlist
        playlistManager.addSongToPlaylist(playlistName,getCurrentUser(),song);
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

    public void setShuffle(boolean isShuffle){
        songPlayerManager.setShuffle(isShuffle);
    }
    public void setLoop(boolean isLoop){
        songPlayerManager.setLoop(isLoop);
    }

    public void playNextSong(Song currentSong){ // play next song when next or prev is pressed
        try {
            songPlayerManager.playNextSong(currentSong, playerThread);
        } catch (FileNotFoundException | JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void pausePlayer() {
        player.pauseSong();
    }

    @Override
    public void playSong(Song song) {
        try {
            player.playSong(song, playerThread);
        } catch (FileNotFoundException | JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logOut() {
        userManager.logOut();
    }

}
