package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import interact.MotherObject;
import main.Game;
import static util.LoadSave.*;
import static util.Constants.Config.*;
import static util.Constants.LayerOrder.*;
import static util.ObjectType.*;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelLayers[];

    private int curMapIndex = 2;
    private String[] mapNameArr;

    public Level getCollision() {
        return levelLayers[COLLISION];
    }

    public LevelManager(Game game) {
        this.game = game;
        importTile();
        mapNameArr = getFileList("");
        levelLayers = getLevelLeyerData(mapNameArr[curMapIndex]);
    }

    public void importTile() {
        BufferedImage img = GetLevelAtlas("universal_tile_set.png");

        int highCount = (img.getHeight() / TILE_DEFAULT_SIZE);
        int widthCount = (img.getWidth() / TILE_DEFAULT_SIZE);
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

    public void nextMap() {
        curMapIndex = (curMapIndex + 1) % mapNameArr.length;
        levelLayers = getLevelLeyerData(mapNameArr[curMapIndex]);
    }

    public void drawBehind(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (int layer = 0; layer < 2; layer++) {
            drawMap(g, layer, xLvlOffset, yLvlOffset);
        }
    }

    public void drawFront(Graphics g, int xLvlOffset, int yLvlOffset) {
        drawMap(g, FRONT, xLvlOffset, yLvlOffset);
    }

    private void drawMap(Graphics g, int layer, int xLvlOffset, int yLvlOffset) {
        for (int j = 0; j < levelLayers[layer].getYlength(); j++) {
            for (int i = 0; i < levelLayers[layer].getXlength(); i++) {
                int index = levelLayers[layer].getSpriteIndex(i, j);
                if (index >= 0) {
                    g.drawImage(levelSprite[index],
                            (int) (i * TILE_SIZE) - xLvlOffset,
                            (int) (j * TILE_SIZE) - yLvlOffset,
                            TILE_SIZE,
                            TILE_SIZE,
                            null);
                }
            }
        }
    }

    public Level[] getCurrentLevels() {
        return levelLayers;
    }
}
