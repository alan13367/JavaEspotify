package persistence;

import business.entities.Song;
import business.entities.User;

/**
 * Interface to persist User data in the database
 */
public interface UserDAO {
    /**
     * returns a User from the database
     * @param username name of the user
     * @return user
     */
    User getUser(String username);

    /**
     * adds a user to the database
     * @param user user to add
     */
    void addUser(User user);

    /**
     * deletes a user from the database
     * @param username name of the user to delete
     */
    void deleteUser(String username);
}
