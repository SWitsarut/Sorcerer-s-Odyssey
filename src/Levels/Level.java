package Levels;

public class Level {

    private int[][] lvlData;

    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
        System.out.println(getXlength());
    }

    public int[][] getData() {
        return lvlData;
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int getYlength() {
        return lvlData.length;
    }

    public int getXlength() {
        return lvlData[0].length;
    }
}
