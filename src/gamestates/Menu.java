package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;
import util.Constants.Config;

public class Menu extends State implements Statemethods {

    public Menu(Game game) {
        super(game);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        String continuieString = "Press any key to start";
        g.drawString(continuieString, Config.SCREEN_WIDTH / 2 - continuieString.length(),
                Config.SCREEN_HEIGHT / 2 - continuieString.length());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Gamestate.state = Gamestate.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
