package entity;

import arena.Arena;
import bomb.Bomb;
import entity.Flame;
import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;

public class Player extends Mob {
	
	private Keyboard input;
	private int anim, bombRange;
	private boolean walking;
	private int speed;
	private int flip;
	private Bomb bomb;
	private int bombsInHand;
	private boolean isDying;
	private int playerNum;
	
	public Player(int x, int y, Keyboard input, Arena arena, int playerNum) {
		this.x = x << 6;
		this.y = y << 6;
		this.input = input;
		this.playerNum = playerNum;
		anim = 0;
		walking = false;
		speed = 2;
		flip = -1;
		this.arena = arena;
		sprite = Sprite.playerDown[0];
		bombsInHand = 10;
		isAlive = true;
		bombRange = 5;
		isDying = false;
		
	}
	
	public void update() {
		int xa = 0, ya = 0;
		
		if(anim < 7500) anim++;
		else anim = 0;
		
		if(input.up) ya -= speed;
		if(input.down) ya += speed;
		if(input.left) xa -= speed;
		if(input.right) xa += speed;
		if(input.placeBomb) {
			if(bombsInHand > 0 && !checkSpot()) {
				bombsInHand--;
				plantBomb();
			}
		}
	
		if(xa != 0 || ya != 0) {
			walking = true;
			move(xa, ya);
		}else {
			walking = false;
		}
		
		clear();
		
		
		for(int i = 0; i < Arena.flame.size(); i++) {
			if(Arena.flame.get(i).flameCollision(x >> 6, y >> 6)) {
				isAlive = false;
				break;
			}
		}
	}
	
	
	public boolean checkSpot() {
		for(int i = 0; i < Arena.bomb.size(); i++) {
			if((x) >= Arena.bomb.get(i).getX() && (x) < (Arena.bomb.get(i).getX() + 64) && (y + 10) >= arena.bomb.get(i).getY() && (y + 10) < (arena.bomb.get(i).getY() + 64)) return true;
			if((x + 64) >= Arena.bomb.get(i).getX() && (x + 64) < (Arena.bomb.get(i).getX() + 64) && (y + 10) >= arena.bomb.get(i).getY() && (y + 10) < (arena.bomb.get(i).getY() + 64)) return true;
			if((x) >= Arena.bomb.get(i).getX() && (x) < (Arena.bomb.get(i).getX() + 64) && (y + 10 + 64) >= arena.bomb.get(i).getY() && (y + 10 + 64) < (arena.bomb.get(i).getY() + 64)) return true;
			if((x + 64) >= Arena.bomb.get(i).getX() && (x + 64) < (Arena.bomb.get(i).getX() + 64) && (y + 10 + 64) >= arena.bomb.get(i).getY() && (y + 10 + 64) < (arena.bomb.get(i).getY() + 64)) return true;
		}
		
		return false;
	}
	
	private void plantBomb() {
		bomb = new Bomb(((x + 20) >> 6) << 6, ((y + 20) >> 6) << 6, Sprite.bomb_1, arena, bombRange);
		arena.addBomb(bomb);
	}
	
	private void clear() {
		for(int i = 0; i < Arena.bomb.size(); i++) {
			if(Arena.bomb.get(i).removed) {
				Arena.bomb.remove(i);
				bombsInHand++;
			}
		}
		
		for(int i = 0; i < Arena.flame.size(); i++) {
			if(Arena.flame.get(i).removed) {
				Arena.flame.remove(i);
			}
		}
	}
	
