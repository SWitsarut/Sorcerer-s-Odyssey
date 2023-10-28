package effect;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import util.Constants.Config;

public class EffectPlayer {

    BufferedImage animation[];
    int x, y;
    private boolean ended = false;
    double scale;

    private int Tick = 0, Index, aniFramePersecond = Config.ANIMATION_FRAME_PERSECOND / 2;

    public EffectPlayer(BufferedImage[] animation, int x, int y, double scale) {
        this.animation = animation;
        this.x = x;
        this.y = y;
        this.scale = scale;

    }

    private void updateAnimationTick() {
        Tick++;
        // System.out.print(" " + Tick);
        if (Tick >= aniFramePersecond && Index < animation.length) {
            Tick = 0;
            Index++;
            // System.out.print(" " + Index);
            if (Index >= animation.length) {
                ended = true;
            }
        }
    }

    public void update() {

    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        updateAnimationTick();
        // g.setColor(Color.RED);
        // g.fillRect(x - xLvlOffset, y - yLvlOffset, 32, 32);
        if (!ended) {
            g.drawImage(animation[Index], (int) (x - xLvlOffset - (animation[Index].getWidth() * scale) / 2),
                    (int) (y - yLvlOffset - (animation[Index].getHeight() * scale) / 2),
                    (int) (animation[Index].getWidth() * scale),
                    (int) (animation[Index].getHeight() * scale), null);
        }

    }

    public boolean getEnded() {
        return ended;
    }
}
