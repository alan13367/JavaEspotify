package persistence.SQL;

import business.entities.User;
import persistence.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLUserDAO implements UserDAO {
    @Override
    public User getUser(String username) {
        String query = "SELECT username, email, password FROM User WHERE username = '"+username+"'";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            String username1 = result.getString("username");
            String email = result.getString("email");
            String password = result.getString("password");
            return new User(username1,email,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT into User(username, email, password) VALUES('"+user.getUsername()+"'" +
                ",'"+user.getEmail()+"','"+user.getPassword()+"')";
        SQLConnector.getInstance().addQuery(query);
    }

    @Override
    public void deleteUser(String username) {
        String query = "DELETE FROM User WHERE username = '" + username + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }
}
