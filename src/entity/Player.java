package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import util.LoadSave;

import static util.Constants.PlayerConstants.*;
import static util.Constants.Config.*;

public class Player extends Entity {
    private BufferedImage[][] animation;
    private int aniTick = 0, aniIndex, aniFramePersecond = 15;

    private boolean facingLeft = false;

    private int palyerAction = IDLE;
    private double speed = 1.25;

    private boolean up, down, right, left;

    public void resetDirection() {
        up = down = right = left = false;
    }

    private boolean attack, isStunned = false;

    private double deltaStunned = 0;
    private double stunnedDuration;

    private void channel(double duration) {
        this.stunnedDuration = duration * UPS_SET;
        isStunned = true;
    }

    public void stunned(double duration) {
        newAction(IDLE);
        channel(duration);
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
        aniIndex = 3;
        channel(0.3);
    }

    public void useMagic(int clickPos) {
        calculateFacing(clickPos);
        newAction(GESTURE);
        aniIndex = 3;
        channel(0.5);
    }

    public Player(float x, float y) {
        super(x, y);
        loadAnimation();
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
        updateAnimationTick();
    }

    public void render(Graphics g) {
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

    private void updateStatus() {
        if (isStunned) {
            if (deltaStunned >= stunnedDuration) {
                isStunned = false;
                deltaStunned = 0;
            } else {
                deltaStunned += 1;
            }
        }
    }

    public void move() {
        if (!isStunned) {
            if (up || right || left || down) {
                setPalyerAction(WALKING);
                if (left && !right) {
                    x -= speed;
                    facingLeft = true;
                } else if (right && !left) {
                    x += speed;
                    facingLeft = false;
                }

                if (up && !down) {
                    y -= speed;
                } else if (down && !up) {
                    y += speed;
                }
            } else {
                setPalyerAction(IDLE);
            }
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