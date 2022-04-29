package presentation.views;



import business.entities.Song;
import business.managers.SongPlayerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class HomeView extends JPanel {

    private SongsView songsView;
    private PlaylistsView playlistView;
    private JPanel jpMain;

    private final CardLayout mainPanelManager;

    //Side Panel Buttons
    private JButton jbSongs;
    private JButton jbPlaylists;
    private JButton jbStatistics;
    private JButton jbAddSong;
    private JButton jbLogOut;
    private JButton jbDeleteAcc;

    public static final String BTN_PLAYLISTS = "BTN_PLAYLISTS";
    public static final String BTN_STATISTICS = "BTN_STATISTICS";
    public static final String BTN_SONGS = "BTN_SONGS";
    public static final String BTN_ADDSONG = "BTN_ADDSONG";
    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_DELETEACC = "BTN_DELETEACC";

    private JLabel username;

    //Player Buttons
    private JButton jbPlayPause;
    private JButton jbPause;
    private JButton jbShuffle;
    private JButton jbLoop;
    private JButton jbNext;
    private JButton jbPrevious;

    //progress bar
    private JProgressBar bar = new JProgressBar();

    public static final String BTN_PLAYPAUSE = "BTN_PLAYPAUSE";
    public static final String BTN_LOOP = "BTN_LOOP";
    public static final String BTN_NEXT = "BTN_NEXT";
    public static final String BTN_PREV = "BTN_PREV";

    public static final String BTN_PAUSE = "BTN_PAUSE";

    //Cards Global Constants
    public static final String CARD_SONGS = "CARD_SONGS";
    public static final String CARD_PLAYLISTS = "CARD_PLAYLISTS";
    public static final String CARD_STATISTICS = "CARD_STATISTICS";


    public HomeView(){
        setLayout(new BorderLayout());
        mainPanelManager = new CardLayout();
        songsView = new SongsView();
        playlistView = new PlaylistsView();
        configureView();
    }

    private void configureView(){
        configureMainPanel();
        configureSidePanel();
        configurePlayerPanel();
        configureSongsCard();
        configurePlaylistsCard();
        configureStatisticsCard();
    }

    private void configureMainPanel(){
        jpMain = new JPanel(mainPanelManager);
        jpMain.setBackground(new Color(64,64,64));
        add(jpMain,BorderLayout.CENTER);
    }

    private void configureSidePanel(){
        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(280,720));
        sidePanel.setBackground(Color.black);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);
        GridLayout northGridLayout = new GridLayout(4,1);
        northGridLayout.setHgap(0);
        northGridLayout.setVgap(15);
        buttonPanel.setLayout(northGridLayout);
        sidePanel.add(buttonPanel,BorderLayout.NORTH);

        jbSongs = new JButton("Songs");
        jbSongs.setActionCommand(BTN_SONGS);
        jbSongs.setForeground(Color.white);
        jbSongs.setBackground(new Color(0,204,0));
        jbSongs.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(jbSongs);

        jbPlaylists = new JButton("Playlists");
        jbPlaylists.setActionCommand(BTN_PLAYLISTS);
        jbPlaylists.setForeground(Color.white);
        jbPlaylists.setBackground(new Color(0,204,0));
        jbPlaylists.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(jbPlaylists);

        jbStatistics = new JButton("Music Statistics");
        jbStatistics.setActionCommand(BTN_STATISTICS);
        jbStatistics.setForeground(Color.white);
        jbStatistics.setBackground(new Color(0,204,0));
        jbStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(jbStatistics);

        jbAddSong = new JButton("Add Song");
        jbAddSong.setActionCommand(BTN_ADDSONG);
        jbAddSong.setForeground(Color.white);
        jbAddSong.setBackground(new Color(0,204,0));
        jbAddSong.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(jbAddSong);

        //User Panel
        JPanel userPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(3,1);
        userPanel.setBackground(Color.black);
        gridLayout.setHgap(0);
        gridLayout.setVgap(15);
        userPanel.setLayout(gridLayout);
        username = new JLabel();
        username.setForeground(Color.white);
        userPanel.add(username);

        jbDeleteAcc = new JButton("Delete Account");
        jbDeleteAcc.setActionCommand(BTN_DELETEACC);
        jbDeleteAcc.setForeground(Color.white);
        jbDeleteAcc.setBackground(new Color(0,204,0));
        jbDeleteAcc.setAlignmentX(Component.CENTER_ALIGNMENT);
        userPanel.add(jbDeleteAcc);

        jbLogOut = new JButton("Log Out");
        jbLogOut.setActionCommand(BTN_LOGOUT);
        jbLogOut.setForeground(Color.white);
        jbLogOut.setBackground(new Color(0,204,0));
        jbLogOut.setAlignmentX(Component.CENTER_ALIGNMENT);
        userPanel.add(jbLogOut);

        sidePanel.add(userPanel,BorderLayout.SOUTH);


        add(sidePanel,BorderLayout.WEST);
    }

    private void configurePlayerPanel(){
        JPanel playerPanel = new JPanel(new BorderLayout());
        JPanel controlsPanel = new JPanel();
        JPanel progressBarPanel = new JPanel();


        controlsPanel.setBackground(new Color(32,32,32));
        playerPanel.add(controlsPanel,BorderLayout.CENTER);
        playerPanel.add(progressBarPanel,BorderLayout.NORTH);

        GridLayout playerControlsGridLay = new GridLayout(1,5);
        playerControlsGridLay.setHgap(10);
        playerControlsGridLay.setVgap(0);
        playerPanel.setBackground(new Color(32,32,32));
        playerPanel.setPreferredSize(new Dimension(1280,100));
        controlsPanel.setLayout(playerControlsGridLay);

        // song progress bar

        bar.setValue(0);
        bar.setBounds(0,0,420,50);
        bar.setStringPainted(true);
        progressBarPanel.add(bar);


        jbLoop = new JButton(new ImageIcon("assets/loop.png"));
        jbLoop.setBackground(null);
        jbLoop.setBorder(BorderFactory.createEmptyBorder());
        jbLoop.setContentAreaFilled(false);
        jbLoop.setActionCommand(BTN_LOOP);
        controlsPanel.add(jbLoop);

        jbPrevious = new JButton(new ImageIcon("assets/previous.png"));
        jbPrevious.setBackground(null);
        jbPrevious.setBorder(BorderFactory.createEmptyBorder());
        jbPrevious.setContentAreaFilled(false);
        jbPrevious.setActionCommand(BTN_PREV);
        controlsPanel.add(jbPrevious);

        jbPlayPause = new JButton(new ImageIcon("assets/playButton.png"));
        jbPlayPause.setBackground(null);
        jbPlayPause.setBorder(BorderFactory.createEmptyBorder());
        jbPlayPause.setContentAreaFilled(false);
        jbPlayPause.setActionCommand(BTN_PLAYPAUSE);
        controlsPanel.add(jbPlayPause);

        jbNext = new JButton(new ImageIcon("assets/next.png"));
        jbNext.setBackground(null);
        jbNext.setBorder(BorderFactory.createEmptyBorder());
        jbNext.setContentAreaFilled(false);
        jbNext.setActionCommand(BTN_NEXT);
        controlsPanel.add(jbNext);

        jbShuffle = new JButton(new ImageIcon("assets/shuffle.png"));
        jbShuffle.setBackground(null);
        jbShuffle.setBorder(BorderFactory.createEmptyBorder());
        jbShuffle.setContentAreaFilled(false);
        jbShuffle.setActionCommand(BTN_PREV);
        controlsPanel.add(jbShuffle);

        add(playerPanel,BorderLayout.SOUTH);
    }

    public void setUsername(String username){
        this.username.setText(username);
    }

    public void registerController(ActionListener controller){
        jbPlaylists.addActionListener(controller);
        jbStatistics.addActionListener(controller);
        jbSongs.addActionListener(controller);
        jbAddSong.addActionListener(controller);
        jbDeleteAcc.addActionListener(controller);
        jbLogOut.addActionListener(controller);
        jbPlayPause.addActionListener(controller);

    }

    private void fillBar(){
        bar.setValue(10);
    }

    private void configureSongsCard(){
        jpMain.add(songsView,CARD_SONGS);
    }

    private void configurePlaylistsCard(){
        //JPanel jPanel = new JPanel();
        //jPanel.setBackground(Color.red);
        jpMain.add(songsView,CARD_PLAYLISTS);
    }

    private void configureStatisticsCard(){
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.green);
        jpMain.add(jPanel,CARD_STATISTICS);
    }

    public void pauseButton(){
        jbPlayPause.setIcon(new ImageIcon("assets/pause.png"));
        SongPlayerManager playerManager = new SongPlayerManager();
        playerManager.playSong(new Song("dammit","dude ranch","punk rock","blink182","/Users/alvarofeher/Desktop/WORK/DPO/dpoo-2122-s2-ice3/songs/blink-182 - Dammit (Official Video).mp3",3,"alvarofeher",69));

    }

    public void configureSlider(){

    }


    public void showSongsCard(){
        mainPanelManager.show(jpMain,CARD_SONGS);
    }
    public void showPlaylistsCard(){
        mainPanelManager.show(jpMain,CARD_PLAYLISTS);
    }
    public void showStatisticsCard(){
        mainPanelManager.show(jpMain,CARD_STATISTICS);
    }

    public SongsView getSongsView(){
        return songsView;
    }

    public PlaylistsView getPlaylistView() {
        return playlistView;
    }
}
