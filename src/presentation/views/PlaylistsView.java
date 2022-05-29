package presentation.views;

import presentation.controllers.PlaylistsController;
import presentation.views.GUIassets.MyScrollBarUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.*;
import java.util.List;

/**
 * the GUI of the playlist panel
 * @author Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 12/04/2022
 */
public class PlaylistsView extends JPanel {
    //General Assets
    private final CardLayout viewManager;

    //My Playlists and All Playlists Panels
    private final CardLayout playlistsPanelManager;
    private JPanel myPlaylistsListPanel;
    private JPanel allPlaylists;
    private JPanel playlistsWrapperPanel;

    private JButton jbMyPlaylists,jbAllPlaylists,jbCreatePlaylist;
    Map<TextAttribute, Integer> underlinedText = new HashMap<TextAttribute, Integer>();
    private static final Font switchButtonsFont = new Font("Arial",Font.BOLD,30);
    public static final String BTN_MY_PLAYLISTS = "BTN_MY_PLAYLISTS";
    public static final String BTN_ALL_PLAYLISTS = "BTN_ALL_PLAYLISTS";
    public static final String BTN_CREATE_PLAYLIST = "BTN_CREATE_PLAYLIST";
    private static final String MY_PLAYLISTS_CARD = "MY_PLAYLISTS_CARD";
    private static final String ALL_PLAYLISTS_CARD = "ALL_PLAYLISTS_CARD";

    //Playlist Info Panel
    private JPanel playlistInfoPanel,jpSongsFromPlaylist;
    private JLabel jlPlaylistName,jlPlaylistOwner;
    private JButton jbClose,jbPlayPlaylist,jbDeletePlaylist;
    public static final String BTN_CLOSE = "BTN_CLOSE";
    public static final String BTN_PLAY_PLAYLIST = "BTN_PLAY_PLAYLIST";
    public static final String BTN_DELETE_PLAYLIST = "BTN_DELETE_PLAYLIST";
    public static final String BTN_DELETE_SONG_FROM_PLAYLIST = "BTN_DELETE_SONG_FROM_PLAYLIST";
    public static final String BTN_MOVE_SONG_DOWN = "BTN_MOVE_SONG_DOWN";
    public static final String BTN_MOVE_SONG_UP = "BTN_MOVE_SONG_UP";


    private static final String PLAYLISTS_CARD = "PLAYLISTS_CARD";
    private static final String PLAYLIST_INFO_CARD = "PLAYLIST_INFO_CARD";
    private PlaylistsController playlistsController;
    private final List<SongItemHolder> songsHolder;
    public  static final int MOVEUP = 1;
    public  static final int MOVEDOWN = 0;

