import presentation.controllers.HomeController;
import presentation.views.HomeView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HomeView homeView = new HomeView();
                HomeController controller = new HomeController(homeView);
                homeView.registerController(controller);
                homeView.start();
            }
        });
    }
}
