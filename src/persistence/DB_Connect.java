package persistence;

import java.sql.*;

public class DB_Connect {

    // here we will implement the jdbc with the data read from SQLconfigDAO

    private final String dbName;
    private final String username;
    private final String password;
    private final int port;
    private final String url;


    // CONSTRUCTOR TO GET JSON DATA
    public DB_Connect(String dbName, String username, String password, int port,  String url) { // remove parameters, get data from configDAO
        this.dbName = dbName;
        this.username = username;
        this.password = password;
        this.port = port;
        this.url = url;
    }

    // https://www.tutorialspoint.com/jdbc/jdbc-create-database.htm
    private void JDBC() throws SQLException {
        try(Connection connection = DriverManager.getConnection(url,username,password)){
            Statement statement = connection.createStatement();
        }catch (SQLException e){
            System.out.println("db error");
        }
    }





}
