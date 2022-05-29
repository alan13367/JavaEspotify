package persistence.SQL;

import business.entities.User;
import persistence.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * implementation of all the queries that retrieve or add data related to Users
 *
 *   manager if the playlists, in charge of creating, deleting and editting playlists
 *    @author Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 *    @version 1.0
 *    @since 12/4/2022
 *
 */

public class SQLUserDAO implements UserDAO {
    // get data from an existent user
    @Override
    public User getUser(String username) {
        String query = "SELECT username, email, password FROM User WHERE username = '"+username+"'";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            result.next();
            String username1 = result.getString("username");
            String email = result.getString("email");
            String password = result.getString("password");
            return new User(username1,email,password);
        } catch (SQLException e) {
            System.out.println("User doesn't exist");
        }
        return null;
    }

    /**
     *
     // add a new user to the database
     * @param user user to add
     */
    @Override
    public void addUser(User user) {
        String query = "INSERT into User(username, email, password) VALUES('"+user.getUsername()+"'" +
                ",'"+user.getEmail()+"','"+user.getPassword()+"')";
        SQLConnector.getInstance().addQuery(query);
    }

    /**
     * delete existent user from the database
     * @param username name of the user to delete
     */
    @Override
    public void deleteUser(String username) {
        String query = "DELETE FROM User WHERE username = '" + username + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }
}
