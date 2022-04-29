package presentation.controllers;

import business.BusinessFacade;
import presentation.views.HomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController implements ActionListener {
    private final HomeView view;
    private final BusinessFacade businessFacade;

    public HomeController(HomeView view, BusinessFacade businessFacade){
        this.view = view;
        this.businessFacade = businessFacade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case (HomeView.BTN_SONGS) -> {
                System.out.println("Songs");
                //Show songs list
                view.showSongsCard();
                view.getSongsView().showSongsTableCard();
            }
            case (HomeView.BTN_PLAYLISTS) -> {
                System.out.println("Playlists");
                //Show Playlists
                view.showPlaylistsCard();
                view.getPlaylistView().showPlaylistTableCard();
            }
            case (HomeView.BTN_STATISTICS) -> {
                System.out.println("Statistics");
                //Show statistics
                view.showStatisticsCard();
            }

            case (HomeView.BTN_ADDSONG) -> {
                System.out.println("Add Song");
            }

            case (HomeView.BTN_DELETEACC) -> {
                System.out.println("Delete Account");
            }

            case(HomeView.BTN_LOGOUT)->{
                System.out.println("Log Out");
            }

            case (HomeView.BTN_PLAYPAUSE) -> {
                view.pauseButton();
                System.out.println("Played");
            }
        }
    }

}
