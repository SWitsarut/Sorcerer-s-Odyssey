package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Player;
import main.GamePanel;

import static util.Constants.PlayerConstants.*;
import static util.Constants.Direction.*;

public class KeyboardInput implements KeyListener {
    Player player;

    public KeyboardInput(GamePanel gp) {
        this.player = gp.getGame().getPlayer();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: // W
                player.setUp(true);
                break;
            case KeyEvent.VK_D:// D
                player.setRight(true);
                break;
            case KeyEvent.VK_A:// A
                player.setLeft(true);
                break;
            case KeyEvent.VK_S:// S
                player.setDown(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: // W
                player.setUp(false);
                break;
            case KeyEvent.VK_D:// D
                player.setRight(false);
                break;
            case KeyEvent.VK_A:// A
                player.setLeft(false);
                break;
            case KeyEvent.VK_S:// S
                player.setDown(false);
                break;
        }
    }

}