    /**
     * The view constructor, sets the layout of the panel and configures the cards, also calls a new array list that holds the songs
     */
    public PlaylistsView(){
        songsHolder = new ArrayList<>();
        viewManager = new CardLayout();
        playlistsPanelManager = new CardLayout();
        setLayout(viewManager);
        setBackground(new Color(16,16,16));
        configurePlaylistsCard();
        configurePlaylistInfoCard();
    }
    private void configurePlaylistsCard(){
        JPanel playlistsGeneralPanel = new JPanel(new BorderLayout());
        playlistsWrapperPanel = new JPanel(playlistsPanelManager);

        //My Playlists Section
        JPanel myPlaylists = new JPanel(new BorderLayout());
        myPlaylists.setBackground(new Color(16,16,16));
        myPlaylistsListPanel = new JPanel();
        myPlaylistsListPanel.setBackground(new Color(16,16,16));
        BoxLayout boxLayoutPlaylists = new BoxLayout(myPlaylistsListPanel,BoxLayout.Y_AXIS);
        myPlaylistsListPanel.setLayout(boxLayoutPlaylists);
        JScrollPane myPlaylistsSP = new JScrollPane(myPlaylistsListPanel);
        myPlaylistsSP.setBorder(BorderFactory.createEmptyBorder());
        myPlaylistsSP.getVerticalScrollBar().setUI(new MyScrollBarUI());
        myPlaylists.add(myPlaylistsSP,BorderLayout.CENTER);
        JPanel southPanel= new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.setBackground(new Color(16,16,16));
        JPanel panelButtonHolder = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panelButtonHolder,BoxLayout.Y_AXIS);
        panelButtonHolder.setLayout(boxLayout);
        panelButtonHolder.setBackground(new Color(16,16,16));
        jbCreatePlaylist = new JButton("Create Playlist",new ImageIcon("assets/plus-4-32.png"));
        jbCreatePlaylist.setActionCommand(BTN_CREATE_PLAYLIST);
        jbCreatePlaylist.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbCreatePlaylist.setFont(new Font("Arial",Font.BOLD,20));
        jbCreatePlaylist.setForeground(Color.white);
        jbCreatePlaylist.setBackground(new Color(0,204,0));
        panelButtonHolder.add(jbCreatePlaylist);
        southPanel.add(panelButtonHolder,BorderLayout.CENTER);
        myPlaylists.add(southPanel,BorderLayout.SOUTH);
        playlistsWrapperPanel.add(myPlaylists,MY_PLAYLISTS_CARD);

        //All Playlist Section
        allPlaylists = new JPanel();
        BoxLayout boxLayoutAllPlaylists = new BoxLayout(allPlaylists,BoxLayout.Y_AXIS);
        allPlaylists.setLayout(boxLayoutAllPlaylists);
        allPlaylists.setBackground(new Color(16,16,16));
        JScrollPane allPlaylistsSP = new JScrollPane(allPlaylists);
        allPlaylistsSP.setBorder(BorderFactory.createEmptyBorder());
        allPlaylistsSP.getVerticalScrollBar().setUI(new MyScrollBarUI());
        playlistsWrapperPanel.add(allPlaylistsSP,ALL_PLAYLISTS_CARD);

