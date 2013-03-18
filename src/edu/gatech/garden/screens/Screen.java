package edu.gatech.garden.screens;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;

import edu.gatech.garden.main.Garden;

/**
 * @author Glenn
 * @version $Revision: 1.0 $
 */
public abstract class Screen {
	protected static final int WIDTH = Garden.WIDTH, HEIGHT = Garden.HEIGHT;
	protected Point hoverPoint;
	
	/**
	 * Method draw.
	 * 
	 * @param g Graphics
	 */
	public abstract void draw(Graphics g);

	/**
	 * Method checkForClick.
	 * 
	 * @param p Point
	 * @throws IOException
	 */
	public void checkForClick(Point p){}
	
	/**
	 * Method keyTyped.
	 * 
	 * @param e KeyEvent
	 */
	public void keyTyped(KeyEvent e){}

	/**
	 * Method tick. Updates objects on the screen.
	 */
	public void tick(){}

	public abstract void setHoverPoint(Point p);
}