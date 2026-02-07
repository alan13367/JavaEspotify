package presentation.views;

import business.utils.MP3DurationUtil;
import business.utils.MP3MetadataUtil;
import presentation.views.GUIassets.MyHintTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * the GUI of the add song view
 * @author Alan Beltrán
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

    private static final Font ARIAL_FONT = new Font("Arial", Font.PLAIN, 25);
    private static final Font ALEGREYA_FONT = new Font("Alegreya Sans SC", Font.BOLD, 30);

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
        titleField.setFont(ARIAL_FONT);
        titleField.setForeground(Color.gray);
        titleField.setBackground(new Color(40, 40, 40));
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
        authorField.setFont(ARIAL_FONT);
        authorField.setForeground(Color.gray);
        authorField.setBackground(new Color(40, 40, 40));
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
        genreField.setFont(ARIAL_FONT);
        genreField.setForeground(Color.gray);
        genreField.setBackground(new Color(40, 40, 40));
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
        albumField.setFont(ARIAL_FONT);
        albumField.setForeground(Color.gray);
        albumField.setBackground(new Color(40, 40, 40));
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
        durationField.setFont(ARIAL_FONT);
        durationField.setForeground(Color.gray);
        durationField.setBackground(new Color(40, 40, 40));
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
        importSongButton.setFocusPainted(false);
        importSongButton.setBorderPainted(false);
        importSongButton.setContentAreaFilled(true);
        importSongButton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        importSongButton.setFont(ALEGREYA_FONT);
        importSongButton.setBackground(new Color(0,204,0));
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
        addButton_bottom.setFocusPainted(false);
        addButton_bottom.setBorderPainted(false);
        addButton_bottom.setContentAreaFilled(true);
        addButton_bottom.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        addButton_bottom.setFont(ALEGREYA_FONT);
        addButton_bottom.setBackground(new Color(0,204,0));
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
            // Auto-import metadata from MP3
            importMetadataFromMP3();
        }
    }

    /**
     * Imports metadata from the selected MP3 file
     */
    private void importMetadataFromMP3() {
        String filepath = getFilePath();
        if (filepath == null || filepath.isEmpty()) {
            return;
        }

        // Extract metadata
        MP3MetadataUtil.MP3Metadata metadata = MP3MetadataUtil.extractMetadata(filepath);

        if (metadata.isValid()) {
            // Auto-fill fields if metadata is available
            if (metadata.getTitle() != null && !metadata.getTitle().isEmpty()) {
                titleField.setText(metadata.getTitle());
                titleField.setForeground(Color.WHITE);
            }

            if (metadata.getArtist() != null && !metadata.getArtist().isEmpty()) {
                authorField.setText(metadata.getArtist());
                authorField.setForeground(Color.WHITE);
            }

            if (metadata.getAlbum() != null && !metadata.getAlbum().isEmpty()) {
                albumField.setText(metadata.getAlbum());
                albumField.setForeground(Color.WHITE);
            }

            if (metadata.getGenre() != null && !metadata.getGenre().isEmpty()) {
                genreField.setText(metadata.getGenre());
                genreField.setForeground(Color.WHITE);
            }

            // Auto-detect duration
            long duration = MP3DurationUtil.getDuration(filepath);
            if (duration > 0) {
                int minutes = (int) (duration / 60000);
                int seconds = (int) ((duration % 60000) / 1000);
                durationField.setText(String.format("%d:%02d", minutes, seconds));
                durationField.setForeground(Color.WHITE);
            }

            pop_up_SuccessDialog("Metadata imported successfully from MP3 file!", "Import Success");
        } else {
            pop_up_ErrorDialog("No metadata found in MP3 file. Please enter manually.", "Import Warning");
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

