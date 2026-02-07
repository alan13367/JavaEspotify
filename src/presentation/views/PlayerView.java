package presentation.views;

import presentation.controllers.PlayerController;
import presentation.views.GUIassets.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * the GUI of the player view
 * @author Alan Beltrán
 * @version 1.0
 * @since 19/04/2022
 */
public class PlayerView extends JPanel {
    // Constants for button action commands
    public static final String BTN_PLAYPAUSE = "BTN_PLAYPAUSE";
    public static final String BTN_LOOP = "BTN_LOOP";
    public static final String BTN_NEXT = "BTN_NEXT";
    public static final String BTN_PREV = "BTN_PREV";
    public static final String BTN_STOP = "BTN_STOP";
    public static final String BTN_MUTE = "BTN_MUTE";
    
    // Constants for icon paths
    private static final String ICON_PLAY = "assets/playButton.png";
    private static final String ICON_PAUSE = "assets/pause.png";
    private static final String ICON_LOOP = "assets/loop.png";
    private static final String ICON_LOOP_ACTIVE = "assets/loop_active.png";
    private static final String ICON_PREVIOUS = "assets/previous.png";
    private static final String ICON_NEXT = "assets/next.png";
    private static final String ICON_STOP = "assets/stop.jpg";
    
    //Player Buttons
    private JButton jbPlayPause, jbStop, jbLoop, jbNext, jbPrevious, jbMute;
    private JSlider jslider, volumeSlider;
    private JLabel songName, songAuthor;
    private JTextField currentTime;
    private JTextField totalTime;
    private boolean isLoopActive = false;
    
    private PlayerController playerController;

