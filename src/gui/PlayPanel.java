package gui;

import game.DrawUtils;
import game.Game;
import game.GameBoard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayPanel extends GuiPanel{

    private GameBoard board;

    private GuiButton tryAgain;
    private GuiButton mainMenu;
    private int buttonWidth = 340;
    private int buttonHeight = 50;
    private boolean added;
    private Font gameOverFont;

    public PlayPanel() {
        super();
        gameOverFont = Game.main.deriveFont(70f);
        board = new GameBoard(Game.WIDTH / 2 - GameBoard.BOARD_WIDTH / 2, Game.HEIGHT - GameBoard.BOARD_HEIGHT - 20);

        mainMenu = new GuiButton(Game.WIDTH / 2 - buttonWidth / 2, 450, buttonWidth, buttonHeight);
        tryAgain = new GuiButton(mainMenu.getX(), mainMenu.getY() - buttonHeight - 20 , buttonWidth, buttonHeight);

        tryAgain.setText("Try again");
        mainMenu.setText("Back to Main Menu");


        tryAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board = new GameBoard(Game.WIDTH / 2 - GameBoard.BOARD_WIDTH / 2, Game.HEIGHT - GameBoard.BOARD_HEIGHT - 20);
                remove(tryAgain);
                remove(mainMenu);
                added = false;
            }
        });

        mainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiScreen.getInstance().setCurrentPanel("Menu");
            }
        });
    }

    public void drawGameOver (Graphics2D g) {

        g.setColor(Color.red);
        g.setFont(gameOverFont);
        g.drawString("Game Over!", Game.WIDTH / 2 - DrawUtils.getMessageWidth("Game Over!", gameOverFont, g) / 2, 70);
    }

    public void drawYouWin (Graphics2D g) {

        g.setColor(Color.blue);
        g.setFont(gameOverFont);
        g.drawString("You win!", Game.WIDTH / 2 - DrawUtils.getMessageWidth("You win!", gameOverFont, g) / 2, 70);
    }

    @Override
    public void update() {
        board.update();
    }

    @Override
    public void render(Graphics2D g) {
        board.render(g);

        if (board.isDead()) {
            if (!added) {
                added = true;
                add(tryAgain);
                add(mainMenu);
            }
            drawGameOver(g);
        }
        if (board.isWon()) {
            if (!added) {
                added = true;
                add(tryAgain);
                add(mainMenu);
            }
            drawYouWin(g);

        }
        super.render(g);
    }

}

