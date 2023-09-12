package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;

import static util.LoadSave.*;
import static util.Constants.Config.*;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;

    public LevelManager(Game game) {
        this.game = game;
        importTile();
        int arr[][] = getLevelData("desert.csv");
        for (int[] is : arr) {
            for (int is2 : is) {
                System.out.print(is2 + " ");
            }
            System.out.println();
        }

    }

    public void importTile() {
        BufferedImage img = GetLevelAtlas("desert.png");

        int highCount = (img.getHeight() / TILE_DEFAULT_SIZE);
        int widthCount = (img.getWidth() / TILE_DEFAULT_SIZE);
        System.out.println("h " + highCount + " w " + widthCount);
        int amountOfSprite = highCount * widthCount;
        levelSprite = new BufferedImage[amountOfSprite];
        for (int i = 0; i < highCount; i++) {
            for (int j = 0; j < widthCount; j++) {
                int index = (i * widthCount) + j;
                levelSprite[index] = img.getSubimage((TILE_DEFAULT_SIZE * j), (TILE_DEFAULT_SIZE
                        * i), TILE_DEFAULT_SIZE,
                        TILE_DEFAULT_SIZE);
            }
        }
    }

    public void draw(Graphics g) {
        g.drawImage(levelSprite[92], 0, 0, (int) (TILE_SIZE * SCALE), (int) (TILE_SIZE * SCALE), null);
    }
}
