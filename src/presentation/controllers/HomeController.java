package presentation.controllers;

import presentation.views.HomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController implements ActionListener {
    private final HomeView view;

    public HomeController(HomeView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case(HomeView.BTN_SONGS):
                System.out.println("Songs");
                //Show songs list
                break;
            case (HomeView.BTN_PLAYLISTS):
                System.out.println("Playlists");
                //Show Playlists
                break;
            case(HomeView.BTN_STATISTICS):
                System.out.println("Statistics");
                //Show statistics
                break;

            case(HomeView.BTN_PLAY):
                System.out.println("Played");
                //Show statistics
                break;

        }
    }
}
