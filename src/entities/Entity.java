package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float width, height;

    protected Rectangle2D.Float hitbox;

    public Entity(float x, float y, float width, float height) {
        this.width = width;
        this.height = height;
        initHitbox(x, y, width, height);
    }

    public void updateRelativePos(float x, float y) {
        this.hitbox.x += x;
        this.hitbox.y += y;
    }


    public void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    public int getCenterX() {
        return (int) (hitbox.x + width / 2);
    }

    public int getCenterY() {
        return (int) (hitbox.y + width / 2);
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }
}
