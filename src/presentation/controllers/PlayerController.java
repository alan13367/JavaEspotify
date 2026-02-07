package presentation.controllers;

import business.BusinessFacade;
import business.audio.VolumeChangeListener;
import business.entities.Song;
import presentation.views.HomeView;
import presentation.views.PlayerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * PlayerController class manages the behaviour of the {@link PlayerView} by implementing the {@link  ActionListener}
 * interface.
 *
 * @author Alan Beltrán
 * @version 1.0
 * @since 19/4/2022
 */
public class PlayerController implements ActionListener, ChangeListener, VolumeChangeListener {
    private final BusinessFacade businessFacade;
    private final PlayerView view;
    private Timer timer;
    private int secondsSong;
    private long songStartTime;
    private boolean isAdjustingVolume = false;

    /**
     * Default PlayerController Constructor that will link the views needed with {@link HomeView} and the business
     * logic with the {@link BusinessFacade} interface.
     * @param homeView HomeView reference
     * @param businessFacade link to the logic of the program
     */
    public PlayerController(HomeView homeView, BusinessFacade businessFacade) {
        this.businessFacade = businessFacade;
        this.view = homeView.getPlayerView();
        setupSliderSeekListener();
        setupVolumeListener();
        // Register as volume change listener to sync UI
        businessFacade.addVolumeChangeListener(this);
        // Initialize volume UI
        float currentVolume = businessFacade.getVolume();
        view.setVolumeSliderValue((int) (currentVolume * 100));
        view.updateVolumeIcon(currentVolume, businessFacade.isMuted());
    }

    private void setupVolumeListener() {
        // Volume slider listener is now handled by stateChanged method
    }

