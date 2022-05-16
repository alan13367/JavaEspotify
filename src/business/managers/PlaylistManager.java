package business.managers;

import business.entities.Playlist;
import business.entities.Song;
import persistence.PlaylistDAO;
import persistence.SQL.SQLPlaylistDAO;
import presentation.views.PlaylistsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlaylistManager {
    private final PlaylistDAO playlistDAO;
    private List<Playlist> playlists;


    public PlaylistManager() {
        this.playlistDAO = new SQLPlaylistDAO();
        playlists = new ArrayList<>(playlistDAO.loadPlaylists());
    }

    public void createPlaylist(String name, String owner){
        Playlist playlist = new Playlist(name, owner);
        playlists.add(playlist);
        playlistDAO.addPlaylist(playlist);
    }

    public void deletePlaylist(String name, String owner){
        for (Playlist p:playlists){
            if(p.getName().equals(name) && p.getOwner().equals(owner)){
                playlistDAO.deletePlayList(p);
                playlists.remove(p);
                break;
            }
        }

    }

    public void addSongToPlaylist(String playlistName,String owner, Song song){
        Playlist playlist = null;
        for (Playlist playlist1:getUserPlaylists(owner)){
            if(playlist1.getName().equals(playlistName)){
                playlist = playlist1;
            }
        }
        playlistDAO.addSongToPlaylist(song,playlist);

    }

    public void deleteSongFromPlaylist(Playlist playlist, Song song){
        playlistDAO.deleteSongFromPlaylist(song, playlist);
    }

    public List<Playlist> getPlaylists(){
        return new ArrayList<>(playlists);
    }

    public List<Playlist> getUserPlaylists(String username){
        playlists = new ArrayList<>(playlistDAO.loadPlaylists());
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
