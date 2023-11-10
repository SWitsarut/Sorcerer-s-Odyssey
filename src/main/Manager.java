package main;

import java.awt.*;

public interface Manager {
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset);

    public void update();
}
