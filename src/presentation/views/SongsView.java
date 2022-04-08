package presentation.views;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.print.Printable;
import java.text.MessageFormat;

public class SongsView extends JPanel {

    private JTable songsTable;

    private JTextField textField;


    private static final String[] columns ={"TITLE","GENRE","ALBUM","AUTHOR","OWNER"};

    public SongsView(){
        configureTable();
        configureView();
    }

    public void configureTable(){
        String data[][]={
                {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},
                {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},
                {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},
                {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"}, {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},
                {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"}, {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},
                {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"}, {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},
                {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"}, {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},
                {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"}, {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},
                {"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"},{"101","Amit","670000","ASF","DASFA"},
                {"102","Jai","780000","ASF","DASFA"},
                {"101","Sachin","700000","ASF","DASFA"}
        };

        songsTable = new JTable(data,columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        songsTable.getTableHeader().setReorderingAllowed(false);
        songsTable.setBackground(new Color(0,60,0));
        songsTable.setGridColor(Color.white);

        JTableHeader jTableHeader = songsTable.getTableHeader();
        jTableHeader.setBackground(new Color(0,204,0));
        jTableHeader.setForeground(Color.white);
        jTableHeader.setFont(new Font("Tahome", Font.BOLD, 20)); // font name style size
        ((DefaultTableCellRenderer)jTableHeader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER); // center header text

        songsTable.setForeground(Color.white);


    }

    public void configureView(){
        setLayout(new GridLayout());
        JScrollPane jsp = new JScrollPane(songsTable);
        add(jsp);
    }
}
