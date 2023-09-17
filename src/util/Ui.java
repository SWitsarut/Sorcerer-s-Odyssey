package util;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import helperClass.Coordinate;
import util.Constants.Config;

public class Ui {
    public static Coordinate GetTextMiddleScreen(Graphics g, String text) {
        FontMetrics fm = g.getFontMetrics();
        int x = (Config.SCREEN_WIDTH - fm.stringWidth(text)) / 2;
        int y = (Config.SCREEN_HEIGHT - fm.getHeight()) / 2 + fm.getAscent();

        return new Coordinate(x, y);
    }

    public static Rectangle GetRectangleMiddleScreen(int rectWidth, int rectHeight) {
        int x = (Config.SCREEN_WIDTH - rectWidth) / 2;
        int y = (Config.SCREEN_HEIGHT - rectHeight) / 2;
        
        return new Rectangle(x, y, rectWidth, rectHeight);
    }

    public static Coordinate GetTextXmiddleScreen(Graphics g, String text, int y) {
        FontMetrics fm = g.getFontMetrics();
        int x = (int) (Config.SCREEN_WIDTH - fm.stringWidth(text)) / 2;
        return new Coordinate(x, y);
    }

}
