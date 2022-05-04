package presentation.views;

import presentation.controllers.HomeController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.UnknownServiceException;

public class HomeView extends JPanel {

    private SongsView songsView;

    private PlaylistsView playlistsView;

    private AddSongsView addSongsView;

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

    public static final String BTN_PLAYPAUSE = "BTN_PLAYPAUSE";
    public static final String BTN_LOOP = "BTN_LOOP";
    public static final String BTN_NEXT = "BTN_NEXT";
    public static final String BTN_PREV = "BTN_PREV";

    public static final String BTN_PAUSE = "BTN_PAUSE";
    public static final String BTN_PLAYSONG = "BTN_PLAYSONG";

   // public static final String BTN_PAUSE = "BTN_PAUSE";

    //Cards Global Constants
    public static final String CARD_SONGS = "CARD_SONGS";
    public static final String CARD_PLAYLISTS = "CARD_PLAYLISTS";
    public static final String CARD_STATISTICS = "CARD_STATISTICS";
    public static final String CARD_ADD_SONGS = "CARD_ADD_SONGS";


    public HomeView(){
        setLayout(new BorderLayout());
        mainPanelManager = new CardLayout();
        songsView = new SongsView();
        playlistsView = new PlaylistsView();
        addSongsView = new AddSongsView();
        configureView();
    }


    private void configureView(){
        configureMainPanel();
        configureSidePanel();
        configurePlayerPanel();
        configureSongsCard();
        configurePlaylistsCard();
        configureStatisticsCard();
        configureAddSongsCard();
    }



    private void configureMainPanel(){
        jpMain = new JPanel(mainPanelManager);
        jpMain.setBackground(new Color(64,64,64));
        add(jpMain,BorderLayout.CENTER);
    }

    private void configureSidePanel(){
        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(300,720));
        sidePanel.setBackground(Color.black);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);
        GridLayout northGridLayout = new GridLayout(5,1);
        northGridLayout.setHgap(0);
        northGridLayout.setVgap(15);
        buttonPanel.setLayout(northGridLayout);
        sidePanel.add(buttonPanel,BorderLayout.NORTH);

        jbSongs = createHomeButton("Songs","assets/music-2-32.png",BTN_SONGS);
        buttonPanel.add(jbSongs);

        jbPlaylists = createHomeButton("Playlists","assets/playlist-32.png",BTN_PLAYLISTS);
        buttonPanel.add(jbPlaylists);

        jbStatistics = createHomeButton("Statistics","assets/statistics-32.png",BTN_STATISTICS);
        buttonPanel.add(jbStatistics);

        jbAddSong = createHomeButton("Add Song","assets/plus-4-32.png",BTN_ADDSONG);
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
        username.setFont(new Font("Arial",Font.PLAIN,25));
        userPanel.add(username);

        jbDeleteAcc = createHomeButton("Delete Account","assets/delete-2-32.png",BTN_DELETEACC);
        userPanel.add(jbDeleteAcc);

        jbLogOut = createHomeButton("Log Out","assets/exit-32.png",BTN_LOGOUT);
        userPanel.add(jbLogOut);

        sidePanel.add(userPanel,BorderLayout.SOUTH);


        add(sidePanel,BorderLayout.WEST);
    }

    private JButton createHomeButton(String text,String imagePath,final String actionCommand){
        JButton button = new JButton(" "+text,new ImageIcon(imagePath));
        button.setFont(new Font("Arial",Font.BOLD,30));
        button.setActionCommand(actionCommand);
        button.setForeground(Color.white);
        button.setBackground(new Color(0,204,0));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private void configurePlayerPanel(){
        JPanel playerPanel = new JPanel(new BorderLayout());
        JPanel controlsPanel = new JPanel();
        controlsPanel.setBackground(new Color(32,32,32));
        playerPanel.add(controlsPanel,BorderLayout.CENTER);

        GridLayout playerControlsGridLay = new GridLayout(1,5);
        playerControlsGridLay.setHgap(10);
        playerControlsGridLay.setVgap(0);
        playerPanel.setBackground(new Color(32,32,32));
        playerPanel.setPreferredSize(new Dimension(1280,80));
        controlsPanel.setLayout(playerControlsGridLay);

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
        this.username.setText("  "+username);
    }

    public void registerController(HomeController controller){
        jbPlaylists.addActionListener(controller);
        jbStatistics.addActionListener(controller);
        jbSongs.addActionListener(controller);
        jbAddSong.addActionListener(controller);
        jbDeleteAcc.addActionListener(controller);
        jbLogOut.addActionListener(controller);
        jbPlayPause.addActionListener(controller);

    }

    private void configureSongsCard(){
        jpMain.add(songsView,CARD_SONGS);
    }

    private void configurePlaylistsCard(){
        jpMain.add(playlistsView,CARD_PLAYLISTS);
    }

    private void configureStatisticsCard(){
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.green);
        jpMain.add(jPanel,CARD_STATISTICS);
    }

    private void configureAddSongsCard() { jpMain.add(addSongsView,CARD_ADD_SONGS); }

    public void pauseButton(){
        jbPlayPause.setIcon(new ImageIcon("assets/pause.png"));
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
    public void showAddSongsCard() { mainPanelManager.show(jpMain, CARD_ADD_SONGS);}


    public SongsView getSongsView(){
        return songsView;
    }
    public PlaylistsView getPlaylistsView(){return playlistsView;}
    public AddSongsView getAddSongsView(){return addSongsView;}

}
