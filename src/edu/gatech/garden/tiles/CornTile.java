package edu.gatech.garden.tiles;

import javax.swing.ImageIcon;

public class CornTile extends CropTile {
	private static final ImageIcon 
			SPRITE_PLANTED = new ImageIcon(CornTile.class.getResource("/edu/gatech/garden/res/sprites/cornPlanted.png")),
			SPRITE_WATERED = new ImageIcon(CornTile.class.getResource("/edu/gatech/garden/res/sprites/cornWatered.png")),
			SPRITE_GROWN = new ImageIcon(CornTile.class.getResource("/edu/gatech/garden/res/sprites/cornGrown.png"));
		
	public CornTile(int x, int y) {
		super(x, y);
		sprite = SPRITE_PLANTED;
	}
	
	public void water() {
		super.water();
		sprite = SPRITE_WATERED;
	}
	
	public void advance() {
		if (isWatered()) sprite = SPRITE_GROWN;
	}
	
	public boolean isGrown() {
		return sprite == SPRITE_GROWN;
	}
	
	public int getScore() {
		return 10;
	}
	
	public int getStamina() {
		return 1;
	}
}