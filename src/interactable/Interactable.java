package interactable;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Interactable {

    protected String massage;
    protected Rectangle2D.Float hitbox;
    protected boolean showMessage = false;

    public boolean isShowMessage() {
        return showMessage;
    }

    public void setShowMessage(boolean showMessage) {
        this.showMessage = showMessage;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public Interactable(String massage) {
        hitbox = new Rectangle2D.Float(0f, 0f, 0f, 0f);
        this.massage = massage;
    }

    public abstract void onSubmit();

    public abstract void draw(Graphics g, int xLvlOffset, int yLvlOffset);

    public abstract void update();

    public abstract void onIntersects();

    public abstract void notIntersects();

    public String getMassage() {
        return massage;
    };
}
