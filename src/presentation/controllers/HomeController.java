package presentation.controllers;

import business.BusinessFacade;

import presentation.MainView;
import presentation.views.HomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController implements ActionListener {
    private final HomeView view;
    private final BusinessFacade businessFacade;
    private final MainView mainView;


    public HomeController(MainView mainView,HomeView view, BusinessFacade businessFacade){
        this.mainView = mainView;
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
                int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to Delete your account?","Warning",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    businessFacade.deleteAccount();
                    mainView.showRegisterView();
                }
            }

            case(HomeView.BTN_LOGOUT)->{
                System.out.println("Log Out");
                int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to Log Out?","Warning",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    businessFacade.logOut();
                    mainView.showRegisterView();
                }
            }

            case (HomeView.BTN_PLAYPAUSE) -> {
                view.pauseButton();
                System.out.println("Played");
            }

        }
    }

}
