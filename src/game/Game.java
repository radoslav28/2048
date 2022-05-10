package game;

import gui.GuiScreen;
import gui.HelpPanel;
import gui.MainMenuPanel;
import gui.PlayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class Game extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 600;
    public static final Font main = new Font("Georgia", Font.PLAIN, 28);
    private Thread game;
    private boolean running;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
    private GuiScreen screen;

    public Game() {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);


        screen = GuiScreen.getInstance();
        screen.add("Menu", new MainMenuPanel());
        screen.add("Play", new PlayPanel());
        screen.add("Help", new HelpPanel());
        screen.setCurrentPanel("Menu");
    }

    private void update() {
        screen.update();
        Keyboard.update();

    }

    private void render() {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,WIDTH,HEIGHT);
        screen.render(g);


        g.dispose();

        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.drawImage(image,0,0,null);
        g2d.dispose();
    }

    @Override
    public void run() {
        int fps = 0;
        int updates = 0;
        double nsPerUpdate = 100000000.0 / 60;

        double then = System.nanoTime();
        double unprocessed = 0;

        while (running) {

            boolean shouldRender = false;
            double now = System.nanoTime();
            unprocessed += (now - then) / nsPerUpdate;
            then = now;

            while (unprocessed >= 1) {
                updates++;
                update();
                unprocessed--;
                shouldRender = true;
            }

            if (shouldRender) {
                fps++;
                render();
                shouldRender = false;
            }
            else {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void start() {
        if(running) return;
        running = true;
        game = new Thread(this,"game");
        game.start();
    }

    public synchronized void stop () {
        if(!running) return;
        running = false;
        System.exit(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Keyboard.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Keyboard.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        screen.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        screen.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        screen.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        screen.mouseMoved(e);
    }
}
