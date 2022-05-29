package presentation.controllers;

import business.BusinessFacade;
import business.entities.Song;
import presentation.views.HomeView;
import presentation.views.PlayerView;
import presentation.views.SongsView;

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
    private final SongsView songsView;
    private boolean isLoop = false;
    private boolean isShuffle = false;
    private boolean inPlaylist;
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
        this.songsView = homeView.getSongsView();
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
                if (isShuffle) {
                    System.out.println("shuffln");
                    try {
                        businessFacade.playRandomSong();
                    } catch (FileNotFoundException ex) {
                        view.showErrorDialog("File of the song was not found.");
                    }
                    view.changeShownSong(businessFacade.getCurrentSong().getTitle(),businessFacade.getCurrentSong().getAuthor());
                }


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
                        view.changeTotalTime(song.getSongMinutes(),song.getSongSeconds());
                        view.startTimer(song.getSongSeconds());
                    }else {
                        view.changePlayPause(businessFacade.isPlaying());
                        view.changeShownSong("","");
                        timer.stop();
                        view.moveSliderPosition(0);
                    }

                        // view.initSlider(businessFacade.getCurrentSong());
                        // businessFacade.moveSlider(view.getJslider());
                    System.out.println("Next");
                }
            }

            case (PlayerView.BTN_PREV)->{
                if(businessFacade.isPlayingPlaylist()){
                    businessFacade.pausePlayer();
                    if(isLoop){
                        System.out.println("playing in loop");
                        try {
                            businessFacade.playNextInLoop(businessFacade.getCurrentSong());
                        } catch (FileNotFoundException ex) {
                            view.showErrorDialog("File of the song was not found.");
                        }

                    } else if (isShuffle) {
                        try {
                            businessFacade.playRandomSong();
                        } catch (FileNotFoundException ex) {
                            view.showErrorDialog("File of the song was not found.");
                        }
                        view.changeShownSong(businessFacade.getCurrentSong().getTitle(),businessFacade.getCurrentSong().getAuthor());
                    }else {
                        try {
                            businessFacade.playPrevSong();
                        } catch (FileNotFoundException ex) {
                            view.showErrorDialog("File of the song was not found.");
                        }
                        view.changeShownSong(businessFacade.getCurrentSong().getTitle(),businessFacade.getCurrentSong().getAuthor());
                    }
                    System.out.println("Previous");
                }
            }

            case (PlayerView.BTN_SHUFFLE)->{
                inPlaylist = businessFacade.isPlayingPlaylist();
                if(inPlaylist){
                    System.out.println("hola");
                    businessFacade.pausePlayer();
                    try {
                        businessFacade.playRandomSong();
                        view.changeShownSong(businessFacade.getCurrentSong().getTitle(),businessFacade.getCurrentSong().getAuthor());
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    if(isShuffle){
                        isShuffle = false;
                        System.out.println("Shuffle true");
                    }else{
                        isShuffle = true;
                        System.out.println("Shuffle false");
                    }
                }
            }

            case (PlayerView.BTN_LOOP)->{
                if(businessFacade.isPlayingPlaylist()){
                    if(businessFacade.isLoopingPlaylist()){
                        businessFacade.setLoopingPlaylist(false);
                        System.out.println("NOT looping");
                    }else {
                        businessFacade.setLoopingPlaylist(true);
                        System.out.println("looping");
                    }
                }
            }
        }
    }

    public void startTimer(int songDuration){
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
                    businessFacade.stopPlayer();
                    view.changePlayPause(businessFacade.isPlaying());
                    view.changeShownSong("","");
                    timer.stop();
                    view.moveSliderPosition(0);
                    if(businessFacade.isPlayingPlaylist()){
                        try {
                            businessFacade.playNextSong();
                        } catch (FileNotFoundException ex) {
                            view.showErrorDialog("File of the song was not found.");
                        }
                        if(!businessFacade.queueIsEmpty()){
                            Song song = businessFacade.getCurrentSong();
                            view.changeShownSong(song.getTitle(),song.getAuthor());
                            view.changeTotalTime(song.getSongMinutes(),song.getSongSeconds());
                            view.startTimer(song.getSongSeconds());
                        }
                        view.changePlayPause(businessFacade.isPlaying());
                    }
                }
            }
        });
        timer.start();
    }

    public void resumeTimer(){
        timer.start();
    }

    public void pauseTimer(){
        timer.stop();
    }
}
