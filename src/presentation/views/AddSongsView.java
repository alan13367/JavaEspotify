package presentation.views;

import business.entities.Song;
import presentation.views.GUIassets.MyHintTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddSongsView extends JPanel {

    public static final String BTN_ADD_SONG = "BTN_ADD_SONG";
    public static final String BTN_IMPORT_SONG = "BTN_IMPORT_SONG";

    Song song;

    private MyHintTextField.RoundedMyHintTextField titleField;
    private MyHintTextField.RoundedMyHintTextField genreField;
    private MyHintTextField.RoundedMyHintTextField albumField;
    private MyHintTextField.RoundedMyHintTextField authorField;
    private MyHintTextField.RoundedMyHintTextField durationField;

    private JButton importSongButton;
    private JButton addButton_bottom;

    Font arialFont = new Font("Arial", Font.PLAIN, 25);
    Font alegreyaFont = new Font("Alegreya Sans SC", Font.BOLD, 30);
    Font smallerArialFont = new Font("Arial", Font.ITALIC, 15);

    public AddSongsView() {
        configureView();
    }

    private void configureView(){
        setLayout(new GridBagLayout());

        Color myBlack = new Color(18, 18, 18);
        setBackground(myBlack);

        GridBagConstraints constraints = new GridBagConstraints();



        titleField = new MyHintTextField.RoundedMyHintTextField("title                                      ");
        titleField.setToolTipText("Title");
        titleField.setFont(arialFont);
        titleField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(titleField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        authorField = new MyHintTextField.RoundedMyHintTextField("author                                 ");
        authorField.setToolTipText("Author");
        authorField.setFont(arialFont);
        authorField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(authorField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        genreField = new MyHintTextField.RoundedMyHintTextField("genre                                  ");
        genreField.setToolTipText("Genre");
        genreField.setFont(arialFont);
        genreField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(genreField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        albumField = new MyHintTextField.RoundedMyHintTextField("album                                  ");
        albumField.setToolTipText("Album");
        albumField.setFont(arialFont);
        albumField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(albumField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        durationField = new MyHintTextField.RoundedMyHintTextField("duration                              ");
        durationField.setToolTipText("Duration");
        durationField.setFont(arialFont);
        durationField.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(durationField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        importSongButton = new JButton("import file...");
        importSongButton.setOpaque(true);
        importSongButton.setActionCommand(BTN_IMPORT_SONG);
        importSongButton.setFocusPainted(true);
        importSongButton.setBorderPainted(true);
        importSongButton.setContentAreaFilled(true);
        importSongButton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Especially important
        importSongButton.setFont(alegreyaFont);
        importSongButton.setBackground(Color.green);
        importSongButton.setForeground(Color.white);
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(importSongButton, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        addButton_bottom=new JButton("    ADD SONG    ");
        addButton_bottom.setActionCommand(BTN_ADD_SONG);
        addButton_bottom.setOpaque(true);
        addButton_bottom.setFocusPainted(true);
        addButton_bottom.setBorderPainted(true);
        addButton_bottom.setContentAreaFilled(true);
        addButton_bottom.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Especially important
        addButton_bottom.setFont(alegreyaFont);
        addButton_bottom.setBackground(Color.green);
        addButton_bottom.setForeground(Color.white);
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(addButton_bottom, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
    }

    public void registerController(ActionListener controller) {
        importSongButton.addActionListener(controller);
        addButton_bottom.addActionListener(controller);
    }

    public void pop_up_SuccessDialog(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void pop_up_ErrorDialog(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public String getTitleFieldAdd(){
        return String.valueOf(titleField.getText());
    }

    public String getAlbumFieldAdd(){
        return String.valueOf(albumField.getText());
    }

    public String getAuthorFieldAdd(){
        return String.valueOf(authorField.getText());
    }

    public String getGenreFieldAdd(){
        return String.valueOf(genreField.getText());
    }

    public long getDurationFieldAdd(){return Long.valueOf(durationField.getText());}

    public boolean addSongsFieldEmpty(){
        return getTitleFieldAdd().isEmpty() || getGenreFieldAdd().isEmpty() || getAlbumFieldAdd().isEmpty()
                || getAuthorFieldAdd().isEmpty() || durationField.getText().equals("");
    }

    public void clearFields() {
        titleField.setText("");
        albumField.setText("");
        authorField.setText("");
        genreField.setText("");
        durationField.setText("");
    }
}

