package persistence.SQL;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

/**
 * manager if the playlists, in charge of creating, deleting and editting playlists
 *  @author Alan Beltrán
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
        private static void readConfigJson() {
            try (FileReader fr = new FileReader(jsonPath))
                instance = new Gson().fromJson(fr, SQLConfigDAO.class);
        } catch (IOException e) {
            System.err.println("Error reading config JSON: " + e.getMessage());
        }

        public static SQLConfigDAO getInstance(){
            if(instance == null){
                if (System.getenv("DB_NAME") != null && System.getenv("DB_USERNAME") != null && System.getenv("DB_PASSWORD") != null) {
                    instance = new SQLConfigDAO();
                    instance.name = System.getenv("DB_NAME");
                    instance.username = System.getenv("DB_USERNAME");
                    instance.password = System.getenv("DB_PASSWORD");
                    instance.ip = System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : "localhost";
                    instance.port = System.getenv("DB_PORT") != null ? Integer.parseInt(System.getenv("DB_PORT")) : 3306;
                } else {
                    readConfigJson();
                }
            }
            return instance;
        }
    /**
     * reads the configuration Json file
     */
    private static void readConfigJson() {
        try (FileReader fr = new FileReader(jsonPath)) {
            // read with GSON
            instance = new Gson().fromJson(fr, SQLConfigDAO.class);
        } catch (IOException e) {
            System.err.println("Error reading config JSON: " + e.getMessage());
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
