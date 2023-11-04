package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import util.LoadSave;
import util.Ui;

public class SkillHud {

    private String[][] skill = new String[4][3];
    private Color[] elementColors = new Color[4];

    private int Size = 100;
    private Font font;
    private FontMetrics fm;

    private int yHudStart = util.Constants.Config.SCREEN_HEIGHT - Ui.GetPercentY(3) - Size;
    private int xHudStart = Ui.GetPercentX(3);

    public SkillHud() {
        font = LoadSave.GetFont(18);

        elementColors[Magic.Magic.Fire] = new Color(234, 98, 98);
        elementColors[Magic.Magic.Arcane] = new Color(122, 9, 250);
        elementColors[Magic.Magic.Holy] = new Color(167, 147, 0);
        elementColors[Magic.Magic.Lightning] = new Color(0, 152, 220);

        skill[Magic.Magic.Fire][0] = "Fire Ball";
        skill[Magic.Magic.Fire][1] = "Fire Breath";
        skill[Magic.Magic.Fire][2] = "Rage";

        skill[Magic.Magic.Arcane][0] = "Blaster";
        skill[Magic.Magic.Arcane][1] = "Machine gun";
        skill[Magic.Magic.Arcane][2] = "Surge";

        skill[Magic.Magic.Lightning][0] = "Bolt!";
        skill[Magic.Magic.Lightning][1] = "Strike!";
        skill[Magic.Magic.Lightning][2] = "Haste!";

        skill[Magic.Magic.Holy][0] = "Divine orb";
        skill[Magic.Magic.Holy][1] = "Divine Aids";
        skill[Magic.Magic.Holy][2] = "Regeneration";

    }

    public void draw(Graphics g) {

        g.setColor(Color.GRAY);
        for (int i = 0; i < 3; i++) {
            g.drawRect(xHudStart + (i * Size), yHudStart, Size, Size);
        }

        g.setColor(elementColors[Magic.Magic.selectedElement]);
        g.fillRect(xHudStart + (Size * Magic.Magic.selectedChoice), yHudStart, Size, Size);

        g.setColor(Color.WHITE);
        g.setFont(font);
        fm = g.getFontMetrics(font); // Moved this line to get accurate font metrics

        for (int i = 0; i < 3; i++) {
            String skillName = skill[Magic.Magic.selectedElement][i];
            int textWidth = fm.stringWidth(skillName); // Calculate the width of the text

            // Calculate X coordinate to center the text in the skill box
            int xCentered = xHudStart + (i * Size) + (Size - textWidth) / 2;

            // Calculate Y coordinate to vertically center the text in the skill box
            int yCentered = yHudStart + (Size / 2) + fm.getAscent() / 2;

            g.drawString(skillName, xCentered, yCentered);
        }
    }

}
