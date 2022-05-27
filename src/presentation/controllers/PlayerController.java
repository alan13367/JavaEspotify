package presentation.controllers;

import business.BusinessFacade;
import presentation.views.HomeView;
import presentation.views.PlayerView;
import presentation.views.SongsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerController implements ActionListener {
    private final BusinessFacade businessFacade;
    private final PlayerView view;
    private final SongsView songsView;
    private boolean isLoop = false;
    private boolean isShuffle = false;
    private boolean inPlaylist;

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
                    businessFacade.resumeSong();
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
                        businessFacade.playNextInLoop(businessFacade.getCurrentSong());

                    } else if (isShuffle) {
                        System.out.println("shuffln");
                        businessFacade.playRandomSong();
                        view.changeShownSong(businessFacade.getCurrentSong().getTitle(),businessFacade.getCurrentSong().getAuthor());
                    }else{
                        businessFacade.playNextSong();
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
                        businessFacade.playNextInLoop(businessFacade.getCurrentSong());

                    } else if (isShuffle) {
                        businessFacade.playRandomSong();
                        view.changeShownSong(businessFacade.getCurrentSong().getTitle(),businessFacade.getCurrentSong().getAuthor());
                    }else {
                        businessFacade.playPrevSong();
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
