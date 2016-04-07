package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Players use the keyboard to move their character around the map
public class Keyboard implements KeyListener{
	
	// Stores all characters possible
	// Keys exist in 2 states, pressed or not pressed.
	private boolean[] keys = new boolean[65536];
	
	private int playerNum;
	public boolean up, down, left, right, placeBomb, reset;	
	
	public Keyboard(int playerNum){
		this.playerNum = playerNum;
	}
	
	public void update() {
		// Player one uses the arrow keys to move and "Enter" to bomb
		if (playerNum == 1 ){
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		placeBomb = keys[KeyEvent.VK_ENTER];
		
		// Player 2 uses WASD to move and space to "G" to bomb
		} else if (playerNum == 2){
			up =  keys[KeyEvent.VK_W];
			down = keys[KeyEvent.VK_S];
			left =  keys[KeyEvent.VK_A];
			right =  keys[KeyEvent.VK_D];
			placeBomb = keys[KeyEvent.VK_G];
		}
		
		reset = keys[KeyEvent.VK_BACK_SPACE];
	}
	
	// Detects if a key is pressed
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;	
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public void keyTyped(KeyEvent arg0) {

	}
}
