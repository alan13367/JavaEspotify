package persistence.SQL;

import java.sql.*;

public class SQLConnector {

    // here we will implement the jdbc with the data read from SQLconfigDAO

    private final String username;
    private final String password;
    private final String url;
    private Connection connection;

    private static SQLConnector instance;

    // CONSTRUCTOR TO GET JSON DATA FROM CONFIG DAO
    public SQLConnector() {
        SQLConfigDAO configDAO = SQLConfigDAO.getInstance();
        String[] data = configDAO.getData();
        this.url = data[0];
        this.username = data[1];
        this.password = data[2];
    }

    public static SQLConnector getInstance() {
        if(instance == null){
            instance = new SQLConnector();
            instance.connect();
        }
            //instance.connect();
            return instance;
    }

    // CONNECT TO THE DATABASE
    private void connect() {
        try{
        connection = DriverManager.getConnection(url,username,password);
        } catch(SQLException e) {
            System.err.println("Couldn't connect to --> " + url + " (" + e.getMessage() + ")");
        }
        System.out.println("TEST");
    }

    // USED FOR QUERIES THAT INSERT STUFF
    public void addQuery(String query){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch(SQLException e){
            System.out.println("ERROR: query not added");
        }
    }

    // USED FOR QUERIES THAT SELECT STUFF
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

    // USED FOR QUERIES THAT DELETE STUFF
    public void deleteQuery(String query){
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when deleting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }

    }

    // DISCONNECT FROM SERVER
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException ignored) { }
    }

}
