package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import helperClass.Coordinate;
import main.Game;
import main.Sound;
import util.LoadSave;
import util.Constants.Config;
import util.Ui;

public class Menu extends State implements Statemethods {
    Font font;
    FontMetrics fm;
    int selectedChoice = 0;
    ArrayList<String> text = new ArrayList<>();
    int maxChoice;
    Sound titleMusic;
    boolean musicPlaying = true;

    private void initClasses() {
        text.add("START");
        text.add("OPTION");
        text.add("CREDIT");
        text.add("EXIT");
        maxChoice = text.size();
        titleMusic = new Sound("song/title.wav");

    }

    private void submit() {
        switch (selectedChoice) {
            case 0:
                titleMusic.stop();
                titleMusic.close();
                Gamestate.state = Gamestate.PLAYING;
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    private void circleChoice(int amount) {
        if (amount == -1 && selectedChoice == 0) {
            selectedChoice = maxChoice;
        }
        selectedChoice = (selectedChoice + amount) % maxChoice;
    }

    public Menu(Game game) {
        super(game);
        font = LoadSave.GetFont(40);
        initClasses();

        // TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        if (Gamestate.MENU == Gamestate.state) {
            titleMusic.play();
        }
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Point start = new Point(0, 0);
        Point end = new Point(Config.SCREEN_WIDTH, 0);
        GradientPaint gradientPaint = new GradientPaint(start, Color.RED, end, Color.BLACK);
        g2d.setPaint(gradientPaint);
        g2d.fill(new Rectangle2D.Double(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));

        g.setFont(font);
        fm = g.getFontMetrics(font);
        g.setColor(Color.WHITE);
        int fontHeight = fm.getHeight();
        Coordinate[] coordinates = Ui.DrawArrayLineMiddle(g, text);
        g.drawLine(Ui.GetPercentX(20), (int) (coordinates[selectedChoice].y - fontHeight), Ui.GetPercentX(80),
                (int) (coordinates[selectedChoice].y - fontHeight));
        g.drawLine(Ui.GetPercentX(20), (int) (coordinates[selectedChoice].y + 7.5), Ui.GetPercentX(80),
                (int) (coordinates[selectedChoice].y + 7.5));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        submit();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Gamestate.state = Gamestate.PLAYING;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                circleChoice(1);
                break;
            case KeyEvent.VK_S:
                circleChoice(1);
                break;
            case KeyEvent.VK_W:
                circleChoice(-1);
                break;
            case KeyEvent.VK_UP:
                circleChoice(-1);
                break;
            case KeyEvent.VK_ENTER:
                submit();
                break;
            case KeyEvent.VK_E:
                submit();
                break;
            case KeyEvent.VK_SPACE:
                submit();
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
