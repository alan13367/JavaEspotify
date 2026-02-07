package presentation.views;

import presentation.controllers.SongsController;
import presentation.views.GUIassets.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;

/**
 * the GUI of the songs panel
 * @author Alan Beltrán
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
        jpSong = new RoundedPanel(ThemeDimensions.BORDER_RADIUS, ThemeColors.BACKGROUND_SECONDARY);
        jpSong.setLayout(new BorderLayout());
        jpSong.setBorder(new EmptyBorder(ThemeDimensions.CARD_PADDING, ThemeDimensions.CARD_PADDING,
                ThemeDimensions.CARD_PADDING, ThemeDimensions.CARD_PADDING));

        // Top panel with close button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(ThemeColors.BACKGROUND_SECONDARY);
        topPanel.setBorder(new EmptyBorder(0, 0, ThemeDimensions.SECTION_GAP, 0));

        StyledButton closeButton = new StyledButton("", "assets/x-mark-3-32.png", StyledButton.ButtonType.ICON);
        closeButton.setActionCommand(BTN_CLOSE);
        this.jbClose = closeButton;
        topPanel.add(closeButton, BorderLayout.LINE_END);

        // Song info panel
        JPanel songInfoPanel = new JPanel(new GridLayout(3, 3, ThemeDimensions.SECTION_GAP_LARGE, ThemeDimensions.SECTION_GAP));
        songInfoPanel.setBackground(ThemeColors.BACKGROUND_SECONDARY);

        jlTitle = createSongLabels(ThemeFonts.HEADER_MEDIUM.getSize());
        jlDuration = createSongLabels(ThemeFonts.LABEL.getSize());
        jlAuthor = createSongLabels(ThemeFonts.LABEL.getSize());
        jlAlbum = createSongLabels(ThemeFonts.LABEL.getSize());
        jlGenre = createSongLabels(ThemeFonts.LABEL.getSize());
        jlOwner = createSongLabels(ThemeFonts.LABEL.getSize());

        songInfoPanel.add(jlTitle);
        songInfoPanel.add(jlAlbum);
        songInfoPanel.add(jlDuration);
        songInfoPanel.add(jlAuthor);
        songInfoPanel.add(jlGenre);
        songInfoPanel.add(jlOwner);

        JPanel jPanelAux = new JPanel();
        jPanelAux.setBackground(ThemeColors.BACKGROUND_SECONDARY);
        songInfoPanel.add(jPanelAux);

        topPanel.add(songInfoPanel, BorderLayout.CENTER);
        jpSong.add(topPanel, BorderLayout.PAGE_START);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, ThemeDimensions.SECTION_GAP_LARGE, 0));
        buttonPanel.setBackground(ThemeColors.BACKGROUND_SECONDARY);
        buttonPanel.setBorder(new EmptyBorder(ThemeDimensions.SECTION_GAP, 0, ThemeDimensions.SECTION_GAP, 0));

        StyledButton playButton = new StyledButton(" Play Song", "assets/play-32.png");
        playButton.setActionCommand(BTN_PLAY_SONG);
        this.jbPlay = playButton;

        StyledButton addButton = new StyledButton(" Add To Playlist", "assets/plus-4-32.png");
        addButton.setActionCommand(BTN_ADD_TO_PLAYLIST);
        this.jbAddToPlaylist = addButton;

        StyledButton deleteButton = new StyledButton(" Delete Song", "assets/trashicon32.png");
        deleteButton.setActionCommand(BTN_DELETE_SONG);
        this.jbDelete = deleteButton;

        buttonPanel.add(playButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        jpSong.add(buttonPanel, BorderLayout.SOUTH);
        add(jpSong, SONGPANEL_CARD);
    }

    private JLabel createSongLabels(int size){
        JLabel label = new JLabel();
        label.setFont(new Font(ThemeFonts.BODY.getFamily(),Font.BOLD,size));
        label.setForeground(ThemeColors.TEXT_PRIMARY);
        return label;
    }

    private void configureSongsPanel(){
        jpSongs = new RoundedPanel(ThemeDimensions.BORDER_RADIUS, ThemeColors.BACKGROUND_SECONDARY);
        jpSongs.setLayout(new BorderLayout());
        jpSongs.setBorder(new EmptyBorder(ThemeDimensions.CARD_PADDING, ThemeDimensions.CARD_PADDING,
                ThemeDimensions.CARD_PADDING, ThemeDimensions.CARD_PADDING));
        configureSearch();
        configureTable();
        add(jpSongs,SONGSTABLE_CARD);
    }

    private void configureTable(){

        String data1[][] = new String[0][0];
        songsTable = new JTable(new DefaultTableModel(columns,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        songsTable.getTableHeader().setReorderingAllowed(false);
        songsTable.setBackground(ThemeColors.TABLE_BACKGROUND);
        songsTable.setGridColor(ThemeColors.TABLE_GRID);
        songsTable.setForeground(ThemeColors.TEXT_PRIMARY);
        songsTable.setFont(ThemeFonts.TABLE_CELL);
        songsTable.setRowHeight(ThemeDimensions.TABLE_ROW_HEIGHT);
        songsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songsTable.setSelectionBackground(ThemeColors.ACCENT_PRIMARY);
        songsTable.setSelectionForeground(ThemeColors.TEXT_PRIMARY);

        // Modern table header
        JTableHeader jTableHeader = songsTable.getTableHeader();
        jTableHeader.setBackground(ThemeColors.TABLE_HEADER_BACKGROUND);
        jTableHeader.setForeground(ThemeColors.TEXT_PRIMARY);
        jTableHeader.setFont(ThemeFonts.TABLE_HEADER);
        ((DefaultTableCellRenderer)jTableHeader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        // Custom row renderer for zebra striping
        songsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? ThemeColors.TABLE_BACKGROUND : ThemeColors.TABLE_ROW_ALTERNATE);
                }
                setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        });

        JScrollPane jsp = new JScrollPane(songsTable);
        jsp.getViewport().setBackground(ThemeColors.BACKGROUND_SECONDARY);
        jsp.setBorder(BorderFactory.createEmptyBorder());
        jsp.getVerticalScrollBar().setUI(new MyScrollBarUI());
        JPanel panel = new JPanel();
        panel.setBackground(ThemeColors.BACKGROUND_SECONDARY);
        jsp.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
        jpSongs.add(jsp,BorderLayout.CENTER);

        model = (DefaultTableModel) songsTable.getModel();
    }

    private void configureSearch(){
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(new EmptyBorder(0, 0, ThemeDimensions.SECTION_GAP, 0));
        searchPanel.setBackground(ThemeColors.BACKGROUND_SECONDARY);

        // Search field panel
        JPanel searchFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, ThemeDimensions.COMPONENT_GAP, 0));
        searchFieldPanel.setBackground(ThemeColors.BACKGROUND_SECONDARY);

        searchField = new MyHintTextField.RoundedMyHintTextField(HINT_TEXTFIELD);
        searchField.setFont(ThemeFonts.INPUT);
        searchField.setBackground(ThemeColors.BACKGROUND_INPUT);
        searchField.setForeground(ThemeColors.TEXT_PRIMARY);
        searchField.setPreferredSize(new Dimension(800, ThemeDimensions.INPUT_HEIGHT));
        searchFieldPanel.add(searchField);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, ThemeDimensions.COMPONENT_GAP, 0));
        buttonsPanel.setBackground(ThemeColors.BACKGROUND_SECONDARY);

        StyledButton searchButton = new StyledButton("", "assets/lupa32.png", StyledButton.ButtonType.PRIMARY);
        searchButton.setActionCommand(BTN_SEARCH);
        this.jbSearch = searchButton;

        StyledButton refreshButton = new StyledButton("", "assets/refresh-32.png", StyledButton.ButtonType.SECONDARY);
        refreshButton.setActionCommand(BTN_REFRESH);
        this.jbRefresh = refreshButton;

        buttonsPanel.add(searchButton);
        buttonsPanel.add(refreshButton);

        searchPanel.add(searchFieldPanel, BorderLayout.CENTER);
        searchPanel.add(buttonsPanel, BorderLayout.EAST);
        jpSongs.add(searchPanel, BorderLayout.NORTH);
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
         songsTable.clearSelection();
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


