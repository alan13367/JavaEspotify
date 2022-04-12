package business;

import business.entities.Song;
import business.entities.User;
import business.managers.PlaylistManager;
import business.managers.SongManager;
import business.managers.UserManager;

import java.util.List;

public class ModelFacade implements BusinessFacade {
    SongManager songManager;
    UserManager userManager;
    PlaylistManager playlistManager;
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
    public void login(String username, String password) {
        userManager.logIn(username,password);
    }


}
