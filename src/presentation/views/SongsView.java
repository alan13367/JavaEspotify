package presentation.views;

import presentation.controllers.SongsController;
import presentation.views.GUIassets.MyHintTextField;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SongsView extends JPanel {

    private static final int TIME_VISIBLE = 1000;
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
    private JLabel jlDuration;
    private JLabel jlAlbum;
    private JLabel jlOwner;
    private JLabel jlGenre;
    private JPanel jpLyrics;
    private JScrollPane lyricsPane;
    private JButton jbClose;
    private JButton jbPlay;
    private JButton jbAddToPlaylist;
    private JButton jbDelete;
    private static final String SONGPANEL_CARD = "SONGPANEL_CARD";
    public static final String BTN_PLAY_SONG = "BTN_PLAY_SONG";
    public static final String BTN_ADD_TO_PLAYLIST = "BTN_ADD_TO_PLAYLIST";
    public static final String BTN_DELETE_SONG = "BTN_DELETE_SONG";
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

        JPanel songInfoPanel = new JPanel();
        GridLayout gridLayout1 = new GridLayout(3,3);
        gridLayout1.setHgap(30);
        gridLayout1.setVgap(30);
        songInfoPanel.setBackground(new Color(16,16,16));
        songInfoPanel.setLayout(gridLayout1);
        jlTitle = createSongLabels(35);
        jlDuration = createSongLabels(25);
        jlAuthor = createSongLabels(25);
        jlAlbum = createSongLabels(25);
        jlGenre =createSongLabels(25);
        jlOwner = createSongLabels(25);
        songInfoPanel.add(jlTitle);
        songInfoPanel.add(jlAlbum);
        songInfoPanel.add(jlDuration);
        songInfoPanel.add(jlAuthor);
        songInfoPanel.add(jlGenre);
        songInfoPanel.add(jlOwner);
        JPanel jPanelAux = new JPanel();
        jPanelAux.setBackground(new Color(16,16,16));
        songInfoPanel.add(jPanelAux);
        topPanel.add(songInfoPanel,BorderLayout.CENTER);

        //Lyrics


        //Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(16,16,16));
        GridLayout gridLayout = new GridLayout(1,3);
        gridLayout.setHgap(50);
        buttonPanel.setLayout(gridLayout);

        jbPlay = new JButton(" Play Song",new ImageIcon("assets/play-32.png"));
        jbPlay.setBackground(new Color(0,80,0));
        jbPlay.setForeground(Color.white);
        jbPlay.setFont(new Font("Arial",Font.BOLD,20));
        jbPlay.setActionCommand(BTN_PLAY_SONG);

        jbAddToPlaylist = new JButton(" Add To PlayList",new ImageIcon("assets/plus-4-32.png"));
        jbAddToPlaylist.setBackground(new Color(0,80,0));
        jbAddToPlaylist.setFont(new Font("Arial",Font.BOLD,20));
        jbAddToPlaylist.setForeground(Color.white);
        jbAddToPlaylist.setActionCommand(BTN_ADD_TO_PLAYLIST);

        jbDelete = new JButton(" Delete Song",new ImageIcon("assets/trashicon32.png"));
        jbDelete.setBackground(new Color(0,80,0));
        jbDelete.setForeground(Color.white);
        jbDelete.setFont(new Font("Arial",Font.BOLD,20));
        jbDelete.setActionCommand(BTN_DELETE_SONG);

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

    private JLabel createSongLabels(int size){
        JLabel label = new JLabel();
        label.setFont(new Font("Arial",Font.BOLD,size));
        label.setForeground(Color.white);
        return label;
    }

    private void configureSongsPanel(){
        jpSongs = new JPanel(new BorderLayout());
        jpSongs.setBackground(new Color(0,0,0));
        configureSearch();
        configureTable();
        add(jpSongs,SONGSTABLE_CARD);
    }

    private void configureTable(){

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
        searchField.setFont(new Font("Tahome",Font.PLAIN,25));
        searchPanel.add(searchField,BorderLayout.CENTER);
        jbSearch = new JButton("Search",new ImageIcon("assets/lupa32.png"));
        jbSearch.setFont(new Font("Arial",Font.PLAIN,25));
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

   public void showSongCard(String title,String author,String album,String genre,String duration,String owner){
        jlTitle.setText("   "+title);
        jlAuthor.setText("    "+author);
        jlAlbum.setText("Album:  "+album);
        jlGenre.setText("Genre:  "+genre);
        jlDuration.setText("Duration:  "+duration);
        jlOwner.setText("Owner:  "+owner);
        cardManager.show(this,SONGPANEL_CARD);
   }

   public void showSongsTableCard(){
        if(lyricsPane!=null)
            jpSong.remove(lyricsPane);
        cardManager.show(this,SONGSTABLE_CARD);
   }


    public void registerController(SongsController controller){
        jbSearch.addActionListener(controller);
        songsTable.getSelectionModel().addListSelectionListener(controller);
        jbClose.addActionListener(controller);
        jbPlay.addActionListener(controller);
        jbAddToPlaylist.addActionListener(controller);
        jbDelete.addActionListener(controller);
    }

    public void createLyricsPanel(String lyrics){
        jpLyrics = new JPanel();
        jpLyrics.setLayout(new BoxLayout(jpLyrics,BoxLayout.Y_AXIS));
        jpLyrics.setBackground(new Color(16,16,16));
        if(lyrics != null){
            String[] songVerses = lyrics.split("\\r?\\n");
            for (String s:songVerses) {
                JLabel jl = createSongLabels(15);
                jl.setAlignmentX(Component.CENTER_ALIGNMENT);
                jl.setText(s);
                jpLyrics.add(jl);
            }
        }else {
            JLabel jl = createSongLabels(30);
            jl.setAlignmentX(Component.CENTER_ALIGNMENT);
            jl.setText("No Lyrics Found");
            jpLyrics.add(jl);
        }

        lyricsPane = new JScrollPane(jpLyrics,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        lyricsPane.setBackground(new Color(16,16,16));
        lyricsPane.setBorder(BorderFactory.createEmptyBorder());
        jpSong.add(lyricsPane,BorderLayout.CENTER);
        revalidate();
    }

    public void showLoadingDialog(){
        JOptionPane pane = new JOptionPane("Loading",
                JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog(this, "");
        dialog.setModal(false);
        dialog.setVisible(true);

        new Timer(TIME_VISIBLE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        }).start();
    }

    public String getSongTitle(){
        return jlTitle.getText().trim();
    }

    public String getSongAuthor(){
        return jlAuthor.getText().trim();
    }

    public void showOwnerErrorDialog(){
        JOptionPane.showMessageDialog(this, "You can't delete this Song because you are not the owner.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}


