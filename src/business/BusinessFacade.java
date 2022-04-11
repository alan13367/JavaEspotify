package business;

import business.entities.Song;

import java.util.List;

public interface BusinessFacade {
    //Methods to be implemented in ModelFacade
    List<Song> getSongs();
    void deleteAccount();
    void createUser(String username,String email,String password);
}
