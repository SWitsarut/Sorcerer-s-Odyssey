package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import main.Game;
import static util.LoadSave.*;
import static util.Constants.Config.*;
import static util.Constants.LayerOrder.*;

public class LevelManager {

    public static final int LAVADUNGEON = 0;

    private Playing playing;
    private BufferedImage[] levelSprite;
    private Level levelLayers[];
    public LevelEvent levelEvents[];

    // private EnemyManager enemyManager;
    public static int curMapIndex = 0;
    private String[] mapNameArr;

    public Level getCollision() {
        return levelLayers[COLLISION];
    }

    public LevelManager(Playing game) {
        this.playing = game;
        // enemyManager = game.getPlaying().getEnemyManager();
        importTile();
        mapNameArr = getFileList("");

        System.out.println("\n**********\nList of map name\n__________\n");
        for (String lvName : mapNameArr) {
            System.out.println(lvName);
        }
        System.out.println("\n**********");

        levelLayers = getLevelLeyerData(mapNameArr[curMapIndex]);
        levelEvents = new LevelEvent[levelLayers.length];

        initLevelEvent();

    }

    private void initLevelEvent() {
        // levelEvents[CAVE] = new LevelEvent() {

        // @Override
        // public void onEnter() {
        // }

        // @Override
        // public void onExit() {
        // }

        // };

        // levelEvents[CAMP] = new LevelEvent() {

        // @Override
        // public void onEnter() {
        // }

        // @Override
        // public void onExit() {
        // }

        // };
        // levelEvents[TEMPLE] = new LevelEvent() {

        // @Override
        // public void onEnter() {
        // game.getPlaying().geteventManager().monsterHordeEvent();
        // }

        // @Override
        // public void onExit() {
        // game.getPlaying().geteventManager().monsterHordeEnd();
        // }
        // };
        levelEvents[LAVADUNGEON] = new LevelEvent() {

            @Override
            public void onEnter() {
                playing.geteventManager().monsterHordeEvent();
            }

            @Override
            public void onExit() {
                // game.getPlaying().geteventManager().monsterHordeEnd();
            }

        };
    }

    public void importTile() {
        BufferedImage img = GetLevelAtlas("newTileSet.png");

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

    public int getIndexFromMapName(String name) {
        for (int i = 0; i < mapNameArr.length; i++) {
            if (name == mapNameArr[i]) {
                return i;
            }
        }
        return 0;
    }

    public void nextMap() {
        playing.getEnemyManager().enemiesClear();
        levelEvents[curMapIndex].onExit();
        curMapIndex = (curMapIndex + 1) % mapNameArr.length;
        levelLayers = getLevelLeyerData(mapNameArr[curMapIndex]);
        playing.handleMapChange();
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
