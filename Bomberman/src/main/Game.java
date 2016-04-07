package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import entity.Player;

import arena.Arena;
import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;
import graphics.Tab;
import input.Keyboard;


public class Game extends Canvas implements Runnable {

	public static Game game;
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private int width = 960, height = 690;
	private Dimension size;
	public String title = "Bomberman";
	private Thread thread;
	private boolean running = false;
	
	Random random = new Random();
	
	private Screen screen;
	
	// 2 Players 
	private Keyboard input;
	private Keyboard input2;
	public Player player;
	public Player player2;
	private Arena arena;
	public int playerTurn = 1;
	public int player2Turn = -1;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	// How long each game is
	private long gameTimer;
	
	// State of the game
	private boolean endGame = false;
	
	public Game() {
		size = new Dimension(width, height);
		setPreferredSize(size);
		
		frame = new JFrame();
		screen = new Screen(width, height);
		input = new Keyboard(1);
		input2= new Keyboard(2);
		
		// Preps the arena
		setArena();

		addKeyListener(input);
		addKeyListener(input2);
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				update();
				updates++;
				render();
				frames++;
				delta--;
			}
			if((System.currentTimeMillis() - timer) > 1000) {
				frame.setTitle(title + " |  UPS " + updates + " FPS " + frames +  " | Timer: "+ gameTimer);
				frames = 0;
				updates = 0;
				timer += 1000;
				gameTimer--;
				
				// Resets game
				if (input.reset == true){
					reset();
				}
			}
		}
	}
	
	// Will continue to update the game as long as both players are alive
	// or the game will end when the timer reaches 0, counting down.
	public void update() {	
		if (gameTimer >= 0){
			if(player.isAlive && player2.isAlive){
				input.update();
				input2.update();
				
				player.update();
				player2.update();
			
			// Win Screens
			} else if (player2.isAlive == false){
				endGame = true;
				screen.renderTab(Tab.player1WinTab);
				endUpdate();
			} else if (player.isAlive ==  false){
				endGame = true;
				screen.renderTab(Tab.player2WinTab);
				endUpdate();
			} 
		} else {
			endGame = true;
			screen.renderTab(Tab.drawGameTab);
			endUpdate();
		}
	}
	
	public void endUpdate(){
		render();
		input.update();
	}
	
	public void render() {
		
		// Triple buffering strategy
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		// Renders on the screen
		arena.render(screen);
		player.render(screen);
		player2.render(screen);
		
		if (endGame == false){
			screen.renderTab(Tab.controlsTab);
		}
		
		// Actually renders on the game pixels
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	private void setArena() {
		// Sets timer length
		gameTimer = 180;
		
		endGame = false;
		// Random arena each new game
		arena = new Arena("/textures/arena/Arena" + (random.nextInt(6)+1) + ".png");
		// Player 1 spawns on the top left corner
		player = new Player(1, 1, input, arena,1);
		
		// Player 2 spawns on the bottom right corner
		player2 = new Player(13, 7, input2, arena,2);
	}

	public static void main(String[] args) {
		game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(game.title + " | ");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		Sprite.initSprites();
		
		game.start();
	}
		
	private void reset(){
		setArena();
	}
	public boolean getRunning(){
		return running;
	}
	
	public void setGameTimer(int seconds){
		gameTimer = seconds;
	}
	
	public Arena getArena(){
		return arena;
	}
	
	public Keyboard getInput(){
		return input;
	}
}
