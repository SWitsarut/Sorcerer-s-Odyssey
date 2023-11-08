package util;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import helperClass.Coordinate;
import util.Constants.Config;

public class Ui {
    public static Coordinate GetTextMiddleScreen(Graphics g, String text) {
        FontMetrics fm = g.getFontMetrics();
        int x = (Config.SCREEN_WIDTH - fm.stringWidth(text)) / 2;
        int y = (Config.SCREEN_HEIGHT - fm.getHeight()) / 2 + fm.getAscent();

        return new Coordinate(x, y);
    }

    public static Coordinate DrawTextMiddleScreen(Graphics g, String text) {
        Coordinate coor = GetTextMiddleScreen(g, text);
        g.drawString(text, coor.x, coor.y);
        return coor;
    }

    public static Coordinate GetTextNextLine(Graphics g, String text, int Offset) {
        FontMetrics fm = g.getFontMetrics();
        int x = (Config.SCREEN_WIDTH - fm.stringWidth(text)) / 2;
        int y = Offset + fm.getAscent() + 10;

        return new Coordinate(x, y);
    }

    public static Coordinate DrawTextNextLine(Graphics g, String text, int Offset) {
        Coordinate coor = GetTextNextLine(g, text, Offset);
        g.drawString(text, coor.x, coor.y);
        return coor;
    }

    public static Coordinate[] DrawArrayLineMiddle(Graphics g, ArrayList<String> text) {
        ArrayList<Coordinate> coor = new ArrayList<>();
        coor.add(DrawTextMiddleScreen(g, text.get(0)));

        for (int i = 1; i < text.size(); i++) {
            coor.add(DrawTextNextLine(g, text.get(i), coor.get(i - 1).y));
        }

        return coor.toArray(new Coordinate[0]);
    }

    public static int GetPercentX(double percent) {
        int x = (int) (Config.SCREEN_WIDTH * (percent / 100));
        return x;
    }

    public static int GetPercentY(double percent) {
        int y = (int) (Config.SCREEN_HEIGHT * (percent / 100));
        return y;
    }

}