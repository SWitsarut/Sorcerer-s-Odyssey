package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.GamePanel;

public class KeyboardInput implements KeyListener {

    private GamePanel gp;


    public KeyboardInput(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gp.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gp.getGame().getPlaying().keyPressed(e);
                break;
            case PAUSE:
                gp.getGame().getPause().keyPressed(e);
                break;
            case GAMEOVER:
                gp.getGame().getGameOver().keyPressed(e);
                break;
            case WIN:
                gp.getGame().getWin().keyPressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gp.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                gp.getGame().getPlaying().keyReleased(e);
                break;
            case PAUSE:
                gp.getGame().getPause().keyReleased(e);
                break;
            case GAMEOVER:
                gp.getGame().getGameOver().keyReleased(e);
                break;
            case WIN:
                gp.getGame().getWin().keyReleased(e);
                break;
            default:
                break;
        }
    }

}
