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
    public void addGenre(String genreName){
        String query = "INSERT INTO Genre(name) VALUES ('"+genreName+"');";
        SQLConnector connector;
        try{
            connector = SQLConnector.getInstance();
            connector.addQuery(query);
        }catch (SQLException e){
            System.out.println("ERROR: GENRE COULD NOT BE ADDED");
        }
    }

    public Song addSong(Song song)  {
        //FIXME:  MUST ADD THE GENRE FIRST, check if it already exists !!! ??? ADD DURATION
        addGenre(song.getGenre());
        String query = "INSERT INTO Song(name,author,genre,album,lyrics) VALUES ('"+ song.getName()
                +"','"+song.getAuthor()+"','"+song.getGenre()+"','"+song.getAlbum()+"','"+song.getLyrics()+"');";
        SQLConnector connector;
        try{
            connector = SQLConnector.getInstance();
            connector.addQuery(query);
        }catch (SQLException e){
            System.out.println("ERROR: SONG COULD NOT BE ADDED");
        }
        return song;
    }

    @Override
    public Song deleteSong(Song song) {
        String query = "DELETE FROM song WHERE name = '"+song.getName()+"';";
        SQLConnector connector;
        try{
            connector = SQLConnector.getInstance();
            connector.addQuery(query);
        }catch (SQLException e){
            System.out.println("ERROR: SONG COULD NOT BE DELETED");
        }
        return song;
    }
    //DELETE SONGS
}
