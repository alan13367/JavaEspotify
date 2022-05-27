package business.entities;


import java.util.LinkedList;
import java.util.Locale;

/**
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 14/5/2022
 */

public class Playlist implements Comparable<Playlist> {
    private int index; // not related to the id of the database, just to get the index of a song
    private String name;
    private String owner;
    private LinkedList<Song> songs;
    private boolean isLooping;
    private boolean isShuffle;

    /**
     * playlist constructor
     * @param name name of the playlist
     * @param owner owner of the playlist
     */
    public Playlist(String name, String owner) {
        this.name = name;
        this.owner = owner;
        index =0; // start the index at 0
    }

    /**
     * get name of the playlist
     * @return name of the playlist
     */
    public String getName() {
        return name;
    }

    /**
     * get owner of the playlist
     * @return namne of the owner
     */
    public String getOwner() {
        return owner;
    }

    @Override
    public int compareTo(Playlist o) {
        return this.name.toLowerCase(Locale.ROOT).compareTo(o.getName().toLowerCase(Locale.ROOT));
    }
}
