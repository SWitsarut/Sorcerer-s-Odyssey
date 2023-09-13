package util;

public class Constants {
    public static class Config {
        public static final int FPS_SET = 120;
        public static final int UPS_SET = 200;
        public static final int aniFramePersecond = 12;
        public static final int TILE_DEFAULT_SIZE = 32;
        public static final float SCALE = 2.5f;

        public static final int TILE_SIZE = (int) (TILE_DEFAULT_SIZE * SCALE);
        public static final int SCREEN_WIDTH = 1200;
        public static final int SCREEN_HEIGHT = 800;
        public static final int TILES_IN_WIDTH = (int) (SCREEN_WIDTH / TILE_SIZE);
        public static final int TILES_IN_HEIGHT = (int) (SCREEN_HEIGHT / TILE_SIZE);
        public static final int CHAR_SIZE = 64;
        public static final int CHAR_DEFAULT_SIZE = 32;

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

    public static class LayerOrder {
        public static final int GROUND = 0;
        public static final int WALL = 1;
        public static final int FRONT = 2;
        public static final int COLLISION = 3;
    }
}
