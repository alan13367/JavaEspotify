package presentation.controllers;

import business.BusinessFacade;
import business.entities.Playlist;
import presentation.views.PlaylistsView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlaylistsController implements ActionListener, MouseListener {
    private final PlaylistsView playlistsView;
    private final BusinessFacade businessFacade;

    public PlaylistsController(PlaylistsView playlistsView, BusinessFacade businessFacade) {
        this.playlistsView = playlistsView;
        this.businessFacade = businessFacade;
    }

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
                playlistsView.showPlaylistsPanelCard();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof PlaylistsView.PlaylistItemHolder){
            PlaylistsView.PlaylistItemHolder playlistItemHolder = (PlaylistsView.PlaylistItemHolder) e.getSource();
            String owner = playlistItemHolder.getPlaylistOwner();
            playlistsView.showPlaylistInfoCard(playlistItemHolder.getPlaylistName(),owner
                    ,businessFacade.getCurrentUser().equals(owner));
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
