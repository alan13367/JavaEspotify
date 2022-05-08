package presentation.views;

import presentation.controllers.PlaylistsController;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class PlaylistsView extends JPanel {
    //General Assets
    private final CardLayout viewManager;
    private JPanel playlistsGeneralPanel;

    //Playlists All and Users Panels
    private final CardLayout playlistsPanelManager;
    private JPanel myPlaylists;
    private JPanel allPlaylists;
    private JPanel playlistsWrapperPanel;

    private JButton jbCreatePlaylist;
    private JButton jbMyPlaylists,jbAllPlaylists;
    public static final String BTN_MY_PLAYLISTS = "BTN_MY_PLAYLISTS";
    public static final String BTN_ALL_PLAYLISTS = "BTN_ALL_PLAYLISTS";
    public static final String BTN_CREATE_PLAYLIST = "BTN_CREATE_PLAYLIST";
    private static final String MY_PLAYLISTS_CARD = "MY_PLAYLISTS_CARD";
    private static final String ALL_PLAYLISTS_CARD = "ALL_PLAYLISTS_CARD";

    //Playlist Info Panel
    private JPanel playlistInfoPanel;
    private JButton jbClose;
    public static final String BTN_CLOSE = "BTN_CLOSE";



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
        showPlaylistInfo();
    }
    private void configurePlaylistsCard(){
        playlistsGeneralPanel = new JPanel(new BorderLayout());
        playlistsWrapperPanel = new JPanel(playlistsPanelManager);

        myPlaylists = new JPanel(new BorderLayout());
        myPlaylists.add(new JPanel(), BorderLayout.CENTER);
        jbCreatePlaylist = new JButton("Create Playlist");
        jbCreatePlaylist.setActionCommand(BTN_CREATE_PLAYLIST);
        JPanel southPanel= new JPanel();
        BoxLayout boxLayout = new BoxLayout(southPanel,BoxLayout.X_AXIS);
        southPanel.setLayout(boxLayout);
        southPanel.add(jbCreatePlaylist);
        myPlaylists.add(southPanel,BorderLayout.SOUTH);
        playlistsWrapperPanel.add(new JScrollPane(myPlaylists),MY_PLAYLISTS_CARD);

        allPlaylists = new JPanel(new BorderLayout());
        allPlaylists.add(new JPanel(), BorderLayout.CENTER);
        playlistsWrapperPanel.add(new JScrollPane(allPlaylists),ALL_PLAYLISTS_CARD);

        configureTopPlaylistsPanel();
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
        playlistInfoPanel.add(topPanel,BorderLayout.PAGE_START);


        add(playlistInfoPanel,PLAYLIST_INFO_CARD);
    }

    private void configureTopPlaylistsPanel(){
        JPanel jPanel = new JPanel(new GridLayout());
        jbMyPlaylists = new JButton("My Playlists");
        jbMyPlaylists.setFont(new Font("Arial",Font.BOLD,30));
        jbMyPlaylists.setActionCommand(BTN_MY_PLAYLISTS);
        jbAllPlaylists = new JButton("All Playlists");
        jbAllPlaylists.setFont(new Font("Arial",Font.BOLD,30));
        jbAllPlaylists.setActionCommand(BTN_ALL_PLAYLISTS);
        jPanel.add(jbMyPlaylists);
        jPanel.add(jbAllPlaylists);
        playlistsGeneralPanel.add(jPanel,BorderLayout.NORTH);
    }


    public void addPlaylist(String name){
        JPanel panel = new JPanel();
        JLabel label = new JLabel(name);
        label.addMouseListener(playlistsController);
        panel.add(label,BorderLayout.CENTER);
        panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        myPlaylists.add(panel, gbc, 0);
        validate();
        repaint();
    }

    public String createPlaylistDialog(){
        return (String)JOptionPane.showInputDialog(
                null,
                "Enter name of the Playlist you want to create",
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
        playlistsPanelManager.show(playlistsWrapperPanel,MY_PLAYLISTS_CARD);
    }

    public void showAllPlaylistsCard() {
        playlistsPanelManager.show(playlistsWrapperPanel,ALL_PLAYLISTS_CARD);
    }

    public void showPlaylistInfo(){
        viewManager.show(this,PLAYLIST_INFO_CARD);
    }

    public void showPlaylistsPanelCard() {
        viewManager.show(this,PLAYLISTS_CARD);
    }
}
