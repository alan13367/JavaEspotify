package persistence.SQL;

import business.entities.Playlist;
import business.entities.Song;
import persistence.PlaylistDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SQLPlaylistDAO implements PlaylistDAO {

    public void addPlaylist(Playlist playlist){
        String query = "INSERT INTO Playlist(name, author) VALUES ('"+playlist.getName()+"','"+playlist.getOwner()+"');";
        SQLConnector.getInstance().addQuery(query);
    }

    @Override
    public void deletePlayList(Playlist playlist) {
        String query = "DELETE FROM Playlist WHERE name = '"+playlist.getName()+"' AND author = '"+playlist.getOwner()+"';";
        SQLConnector.getInstance().deleteQuery(query);
    }

    public List<Playlist> loadPlaylists(){
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
        return new ArrayList<>(playlists);
    }

    @Override
    public void addSongToPlaylist(Song song, Playlist playlist) {
        String query = "INSERT INTO SongPlaylistLink(playlistName,playlistAuthor,songArtist,songTitle) VALUES ('"+playlist.getName()+"','"+playlist.getOwner()+"','"+song.getAuthor()+"','"+song.getTitle()+"');";
        SQLConnector.getInstance().addQuery(query);
    }

    @Override
    public void deleteSongFromPlaylist(Song song, Playlist playlist) {
        String query = "DELETE FROM SongPlaylistLink WHERE playlistName = '"+playlist.getName()+
                "' AND songTitle = '"+song.getTitle()+"' AND playlistAuthor = '"+playlist.getOwner()+"' AND songArtist='"+song.getAuthor()+"'; ";
        SQLConnector.getInstance().deleteQuery(query);
    }

    //  get all playlists from the database
    private List<Playlist> getPlaylists(){
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM Playlist";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try{
            while (result.next()) {
                String name= result.getString("name");
                String author = result.getString("author");
                playlists.add(new Playlist(name,author));
            }

        }catch(SQLException e){
            System.out.println("error getting all playlists");
        }
        return new ArrayList<>(playlists);
    }

    public List<Song> getSongsFromPlaylists(String title, String author){
        List<Song> songs = new ArrayList<>();
        String query = "SELECT * FROM Song JOIN SongPlaylistLink ON Song.title = SongPlaylistLink.songTitle WHERE playlistName = '"+title+"' AND playlistAuthor = '"+author+"'";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try{
            while (result.next()) {
                String name= result.getString("title");
                String artist = result.getString("author");
                String genre = result.getString("genre");
                String album = result.getString("genre");
                String filepath = result.getString("filepath");
                Long duration = result.getLong("duration");
                String owner = result.getString("owner");

                songs.add(new Song(name,artist,genre,album,filepath,duration,owner));
            }

        }catch(SQLException e){
            System.out.println("error getting all playlists");
        }
        return songs;
    }



}
