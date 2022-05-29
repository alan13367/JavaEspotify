package persistence.SQL;

import java.sql.*;

/**
 * manager if the playlists, in charge of creating, deleting and editting playlists
 *  @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 *  @version 1.0
 *  @since 12/4/2022
 */

public class SQLConnector {
    /**
     * implementation of the jdbc with the data read from SQLconfigDAO
     */
    private final String username;
    private final String password;
    private final String url;
    private Connection connection;

    private static SQLConnector instance;

    /**
     * CONSTRUCTOR TO GET JSON DATA FROM CONFIG DAO
     */
    public SQLConnector() {
        SQLConfigDAO configDAO = SQLConfigDAO.getInstance();
        String[] data = configDAO.getData();
        this.url = data[0];
        this.username = data[1];
        this.password = data[2];
    }

    /**
     * generate instance
     * @return instance to stablish connection with the database
     */
    public static SQLConnector getInstance() {
        if(instance == null){
            instance = new SQLConnector();
            instance.connect();
        }
            //instance.connect();
            return instance;
    }

    /**
     * method to connect to the database
     */
    private void connect() {
        try{
        connection = DriverManager.getConnection(url,username,password);
        } catch(SQLException e) {
            System.err.println("Couldn't connect to --> " + url + " (" + e.getMessage() + ")");
        }
    }

    /**
     * implement addition query
     * @param query query to run in database
     */
    public void addQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    /**
     * implement selection query
     * @param query query to run in database
     * @return result set
     */
    public ResultSet selectQuery(String query){
        ResultSet rs = null;
        try {
            Statement s = connection.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return rs;
    }



    /**
     *  implement deletion query
     * @param query query to run in database
     */
    public void deleteQuery(String query){
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when deleting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }

    }

    /**
     * DISCONNECT FROM SERVER
     */
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException ignored) { }
    }

}
