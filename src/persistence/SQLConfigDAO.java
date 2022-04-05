package persistence;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

// https://www.adictosaltrabajo.com/2011/02/25/tutorial-basico-jdbc/


// here we will read the config json with Gson and keep the data to initialize the database

public class SQLConfigDAO {
    private static SQLConfigDAO sqlConnectorSingletone;
    private static String name;
    private static String username;
    private static String password;
    private static String ip;
    private static int port;
    private static final String jsonPath = "/Users/alvarofeher/Desktop/WORK/DPO/dpoo-2122-s2-ice3/config/config.json"; // FIXME: put json file path

    private SQLConfigDAO(){

    }

    public static SQLConfigDAO getInstance(){
        if(sqlConnectorSingletone == null){
            sqlConnectorSingletone = new SQLConfigDAO();
            readConfigJson();
        }
        return sqlConnectorSingletone;
    }

    private static void readConfigJson(){
        try(FileReader fr = new FileReader(jsonPath)){
            // read with GSON
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
