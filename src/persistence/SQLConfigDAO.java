package persistence;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

// https://www.adictosaltrabajo.com/2011/02/25/tutorial-basico-jdbc/


// here we will read the config json with Gson and keep the data to initialize the database

public class SQLConfigDAO {
    private static SQLConfigDAO sqlConnectorSingletone;
    private static String dbName;
    private static String username;
    private static String password;
    private static String databaseIP;
    private static int port;

    private SQLConfigDAO(){

    }

    private static void readConfigJson(String jsonPath){
        try(FileReader fr = new FileReader(jsonPath)){
            // read with GSON
            sqlConnectorSingletone = new Gson().fromJson(fr, SQLConfigDAO.class);
        }catch(IOException e){
            System.out.println("error");
        }
    }


}
