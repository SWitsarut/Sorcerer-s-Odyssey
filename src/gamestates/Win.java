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
import main.sound.SoundEffect;
import util.Constants.Config;
import util.Constants.SoundFile;
import util.LoadSave;
import util.Ui;

public class Win extends State implements Statemethods {

    private Playing playing;

    public Win(Game game) {
        super(game);
        playing = game.getPlaying();
        initClasses();
    }

    private Font font;
    private FontMetrics fm;
    private int selectedChoice = 0;
    private ArrayList<String> text = new ArrayList<>();
    private SoundEffect nextEffect;
    private SoundEffect submitEffect;
    private Coordinate[] coordinates;

    private void initClasses() {
        font = LoadSave.GetFont(40);
        text.add("YOU WIN!!!");
        text.add("Press F to Continue");
        nextEffect = new SoundEffect(SoundFile.NEXT_EFFECT, 80);
        submitEffect = new SoundEffect(SoundFile.SUBMIT_EFFECT, 80);
        nextEffect.setVolume(60);
        submitEffect.setVolume(60);

    }

    private void submit() {
        submitEffect.play();
        playing.initClass();
        Gamestate.state = Gamestate.MENU;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Point start = new Point(Config.SCREEN_WIDTH / 2, 0);
        Point end = new Point(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT);
        GradientPaint gradientPaint = new GradientPaint(start, new Color(255, 255, 255, 0), end, Color.BLACK);
        g2d.setPaint(gradientPaint);
        g2d.fill(new Rectangle2D.Double(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));

        g.setFont(font);
        fm = g.getFontMetrics(font);
        g.setColor(Color.WHITE);
        int fontHeight = fm.getHeight();
        coordinates = Ui.DrawArrayLineMiddle(g, text);
        g.drawLine(Ui.GetPercentX(20), (int) (coordinates[selectedChoice].y - fontHeight), Ui.GetPercentX(80), (int) (coordinates[selectedChoice].y - fontHeight));
        g.drawLine(Ui.GetPercentX(20), (int) (coordinates[selectedChoice].y + 7.5), Ui.GetPercentX(80), (int) (coordinates[selectedChoice].y + 7.5));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F) {
            submit();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
