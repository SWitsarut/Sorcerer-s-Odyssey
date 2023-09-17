package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import helperClass.Coordinate;
import main.Game;
import util.LoadSave;
import util.Constants.Config;
import util.Ui;

public class Menu extends State implements Statemethods {
    Font font;

    public Menu(Game game) {
        super(game);
        font = LoadSave.GetFont(20);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
         
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        Rectangle rec = Ui.GetRectangleMiddleScreen(300, 400);
        g.setColor(Color.BLUE);
        g.fillRect(rec.x, rec.y, rec.width, rec.height);

        g.setFont(font);
        g.setColor(new Color(200, 0, 0));
        String continuieString = "PRESS ANY BUTTON";
        Coordinate middleCoordinate = Ui.GetTextMiddleScreen(g, continuieString);
        g.drawString(continuieString, middleCoordinate.x, middleCoordinate.y);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Gamestate.state = Gamestate.PLAYING;
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
