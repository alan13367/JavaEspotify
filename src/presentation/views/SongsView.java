package presentation.views;

import presentation.controllers.SongsController;
import presentation.views.GUIassets.MyHintTextField;
import presentation.views.GUIassets.MyScrollBarUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;

/**
 * the GUI of the songs panel
 * @author Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 04/05/2022
 */
public class SongsView extends JPanel {

    private CardLayout cardManager;

    //SongsPanel
    private JPanel jpSongs;
    private JTable songsTable;
    private DefaultTableModel model;
    private JTextField searchField;
    private JButton jbSearch,jbRefresh;
    private static final String[] columns ={"TITLE","GENRE","ALBUM","AUTHOR","OWNER"};
    private static final String HINT_TEXTFIELD ="Enter Title to Search Songs";
    private static final String SONGSTABLE_CARD = "SONGSTABLE_CARD";
    public static final String BTN_SEARCH = "BTN_SEARCH";
    public static final String BTN_REFRESH = "BTN_REFRESH";

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


    /**
     * The view constructor, sets the layout of the panel and configures view, also calls a new card layout
     */
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
        jpSongs.setBackground(new Color(16,16,16));
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
        songsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableHeader jTableHeader = songsTable.getTableHeader();
        jTableHeader.setBackground(new Color(0,204,0));
        jTableHeader.setForeground(Color.white);
        jTableHeader.setFont(new Font("Tahome", Font.BOLD, 25)); // font name style size
        ((DefaultTableCellRenderer)jTableHeader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER); // center header text


        JScrollPane jsp = new JScrollPane(songsTable);
        jsp.getViewport().setBackground(new Color(16,16,16));
        jsp.setBorder(BorderFactory.createEmptyBorder());
        jsp.getVerticalScrollBar().setUI(new MyScrollBarUI());
        JPanel panel = new JPanel();
        panel.setBackground(new Color(16,16,16));
        jsp.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
        jpSongs.setBorder(new EmptyBorder(0,20,0,20));
        jpSongs.add(jsp,BorderLayout.CENTER);

