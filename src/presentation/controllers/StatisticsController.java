package presentation.controllers;

import business.BusinessFacade;
import business.ModelFacade;
import presentation.views.HomeView;
import presentation.views.StatisticsView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsController{
    private final StatisticsView view;
    private final BusinessFacade businessFacade;

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
