package presentation.controllers;

import business.BusinessFacade;
import business.managers.SongPlayerManager;
import presentation.views.PlayerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerController implements ActionListener {
    private final BusinessFacade businessFacade;
    private final PlayerView view;

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
                businessFacade.playNextSong();
                System.out.println("Next");

            } // la cancion q va aqui es la seleccionada en la song view

            case (PlayerView.BTN_PREV)->{
                System.out.println("Previous");
            }

            case (PlayerView.BTN_SHUFFLE)->{
                businessFacade.setLoop(false);
                businessFacade.setShuffle(true);
                System.out.println("Shuffle");
            }

            case (PlayerView.BTN_LOOP)->{
                businessFacade.setShuffle(false);
                businessFacade.setLoop(true);
                System.out.println("Loop");
            }
        }
    }
}
