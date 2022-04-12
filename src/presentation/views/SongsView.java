package presentation.views;

import presentation.views.GUIassets.MyHintTextField;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SongsView extends JPanel {

    private JTable songsTable;

    private MyHintTextField textField;

    private JButton jbSearch;

    public static final String BTN_SEARCH = "BTN_SEARCH";


    private static final String[] columns ={"TITLE","GENRE","ALBUM","AUTHOR","OWNER"};

    public SongsView(){
        configureTable();
        configureView();
        configureSearch();
    }

    private void configureTable(){
        String data[][]={
                {"Title1","Genre1","Album1","Artist1","User1"},
                {"ESTA","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"MUSICA","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"ES","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"TRASH","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"Una Vez","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"La Dificil","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"Que Malo","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"Callaita","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"Una Vez","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"La Dificil","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"Que Malo","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"Callaita","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"Una Vez","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"La Dificil","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"Que Malo","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"Callaita","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"Una Vez","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"La Dificil","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"Que Malo","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"Callaita","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"Una Vez","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
                {"La Dificil","Reggeaton","YHLQMDLG","Bad Bunny","Alan"},
        };



        songsTable = new JTable(data,columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        songsTable.getTableHeader().setReorderingAllowed(false);
        songsTable.setBackground(new Color(0,80,0));
        songsTable.setGridColor(Color.white);
        songsTable.setForeground(Color.white);
        songsTable.setFont(new Font("Tahome", Font.PLAIN,15));


        JTableHeader jTableHeader = songsTable.getTableHeader();
        jTableHeader.setBackground(new Color(0,204,0));
        jTableHeader.setForeground(Color.white);
        jTableHeader.setFont(new Font("Tahome", Font.BOLD, 20)); // font name style size
        ((DefaultTableCellRenderer)jTableHeader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER); // center header text
    }

    private void configureSearch(){
        JPanel jPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(1,2);
        gridLayout.setHgap(20);
        jPanel.setLayout(gridLayout);
        textField = new MyHintTextField("Enter Title to Search Songs");
        jPanel.add(textField);
        jbSearch = new JButton("Search");
        jbSearch.setPreferredSize(new Dimension(30,0));
        jbSearch.setActionCommand(BTN_SEARCH);
        jPanel.add(jbSearch);
        add(jPanel,BorderLayout.NORTH);
    }

    public void configureView(){
        setBackground(new Color(0,0,0));
        setLayout(new BorderLayout());
        JScrollPane jsp = new JScrollPane(songsTable);
        jsp.getViewport().setBackground(new Color(8,8,8));
        jsp.setBorder(BorderFactory.createEmptyBorder());
        add(jsp,BorderLayout.CENTER);
    }



    public void registerController(ActionListener controller){
        jbSearch.addActionListener(controller);
    }
}
