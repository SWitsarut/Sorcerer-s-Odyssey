package main;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;

    GameWindow(GamePanel gp) {
        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gp);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.pack();

        // keep last
        jframe.setVisible(true);
    }
}
