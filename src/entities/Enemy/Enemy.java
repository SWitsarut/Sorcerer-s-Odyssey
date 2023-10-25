package entities.Enemy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Action.Damage;
import Action.Def;
import effect.EffectManager;
import entities.Entity;
import gamestates.Gamestate;
import util.Constants.Config;

public abstract class Enemy extends Entity {

    private BufferedImage[] animation;

    protected int maxAniFrame = 1;
    protected int aniTick = 0, aniIndex, aniFramePersecond = Config.ANIMATION_FRAME_PERSECOND;
    protected float scale = 1;
    protected double damage;
    protected boolean isDead;

    protected double hp;
    protected double maxHp;
    protected double speed;
    protected Def def;

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniFramePersecond) {
            aniTick = 0;
            aniIndex = (aniIndex + 1) % maxAniFrame;
        }
    }

    public void setHp(double hp) {
        this.maxHp = hp;
        this.hp = hp;
    }

    public Enemy(BufferedImage[] animation, float scale, Def def, float x, float y, float width, float height) {
        super(x, y, scale * width, scale * height);
        this.def = def;
        this.scale = scale;
        this.animation = animation;
        this.isDead = false;
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        if (Gamestate.state == Gamestate.PLAYING)
            updateAnimationTick();
        g.drawImage(animation[aniIndex], (int) hitbox.x - xLvlOffset, (int) hitbox.y - yLvlOffset,
                (int) (animation[aniIndex].getWidth() * scale * Config.SCALE),
                (int) (animation[aniIndex].getHeight() * scale * Config.SCALE), null);
        drawHitbox(g, xLvlOffset, yLvlOffset);
    }

    public void getAttacked(Damage damage) {
        switch (damage.getType()) {
            case Damage.FIRE:
                hp -= damage.getDamage() * (100 - def.FireDef) / 100;
                break;
            case Damage.HOLY:
                hp -= damage.getDamage() - Math.min((100 - def.HolyDef / 100), 0);
                break;
            case Damage.LIGHTING:
                hp -= damage.getDamage() - Math.min((100 - def.LightingDef / 100), 0);
                break;
            case Damage.ARCANE:
                // hp -= damage.getDamage() - Math.min((100 - def.ArcaneDef / 100), 0);
                hp -= damage.getDamage() * (100 - def.ArcaneDef) / 100;
                break;
            case Damage.PHYSICAL:
                hp -= damage.getDamage() - Math.min((100 - def.PhysicalDef / 100), 0);
                break;
            default:
                break;
        }
        checkDied();
    }

    public void update() {
        hitbox.x = x + (hitbox.x + 1) % 100;
    };

    protected void checkDied() {
        if (hp <= 0) {
            isDead = true;
        }
    }

}
