package presentation.controllers;

import business.BusinessFacade;

import presentation.MainView;
import presentation.views.HomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * HomeController class manages the behaviour of the {@link HomeView} by implementing the {@link  ActionListener}
 * interface.
 *
 * @author Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 30/4/2022
 */
public class HomeController implements ActionListener {
    private final HomeView view;
    private final BusinessFacade businessFacade;
    private final MainView mainView;

    /**
     * Default HomeController Constructor that will link the views needed with {@link MainView} and the business
     * logic with the {@link BusinessFacade} interface.
     * @param mainView MainView object
     * @param businessFacade link to the logic of the program
     */
    public HomeController(MainView mainView, BusinessFacade businessFacade){
        this.mainView = mainView;
        this.view = mainView.getHomeView();
        this.businessFacade = businessFacade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case (HomeView.BTN_SONGS) -> {
                //Show songs list
                view.showSongsCard();
                view.getSongsView().showSongsTableCard();
            }
            case (HomeView.BTN_PLAYLISTS) -> {
                //Show Playlists
                view.showPlaylistsCard();
                view.getPlaylistsView().clearSongsPanel();
                view.getPlaylistsView().showPlaylistsPanelCard();
            }
            case (HomeView.BTN_STATISTICS) -> {
                //Show statistics
                view.showStatisticsCard();
            }

            case (HomeView.BTN_ADDSONG) -> {
                view.showAddSongsCard();
            }

            case (HomeView.BTN_DELETEACC) -> {
                int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to Delete your account?","Warning",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    businessFacade.deleteAccount();
                    mainView.showRegisterView();
                }
            }

            case(HomeView.BTN_LOGOUT)->{
                int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to Log Out?","Warning",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    view.getPlaylistsView().clearPlaylistsPanel();
                    if(businessFacade.isPlaying()){
                        businessFacade.stopPlayer();
                        view.getPlayerView().stopTimer();
                    }
                    view.getPlayerView().changeShownSong("","");
                    view.getPlayerView().changePlayPause(businessFacade.isPlaying());
                    view.getPlayerView().moveSliderPosition(0);
                    view.getPlayerView().updateCurrentTime(0);
                    businessFacade.logOut();
                    mainView.showRegisterView();
                }
            }
        }
    }

}
