package business.entities;


import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;

public class Playlist implements Comparable<Playlist> {
    private int index; // not related to the id of the database, just to get the index of a song
    private String name;
    private String owner;
    private LinkedList<Song> songs;
    private boolean isLooping;
    private boolean isShuffle;

    public Playlist(String name, String owner) {
        this.name = name;
        this.owner = owner;
        index =0; // start the index at 0
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

    // esto al player class

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

    public Song getPrevSong(){  // if it is looping returns the same, if not it just returns the last song played
        if(isLooping){
            return songs.get(index);
        }
        else {
            if(index-1 >= 0){    // this wont work, it will return the prev song in list, not last one played
                return songs.get(index--);
            }
        }
        return null;
    }

    public boolean isLooping() {
        return isLooping;
    }

    public boolean isShuffle() {
        return isShuffle;
    }

    @Override
    public int compareTo(Playlist o) {
        return this.name.toLowerCase(Locale.ROOT).compareTo(o.getName().toLowerCase(Locale.ROOT));
    }
}
