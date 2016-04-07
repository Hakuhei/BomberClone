/**
 * 
 */
package testing;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import main.Game;
import tiles.Tile;

import org.junit.AfterClass;
import org.junit.Test;

import arena.Arena;
import entity.Player;

/**
 * @author Jay
 *
 */
public class GameTesting {

	@Test
	public void test() {
		
		// Game should not be running when player is dead
		Game game = new Game();
		game.start();
		
		Arena arena = game.getArena();
		int[] tiles = arena.getTiles();
		int width = arena.getWidth();
		
		// Checks collisions
		for (int x = 0; x < 15; x++){
			for (int y = 0; y < 9; y++){
				game.player = new Player(x,y,game.getInput(),arena,1);
				assertEquals(game.player.tileCollision(0,0),arena.getTile(x, y).solid());
			}
		}
		
		// Goes through all tiles and checks if they match given arena
		for (int x = 0; x < 15; x++){
			for (int y = 0; y < 9; y++){
				if (tiles[x+y*width] ==  0xFF000000){
					assertEquals(arena.getTile(x,y),Tile.steelTile);
				} else if (tiles[x+y*width] ==  0xFF00FF00) {
					assertEquals(arena.getTile(x,y),Tile.rockTile);
				} else if (tiles[x+y*width] ==  0xFFFFFFFF){
					assertEquals(arena.getTile(x,y),Tile.grassTile);
				}
			}
		}
		
		// Checks broken brick states
		for (int x = 0; x < 15; x++){
			for (int y = 0; y < 9; y++){
				arena.rockX.add(x);
				arena.rockY.add(y);
				assertEquals(arena.getTile(x, y),Tile.grassTile);
			}
		}
		
		game.player.setPlayerAlive(false);
		game.update();
		assertEquals(game.getRunning(),false);
		
		game = new Game();
		game.start();
		game.player2.setPlayerAlive(false);
		game.update();
		assertEquals(game.getRunning(),false);
		
		// Game should be running when player is alive
		game = new Game();
		game.start();
		assertEquals(game.getRunning(),true);
		
		
		// Game should be running when timer is > 0
		for (int i = 180; i > 0; i--){
		game.start();
		game.setGameTimer(180);
		game.update();
		assertEquals(game.getRunning(),true);
		game.stop();
		}
		
		// Game should be not running when timer = 0
		game.start();
		game.setGameTimer(0);
		game.update();
		assertEquals(game.getRunning(),false);
		
	}

}
