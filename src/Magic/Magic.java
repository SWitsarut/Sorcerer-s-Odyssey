package Magic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import entities.Player;
import entities.Projectile.Projectile;
import gamestates.Playing;
import helperClass.Coordinate;
import main.sound.SoundEffect;
import util.LoadSave;

public class Magic {

    public static final int Fire = 0;
    public static final int Arcane = 1;
    public static final int Lightning = 2;
    public static final int Holy = 3;

    public static int selectedElement = Magic.Fire;
    public static int selectedChoice = 0;

    private Player player;
    public ArrayList<Projectile> projectiles = new ArrayList<>();
    public ArrayList<Buff> buffs = new ArrayList<>();

    private BufferedImage lightningAni[];

    private SoundEffect cyclSoundEffect[] = new SoundEffect[2];
    private Random random = new Random(System.nanoTime());

    public Magic(Playing playing) {
        this.player = playing.getPlayer();
        lightningAni = LoadSave.LinearAnimationLoader("asset/effect/lightning.png", 32);
        cyclSoundEffect[0] = new SoundEffect("general/wav/handleSmallLeather.wav", 80);
        cyclSoundEffect[1] = new SoundEffect("general/wav/handleSmallLeather2.wav", 80);
    }

    public void cast(Coordinate targetCoor) {
        switch (selectedElement) {
            case Fire:
                switch (selectedChoice) {
                    case 0:
                        castFireBall(targetCoor);
                        break;
                }
                break;
            case Arcane:
                switch (selectedChoice) {
                    case 0:
                        castArcaneBullets(targetCoor);
                        break;
                }
                break;
            case Lightning:
                switch (selectedChoice) {
                    case 0:
                        // castLightningBuff();
                        castBolt(targetCoor);
                        break;
                }
                break;
            case Holy:
                switch (selectedChoice) {
                    case 0:
                        castRegeneration();
                        // castSmite(targetCoor);

                        break;
                }
                break;

        }
    }

    public void update() {
        for (int i = 0; i < buffs.size(); i++) {
            Buff buff = buffs.get(i);
            if (buff.isActive()) {
                buffs.get(i).update();
            } else {
                buffs.remove(i);
                i--;
            }
        }

        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            if (!projectile.isActive()) {
                projectiles.remove(i);
                i--;
            } else {
                projectile.update();
            }

        }
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Buff buff : buffs) {
            buff.draw(g, xLvlOffset, yLvlOffset);
        }
        for (Projectile projectile : projectiles) {
            projectile.draw(g, xLvlOffset, yLvlOffset);
        }
    }

    public void cycleLeft() {
        selectedElement = (selectedElement + 1) % 4;
        cyclSoundEffect[random.nextInt(2)].play();

    }

    public void cycleRight() {
        if (selectedElement - 1 == -1) {
            selectedElement = 3;
        } else {
            selectedElement = selectedElement - 1;
        }
        cyclSoundEffect[random.nextInt(2)].play();
    }

    public void castFireBall(Coordinate targetCoor) {
        if (player.castSpell(FireBall.cost)) {

            projectiles.add(new FireBall(player.getPlayerCenter(), targetCoor));

        }
    }

    public void castBolt(Coordinate targetCoor) {
        if (player.castSpell(Bolt.cost)) {
            projectiles.add(new Bolt(player.getPlayerCenter(), targetCoor));
        }
    }

    public void castLightningBuff() {
        if (player.castSpell(LightningBuff.cost)) {
            buffs.add(new LightningBuff(lightningAni, player));
        }
    }

    public void castSmite(Coordinate targetCoor) {
        if (player.castSpell(Bolt.cost)) {

            projectiles.add(new Smite(player.getPlayerCenter(), targetCoor));
        }
    }

    public void castRegeneration() {
        if (player.castSpell(Regeneration.cost)) {
            buffs.add(new Regeneration(player));
        }
    }

    public void castArcaneBullets(Coordinate targetCoor) {
        if (player.castSpell(ArcaneBullet.cost)) {
            Coordinate left = new Coordinate(targetCoor.x - 20, targetCoor.y - 50);
            Coordinate right = new Coordinate(targetCoor.x - 20, targetCoor.y + 50);
            projectiles.add(new ArcaneBullet(player.getPlayerCenter(), left));
            projectiles.add(new ArcaneBullet(player.getPlayerCenter(), targetCoor));
            projectiles.add(new ArcaneBullet(player.getPlayerCenter(), right));

        }
    }

}
