package entities.Projectile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Action.Damage;
import entities.Entity;
import entities.Enemy.Enemy;
import helperClass.Coordinate;
import helperClass.UpdateCounter;

public abstract class Projectile extends Entity {

    protected boolean active;
    protected boolean oldActive;
    protected UpdateCounter lifeTime;
    protected boolean PlayerOwn;

    public Damage damage;
    public ArrayList<Enemy> enemyhitted;

    /**
     * speed : pixel per update
     */
    protected double speed;
    protected boolean expiredOntarget;
    protected boolean expiredOnHit;

    public void setExpiredOnHit(boolean expiredOnHit) {
        this.expiredOnHit = expiredOnHit;
    }

    protected Coordinate targetCoor;
    protected BufferedImage[] animate;

    protected double xSpeed;
    protected double ySpeed;

    public Projectile(Damage dmg, Boolean playerOwn, double lifeTime, float xStart, float yStart, Coordinate targetCoor,
            float width,
            float height) {
        super(xStart - width / 2, yStart - height / 2, width, height);
        damage = dmg;
        enemyhitted = new ArrayList<>();
        this.active = true;
        this.lifeTime = new UpdateCounter(lifeTime, false) {

            @Override
            public void onUpdate() {
            }

        };
        this.lifeTime.start();
        this.targetCoor = targetCoor;
        this.targetCoor.x -= hitbox.width;
        this.targetCoor.y -= hitbox.height;
        this.PlayerOwn = playerOwn;
    }

    public void setExpiredOntarget(boolean expiredOntarget) {
        this.expiredOntarget = expiredOntarget;
    }

    public void update() {
        updatePos();
        oldActive = active;
        lifeTime.update();

        active = lifeTime.active;
        expiredUpdate();
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.setColor(new Color(255, 12, 12, 80));
        g.fillOval((int) (hitbox.x - xLvlOffset), (int) (hitbox.y - yLvlOffset),
                (int) (hitbox.width),
                (int) (hitbox.height));
    }

    protected void expiredUpdate() {
        if (oldActive != active) {
            onExpired();
        }
    }

    protected abstract void onExpired();

    protected void updatePos() {
        if (expiredOntarget && Math.abs(targetCoor.x - hitbox.x) <= xSpeed
                && Math.abs(targetCoor.y - hitbox.y) <= ySpeed) {
            lifeTime.active = false;
        } else {

            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
        }
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        double dx = targetCoor.x - hitbox.x;
        double dy = targetCoor.y - hitbox.y;

        if (dx == 0 && dy == 0) {
            xSpeed = 0;
            ySpeed = 0;
        } else {
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance != 0) {
                xSpeed = (speed / distance) * dx;
                ySpeed = (speed / distance) * dy;
            }
        }
    }

    public Damage hit(Entity entity) {
        if (expiredOntarget | expiredOnHit) {
            onExpired();
            active = false;
        }
        onHit();
        return damage;
    }

    protected void onHit() {

    };

    public boolean isActive() {
        return active;
    }

    public boolean isPlayerOwn() {
        return PlayerOwn;
    }


    public Coordinate getTargetCoor() {
        return targetCoor;
    }

}
