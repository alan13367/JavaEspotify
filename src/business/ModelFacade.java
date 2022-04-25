package business;

import business.entities.Song;
import business.entities.User;
import business.managers.PlaylistManager;
import business.managers.SongManager;
import business.managers.UserManager;

import java.util.List;

public class ModelFacade implements BusinessFacade {
    private SongManager songManager;
    private UserManager userManager;
    private PlaylistManager playlistManager;
    //Managers

    public ModelFacade() {
        this.songManager = new SongManager();
        this.userManager = new UserManager();
    }

    @Override
    public List<Song> getSongs() {
        return songManager.getSongs();
    }

    @Override
    public void deleteAccount() {
        userManager.deleteAccount();
    }

    public void createUser(String username,String email,String password){
        userManager.createUser(new User(username,email,password));
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
    public Song getSong(String name, String author) {
        return songManager.getSong(name,author);
    }

    @Override
    public String getUsername() {
        return userManager.getUsername();
    }

    @Override
    public String getLyrics(String artist, String title) {
        return songManager.getSongLyrics(artist,title);
    }


}
