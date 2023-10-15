package effect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import util.Constants;
import util.Constants.Config;

public class EffectPlayer {

    BufferedImage animation[];
    int x, y;
    private boolean ended = false;
    double scale;

    protected int aniTick = 0, aniIndex, aniFramePersecond = Config.ANIMATION_FRAME_PERSECOND;

    public EffectPlayer(BufferedImage[] animation, int x, int y, double scale) {
        this.animation = animation;
        this.x = x;
        this.y = y;
        this.scale = scale;

    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniFramePersecond) {
            aniTick = 0;
            aniIndex++;
        }
        if (aniIndex >= animation.length / 4) {
            ended = true;
        }
    }

    public void update() {
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        updateAnimationTick();
        // g.setColor(Color.RED);
        // g.fillRect(x - xLvlOffset, y - yLvlOffset, 32, 32);
        g.drawImage(animation[aniIndex], (int)(x - xLvlOffset - (animation[aniIndex].getWidth() * scale) / 2),
                (int) (y - yLvlOffset - (animation[aniIndex].getHeight() * scale) / 2),
                (int) (animation[aniIndex].getWidth() * scale),
                (int) (animation[aniIndex].getHeight() * scale), null);

    }

    public boolean getEnded() {
        return ended;
    }
}
