package edu.gatech.garden.tiles;

import java.util.Random;

import javax.swing.ImageIcon;

public class GrassTile extends Tile {
	private static final Random rand = new Random();
	private final ImageIcon SPRITE = 
			new ImageIcon(GrassTile.class.getResource("/edu/gatech/garden/res/sprites/grass" + rand.nextInt(4) + ".png"));

	public GrassTile(int x, int y) {
		super(x, y);
		this.sprite = SPRITE;
	}
}
