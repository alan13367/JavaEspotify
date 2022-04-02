package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeView extends JFrame {

    private final CardLayout cardManager;

    private JPanel jpMain;

    private JButton jbSongs;

    private JButton jbPlaylists;

    private JButton jbStatistics;


    public static final String BTN_PLAYLISTS = "BTN_PLAYLISTS";
    public static final String BTN_STATISTICS = "BTN_STATISTICS";
    public static final String BTN_SONGS = "BTN_SONGS";

    public HomeView(){
        setLayout(new BorderLayout());
        cardManager = new CardLayout();

        configureView();
        configureWindow();
    }

    private void configureWindow(){
        setTitle("Home");
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void configureView(){
        configureMainPanel();
        configureSidePanel();
        configurePlayerPanel();
    }

    private void configureMainPanel(){
        jpMain = new JPanel(cardManager);
        jpMain.setBackground(new Color(32,32,32));
        add(jpMain,BorderLayout.CENTER);
        //JScrollPane jsp = new JScrollPane();
        //mainPanel.add(jsp);
    }

    private void configureSidePanel(){
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(280,720));
        sidePanel.setBackground(Color.black);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);
        GridLayout buttonGridLayout = new GridLayout(4,1);
        buttonGridLayout.setHgap(0);
        buttonGridLayout.setVgap(10);
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

        add(sidePanel,BorderLayout.WEST);
    }

    private void configurePlayerPanel(){
        JPanel playerPanel = new JPanel(new FlowLayout());
        playerPanel.setBackground(Color.black);
        playerPanel.setPreferredSize(new Dimension(1280,80));
        add(playerPanel,BorderLayout.SOUTH);
    }

    public void registerController(ActionListener controller){
        jbPlaylists.addActionListener(controller);
        jbStatistics.addActionListener(controller);
        jbSongs.addActionListener(controller);

    }

    public  void start(){
        setVisible(true);
    }
}
