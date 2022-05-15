package business.managers;

import business.entities.Player;
import business.entities.Playlist;
import business.entities.Song;
import javazoom.jl.decoder.JavaLayerException;
import persistence.SQL.SQLSongDAO;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

public class SongPlayerManager {

   // private Player player = new Player(); // tengo dos players, he de usar solo uno!!!!
    private boolean isShuffle;
    private boolean isLoop;
    private boolean playNext;
    private boolean playPrev;
    Player player;


    // hacer que si esta en loop y le das a next haga loop, si le das a shuffle y a next q haga play de una random
    // si estas en una playlist y le das a prev o next q pille el index anterior o posterior en la playlist
    // asi no dependo de q acabe la cancion, le das a las flechas del player


    public void setShuffle(boolean shuffle) {
        isShuffle = shuffle;
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
    }

    // TODO: METER ESTO EN EL FACADE Y QUITAR EL Q VIENE DE PLAYER
    //  hacer tmb q vayan los botones next, prev, loop, shuffle

    // when a next/prev arrow is pressed
    public void playNextSong(Song song, Thread thread) throws FileNotFoundException, JavaLayerException {
        if(isShuffle){
            player.playSong(getRandomSong(),thread);
        } else if (isLoop) {
            player.playSong(song, thread);
        }
    }

    // FOR SHUFFLE MODE
    public Song getRandomSong(){
        Random random = new Random();
        SQLSongDAO dao = new SQLSongDAO();
        List<Song> songs = dao.loadSongs() ;
        int next;
        next = random.nextInt(songs.size());
        return songs.get(next);
    }

    public void playPlaylist(Playlist playlist){
        int index;
        if(isLoop){

        }
        if(isShuffle){

        }
    }
}



