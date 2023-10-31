package event;

import java.awt.Graphics;

public interface Event {
    // public String interactWord();
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset);

    public void update();

    public boolean isActive();
}
