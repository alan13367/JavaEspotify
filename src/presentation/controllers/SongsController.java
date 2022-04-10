package presentation.controllers;

import presentation.views.SongsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SongsController implements ActionListener {

    private SongsView view;

    public SongsController(SongsView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case (SongsView.BTN_SEARCH) -> {
                System.out.println("SEARCH");
            }

        }
    }
}
