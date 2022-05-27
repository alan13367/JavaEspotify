package business.entities;

/**
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version
 * @since
 */
public class User {
    private String username;
    private String email;
    private String password;

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
