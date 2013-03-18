package edu.gatech.garden.tiles;

public abstract class CropTile extends Tile {
	private boolean watered, grown;
	
	public CropTile(int x, int y) {
		super(x, y);
	}
	
	public boolean isGrown() {
		return grown;
	}

	public boolean isWatered() {
		return watered;
	}
	
	public void water() {
		watered = true;
	}
	
	public abstract int getScore();
	
	public abstract int getStamina();
}
