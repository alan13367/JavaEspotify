package persistence;

import business.entities.Playlist;
import business.entities.Song;

import java.util.List;

public interface PlaylistDAO {
    Playlist addPlaylist(Playlist playlist);
    Playlist deletePlayList(Playlist playlist);
    List<Playlist> loadPlaylists();
    void addSongToPlaylist(Song song, Playlist playlist);
    void deleteSongFromPlaylist(Song song, Playlist playlist);
}
