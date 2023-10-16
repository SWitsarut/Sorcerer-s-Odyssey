package entities.Projectile;

import java.awt.Color;
import java.awt.Graphics;

import entities.Entity;
import gamestates.Gamestate;
import helperClass.Coordinate;
import util.Constants.Config;

public abstract class Projectile extends Entity {

    protected long maxlifeTime;
    protected long curlifeTime;
    protected boolean active;
    protected boolean PlayerOwn;

    /**
     * speed : pixel per update
     */
    private double speed;
    private boolean expiredOntarget;

    protected Coordinate targetCoor;

    private double xSpeed;
    private double ySpeed;

    public Projectile(Boolean playerOwn, float xStart, float yStart, Coordinate targetCoor, float width,
            float height) {
        super(xStart, yStart, width, height);
        this.active = true;
        curlifeTime = 0;
        this.targetCoor = targetCoor;
        this.PlayerOwn = playerOwn;
    }

    public void setLifeTime(double second) {
        maxlifeTime = (long) (second * Config.UPS_SET);
    }

    public void setExpiredOntarget(boolean expiredOntarget) {
        this.expiredOntarget = expiredOntarget;
    }

    public void update() {
        if (active) {
            updatePos();
            updateLifeTime();
        }
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.setColor(Color.RED);
        g.fillOval((int) (hitbox.x - xLvlOffset), (int) (hitbox.y - yLvlOffset),
                (int) (hitbox.width),
                (int) (hitbox.height));
    }

    protected void onExpired() {

    }

    protected void updatePos() {
        hitbox.x += xSpeed;
        hitbox.y += ySpeed;
    }

    protected void updateLifeTime() {
        curlifeTime++;
        if (curlifeTime >= maxlifeTime) {
            active = false;
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
            double angle = Math.atan2(dy, dx);
            xSpeed = speed * Math.cos(angle);
            ySpeed = speed * Math.sin(angle);
        }
    }

    public long getMaxlifeTime() {
        return maxlifeTime;
    }

    public long getCurlifeTime() {
        return curlifeTime;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isPlayerOwn() {
        return PlayerOwn;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isExpiredOntarget() {
        return expiredOntarget;
    }

    public Coordinate getTargetCoor() {
        return targetCoor;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public double getySpeed() {
        return ySpeed;
    }

}
