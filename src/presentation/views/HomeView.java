package presentation.views;

import presentation.controllers.HomeController;
import presentation.views.GUIassets.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * the GUI of the home view
 * @author Alan Beltrán
 * @version 1.0
 * @since 30/04/2022
 */
public class HomeView extends JPanel {

    private final SongsView songsView;

    private final PlaylistsView playlistsView;

    private final AddSongsView addSongsView;

    private final PlayerView playerView;

    private final StatisticsView statisticsView;

    private JPanel jpMain;

    private final CardLayout mainPanelManager;

    //Side Panel Buttons
    private JButton jbSongs,jbPlaylists,jbStatistics,jbAddSong,jbLogOut,jbDeleteAcc;
    private JLabel username;

    public static final String BTN_PLAYLISTS = "BTN_PLAYLISTS";
    public static final String BTN_STATISTICS = "BTN_STATISTICS";
    public static final String BTN_SONGS = "BTN_SONGS";
    public static final String BTN_ADDSONG = "BTN_ADDSONG";
    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_DELETEACC = "BTN_DELETEACC";


    //Cards Global Constants
    public static final String CARD_SONGS = "CARD_SONGS";
    public static final String CARD_PLAYLISTS = "CARD_PLAYLISTS";
    public static final String CARD_STATISTICS = "CARD_STATISTICS";
    public static final String CARD_ADD_SONGS = "CARD_ADD_SONGS";

    /**
     * The view constructor, sets the layout of the panel and configures view, calls all other views except the sign in and sign up view
     */
    public HomeView(){
        setLayout(new BorderLayout());
        mainPanelManager = new CardLayout();
        songsView = new SongsView();
        playlistsView = new PlaylistsView();
        statisticsView = new StatisticsView();
        addSongsView = new AddSongsView();
        playerView = new PlayerView();
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
        jpMain = new RoundedPanel(ThemeDimensions.BORDER_RADIUS_LARGE, ThemeColors.BACKGROUND_SECONDARY);
        jpMain.setLayout(mainPanelManager);
        jpMain.setBorder(new EmptyBorder(ThemeDimensions.CARD_PADDING, ThemeDimensions.CARD_PADDING,
                ThemeDimensions.CARD_PADDING, ThemeDimensions.CARD_PADDING));
        add(jpMain,BorderLayout.CENTER);
    }

    private void configureSidePanel(){
        RoundedPanel sidePanel = new RoundedPanel(0, ThemeColors.BACKGROUND_PRIMARY);
        sidePanel.setLayout(new BorderLayout());
        sidePanel.setBorder(new EmptyBorder(ThemeDimensions.CARD_PADDING, ThemeDimensions.CARD_PADDING_SMALL,
                ThemeDimensions.CARD_PADDING, ThemeDimensions.CARD_PADDING_SMALL));
        sidePanel.setPreferredSize(new Dimension(ThemeDimensions.SIDEBAR_WIDTH, 720));
        sidePanel.setMaximumSize(sidePanel.getPreferredSize());
        sidePanel.setMinimumSize(sidePanel.getPreferredSize());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(ThemeColors.BACKGROUND_PRIMARY);
        buttonPanel.setLayout(new GridLayout(5, 1, 0, ThemeDimensions.SIDEBAR_GAP));
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
        userPanel.setBackground(ThemeColors.BACKGROUND_PRIMARY);
        userPanel.setLayout(new GridLayout(3, 1, 0, ThemeDimensions.SIDEBAR_GAP));
        username = new JLabel();
        username.setForeground(ThemeColors.TEXT_PRIMARY);
        username.setFont(ThemeFonts.SIDEBAR_USER);
        userPanel.add(username);

        jbDeleteAcc = createHomeButton("Delete Account","assets/delete-2-32.png",BTN_DELETEACC);
        userPanel.add(jbDeleteAcc);

        jbLogOut = createHomeButton("Log Out","assets/exit-32.png",BTN_LOGOUT);
        userPanel.add(jbLogOut);

        sidePanel.add(userPanel,BorderLayout.SOUTH);
        add(sidePanel,BorderLayout.WEST);
    }

    private JButton createHomeButton(String text,String imagePath,final String actionCommand){
        StyledButton button = new StyledButton(" " + text, imagePath, StyledButton.ButtonType.PRIMARY);
        button.setActionCommand(actionCommand);
        button.setFont(ThemeFonts.SIDEBAR_BUTTON);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setPreferredSize(new Dimension(ThemeDimensions.SIDEBAR_WIDTH - 40, ThemeDimensions.SIDEBAR_BUTTON_HEIGHT));
        return button;
    }

    private void configurePlayerPanel(){
        add(playerView,BorderLayout.SOUTH);
    }

    /**
     * username setter
     * @param username the username of the current user
     */
    public void setUsername(String username){
        this.username.setText("  "+username);
    }

    /**
     * adds an action listener to all the components
     * @param controller home Controller
     */
    public void registerController(HomeController controller){
        jbPlaylists.addActionListener(controller);
        jbStatistics.addActionListener(controller);
        jbSongs.addActionListener(controller);
        jbAddSong.addActionListener(controller);
        jbDeleteAcc.addActionListener(controller);
        jbLogOut.addActionListener(controller);
    }

    private void configureSongsCard(){
        jpMain.add(songsView,CARD_SONGS);
    }

    private void configurePlaylistsCard(){
        jpMain.add(playlistsView,CARD_PLAYLISTS);
    }

    private void configureStatisticsCard(){
        jpMain.add(statisticsView,CARD_STATISTICS);
    }

    private void configureAddSongsCard() { jpMain.add(addSongsView,CARD_ADD_SONGS); }


    /**
     * shows the songs view
     * @see SongsView
     */
    public void showSongsCard(){
        mainPanelManager.show(jpMain,CARD_SONGS);
    }

    /**
     * shows the playlist view
     * @see PlaylistsView
     */
    public void showPlaylistsCard(){
        mainPanelManager.show(jpMain,CARD_PLAYLISTS);
    }

    /**
     * shows the stats view
     * @see StatisticsView
     */
    public void showStatisticsCard(){
        mainPanelManager.show(jpMain,CARD_STATISTICS);
    }

    /**
     * shows the add song view
     * @see AddSongsView
     */
    public void showAddSongsCard() { mainPanelManager.show(jpMain, CARD_ADD_SONGS);}


    /**
     * songs view getter
     * @return the songs view
     */
    public SongsView getSongsView(){
        return songsView;
    }

    /**
     * playlist view getter
     * @return the playlist view
     */
    public PlaylistsView getPlaylistsView(){return playlistsView;}

    /**
     * add song view getter
     * @return the add song view
     */
    public AddSongsView getAddSongsView(){return addSongsView;}

    /**
     * player view getter
     * @return the player view
     */
    public PlayerView getPlayerView(){return playerView;}

    /**
     * statistics view getter
     * @return the statistics view
     */
    public StatisticsView getStatisticsView() {return statisticsView;}

}
