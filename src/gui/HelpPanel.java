package gui;

import game.DrawUtils;
import game.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpPanel extends GuiPanel{

    private int buttonWidth = 150;
    private int buttonHeight = 60;
    private int spacing = 40;
    private Font instructionsFont;

    public HelpPanel() {
        super();
        instructionsFont = Game.main.deriveFont(20f);
        GuiButton playButton = new GuiButton(spacing, 70, buttonWidth, buttonHeight);
        GuiButton quitButton = new GuiButton(Game.WIDTH - buttonWidth - spacing, 70, buttonWidth, buttonHeight);

        playButton.setText("Play");
        quitButton.setText("Quit");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                GuiScreen.getInstance().setCurrentPanel("Play");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(playButton);
        add(quitButton);

    }

    public void drawInstructions (Graphics2D g) {

        g.setColor(Color.black);
        g.setFont(instructionsFont);
        g.drawString("Use the arrow keys to combine",Game.WIDTH / 2 - DrawUtils.getMessageWidth("Use the arrow keys to combine ", instructionsFont, g) / 2, Game.HEIGHT / 2);
        g.drawString(" the same squares, combining two you get",Game.WIDTH / 2 - DrawUtils.getMessageWidth("the same squares, combining two you get", instructionsFont, g) / 2, Game.HEIGHT / 2 + instructionsFont.getSize());
        g.drawString(" a new value with the sum of their values.",Game.WIDTH / 2 - DrawUtils.getMessageWidth(" a new value with the sum of their values.", instructionsFont, g) / 2, Game.HEIGHT / 2 + instructionsFont.getSize() * 2);
        g.drawString("If you have no more moves you lose the game,",Game.WIDTH / 2 - DrawUtils.getMessageWidth("If you have no more moves you lose the game,", instructionsFont, g) / 2, Game.HEIGHT / 2 + instructionsFont.getSize() * 3);
        g.drawString(" and if you reach a value of 2048 you win.",Game.WIDTH / 2 - DrawUtils.getMessageWidth(" and if you reach a value of 2048 you win.", instructionsFont, g) / 2, Game.HEIGHT / 2 + instructionsFont.getSize() * 4);
    }

    @Override
    public void render(Graphics2D g) {
        drawInstructions(g);
        super.render(g);
    }

}

