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
    private DefaultTableModel model;
    private MyHintTextField searchField;
    private JButton jbSearch;
    private static final String[] columns ={"TITLE","GENRE","ALBUM","AUTHOR","OWNER"};
    private static final String HINT_TEXTFIELD ="Enter Title to Search Songs";
    private static final String SONGSTABLE_CARD = "SONGSTABLE_CARD";
    public static final String BTN_SEARCH = "BTN_SEARCH";

    //SongPanel
    private JPanel jpSong;
    private JLabel jlTitle;
    private JLabel jlAuthor;
    private JButton jbClose;
    private JButton jbPlay;
    private JButton jbAddToPlaylist;
    private JButton jbDelete;
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
        jpSong.setBackground(new Color(16,16,16));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(16,16,16));
        jbClose = new JButton(new ImageIcon("assets/x-mark-3-32.png"));
        jbClose.setOpaque(false);
        jbClose.setContentAreaFilled(false);
        jbClose.setBorderPainted(false);
        jbClose.setActionCommand(BTN_CLOSE);
        topPanel.add(jbClose,BorderLayout.LINE_END);
        jpSong.add(topPanel,BorderLayout.PAGE_START);


        jlTitle = new JLabel();
        jlTitle.setFont(new Font("Arial",Font.BOLD,25));
        jlTitle.setForeground(Color.white);
        jlAuthor = new JLabel();
        jlAuthor.setFont(new Font("Arial",Font.BOLD,20));
        jlAuthor.setForeground(Color.white);
        topPanel.add(jlTitle,BorderLayout.LINE_START);
        jpSong.add(jlAuthor,BorderLayout.CENTER);

        //Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(16,16,16));
        GridLayout gridLayout = new GridLayout(1,3);
        gridLayout.setHgap(50);
        buttonPanel.setLayout(gridLayout);

        jbPlay = new JButton("Play Song",new ImageIcon("assets/play-32.png"));
        jbPlay.setBackground(new Color(0,80,0));
        jbPlay.setForeground(Color.white);
        jbPlay.setFont(new Font("Arial",Font.BOLD,20));
        jbAddToPlaylist = new JButton("Add To PlayList");
        jbAddToPlaylist.setBackground(new Color(0,80,0));
        jbAddToPlaylist.setFont(new Font("Arial",Font.BOLD,20));
        jbAddToPlaylist.setForeground(Color.white);

        jbDelete = new JButton("Delete Song",new ImageIcon("assets/trashicon32.png"));
        jbDelete.setBackground(new Color(0,80,0));
        jbDelete.setForeground(Color.white);
        jbDelete.setFont(new Font("Arial",Font.BOLD,20));

        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(16,16,16));
        JPanel jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(16,16,16));
        JPanel jPanel2 = new JPanel();
        jPanel2.setBackground(new Color(16,16,16));
        buttonPanel.add(jbPlay);
        buttonPanel.add(jbAddToPlaylist);
        buttonPanel.add(jbDelete);


        jpSong.add(buttonPanel,BorderLayout.SOUTH);
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


        String data1[][] = new String[0][0];
        songsTable = new JTable(new DefaultTableModel(columns,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        });
        songsTable.getTableHeader().setReorderingAllowed(false);
        songsTable.setBackground(new Color(0,80,0));
        songsTable.setGridColor(Color.white);
        songsTable.setForeground(Color.white);
        songsTable.setFont(new Font("Tahome", Font.PLAIN,20));
        songsTable.setRowHeight(30);



        JTableHeader jTableHeader = songsTable.getTableHeader();
        jTableHeader.setBackground(new Color(0,204,0));
        jTableHeader.setForeground(Color.white);
        jTableHeader.setFont(new Font("Tahome", Font.BOLD, 25)); // font name style size
        ((DefaultTableCellRenderer)jTableHeader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER); // center header text


        JScrollPane jsp = new JScrollPane(songsTable);
        jsp.getViewport().setBackground(new Color(8,8,8));
        jsp.setBorder(BorderFactory.createEmptyBorder());
        jpSongs.add(jsp,BorderLayout.CENTER);

        model = (DefaultTableModel) songsTable.getModel();
    }




    private void configureSearch(){
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(new Color(0,0,0));

        searchField = new MyHintTextField.RoundedMyHintTextField(HINT_TEXTFIELD);
        searchField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        searchField.setFont(new Font("Tahome",Font.PLAIN,20));
        searchPanel.add(searchField,BorderLayout.CENTER);
        jbSearch = new JButton("Search",new ImageIcon("assets/lupa32.png"));
        jbSearch.setForeground(Color.white);
        jbSearch.setBackground(new Color(0,80,0));
        jbSearch.setActionCommand(BTN_SEARCH);

        searchPanel.add(jbSearch,BorderLayout.LINE_END);
        jpSongs.add(searchPanel,BorderLayout.NORTH);
    }

    public boolean searchFieldEmpty(){
        return searchField.getText().isEmpty() || searchField.getText().equals(HINT_TEXTFIELD);
    }

    public String getSearchField(){
        return searchField.getText().trim();
    }


    public String getSongTitleAtRow(int index){
        return songsTable.getValueAt(index,0).toString();
    }

    public String getSongAuthorAtRow(int index){
        return songsTable.getValueAt(index,3).toString();
    }

    public void addTableRow(String title,String genre,String album,String author,String owner){
        model.addRow(new String[]{title,genre,album,author,owner});
    }

    public void clearTable(){
        model.setRowCount(0);
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
