package arena;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import bomb.Bomb;
import entity.Flame;
import graphics.Screen;
import tiles.Tile;

public class Arena {

	private String path;
	protected int[] tiles;
	protected int width, height;
	public int creeps;
	
	// Level keeps tracks of bombs, broken rocks, and flames from the bombs
	public static List<Bomb> bomb;
	public static List<Flame> flame;
	public static List<Integer> rockX;
	public static List<Integer> rockY;
	
	public Arena(String path) {
		bomb = new ArrayList<Bomb>();
		flame = new ArrayList<Flame>();
		rockX = new ArrayList<Integer>();
		rockY = new ArrayList<Integer>();
		this.path = path;
		loadArena();
	}

	public void loadArena() {
		try {
			BufferedImage image = ImageIO.read(Arena.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Screen screen) {		
		int x1 = (screen.width + 64) >> 6;
		int y1 = (screen.height + 64) >> 6;
		for (int y = 0; y < y1; y++) {
			for (int x = 0; x < x1; x++) {
				if (x >= 15 || y > 8)break;
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for (int i = 0; i < bomb.size(); i++) {
			bomb.get(i).update();
			bomb.get(i).render(screen);
		}
		
		for (int i = 0; i < Arena.flame.size(); i++) {
			Arena.flame.get(i).update();
			Arena.flame.get(i).render(screen);
		}

	}

	public Tile getTile(int x, int y) {
		Tile t = Tile.grassTile;
		// The border is filled with unbreakable steel tiles
		if (x >= width || x < 0 || y < 0 || y >= height) t = Tile.steelTile;
		
		// Game detects which tile is which through the png file given in the path
		else if (tiles[x + y * width] == 0xFF000000) t = Tile.steelTile;
		else if (tiles[x + y * width] == 0xFF00FF00) t = Tile.rockTile;
		else if (tiles[x + y * width] == 0xFFFFFFFF) t = Tile.grassTile;
	
		// Broken rock tiles are passed as grass tiles
		for(int yy = 0; yy < rockY.size(); yy++) {
			if(rockX.get(yy) == x && rockY.get(yy) == y) {
				return Tile.grassTile;
			}
		}
		
		return t;
	}

	public void addBomb(Bomb bomb) {
		this.bomb.add(bomb);
	}

	public void addFlame(Flame flame) {
		this.flame.add(flame);
	}
	
	public void addBrokenRocks(int x, int y) {
		rockX.add(x);
		rockY.add(y);
	}

	public int[] getTiles(){
		return tiles;
	}
	
	public int getWidth(){
		return width;
	}
}
