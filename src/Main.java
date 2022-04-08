import business.entities.Song;
import persistence.SQL.SQLConnector;
import persistence.SQL.SQLSongDAO;
import persistence.SQLConfigDAO;

public class Main {
    public static void main(String[] args) {
        String [] data = SQLConfigDAO.getInstance().getData();
        System.out.println("shdv");
        Song song = new Song("testSong","test","test","test","","",0);
        SQLSongDAO songD = new SQLSongDAO();
        songD.addSong(song);
    }
}
