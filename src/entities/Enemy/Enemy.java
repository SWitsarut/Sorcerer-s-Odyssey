package entities.Enemy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Action.Damage;
import Action.Def;
import entities.Entity;
import entities.Player;
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
    protected double xSpeed;
    protected double ySpeed;

    public boolean attacked = false;
    protected Def def;

    protected boolean chasing;
    protected double aggroRange = 100;

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
        this.chasing = false;
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        if (Gamestate.state == Gamestate.PLAYING)
            updateAnimationTick();
        g.drawImage(animation[aniIndex], (int) hitbox.x - xLvlOffset, (int) hitbox.y - yLvlOffset,
                (int) (animation[aniIndex].getWidth() * scale * Config.SCALE),
                (int) (animation[aniIndex].getHeight() * scale * Config.SCALE), null);
        // drawHitbox(g, xLvlOffset, yLvlOffset);
    }

    public void getAttacked(Damage damage) {
        chasing = true;
        switch (damage.getType()) {
            case Damage.FIRE:
                hp -= damage.getDamage() * (100 - def.FireDef) / 100;
                break;
            case Damage.HOLY:
                hp -= damage.getDamage() * (100 - def.HolyDef) / 100;
                break;
            case Damage.LIGHTING:
                hp -= damage.getDamage() * (100 - def.LightingDef) / 100;
                break;
            case Damage.ARCANE:
                hp -= damage.getDamage() * (100 - def.ArcaneDef) / 100;
                break;
            case Damage.PHYSICAL:
                hp -= damage.getDamage() * (100 - def.PhysicalDef) / 100;
                break;
            default:
                break;
        }
        checkDied();
    }

    public void update(Player player) {
        chase(player);
        move();
        if (hp < maxHp) {
            attacked = true;
        }
    };

    protected void chase(Player player) {
        int playerX = (int) (player.getHitbox().x + player.getHitbox().width / 2);
        int playerY = (int) (player.getHitbox().y + player.getHitbox().height / 2);
        // int playerY = player.getHitboxCenterY();

        int deltaX = (int) (playerX - (hitbox.x + hitbox.width / 2));
        int deltaY = (int) (playerY - (hitbox.y + hitbox.height / 2));

        double distance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));

        if (distance <= aggroRange) {
            chasing = true;
        } else if (hp >= maxHp && !attacked) {
            chasing = false;
            xSpeed = 0;
            ySpeed = 0;
        }
        if (chasing) {
            // System.out.println("chasing");
            double directionX = deltaX / distance;
            double directionY = deltaY / distance;

            xSpeed = (int) (speed * directionX);
            ySpeed = (int) (speed * directionY);
        }
    }

    protected void move() {
        hitbox.x += xSpeed;
        hitbox.y += ySpeed;
    }

    protected void checkDied() {
        if (hp <= 0) {
            isDead = true;
        }
    }

}