    /**
     * The view constructor, sets the layout of the panel and configures view
     */
    public PlayerView(){
        setLayout(new BorderLayout());
        configureView();
    }
    private void configureView(){
        setBackground(ThemeColors.PLAYER_BACKGROUND);
        setPreferredSize(new Dimension(1500, ThemeDimensions.PLAYER_HEIGHT));
        setBorder(new EmptyBorder(ThemeDimensions.COMPONENT_GAP, ThemeDimensions.CARD_PADDING, 
                ThemeDimensions.COMPONENT_GAP, ThemeDimensions.CARD_PADDING));

        // Song info panel (left side)
        JPanel songPanel = new JPanel(new GridLayout(2, 1, 0, ThemeDimensions.COMPONENT_GAP));
        songPanel.setBackground(ThemeColors.PLAYER_BACKGROUND);
        songPanel.setPreferredSize(new Dimension(300, ThemeDimensions.PLAYER_HEIGHT - 20));

        songName = new JLabel("   ");
        songName.setFont(ThemeFonts.PLAYER_TITLE);
        songName.setForeground(ThemeColors.TEXT_PRIMARY);
        songName.setBackground(ThemeColors.PLAYER_BACKGROUND);

        songAuthor = new JLabel("   ");
        songAuthor.setFont(ThemeFonts.PLAYER_ARTIST);
        songAuthor.setForeground(ThemeColors.TEXT_SECONDARY);
        songAuthor.setBackground(ThemeColors.PLAYER_BACKGROUND);

        songPanel.add(songName);
        songPanel.add(songAuthor);
        add(songPanel, BorderLayout.WEST);

        // Player controls panel (center)
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBackground(ThemeColors.PLAYER_BACKGROUND);

        // Control buttons
        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, ThemeDimensions.COMPONENT_GAP_LARGE, 0));
        controlsPanel.setBackground(ThemeColors.PLAYER_BACKGROUND);

        jbLoop = createIconButton(ICON_LOOP, 20, 20, BTN_LOOP);
        controlsPanel.add(jbLoop);

        jbPrevious = createIconButton(ICON_PREVIOUS, 24, 24, BTN_PREV);
        controlsPanel.add(jbPrevious);

        jbPlayPause = createIconButton(ICON_PLAY, 36, 36, BTN_PLAYPAUSE);
        controlsPanel.add(jbPlayPause);

        jbNext = createIconButton(ICON_NEXT, 24, 24, BTN_NEXT);
        controlsPanel.add(jbNext);

        jbStop = createIconButton(ICON_STOP, 20, 20, BTN_STOP);
        controlsPanel.add(jbStop);

        playerPanel.add(controlsPanel, BorderLayout.NORTH);

        // Progress bar panel
        JPanel progressPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, ThemeDimensions.COMPONENT_GAP, 0));
        progressPanel.setBackground(ThemeColors.PLAYER_BACKGROUND);

        currentTime = new JTextField("0:00");
        currentTime.setFont(ThemeFonts.PLAYER_TIME);
        currentTime.setForeground(ThemeColors.TEXT_SECONDARY);
        currentTime.setBackground(ThemeColors.PLAYER_BACKGROUND);
        currentTime.setBorder(BorderFactory.createEmptyBorder());
        currentTime.setEditable(false);
        currentTime.setPreferredSize(new Dimension(45, 20));

        totalTime = new JTextField("0:00");
        totalTime.setFont(ThemeFonts.PLAYER_TIME);
        totalTime.setForeground(ThemeColors.TEXT_SECONDARY);
        totalTime.setBackground(ThemeColors.PLAYER_BACKGROUND);
        totalTime.setBorder(BorderFactory.createEmptyBorder());
        totalTime.setEditable(false);
        totalTime.setPreferredSize(new Dimension(45, 20));

        jslider = new JSlider(0, 100, 0);
        jslider.setBackground(ThemeColors.PLAYER_BACKGROUND);
        jslider.setPreferredSize(new Dimension(600, 20));
        jslider.setCursor(new Cursor(Cursor.HAND_CURSOR));

        progressPanel.add(currentTime);
        progressPanel.add(jslider);
        progressPanel.add(totalTime);

        playerPanel.add(progressPanel, BorderLayout.SOUTH);
        add(playerPanel, BorderLayout.CENTER);

        // Volume panel (right side)
        JPanel volumePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, ThemeDimensions.COMPONENT_GAP, 0));
        volumePanel.setBackground(ThemeColors.PLAYER_BACKGROUND);
        volumePanel.setPreferredSize(new Dimension(200, ThemeDimensions.PLAYER_HEIGHT - 20));

        // Volume mute button - using programmatic icon
        jbMute = createVolumeButton(2, 24, 24, BTN_MUTE); // Start with medium volume icon
        volumePanel.add(jbMute);

        // Volume slider
        volumeSlider = new JSlider(0, 100, 50); // Default 50%
        volumeSlider.setBackground(ThemeColors.PLAYER_BACKGROUND);
        volumeSlider.setPreferredSize(new Dimension(120, 20));
        volumeSlider.setCursor(new Cursor(Cursor.HAND_CURSOR));
        volumePanel.add(volumeSlider);

        add(volumePanel, BorderLayout.EAST);
    }

    /**
     * Helper method to create a button with a scaled icon
     * @param iconPath path to the icon image
     * @param width desired width
     * @param height desired height
     * @param actionCommand action command for the button
     * @return configured JButton
     */
    private JButton createIconButton(String iconPath, int width, int height, String actionCommand) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImg);
        JButton button = new JButton(icon);
        button.setBackground(null);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setActionCommand(actionCommand);
        return button;
    }

    /**
     * Creates a volume icon button with programmatically drawn icon
     * @param volumeLevel 0=mute, 1=low, 2=medium, 3=high
     * @param width icon width
     * @param height icon height
     * @param actionCommand action command
     * @return JButton with volume icon
     */
    private JButton createVolumeButton(int volumeLevel, int width, int height, String actionCommand) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(null);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setActionCommand(actionCommand);
        
        // Draw volume icon
        ImageIcon icon = createVolumeIcon(volumeLevel, width, height);
        button.setIcon(icon);
        
        return button;
    }

    /**
     * Creates a volume icon image programmatically
     * @param volumeLevel 0=mute, 1=low, 2=medium, 3=high
     * @param width icon width
     * @param height icon height
     * @return ImageIcon with volume graphic
     */
    private ImageIcon createVolumeIcon(int volumeLevel, int width, int height) {
        java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(ThemeColors.TEXT_PRIMARY);
        
        int speakerX = 2;
        int speakerY = height / 4;
        int speakerW = width / 3;
        int speakerH = height / 2;
        
        // Draw speaker body (trapezoid)
        int[] xPoints = {speakerX, speakerX + speakerW/2, speakerX + speakerW/2, speakerX};
        int[] yPoints = {speakerY + speakerH/4, speakerY, speakerY + speakerH, speakerY + 3*speakerH/4};
        g2d.fillPolygon(xPoints, yPoints, 4);
        
        // Draw speaker base (rectangle)
        g2d.fillRect(speakerX - 2, speakerY + speakerH/4, 4, speakerH/2);
        
        // Draw volume waves based on level
        g2d.setStroke(new BasicStroke(2));
        int waveX = speakerX + speakerW/2 + 2;
        int centerY = height / 2;
        
        if (volumeLevel == 0) {
            // Mute - draw X
            g2d.setColor(ThemeColors.TEXT_SECONDARY);
            g2d.drawLine(waveX + 2, centerY - 4, waveX + 8, centerY + 4);
            g2d.drawLine(waveX + 8, centerY - 4, waveX + 2, centerY + 4);
        } else {
            // Draw sound waves
            if (volumeLevel >= 1) {
                // Low - small arc
                g2d.drawArc(waveX, centerY - 3, 6, 6, -45, 90);
            }
            if (volumeLevel >= 2) {
                // Medium - medium arc
                g2d.drawArc(waveX + 2, centerY - 5, 10, 10, -45, 90);
            }
            if (volumeLevel >= 3) {
                // High - large arc
                g2d.drawArc(waveX + 4, centerY - 7, 14, 14, -45, 90);
            }
        }
        
        g2d.dispose();
        return new ImageIcon(image);
    }

    /**
     * Updates the volume button icon
     * @param volumeLevel 0=mute, 1=low, 2=medium, 3=high
     */
    public void updateVolumeButtonIcon(int volumeLevel) {
        ImageIcon icon = createVolumeIcon(volumeLevel, 24, 24);
        jbMute.setIcon(icon);
    }

    /**
     * adds an action listener to all the components
     * @param controller playerController
     */
    public void registerController(PlayerController controller){
        if (controller == null) {
            throw new IllegalArgumentException("Controller cannot be null");
        }
        jbPlayPause.addActionListener(controller);
        jbNext.addActionListener(controller);
        jbStop.addActionListener(controller);
        jbPrevious.addActionListener(controller);
        jbLoop.addActionListener(controller);
        jbMute.addActionListener(controller);
        volumeSlider.addChangeListener(controller);
        this.playerController = controller;
    }

    /**
     * change the button icon from paused to played, and vice versa
     * @param isPlaying true if the song is playing, false otherwise
     */
    public void changePlayPause(boolean isPlaying){
        String iconPath = isPlaying ? ICON_PAUSE : ICON_PLAY;
        ImageIcon icon = loadScaledIcon(iconPath, 30, 30);
        jbPlayPause.setIcon(icon);
    }
    
    /**
     * Toggle the loop button visual state
     * @param isLooping true if looping is enabled, false otherwise
     */
    public void setLoopButtonState(boolean isLooping){
        isLoopActive = isLooping;
        String iconPath = isLooping ? ICON_LOOP_ACTIVE : ICON_LOOP;
        ImageIcon icon = loadScaledIcon(iconPath, 15, 15);
        jbLoop.setIcon(icon);
    }
    
    /**
     * Helper method to load and scale an icon
     * @param iconPath path to the icon
     * @param width desired width
     * @param height desired height
     * @return scaled ImageIcon
     */
    private ImageIcon loadScaledIcon(String iconPath, int width, int height) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
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
        String time;
        if(seconds<10) {
            time =  minutes + ":0" + seconds_rest;
        }else {
            time =  minutes + ":" + seconds_rest;
        }
        totalTime.setText(time);
    }

    /**
     * pops up an error message
     * @param message the error message
     */
    public void showErrorDialog(String message){
        JOptionPane.showMessageDialog(this, message,"Error", JOptionPane.ERROR_MESSAGE);
    }

    public void setSliderMaximum(int maximum){
        jslider.setMaximum(maximum);
    }

    public void moveSliderPosition(int position){
        jslider.setValue(position);
    }

    public void startTimer(int songDuration){
        playerController.startSongTimer(songDuration);
    }

    public void stopTimer(){
        playerController.pauseTimer();
    }

    /**
     * Gets the slider value at a specific X coordinate for seek functionality
     * @param x X coordinate of the click
     * @return corresponding slider value in seconds
     */
    public int getSliderValueAt(int x) {
        int sliderWidth = jslider.getWidth();
        int min = jslider.getMinimum();
        int max = jslider.getMaximum();

        // Account for slider insets
        java.awt.Insets insets = jslider.getInsets();
        int trackWidth = sliderWidth - insets.left - insets.right;

        // Calculate value based on click position
        int value = min + (int) ((double) (x - insets.left) / trackWidth * (max - min));
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Adds a mouse listener to the progress slider for seeking
     * @param listener MouseListener for seek functionality
     */
    public void addSliderMouseListener(java.awt.event.MouseListener listener) {
        jslider.addMouseListener(listener);
    }

    /**
     * change current time of the song in the player view
     * @param secondsSong seconds part duration of the song
     */
    public void updateCurrentTime(int secondsSong) {
        int minutes = secondsSong/60;
        int seconds = secondsSong%60;
        String time;
        if(seconds<10) {
            time =  minutes + ":0" + seconds;
        }else {
            time =  minutes + ":" + seconds;
        }
        currentTime.setText(time);
        this.validate();
        this.repaint();
    }

    /**
     * Gets the current volume slider value
     * @return volume level (0-100)
     */
    public int getVolumeSliderValue() {
        return volumeSlider.getValue();
    }

    /**
     * Sets the volume slider value
     * @param volume volume level (0-100)
     */
    public void setVolumeSliderValue(int volume) {
        volumeSlider.setValue(volume);
    }

    /**
     * Updates the volume icon based on volume level and mute state
     * @param volume volume level (0.0 to 1.0)
     * @param isMuted true if muted
     */
    public void updateVolumeIcon(float volume, boolean isMuted) {
        int volumeLevel;
        if (isMuted || volume <= 0.0f) {
            volumeLevel = 0; // Mute
        } else if (volume <= 0.33f) {
            volumeLevel = 1; // Low
        } else if (volume <= 0.66f) {
            volumeLevel = 2; // Medium
        } else {
            volumeLevel = 3; // High
        }
        updateVolumeButtonIcon(volumeLevel);
    }
}
