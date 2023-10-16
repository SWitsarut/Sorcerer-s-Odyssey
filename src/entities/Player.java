package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import Levels.Level;
import effect.EffectManager;
import gamestates.Gamestate;
import helperClass.Coordinate;
import main.sound.Sound;
import main.sound.SoundEffect;
import util.LoadSave;

import static util.Constants.PlayerConstants.*;
import static util.Constants.Config.*;
import static util.Helper.*;

public class Player extends Entity {

    // no clip
    private boolean noclip = true;

    private EffectManager effectManager;

    private BufferedImage[][] animation;
    private Level collisionMap;
    private float xDrawOffset = (float) (10 * SCALE);
    private float yDrawOffset = (float) (16 * SCALE);
    private Sound footStepSound[] = new Sound[10];
    private Random random = new Random(System.nanoTime());
    private long lastFootstepPlayed = 0;
    public int hitboxXcenter;
    public int hitboxYcenter;

    private int aniTick = 0, aniIndex, aniFramePersecond = ANIMATION_FRAME_PERSECOND;

    private boolean facingLeft = false;

    private int palyerAction = IDLE;
    private double speed = 5;

    private boolean up, down, right, left;

    private double hp, mp;
    private double maxHp = 200, maxMp = 100;

    private double mpRegen;
    private double hpRegen;

    private double mpRegenMul = 1;
    private double hpRegenMul = 1;
    private double mpRegenMulDefault = 1;
    private double hpRegenMulDefault = 1;

    private int curSpellgap, maxSpellGap = (int) (0.1 * UPS_SET);
    private boolean castSpellable = true;

    private boolean iFraming = false;
    private int curIFrameTick = 0, maxIFrameTick = (int) (0.5 * UPS_SET);

    public void resetDirection() {
        up = down = right = left = false;
    }

    public Player(float x, float y, EffectManager effectManager) {
        super(x, y, CHAR_SIZE, CHAR_SIZE);
        loadAnimation();
        loadSound();
        initHitbox(x, y, (float) (10 * SCALE), (float) (16 * SCALE));
        hitboxXcenter = (int) (hitbox.x + hitbox.width / 2);
        hitboxYcenter = (int) (hitbox.y + hitbox.height / 2);
        this.effectManager = effectManager;
        hp = maxHp;
        mp = maxMp;
    }

    public void getAttacked(double damage) {
        if (!iFraming) {
            hp -= damage;
            curIFrameTick = 0;
            iFraming = true;
            effectManager.playAttacked((int) hitbox.x + hitboxXcenter, (int) hitbox.y +
                    hitboxYcenter);
        }
    }

    private void loadSound() {
        for (int i = 0; i < 10; i++) {
            footStepSound[i] = new SoundEffect("general/wav/footstep0" + i + ".wav", 45);
            footStepSound[i].setVolume(10);
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniFramePersecond) {
            aniTick = 0;
            aniIndex = (aniIndex + 1) % 10;
        }
    }

    public void update() {
        updateStatus();
        move();
    }

    private void updateStatus() {
        regen();
        SpellCastGap();
        switch (palyerAction) {
            case WALKING:
                playFootStepSound();
                break;
            default:
                break;
        }
        if (iFraming) {
            if (curIFrameTick >= maxIFrameTick) {
                iFraming = false;
                curIFrameTick = 0;
            } else {
                curIFrameTick++;
            }
        }
    }

    private void SpellCastGap() {
        if (!castSpellable) {
            if (curSpellgap >= maxSpellGap) {
                curSpellgap = 0;
                castSpellable = true;
            }
            curSpellgap++;
        }
    }

