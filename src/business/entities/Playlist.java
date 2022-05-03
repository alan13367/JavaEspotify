package business.entities;


import java.util.LinkedList;

public class Playlist {
    private int id;
    private String name;
    private String owner;
    //private LinkedList<Song> songs;
    private boolean isLooping;
    private boolean isShuffle;

    public Playlist(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isLooping() {
        return isLooping;
    }

    public boolean isShuffle() {
        return isShuffle;
    }
}
