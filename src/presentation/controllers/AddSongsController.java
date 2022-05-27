package presentation.controllers;

import business.BusinessFacade;

import presentation.views.AddSongsView;
import presentation.views.HomeView;
import presentation.views.StatisticsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * AddSongsController class manages the behaviour of the {@link AddSongsView} by implementing the {@link  ActionListener}
 * interface.
 *
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 19/4/2022
 */
public class AddSongsController implements ActionListener {
    private final AddSongsView view;
    private final HomeView homeView;
    private final StatisticsView statisticsView;
    private final BusinessFacade businessFacade;

    /**
     * Default AddSongsController Constructor that will link the views needed with {@link HomeView} and the business
     * logic with the {@link BusinessFacade} interface.
     * @param homeView HomeView object
     * @param businessFacade link to the logic of the program
     */
    public AddSongsController(HomeView homeView,BusinessFacade businessFacade) {
        this.view = homeView.getAddSongsView();
        this.businessFacade = businessFacade;
        this.homeView = homeView;
        this.statisticsView = homeView.getStatisticsView();
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


