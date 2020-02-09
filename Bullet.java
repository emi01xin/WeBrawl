// FIGHT GAME
// Sheng Fang, Emily Wang, Grace Tsai

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

class Bullet{
	
	private ArrayList<Rectangle> bRight= new ArrayList<Rectangle>();
	private ArrayList<Rectangle> bLeft = new ArrayList<Rectangle>();
	
	Image brPic, blPic;
	
	GamePanel game;
	
	public Bullet(GamePanel game){               
		this.game = game;
		
		brPic = new ImageIcon("right bullet.png").getImage();
		brPic = brPic.getScaledInstance(75, 50, Image.SCALE_SMOOTH);
		
		blPic = new ImageIcon("left bullet.png").getImage();
		blPic = blPic.getScaledInstance(75, 50, Image.SCALE_SMOOTH);
	}
	
	public void createBulletsR(int x, int y){   
		bRight.add(new Rectangle(x, y, 5, 5));
	}
	
	public void createBulletsL(int x, int y){
		bLeft.add(new Rectangle(x, y, 5, 5));
	}
	
	public ArrayList <Rectangle> getBRight(){  
    	return bRight;
	}
	
	public ArrayList <Rectangle> getBLeft(){
    	return bLeft;
	}

	public void shoot(Rectangle enemyRect, Player playerChar, Player enemyChar){   
		int SHOOT = 1;    								//position of attack in the array of attacks 
		
		Attack shootAttk = (playerChar.getAttacks()).get(SHOOT);
		
		// SHOOT RIGHT
		for(int i = bRight.size()-1; i >= 0; i--){      //increases the x coordinate of the array of bullets			
			Rectangle br = bRight.get(i);
			br.x += 15;
			
			// COLLIDE
			if(br.x > game.getWidth()){              	//removes the bullet after it goes offscreen	
				bRight.remove(br);
			}
				
			else if(br.intersects(enemyRect)){			//removes the bullet upon intersection
				bRight.remove(br);
				enemyChar.HP -= shootAttk.dmg;
			}
			
		}
		
		// SHOOT LEFT
		for(int i = bLeft.size()-1; i >= 0; i--){		
			Rectangle bl = bLeft.get(i);
			
			bl.x -= 15;
			
			// COLLIDE
			if(bl.x < 0){
				bLeft.remove(bl);
			}
			
			else if(bLeft.get(i).intersects(enemyRect)){
				bLeft.remove(bl);
				enemyChar.HP -= shootAttk.dmg;
			}
		}
	}
	
	public void drawBullets(Graphics g){					
		
		// DRAW RIGHT BULLETS
		for (Rectangle rec : bRight){
	 		g.drawImage(brPic, rec.x, rec.y - 25, game);
		}
		// DRAW LEFT BULLETS
		for (Rectangle rec : bLeft){
	 		g.drawImage(blPic, rec.x, rec.y - 25, game);
		}
	}
}