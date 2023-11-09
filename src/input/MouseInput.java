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
        switch (Gamestate.state) {
            case MENU:
                gp.getGame().getMenu().mouseDragged(e);
                break;
            case PLAYING:
                gp.getGame().getPlaying().mouseDragged(e);
                break;
            case PAUSE:
                gp.getGame().getPause().mouseDragged(e);
                break;
            case GAMEOVER:
                gp.getGame().getGameOver().mouseDragged(e);
                break;
            case WIN:
                gp.getGame().getWin().mouseDragged(e);
                break;
            default:
                break;
        }
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
            case GAMEOVER:
                gp.getGame().getGameOver().mouseMoved(e);
                break;
            case WIN:
                gp.getGame().getWin().mouseMoved(e);
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
            case GAMEOVER:
                gp.getGame().getGameOver().mouseClicked(e);
                break;
            case WIN:
                gp.getGame().getWin().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gp.getGame().getMenu().mousePressed(e);
                break;
            case PLAYING:
                gp.getGame().getPlaying().mousePressed(e);
                break;
            case PAUSE:
                gp.getGame().getPause().mousePressed(e);
                break;
            case GAMEOVER:
                gp.getGame().getGameOver().mousePressed(e);
                break;
            case WIN:
                gp.getGame().getWin().mousePressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gp.getGame().getMenu().mouseReleased(e);
                break;
            case PLAYING:
                gp.getGame().getPlaying().mouseReleased(e);
                break;
            case PAUSE:
                gp.getGame().getPause().mouseReleased(e);
                break;
            case GAMEOVER:
                gp.getGame().getGameOver().mouseReleased(e);
                break;
            case WIN:
                gp.getGame().getWin().mouseReleased(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
