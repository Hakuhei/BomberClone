package tiles;

import graphics.Screen;
import graphics.Sprite;

public class SteelTile extends Tile{

	public SteelTile(Sprite sprite){
		super(sprite);
		removed = false;
	}
	
	// Jet fuel can't melt steel beams
	public boolean breakable(){
		return false;
	}
	
	// A steel tile cannot be passed through
	public boolean solid(){
		return true;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 6, y << 6, this);
	}
}
