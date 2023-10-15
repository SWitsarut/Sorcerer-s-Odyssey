package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Levels.LevelManager;
import entities.Player;
import helperClass.Coordinate;
import interact.InteractableManager;
import main.Game;
import util.LoadSave;
import util.Constants.Config;

public class Playing extends State implements Statemethods {

    private LevelManager levelManager;
    private Player player;
    private InteractableManager InterManager;

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

    private int mousePosX;
    private int mousePosY;
    private BufferedImage crosshair;
    private int crosshairSize = 40;
    // private ArrayList<MotherObject> arrObj = new ArrayList<>();

    public Playing(
            Game game) {
        super(game);
        initClass();
    }

    private void initClass() {
        crosshair = LoadSave.GetImage("crosshair007.png");
        player = new Player(0, 0);
        levelManager = new LevelManager(game);
        InterManager = new InteractableManager(levelManager);
        handleMapChange();
    }

    public void handleMapChange() {
        player.loadCollision(levelManager.getCollision());
        lvlTileWide = levelManager.getCurrentLevels()[0].getXlength();
        lvlTileHeight = levelManager.getCurrentLevels()[0].getYlength();
        maxTileOffset = lvlTileWide - Config.TILES_IN_WIDTH;
        maxLevelOffsetX = maxTileOffset * Config.TILE_SIZE;
        maxYTileOffset = lvlTileHeight - Config.TILES_IN_HEIGHT - 1;
        maxLevelOffsetY = maxYTileOffset * Config.TILE_SIZE;
        InterManager.handleMapUpdate();
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
        getWorldRealPos();
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

    public void getWorldRealPos() {
        System.out.println(player.getHitbox().x + xLvlOffset - 50);
        System.out.println("player hitbox " + player.getHitbox().x + " , " + player.getHitbox().y);

        System.out
                .println("coor"
                        + new Coordinate((int) (player.getHitbox().x - xLvlOffset),
                                (int) (player.getHitbox().y - yLvlOffset)));
        System.out.println(
                "cal " + (player.getHitbox().x + (player.getHitbox().x + xLvlOffset - 600)) + " , "
                        + (player.getHitbox().y + (player.getHitbox().y + yLvlOffset - 400)));
    }

    @Override
    public void draw(Graphics g) {
        int aniIndex = player.getAniIndex();
        levelManager.drawBehind(g, xLvlOffset, yLvlOffset);
        player.render(g, xLvlOffset, yLvlOffset);
        levelManager.drawFront(g, xLvlOffset, yLvlOffset);
        g.drawImage(crosshair, mousePosX - (crosshairSize + aniIndex) / 2, mousePosY - (crosshairSize + aniIndex) / 2,
                crosshairSize + aniIndex,
                crosshairSize + aniIndex, null);// cross hair
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                getRealPos(e.getX(), e.getY());
                break;
            case MouseEvent.BUTTON3:

                break;

            default:
                break;
        }
    }

    private void getRealPos(int x, int y) {
        System.out.println();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosX = e.getX();
        mousePosY = e.getY();
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
                player.resetDirection();
                Gamestate.state = Gamestate.PAUSE;
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

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosX = e.getX();
        mousePosY = e.getY();
    }

}
