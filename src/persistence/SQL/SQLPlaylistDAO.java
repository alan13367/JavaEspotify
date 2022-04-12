package persistence.SQL;

import business.entities.Playlist;
import business.entities.Song;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SQLPlaylistDAO {

    public Playlist addPlaylist(Playlist playlist){
        String query = "INSERT INTO Playlist(name, author) VALUES ('"+playlist.getName()+"','"+playlist.getAuthor()+"');";
        SQLConnector connector = new SQLConnector();
        connector.addQuery(query);
        return playlist;
    }

    private Playlist deletePlaylist(Playlist playlist){
        String query = "DELETE FROM Playlist WHERE name = '"+playlist.getName()+"';";
        SQLConnector connector = new SQLConnector();
        connector.deleteQuery(query);
        return playlist;
    }

    private List<Playlist> loadPlaylists(){
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM Playlist";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            while (result.next()) {
                String name= result.getString("name");
                String author= result.getString("author");
                playlists.add(new Playlist(name,author));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlists;
    }

    // since playlists contain their songs ids in csv format, this function returns the song ids from the songs it has
    private List<Integer> getSongIdsFromPlaylist(Playlist playlist){
        List<Integer> songIds = new ArrayList<>();
        String[] idsString;
        String query = "SELECT songs FROM playlist WHERE name = '"+playlist.getName()+"';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            while (result.next()) {
                String ids= result.getString("id");
                idsString = ids.split(";");
                for (int i = 0; i < idsString.length; i++) {
                    songIds.add(i,Integer.parseInt(idsString[i]));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songIds;
    }



}
