package presentation.controllers;

import business.BusinessFacade;
import presentation.MainView;
import presentation.views.HomeView;
import presentation.views.PlayerView;
import presentation.views.SongsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

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
            case(PlayerView.BTN_PLAYPAUSE)->{ // default is in play mode
                if(!businessFacade.isPlaying()){
                    System.out.println("Song resumed");
                    try {
                        businessFacade.resumeSong();
                    } catch (FileNotFoundException ex) {
                        view.showErrorDialog("File of the song was not found.");
                    }
                    view.changePlayPause(businessFacade.isPlaying());
                }else {
                    businessFacade.pausePlayer();
                    view.changePlayPause(businessFacade.isPlaying());
                }
            }

            case (PlayerView.BTN_NEXT)->{
                inPlaylist = businessFacade.getInPlaylist();
                if(inPlaylist){
                    businessFacade.pausePlayer();
                    if(isLoop){
                        System.out.println("playing in loop");
                        try {
                            businessFacade.playNextInLoop(businessFacade.getCurrentSong());
                        } catch (FileNotFoundException ex) {
                            view.showErrorDialog("File of the song was not found.");
                        }

                    } else if (isShuffle) {
                        System.out.println("shuffln");
                        try {
                            businessFacade.playRandomSong();
                        } catch (FileNotFoundException ex) {
                            view.showErrorDialog("File of the song was not found.");
                        }
                        view.changeShownSong(businessFacade.getCurrentSong().getTitle(),businessFacade.getCurrentSong().getAuthor());
                    }else{
                        try {
                            businessFacade.playNextSong();
                        } catch (FileNotFoundException ex) {
                            view.showErrorDialog("File of the song was not found.");
                        }
                        view.changeShownSong(businessFacade.getCurrentSong().getTitle(),businessFacade.getCurrentSong().getAuthor());
                        // view.initSlider(businessFacade.getCurrentSong());
                        // businessFacade.moveSlider(view.getJslider());
                        System.out.println("Next");
                    }
                }

            }

            case (PlayerView.BTN_PREV)->{
                inPlaylist = businessFacade.getInPlaylist();
                if(inPlaylist){
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
                inPlaylist = businessFacade.getInPlaylist();
                if(inPlaylist){
                    System.out.println("hola");
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
                inPlaylist = businessFacade.getInPlaylist();
                if(inPlaylist){
                    if(isLoop){
                        isLoop = false;
                        System.out.println("NOT looping");
                    }else {
                        isLoop = true;
                        System.out.println("looping");
                    }
                }
            }
        }
    }
}
