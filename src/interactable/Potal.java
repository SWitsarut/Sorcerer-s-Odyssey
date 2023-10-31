package interactable;

import static util.Constants.Config.SCREEN_WIDTH;
import static util.Constants.Config.TILE_SIZE;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import Levels.LevelManager;
import main.Game;
import util.Ui;
import util.Constants.Config;

public class Potal extends Interactable {
    private Game game;

    public Potal(LevelManager levelManager, String mapName) {
        super("go to " + mapName);
    }

    public void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width * TILE_SIZE, height * TILE_SIZE);
    }

    @Override
    public void onSubmit() {
        LevelManager.curMapIndex = game.getPlaying().getLevelManager().getIndexFromMapName(massage);
    }

    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.setColor(Color.red);
        g.fillRect((int) (hitbox.x - xLvlOffset), (int) (hitbox.y - yLvlOffset), (int) (hitbox.width),
                (int) (hitbox.height));
        if (showMessage) {
            Graphics2D g2d = (Graphics2D) g;

            int yStart = Ui.GetPercentY(60);
            Point start = new Point(Config.SCREEN_WIDTH / 2, yStart);
            Point end = new Point(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT);
            GradientPaint gradientPaint = new GradientPaint(start, new Color(0, 0, 0, 0), end, new Color(0, 10, 40));
            g2d.setPaint(gradientPaint);
            g2d.fillRect(0, start.y, SCREEN_WIDTH, Config.SCREEN_HEIGHT - start.y);
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void onIntersects() {
        showMessage = true;
    }

    @Override
    public void notIntersects() {
        showMessage = false;
    }

}