    public boolean castSpell(double cost) {
        if (castSpellable) {
            if (mp >= cost) {
                mp -= cost;
                castSpellable = false;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void regen() {
        mpRegen = 0.5 * mpRegenMul;
        hpRegen = 0.05 * hpRegenMul;
        if (hp < maxHp) {
            hp = Math.min(hp + hpRegen, maxHp);
        }
        if (mp < maxMp) {
            mp = Math.min(mp + mpRegen, maxMp);
        }
    }

    private void playFootStepSound() {
        long curtime = System.currentTimeMillis();
        if (curtime - lastFootstepPlayed > 300) {
            footStepSound[random.nextInt(10)].play();
            lastFootstepPlayed = curtime;
        }
    }

    private Coordinate getStandingTile() {
        float centerX = hitbox.x + (hitbox.width / 2);
        float centerY = hitbox.y + (hitbox.height / 2);

        int tileX = (int) (centerX / TILE_SIZE); // Assuming TILE_SIZE is the size of a map tile
        int tileY = (int) (centerY / TILE_SIZE);

        Coordinate standingTile = new Coordinate(tileX, tileY);

        return standingTile;
    }

    public void render(Graphics g, int xLvlOffset, int yLvlOffset) {
        Coordinate playerOnScreen = getPlayerOnScreen(xLvlOffset, yLvlOffset);
        if (Gamestate.state == Gamestate.PLAYING) {
            updateAnimationTick();
        }
        if (noclip) {
            g.setColor(Color.WHITE);
            g.drawString("no clip enable", 0, SCREEN_HEIGHT);
        }
        if (facingLeft) {
            g.drawImage(animation[palyerAction][aniIndex], (int) ((hitbox.x - xDrawOffset) - xLvlOffset + CHAR_SIZE),
                    (int) (hitbox.y - yDrawOffset) - yLvlOffset,
                    CHAR_SIZE * -1,
                    CHAR_SIZE,
                    null);
        } else {
            g.drawImage(animation[palyerAction][aniIndex], (int) playerOnScreen.x,
                    (int) playerOnScreen.y,
                    CHAR_SIZE,
                    CHAR_SIZE,
                    null);
        }
        drawHitbox(g, xLvlOffset, yLvlOffset);
    }

    public Coordinate getPlayerOnScreen(int xLvlOffset, int yLvlOffset) {
        int playerScreenX = (int) (hitbox.x - xDrawOffset) - xLvlOffset;
        int playerScreenY = (int) (hitbox.y - yDrawOffset) - yLvlOffset;

        Coordinate playerScreenPosition = new Coordinate(playerScreenX, playerScreenY);
        return playerScreenPosition;
    }

    private void loadAnimation() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animation = new BufferedImage[5][10];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                animation[i][j] = img.getSubimage(CHAR_DEFAULT_SIZE * j, CHAR_DEFAULT_SIZE * i, CHAR_DEFAULT_SIZE,
                        CHAR_DEFAULT_SIZE);
            }
        }
    }

    public void loadCollision(Level collision) {
        this.collisionMap = collision;
    }

    private void move() {
        if (up || right || left || down) {

            float xSpeed = 0, ySpeed = 0;
            setPalyerAction(WALKING);
            if (left && !right) {
                xSpeed -= speed;
                facingLeft = true;
            } else if (right && !left) {
                xSpeed += speed;
                facingLeft = false;
            }

            if (up && !down) {
                ySpeed -= speed;
            } else if (down && !up) {
                ySpeed += speed;
            }
            if (!noclip) {
                if (CanMoveHere((hitbox.x + xSpeed), (hitbox.y + ySpeed), hitbox.width,
                        hitbox.height,
                        collisionMap.getData())) {
                    hitbox.x += xSpeed;
                    hitbox.y += ySpeed;
                }
            } else {
                hitbox.x += xSpeed;
                hitbox.y += ySpeed;
            }
            // if (!unWalkable(hitbox.x + xSpeed + hitbox.width, hitbox.y + hitbox.height,
            // collisionMap.getData())) {
            // hitbox.x += xSpeed;
            // }
            // if (!unWalkable(hitbox.x + hitbox.width, hitbox.y + hitbox.height + ySpeed,
            // collisionMap.getData())) {
            // hitbox.y += ySpeed;
            // }
        } else {
            setPalyerAction(IDLE);
        }
    }

    public Coordinate calMapCoordinate() {
        Coordinate coor = new Coordinate((int) (hitbox.x + xDrawOffset), (int) (hitbox.y + yDrawOffset));
        // System.out.println("coor " + coor);
        return coor;
    }

    public void newAction(int action) {
        aniTick = 0;
        aniIndex = 0;
        setPalyerAction(action);
    }

    public void setPalyerAction(int palyerAction) {
        this.palyerAction = palyerAction;
    }

    public Coordinate getPlayerCenter() {
        Coordinate playerCoor = new Coordinate((int) (hitbox.x + hitboxXcenter), (int) (hitbox.y + hitboxYcenter));
        return playerCoor;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setPosition(Coordinate coor) {
        hitbox.x = coor.x;
        hitbox.y = coor.y;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public boolean getNoclip() {
        return noclip;
    }

    public void toggleNoClip() {
        this.noclip = !noclip;
    }

    public double getHp() {
        return hp;
    }

    public double getMp() {
        return mp;
    }

    public double getMaxHp() {
        return maxHp;
    }

    public double getMaxMp() {
        return maxMp;
    }

    public EffectManager getEffectManager() {
        return effectManager;
    }

    public BufferedImage[][] getAnimation() {
        return animation;
    }

    public Level getCollisionMap() {
        return collisionMap;
    }

    public float getxDrawOffset() {
        return xDrawOffset;
    }

    public float getyDrawOffset() {
        return yDrawOffset;
    }

    public boolean isCastable() {
        return castSpellable;
    }

}