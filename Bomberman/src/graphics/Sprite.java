package graphics;

public class Sprite {

	private int x, y;
	public final int SIZE;
	private SpriteSheet sheet;
	public int[] pixels;




	
	public static Sprite steel = new Sprite(64, 0, 0, SpriteSheet.mainSheet);
	public static Sprite rock = new Sprite(64, 1, 0, SpriteSheet.mainSheet);
	public static Sprite grass = new Sprite(64, 2, 0, SpriteSheet.mainSheet);

	public static Sprite bomb_1 = new Sprite(64, 8, 1, SpriteSheet.mainSheet);
	public static Sprite bomb_2 = new Sprite(64, 8, 2, SpriteSheet.mainSheet);
	public static Sprite bomb_3 = new Sprite(64, 8, 3, SpriteSheet.mainSheet);

	public static Sprite flame_1 = new Sprite(64, 0, 4, SpriteSheet.mainSheet);
	public static Sprite flame_2 = new Sprite(64, 1, 4, SpriteSheet.mainSheet);
	public static Sprite flame_3 = new Sprite(64, 2, 4, SpriteSheet.mainSheet);
	public static Sprite flame_4 = new Sprite(64, 3, 4, SpriteSheet.mainSheet);
	public static Sprite flame_5 = new Sprite(64, 4, 4, SpriteSheet.mainSheet);

	// Player and bomb sprites
	public static Sprite playerUp[] = new Sprite[8];
	public static Sprite playerDown[] = new Sprite[8];
	public static Sprite playerSide[] = new Sprite[8];
	public static Sprite bomb[] = new Sprite[3];
	public static Sprite flame[] = new Sprite[5];
	
	// Player 2
	public static Sprite playerUp2[] = new Sprite[8];
	public static Sprite playerDown2[] = new Sprite[8];
	public static Sprite playerSide2[] = new Sprite[8];
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		pixels = new int[SIZE * SIZE];
		load();
	}

	public void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
	
	// Initializes the sprites
	public static void initSprites(){
		for (int i = 0; i < playerUp.length; i++){
			playerDown[i] = new Sprite(64,i,1,SpriteSheet.mainSheet);
			playerDown2[i] = new Sprite(64,i,5,SpriteSheet.mainSheet);
		}
		
		for (int i = 0; i < playerDown.length; i++){
			playerUp[i] = new Sprite(64,i,2,SpriteSheet.mainSheet);
			playerUp2[i] = new Sprite(64,i,6,SpriteSheet.mainSheet);
		}
		
		for (int i = 0; i < playerSide.length; i++){
			playerSide[i] = new Sprite(64,i,3,SpriteSheet.mainSheet);
			playerSide2[i] = new Sprite(64,i,7,SpriteSheet.mainSheet);
		}
		
		for (int i = 0; i < bomb.length; i++){
			bomb[i] = new Sprite(64,8,i+1,SpriteSheet.mainSheet);
		}
	}

}
