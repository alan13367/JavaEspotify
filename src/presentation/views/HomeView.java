package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeView extends JPanel {

    private JPanel jpMain;

    private final CardLayout mainPanelManager;


    //Side Panel Buttons
    private JButton jbSongs;
    private JButton jbPlaylists;
    private JButton jbStatistics;
    private JButton jbAddSong;

    public static final String BTN_PLAYLISTS = "BTN_PLAYLISTS";
    public static final String BTN_STATISTICS = "BTN_STATISTICS";
    public static final String BTN_SONGS = "BTN_SONGS";
    public static final String BTN_ADDSONG = "BTN_ADDSONG";



    //Player Buttons
    private JButton jbPlay;

    public static final String BTN_PLAY = "BTN_PLAY";


    //Cards Global Constants
    public static final String CARD_SONGS = "CARD_SONGS";
    public static final String CARD_PLAYLISTS = "CARD_PLAYLISTS";
    public static final String CARD_STATISTICS = "CARD_STATISTICS";

    public HomeView(){
        setLayout(new BorderLayout());
        mainPanelManager = new CardLayout();

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
        //JScrollPane jsp = new JScrollPane();
        //mainPanel.add(jsp);
    }

    private void configureSidePanel(){
        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(280,720));
        sidePanel.setBackground(Color.black);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);
        GridLayout buttonGridLayout = new GridLayout(4,1);
        buttonGridLayout.setHgap(0);
        buttonGridLayout.setVgap(15);
        buttonPanel.setLayout(buttonGridLayout);
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
        userPanel.setBackground(Color.black);
        JLabel username = new JLabel("Username");
        username.setForeground(Color.white);
        userPanel.add(username);
        sidePanel.add(userPanel,BorderLayout.SOUTH);

        add(sidePanel,BorderLayout.WEST);
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

        jbPlay = new JButton(new ImageIcon("assets/playButton.png"));
        jbPlay.setBackground(null);
        jbPlay.setBorder(BorderFactory.createEmptyBorder());
        jbPlay.setContentAreaFilled(false);
        jbPlay.setActionCommand(BTN_PLAY);
        controlsPanel.add(jbPlay);


        add(playerPanel,BorderLayout.SOUTH);
    }

    public void registerController(ActionListener controller){
        jbPlaylists.addActionListener(controller);
        jbStatistics.addActionListener(controller);
        jbSongs.addActionListener(controller);
        jbPlay.addActionListener(controller);
    }

    private void configureSongsCard(){
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.white);
        jpMain.add(jPanel,CARD_SONGS);
    }

    private void configurePlaylistsCard(){
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.red);
        jpMain.add(jPanel,CARD_PLAYLISTS);
    }

    private void configureStatisticsCard(){
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.green);
        jpMain.add(jPanel,CARD_STATISTICS);
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




    public  void start(){
        setVisible(true);
    }
}