    private void setupSliderSeekListener() {
        view.addSliderMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (businessFacade.getCurrentSong() != null) {
                    int seekPosition = view.getSliderValueAt(e.getX());
                    try {
                        // Update timer to match new position
                        secondsSong = seekPosition;
                        songStartTime = System.currentTimeMillis() - (secondsSong * 1000L);

                        // Perform seek
                        businessFacade.seekTo(seekPosition);
                        view.updateCurrentTime(seekPosition);
                        view.moveSliderPosition(seekPosition);
                    } catch (FileNotFoundException ex) {
                        view.showErrorDialog("File of the song was not found.");
                    }
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case(PlayerView.BTN_PLAYPAUSE)->{
                if(businessFacade.getCurrentSong() != null){
                    if(!businessFacade.isPlaying()){
                        try {
                            businessFacade.resumeSong();
                        } catch (FileNotFoundException ex) {
                            view.showErrorDialog("File of the song was not found.");
                        }
                        view.changePlayPause(businessFacade.isPlaying());
                        resumeTimer();
                    }else {
                        businessFacade.pausePlayer();
                        view.changePlayPause(businessFacade.isPlaying());
                        pauseTimer();
                    }
                }
            }

            case (PlayerView.BTN_NEXT)->{
                if(businessFacade.isPlayingPlaylist()){
                    businessFacade.stopPlayer();
                    if(timer != null) {
                        timer.stop();
                    }
                    try {
                        businessFacade.playNextSong();
                    } catch (FileNotFoundException ex) {
                        view.showErrorDialog("File of the song was not found.");
                    }
                    if(!businessFacade.queueIsEmpty()){
                        Song song = businessFacade.getCurrentSong();
                        updateViewForNewSong(song);
                    }else {
                        resetPlayerView();
                    }
                }
            }

            case (PlayerView.BTN_PREV)->{
                if(businessFacade.isPlayingPlaylist() && !businessFacade.playedSongsIsEmpty()){
                    businessFacade.stopPlayer();
                    if(timer != null) {
                        timer.stop();
                    }
                    try {
                        businessFacade.playPrevSong();
                    } catch (FileNotFoundException ex) {
                        view.showErrorDialog("File of the song was not found.");
                    }
                    Song song = businessFacade.getCurrentSong();
                    updateViewForNewSong(song);
                }
            }

            case (PlayerView.BTN_STOP)->{
                if(timer != null){
                    if(businessFacade.isPlaying()){
                        businessFacade.stopPlayer();
                    }else {
                        businessFacade.clearCurrentSong();
                    }
                    timer.stop();
                    view.updateCurrentTime(0);
                    view.changeTotalTime(0,0);
                    view.changePlayPause(businessFacade.isPlaying());
                    view.changeShownSong("","");
                    view.moveSliderPosition(0);
                }
            }

            case (PlayerView.BTN_LOOP)->{
                boolean isNowLooping;
                if(businessFacade.isPlayingPlaylist()){
                    isNowLooping = !businessFacade.isLoopingPlaylist();
                    businessFacade.setLoopingPlaylist(isNowLooping);
                } else if (businessFacade.getCurrentSong() != null) {
                    isNowLooping = !businessFacade.isLoopingSong();
                    businessFacade.setLoopingSong(isNowLooping);
                } else {
                    isNowLooping = false;
                }
                view.setLoopButtonState(isNowLooping);
            }

            case (PlayerView.BTN_MUTE)->{
                if (businessFacade.isMuted()) {
                    businessFacade.unmute();
                } else {
                    businessFacade.mute();
                }
                // Icon will be updated via VolumeChangeListener
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // Handle volume slider changes
        if (!isAdjustingVolume) {
            int sliderValue = view.getVolumeSliderValue();
            float volume = sliderValue / 100.0f;
            businessFacade.setVolume(volume);
        }
    }

    @Override
    public void onVolumeChanged(float newVolume) {
        // Update UI when volume changes programmatically
        isAdjustingVolume = true;
        view.setVolumeSliderValue((int) (newVolume * 100));
        view.updateVolumeIcon(newVolume, businessFacade.isMuted());
        isAdjustingVolume = false;
    }

    @Override
    public void onMuteStateChanged(boolean isMuted) {
        // Update icon when mute state changes
        view.updateVolumeIcon(businessFacade.getVolume(), isMuted);
    }

    /**
     * Method that will start the timer of the song and move the slider
     * @param songDuration total duration of the song
     */
    public void startSongTimer(int songDuration){
        if(timer != null){
            timer.stop();
        }
        view.setSliderMaximum(songDuration);
        secondsSong = 0;
        songStartTime = System.currentTimeMillis();
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedMillis = System.currentTimeMillis() - songStartTime;
                secondsSong = (int) (elapsedMillis / 1000);
                view.moveSliderPosition(secondsSong);
                view.updateCurrentTime(secondsSong);
                if(secondsSong >= songDuration){
                    handleSongEnd();
                }
            }
        });
        timer.start();
    }
    
    /**
     * Handles the end of a song - either loops, stops, or plays next song
     */
    private void handleSongEnd() {
        Song song = businessFacade.getCurrentSong();
        businessFacade.stopPlayer();
        timer.stop();
        view.moveSliderPosition(0);
        
        if(businessFacade.isLoopingSong()){
            try {
                businessFacade.playSong(song.getTitle(),song.getAuthor());
            } catch (FileNotFoundException ex) {
                view.showErrorDialog("File of the song was not found.");
            }
            view.startTimer(song.getSongSeconds());
        } else if(businessFacade.isPlayingPlaylist()){
            try {
                businessFacade.playNextSong();
            } catch (FileNotFoundException ex) {
                view.showErrorDialog("File of the song was not found.");
            }
            if(!businessFacade.queueIsEmpty()){
                Song nextSong = businessFacade.getCurrentSong();
                updateViewForNewSong(nextSong);
            }
            view.changePlayPause(businessFacade.isPlaying());
        } else {
            view.changePlayPause(businessFacade.isPlaying());
            view.changeShownSong("","");
        }
    }
    
    /**
     * Updates the view when transitioning to a new song
     * @param song the new song to display
     */
    private void updateViewForNewSong(Song song) {
        view.changeShownSong(song.getTitle(),song.getAuthor());
        view.updateCurrentTime(0);
        view.changeTotalTime(song.getSongMinutes(),song.getSongSeconds());
        view.startTimer(song.getSongSeconds());
    }

    /**
     * Resets the player view to its initial state
     */
    private void resetPlayerView() {
        view.changePlayPause(businessFacade.isPlaying());
        view.changeShownSong("","");
        view.updateCurrentTime(0);
        view.changeTotalTime(0,0);
        view.moveSliderPosition(0);
    }

    /**
     * Method that will resume the timer
     */
    public void resumeTimer(){
        if(timer != null){
            songStartTime = System.currentTimeMillis() - (secondsSong * 1000L);
            timer.start();
        }
    }

    /**
     * Method that will pause the timer
     */
    public void pauseTimer(){
        if(timer != null){
            timer.stop();
        }
    }
}
