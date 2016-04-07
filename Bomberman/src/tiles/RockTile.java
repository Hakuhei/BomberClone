package tiles;

import graphics.Screen;
import graphics.Sprite;

public class RockTile extends Tile{
	
	public RockTile(Sprite sprite){
		super(sprite);
	}
	
	// Rock tiles can be destroyed by bombs
	public boolean breakable(){
		return true;
	}
	
	// A rock tile cannot be passed through
	public boolean solid(){
		return true;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 6, y << 6, this);
	}
}
