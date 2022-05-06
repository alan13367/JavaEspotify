package presentation.views;

import presentation.controllers.PlaylistsController;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class PlaylistsView extends JPanel {
    private final CardLayout playlistsPanelManager;
    private JPanel playlists;
    private JButton jbCreatePlaylist;
    private JButton jbMyPlaylists;
    private JButton jbAllPlaylists;
    public static final String BTN_MY_PLAYLISTS = "BTN_MY_PLAYLISTS";
    public static final String BTN_ALL_PLAYLISTS = "BTN_ALL_PLAYLISTS";

    private PlaylistsController playlistsController;
    public static final String BTN_CREATE_PLAYLIST = "BTN_CREATE_PLAYLIST";


    public PlaylistsView(){
        playlistsPanelManager = new CardLayout();
        configureView();
        configureTopPanel();
        configureComponents();
    }

    private void configureView(){
        setLayout(new BorderLayout());
        setBackground(new Color(8,8,8));
        playlists = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        playlists.add(new JPanel(), gbc);
        add(new JScrollPane(playlists),BorderLayout.CENTER);
    }

    private void configureTopPanel(){
        JPanel jPanel = new JPanel(new GridLayout());
        jbMyPlaylists = new JButton("My Playlists");
        jbMyPlaylists.setFont(new Font("Arial",Font.BOLD,30));
        jbMyPlaylists.setActionCommand(BTN_MY_PLAYLISTS);
        jbAllPlaylists = new JButton("All Playlists");
        jbAllPlaylists.setFont(new Font("Arial",Font.BOLD,30));
        jbAllPlaylists.setActionCommand(BTN_ALL_PLAYLISTS);
        jPanel.add(jbMyPlaylists);
        jPanel.add(jbAllPlaylists);
        add(jPanel,BorderLayout.NORTH);

    }

    private void configureComponents(){
        jbCreatePlaylist = new JButton("Create Playlist");
        jbCreatePlaylist.setActionCommand(BTN_CREATE_PLAYLIST);
        add(jbCreatePlaylist,BorderLayout.SOUTH);
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
        playlists.add(panel, gbc, 0);
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
    }



}
