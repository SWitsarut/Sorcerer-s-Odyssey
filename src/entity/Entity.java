package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import static util.Constants.Config.*;

public abstract class Entity {

    protected float x, y;

    protected Rectangle hitbox;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        initHitbox();
    }

    protected void drawHitbox(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(hitbox.x, hitbox.y, CHAR_SIZE, CHAR_SIZE);
    }

    private void initHitbox() {
        hitbox = new Rectangle((int) x, (int) y, CHAR_SIZE, CHAR_SIZE);
    }

    protected void updateHitbox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
