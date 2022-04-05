package persistence;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

// https://www.adictosaltrabajo.com/2011/02/25/tutorial-basico-jdbc/


// here we will read the config json with Gson and keep the data to initialize the database

public class SQLConfigDAO {
    private static SQLConfigDAO sqlConnectorSingletone;
    private  String name;
    private  String username;
    private  String password;
    private  String ip;
    private  int port;
    private static final String jsonPath = "config/config.json"; // FIXME: put json file path

    private SQLConfigDAO(){

    }

    public static SQLConfigDAO getInstance(){
        if(sqlConnectorSingletone == null){
            readConfigJson();
        }
        return sqlConnectorSingletone;
    }

    private static void readConfigJson(){
        try{
            // read with GSON
            FileReader fr = new FileReader(jsonPath);
            sqlConnectorSingletone = new Gson().fromJson(fr, SQLConfigDAO.class);
        }catch(IOException e){
            System.out.println("error");
        }
    }

    public String[] getData(){
        String[] data = new String[3];
        data[0] = ip;
        data[1] = username;
        data[2] = "jdbc:mysql://" + ip + ":" + port + "/" + name;
        return data;
    }




}
