package entities.Projectile;

import java.awt.Color;
import java.awt.Graphics;

import entities.Entity;
import helperClass.Coordinate;
import helperClass.UpdateCounter;

public abstract class Projectile extends Entity {

    protected boolean active;
    protected boolean oldActive;
    protected UpdateCounter lifeTime;
    protected boolean PlayerOwn;

    /**
     * speed : pixel per update
     */
    private double speed;
    private boolean expiredOntarget;

    protected Coordinate targetCoor;

    private double xSpeed;
    private double ySpeed;

    public Projectile(Boolean playerOwn, double lifeTime, float xStart, float yStart, Coordinate targetCoor,
            float width,
            float height) {
        super(xStart, yStart, width, height);
        this.active = true;
        this.lifeTime = new UpdateCounter(lifeTime, false);
        this.targetCoor = targetCoor;
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
        g.setColor(Color.RED);
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
            // double distance = Math.sqrt(dx * dx + dy * dy);
            double angle = Math.atan2(dy, dx);
            xSpeed = speed * Math.cos(angle);
            ySpeed = speed * Math.sin(angle);
        }
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