	public void render(Screen screen) {
		if (playerNum == 1){
			if(dir == 0) {
				flip = -1;
				sprite = Sprite.playerUp[0];
					if(walking) {
						if(anim % 40 > 35) {
							if(isDying) isAlive = false;
							sprite = Sprite.playerUp[7];
						}else if(anim % 40 > 30) {
							sprite = Sprite.playerUp[6];
						}else if(anim % 40 > 25) {
							sprite = Sprite.playerUp[5];
						}else if(anim % 40 > 20) {
							sprite = Sprite.playerUp[4];
						}else if(anim % 40 > 15) {
							sprite = Sprite.playerUp[3];
						}else if(anim % 40 > 10) {
							sprite = Sprite.playerUp[2];
						}else {
							sprite = Sprite.playerUp[1];
						}
					}
			}else if(dir == 1) {
				flip = -1;
				sprite = Sprite.playerSide[0];
				if(walking) {
					if(anim % 40 > 35) {
						if(isDying) isAlive = false;
						sprite = Sprite.playerSide[7];
					}else if(anim % 40 > 30) {
						sprite = Sprite.playerSide[6];
					}else if(anim % 40 > 25) {
						sprite = Sprite.playerSide[5];
					}else if(anim % 40 > 20) {
						sprite = Sprite.playerSide[4];
					}else if(anim % 40 > 15) {
						sprite = Sprite.playerSide[3];
					}else if(anim % 40 > 10) {
						sprite = Sprite.playerSide[2];
					}else {
						sprite = Sprite.playerSide[1];
					}
				}
			}else if(dir == 2) {
				flip = -1;
				sprite = Sprite.playerDown[0];
				if(walking) {
					if(anim % 40 > 35) {
						if(isDying) isAlive = false;
						sprite = Sprite.playerDown[7];
					}else if(anim % 40 > 30) {
						sprite = Sprite.playerDown[6];
					}else if(anim % 40 > 25) {
						sprite = Sprite.playerDown[5];
					}else if(anim % 40 > 20) {
						sprite = Sprite.playerDown[4];
					}else if(anim % 40 > 15) {
						sprite = Sprite.playerDown[3];
					}else if(anim % 40 > 10) {
						sprite = Sprite.playerDown[2];
					}else {
						sprite = Sprite.playerDown[1];
					}
				}
			}else {
				flip = 3;
				sprite = Sprite.playerSide[0];
				if(walking) {
					if(anim % 40 > 35) {
						if(isDying) isAlive = false;
						sprite = Sprite.playerSide[7];
					}else if(anim % 40 > 30) {
						sprite = Sprite.playerSide[6];
					}else if(anim % 40 > 25) {
						sprite = Sprite.playerSide[5];
					}else if(anim % 40 > 20) {
						sprite = Sprite.playerSide[4];
					}else if(anim % 40 > 15) {
						sprite = Sprite.playerSide[3];
					}else if(anim % 40 > 10) {
						sprite = Sprite.playerSide[2];
					}else {
						sprite = Sprite.playerSide[1];
					}
				}
			}
		}
		// Player 2 Sprites animation
		else if (playerNum == 2){
			if(dir == 0) {
				flip = -1;
				sprite = Sprite.playerUp2[0];
					if(walking) {
						if(anim % 40 > 35) {
							if(isDying) isAlive = false;
							sprite = Sprite.playerUp2[7];
						}else if(anim % 40 > 30) {
							sprite = Sprite.playerUp2[6];
						}else if(anim % 40 > 25) {
							sprite = Sprite.playerUp2[5];
						}else if(anim % 40 > 20) {
							sprite = Sprite.playerUp2[4];
						}else if(anim % 40 > 15) {
							sprite = Sprite.playerUp2[3];
						}else if(anim % 40 > 10) {
							sprite = Sprite.playerUp2[2];
						}else {
							sprite = Sprite.playerUp2[1];
						}
					}
			}else if(dir == 1) {
				flip = -1;
				sprite = Sprite.playerSide2[0];
				if(walking) {
					if(anim % 40 > 35) {
						if(isDying) isAlive = false;
						sprite = Sprite.playerSide2[7];
					}else if(anim % 40 > 30) {
						sprite = Sprite.playerSide2[6];
					}else if(anim % 40 > 25) {
						sprite = Sprite.playerSide2[5];
					}else if(anim % 40 > 20) {
						sprite = Sprite.playerSide2[4];
					}else if(anim % 40 > 15) {
						sprite = Sprite.playerSide2[3];
					}else if(anim % 40 > 10) {
						sprite = Sprite.playerSide2[2];
					}else {
						sprite = Sprite.playerSide2[1];
					}
				}
			}else if(dir == 2) {
				flip = -1;
				sprite = Sprite.playerDown2[0];
				if(walking) {
					if(anim % 40 > 35) {
						if(isDying) isAlive = false;
						sprite = Sprite.playerDown2[7];
					}else if(anim % 40 > 30) {
						sprite = Sprite.playerDown2[6];
					}else if(anim % 40 > 25) {
						sprite = Sprite.playerDown2[5];
					}else if(anim % 40 > 20) {
						sprite = Sprite.playerDown2[4];
					}else if(anim % 40 > 15) {
						sprite = Sprite.playerDown2[3];
					}else if(anim % 40 > 10) {
						sprite = Sprite.playerDown2[2];
					}else {
						sprite = Sprite.playerDown2[1];
					}
				}
			}else {
				flip = 3;
				sprite = Sprite.playerSide2[0];
				if(walking) {
					if(anim % 40 > 35) {
						if(isDying) isAlive = false;
						sprite = Sprite.playerSide2[7];
					}else if(anim % 40 > 30) {
						sprite = Sprite.playerSide2[6];
					}else if(anim % 40 > 25) {
						sprite = Sprite.playerSide2[5];
					}else if(anim % 40 > 20) {
						sprite = Sprite.playerSide2[4];
					}else if(anim % 40 > 15) {
						sprite = Sprite.playerSide2[3];
					}else if(anim % 40 > 10) {
						sprite = Sprite.playerSide2[2];
					}else {
						sprite = Sprite.playerSide2[1];
					}
				}
			}
		}
		
		screen.renderPlayer(x , y, sprite, flip);
	}
	
	public boolean getPlayerAlive(){
		return isAlive;
	}
	
	public void setPlayerAlive(boolean state){
		isAlive = state;
	}

}
