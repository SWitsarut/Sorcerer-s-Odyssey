package entities.Projectile;

import entities.Entity;
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

    public void setExpiredOntarget(boolean expiredOntarget) {
        this.expiredOntarget = expiredOntarget;
    }

    public Projectile(Boolean playerOwn, float xStart, float yStart, Coordinate targetCoor, float width,
            float height) {
        super(xStart, yStart, width, height);
        this.active = true;
        curlifeTime = 0;
        this.PlayerOwn = playerOwn;
    }

    public void setLifeTime(double second) {
        maxlifeTime = (long) (second * Config.UPS_SET);
    }

    public void update() {
        if (active) {
            updatePos();
            updateLifeTime();
        }
    }

    protected void onExpired() {

    }

    protected void updatePos() {

    }

    protected void updateLifeTime() {
        curlifeTime++;
        if (curlifeTime >= maxlifeTime) {
            active = false;
        }
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}
