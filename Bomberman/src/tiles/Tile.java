package tiles;


import graphics.Screen;
import graphics.Sprite;


public class Tile {
	
	public Sprite sprite;
	protected boolean removed;
	
	
	// 3 types of tiles: steel, rock, and grass
	public static Tile steelTile = new SteelTile(Sprite.steel); 
	public static Tile rockTile = new RockTile(Sprite.rock);
	public static Tile grassTile = new GrassTile(Sprite.grass);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public boolean solid() {
		return false;
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean breakable() {
		return false;
	}
	
	public void remove() {
		
	}
	
	public boolean isRemove() {
		return removed;
	}
	
}
