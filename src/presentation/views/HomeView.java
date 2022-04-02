package presentation.views;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JFrame {

    private final CardLayout cardManager;


    private JPanel mainPanel;

    private JPanel sidePanel;

    private JPanel playerPanel;


    public HomeView(){
        setLayout(new BorderLayout());
        cardManager = new CardLayout();

        configureView();
        configureWindow();
    }

    private void configureWindow(){
        setTitle("Home");
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void configureView(){
        sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(280,720));
        sidePanel.setBackground(Color.black);
        add(sidePanel,BorderLayout.WEST);

        mainPanel = new JPanel(cardManager);
        mainPanel.setBackground(Color.gray);
        add(mainPanel,BorderLayout.CENTER);

        playerPanel = new JPanel(new FlowLayout());
        playerPanel.setBackground(Color.blue);
        playerPanel.setPreferredSize(new Dimension(1280,80));
        add(playerPanel,BorderLayout.SOUTH);

    }

    public  void start(){
        setVisible(true);
    }
}
