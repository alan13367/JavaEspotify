package presentation.controllers;

import business.BusinessFacade;
import business.entities.Song;

import presentation.views.SongsView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SongsController implements ActionListener, ListSelectionListener {

    private final SongsView view;
    private final BusinessFacade businessFacade;

    public SongsController(SongsView view,BusinessFacade businessFacade){
        this.view = view;
        this.businessFacade = businessFacade;
        loadSongs();
    }

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
                }else{
                    view.clearTable();
                    loadSongs();
                }
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
                    }
                }
                else{
                    view.showOwnerErrorDialog();
                }
            }
            case(SongsView.BTN_PLAY_SONG) ->{
                String title = view.getSongTitle();
                String author = view.getSongAuthor();
                businessFacade.startPlayer(businessFacade.getSong(title,author));

            }

            case (SongsView.BTN_ADD_TO_PLAYLIST)->{
                String title = view.getSongTitle();
                String author = view.getSongAuthor();
                JComboBox<String> jComboBox = new JComboBox<>(businessFacade.getUserPlaylistsNames());
                if(jComboBox.getItemCount() > 0){
                    int option = view.showPlaylistPickerDialog(jComboBox);
                    if (option ==JOptionPane.YES_OPTION){
                        System.out.println(jComboBox.getSelectedItem());
                        businessFacade.addSongToPlaylist((String) jComboBox.getSelectedItem(),businessFacade.getSong(title,author));
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
                String lyrics = businessFacade.getLyrics(song.getAuthor(),song.getTitle());

                view.createLyricsPanel(lyrics);
                view.showSongCard(song.getTitle(),song.getAuthor(),song.getAlbum(),song.getGenre(),
                        song.getSongMinutes() + ":" + (song.getSongSeconds() - (song.getSongMinutes() * 60)),song.getOwner());

            }
        }
    }
}
