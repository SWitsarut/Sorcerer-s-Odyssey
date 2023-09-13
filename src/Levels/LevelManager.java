package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entity.Player;
import main.Game;
import util.LoadSave;

import static util.LoadSave.*;
import static util.Constants.Config.*;
import static util.Constants.LayerOrder.*;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelLayers[];
    private Player player;

    public LevelManager(Game game, Player player) {
        this.game = game;
        this.player = player;
        importTile();
        // private Level levelLayers[] = LoadSave.getLevelLeyerData("forest");
        levelLayers = LoadSave.getLevelLeyerData("forest");

    }

    public void importTile() {
        BufferedImage img = GetLevelAtlas("terrain_atlas.png");

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
        for (int layer = 0; layer < 2; layer++) {
            drawMap(g, layer);
        }
        player.render(g);
        drawMap(g, FRONT);

    }

    private void drawMap(Graphics g, int layer) {
        for (int j = 0; j < TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < TILES_IN_WIDTH; i++) {
                int index = levelLayers[layer].getSpriteIndex(i, j);
                if (index >= 0) {
                    g.drawImage(levelSprite[index], (int) (i * TILE_SIZE), (int) (j * TILE_SIZE), TILE_SIZE,
                            TILE_SIZE,
                            null);
                }
            }
        }
    }
}
