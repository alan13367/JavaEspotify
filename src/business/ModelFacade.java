package business;

import business.entities.Song;
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
}
