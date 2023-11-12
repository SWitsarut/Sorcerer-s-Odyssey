package interactable;

import static util.Constants.Config.SCREEN_WIDTH;
import static util.Constants.Config.TILE_SIZE;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import Levels.LevelManager;
import gamestates.Playing;
import helperClass.Coordinate;
import util.LoadSave;
import util.Ui;
import util.Constants.Config;

public class Portal extends Interactable {

    public int mapIndex;
    //    public String mapName;
    public int targetMap;
    protected Font font;
    protected LevelManager levelManager;
    protected Playing playing;
    protected Coordinate targetCoor;

    public Portal(Playing playing, int targetMap, int mapIndex, String massage) {
        super(massage);
        this.mapIndex = mapIndex;
        this.targetMap = targetMap;
        font = LoadSave.GetFont(42);
        this.playing = playing;
        this.levelManager = playing.getLevelManager();
        targetCoor = new Coordinate(0, 0);
    }

    public void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width * TILE_SIZE, height * TILE_SIZE);
    }

    public void setDes(Coordinate coor) {
        targetCoor = coor;
    }

    @Override
    public void onSubmit() {
        playing.goTomap(targetMap);
        System.out.println(LevelManager.curMapIndex);
        playing.getPlayer().setPosition(targetCoor);
        playing.getPlayer().interacting = false;
        playing.getPlayer().interactBlock = true;
    }


    @Override
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.setColor(new Color(122, 9, 250, 30));
        g.fillRect((int) (hitbox.x - xLvlOffset), (int) (hitbox.y - yLvlOffset), (int) (hitbox.width),
                (int) (hitbox.height));

        if (showMessage) {
            g.setFont(font);
            Coordinate coor = Ui.GetTextMiddleScreen(g, massage);
            Graphics2D g2d = (Graphics2D) g;

            int yStart = Ui.GetPercentY(60);
            Point start = new Point(Config.SCREEN_WIDTH / 2, yStart);
            Point end = new Point(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT);

            GradientPaint gradientPaint = new GradientPaint(start, new Color(0, 0, 0, 0), end, new Color(0, 10, 40));
            g2d.setPaint(gradientPaint);
            g2d.fillRect(0, start.y, SCREEN_WIDTH, Config.SCREEN_HEIGHT - start.y);

            g.setColor(Color.white);
            g.drawString(massage, coor.x, Ui.GetPercentY(80));
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
