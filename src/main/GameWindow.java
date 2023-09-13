package main;

import javax.swing.JFrame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {
    private JFrame jframe;

    GameWindow(GamePanel gp) {
        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gp);
        jframe.setResizable(false);
        // jframe.setLocationRelativeTo(null);
        jframe.setLocation(0, 0);
        jframe.pack();
        jframe.setTitle("Travellering sorcerer");
        // keep last
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {
                gp.getGame().windowFocusLost();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gp.getGame().windowFocusLost();
            }
        });
    }
}
