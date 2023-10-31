package ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Magic.Magic;
import entities.Player;
import helperClass.Coordinate;
import util.Constants.Config;
import util.LoadSave;
import util.Ui;

public class Hud {

        private Coordinate elementPos = new Coordinate(Ui.GetPercentX(3), Ui.GetPercentY(3));
        private Player player;
        private int scale = 3;

        private int aniTick = 0, aniIndex, aniFramePersecond = Config.ANIMATION_FRAME_PERSECOND;
        private int oldElement = Magic.selectedElement;

        private BufferedImage[][] elementAni = new BufferedImage[4][];

        private SkillHud skillHud;

        public Hud(Player player) {
                this.player = player;
                skillHud = new SkillHud();
                BufferedImage img = LoadSave.GetImage("asset/effect/Pixel Holy Spell Effect 01.png");
                // 64 px
                elementAni[Magic.Holy] = new BufferedImage[16];
                for (int i = 0; i < 16; i++) {
                        elementAni[Magic.Holy][i] = img.getSubimage(i * 64, 64, 64, 64);
                }

                elementAni[Magic.Arcane] = new BufferedImage[16];
                for (int i = 0; i < 16; i++) {
                        elementAni[Magic.Arcane][i] = img.getSubimage(i * 64, 2 * 64, 64, 64);
                }

                elementAni[Magic.Fire] = new BufferedImage[8];
                for (int i = 0; i < 8; i++) {
                        elementAni[Magic.Fire][i] = img.getSubimage(i * 64, 4 * 64, 64, 64);
                }
                elementAni[Magic.Lightning] = new BufferedImage[8];
                for (int i = 0; i < 8; i++) {
                        elementAni[Magic.Lightning][i] = img.getSubimage(i * 64, 6 * 64, 64, 64);
                }
        }

        private void updateAnimationTick() {
                if (oldElement != Magic.selectedElement) {
                        aniTick = 0;
                        aniIndex = 0;
                }
                aniTick++;
                if (aniTick >= aniFramePersecond) {
                        aniTick = 0;
                        aniIndex = (aniIndex + 1) % (elementAni[Magic.selectedElement].length - 1);
                }
                oldElement = Magic.selectedElement;
        }

        public void update() {
                updateAnimationTick();
        }

        public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {

                Graphics2D g2d = (Graphics2D) g;

                g.setColor(new Color(255, 255, 255, 100));
                g.fillRect(Ui.GetPercentX(3) + 64 + Ui.GetPercentX(1.5), Ui.GetPercentY(3),
                                (int) (player.getMaxHp() * scale), 6 * scale);
                g.fillRect(Ui.GetPercentX(3) + 64 + Ui.GetPercentX(1.5), Ui.GetPercentY(6),
                                (int) (player.getMaxMp() * scale), 6 * scale);

                Point start = new Point((Ui.GetPercentX(3) + 150), (Ui.GetPercentY(3) + 8));
                Point end = new Point(Ui.GetPercentX(3) + 150, Ui.GetPercentY(3) + 16);
                GradientPaint gradientPaint = new GradientPaint(start, new Color(46, 182, 44), end,
                                new Color(131, 212, 117));
                g2d.setPaint(gradientPaint);
                g2d.fill(new Rectangle2D.Double(Ui.GetPercentX(3) + 64 + Ui.GetPercentX(1.5), Ui.GetPercentY(3),
                                (int) (player.getHp() * scale),
                                6 * scale));

                Point Mpstart = new Point((int) (Ui.GetPercentX(3) + player.getHp() / 2 * scale),
                                (Ui.GetPercentY(6) + 8));
                Point Mpend = new Point((int) (Ui.GetPercentX(3) + player.getHp() / 2 * scale), Ui.GetPercentY(3) + 16);

                GradientPaint MpgradientPaint = new GradientPaint(Mpstart, new Color(26, 132, 184), Mpend,
                                new Color(26, 148, 184));
                g2d.setPaint(MpgradientPaint);
                g2d.fill(new Rectangle2D.Double(Ui.GetPercentX(3) + 64 + Ui.GetPercentX(1.5), Ui.GetPercentY(6),
                                (int) (player.getMp() * scale),
                                6 * scale));

                g.setColor(Color.GREEN);
                g.drawRect(Ui.GetPercentX(3) + 64 + Ui.GetPercentX(1.5), Ui.GetPercentY(3),
                                (int) (player.getMaxHp() * scale), 6 * scale);
                g.setColor(Color.BLUE);
                g.drawRect(Ui.GetPercentX(3) + 64 + Ui.GetPercentX(1.5), Ui.GetPercentY(6),
                                (int) (player.getMaxMp() * scale), 6 * scale);

                g.setColor(new Color(0, 0, 0, 200));
                g.fillRect(elementPos.x, elementPos.y, 64, 64);
                g.setColor(Color.GRAY);
                g.drawRect(elementPos.x, elementPos.y, 64, 64);

                g.drawImage(elementAni[Magic.selectedElement][aniIndex], elementPos.x, elementPos.y, 64, 64, null);
                Coordinate playerCoor = player.getPlayerOnScreen(xLvlOffset, yLvlOffset);
                g.drawImage(elementAni[Magic.selectedElement][aniIndex],
                                playerCoor.x + 16, playerCoor.y - 20, 32, 32, null);

                // skill hud
                skillHud.draw(g);
        }
}