        //Top Panel Playlists Switcher
        underlinedText = new HashMap<>();
        underlinedText.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        JPanel jPanel = new JPanel(new GridLayout());
        jbMyPlaylists = new JButton("My Playlists");
        jbMyPlaylists.setFont(switchButtonsFont.deriveFont(underlinedText));
        jbMyPlaylists.setActionCommand(BTN_MY_PLAYLISTS);
        jbMyPlaylists.setBackground(new Color(16,16,16));
        jbMyPlaylists.setForeground(Color.green);
        jbMyPlaylists.setOpaque(true);
        jbMyPlaylists.setFocusPainted(true);
        jbMyPlaylists.setBorderPainted(false);
        jbMyPlaylists.setContentAreaFilled(true);
        jbAllPlaylists = new JButton("All Playlists");
        jbAllPlaylists.setFont(new Font("Arial",Font.BOLD,30));
        jbAllPlaylists.setActionCommand(BTN_ALL_PLAYLISTS);
        jbAllPlaylists.setBackground(new Color(16,16,16));
        jbAllPlaylists.setForeground(Color.white);
        jbAllPlaylists.setOpaque(true);
        jbAllPlaylists.setFocusPainted(true);
        jbAllPlaylists.setBorderPainted(false);
        jbAllPlaylists.setContentAreaFilled(true);
        jPanel.add(jbMyPlaylists);
        jPanel.add(jbAllPlaylists);
        playlistsGeneralPanel.add(jPanel,BorderLayout.NORTH);
        playlistsGeneralPanel.add(playlistsWrapperPanel,BorderLayout.CENTER);
        add(playlistsGeneralPanel,PLAYLISTS_CARD);
    }

    private void configurePlaylistInfoCard(){
        playlistInfoPanel = new JPanel(new BorderLayout());
        playlistInfoPanel.setBackground(new Color(16,16,16));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(16,16,16));
        jbClose = new JButton(new ImageIcon("assets/x-mark-3-32.png"));
        jbClose.setOpaque(false);
        jbClose.setContentAreaFilled(false);
        jbClose.setBorderPainted(false);
        jbClose.setActionCommand(BTN_CLOSE);
        topPanel.add(jbClose,BorderLayout.LINE_END);

        JPanel jPanel = new JPanel(new GridLayout(1,2));
        jPanel.setBackground(new Color(16,16,16));
        jlPlaylistName = new JLabel();
        jlPlaylistOwner = new JLabel();
        jlPlaylistName.setFont(new Font("Arial",Font.BOLD,40));
        jlPlaylistName.setHorizontalAlignment(SwingConstants.CENTER);
        jlPlaylistName.setForeground(Color.white);
        jlPlaylistOwner.setFont(new Font("Arial",Font.BOLD,40));
        jlPlaylistOwner.setHorizontalAlignment(SwingConstants.CENTER);
        jlPlaylistOwner.setForeground(Color.white);
        jPanel.add(jlPlaylistName);
        jPanel.add(jlPlaylistOwner);
        topPanel.add(jPanel,BorderLayout.CENTER);
        playlistInfoPanel.add(topPanel,BorderLayout.PAGE_START);

        //Songs
        configurePlaylistSongsPanel();

        //Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(16,16,16));
        GridLayout gridLayout = new GridLayout(1,2);
        gridLayout.setHgap(100);
        buttonPanel.setLayout(gridLayout);
        jbPlayPlaylist = new JButton(" Play Playlist",new ImageIcon("assets/play-32.png"));
        jbPlayPlaylist.setBackground(new Color(0,80,0));
        jbPlayPlaylist.setForeground(Color.white);
        jbPlayPlaylist.setFont(new Font("Arial",Font.BOLD,20));
        jbPlayPlaylist.setActionCommand(BTN_PLAY_PLAYLIST);
        buttonPanel.add(jbPlayPlaylist);
        jbDeletePlaylist = new JButton(" Delete Playlist",new ImageIcon("assets/trashicon32.png"));
        jbDeletePlaylist.setBackground(new Color(0,80,0));
        jbDeletePlaylist.setForeground(Color.white);
        jbDeletePlaylist.setFont(new Font("Arial",Font.BOLD,20));
        jbDeletePlaylist.setActionCommand(BTN_DELETE_PLAYLIST);
        buttonPanel.add(jbDeletePlaylist);
        playlistInfoPanel.add(buttonPanel,BorderLayout.SOUTH);

        add(playlistInfoPanel,PLAYLIST_INFO_CARD);
    }

    private void configurePlaylistSongsPanel(){
        jpSongsFromPlaylist = new JPanel();
        jpSongsFromPlaylist.setBorder(new EmptyBorder(0,0,0,20));
        jpSongsFromPlaylist.setLayout(new BoxLayout(jpSongsFromPlaylist,BoxLayout.Y_AXIS));
        jpSongsFromPlaylist.setBackground(new Color(16,16,16));

        JScrollPane songsScrollPane = new JScrollPane(jpSongsFromPlaylist, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        songsScrollPane.setBackground(new Color(16,16,16));
        songsScrollPane.setBorder(BorderFactory.createEmptyBorder());
        songsScrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
        playlistInfoPanel.add(songsScrollPane,BorderLayout.CENTER);
        revalidate();
    }

    /**
     * adds a chosen song to a chosen playlist
     * @param name name of the song
     * @param author author of the song
     * @param position position of the song in the list
     * @param isOwner true if the user is the owner of the song, false otherwise
     */
    public void addSongToPanel(String name,String author,int position,boolean isOwner){
        SongItemHolder songItemHolder = new SongItemHolder(name,author,position,isOwner);
        songItemHolder.registerController(playlistsController);
        jpSongsFromPlaylist.add(songItemHolder);
        songsHolder.add(songItemHolder);
        validate();
        repaint();
    }

    /**
     * empty a playlist from songs
     */
    public void clearSongsPanel(){
        jpSongsFromPlaylist.removeAll();
        songsHolder.clear();
        validate();
        repaint();
    }

    /**
     * adds a new playlist to my playlist panel
     * @param name name of the playlist
     * @param owner owner of the playlist
     */
    public void addMyPlaylists(String name, String owner){
        PlaylistItemHolder playlistItemHolder = new PlaylistItemHolder(name,owner);
        playlistItemHolder.registerController(playlistsController);
        myPlaylistsListPanel.add(playlistItemHolder);
        validate();
        repaint();
    }

    /**
     * adds all the playlists to all playlists panel
     * @param name name of the playlist
     * @param owner owner of the playlist
     */
    public void addAllPlaylists(String name,String owner){
        PlaylistItemHolder playlistItemHolder = new PlaylistItemHolder(name,owner);
        playlistItemHolder.registerController(playlistsController);
        allPlaylists.add(playlistItemHolder);
        validate();
        repaint();
    }

    /**
     * empty the 'my playlists' and 'all playlists' panels
     */
    public void clearPlaylistsPanel(){
        allPlaylists.removeAll();
        myPlaylistsListPanel.removeAll();
        validate();
        repaint();
    }


    /**
     * pop dialog to enter the name of the playlist the user wants to create
     * @return the playlist name
     */
    public String createPlaylistDialog(){
        return (String)JOptionPane.showInputDialog(
                null,
                "Enter name for the Playlist you want to Create:",
                "Create New Playlist",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "Enter name here"
        );
    }

    /**
     * adds an action listener to all the components of the view
     * @param playlistsController playlistsController
     */
    public void registerController(PlaylistsController playlistsController){
        this.playlistsController = playlistsController;
        jbCreatePlaylist.addActionListener(playlistsController);
        jbAllPlaylists.addActionListener(playlistsController);
        jbMyPlaylists.addActionListener(playlistsController);
        jbClose.addActionListener(playlistsController);
        jbDeletePlaylist.addActionListener(playlistsController);
        jbPlayPlaylist.addActionListener(playlistsController);
    }


    /**
     * shows my playlist view
     */
    public void showMyPlaylistsCard() {
        jbMyPlaylists.setForeground(Color.green);
        jbMyPlaylists.setFont(switchButtonsFont.deriveFont(underlinedText));
        jbAllPlaylists.setForeground(Color.white);
        jbAllPlaylists.setFont(switchButtonsFont);
        playlistsPanelManager.show(playlistsWrapperPanel,MY_PLAYLISTS_CARD);
    }

    /**
     * shows all playlist views
     */
    public void showAllPlaylistsCard() {
        jbAllPlaylists.setForeground(Color.green);
        jbAllPlaylists.setFont(switchButtonsFont.deriveFont(underlinedText));
        jbMyPlaylists.setForeground(Color.white);
        jbMyPlaylists.setFont(switchButtonsFont);
        playlistsPanelManager.show(playlistsWrapperPanel,ALL_PLAYLISTS_CARD);
    }

    /**
     * shows the information of the playlist
     * @param name name of the playlist
     * @param owner owner of the playlist
     * @param isOwner if the current user is the owner
     */
    public void showPlaylistInfoCard(String name,String owner,boolean isOwner){
        jlPlaylistName.setText(name);
        jlPlaylistOwner.setText(owner);
        jbDeletePlaylist.setVisible(isOwner);
        viewManager.show(this,PLAYLIST_INFO_CARD);
    }

    /**
     * shows the view panel
     */
    public void showPlaylistsPanelCard() {
        viewManager.show(this,PLAYLISTS_CARD);
    }

    /**
     * loads all playlist owned by the user
     * @param username the current username of the user
     */
    public void loadUserPlaylists(String username){
        playlistsController.loadPlaylists(username);
    }

    /**
     * pops up an error message
     * @param message the error message
     */
    public void showErrorDialog(String message){
        JOptionPane.showMessageDialog(this, message,"Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * shows a confirmation pop-ip dialog
     * @param message the message where the user will either choose yes or no as an answer
     * @param title brief idea of what the pop-up dialog is about
     * @return option chosen YES or NO
     */
    public int showPlaylistOptionDialog(String message,String title){
        return JOptionPane.showConfirmDialog(this,message, title,JOptionPane.YES_NO_OPTION);
    }

    /**
     * playlist name getter
     * @return the playlist name
     */
    public String getPlaylistName() {
        return jlPlaylistName.getText();
    }


    private void reloadSongsPanel(){
        jpSongsFromPlaylist.removeAll();
        validate();
        repaint();
        for (int i = 0;i<songsHolder.size();i++){
            songsHolder.get(i).setPosition(i+1);
            jpSongsFromPlaylist.add(songsHolder.get(i));
            validate();
            repaint();
        }
    }

    /**
     * moves a song position in the playlist
     * @param position position of song in the playlist
     * @param upOrDown up or down buttons
     */
    public void moveSongInPlaylist(int position,final int upOrDown){
        switch(upOrDown){
            case MOVEUP ->{
                Collections.swap(songsHolder,position,position-1);
            }
            case MOVEDOWN ->{
                Collections.swap(songsHolder,position,position+1);
            }
        }

        reloadSongsPanel();
    }

    /**
     * deletes a song from a playlist
     * @param position position of the song in the playlist
     */
    public void deleteSongFromPlaylist(int position){
        songsHolder.remove(position);
        reloadSongsPanel();
    }

    /**
     * gets the size of the song holder list
     * @return the size of the song holder list
     */
    public int getSongsListSize(){
        return songsHolder.size();
    }

    /**
     * song holder getter
     * @return the song holder list
     */
    public List<SongItemHolder> getSongsHolder(){
        return new ArrayList<>(songsHolder);
    }

    /**
     * internal class extending a panel, holds playlists
     */
    public static class PlaylistItemHolder extends JPanel{
        private final JLabel playlistName;
        private final JLabel playlistOwner;

        private PlaylistItemHolder(String playlistName,String playlistOwner){
            this.setPreferredSize(new Dimension(1150,180));
            this.setMinimumSize(this.getPreferredSize());
            this.setMaximumSize(this.getPreferredSize());
            this.setBackground(new Color(16,16,16));
            this.setLayout(new GridLayout());
            this.setBorder(BorderFactory.createLineBorder(new Color(80,80,80),1,true));
            this.playlistName = new JLabel(playlistName);
            this.playlistName.setFont(new Font("Arial",Font.PLAIN,25));
            this.playlistName.setForeground(Color.white);
            this.playlistName.setHorizontalAlignment(SwingConstants.CENTER);
            this.playlistOwner = new JLabel(playlistOwner);
            this.playlistOwner.setFont(new Font("Arial",Font.PLAIN,25));
            this.playlistOwner.setForeground(Color.white);
            this.playlistOwner.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(this.playlistName);
            this.add(this.playlistOwner);
        }

        /**
         * adds a mouse listener
         * @param playlistsController playlistsController
         */
        public void registerController(PlaylistsController playlistsController){
            this.addMouseListener(playlistsController);
        }

        /**
         * the playlist name getter
         * @return the playlist name
         */
        public String getPlaylistName(){
            return this.playlistName.getText();
        }

        /**
         * the playlist owner getter
         * @return the playlist owner
         */
        public String getPlaylistOwner(){
            return this.playlistOwner.getText();
        }

    }

    /**
     * internal class extending a panel, holds the songs in a playlist
     */
    public static class SongItemHolder extends JPanel {
        private final JLabel position;
        private final JLabel songName;
        private final JLabel songAuthor;
        private final JButton deleteSong;
        private final JButton moveUp;
        private final JButton moveDown;

        private SongItemHolder(String name,String author,int position,boolean isOwner){
            this.setPreferredSize(new Dimension(1150,70));
            this.setMinimumSize(this.getPreferredSize());
            this.setMaximumSize(this.getPreferredSize());
            this.setBackground(new Color(16,16,16));
            this.setLayout(new BorderLayout());
            JPanel centerPanel = new JPanel(new GridLayout(1,4));
            centerPanel.setBackground(new Color(16,16,16));
            this.position = new JLabel("   "+position+".");
            this.position.setFont(new Font("Arial",Font.PLAIN,20));
            this.position.setForeground(Color.white);
            this.songName = new JLabel(name);
            this.songName.setFont(new Font("Arial",Font.PLAIN,20));
            this.songName.setForeground(Color.white);

            this.songAuthor = new JLabel(author);
            this.songAuthor.setFont(new Font("Arial",Font.PLAIN,20));
            this.songAuthor.setForeground(Color.white);

            centerPanel.add(this.position);
            centerPanel.add(songName);
            centerPanel.add(songAuthor);


            //Buttons
            JPanel buttonsPanel = new JPanel(new BorderLayout());
            buttonsPanel.setBackground(new Color(16,16,16));
            JPanel arrowsPanel = new JPanel(new GridLayout(2,1));
            arrowsPanel.setBackground(new Color(16,16,16));
            arrowsPanel.setBorder(new EmptyBorder(0,0,0,10));

            moveUp = new JButton(new ImageIcon("assets/arrowup.png"));
            moveUp.setActionCommand(BTN_MOVE_SONG_UP);
            moveUp.setBackground(null);
            moveUp.setBorder(BorderFactory.createEmptyBorder());
            moveUp.setContentAreaFilled(false);

            moveDown = new JButton(new ImageIcon("assets/arrowdown.png"));
            moveDown.setActionCommand(BTN_MOVE_SONG_DOWN);
            moveDown.setBackground(null);
            moveDown.setBorder(BorderFactory.createEmptyBorder());
            moveDown.setContentAreaFilled(false);
            arrowsPanel.add(moveUp);
            arrowsPanel.add(moveDown);
            buttonsPanel.add(arrowsPanel,BorderLayout.CENTER);

            deleteSong = new JButton(new ImageIcon("assets/trash-10-24.png"));
            deleteSong.setActionCommand(BTN_DELETE_SONG_FROM_PLAYLIST);
            deleteSong.setBackground(null);
            deleteSong.setBorder(BorderFactory.createEmptyBorder());
            deleteSong.setContentAreaFilled(false);

            moveDown.setVisible(isOwner);
            moveUp.setVisible(isOwner);
            deleteSong.setVisible(isOwner);
            buttonsPanel.add(deleteSong,BorderLayout.LINE_END);
            this.add(buttonsPanel,BorderLayout.LINE_END);
            this.add(centerPanel,BorderLayout.CENTER);
            this.setBorder(BorderFactory.createLineBorder(new Color(80,80,80),1));
        }

        private void registerController(PlaylistsController playlistsController){
            this.deleteSong.addActionListener(playlistsController);
            this.moveUp.addActionListener(playlistsController);
            this.moveDown.addActionListener(playlistsController);
        }

        /**
         * position setter
         * @param position position of the song in the playlist
         */
        public void setPosition(int position){
            this.position.setText("   "+position+".");
        }

        /**
         * song name getter
         * @return the song name
         */
        public String getSongName(){
            return songName.getText();
        }

        /**
         * song author getter
         * @return the song author
         */
        public String getAuthor(){
            return songAuthor.getText();
        }

        /**
         * song position getter
         * @return the position of the song in the playlist
         */
        public int getPosition(){
            String[] split = position.getText().split("\\.");
            return Integer.parseInt(split[0].trim());
        }
    }
}
