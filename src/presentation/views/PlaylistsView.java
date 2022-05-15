package presentation.views;

import presentation.controllers.PlaylistsController;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class PlaylistsView extends JPanel {
    //General Assets
    private final CardLayout viewManager;
    private JPanel playlistsGeneralPanel;

    //My Playlists and All Playlists Panels
    private final CardLayout playlistsPanelManager;
    private JPanel myPlaylists;
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
    private JPanel playlistInfoPanel;
    private JLabel jlPlaylistName;
    private JLabel jlPlaylistOwner;
    private JButton jbClose,jbPlayPlaylist,jbDeletePlaylist;
    public static final String BTN_CLOSE = "BTN_CLOSE";
    public static final String BTN_PLAY_PLAYLIST = "BTN_PLAY_PLAYLIST";
    public static final String BTN_DELETE_PLAYLIST = "BTN_DELETE_PLAYLIST";

    private static final String PLAYLISTS_CARD = "PLAYLISTS_CARD";
    private static final String PLAYLIST_INFO_CARD = "PLAYLIST_INFO_CARD";
    private PlaylistsController playlistsController;



    public PlaylistsView(){
        viewManager = new CardLayout();
        playlistsPanelManager = new CardLayout();
        setLayout(viewManager);
        setBackground(new Color(8,8,8));
        configurePlaylistsCard();
        configurePlaylistInfoCard();
    }
    private void configurePlaylistsCard(){
        playlistsGeneralPanel = new JPanel(new BorderLayout());
        playlistsWrapperPanel = new JPanel(playlistsPanelManager);

        //My Playlists Section
        myPlaylists = new JPanel(new BorderLayout());
        myPlaylists.setBackground(new Color(16,16,16));
        myPlaylistsListPanel = new JPanel();
        BoxLayout boxLayoutPlaylists = new BoxLayout(myPlaylistsListPanel,BoxLayout.Y_AXIS);
        myPlaylistsListPanel.setLayout(boxLayoutPlaylists);
        JScrollPane myPlaylistsSP = new JScrollPane(myPlaylistsListPanel);
        myPlaylistsSP.setBorder(BorderFactory.createEmptyBorder());
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
        playlistsWrapperPanel.add(allPlaylistsSP,ALL_PLAYLISTS_CARD);

        //Top Panel Playlists Switcher
        underlinedText = new HashMap<TextAttribute,Integer>();
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
        jlPlaylistName.setFont(new Font("Arial",Font.BOLD,30));
        jlPlaylistName.setHorizontalAlignment(SwingConstants.CENTER);
        jlPlaylistName.setForeground(Color.white);
        jlPlaylistOwner.setFont(new Font("Arial",Font.BOLD,30));
        jlPlaylistOwner.setHorizontalAlignment(SwingConstants.CENTER);
        jlPlaylistOwner.setForeground(Color.white);
        jPanel.add(jlPlaylistName);
        jPanel.add(jlPlaylistOwner);
        topPanel.add(jPanel,BorderLayout.CENTER);
        playlistInfoPanel.add(topPanel,BorderLayout.PAGE_START);

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

    public void addMyPlaylists(String name, String owner){
        PlaylistItemHolder playlistItemHolder = new PlaylistItemHolder(name,owner);
        playlistItemHolder.registerController(playlistsController);
        myPlaylistsListPanel.add(playlistItemHolder);
        validate();
        repaint();
    }

    public void addAllPlaylists(String name,String owner){
        PlaylistItemHolder playlistItemHolder = new PlaylistItemHolder(name,owner);
        playlistItemHolder.registerController(playlistsController);
        allPlaylists.add(playlistItemHolder);
        validate();
        repaint();
    }


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

    public void registerController(PlaylistsController playlistsController){
        this.playlistsController = playlistsController;
        jbCreatePlaylist.addActionListener(playlistsController);
        jbAllPlaylists.addActionListener(playlistsController);
        jbMyPlaylists.addActionListener(playlistsController);
        jbClose.addActionListener(playlistsController);
    }


    public void showMyPlaylistsCard() {
        jbMyPlaylists.setForeground(Color.green);
        jbMyPlaylists.setFont(switchButtonsFont.deriveFont(underlinedText));
        jbAllPlaylists.setForeground(Color.white);
        jbAllPlaylists.setFont(switchButtonsFont);
        playlistsPanelManager.show(playlistsWrapperPanel,MY_PLAYLISTS_CARD);
    }

    public void showAllPlaylistsCard() {
        jbAllPlaylists.setForeground(Color.green);
        jbAllPlaylists.setFont(switchButtonsFont.deriveFont(underlinedText));
        jbMyPlaylists.setForeground(Color.white);
        jbMyPlaylists.setFont(switchButtonsFont);
        playlistsPanelManager.show(playlistsWrapperPanel,ALL_PLAYLISTS_CARD);
    }

    public void showPlaylistInfoCard(String name,String owner,boolean isOwner){
        jlPlaylistName.setText(name);
        jlPlaylistOwner.setText(owner);
        jbDeletePlaylist.setVisible(isOwner);
        viewManager.show(this,PLAYLIST_INFO_CARD);
    }

    public void showPlaylistsPanelCard() {
        viewManager.show(this,PLAYLISTS_CARD);
    }

    public void loadUserPlaylists(String username){
        playlistsController.loadPlaylists(username);
    }

    public void showErrorDialog(String message){
        JOptionPane.showMessageDialog(this, message,"Error", JOptionPane.ERROR_MESSAGE);
    }

    public static class PlaylistItemHolder extends JPanel{
        private JLabel playlistName;
        private JLabel playlistOwner;

        private PlaylistItemHolder(String playlistName,String playlistOwner){
            this.setPreferredSize(new Dimension(1000,200));
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

        public void registerController(PlaylistsController playlistsController){
            this.addMouseListener(playlistsController);
        }

        public String getPlaylistName(){
            return this.playlistName.getText();
        }

        public String getPlaylistOwner(){
            return this.playlistOwner.getText();
        }

    }
}
