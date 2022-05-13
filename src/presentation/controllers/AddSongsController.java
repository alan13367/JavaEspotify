package presentation.controllers;

import business.BusinessFacade;

import presentation.views.AddSongsView;
import presentation.views.HomeView;
import presentation.views.StatisticsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AddSongsController implements ActionListener {
    private  AddSongsView view;
    private HomeView homeView;
    private StatisticsView statisticsView;
    private  BusinessFacade businessFacade;


    public AddSongsController(AddSongsView view, HomeView homeView,BusinessFacade businessFacade, StatisticsView statisticsView) {
        this.view = view;
        this.businessFacade = businessFacade;
        this.homeView = homeView;
        this.statisticsView = statisticsView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){

            case (AddSongsView.BTN_IMPORT_SONG) -> {
                System.out.println("Import songs button pressed");
                view.showFileChooser();
            }
            case (AddSongsView.BTN_ADD_SONG) -> {
                System.out.println("Add songs button pressed");
                System.out.println(view.getTitleFieldAdd());
                System.out.println(view.getAuthorFieldAdd());
                System.out.println(view.getGenreFieldAdd());
                System.out.println(view.getDurationFieldAdd());
                System.out.println(view.getAlbumFieldAdd());

                if (view.addSongsFieldEmpty()) {
                    view.pop_up_ErrorDialog("There can no be empty values", "Error");
                }
                else {
                    String filename = view.getFilename();
                    System.out.println(filename);
                    File file = new File(view.getFilePath());
                    file.renameTo(new File("songs/" + view.getFilename()));
                    businessFacade.addSong(view.getTitleFieldAdd(),view.getAlbumFieldAdd(),view.getGenreFieldAdd()
                            ,view.getAuthorFieldAdd(),"songs/"+view.getFilename(),view.getDurationFieldAdd());
                    view.pop_up_SuccessDialog("Song added successfully", "Success");
                    view.clearFields();
                    homeView.showSongsCard();


                    //stats update
                    ArrayList<String> stringArrayList = businessFacade.getStatsGenres();
                    ArrayList<Integer> valueArrayList = businessFacade.getStatsValues();
                    statisticsView.removeAllBars();
                    for (int i=0;i<stringArrayList.size();i++) {
                        statisticsView.addBar(valueArrayList.get(i), stringArrayList.get(i));
                    }
                    statisticsView.plotBars();

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



