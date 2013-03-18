package edu.gatech.garden.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

import edu.gatech.garden.gui.BigButton;
import edu.gatech.garden.main.Garden;

public class GameEndScreen extends Screen {
    private final BigButton menu;
	private static final ImageIcon BG = 
			new ImageIcon(GameEndScreen.class.getResource("/edu/gatech/garden/res/end_bg.png"));
	private int score;
	
	public GameEndScreen(int score) {
		this.score = score;
        menu = new BigButton("Menu", WIDTH / 2, 325);
	}
	
	@Override
	public void draw(Graphics g) {
		BG.paintIcon(null, g, 0, 0);
		menu.draw(g);
		final Font default_font = g.getFont();
        g.setFont(new Font("serif", Font.PLAIN, 25));
		g.drawString("Score: " + score, 
				(WIDTH / 2) - ((g.getFontMetrics().stringWidth("Score: " + score)) / 2), 
				230);
		g.drawString("Thanks for Playing!", 
				(WIDTH / 2) - ((g.getFontMetrics().stringWidth("Thanks for Playing!")) / 2), 
				260);
		g.setFont(default_font);
		g.setColor(Color.BLACK);
	}

	 /**
     * Method checkForClick.
     * 
     * @param point Point
     */
    @Override
    public void checkForClick(Point point) {
        if (menu.isClicked(point)) {
        	Garden.PANEL.setActiveScreen(new TitleScreen());
        }
    }
	
	@Override
	public void setHoverPoint(Point p) {
		this.hoverPoint = p;
		menu.setHovered(p);
	}
}
