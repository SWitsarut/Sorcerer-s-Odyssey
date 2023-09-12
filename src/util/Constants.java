package util;

public class Constants {
    public static class Config {
        public static final int FPS_SET = 120;
        public static final int UPS_SET = 200;
        public static final int aniFramePersecond = 12;
        public static final int TILE_DEFAULT_SIZE = 16;
        public static final float SCALE = 1.5f;
        public static final int TILES_IN_WIDTH = 70;
        public static final int TILES_IN_HEIGHT = 40;
        public static final int TILE_SIZE = (int) (TILE_DEFAULT_SIZE * SCALE);
        public static final int SCREEN_WIDTH = TILE_SIZE * TILES_IN_WIDTH;
        public static final int SCREEN_HEIGHT = TILE_SIZE * TILES_IN_HEIGHT;

    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int GESTURE = 1;
        public static final int WALKING = 2;
        public static final int ATTACK = 3;
        public static final int DYING = 4;
        public static final int STUNNED = DYING;
    }

    public static class Direction {
        public static final int UP = 0;
        public static final int UP_RIGHT = 1;
        public static final int RIGHT = 2;
        public static final int DOWN_RIGHT = 3;
        public static final int DOWN = 4;
        public static final int DOWN_LEFT = 5;
        public static final int LEFT = 6;
        public static final int UP_LEFT = 7;
    }

}
