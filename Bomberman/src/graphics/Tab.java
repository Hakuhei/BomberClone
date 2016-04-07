package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tab {
	
	public final int HEIGHT;
	public final int WIDTH;
	private String path;
	public int[] pixels;
	
	public static Tab controlsTab = new Tab(960,150, "/textures/tabs/BombermanControls.png");
	public static Tab player1WinTab = new Tab(960,150, "/textures/tabs/Player1Win.png");
	public static Tab player2WinTab = new Tab(960,150, "/textures/tabs/Player2Win.png");
	public static Tab drawGameTab = new Tab(960,150, "/textures/tabs/DrawGame.png");
	
	public Tab(int height, int width, String path) {
		this.HEIGHT = height;
		this.WIDTH = width;
		this.path = path;
		pixels = new int[height * width];
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
