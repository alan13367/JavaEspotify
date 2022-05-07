package business.managers;

import business.entities.Playlist;
import business.entities.Song;
import persistence.PlaylistDAO;
import persistence.SQL.SQLPlaylistDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlaylistManager {
    private Playlist playlist;
    private final PlaylistDAO playlistDAO;
    List<Playlist> playlists ;


    public PlaylistManager() {
        this.playlistDAO = new SQLPlaylistDAO();
        playlists = new ArrayList<>(playlistDAO.loadPlaylists());
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

    public List<Playlist> getUserPlaylists(String username){
        List<Playlist> list = new ArrayList<>();
        for (Playlist playlist1:playlists){
            if(Objects.equals(playlist1.getOwner(), username)){
                list.add(playlist1);
            }
        }
        return list;
    }

    public PlaylistDAO getPlaylistDAO() {
        return playlistDAO;
    }
}
