package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Levels.Level;
import util.LoadSave;

import static util.Constants.PlayerConstants.*;
import static util.Constants.Config.*;
import static util.Helper.*;

public class Player extends Entity {
    private BufferedImage[][] animation;
    private Level collisionMap;
    private float xDrawOffset = 20;
    private float yDrawOffset = 10;

    private int aniTick = 0, aniIndex, aniFramePersecond = ANIMATION_FRAME_PERSECOND;

    private boolean facingLeft = false;

    private int palyerAction = IDLE;
    private double speed = 2;

    private boolean up, down, right, left;

    public void resetDirection() {
        up = down = right = left = false;
    }

    private void calculateFacing(int clickPos) {
        if (clickPos < SCREEN_WIDTH / 2) {
            facingLeft = true;
        } else {
            facingLeft = false;
        }
    }

    public void attack(int clickPos) {
        calculateFacing(clickPos);
        newAction(ATTACK);
        aniIndex = 4;
    }

    public void useMagic(int clickPos) {
        calculateFacing(clickPos);
        newAction(GESTURE);
        aniIndex = 3;
    }

    public Player(float x, float y) {
        super(x, y);
        loadAnimation();
        initHitbox((int) x, (int) y);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniFramePersecond) {
            aniTick = 0;
            aniIndex = (aniIndex + 1) % 10;
        }
    }

    public void update() {
        // updateHitbox();
        move();
        updateAnimationTick();
    }

    public void render(Graphics g) {
        // drawHitbox(g);
        if (facingLeft) {
            g.drawImage(animation[palyerAction][aniIndex], (int) x + CHAR_SIZE, (int) y, CHAR_SIZE * -1, CHAR_SIZE,
                    null);
        } else {
            g.drawImage(animation[palyerAction][aniIndex], (int) x, (int) y, CHAR_SIZE, CHAR_SIZE,
                    null);
        }
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
            if (CanMoveHere(x + xSpeed, y + ySpeed, collisionMap.getData())) {
                this.x += xSpeed;
                this.y += ySpeed;
            }
        } else {
            setPalyerAction(IDLE);
        }
    }

    public void newAction(int action) {
        aniTick = 0;
        aniIndex = 0;
        setPalyerAction(action);
    }

    public void setPalyerAction(int palyerAction) {
        this.palyerAction = palyerAction;
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

}