package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Levels.LevelManager;
import Magic.Magic;
import effect.EffectManager;
import entities.Player;
import entities.Enemy.EnemyManager;
import event.EventManager;
import helperClass.Coordinate;
import interactable.InteractableManager;
import main.Game;
import ui.Hud;
import util.LoadSave;
import util.Constants.Config;

public class Playing extends State implements Statemethods {

    private LevelManager levelManager;

    public LevelManager getLevelManager() {
        return levelManager;
    }

    private Player player;
    private EnemyManager enemyManager;
    private EventManager eventManager;

    private InteractableManager interactableManager;

    public EventManager geteventManager() {
        return eventManager;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    private EffectManager effectManager;
    private Hud hud;
    private Magic magic;

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

    private boolean m1pressed = false;
    private boolean m3pressed = false;

    public Playing(Game game) {
        super(game);
        initClass();
    }

    public void initClass() {
        crosshair = LoadSave.GetImage("asset/crosshair007.png");
        effectManager = new EffectManager();
        player = new Player(0, 0, effectManager);
        levelManager = new LevelManager(game);
        hud = new Hud(player);
        magic = new Magic(this);
        enemyManager = new EnemyManager(this);
        eventManager = new EventManager(game);
        interactableManager = new InteractableManager(this);
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
        levelManager.levelEvents[LevelManager.curMapIndex].onEnter();
        enemyManager.initEnemy(LevelManager.curMapIndex);
        interactableManager.initPortal(LevelManager.curMapIndex);
    }

    public void windowFocusLost() {
        player.resetDirection();
    }

    public Player getPlayer() {
        return player;
    }

    public Magic getMagic() {
        return magic;
    }

    public EffectManager getEffectManager() {
        return effectManager;
    }

    @Override
    public void update() {
        player.update();
        if (m3pressed) {
            if (player.isCastable()) {
                magic.cast(getClickedPos(mousePosX, mousePosY, xLvlOffset,
                        yLvlOffset));
            }

        }
        enemyManager.update();
        effectManager.update();
        hud.update();
        magic.update();
        eventManager.update();
        checkCloseToBorder();
        interactableManager.update();
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
        interactableManager.draw(g, xLvlOffset, yLvlOffset);
        player.render(g, xLvlOffset, yLvlOffset);
        enemyManager.draw(g, xLvlOffset, yLvlOffset);
        effectManager.draw(g, xLvlOffset, yLvlOffset);
        levelManager.drawFront(g, xLvlOffset, yLvlOffset);
        magic.draw(g, xLvlOffset, yLvlOffset);
        int aniIndex = player.getAniIndex();
        g.drawImage(crosshair, mousePosX - (crosshairSize + aniIndex) / 2, mousePosY - (crosshairSize + aniIndex) / 2,
                crosshairSize + aniIndex,
                crosshairSize + aniIndex, null);// cross hair
        eventManager.draw(g, xLvlOffset, yLvlOffset);
        hud.draw(g, xLvlOffset, yLvlOffset);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                m1pressed = false;
                break;
            case MouseEvent.BUTTON3:
                m3pressed = false;
                break;
        }
    }

    private Coordinate getClickedPos(int x, int y, int xLvlOffset, int yLvlOffset) {
        Coordinate playerCoor = player.getPlayerOnScreen(xLvlOffset, yLvlOffset);
        return new Coordinate((int) (player.getHitbox().x + x - playerCoor.x),
                (int) (player.getHitbox().y + y - playerCoor.y));
    }

    @Override
    public void keyPressed(KeyEvent e) {
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
                break;
            case KeyEvent.VK_N:
                player.toggleNoClip();
                break;
            case KeyEvent.VK_Q:
                magic.cycleRight();
                break;
            case KeyEvent.VK_E:
                magic.cycleLeft();
                break;
            case KeyEvent.VK_1:
                Magic.selectedChoice = 0;
                break;
            case KeyEvent.VK_2:
                Magic.selectedChoice = 1;
                break;
            case KeyEvent.VK_3:
                Magic.selectedChoice = 2;
                break;
            case KeyEvent.VK_P:
                levelManager.nextMap();
                break;
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

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                m1pressed = false;
                break;
            case MouseEvent.BUTTON3:
                m3pressed = false;
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosX = e.getX();
        mousePosY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                m1pressed = true;
                break;
            case MouseEvent.BUTTON3:
                m3pressed = true;
                break;
        }

    }

}
