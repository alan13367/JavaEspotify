package presentation.controllers;

import business.BusinessFacade;
import business.entities.Song;

import presentation.MainView;
import presentation.views.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;



/**
 * SongsController class manages the behaviour of the {@link SongsView} by implementing the {@link  ActionListener}
 * interface and the {@link ListSelectionListener} interface.
 *
 * @author Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 25/4/2022
 */
public class SongsController implements ActionListener, ListSelectionListener {

    private final SongsView view;
    private final PlayerView playerView;
    private final BusinessFacade businessFacade;
    private final StatisticsView statisticsView;

    /**
     * Default SongsController Constructor that will link the views needed with {@link MainView} and the business
     * logic with the {@link BusinessFacade} interface.
     * @param homeView HomeView reference
     * @param businessFacade link to the logic of the program
     */
    public SongsController(HomeView homeView, BusinessFacade businessFacade){
        this.view = homeView.getSongsView();
        this.businessFacade = businessFacade;
        this.statisticsView = homeView.getStatisticsView();
        this.playerView = homeView.getPlayerView();
        loadSongs();
    }

    /**
     * Method that will load the songs into the JTable graphical component of the SongsView
     */
    private void loadSongs(){
        for (Song song: businessFacade.getSongs()){
            view.addTableRow(song.getTitle(),song.getGenre(),song.getAlbum(),song.getAuthor(),song.getOwner());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case (SongsView.BTN_SEARCH) -> {
                System.out.println("SEARCH");
                if(!view.searchFieldEmpty()){
                    view.clearTable();
                    for (Song song: businessFacade.getSongs()){
                        if(song.getTitle().contains(view.getSearchField())){
                            view.addTableRow(song.getTitle(),song.getGenre(),song.getAlbum(),song.getAuthor(),song.getOwner());
                        }
                    }
                }
            }

            case(SongsView.BTN_REFRESH)->{
                view.clearTable();
                loadSongs();
            }
            case(SongsView.BTN_CLOSE) -> {
                view.showSongsTableCard();
            }

            case(SongsView.BTN_DELETE_SONG) -> {
                String title = view.getSongTitle();
                String author = view.getSongAuthor();
                if(businessFacade.getSong(title,author).getOwner().equals(businessFacade.getCurrentUser())){
                    int dialogResult = view.showSongDeleteDialog();
                    if(dialogResult == JOptionPane.YES_OPTION){
                        businessFacade.deleteSong(title,author);
                        view.clearTable();
                        loadSongs();
                        view.showSongsTableCard();

                        //update stats
                        ArrayList<String> stringArrayList = businessFacade.getStatsGenres();
                        ArrayList<Integer> valueArrayList = businessFacade.getStatsValues();
                        statisticsView.removeAllBars();
                        for (int i=0;i<stringArrayList.size();i++) {
                            statisticsView.addBar(valueArrayList.get(i), stringArrayList.get(i));
                        }
                        statisticsView.plotBars();
                    }
                }
                else{
                    view.showErrorDialog("You can't delete this Song because you are not the owner.");
                }
            }
            case(SongsView.BTN_PLAY_SONG) ->{
                businessFacade.setPlayingPlaylist(false);
                String title = view.getSongTitle();
                String author = view.getSongAuthor();
                //businessFacade.startPlayer(businessFacade.getSong(title,author));
                try {
                    businessFacade.playSong(title,author);
                    playerView.changePlayPause(businessFacade.isPlaying());
                    playerView.changeShownSong(title,author);
                    playerView.changeTotalTime(businessFacade.getSong(title, author).getSongMinutes(), businessFacade.getSong(title, author).getSongSeconds());
                    playerView.startTimer(businessFacade.getSong(title, author).getSongSeconds());
                } catch (FileNotFoundException ex) {
                    view.showErrorDialog("File for song: "+ title+" was not found.");
                }
            }

            case (SongsView.BTN_ADD_TO_PLAYLIST)->{
                String title = view.getSongTitle();
                String author = view.getSongAuthor();
                JComboBox<String> jComboBox = new JComboBox<>(businessFacade.getUserPlaylistsNames());
                if(jComboBox.getItemCount() > 0){
                    int option = view.showPlaylistPickerDialog(jComboBox);
                    if (option ==JOptionPane.YES_OPTION){
                        System.out.println(jComboBox.getSelectedItem());
                        Song song = businessFacade.getSong(title,author);
                        boolean exists = false;
                        for (Song s:businessFacade.getSongsFromPlaylist((String) jComboBox.getSelectedItem()
                                ,businessFacade.getCurrentUser())){
                            if(song.getTitle().equals(s.getTitle())&&song.getAuthor().equals(s.getAuthor())){
                                exists = true;
                                view.showErrorDialog("Song Already Exists in the playlist!!");
                                break;
                            }
                        }
                        if(!exists){
                            businessFacade.addSongToPlaylist((String) jComboBox.getSelectedItem(),song);
                        }

                    }
                }else {
                    view.showPlaylistsErrorDialog();
                }
            }
        }
    }



    @Override
    public void valueChanged(ListSelectionEvent e) {
        //view.showLoadingDialog();
        if(!e.getValueIsAdjusting()){
            DefaultListSelectionModel songsTable = ((DefaultListSelectionModel)e.getSource());
            if (songsTable.getMaxSelectionIndex() > -1) {
                // print first column value from selected row
                int index = songsTable.getMaxSelectionIndex();
                //Show song card with the song details
                //GetSong
                Song song = businessFacade.getSong(view.getSongTitleAtRow(index),view.getSongAuthorAtRow(index));
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // code goes here.
                        String lyrics = businessFacade.getLyrics(song.getAuthor(),song.getTitle());
                        view.createLyricsPanel(lyrics);
                    }
                });
                t1.start();

                view.showSongCard(song.getTitle(),song.getAuthor(),song.getAlbum(),song.getGenre(),
                        song.getSongMinutes() + ":" + (song.getSongSeconds() - (song.getSongMinutes() * 60)),song.getOwner());

            }
        }
    }

}
