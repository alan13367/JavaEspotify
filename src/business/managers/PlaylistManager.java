package business.managers;

import business.entities.Playlist;
import business.entities.Song;
import persistence.PlaylistDAO;
import persistence.SQL.SQLPlaylistDAO;
import presentation.views.PlaylistsView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Manager of the Playlists, in charge of creating, deleting and editing playlists
 * @author: Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 *  @version 1.0
 *  @since 12/4/2022
 */
public class PlaylistManager {
    private final PlaylistDAO playlistDAO;
    private List<Playlist> playlists;


    /**
     * Playlist manager constructor
     */
    public PlaylistManager() {
        this.playlistDAO = new SQLPlaylistDAO();
        playlists = new ArrayList<>(playlistDAO.loadPlaylists());
    }

    /**
     * create new playlist
     * @param name name of the playlist
     * @param owner owner of the playlist
     */
    public void createPlaylist(String name, String owner){
        Playlist playlist = new Playlist(name, owner);
        playlists.add(playlist);
        playlistDAO.addPlaylist(playlist);
    }

    /**
     * Method that will delete a playlist by its name and owner
     * @param name name of playlist to delete
     * @param owner playlist owner
     */
    public void deletePlaylist(String name, String owner){
        for (Playlist p:playlists){
            if(p.getName().equals(name) && p.getOwner().equals(owner)){
                playlistDAO.deletePlayList(p);
                playlists.remove(p);
                break;
            }
        }

    }

    /**
     *  add new song to playlist
     * @param playlistName name of playlist where song is added
     * @param owner name of playlist owner
     * @param song song to add
     */
    public void addSongToPlaylist(String playlistName,String owner, Song song){
        Playlist playlist = null;
        for (Playlist playlist1:getUserPlaylists(owner)){
            if(playlist1.getName().equals(playlistName)){
                playlist = playlist1;
            }
        }
        playlistDAO.addSongToPlaylist(song,playlist);
    }

    /**
     * delete a song from a playlist
     * @param playlistName playlist where song is deleted
     * @param playlistOwner name of owner of the playlist
     * @param song song to delete
     */
    public void deleteSongFromPlaylist(String playlistName,String playlistOwner, Song song){
        for (Playlist p:playlists){
            if(p.getName().equals(playlistName) && p.getOwner().equals(playlistOwner)){
                playlistDAO.deleteSongFromPlaylist(song, p);
            }
        }
    }

    /**
     * get all playlists
     * @return list of playlists
     */
    public List<Playlist> getPlaylists(){
        return new ArrayList<>(playlists);
    }

    /**
     * get songs from a playlist
     * @param title name of the playlist
     * @param author owner of the playlist
     * @return list of songs from the playlist
     */
    public List<Song> getSongsFromPlaylist(String title, String author){
        return playlistDAO.getSongsFromPlaylists(title,author);
    }

    /**
     * get all playlist from a user
     * @param username name of the user
     * @return list of playlists owned by the user
     */
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

    /**
     * Method that will delete all the playlists in the system that belong to the user given
     * @param username username to delete the playlists from
     */
    public void deletePlaylistsFromUser(String username){
        for(Playlist playlist:playlists){
            if (playlist.getOwner().equals(username)){
                playlistDAO.deletePlayList(playlist);
            }
        }
    }
}
