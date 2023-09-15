package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import static util.Constants.Config.*;

public abstract class Entity {

    protected float x, y;

    protected Rectangle2D.Float hitbox;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    protected void drawHitbox(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int) hitbox.x, (int) hitbox.y, CHAR_SIZE, CHAR_SIZE);
    }

    public  void initHitbox(int x, int y) {
        hitbox = new Rectangle2D.Float(x, y, CHAR_SIZE, CHAR_SIZE);
    }

    // protected void updateHitbox() {
    // hitbox.x = (int) x;
    // hitbox.y = (int) y;
    // }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }
}
