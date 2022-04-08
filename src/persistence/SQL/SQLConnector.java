package persistence.SQL;

import persistence.SQLConfigDAO;

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

    public static SQLConnector getInstance() throws SQLException {
        if(instance == null){
            instance = new SQLConnector();
            instance.connect();
        }
            //instance.connect();
            return instance;
    }

    // CONNECT TO THE DATABASE
    private void connect() throws SQLException {
        try{
        connection = DriverManager.getConnection(url,username,password);
        } catch(SQLException e) {
            System.err.println("Couldn't connect to --> " + url + " (" + e.getMessage() + ")");
        }
        System.out.println("TEST");
    }

    // INSERT A QUERY IN STRING FORMAT
    public void addQuery(String query){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch(SQLException e){
            System.out.println("ERROR: query not added");
        }
    }

    // DISCONNECT FROM SERVER
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException ignored) { }
    }

}
