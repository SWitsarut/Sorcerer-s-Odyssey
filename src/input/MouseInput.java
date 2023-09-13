package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import entity.Player;
import main.GamePanel;

public class MouseInput implements MouseListener, MouseMotionListener {

    GamePanel gp;
    Player player;

    public MouseInput(GamePanel gp) {
        player = gp.getGame().getPlayer();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // System.err.println("x is " + e.getX() + " y is " + e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // click
        switch (e.getButton()) {
            case 1:
                player.attack(e.getX());
                break;
            case 3:
                player.useMagic(e.getX());
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

}
