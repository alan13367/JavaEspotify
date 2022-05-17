package presentation.views;

import presentation.controllers.PlayerController;

import javax.swing.*;
import java.awt.*;

public class PlayerView extends JPanel {
    //Player Buttons
    private JButton jbPlayPause, jbShuffle, jbLoop, jbNext, jbPrevious;
    private JSlider jslider;

    public static final String BTN_PLAYPAUSE = "BTN_PLAYPAUSE";
    public static final String BTN_LOOP = "BTN_LOOP";
    public static final String BTN_NEXT = "BTN_NEXT";
    public static final String BTN_PREV = "BTN_PREV";
    public static final String BTN_SHUFFLE ="BTN_SHUFFLE";


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

        playerPanel.setLayout(new BorderLayout());
        songPanel.setLayout(new GridLayout(2, 1, 1, 1));

        JPanel aux4 = new JPanel();
        //aux4.setBackground(new Color(32, 32, 32));
        aux4.setPreferredSize(new Dimension(350, 200));
        aux4.setMinimumSize(aux4.getPreferredSize());
        aux4.setMaximumSize(aux4.getPreferredSize());
        playerPanel.add(aux4, BorderLayout.WEST);

        ImageIcon icon = new ImageIcon("assets/loop.png");
        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance( 15, 15,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon( newimg );
        jbLoop = new JButton(icon);
        jbLoop.setBackground(null);
        jbLoop.setBorder(BorderFactory.createEmptyBorder());
        jbLoop.setContentAreaFilled(false);
        jbLoop.setActionCommand(BTN_LOOP);
        controlsPanel.add(jbLoop);

        icon = new ImageIcon("assets/previous.png");
        img = icon.getImage() ;
        newimg = img.getScaledInstance( 15, 15,  java.awt.Image.SCALE_SMOOTH ) ;
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
        newimg = img.getScaledInstance( 15, 15,  java.awt.Image.SCALE_SMOOTH ) ;
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
        jbShuffle.setActionCommand(BTN_PREV);
        controlsPanel.add(jbShuffle);

        JPanel aux3 = new JPanel();
        //aux3.setBackground(new Color(32, 32, 32));
        aux3.setPreferredSize(new Dimension(350, 40));
        aux3.setMinimumSize(aux3.getPreferredSize());
        aux3.setMaximumSize(aux3.getPreferredSize());
        playerPanel.add(aux3, BorderLayout.WEST);

        playerPanel.add(controlsPanel, BorderLayout.NORTH);
        JPanel aux = new JPanel();
        aux.setBackground(new Color(32, 32, 32));
        aux.setPreferredSize(new Dimension(350, 80));
        aux.setMinimumSize(aux.getPreferredSize());
        aux.setMaximumSize(aux.getPreferredSize());
        playerPanel.add(aux, BorderLayout.WEST);
        jslider = new JSlider(0, 100, 0);
        jslider.setBackground(new Color(32, 32, 32));
        jslider.setPreferredSize(new Dimension(10, 40));
        jslider.setMaximumSize(jslider.getPreferredSize());
        jslider.setMinimumSize(jslider.getPreferredSize());
        playerPanel.add(jslider, BorderLayout.CENTER);
        JPanel aux2 = new JPanel();
        aux2.setBackground(new Color(32, 32, 32));
        aux2.setPreferredSize(new Dimension(350, 80));
        aux2.setMinimumSize(aux2.getPreferredSize());
        aux2.setMaximumSize(aux2.getPreferredSize());
        playerPanel.add(aux2, BorderLayout.EAST);

        JLabel songName = new JLabel("\n   She don't give a fo");
        JLabel songAuthor = new JLabel("     Duki");
        songName.setBackground(new Color(32, 32, 32));
        songName.setFont(new Font("Arial", Font.PLAIN, 20));
        songAuthor.setBackground(new Color(32, 32, 32));
        songAuthor.setFont(new Font("Arial", Font.PLAIN, 14));
        songName.setForeground(Color.white);
        songAuthor.setForeground(Color.white);
        songPanel.add(songName);
        songPanel.add(songAuthor);
    }

    public void initSlider(int seconds) {
        jslider = new JSlider(0, seconds, 0);
    }

    public void registerController(PlayerController controller){
        jbPlayPause.addActionListener(controller);
        jbNext.addActionListener(controller);
        jbShuffle.addActionListener(controller);
        jbPrevious.addActionListener(controller);
        jbLoop.addActionListener(controller);
    }

    public void changePlayPause(boolean isPlaying){
        if(isPlaying){
            jbPlayPause.setIcon(new ImageIcon("assets/pause.png"));
        }else{
            jbPlayPause.setIcon(new ImageIcon("assets/playButton.png"));
        }
    }

}
