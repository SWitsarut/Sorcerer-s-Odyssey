package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import Inventory.Inventory;
import Levels.Level;
import effect.EffectManager;
import gamestates.Gamestate;
import helperClass.Coordinate;
import main.sound.Sound;
import main.sound.SoundEffect;
import util.Helper;
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

    private Inventory inv;

    public Inventory getInv() {
        return inv;
    }

    private int aniTick = 0, aniIndex, aniFramePersecond = ANIMATION_FRAME_PERSECOND;

    private boolean facingLeft = false;

    private int palyerAction = IDLE;
    public double speed = 5;
    public static double speedDefault = 5;

    private boolean up, down, right, left;

    public boolean interacting = false;
    public boolean interactBlock = false;
    public int curInteractingDelay = 0;
    public int maxInteractingDelay = (int) (0.5 * UPDATE_PER_TIME);

    public void interactDelayUpdate() {
        if (interactBlock) {
            interacting = false;
            curInteractingDelay++;
            if (curInteractingDelay >= maxInteractingDelay) {
                interactBlock = false;
                curInteractingDelay = 0;
            }
        }
    }

    private double hp, mp;
    private double maxHp = 175, maxMp = 150;

    private double mpRegen;
    private double hpRegen;

    public double mpRegenMulDefault = 1.25;
    public double hpRegenMulDefault = 1;
    public double mpRegenMul = mpRegenMulDefault;
    public double hpRegenMul = hpRegenMulDefault;

    public static double dmgMulDefault = 1;
    public static double dmgMul = dmgMulDefault;

    public int curSpellgap, maxSpellGap = (int) (0.2 * UPS_SET);
    private boolean castSpellable = true;

    public int curAttackgap, maxAttackGap = (int) (0.3 * UPS_SET);
    private boolean attackable = true;

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
        this.inv = new Inventory();
        hp = maxHp;
        mp = maxMp;
    }

    public void getAttacked(double damage) {
        if (!iFraming) {
            hp -= damage;
            curIFrameTick = 0;
            iFraming = true;
            effectManager.playAttacked((int) hitbox.x + hitboxXcenter, (int) hitbox.y + hitboxYcenter, true);
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
//        System.out.println(Helper.getTileFromPos(getPlayerCenter()) + " | x:" + hitbox.x + " y:" + hitbox.y);
        // System.out.println(hitbox);
        updateStatus();
        move();
    }

    private void updateStatus() {
        regen();
        SpellCastGap();
        AttackGap();
        interactDelayUpdate();
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
            curSpellgap++;
            if (curSpellgap >= maxSpellGap) {
                curSpellgap = 0;
                castSpellable = true;
            }
        }
    }

    private void AttackGap() {
        if (!attackable) {
            curAttackgap++;
            if (curAttackgap >= maxAttackGap) {
                curAttackgap = 0;
                attackable = true;
            }
        }
    }

    public boolean canAttack() {
        if (attackable) {
            attackable = false;
            return true;
        } else {
            return false;
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

    public void heal(double amount) {
        hp = Math.min(hp + amount, maxHp);
    }

    private void playFootStepSound() {
        long curtime = System.currentTimeMillis();
        if (curtime - lastFootstepPlayed > 300) {
            footStepSound[random.nextInt(10)].play();
            lastFootstepPlayed = curtime;
        }
    }

    public void render(Graphics g, int xLvlOffset, int yLvlOffset) {
        Coordinate playerOnScreen = getPlayerOnScreen(xLvlOffset, yLvlOffset);
        if (Gamestate.state == Gamestate.PLAYING) {
            updateAnimationTick();
        }
        if (noclip) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Consolas", Font.PLAIN, 15));
            g.drawString("no clip enable", 0, SCREEN_HEIGHT);
        }
        if (facingLeft) {
            g.drawImage(animation[palyerAction][aniIndex], (int) ((hitbox.x - xDrawOffset) - xLvlOffset + CHAR_SIZE), (int) (hitbox.y - yDrawOffset) - yLvlOffset, CHAR_SIZE * -1, CHAR_SIZE, null);
        } else {
            g.drawImage(animation[palyerAction][aniIndex], (int) playerOnScreen.x, (int) playerOnScreen.y, CHAR_SIZE, CHAR_SIZE, null);
        }
        // drawHitbox(g, xLvlOffset, yLvlOffset);
    }

    public Coordinate getPlayerOnScreen(int xLvlOffset, int yLvlOffset) {
        int playerScreenX = (int) (hitbox.x - xDrawOffset) - xLvlOffset;
        int playerScreenY = (int) (hitbox.y - yDrawOffset) - yLvlOffset;

        return new Coordinate(playerScreenX, playerScreenY);
    }

    private void loadAnimation() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animation = new BufferedImage[5][10];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                animation[i][j] = img.getSubimage(CHAR_DEFAULT_SIZE * j, 5 * CHAR_DEFAULT_SIZE + CHAR_DEFAULT_SIZE * i, CHAR_DEFAULT_SIZE, CHAR_DEFAULT_SIZE);
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
                xSpeed -= (float) speed;
                facingLeft = true;
            } else if (right && !left) {
                xSpeed += (float) speed;
                facingLeft = false;
            }

            if (up && !down) {
                ySpeed -= (float) speed;
            } else if (down && !up) {
                ySpeed += (float) speed;
            }
            if (!noclip) {
                if (CanMoveHere((hitbox.x + xSpeed), (hitbox.y + ySpeed), hitbox.width, hitbox.height, collisionMap.getData())) {
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


    public void setPalyerAction(int palyerAction) {
        this.palyerAction = palyerAction;
    }

    public Coordinate getPlayerCenter() {
        return new Coordinate((int) (hitbox.x + hitboxXcenter), (int) (hitbox.y + hitboxYcenter));
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

    public boolean isCastable() {
        return castSpellable;
    }

}