package business.managers;

import business.entities.Playlist;
import business.entities.Song;
import persistence.PlaylistDAO;
import persistence.SQL.SQLPlaylistDAO;

import java.util.List;

public class PlaylistManager {
    private Playlist playlist;
    private PlaylistDAO playlistDAO;
    List<Playlist> playlists ;


    public PlaylistManager() {
        this.playlistDAO = new SQLPlaylistDAO();
    }

    public void createPlaylist(String name, String owner){
        Playlist playlist = new Playlist(name, owner);
        playlistDAO.addPlaylist(playlist);
    }

    public void deletePlaylist(String name, String author){
        Playlist playlist = new Playlist(name, author);
        playlistDAO.deletePlayList(playlist);
    }

    public void addSongToPlaylist(Playlist playlist, Song song){
        playlistDAO.addSongToPlaylist(song,playlist);

    }

    public void deleteSongFromPlaylist(Playlist playlist, Song song){
        playlistDAO.deleteSongFromPlaylist(song, playlist);
    }

    public List<Playlist> getPlaylists(){return playlists;}

    public Playlist getPlaylist() {
        return playlist;
    }

    public PlaylistDAO getPlaylistDAO() {
        return playlistDAO;
    }
}
