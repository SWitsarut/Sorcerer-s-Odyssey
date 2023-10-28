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
import main.sound.Sound;
import main.sound.SoundEffect;
import util.Constants.Config;
import util.Constants.SoundFile;
import util.LoadSave;
import util.Ui;

public class Menu extends State implements Statemethods {
    private Font font;
    private FontMetrics fm;
    private int selectedChoice = 0;
    private ArrayList<String> text = new ArrayList<>();
    private int maxChoice;
    private Sound titleMusic;
    private SoundEffect nextEffect;
    private SoundEffect submitEffect;
    private Coordinate[] coordinates;

    private void initClasses() {
        text.add("NEW GAME");
        text.add("OPTION");
        text.add("CREDIT");
        text.add("EXIT TO DESKTOP");
        maxChoice = text.size();
        titleMusic = new Sound("song/title.wav");
        nextEffect = new SoundEffect(SoundFile.NEXT_EFFECT, 80);
        submitEffect = new SoundEffect(SoundFile.SUBMIT_EFFECT, 80);
        titleMusic.setVolume(60);
        nextEffect.setVolume(60);
        submitEffect.setVolume(60);

    }

    private void submit() {
        submitEffect.play();
        switch (selectedChoice) {
            case 0:
                titleMusic.stop();
                titleMusic.close();
                Gamestate.state = Gamestate.PLAYING;
                break;
            case 1:
                break;
            case 2:
                titleMusic.toggleSound();
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
        coordinates = Ui.DrawArrayLineMiddle(g, text);
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
        int mouseY = e.getY();
        for (int i = 0; i < maxChoice; i++) {
            if (mouseY >= coordinates[i].y && mouseY <= coordinates[i].y + fm.getHeight() / 2) {
                // The mouse is over choice 'i'.
                selectedChoice = i;
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Gamestate.state = Gamestate.PLAYING;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                nextEffect.play();
                circleChoice(1);
                break;
            case KeyEvent.VK_S:
                nextEffect.play();
                circleChoice(1);
                break;
            case KeyEvent.VK_W:
                nextEffect.play();
                circleChoice(-1);
                break;
            case KeyEvent.VK_UP:
                nextEffect.play();
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

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
