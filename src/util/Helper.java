package util;

public class Helper {

    public static boolean CanMoveHere(float x, float y, int[][] levelData) {
        System.out.println(x + " " + y);
        if (!unWalkable(x, y, levelData))
            if (!unWalkable(x + Constants.Config.CHAR_SIZE, y + Constants.Config.CHAR_SIZE, levelData))
                if (!unWalkable(x + Constants.Config.CHAR_SIZE, y, levelData))
                    if (!unWalkable(x, y + Constants.Config.CHAR_SIZE, levelData))
                        return true;
        return false;
    }

    private static boolean unWalkable(float x, float y, int[][] levelData) {
        if (x < 0 || x >= Constants.Config.SCREEN_WIDTH) {
            return true;
        }
        if (y < 0 || y >= Constants.Config.SCREEN_HEIGHT) {
            return true;
        }

        float xIndex = x / Constants.Config.CHAR_SIZE;
        float yIndex = y / Constants.Config.CHAR_SIZE;

        int value = levelData[(int) yIndex][(int) xIndex];
        if (value == 0) {
            return true;
        }
        return false;
    }
}
