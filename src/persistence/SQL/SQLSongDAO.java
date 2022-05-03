package persistence.SQL;

import business.entities.Song;
import persistence.SongDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLSongDAO implements SongDAO {

    @Override
    public List<Song> loadSongs() {

        List<Song> songs = new ArrayList<>();
        String query = "SELECT * FROM Song;";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            while (result.next()) {
                String title= result.getString("title");
                String album = result.getString("album");
                String genre= result.getString("genre");
                String author= result.getString("author");
                String filepath= result.getString("filepath");
                long duration = result.getLong("duration");
                String owner = result.getString("owner");
                int id = result.getInt("id");

                songs.add(new Song(title,album,genre,author,filepath,duration,owner,id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }


    //ADD SONGS
    public void addGenre(String genreName){
        String query = "INSERT INTO Genre(name) VALUES ('"+genreName+"');";
        SQLConnector connector;
        connector = SQLConnector.getInstance();
        connector.addQuery(query);
    }

    public void addSong(Song song)  {
        addGenre(song.getGenre());
        String query = "INSERT INTO Song(title,author,genre,album,filepath,duration,owner) VALUES ('"+ song.getTitle()
                +"','"+song.getAuthor()+"','"+song.getGenre()+"','"+song.getAlbum()+"','"+song.getFilepath()+"','"
                +song.getDuration()+"','"+song.getOwner()+"');";
        SQLConnector connector;
        connector = SQLConnector.getInstance();
        connector.addQuery(query);
    }

    //DELETE SONGS
    @Override
    public void deleteSong(Song song) {
        String query = "DELETE FROM Song WHERE title = '"+song.getTitle()+"' AND author = '"+song.getAuthor()+"';";
        SQLConnector.getInstance().deleteQuery(query);
    }




}
