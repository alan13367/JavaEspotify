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
                    for (Song song: businessFacade.getSongs()){
                        view.addTableRow(song.getTitle(),song.getGenre(),song.getAlbum(),song.getAuthor(),song.getOwner());
                    }
                }
            }
            case(SongsView.BTN_CLOSE) -> {
                view.showSongsTableCard();
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
                String title = view.getSongTitleAtRow(index);
                String author = view.getSongAuthorAtRow(index);

                //Show song card with the song details
                //GetSong
                Song song = businessFacade.getSong(title,author);
                String lyrics = businessFacade.getLyrics(author,title);

                view.createLyricsPanel(lyrics);
                view.showSongCard(title,author,song.getAlbum(),song.getGenre(),
                        song.getSongMinutes() + ":" + (song.getSongSeconds() - (song.getSongMinutes() * 60)),song.getOwner());

            }
        }
    }
}
