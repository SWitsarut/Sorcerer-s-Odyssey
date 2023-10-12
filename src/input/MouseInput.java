package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestates.Gamestate;
import main.GamePanel;

public class MouseInput implements MouseListener, MouseMotionListener {
    private GamePanel gp;

    public MouseInput(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gp.getGame().getMenu().mouseMoved(e);
                break;
            case PLAYING:
                gp.getGame().getPlaying().mouseMoved(e);
                break;
            case PAUSE:
                gp.getGame().getPause().mouseMoved(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gp.getGame().getMenu().mouseClicked(e);
                break;
            case PLAYING:
                gp.getGame().getPlaying().mouseClicked(e);
                break;
            case PAUSE:
                gp.getGame().getPause().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
