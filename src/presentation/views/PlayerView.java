package presentation.views;

import presentation.controllers.PlayerController;

import javax.swing.*;
import java.awt.*;

public class PlayerView extends JPanel {
    //Player Buttons
    private JButton jbPlayPause, jbShuffle, jbLoop, jbNext, jbPrevious;

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
        controlsPanel.setBackground(new Color(32,32,32));
        add(controlsPanel,BorderLayout.CENTER);

        GridLayout playerControlsGridLay = new GridLayout(1,5);
        playerControlsGridLay.setHgap(10);
        playerControlsGridLay.setVgap(0);
        setBackground(new Color(32,32,32));
        setPreferredSize(new Dimension(1500,80));
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
    }

    public void registerController(PlayerController controller){
        jbPlayPause.addActionListener(controller);
        jbNext.addActionListener(controller);
        jbShuffle.addActionListener(controller);
        jbPrevious.addActionListener(controller);
        jbLoop.addActionListener(controller);
    }

    public void changePlayButton(){
        jbPlayPause.setIcon(new ImageIcon("assets/pause.png"));
    }
    public void changePauseButton(){jbPlayPause.setIcon(new ImageIcon("assets/playButton.png"));}
}
