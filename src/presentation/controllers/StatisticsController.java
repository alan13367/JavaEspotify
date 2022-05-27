package presentation.controllers;

import business.BusinessFacade;
import presentation.MainView;
import presentation.views.HomeView;
import presentation.views.StatisticsView;


import java.util.ArrayList;


/**
 * SongsController class manages the behaviour of the {@link StatisticsView}.
 *
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 25/4/2022
 */
public class StatisticsController{
    private final StatisticsView view;
    private final BusinessFacade businessFacade;

    /**
     * Default StatisticsController Constructor that will link the views needed with {@link MainView} and the business
     * logic with the {@link BusinessFacade} interface.
     * @param homeView HomeView reference
     * @param businessFacade link to the logic of the program
     */
    public StatisticsController(HomeView homeView, BusinessFacade businessFacade) {
        this.view = homeView.getStatisticsView();
        this.businessFacade = businessFacade;

        ArrayList<String> stringArrayList = businessFacade.getStatsGenres();
        ArrayList<Integer> valueArrayList = businessFacade.getStatsValues();
        for (int i=0;i<stringArrayList.size();i++) {
            view.addBar(valueArrayList.get(i), stringArrayList.get(i));
        }
        view.plotBars();
    }

}
