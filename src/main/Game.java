package main;

import java.awt.Graphics;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

import static util.Constants.Config.*;
import static gamestates.Gamestate.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    private Playing playing;
    private Menu menu;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    public void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    public void render(Graphics g) {

        switch (state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            case DIALOGUE:
                break;
            default:
                break;
        }

    }

    public void update() {

        switch (state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case DIALOGUE:

                break;
            default:
                break;
        }
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        long previousTime = System.nanoTime();

        int frame = 0;
        int update = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / TIME_PER_UPDATE;

            deltaF += (currentTime - previousTime) / TIME_PER_FRAME;
            previousTime = currentTime;
            if (deltaU >= 1) {
                // update
                update++;
                deltaU--;
            }
            if (deltaF >= 1) {
                gamePanel.repaint();
                frame++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                System.out.println("fps : " + frame + " | ups : " + update);
                lastCheck = System.currentTimeMillis();
                frame = 0;
                update = 0;
            }
        }
    }

    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING) {
            playing.getPlayer().resetDirection();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
}
