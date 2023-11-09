package util;

public class Constants {
    public static class SoundFile {
        public static final String NEXT_EFFECT = "Fantasy/Fantasy_UI (1).wav";
        public static final String SUBMIT_EFFECT = "Fantasy/Fantasy_UI (4).wav";
    }

    public static class Config {

        public static final int FPS_SET = 60;
        public static final int UPS_SET = 120;

        public static final double TIME_PER_FRAME = 1000000000.0 / FPS_SET;
        public static final double TIME_PER_UPDATE = 1000000000.0 / UPS_SET;
        public static final double TIME_PER_UPDATE_SECOND = TIME_PER_UPDATE / 1e9;
        public static final double UPDATE_PER_TIME = 1 / TIME_PER_UPDATE_SECOND;

        public static final int ANIMATION_FRAME_PERSECOND = FPS_SET / 10;

        public static final double SCALE = 2;
        public static final int TILE_DEFAULT_SIZE = 16;
        public static final int TILE_SIZE = (int) (TILE_DEFAULT_SIZE * SCALE * 1.5);
        public static final int SCREEN_WIDTH = 1200;
        public static final int SCREEN_HEIGHT = 800;
        public static final double SCREEN_MOVER_AFTER_PerCent = 0.5;

        public static final int TILES_IN_WIDTH = (int) (SCREEN_WIDTH / TILE_SIZE);
        public static final int TILES_IN_HEIGHT = (int) (SCREEN_HEIGHT / TILE_SIZE);
        public static final int CHAR_DEFAULT_SIZE = 32;
        public static final int CHAR_SIZE = (int) (CHAR_DEFAULT_SIZE * SCALE);

    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int WALKING = 2;
    }


    public static class LayerOrder {
        public static final int FRONT = 2;
        public static final int COLLISION = 3;
    }
}
