package business.entities;

public class Song {
    private String name;
    private String album;
    private String genre;
    private String author;
    private String lyrics;
    private String filepath;
    private int duration;

    public Song(String name, String album, String genre, String author, String lyrics, String filepath, int duration) {
        this.name = name;
        this.album = album;
        this.genre = genre;
        this.author = author;
        this.lyrics = lyrics;
        this.filepath = filepath;
        this.duration = duration;
    }

    public String getLyrics() {
        return lyrics;
    }

    public String getName() {
        return name;
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

    public int getDuration() {
        return duration;
    }
}
