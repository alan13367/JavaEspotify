package business;

import business.entities.Song;

import java.util.List;

public interface BusinessFacade {
    //Methods to be implemented in ModelFacade
    List<Song> getSongs();
    void deleteAccount();
    void createUser(String username,String email,String password);
    boolean login(String username,String password);
    boolean checkEmailFormat(String email);
    boolean checkPasswordFormat(String password);
    Song getSong(String title,String author);
    void deleteSong(String title,String author);
    String getUsername();
    String getLyrics(String author,String title);
    void logOut();
}
