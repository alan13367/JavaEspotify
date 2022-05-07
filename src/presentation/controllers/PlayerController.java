package presentation.controllers;

import business.BusinessFacade;
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

            case(PlayerView.BTN_PLAYPAUSE)->{
                view.changePlayButton();
            }
            case (PlayerView.BTN_NEXT)->{
                System.out.println("Next");
            }
            case (PlayerView.BTN_PREV)->{
                System.out.println("Previous");
            }
            case (PlayerView.BTN_SHUFFLE)->{
                System.out.println("Shuffle");
            }
            case (PlayerView.BTN_LOOP)->{
                System.out.println("Loop");
            }
        }
    }
}
