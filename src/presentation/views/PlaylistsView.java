package presentation.views;

import javax.swing.*;
import java.awt.*;

public class PlaylistsView extends JPanel{

    private CardLayout cardManager;

    // general panel
    private JPanel jpPlaylists;
    private JTable playlistsTable;
    private static final String[] columns ={"NAME","OWNER"};
    private static final String PLAYLISTSTABLE_CARD = "PLAYLISTSTABLE_CARD";

    public PlaylistsView(){
        cardManager = new CardLayout();
        setLayout(cardManager);
        configurePlaylistPanel();
    }

    private void configurePlaylistPanel(){
        jpPlaylists = new JPanel(new BorderLayout());
        jpPlaylists.setBackground(new Color(16,16,16));
        add(jpPlaylists,PLAYLISTSTABLE_CARD);

    }

    public void showPlaylistTableCard(){
    cardManager.show(this,PLAYLISTSTABLE_CARD);
    }

}
