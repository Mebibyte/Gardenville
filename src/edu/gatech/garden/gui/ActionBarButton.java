package edu.gatech.garden.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import edu.gatech.garden.screens.GameScreen.Act;

public class ActionBarButton {
	private int x, y = 711, action;
	private Act act;
	private Rectangle bounds;
	private static final ImageIcon[] imgs = new ImageIcon[]{
		new ImageIcon(ActionBarButton.class.getResource("/edu/gatech/garden/res/sprites/hoe.png")),
		new ImageIcon(ActionBarButton.class.getResource("/edu/gatech/garden/res/sprites/corn.png")),
		new ImageIcon(ActionBarButton.class.getResource("/edu/gatech/garden/res/sprites/carrot.png")),
		new ImageIcon(ActionBarButton.class.getResource("/edu/gatech/garden/res/sprites/tomato.png")),
		new ImageIcon(ActionBarButton.class.getResource("/edu/gatech/garden/res/sprites/water.png")),
		new ImageIcon(ActionBarButton.class.getResource("/edu/gatech/garden/res/sprites/pick.png"))};
	
	public ActionBarButton(int action) {
		this.action = action;
		act = Act.values()[action];
		x = 200 + ((action - 1) * 34);
		bounds = new Rectangle(x, y, 32, 32);
	}
	
	public void draw(Graphics g) {
		imgs[action - 1].paintIcon(null, g, x, y);
	}
	
	public boolean isClicked(Point p) {
		return bounds.contains(p);
	}

	public Act getAct() {
		return act;
	}

	public boolean checkButton(char keyChar) {
		return (keyChar - 48) == action;
	}
}
