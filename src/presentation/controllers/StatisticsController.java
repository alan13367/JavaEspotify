package presentation.controllers;

import business.BusinessFacade;
import presentation.views.StatisticsView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsController{
    private final StatisticsView view;
    private final BusinessFacade businessFacade;

    public StatisticsController(StatisticsView view, BusinessFacade businessFacade) {
        this.view = view;
        this.businessFacade = businessFacade;

        ArrayList<String> stringArrayList = businessFacade.getStatsGenres();
        ArrayList<Integer> valueArrayList = businessFacade.getStatsValues();
        for (int i=0;i<stringArrayList.size();i++) {
            view.addBar(valueArrayList.get(i), stringArrayList.get(i));
        }
        view.plotBars();
    }

}
