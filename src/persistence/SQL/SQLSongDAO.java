package persistence.SQL;

import business.entities.Song;
import persistence.SongDAO;

import java.sql.SQLException;
import java.util.List;

public class SQLSongDAO implements SongDAO {

    @Override
    public List<Song> loadSongs() {
        return null;
    }

    //ADD SONGS
    public void addSong(Song song)  {
        String query = "INSERT INTO Song(name,author,genre,album,lyrics) VALUES ('"+ song.getName()
                +"','"+song.getAuthor()+"','"+song.getGenre()+"','"+song.getAlbum()+"','"+song.getLyrics()+"');";
        SQLConnector connector = null;
        try{
            connector = SQLConnector.getInstance();
            connector.addQuery(query);
        }catch (SQLException e){
            System.out.println("error");
        }

    }

    @Override
    public void deleteSong() {

    }
    //DELETE SONGS
}
