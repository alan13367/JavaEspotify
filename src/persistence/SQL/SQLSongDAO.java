package persistence.SQL;

import business.entities.Song;
import persistence.SongDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// implementation of all the queries that retrieve or add data related to Songs

public class SQLSongDAO implements SongDAO {

    // loads all the songs from the database
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

                songs.add(new Song(title,album,genre,author,filepath,duration,owner));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }


    //add a new genre
    public void addGenre(String genreName){
        String query = "INSERT INTO Genre(name) VALUES ('"+genreName+"');";
        SQLConnector connector;
        connector = SQLConnector.getInstance();
        connector.addQuery(query);
    }

    // add a new song to the database
    public void addSong(Song song)  {
        addGenre(song.getGenre());
        String query = "INSERT INTO Song(title,author,genre,album,filepath,duration,owner) VALUES ('"+ song.getTitle()
                +"','"+song.getAuthor()+"','"+song.getGenre()+"','"+song.getAlbum()+"','"+song.getFilepath()+"','"
                +song.getDuration()+"','"+song.getOwner()+"');";
        SQLConnector connector;
        connector = SQLConnector.getInstance();
        connector.addQuery(query);
    }

    // count all genres
    @Override
    public HashMap<String, Integer> getGenreCount(){
        HashMap<String, Integer> songs = new HashMap<>();
        //select genre, count(*) as cnt FROM Song GROUP BY genre
        String query = "SELECT genre, COUNT(*) AS CNT FROM Song GROUP BY genre ORDER BY CNT DESC LIMIT 10";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            while (result.next()) {
                String genre= result.getString("genre");
                int counter = result.getInt("CNT");
                songs.put(genre, counter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(songs);

        return songs;
    }

    //delete a song
    @Override
    public void deleteSong(Song song) {
        String query = "DELETE FROM Song WHERE title = '"+song.getTitle()+"' AND author = '"+song.getAuthor()+"';";
        SQLConnector.getInstance().deleteQuery(query);
    }

}
