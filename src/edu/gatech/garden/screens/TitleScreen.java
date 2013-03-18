package edu.gatech.garden.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

import edu.gatech.garden.gui.BigButton;
import edu.gatech.garden.main.Garden;

/**
 * @author Glenn
 * @version $Revision: 1.0 $
 */
public class TitleScreen extends Screen {
    private final BigButton newGame, quit;
    private static final ImageIcon BG = 
			new ImageIcon(TitleScreen.class.getResource("/edu/gatech/garden/res/bg.png"));

    /**
     * Constructor for TitleScreen.
     * 
     * @param panel GamePanel
     * @param WIDTH int
     * @param HEIGHT int
     */
    public TitleScreen() {
        newGame = new BigButton("New Game", WIDTH / 2, 200);
        quit = new BigButton("Exit Game", WIDTH / 2, 200 + newGame.getHeight());
    }

    /**
     * Method draw.
     * 
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g) {
        BG.paintIcon(null, g, 0, 0);
        newGame.draw(g);
        quit.draw(g);
        Font default_font = g.getFont();
        g.setFont(new Font("serif", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Garden State Group", WIDTH - 160, 20);
		g.setFont(default_font);
    }

    /**
     * Method checkForClick.
     * 
     * @param point Point
     */
    @Override
    public void checkForClick(Point point) {
        if (newGame.isClicked(point)) {
        	Garden.PANEL.setActiveScreen(new GameScreen());
        } else if (quit.isClicked(point)) {
            System.exit(0);
        }
    }
    
    /**
     * Method setHoverPoint.
     * 
     * @param p Point
     */
    public void setHoverPoint(Point p) {
        this.hoverPoint = p;
        newGame.setHovered(p);
        quit.setHovered(p);
    }
}
