package persistence.SQL;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

/**
 * manager if the playlists, in charge of creating, deleting and editting playlists
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 *  @version 1.0
 *  @since 12/4/2022
 */

public class SQLConfigDAO {
    /**
     * here we will read the config json with Gson and keep the data to initialize the database
     */
    private static SQLConfigDAO instance; // singletone instance
    private String name;
    private String username;
    private String password;
    private String ip;
    private int port;
    private static final String jsonPath = "config/config.json"; // FIXME: put json file path

    private SQLConfigDAO(){  // constructor
    }

    /**
     * get json instance
     * @return json instance
     */
    public static SQLConfigDAO getInstance(){
        if(instance == null){
            readConfigJson();
        }
        return instance;
    }

    /**
     * reads the configuration Json file
     */
    private static void readConfigJson(){
        try{
            // read with GSON
            FileReader fr = new FileReader(jsonPath);
            instance = new Gson().fromJson(fr, SQLConfigDAO.class);
        }catch(IOException e){
            System.out.println("error");
        }
    }

    /**
     * get databse data
     * @return data string from jason
     */
    public String[] getData(){
        String[] data = new String[3];
        data[0] = "jdbc:mysql://" + ip + ":" + port + "/" + name ;
        data[1] = username;
        data[2] = password;
        return data;
    }

}
