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

    public static Coordinate GetTextAtCenter(Graphics g, String text, int yOffset) {
        FontMetrics fm = g.getFontMetrics();
        int maxWidth = Config.SCREEN_WIDTH;
        int y = yOffset + fm.getAscent();

        // Split the text into lines
        String[] lines = text.split("\n");

        for (String line : lines) {
            int textWidth = fm.stringWidth(line);
            int x = (maxWidth - textWidth) / 2;

            // Draw the line
            g.drawString(line, x, y);

            // Move to the next line
            y += fm.getHeight();
        }

        return new Coordinate(0, y); // Return the final y-coordinate
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

    public static int GetPercentX(double percent) {
        int x = (int) (Config.SCREEN_WIDTH * (percent / 100));
        return x;
    }

    public static int GetPercentY(double percent) {
        int y = (int) (Config.SCREEN_HEIGHT * (percent / 100));
        return y;
    }

}