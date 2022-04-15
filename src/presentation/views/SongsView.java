package presentation.views;

import presentation.controllers.SongsController;
import presentation.views.GUIassets.MyHintTextField;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class SongsView extends JPanel {

    private CardLayout cardManager;

    //SongsPanel
    private JPanel jpSongs;
    private JTable songsTable;
    private MyHintTextField searchField;
    private JButton jbSearch;
    private static final String[] columns ={"TITLE","GENRE","ALBUM","AUTHOR","OWNER"};
    private static final String SONGSTABLE_CARD = "SONGSTABLE_CARD";
    public static final String BTN_SEARCH = "BTN_SEARCH";

    //SongPanel
    private JPanel jpSong;
    private JButton jbClose;
    private JLabel jlTitle;
    private JLabel jlAuthor;
    private static final String SONGPANEL_CARD = "SONGPANEL_CARD";
    public static final String BTN_CLOSE = "BTN_CLOSE";


    public SongsView(){
        cardManager = new CardLayout();
        setLayout(cardManager);
        configureView();
    }
    private void configureView(){
        configureSongsPanel();
        configureSongPanel();
    }

    private void configureSongPanel() {
        //Configuration of a Song View Missing
        jpSong = new JPanel(new BorderLayout());
        jlAuthor = new JLabel();
        jlTitle = new JLabel();
        jpSong.add(jlTitle,BorderLayout.NORTH);
        jpSong.add(jlAuthor,BorderLayout.CENTER);
        jbClose = new JButton("Close");
        jbClose.setActionCommand(BTN_CLOSE);
        jpSong.add(jbClose,BorderLayout.SOUTH);
        add(jpSong,SONGPANEL_CARD);
    }

    private void configureSongsPanel(){
        jpSongs = new JPanel(new BorderLayout());
        jpSongs.setBackground(new Color(0,0,0));
        configureSearch();
        configureTable();
        add(jpSongs,SONGSTABLE_CARD);
    }

    private void configureTable(){
        String data[][]={
                {"Title1","Genre1","Album1","Artist1","User1"},
                {"Lithium","grunge","Nevermind","Nirvana","Alan"},
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


        JScrollPane jsp = new JScrollPane(songsTable);
        jsp.getViewport().setBackground(new Color(8,8,8));
        jsp.setBorder(BorderFactory.createEmptyBorder());
        jpSongs.add(jsp,BorderLayout.CENTER);
    }


    private void configureSearch(){
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(0,0,0));
        int i = 2;
        int j = 2;
        GridLayout gridLayout = new GridLayout(i,j);
        gridLayout.setHgap(400);
        searchPanel.setLayout(gridLayout);

        searchField = new MyHintTextField("Enter Title to Search Songs");
        searchPanel.add(searchField);
        jbSearch = new JButton("Search");
        jbSearch.setPreferredSize(new Dimension(30,0));
        jbSearch.setActionCommand(BTN_SEARCH);

        searchPanel.add(jbSearch);
        jpSongs.add(searchPanel,BorderLayout.NORTH);
    }


    public String getSongTitleAtRow(int index){
        return songsTable.getValueAt(index,0).toString();
    }

    public String getSongAuthorAtRow(int index){
        return songsTable.getValueAt(index,3).toString();
    }

   public void showSongCard(String title,String author){
        jlTitle.setText(title);
        jlAuthor.setText(author);
        cardManager.show(this,SONGPANEL_CARD);
   }

   public void showSongsTableCard(){
        cardManager.show(this,SONGSTABLE_CARD);
   }

    public void registerController(SongsController controller){
        jbSearch.addActionListener(controller);
        songsTable.getSelectionModel().addListSelectionListener(controller);
        jbClose.addActionListener(controller);
    }

}
