package persistence;

import business.entities.Playlist;
import business.entities.Song;

import java.util.LinkedList;
import java.util.List;

public interface PlaylistDAO {
    void addPlaylist(Playlist playlist);
    void deletePlayList(Playlist playlist);
    List<Playlist> loadPlaylists();
    void addSongToPlaylist(Song song, Playlist playlist);
    void deleteSongFromPlaylist(Song song, Playlist playlist);

    void deleteSongsFromPlaylistLink(Playlist playlist);

    LinkedList<Song> getSongsFromPlaylists(String name, String owner);
}
