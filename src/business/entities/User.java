package business.entities;

/**
 * Class to represent a User in the program
 * @author Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 18/5/2022
 */
public class User {
    private final String username;
    private final String email;
    private final String password;

    /**
     * User constructor
     * @param username name of the user
     * @param email email address of the user
     * @param password password to login
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * get name of the user
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * get email of the user
     * @return email address string
     */
    public String getEmail() {
        return email;
    }

    /**
     * get user password
     * @return password string
     */
    public String getPassword() {
        return password;
    }
}
