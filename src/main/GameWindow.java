package main;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;

    GameWindow(GamePanel gp) {
        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gp);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.pack();
        
        // keep last
        jframe.setVisible(true);
    }
}
