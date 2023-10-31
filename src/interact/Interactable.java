package interact;

import java.awt.Graphics;

public interface Interactable {
    // public String interactWord();
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset);

    public void update();

    public boolean isActive();
}
