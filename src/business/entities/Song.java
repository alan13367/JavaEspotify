package business.entities;

// Song entity. Represents a song objects, which can be played, added or deleted from the system or from a playlist.

public class Song {
    private String title; // title of the song
    private String album; // album of the song
    private String genre; // genre of the song
    private String author; // author of the song
    private String filepath; // mp3 file location
    private long duration; // duration of the song
    private String owner; // user who added the song

    // song constructor
    public Song(String title, String album, String genre, String author, String filepath, long duration, String owner) {
        this.title = title;
        this.album = album;
        this.genre = genre;
        this.author = author;
        this.filepath = filepath;
        this.duration = duration;
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getFilepath() {
        return filepath;
    }

    public long getDuration() {
        return duration;
    }

    public String getOwner() {
        return owner;
    }

    public int getSongSeconds(){
        return (int) (duration / 1000);
    }

    public int getSongMinutes(){
        return getSongSeconds()/60;
    }
}
