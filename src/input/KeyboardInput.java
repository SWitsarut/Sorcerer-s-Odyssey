package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class KeyboardInput implements KeyListener {
    boolean up, down, left, right;
    GamePanel gp;

    public KeyboardInput(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: // W
                up = true;
                break;
            case KeyEvent.VK_D:// D
                right = true;
                break;
            case KeyEvent.VK_A:// A
                left = true;
                break;
            case KeyEvent.VK_S:// S
                down = true;
                break;
        }
        if (up && right) {
        }
        if (up && left) {
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: // W
                up = false;
                break;
            case KeyEvent.VK_D:// D
                right = false;
                break;
            case KeyEvent.VK_A:// A
                left = false;
                break;
            case KeyEvent.VK_S:// S
                down = false;
                break;
        }
    }

}
