package util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {

    public static String PLAYER_ATLAS = "rogue.png";

    public static BufferedImage GetSpriteAtlas(String file) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/img/entity/" + file);

        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}
