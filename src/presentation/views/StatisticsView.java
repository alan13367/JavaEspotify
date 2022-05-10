package presentation.views;

import presentation.controllers.StatisticsController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StatisticsView extends JPanel {


    private StatisticsController statisticsController;
    public void registerController(StatisticsController statisticsController) {
        this.statisticsController = statisticsController;
    }


    private CardLayout cardManager;

    private JPanel title;
    private JPanel jpGraph;
    private JPanel x_axis;

    private JLabel chart_label;
    private JLabel genre_label;
    private JLabel title_label;

    private ArrayList<Chart> charts;

    private int HGap = 10;
    private int VGap = 0;

    private int height = 500;
    private int chart_width = 100;

    private int Max_value;

    private static final String CARD_STATISTICS = "CARD_STATISTICS";



    public StatisticsView() {
        cardManager = new CardLayout();
        charts = new ArrayList<>();
        setLayout(cardManager);
        convigureView();
    }

    private void convigureView() {
        jpGraph = new JPanel(new GridLayout(1, 0, HGap, VGap));
        jpGraph.setBackground(new Color(16,16,16));
        Border compoundBorder = new CompoundBorder(new MatteBorder(1, 1, 1, 1, new Color(16,16,16)), new EmptyBorder(10, 10, 0, 10));
        jpGraph.setBorder(compoundBorder);

        x_axis = new JPanel(new GridLayout(1, 0, HGap, VGap));
        x_axis.setBackground(new Color(16,16,16));

        title = new JPanel(new GridLayout(1, 0, HGap, VGap));
        title.setBackground(new Color(0,204,0));

        setBackground(new Color(16,16,16));
        setLayout(new BorderLayout());

        add(jpGraph, BorderLayout.CENTER);
        add(title, BorderLayout.PAGE_START);
        add(x_axis, BorderLayout.PAGE_END);
    }

    public void addBar(int value, String genre) {
        Chart chart = new Chart(value, genre);
        charts.add(chart);
    }

    public void plotBars() {
        x_axis.removeAll();
        jpGraph.removeAll();

        Max_value = 0;
        for (Chart chart : charts) {
            Max_value = Math.max(Max_value, chart.getValue());
        }

        //sort the list
        charts.sort(new Comparator<Chart>() {
            public int compare(Chart chart, Chart chart1) {
                return Integer.compare(chart1.value, chart.value);
            }
        });

        for(int i=0;i< charts.size();i++) {
            chart_label = new JLabel(String.valueOf(charts.get(i).getValue()));
            chart_label.setForeground(Color.WHITE);
            chart_label.setHorizontalAlignment(JLabel.CENTER);
            chart_label.setVerticalTextPosition(JLabel.TOP);
            chart_label.setVerticalAlignment(JLabel.BOTTOM);
            int finalI = i;
            chart_label.setIcon(new Icon() {
                @Override
                public void paintIcon(Component c, Graphics g, int x, int y) {
                    g.setColor(randomColor());
                    g.fill3DRect(x, y, getIconWidth() - 2, getIconHeight(), true);
                    g.setColor(new Color(56, 56, 56, 255));
                }

                @Override
                public int getIconWidth() {
                    return chart_width;
                }

                @Override
                public int getIconHeight() {
                    return charts.get(finalI).getValue() * height / Max_value;
                }
            });


            jpGraph.add(chart_label);
            genre_label = new JLabel( charts.get(i).getGenre() );
            genre_label.setForeground(Color.WHITE);
            genre_label.setHorizontalAlignment(JLabel.CENTER);

            x_axis.add( genre_label );

        }

        title_label = new JLabel("Music Statistics");
        title_label.setForeground(Color.WHITE);
        title_label.setHorizontalAlignment(JLabel.CENTER);
        title_label.setFont(new Font("Arial", Font.BOLD, 40));
        title.add(title_label);
    }

    public Color randomColor() {
        return new Color((int)(Math.random() * 0x1000000));
    }

    public static class Chart {
        private int value;
        private String genre;

        public Chart(int value, String genre) {
            this.value = value;
            this.genre = genre;
        }

        public int getValue() {return value;}
        public void setValue(int value) {
            this.value = value;
        }
        public String getGenre() {
            return genre;
        }
        public void setGenre(String genre) {
            this.genre = genre;
        }
    }


}
