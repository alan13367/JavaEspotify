package presentation.controllers;

import business.BusinessFacade;
import business.managers.SongPlayerManager;
import presentation.views.PlayerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerController implements ActionListener {
    private final BusinessFacade businessFacade;
    private final PlayerView view;
    private boolean isLoop;
    private boolean isShuffle;

    public PlayerController(PlayerView view,BusinessFacade businessFacade) {
        this.businessFacade = businessFacade;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case(PlayerView.BTN_PLAYPAUSE)->{ // default is in play mode
                if(!businessFacade.isPlaying()){
                    System.out.println("Song resumed");
                    view.changePlayPause(businessFacade.isPlaying());
                }else {
                    businessFacade.pausePlayer();
                    view.changePlayPause(businessFacade.isPlaying());
                }
            }

            case (PlayerView.BTN_NEXT)->{
                businessFacade.pausePlayer();
                if(isLoop){
                    System.out.println("playing in loop");
                    businessFacade.playNextInLoop();
                } else if (isShuffle) {
                    System.out.println("shuffln");
                    businessFacade.playRandomSong();
                }else{
                    businessFacade.playNextSong();
                    System.out.println("Next");
                }
            }

            case (PlayerView.BTN_PREV)->{
                businessFacade.pausePlayer();
                if(isLoop){
                    businessFacade.playPrevSong();
                } else if (isShuffle) {
                    businessFacade.playRandomSong();
                }else {
                    businessFacade.playPrevSong();
                }
                System.out.println("Previous");
            }

            case (PlayerView.BTN_SHUFFLE)->{
                //businessFacade.setLoop(false);
                //businessFacade.setShuffle(true);
                //businessFacade.playRandomSong();
                System.out.println("hola");
                if(isShuffle){
                    isShuffle = false;
                    System.out.println("Shuffle true");
                }else{
                    isShuffle = true;
                    System.out.println("Shuffle false");
                }

            }

            case (PlayerView.BTN_LOOP)->{
                //businessFacade.setShuffle(false);
                //businessFacade.setLoop(true);
               // businessFacade.playInLoop();
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
