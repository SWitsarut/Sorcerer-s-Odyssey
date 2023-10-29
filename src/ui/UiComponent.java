package ui;

import java.awt.Rectangle;

public abstract class UiComponent {
    protected int xPos, yPos;
    protected boolean mouseOver, mousePressed;
    protected Rectangle bounds;

    public UiComponent(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        initBounds(width, height);
    }

    private void initBounds(int width, int height) {
        bounds = new Rectangle(xPos - (int) (width / 2), yPos - (int) (height / 2), width, height);
    }
}
