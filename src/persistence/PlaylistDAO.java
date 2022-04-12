package persistence;

import business.entities.Playlist;

import java.util.List;

public interface PlaylistDAO {
    Playlist addPlaylist();
    Playlist deletePlayList();
    List<Playlist> loadPlaylists();
}
