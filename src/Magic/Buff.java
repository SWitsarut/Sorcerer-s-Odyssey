package Magic;

import java.awt.Graphics;

import helperClass.UpdateCounter;

public abstract class Buff {

    protected UpdateCounter buff;
    private boolean oldActive;

    public Buff(double second) {
        buff = new UpdateCounter(second, false);
    }

    protected void start() {
        onActive();
        buff.start();
    }

    public abstract void draw(Graphics g, int xLvlOffset, int yLvlOffset);

    public void update() {
        if (buff.active) {
            onUpdate();
            oldActive = buff.active;
            buff.update();
            if (oldActive != buff.active) {
                onExpired();
            }
        }
    }

    public boolean isActive() {
        return buff.active;
    }

    public abstract void onUpdate();

    public abstract void onActive();

    public abstract void onExpired();
}
