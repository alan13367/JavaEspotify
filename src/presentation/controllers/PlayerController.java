package presentation.controllers;

import business.BusinessFacade;
import business.entities.Song;
import presentation.views.HomeView;
import presentation.views.PlayerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.Timer;

/**
 * PlayerController class manages the behaviour of the {@link PlayerView} by implementing the {@link  ActionListener}
 * interface.
 *
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 19/4/2022
 */
public class PlayerController implements ActionListener {
    private final BusinessFacade businessFacade;
    private final PlayerView view;
    private Timer timer;
    private int secondsSong;

    /**
     * Default HomeController Constructor that will link the views needed with {@link HomeView} and the business
     * logic with the {@link BusinessFacade} interface.
     * @param homeView HomeView reference
     * @param businessFacade link to the logic of the program
     */
    public PlayerController(HomeView homeView, BusinessFacade businessFacade) {
        this.businessFacade = businessFacade;
        this.view = homeView.getPlayerView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case(PlayerView.BTN_PLAYPAUSE)->{
                if(businessFacade.getCurrentSong() != null){
                    if(!businessFacade.isPlaying()){
                        System.out.println("Song resumed");
                        try {
                            businessFacade.resumeSong();
                        } catch (FileNotFoundException ex) {
                            view.showErrorDialog("File of the song was not found.");
                        }
                        view.changePlayPause(businessFacade.isPlaying());
                        resumeTimer();
                    }else {
                        businessFacade.pausePlayer();
                        view.changePlayPause(businessFacade.isPlaying());
                        pauseTimer();
                    }
                }
            }

            case (PlayerView.BTN_NEXT)->{
                if(businessFacade.isPlayingPlaylist()){
                    businessFacade.stopPlayer();
                    timer.stop();
                    try {
                        businessFacade.playNextSong();
                    } catch (FileNotFoundException ex) {
                        view.showErrorDialog("File of the song was not found.");
                    }
                    if(!businessFacade.queueIsEmpty()){
                        Song song = businessFacade.getCurrentSong();
                        view.changeShownSong(song.getTitle(),song.getAuthor());
                        view.updateCurrentTime(0);
                        view.changeTotalTime(song.getSongMinutes(),song.getSongSeconds());
                        view.startTimer(song.getSongSeconds());
                    }else {
                        view.changePlayPause(businessFacade.isPlaying());
                        view.changeShownSong("","");
                        view.updateCurrentTime(0);
                        view.changeTotalTime(0,0);
                        view.moveSliderPosition(0);

                    }
                }
            }

            case (PlayerView.BTN_PREV)->{
                if(businessFacade.isPlayingPlaylist() && !businessFacade.playedSongsIsEmpty()){
                    businessFacade.stopPlayer();
                    timer.stop();
                    try {
                        businessFacade.playPrevSong();
                    } catch (FileNotFoundException ex) {
                        view.showErrorDialog("File of the song was not found.");
                    }
                    Song song = businessFacade.getCurrentSong();
                    view.changeShownSong(song.getTitle(),song.getAuthor());
                    view.updateCurrentTime(0);
                    view.changeTotalTime(song.getSongMinutes(),song.getSongSeconds());
                    view.startTimer(song.getSongSeconds());

                }
            }

            case (PlayerView.BTN_STOP)->{
                if(businessFacade.isPlaying()){
                    businessFacade.stopPlayer();
                }
                timer.stop();
                view.updateCurrentTime(0);
                view.changeTotalTime(0,0);
                view.changePlayPause(businessFacade.isPlaying());
                view.changeShownSong("","");
                view.moveSliderPosition(0);
            }

            case (PlayerView.BTN_LOOP)->{
                if(businessFacade.isPlaying()){

                    if(businessFacade.isPlayingPlaylist()){
                        if(businessFacade.isLoopingPlaylist()){
                            businessFacade.setLoopingPlaylist(false);
                            System.out.println("NOT looping");
                        }else {
                            businessFacade.setLoopingPlaylist(true);
                            System.out.println("looping");
                        }
                    }
                    else{
                        businessFacade.setLoopingSong(!businessFacade.isLoopingSong());
                    }
                }

            }
        }
    }

    /**
     * Method that will start the timer of the song and move the slider
     * @param songDuration total duration of the song
     */
    public void startSongTimer(int songDuration){
        if(timer != null){
            timer.stop();
        }
        view.setSliderMaximum(songDuration);
        secondsSong = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.moveSliderPosition(secondsSong++);
                view.updateCurrentTime(secondsSong);
                if(secondsSong == songDuration+1){
                    Song song = businessFacade.getCurrentSong();
                    businessFacade.stopPlayer();
                    timer.stop();
                    view.moveSliderPosition(0);
                    if(businessFacade.isLoopingSong()){
                        try {
                            businessFacade.playSong(song.getTitle(),song.getAuthor());
                        } catch (FileNotFoundException ex) {
                        }
                        view.startTimer(song.getSongSeconds());
                    }else{
                        view.changePlayPause(businessFacade.isPlaying());
                        view.changeShownSong("","");
                        timer.stop();
                    }
                    if(businessFacade.isPlayingPlaylist()){
                        try {
                            businessFacade.playNextSong();
                        } catch (FileNotFoundException ex) {
                            view.showErrorDialog("File of the song was not found.");
                        }
                        if(!businessFacade.queueIsEmpty()){
                            Song song1 = businessFacade.getCurrentSong();
                            view.changeShownSong(song1.getTitle(),song1.getAuthor());
                            view.changeTotalTime(song1.getSongMinutes(),song1.getSongSeconds());
                            view.startTimer(song1.getSongSeconds());
                        }
                        view.changePlayPause(businessFacade.isPlaying());
                    }
                }
            }
        });
        timer.start();
    }

    /**
     * Method that will resume the timer
     */
    public void resumeTimer(){
        timer.start();
    }

    /**
     * Method that will pause the timer
     */
    public void pauseTimer(){
        timer.stop();
    }
}
