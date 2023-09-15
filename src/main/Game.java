package main;

import java.awt.Graphics;

import Levels.LevelManager;
import entity.Player;
import static util.Constants.Config.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private LevelManager levelManager;

    private Player player;

    public Game() {
        initClass();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClass() {
        player = new Player(0, 0);
        levelManager = new LevelManager(this);
        player.loadCollision(levelManager.getCollision());
    }

    public void render(Graphics g) {
        levelManager.drawBehind(g);
        player.render(g);
        levelManager.drawFront(g);
    }

    public void update() {
        player.update();
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
                System.out.println(TIME_PER_UPDATE / 1e9);
                System.out.println("fps : " + frame + " | ups : " + update);
                lastCheck = System.currentTimeMillis();
                frame = 0;
                update = 0;
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirection();
    }
}
