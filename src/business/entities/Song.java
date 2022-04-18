package business.entities;

public class Song {
    private String name;
    private String album;
    private String genre;
    private String author;
    private String filepath;
    private long duration;
    private String owner;

    public Song(String name, String album, String genre, String author, String filepath, long duration) {
        this.name = name;
        this.album = album;
        this.genre = genre;
        this.author = author;
        this.filepath = filepath;
        this.duration = duration;
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

    public long getDuration() {
        return duration;
    }

    public String getOwner() {
        return owner;
    }
}
