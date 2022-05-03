package business;

import business.entities.Song;
import business.managers.PlaylistManager;
import business.managers.SongManager;
import business.managers.UserManager;
import com.google.gson.*;

import java.util.List;

public class ModelFacade implements BusinessFacade {
    private final SongManager songManager;
    private final UserManager userManager;
    private final PlaylistManager playlistManager;
    //Managers

    public ModelFacade() {
        this.songManager = new SongManager();
        this.userManager = new UserManager();
        this.playlistManager = new PlaylistManager();
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
    public void logOut() {
        userManager.logOut();
    }


}
