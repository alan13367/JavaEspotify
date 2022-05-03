package business.entities;

public class Song {
    private int id;
    private String title;
    private String album;
    private String genre;
    private String author;
    private String filepath;
    private long duration;
    private String owner;

    public Song(String title, String album, String genre, String author, String filepath, long duration, String owner, int id) {
        this.title = title;
        this.album = album;
        this.genre = genre;
        this.author = author;
        this.filepath = filepath;
        this.duration = duration;
        this.owner = owner;
        this.id = id;
    }

    public int getId() {
        return id;
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
