package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Levels.LevelManager;
import entity.Player;
import main.Game;
import util.Constants.Config;

public class Playing extends State implements Statemethods {

    private LevelManager levelManager;
    private Player player;

    private int xLvlOffset;
    private int yLvlOffset;
    private int leftBorder = (int) (Config.SCREEN_MOVER_AFTER_PerCent * Config.SCREEN_WIDTH);
    private int rightBorder = (int) ((1 - Config.SCREEN_MOVER_AFTER_PerCent) * Config.SCREEN_WIDTH);
    private int topBorder = (int) (Config.SCREEN_MOVER_AFTER_PerCent * Config.SCREEN_HEIGHT);
    private int bottomBorder = (int) ((1 - Config.SCREEN_MOVER_AFTER_PerCent) * Config.SCREEN_HEIGHT);
    private int lvlTileWide;
    private int lvlTileHeight;
    private int maxTileOffset;
    private int maxLevelOffsetX;
    private int maxYTileOffset;
    private int maxLevelOffsetY;

    public Playing(Game game) {
        super(game);
        initClass();
    }

    private void initClass() {
        player = new Player(0, 0);
        levelManager = new LevelManager(game);
        player.loadCollision(levelManager.getCollision());
        lvlTileWide = levelManager.getCurrentLevels()[0].getXlength() - 2;
        lvlTileHeight = levelManager.getCurrentLevels()[0].getYlength() - 2;
        maxTileOffset = lvlTileWide - Config.TILES_IN_WIDTH;
        maxLevelOffsetX = maxTileOffset * Config.TILE_SIZE;
        maxYTileOffset = lvlTileHeight - Config.TILES_IN_HEIGHT;
        maxLevelOffsetY = maxYTileOffset * Config.TILE_SIZE;
    }

    public void windowFocusLost() {
        player.resetDirection();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() {
        player.update();
        checkCloseToBorder();
    }

    private void checkCloseToBorder() {
        int playerX = (int) player.getHitbox().x;
        int playerY = (int) player.getHitbox().y;

        int diffX = playerX - xLvlOffset;
        int diffY = playerY - yLvlOffset;

        if (diffX > rightBorder)
            xLvlOffset += diffX - rightBorder;
        else if (diffX < leftBorder)
            xLvlOffset += diffX - leftBorder;

        if (diffY > bottomBorder)
            yLvlOffset += diffY - bottomBorder;
        else if (diffY < topBorder)
            yLvlOffset += diffY - topBorder;

        if (xLvlOffset > maxLevelOffsetX)
            xLvlOffset = maxLevelOffsetX;
        else if (xLvlOffset < 0)
            xLvlOffset = 0;

        if (yLvlOffset > maxLevelOffsetY)
            yLvlOffset = maxLevelOffsetY;
        else if (yLvlOffset < 0)
            yLvlOffset = 0;
    }

    @Override
    public void draw(Graphics g) {
        levelManager.drawBehind(g, xLvlOffset, yLvlOffset);
        player.render(g, xLvlOffset, yLvlOffset);
        levelManager.drawFront(g, xLvlOffset, yLvlOffset);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // click
        if (e.getButton() == e.BUTTON1) {
            // player.attack(e.getX());
        } else {
            // player.useMagic(e.getX());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println(player.getHitbox().x + "");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: // W
                player.setUp(true);
                break;
            case KeyEvent.VK_D:// D
                player.setRight(true);
                break;
            case KeyEvent.VK_A:// A
                player.setLeft(true);
                break;
            case KeyEvent.VK_S:// S
                player.setDown(true);
                break;
            case KeyEvent.VK_ESCAPE:
                Gamestate.state = Gamestate.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: // W
                player.setUp(false);
                break;
            case KeyEvent.VK_D:// D
                player.setRight(false);
                break;
            case KeyEvent.VK_A:// A
                player.setLeft(false);
                break;
            case KeyEvent.VK_S:// S
                player.setDown(false);
                break;
        }
    }

}
