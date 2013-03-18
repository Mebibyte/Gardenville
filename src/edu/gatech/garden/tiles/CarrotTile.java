package edu.gatech.garden.tiles;

import javax.swing.ImageIcon;

public class CarrotTile extends CropTile {
	private static final ImageIcon 
			SPRITE_PLANTED = new ImageIcon(CarrotTile.class.getResource("/edu/gatech/garden/res/sprites/carrotPlanted.png")),
			SPRITE_WATERED = new ImageIcon(CarrotTile.class.getResource("/edu/gatech/garden/res/sprites/carrotWatered.png")),
			SPRITE_GROWN = new ImageIcon(CarrotTile.class.getResource("/edu/gatech/garden/res/sprites/carrotGrown.png"));
		
	public CarrotTile(int x, int y) {
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
		return 25;
	}
	
	public int getStamina() {
		return 2;
	}
}