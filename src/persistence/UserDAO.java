package persistence;

import business.entities.Song;
import business.entities.User;

public interface UserDAO {
    User getUser(String username);
    void addUser(User user);
    void deleteUser(String username);
}
