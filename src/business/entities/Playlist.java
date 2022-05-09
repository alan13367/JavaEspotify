package business.entities;


import java.util.LinkedList;
import java.util.Random;

public class Playlist {
    private int index; // not related to the id of the database, just to get the index of a song
    private String name;
    private String owner;
    private LinkedList<Song> songs;
    private boolean isLooping;
    private boolean isShuffle;

    public Playlist(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    //public int getId() {
       // return id;
   // }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public Song getNextSong(){
        if(isLooping){ // returns the same song
            return songs.get(index);
        }

        else if (isShuffle) { // return a random song from the playlist
            Random random = new Random();
            int nextSong;
            do{
               nextSong = random.nextInt(songs.size());
            }while(nextSong == index);
            index = nextSong;
            return songs.get(index);
        }
        else{ // play next song in playlist cue
            if(index+1 <= songs.size()-1){ //check index fits playlist size
                return songs.get(index++);
            }
        }
        return null;
    }

    public Song getPrevSong(){
        return null;
    }

    public boolean isLooping() {
        return isLooping;
    }

    public boolean isShuffle() {
        return isShuffle;
    }
}
