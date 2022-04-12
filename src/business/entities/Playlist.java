package business.entities;


import java.util.LinkedList;

public class Playlist {
    private String name;
    private String author;
    private LinkedList<Song> songs;
    private boolean isLooping;
    private boolean isShuffle;

    public Playlist(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isLooping() {
        return isLooping;
    }

    public boolean isShuffle() {
        return isShuffle;
    }
}
