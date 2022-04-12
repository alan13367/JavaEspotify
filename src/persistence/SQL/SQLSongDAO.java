package persistence.SQL;

import business.entities.Song;
import persistence.SongDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SQLSongDAO implements SongDAO {

    @Override
    public List<Song> loadSongs() {

        List<Song> songs = new ArrayList<>();
        String query = "SELECT * FROM Song;";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            while (result.next()) {
                String name= result.getString("name");
                String album = result.getString("album");
                String genre= result.getString("genre");
                String author= result.getString("author");
                String filepath= result.getString("filepath");
                long duration = result.getLong("duration");

                songs.add(new Song(name,album,genre,author,filepath,duration));
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

    public Song addSong(Song song)  {
        addGenre(song.getGenre());
        String query = "INSERT INTO Song(name,author,genre,album,filepath,duration) VALUES ('"+ song.getName()
                +"','"+song.getAuthor()+"','"+song.getGenre()+"','"+song.getAlbum()+"','"+song.getFilepath()+"','"+song.getDuration()+"');";
        SQLConnector connector;
        connector = SQLConnector.getInstance();
        connector.addQuery(query);
        return song;
    }

    //DELETE SONGS
    @Override
    public Song deleteSong(Song song) {
        String query = "DELETE FROM song WHERE name = '"+song.getName()+"';";
        SQLConnector.getInstance().addQuery(query);
        return song;
    }




}
