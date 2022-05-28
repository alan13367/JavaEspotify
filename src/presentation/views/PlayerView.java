package presentation.views;

import business.entities.Song;
import presentation.controllers.PlayerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * the GUI of the player view
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 19/04/2022
 */
public class PlayerView extends JPanel {
    //Player Buttons
    private JButton jbPlayPause, jbShuffle, jbLoop, jbNext, jbPrevious;
    private JSlider jslider;
    private JLabel songName, songAuthor;
    private JTextField currentTime;
    private JTextField totalTime;
    private Timer timer;
    private int position=0;

    public static final String BTN_PLAYPAUSE = "BTN_PLAYPAUSE";
    public static final String BTN_LOOP = "BTN_LOOP";
    public static final String BTN_NEXT = "BTN_NEXT";
    public static final String BTN_PREV = "BTN_PREV";
    public static final String BTN_SHUFFLE ="BTN_SHUFFLE";

    /**
     * The view constructor, sets the layout of the panel and configures view
     */
    public PlayerView(){
        setLayout(new BorderLayout());
        configureView();
    }
    private void configureView(){
        JPanel controlsPanel = new JPanel();
        JPanel songPanel = new JPanel();
        JPanel playerPanel = new JPanel();
        controlsPanel.setBackground(new Color(32,32,32));
        songPanel.setBackground(new Color(32, 32, 32));
        /*playerPanel.setPreferredSize(new Dimension(50, 80));
        playerPanel.setMaximumSize(controlsPanel.getPreferredSize());
        playerPanel.setMinimumSize(controlsPanel.getPreferredSize());*/
        add(songPanel, BorderLayout.WEST);
        add(playerPanel,BorderLayout.CENTER);

        GridLayout playerControlsGridLay = new GridLayout(1,5);
        playerControlsGridLay.setHgap(2);
        playerControlsGridLay.setVgap(0);
        setBackground(new Color(32,32,32));
        setPreferredSize(new Dimension(1500,80));
        controlsPanel.setLayout(playerControlsGridLay);

        playerPanel.setLayout(new GridLayout(2, 1));
        songPanel.setLayout(new GridLayout(2, 1, 1, 1));

        JPanel aux3 = new JPanel();
        aux3.setSize(400, 60);
        aux3.setBackground(new Color(32, 32, 32));
        JPanel aux4 = new JPanel();
        aux4.setSize(400, 60);
        aux4.setBackground(new Color(32, 32, 32));

        ImageIcon icon = new ImageIcon("assets/loop.png");
        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance( 15, 15,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon(newimg);
        jbLoop = new JButton(icon);
        jbLoop.setBackground(null);
        jbLoop.setBorder(BorderFactory.createEmptyBorder());
        jbLoop.setContentAreaFilled(false);
        jbLoop.setActionCommand(BTN_LOOP);
        controlsPanel.add(aux3);
        controlsPanel.add(jbLoop);

        icon = new ImageIcon("assets/previous.png");
        img = icon.getImage() ;
        newimg = img.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon( newimg );
        jbPrevious = new JButton(icon);
        jbPrevious.setBackground(null);
        jbPrevious.setBorder(BorderFactory.createEmptyBorder());
        jbPrevious.setContentAreaFilled(false);
        jbPrevious.setActionCommand(BTN_PREV);
        controlsPanel.add(jbPrevious);

        icon = new ImageIcon("assets/playButton.png");
        img = icon.getImage() ;
        newimg = img.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon( newimg );
        jbPlayPause = new JButton(icon);
        jbPlayPause.setBackground(null);
        jbPlayPause.setBorder(BorderFactory.createEmptyBorder());
        jbPlayPause.setContentAreaFilled(false);
        jbPlayPause.setActionCommand(BTN_PLAYPAUSE);
        controlsPanel.add(jbPlayPause);

        icon = new ImageIcon("assets/next.png");
        img = icon.getImage() ;
        newimg = img.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon( newimg );
        jbNext = new JButton(icon);
        jbNext.setBackground(null);
        jbNext.setBorder(BorderFactory.createEmptyBorder());
        jbNext.setContentAreaFilled(false);
        jbNext.setActionCommand(BTN_NEXT);
        controlsPanel.add(jbNext);

        icon = new ImageIcon("assets/shuffle.png");
        img = icon.getImage();
        newimg = img.getScaledInstance( 15, 15,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon( newimg );
        jbShuffle = new JButton(icon);
        jbShuffle.setBackground(null);
        jbShuffle.setBorder(BorderFactory.createEmptyBorder());
        jbShuffle.setContentAreaFilled(false);
        jbShuffle.setActionCommand(BTN_SHUFFLE);
        controlsPanel.add(jbShuffle);
        controlsPanel.add(aux4);

        playerPanel.add(controlsPanel);
        jslider = new JSlider(0, 100, 0);
        jslider.setBackground(new Color(32, 32, 32));
        jslider.setPreferredSize(new Dimension(800,20));
        JPanel jsliderPanel = new JPanel(new FlowLayout());
        jsliderPanel.setBackground(new Color(32, 32, 32));
        JPanel aux = new JPanel();
        aux.setSize(60, 60);
        aux.setBackground(new Color(32, 32, 32));
        JPanel aux2 = new JPanel();
        aux2.setSize(60, 60);
        aux2.setBackground(new Color(32, 32, 32));
        currentTime = new JTextField();
        totalTime = new JTextField();
        currentTime.setBackground(new Color(32, 32, 32));
        currentTime.setFont(new Font("arial", Font.PLAIN, 15));
        totalTime.setFont(new Font("arial", Font.PLAIN, 15));
        currentTime.setForeground(Color.white);
        totalTime.setForeground(Color.white);
        totalTime.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        currentTime.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        totalTime.setBackground(new Color(32, 32, 32));
        currentTime.setEditable(false);
        totalTime.setEditable(false);
        jsliderPanel.add(currentTime);
        jsliderPanel.add(jslider);
        jsliderPanel.add(totalTime);
        playerPanel.add(jsliderPanel);

        songName = new JLabel("\n   ");
        songAuthor = new JLabel("   ");
        songName.setBackground(new Color(32, 32, 32));
        songName.setFont(new Font("Arial", Font.PLAIN, 20));
        songAuthor.setBackground(new Color(32, 32, 32));
        songAuthor.setFont(new Font("Arial", Font.PLAIN, 14));
        songName.setForeground(Color.white);
        songAuthor.setForeground(Color.white);
        songPanel.add(songName);
        songPanel.add(songAuthor);

    }

    /**
     * Initializing the slider
     * @param song song data
     */
    public void initSlider(Song song) {
        jslider = new JSlider(0, song.getSongSeconds(), 0);
        //moveSlider(jslider);
    }


    /**
     * jSlider getter
     * @return the slider
     */
    public JSlider getJslider() {
        return jslider;
    }

    /**
     * adds an action listener to all the components
     * @param controller playerController
     */
    public void registerController(PlayerController controller){
        jbPlayPause.addActionListener(controller);
        jbNext.addActionListener(controller);
        jbShuffle.addActionListener(controller);
        jbPrevious.addActionListener(controller);
        jbLoop.addActionListener(controller);
    }

    /**
     * change the button icon from paused to played, and vice versa
     * @param isPlaying true if the song is playing, false otherwise
     */
    public void changePlayPause(boolean isPlaying){
        if(isPlaying){
            ImageIcon icon = new ImageIcon("assets/pause.png");
            Image img = icon.getImage() ;
            Image newimg = img.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;
            icon = new ImageIcon( newimg );
            jbPlayPause.setIcon(icon);
        }else{
            ImageIcon icon = new ImageIcon("assets/playButton.png");
            Image img = icon.getImage() ;
            Image newimg = img.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;
            icon = new ImageIcon( newimg );
            jbPlayPause.setIcon(icon);
        }
    }

    /**
     * change the song details view
     * @param title of the song
     * @param author author of the song
     */
    public void changeShownSong(String title, String author){
        songName.setText(title);
        songAuthor.setText(author);
    }

    /**
     * change the song time view
     * @param minutes minutes part duration of the song
     * @param seconds seconds part duration of the song
     */
    public void changeTotalTime(int minutes, int seconds) {
        int seconds_rest;
        seconds_rest = seconds % 60;
        String time = minutes + ":" + seconds_rest;
        totalTime.setText(time);
    }

    /**
     * pops up an error message
     * @param message the error message
     */
    public void showErrorDialog(String message){
        JOptionPane.showMessageDialog(this, message,"Error", JOptionPane.ERROR_MESSAGE);
    }

    public void startTimer(int seconds){
        if(timer != null){
            timer.stop();
        }
        jslider.setMaximum(seconds);
        position = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jslider.setValue(position++);
                if(position == seconds){
                    timer.stop();
                    jslider.setValue(0);
                }
            }
        });
        timer.start();
    }

    public void resumeTimer(){
        timer.start();
    }

    public void pauseTimer(){
        timer.stop();
    }
}
