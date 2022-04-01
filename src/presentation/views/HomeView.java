package presentation.views;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JFrame {

    public HomeView(){
        setLayout(new BorderLayout());
        configureView();
        configureWindow();
    }

    private void configureWindow(){
        setTitle("Home");
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void configureView(){

    }

    public  void start(){
        setVisible(true);
    }
}
