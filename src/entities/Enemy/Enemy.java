package entities.Enemy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.Entity;

public abstract class Enemy extends Entity {

    protected BufferedImage img;

    public Enemy(float x, float y, float width, float height, BufferedImage bufferedImage) {
        super(x, y, width, height);
        this.img = bufferedImage;
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.drawImage(img, (int) x, (int) y, (int) hitbox.width, (int) hitbox.height, null);
    }

}
