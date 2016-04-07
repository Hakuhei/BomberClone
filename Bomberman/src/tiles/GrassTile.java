package tiles;

import graphics.Screen;
import graphics.Sprite;

public class GrassTile extends Tile{

	public GrassTile(Sprite sprite){
		super(sprite);
		removed = false;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 6, y << 6, this);
	}
}
