package business.entities;


import java.util.LinkedList;

public class Playlist {
    private String name;
    private LinkedList<Song> songs;
    private boolean isLooping;
    private boolean isShuffle;

    public String getName() {
        return name;
    }

    public boolean isLooping() {
        return isLooping;
    }

    public boolean isShuffle() {
        return isShuffle;
    }
}
