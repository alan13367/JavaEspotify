package presentation.controllers;

import business.BusinessFacade;
import presentation.views.PlaylistsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlaylistsController implements ActionListener, MouseListener {
    private PlaylistsView playlistsView;
    private BusinessFacade businessFacade;

    public PlaylistsController(PlaylistsView playlistsView, BusinessFacade businessFacade) {
        this.playlistsView = playlistsView;
        this.businessFacade = businessFacade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case(PlaylistsView.BTN_CREATE_PLAYLIST)->{
                String name = playlistsView.createPlaylistDialog();
                if (name != null && name.length() > 0){
                    playlistsView.addPlaylist(name);
                    businessFacade.createPlaylist(name);
                }

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel jLabel = (JLabel) e.getSource();
        System.out.println(jLabel.getText());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
