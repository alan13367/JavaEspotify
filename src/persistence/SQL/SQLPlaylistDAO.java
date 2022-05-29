package persistence.SQL;

import business.entities.Playlist;
import business.entities.Song;
import persistence.PlaylistDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



/**
 * implementation of all the queries that retrieve or add data related to Playlists
 *
 *   manager if the playlists, in charge of creating, deleting and editting playlists
 *    @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 *    @version 1.0
 *    @since 12/4/2022
 */
public class SQLPlaylistDAO implements PlaylistDAO {
    /**
     * add a new playlist
     * @param playlist playlist to add
     */
    public void addPlaylist(Playlist playlist){
        String query = "INSERT INTO Playlist(name, author) VALUES ('"+playlist.getName()+"','"+playlist.getOwner()+"');";
        try {
            SQLConnector.getInstance().addQuery(query);
        } catch (SQLException e) {
            System.out.println("Playlist Not Created");
        }
    }

    /**
     * delete existent playlist
     * @param playlist polaylist to delete
     */
    @Override
    public void deletePlayList(Playlist playlist) {
        String query = "DELETE FROM Playlist WHERE name = '"+playlist.getName()+"' AND author = '"+playlist.getOwner()+"';";
        SQLConnector.getInstance().deleteQuery(query);
    }

    /**
     * load all playlists
     * @return list of playlists
     */
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

    /**
     *  relate song with playlist in songPlaylistLink table
     * @param song song to be added
     * @param playlist playlist where the song is added
     */
    @Override
    public void addSongToPlaylist(Song song, Playlist playlist) {
        int position = getSongsFromPlaylists(playlist.getName(), playlist.getOwner()).size()+1;
        String query = "INSERT INTO SongPlaylistLink(playlistName,playlistAuthor,songArtist,songTitle) VALUES ('"+playlist.getName()+"','"+playlist.getOwner()+"','"+song.getAuthor()+"','"+song.getTitle()+"');";
        try {
            SQLConnector.getInstance().addQuery(query);
        } catch (SQLException e) {
            System.out.println("Song not added to Playlist");
        }
    }

    /**
     * delete song from songPlaylistLink table
     * @param song song to delete from playlist
     * @param playlist playlist containing song to delete
     */
    @Override
    public void deleteSongFromPlaylist(Song song, Playlist playlist) {
        String query = "DELETE FROM SongPlaylistLink WHERE playlistName = '"+playlist.getName()+
                "' AND songTitle = '"+song.getTitle()+"' AND playlistAuthor = '"+playlist.getOwner()+"' AND songArtist='"+song.getAuthor()+"'; ";
        SQLConnector.getInstance().deleteQuery(query);
    }

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

    /**
     * get all songs from a playlist
     * @param name name of the playlist
     * @param playlistOwner owner of the playlist
     * @return list of songs
     */
    public LinkedList<Song> getSongsFromPlaylists(String name, String playlistOwner){
        LinkedList<Song> songs = new LinkedList<>();
        String query = "SELECT * FROM Song JOIN SongPlaylistLink ON Song.title = SongPlaylistLink.songTitle WHERE playlistName = '"+ name +"' AND playlistAuthor = '"+ playlistOwner +"'";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try{
            while (result.next()) {
                String title= result.getString("title");
                String artist = result.getString("author");
                String genre = result.getString("genre");
                String album = result.getString("album");
                String filepath = result.getString("filepath");
                long duration = result.getLong("duration");
                String owner = result.getString("owner");

                songs.add(new Song(title,album,genre,artist,filepath,duration,owner));
            }

        }catch(SQLException e){
            return new LinkedList<>();
        }
        return songs;
    }

    /**
     * delete relation of a playlist with all of its songs
     * @param playlist playlist which songs will be removed
     */
    public void deleteSongsFromPlaylistLink(Playlist playlist){
        String query = "DELETE FROM SongPlaylistLink WHERE SongPlaylistLink.playlistAuthor = '"+playlist.getOwner()+"' AND SongPlaylistLink.playlistName = '"+playlist.getName()+"';";
        SQLConnector.getInstance().deleteQuery(query);
    }
}