        model = (DefaultTableModel) songsTable.getModel();
    }

    private void configureSearch(){
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(new EmptyBorder(10,0,20,0));
        JPanel buttonsPanel = new JPanel(new GridLayout(1,3,20,0));
        buttonsPanel.setBackground(new Color(16,16,16));
        buttonsPanel.setBorder(new EmptyBorder(0,25,0,50));
        searchPanel.setBackground(new Color(16,16,16));

        searchField = new MyHintTextField.RoundedMyHintTextField(HINT_TEXTFIELD);
        searchField.setFont(new Font("Tahome",Font.PLAIN,25));
        searchField.setPreferredSize(new Dimension(1000,30));
        searchPanel.add(searchField,BorderLayout.CENTER);
        jbSearch = new JButton(new ImageIcon("assets/lupa32.png"));
        jbSearch.setBackground(null);
        jbSearch.setContentAreaFilled(false);
        jbSearch.setActionCommand(BTN_SEARCH);
        jbRefresh = new JButton(new ImageIcon("assets/refresh-32.png"));
        jbRefresh.setBackground(null);
        jbRefresh.setContentAreaFilled(false);
        jbRefresh.setActionCommand(BTN_REFRESH);
        buttonsPanel.add(jbSearch);
        buttonsPanel.add(jbRefresh);
        searchPanel.add(buttonsPanel,BorderLayout.LINE_END);
        jpSongs.add(searchPanel,BorderLayout.NORTH);
    }

    /**
     * checks if the search text field is empty.
     * @return true if the search text field is empty, false otherwise
     */
    public boolean searchFieldEmpty(){
        return searchField.getText().isEmpty() || searchField.getText().equals(HINT_TEXTFIELD);
    }

    /**
     * gets the text the user is searching
     * @return whatever the user has entered in the search text field
     */
    public String getSearchField(){
        return searchField.getText().trim();
    }

    /**
     * looks up for a song title by row position
     * @param index a row in the songs table
     * @return the title of the song at row position 'index'
     */
    public String getSongTitleAtRow(int index){
        return songsTable.getValueAt(index,0).toString();
    }

    /**
     * looks up for a song author by row position
     * @param index a row in the songs table
     * @return the author of the song at row position 'index'
     */
    public String getSongAuthorAtRow(int index){
        return songsTable.getValueAt(index,3).toString();
    }

    /**
     * adds a new song in the table
     * @param title title of song
     * @param genre genre of song
     * @param album album of song
     * @param author author of song
     * @param owner owner of song
     */
    public void addTableRow(String title,String genre,String album,String author,String owner){
        model.addRow(new String[]{title,genre,album,author,owner});
    }

    /**
     * set the table to be empty of rows
     */
    public void clearTable(){
        model.setRowCount(0);
    }

    /**
     * shows the details of a song
     * @param title title of song
     * @param genre genre of song
     * @param album album of song
     * @param author author of song
     * @param owner owner of song
     * @param duration duration of song
     */
   public void showSongCard(String title,String author,String album,String genre,String duration,String owner){
        jlTitle.setText("   "+title);
        jlAuthor.setText("    "+author);
        jlAlbum.setText("Album:  "+album);
        jlGenre.setText("Genre:  "+genre);
        jlDuration.setText("Duration:  "+duration);
        jlOwner.setText("Owner:  "+owner);
        jpLyrics = new JPanel(new BorderLayout());
        jpLyrics.setBackground(new Color(16,16,16));
        jpLyrics.add(new JLabel(new ImageIcon("assets/loading1.gif")),BorderLayout.CENTER);
        jpSong.add(jpLyrics,BorderLayout.CENTER);
        validate();
        repaint();
        cardManager.show(this,SONGPANEL_CARD);
   }

    /**
     * shows the song details along with the lyrics if exists
     */
   public void showSongsTableCard(){
        if(lyricsPane!=null)
            jpSong.remove(lyricsPane);
        cardManager.show(this,SONGSTABLE_CARD);
   }

    /**
     * adding action listeners to all the components of the panel that have to make a change when clicked
     * @param controller handler of all action events
     */
    public void registerController(SongsController controller){
        jbSearch.addActionListener(controller);
        jbRefresh.addActionListener(controller);
        songsTable.getSelectionModel().addListSelectionListener(controller);
        jbClose.addActionListener(controller);
        jbPlay.addActionListener(controller);
        jbAddToPlaylist.addActionListener(controller);
        jbDelete.addActionListener(controller);
    }

    /**
     * an internal panel that contains the lyrics of a song
     * @param lyrics lyrics of a song
     */
    public void createLyricsPanel(String lyrics){
        jpSong.remove(jpLyrics);
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
        lyricsPane.getViewport().setBackground(new Color(16,16,16));
        lyricsPane.setBorder(BorderFactory.createEmptyBorder());
        lyricsPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
        jpSong.add(lyricsPane,BorderLayout.CENTER);
        validate();
        repaint();
    }


    /**
     * gets the song title
     * @return the song title
     */
    public String getSongTitle(){
        return jlTitle.getText().trim();
    }

    /**
     * gets the song author
     * @return the song author
     */
    public String getSongAuthor(){
        return jlAuthor.getText().trim();
    }

    /**
     * pop-up dialog that asks the user for confirmation to delete the selected song
     * @return if the user confirms the deletion of the song or not
     */
    public int showSongDeleteDialog(){
        return JOptionPane.showConfirmDialog(null,"Are you sure you want to Delete this Song?",
                "Warning",JOptionPane.YES_NO_OPTION);
    }

    /**
     * pop-up dialog that shows an error message
     * @param message the error message
     */
    public void showErrorDialog(String message){
        JOptionPane.showMessageDialog(this, message,"Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * pop-up dialog that shows an error message when there is no playlist to add the song to
     */
    public void showPlaylistsErrorDialog(){
        JOptionPane.showMessageDialog(this, "You don't have any playlist to add the song to.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * pop up confirmation dialog where the user gets to make a selection
     * @param playlistsNames the playlist the user owns
     * @return the playlist the song would go to
     */
    public int showPlaylistPickerDialog(JComboBox<String> playlistsNames){
        return JOptionPane.showConfirmDialog(this,playlistsNames,"Select the playlist to add the song to:", JOptionPane.YES_NO_OPTION);
    }
}


