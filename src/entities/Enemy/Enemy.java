package entities.Enemy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.Entity;
import gamestates.Gamestate;
import util.LoadSave;
import util.Constants.Config;

public abstract class Enemy extends Entity {

    protected BufferedImage img;
    private BufferedImage[] animation;
    protected String FileName;
    protected int maxAniFrame = 1;
    protected int aniTick = 0, aniIndex, aniFramePersecond = Config.ANIMATION_FRAME_PERSECOND;
    protected float scale = 1;

    protected double damage;

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniFramePersecond) {
            aniTick = 0;
            aniIndex = (aniIndex + 1) % maxAniFrame;
        }
    }

    public Enemy(String FileName, float scale, float x, float y, float width, float height) {
        super(x, y, scale * width, scale * height);
        this.scale = scale;
        this.FileName = FileName;

    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        if (Gamestate.state == Gamestate.PLAYING)
            updateAnimationTick();
        g.drawImage(animation[aniIndex], (int) x - xLvlOffset, (int) y - yLvlOffset,
                (int) (img.getWidth() / maxAniFrame * scale * Config.SCALE),
                (int) (img.getHeight() * scale * Config.SCALE), null);
        drawHitbox(g, xLvlOffset, yLvlOffset);
    }

    protected void loadImage() {
        img = LoadSave.GetImage("entity/enemy/" + FileName);
        loadAnimation();
    }

    private void loadAnimation() {
        animation = new BufferedImage[maxAniFrame];
        int frameWidth = img.getWidth() / maxAniFrame; // Calculate the width of each animation frame

        for (int i = 0; i < maxAniFrame; i++) {
            animation[i] = img.getSubimage(i * frameWidth, 0, frameWidth, img.getHeight());
        }
    }

    public void update() {
    };

}
