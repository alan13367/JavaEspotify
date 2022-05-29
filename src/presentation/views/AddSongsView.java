package presentation.views;

import presentation.views.GUIassets.MyHintTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * the GUI of the add song view
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 19/04/2022
 */
public class AddSongsView extends JPanel {

    public static final String BTN_ADD_SONG = "BTN_ADD_SONG";
    public static final String BTN_IMPORT_SONG = "BTN_IMPORT_SONG";

    private MyHintTextField.RoundedMyHintTextField titleField;
    private MyHintTextField.RoundedMyHintTextField genreField;
    private MyHintTextField.RoundedMyHintTextField albumField;
    private MyHintTextField.RoundedMyHintTextField authorField;
    private MyHintTextField.RoundedMyHintTextField durationField;

    private JButton importSongButton;
    private JButton addButton_bottom;

    private JFileChooser fc;

    Font arialFont = new Font("Arial", Font.PLAIN, 25);
    Font alegreyaFont = new Font("Alegreya Sans SC", Font.BOLD, 30);
    Font smallerArialFont = new Font("Arial", Font.ITALIC, 15);

    /**
     * the view constructor, configures the view
     */
    public AddSongsView() {
        configureView();
    }

    private void configureView(){
        setLayout(new GridBagLayout());

        Color myBlack = new Color(16, 16, 16);
        setBackground(myBlack);

        GridBagConstraints constraints = new GridBagConstraints();



        titleField = new MyHintTextField.RoundedMyHintTextField("Title");
        titleField.setToolTipText("Title");
        titleField.setFont(arialFont);
        titleField.setForeground(Color.gray);
        titleField.setPreferredSize(new Dimension(400,50));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(titleField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        authorField = new MyHintTextField.RoundedMyHintTextField("Author");
        authorField.setToolTipText("Author");
        authorField.setFont(arialFont);
        authorField.setForeground(Color.gray);
        authorField.setPreferredSize(new Dimension(400,50));
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(authorField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        genreField = new MyHintTextField.RoundedMyHintTextField("Genre");
        genreField.setToolTipText("Genre");
        genreField.setFont(arialFont);
        genreField.setForeground(Color.gray);
        genreField.setPreferredSize(new Dimension(400,50));
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(genreField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        albumField = new MyHintTextField.RoundedMyHintTextField("Album");
        albumField.setToolTipText("Album");
        albumField.setFont(arialFont);
        albumField.setForeground(Color.gray);
        albumField.setPreferredSize(new Dimension(400,50));
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(albumField, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        durationField = new MyHintTextField.RoundedMyHintTextField("duration min:secs");
        durationField.setToolTipText("Duration");
        durationField.setFont(arialFont);
        durationField.setForeground(Color.gray);
        durationField.setPreferredSize(new Dimension(400,50));
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

    /**
     * adds an action listener to the import and add button
     * @param controller Action Listener
     */
    public void registerController(ActionListener controller) {
        importSongButton.addActionListener(controller);
        addButton_bottom.addActionListener(controller);
    }

    /**
     * pops up a successful message to the user
     * @param message the text that will appear in the middle of the pop-up dialog
     * @param title the main idea of why the dialog popped up.
     */
    public void pop_up_SuccessDialog(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * pops up an error message to the user
     * @param message the text that will appear in the middle of the pop-up dialog
     * @param title the main idea of why the dialog popped up.
     */
    public void pop_up_ErrorDialog(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * gets the title of the song
     * @return the tilte field
     */
    public String getTitleFieldAdd(){
        return String.valueOf(titleField.getText()).trim();
    }

    /**
     * gets the album of the song
     * @return the album field
     */
    public String getAlbumFieldAdd(){
        return String.valueOf(albumField.getText()).trim();
    }

    /**
     * gets the author of a song
     * @return the author field
     */
    public String getAuthorFieldAdd(){
        return String.valueOf(authorField.getText()).trim();
    }

    /**
     * gets the genre of the song
     * @return the genre field
     */
    public String getGenreFieldAdd(){
        return String.valueOf(genreField.getText()).trim();
    }

    /**
     * the duration of the song
     * @return the duration field
     */
    public String getDurationFieldAdd(){return durationField.getText().trim();}

    /**
     * looks if there is any empty text fields
     * @return true if at least one of the text fields is empty, false otherwise
     */
    public boolean addSongsFieldEmpty(){
        return getTitleFieldAdd().isEmpty() || getGenreFieldAdd().isEmpty() || getAlbumFieldAdd().isEmpty()
                || getAuthorFieldAdd().isEmpty() || durationField.getText().equals("");
    }

    /**
     * gets the file path
     * @return the absolute path of the chosen file
     */
    public String getFilePath(){
        File file = fc.getSelectedFile();
        return file.getAbsolutePath();
    }


    /**
     * shows the file chooser dialog
     */
    public void showFileChooser(){
        fc = new JFileChooser("songs/");
        int returnvalue = fc.showOpenDialog(null);
        if(returnvalue == JFileChooser.APPROVE_OPTION){

        }
    }

    /**
     * filename getter
     * @return the name of the file
     */
    public String getFilename(){
        File file = fc.getSelectedFile();
        return file.getName();
    }

    /**
     * matches the duration the user enterd with the regex we are looking for (min:sec)
     * @return true if it matches, false otherwise
     */
    public boolean checkDurationFormat(){
        return getDurationFieldAdd().matches("[0-5]?\\d:[0-5]\\d$");
    }

    /**
     * clear all the text fields
     */
    public void clearFields() {
        titleField.setText("");
        albumField.setText("");
        authorField.setText("");
        genreField.setText("");
        durationField.setText("");
        fc.cancelSelection();
    }
}

