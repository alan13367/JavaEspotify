package presentation.controllers;

import business.BusinessFacade;
import business.managers.SongPlayerManager;
import presentation.views.PlayerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerController implements ActionListener {
    private final BusinessFacade businessFacade;
    private final PlayerView view;
    private SongPlayerManager player = new SongPlayerManager();
    private PlaylistsController playlistsController;
    private boolean playing = true;
    private SongsController songsController; // FIXME: COMO LE PASO EL CONTROLLER
    private int songInPlaylistId; 
    public PlayerController(PlayerView view,BusinessFacade businessFacade) {
        this.businessFacade = businessFacade;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case(PlayerView.BTN_PLAYPAUSE)->{ // default is in play mode
                if(playing){
                    System.out.println("Song paused");
                    view.changePlayPause(playing);
                    businessFacade.pausePlayer();
                }else{
                    playing = true;
                    System.out.println("Song resumed");
                    view.changePlayPause(playing);
                   // businessFacade.resumePlayer();
                }
            }

            // TODO: si estamos en una playlist habra q usar songs de la playlist,
            //  añadir "if in playlist"

            case (PlayerView.BTN_NEXT)->{
                System.out.println("Next");

                businessFacade.playNextSong();
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
