package business.entities;


/**
 * Song entity. Represents a song object, it can be played, added or deleted from the system or from a playlist.
 *
 *  @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 *  @version 1.0
 *  @since 16/4/2022
 *
 */
public class Song {
    private String title; // title of the song
    private String album; // album of the song
    private String genre; // genre of the song
    private String author; // author of the song
    private String filepath; // mp3 file location
    private long duration; // duration of the song
    private String owner; // user who added the song
    /**
     * song constructor
     * @param title song title
     * @param album album that contains the song
     * @param genre song genre
     * @param author song author
     * @param filepath filepath where the MP3 file is located
     * @param duration duration of the song in milliseconds
     * @param owner owner of the song
     */
    public Song(String title, String album, String genre, String author, String filepath, long duration, String owner) {
        this.title = title;
        this.album = album;
        this.genre = genre;
        this.author = author;
        this.filepath = filepath;
        this.duration = duration;
        this.owner = owner;
    }

    /**
     * get title of the song
     * @return song title
     */
    public String getTitle() {
        return title;
    }

    /**
     * get album of the song
     * @return song album
     */

    public String getAlbum() {
        return album;
    }

    /**
     * get genre of the song
     * @return song genre
     */

    public String getGenre() {
        return genre;
    }

    /**
     * get author of the song
     * @return song author
     */

    public String getAuthor() {
        return author;
    }

    /**
     * get filepath where mp3 file is located
     * @return file string
     */

    public String getFilepath() {
        return filepath;
    }

    /**
     * get duration of the song
     * @return time in milliseconds
     */

    public long getDuration() {
        return duration;
    }

    /**
     * get user who owns the song
     * @return song owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * get duration in seconds
     * @return duration in seconds
     */
    public int getSongSeconds(){
        return (int) (duration / 1000);
    }

    /**
     * get duration in minutes
     * @return duration in minutes
     */
    public int getSongMinutes(){
        return getSongSeconds()/60;
    }
}
