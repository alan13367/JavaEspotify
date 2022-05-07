package presentation.controllers;

import business.BusinessFacade;

import business.entities.Song;
import presentation.MainView;
import presentation.views.AddSongsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AddSongsController implements ActionListener {
    private  AddSongsView view;
    private  BusinessFacade businessFacade;
    private Song song;
    private MainView mainView;
    private final JFileChooser fc = new JFileChooser();
    private File myFile = new File("/Users/marc/");
    private Component aComponent;

    public AddSongsController(AddSongsView view, BusinessFacade businessFacade) {
        this.view = view;
        this.businessFacade = businessFacade;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){

            case (AddSongsView.BTN_IMPORT_SONG) -> {
                System.out.println("Import songs button pressed");
                int returnVal = fc.showOpenDialog(aComponent);
                myFile.renameTo(new File("/Users/marc/IdeaProjects/dpoo-2122-s2-ice3/songs"));
            }
            case (AddSongsView.BTN_ADD_SONG) -> {
                System.out.println("Add songs button pressed");
                System.out.println(view.getTitleFieldAdd());
                System.out.println(view.getAuthorFieldAdd());
                System.out.println(view.getGenreFieldAdd());
                System.out.println(view.getDurationFieldAdd());
                System.out.println(view.getAlbumFieldAdd());
                System.out.println(myFile.getAbsolutePath());

                if (view.addSongsFieldEmpty()) {
                    view.pop_up_ErrorDialog("There can no be empty values", "Error");
                }
                else {
                    view.pop_up_SuccessDialog("Song added successfully", "Success");
                    businessFacade.addSong(view.getTitleFieldAdd(), view.getAuthorFieldAdd(), view.getGenreFieldAdd(), view.getAlbumFieldAdd(), myFile.getAbsolutePath(), view.getDurationFieldAdd());
                }
            }

        }
    }
}

//for (Song song: businessFacade.getSongs()){
//            view.addTableRow(song.getTitle(),song.getGenre(),song.getAlbum(),song.getAuthor(),song.getOwner());
//            if ((song.getTitle() == view.getTitleFieldAdd()) || song.getAuthor() == view.getAuthorFieldAdd())
//            {
//
//            }
//        }



