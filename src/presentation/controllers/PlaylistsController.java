package presentation.controllers;

import business.BusinessFacade;
import business.entities.Playlist;
import presentation.views.PlaylistsView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlaylistsController implements ActionListener, MouseListener {
    private final PlaylistsView playlistsView;
    private final BusinessFacade businessFacade;

    public PlaylistsController(PlaylistsView playlistsView, BusinessFacade businessFacade) {
        this.playlistsView = playlistsView;
        this.businessFacade = businessFacade;
    }

    public void loadPlaylists(String username){
        for(String userPlaylistName:businessFacade.getUserPlaylistsNames()){
            playlistsView.addMyPlaylists(userPlaylistName,username);
        }
        for (Playlist playlist: businessFacade.getPlaylists()){
            playlistsView.addAllPlaylists(playlist.getName(),playlist.getOwner());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case(PlaylistsView.BTN_CREATE_PLAYLIST)->{
                String name = playlistsView.createPlaylistDialog();
                if (name != null && name.length() > 0){
                    playlistsView.addMyPlaylists(name,businessFacade.getCurrentUser());
                    //businessFacade.createPlaylist(name);
                }
            }
            case (PlaylistsView.BTN_MY_PLAYLISTS)->{
                playlistsView.showMyPlaylistsCard();
            }
            case (PlaylistsView.BTN_ALL_PLAYLISTS)->{
                playlistsView.showAllPlaylistsCard();
            }

            case (PlaylistsView.BTN_CLOSE)->{
                playlistsView.showPlaylistsPanelCard();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof PlaylistsView.PlaylistItemHolder){
            PlaylistsView.PlaylistItemHolder playlistItemHolder = (PlaylistsView.PlaylistItemHolder) e.getSource();
            System.out.println(playlistItemHolder.getPlaylistName()+" "+playlistItemHolder.getPlaylistOwner());
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
