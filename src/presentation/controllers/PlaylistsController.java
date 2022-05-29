package presentation.controllers;

import business.BusinessFacade;
import business.entities.Playlist;
import business.entities.Song;
import presentation.views.HomeView;
import presentation.views.PlayerView;
import presentation.views.PlaylistsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * PlaylistsController class manages the behaviour of the {@link PlaylistsView} by implementing the {@link  ActionListener}
 * interface and the {@link MouseListener} interface.
 *
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 25/4/2022
 */
public class PlaylistsController implements ActionListener, MouseListener {
    private final PlaylistsView playlistsView;
    private final PlayerView playerView;
    private final BusinessFacade businessFacade;


    /**
     * Default PlaylistsController Constructor that will link the views needed with {@link HomeView} and the business
     * logic with the {@link BusinessFacade} interface.
     * @param homeView HomeView reference
     * @param businessFacade link to the logic of the program
     */
    public PlaylistsController(HomeView homeView, BusinessFacade businessFacade) {
        this.playlistsView = homeView.getPlaylistsView();
        this.businessFacade = businessFacade;
        this.playerView = homeView.getPlayerView();
    }

    /**
     * Method that will load the playlists to the view
     * @param username username logged in the system
     */
    public void loadPlaylists(String username){
        List<Playlist> playlists = businessFacade.getPlaylists();
        Collections.sort(playlists);
        for (Playlist playlist:playlists ){
            if(playlist.getOwner().equals(username)){
                playlistsView.addMyPlaylists(playlist.getName(),username);
            }
            playlistsView.addAllPlaylists(playlist.getName(),playlist.getOwner());
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case(PlaylistsView.BTN_CREATE_PLAYLIST)->{
                String name = playlistsView.createPlaylistDialog();
                if (name != null && name.length() > 0){
                    List<String> names = Arrays.stream(businessFacade.getUserPlaylistsNames()).toList();
                    if(names.contains(name)){
                        playlistsView.showErrorDialog("You already have a Playlist with that name.");
                    }else {
                        String owner = businessFacade.getCurrentUser();
                        businessFacade.createPlaylist(name);
                        playlistsView.addMyPlaylists(name,owner);
                        playlistsView.addAllPlaylists(name,owner);
                    }
                }
            }

            case (PlaylistsView.BTN_MY_PLAYLISTS)->{
                playlistsView.showMyPlaylistsCard();
            }

            case (PlaylistsView.BTN_ALL_PLAYLISTS)->{
                playlistsView.showAllPlaylistsCard();
            }

            case (PlaylistsView.BTN_CLOSE)->{
                playlistsView.clearSongsPanel();
                playlistsView.showPlaylistsPanelCard();
            }

            case (PlaylistsView.BTN_DELETE_PLAYLIST)->{
                int option = playlistsView.showPlaylistOptionDialog("Are you sure you want to delete this playlist?"
                        ,"Deleting Playlist");
                if(option == JOptionPane.YES_OPTION){
                    businessFacade.deletePlaylist(playlistsView.getPlaylistName());
                    playlistsView.clearPlaylistsPanel();
                    loadPlaylists(businessFacade.getCurrentUser());
                    playlistsView.clearSongsPanel();
                    playlistsView.showPlaylistsPanelCard();
                }
            }
            case (PlaylistsView.BTN_PLAY_PLAYLIST)->{

                // get playlist and add it to queue
                LinkedList<Song> playlist = new LinkedList<>();
                for (PlaylistsView.SongItemHolder s: playlistsView.getSongsHolder()){
                    playlist.add(businessFacade.getSong(s.getSongName(),s.getAuthor()));
                }
                if(!playlist.isEmpty()){
                    if(businessFacade.isPlaying()){
                        businessFacade.stopPlayer();
                        playerView.stopTimer();
                        playerView.moveSliderPosition(0);
                    }
                    try {
                        String title = playlist.get(0).getTitle();
                        String author = playlist.get(0).getAuthor();
                        int songTotalSeconds = playlist.get(0).getSongSeconds();
                        businessFacade.addPlaylistToQueue(new LinkedList<>(playlist));
                        businessFacade.playSong(title,author);
                        playerView.changeShownSong(title,author);
                        playerView.changeTotalTime(playlist.get(0).getSongMinutes(),songTotalSeconds );
                        playerView.startTimer(songTotalSeconds);
                        playerView.changePlayPause(businessFacade.isPlaying());
                        businessFacade.setPlayingPlaylist(true);
                    } catch (FileNotFoundException ex) {
                        playlistsView.showErrorDialog("File of the song was not found");
                    }
                }


            }

            case(PlaylistsView.BTN_MOVE_SONG_UP)->{
                PlaylistsView.SongItemHolder songItemHolder = ((PlaylistsView.SongItemHolder) ((JButton)e.getSource()).getParent().getParent().getParent());
                int position = songItemHolder.getPosition()-1;
                if(position > 0)
                    playlistsView.moveSongInPlaylist(position,PlaylistsView.MOVEUP);
            }
            case(PlaylistsView.BTN_MOVE_SONG_DOWN)->{
                PlaylistsView.SongItemHolder songItemHolder = ((PlaylistsView.SongItemHolder) ((JButton)e.getSource()).getParent().getParent().getParent());
                int position = songItemHolder.getPosition()-1;
                if(position < playlistsView.getSongsListSize()-1)
                    playlistsView.moveSongInPlaylist(position,PlaylistsView.MOVEDOWN);
            }

            case (PlaylistsView.BTN_DELETE_SONG_FROM_PLAYLIST)->{
                PlaylistsView.SongItemHolder songItemHolder = ((PlaylistsView.SongItemHolder) ((JButton)e.getSource()).getParent().getParent());

                int response = playlistsView.showPlaylistOptionDialog("Are you sure you want to delete this song from the playlist?"
                        ,"Deleting Song From Playlist");
                if(response==JOptionPane.YES_OPTION){
                    businessFacade.deleteSongFromPlaylist(playlistsView.getPlaylistName()
                            , songItemHolder.getSongName(), songItemHolder.getAuthor());
                    playlistsView.deleteSongFromPlaylist(songItemHolder.getPosition()-1);
                }
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof PlaylistsView.PlaylistItemHolder){
            PlaylistsView.PlaylistItemHolder playlistItemHolder = (PlaylistsView.PlaylistItemHolder) e.getSource();
            String owner = playlistItemHolder.getPlaylistOwner();
            boolean isOwner = businessFacade.getCurrentUser().equals(owner);
            List<Song> playlistSongs =  businessFacade.getSongsFromPlaylist(playlistItemHolder.getPlaylistName(), owner);
            for(int i = 0;i<playlistSongs.size();i++){
                playlistsView.addSongToPanel(playlistSongs.get(i).getTitle(),playlistSongs.get(i).getAuthor(),i+1,isOwner);
            }
            playlistsView.showPlaylistInfoCard(playlistItemHolder.getPlaylistName(),owner,isOwner);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof PlaylistsView.PlaylistItemHolder){
            PlaylistsView.PlaylistItemHolder playlistItemHolder = (PlaylistsView.PlaylistItemHolder) e.getSource();
            playlistItemHolder.setBackground(new Color(80,80,80));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof PlaylistsView.PlaylistItemHolder){
            PlaylistsView.PlaylistItemHolder playlistItemHolder = (PlaylistsView.PlaylistItemHolder) e.getSource();
            playlistItemHolder.setBackground(new Color(16,16,16));
        }
    }
}
