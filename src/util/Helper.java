package util;

import util.Constants.Config;

public class Helper {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelData) {
        // System.out.println(x + " " + y);
        if (!unWalkable(x, y, levelData))
            if (!unWalkable(x + width, y + height, levelData))
                if (!unWalkable(x + width, y, levelData))
                    if (!unWalkable(x, y + height, levelData))
                        return true;
        return false;
    }
    

    private static boolean unWalkable(float x, float y, int[][] levelData) {
        int levelHeight = (levelData.length - 1) * Config.TILE_SIZE;
        int levelWidth = (levelData[0].length - 2) * Config.TILE_SIZE;
        if (x < 0 || x >= levelWidth) {
            return true;
        }
        if (y < 0 || y >= levelHeight) {
            return true;
        }

        float xIndex = x / Constants.Config.TILE_SIZE;
        float yIndex = y / Constants.Config.TILE_SIZE;

        int value = levelData[(int) yIndex][(int) xIndex];
        if (value == 0) {
            return true;
        }
        return false;
    }
}
