package gui;

import game.DrawUtils;
import game.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends GuiPanel{

    private Font titleFont = Game.main.deriveFont(100f);
    private String title = "2048";
    private int buttonWidth = 220;
    private int buttonHeight = 60;
    private int spacing = 90;

    public MainMenuPanel() {
        super();
        GuiButton playButton = new GuiButton(Game.WIDTH / 2 - buttonWidth / 2, 220, buttonWidth, buttonHeight);
        GuiButton helpButton = new GuiButton(Game.WIDTH / 2 - buttonWidth / 2, playButton.getY() + spacing, buttonWidth, buttonHeight);
        GuiButton quitButton = new GuiButton(Game.WIDTH / 2 - buttonWidth / 2, helpButton.getY() + spacing, buttonWidth, buttonHeight);

        playButton.setText("Play");
        helpButton.setText("Help");
        quitButton.setText("Quit");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                GuiScreen.getInstance().setCurrentPanel("Play");
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiScreen.getInstance().setCurrentPanel("Help");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(playButton);
        add(helpButton);
        add(quitButton);
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.setFont(titleFont);
        g.setColor(Color.black);
        g.drawString(title, Game.WIDTH / 2 - DrawUtils.getMessageWidth(title, titleFont, g) / 2, 150);

    }
}

