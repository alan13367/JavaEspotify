import business.entities.Song;
import persistence.SQL.SQLConnector;
import persistence.SQL.SQLSongDAO;
import persistence.SQLConfigDAO;

public class Main {
    public static void main(String[] args) {
        String [] data = SQLConfigDAO.getInstance().getData();


    }
}
