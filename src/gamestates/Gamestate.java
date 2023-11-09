package gamestates;

public enum Gamestate {
    PLAYING, MENU, DIALOGUE, PAUSE, GAMEOVER, WIN;

    public static Gamestate state = MENU;
}
