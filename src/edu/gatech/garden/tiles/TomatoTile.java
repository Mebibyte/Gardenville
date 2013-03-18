package edu.gatech.garden.tiles;

import javax.swing.ImageIcon;

public class TomatoTile extends CropTile {
	private static final ImageIcon 
			SPRITE_PLANTED = new ImageIcon(TomatoTile.class.getResource("/edu/gatech/garden/res/sprites/tomatoPlanted.png")),
			SPRITE_WATERED = new ImageIcon(TomatoTile.class.getResource("/edu/gatech/garden/res/sprites/tomatoWatered.png")),
			SPRITE_GROWN = new ImageIcon(TomatoTile.class.getResource("/edu/gatech/garden/res/sprites/tomatoGrown.png"));
		
	public TomatoTile(int x, int y) {
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
		return 45;
	}
	
	public int getStamina() {
		return 3;
	}
}