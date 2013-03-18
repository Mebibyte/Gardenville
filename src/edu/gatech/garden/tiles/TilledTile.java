package edu.gatech.garden.tiles;

import javax.swing.ImageIcon;

public class TilledTile extends Tile {
	private static final ImageIcon SPRITE = 
			new ImageIcon(CornTile.class.getResource("/edu/gatech/garden/res/sprites/tilled.png"));
	
	public TilledTile(int x, int y) {
		super(x, y);
		this.sprite = SPRITE;
	}

}
