package interact;

import java.awt.image.BufferedImage;

public class MotherObject {
    // private BufferedImage img;
    protected int mapX, mapY, TYPE;

    public MotherObject(int mapX, int mapY, int TYPE) {
        this.mapX = mapX;
        this.mapY = mapY;
        this.TYPE = TYPE;
    }

    public int getTYPE() {
        return TYPE;
    }
}
