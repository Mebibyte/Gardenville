package edu.gatech.garden.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


/**
 * @version 1.0
 * @since 1.0
 * @author Glenn
 */
public class BigButton {
	private static final ImageIcon 
		BUTTON = new ImageIcon(BigButton.class.getResource("/edu/gatech/garden/res/button.png")),
		BUTTONHOVERED = new ImageIcon(BigButton.class.getResource("/edu/gatech/garden/res/buttonHovered.png"));
	
	private final String text;
	private final int buttonWidth, buttonHeight;
	private final int x, y;
	private static final int FONTSIZE = 25, FONTY = 3;
	private final Rectangle bounds;
	private boolean hovered;
	
	/**
	 * Constructor for BigButton.
	 * @param text String
	 * @param x int
	 * @param y int
	 */
	public BigButton(String text, int x, int y) {
		this.text = text;
        buttonWidth = BUTTON.getIconWidth();
        buttonHeight = BUTTON.getIconHeight();
        this.x = x - (buttonWidth >> 1);
        this.y = y - (buttonHeight >> 1);
        bounds = new Rectangle(this.x, this.y, buttonWidth, buttonHeight);
	}
	
	/**
	 * Method draw.
	 * @param g Graphics
	 * @param panel GamePanel
	 * @param width int
	 * @param height int
	 */
	public void draw(Graphics g){
		if (hovered) {
		    BUTTONHOVERED.paintIcon(null, g, x, y);
		} else {
			BUTTON.paintIcon(null, g, x, y);
		}
		g.setColor(Color.WHITE);
		final Font default_font = g.getFont();
        g.setFont(new Font("serif", Font.PLAIN, FONTSIZE));
		g.drawString(text, 
				x + (buttonWidth >> 1) - (((g.getFontMetrics()).stringWidth(text)) >> 1), 
				y + (buttonHeight >> 1) + FONTY);
		g.setFont(default_font);
		g.setColor(Color.BLACK);
	}
	
	/**
	 * Method isClicked.
	 * @param point Point
	 * @return boolean
	 */
	public boolean isClicked(Point p){
		return bounds.contains(p);
	}
	
	/**
	 * Method isIn.
	 * @param p Point
	 * @return boolean
	 */
	public boolean isIn(Point p){
	    if (p == null) return false;
		return bounds.contains(p);
	}
	
	/**
	 * Method getHeight.
	 * @return int
	 */
	public int getHeight(){
		return buttonHeight;
	}

	/**
     * Method setHovered.
     * @param p Point
     */
    public void setHovered(Point p) {
        if (bounds.contains(p)) {
            hovered = true;
        } else hovered = false;
    }

    /**
     * Method getWidth.
     * Get's the width of the button image.
     * @return int width.
     */
    public static int getWidth() {
        return BUTTON.getIconWidth();
    }
}
