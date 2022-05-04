package presentation.views;

import presentation.views.GUIassets.MyHintTextField;

import javax.swing.*;
import java.awt.*;

public class AddSongsView extends JPanel {

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

        JTextArea textArea = new JTextArea("Please enter the following information to add a song");
        textArea.setFont(alegreyaFont);
        textArea.setBackground(myBlack);
        textArea.setForeground(Color.white);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 2;
        constraints.weighty = 1.0;
        add (textArea, constraints);
        constraints.weighty = 0.0;

        MyHintTextField userTextField0 = new MyHintTextField.RoundedMyHintTextField("title                                      ");
        userTextField0.setToolTipText("Title");
        userTextField0.setFont(arialFont);
        userTextField0.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(userTextField0, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        MyHintTextField userTextField1 = new MyHintTextField.RoundedMyHintTextField("genre                                  ");
        userTextField1.setToolTipText("Genre");
        userTextField1.setFont(arialFont);
        userTextField1.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(userTextField1, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        MyHintTextField userTextField2 = new MyHintTextField.RoundedMyHintTextField("album                                  ");
        userTextField2.setToolTipText("Album");
        userTextField2.setFont(arialFont);
        userTextField2.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(userTextField2, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        MyHintTextField userTextField3 = new MyHintTextField.RoundedMyHintTextField("author                                 ");
        userTextField3.setToolTipText("Author");
        userTextField3.setFont(arialFont);
        userTextField3.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(userTextField3, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        MyHintTextField userTextField4 = new MyHintTextField.RoundedMyHintTextField("import file...                         ");
        userTextField4.setToolTipText("Import");
        userTextField4.setFont(arialFont);
        userTextField4.setForeground(Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(userTextField4, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        JButton sendButton_bottom=new JButton("    ADD SONG    ");
        sendButton_bottom.setOpaque(true);
        sendButton_bottom.setFocusPainted(true);
        sendButton_bottom.setBorderPainted(true);
        sendButton_bottom.setContentAreaFilled(true);
        sendButton_bottom.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Especially important
        sendButton_bottom.setFont(alegreyaFont);
        sendButton_bottom.setBackground(Color.green);
        sendButton_bottom.setForeground(Color.white);
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(sendButton_bottom, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
    }
}

