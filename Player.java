// WeBrawl
// Sheng Fang, Emily Wang, Grace Tsai

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Player {
	String name, attkName, enCost, special;
	String powerUp;
  	int HP, MP, numAttk;
  	
  	ArrayList <Integer> dmgs = new ArrayList <Integer>();		// Store original values
		
  	int LIVES;
  	double VY, VX;				// velocity in y, velocity in x
  	int w, h;					// width, height
  	double a = 0;				// acceration
  	
  	int effectTime = 0;
  	boolean isEffected;
  	boolean invincibility;
  	
  	int SHIELD;
  	
  	int deaths = 0;
  	int jumps;
  	
  	int spEffectTime;
  	int poisonTime, poisonDelay;
  	
  	int jumpTime;
  	
  	boolean stun = false, disable = false, poison = false;
  	int spTime;
  
  	ArrayList <Attack> attacks;
  	Attack attk; 
  		
  	int frame = 0;     	//current frame within the move
	int action = 0;
	int animateTime = 0;
	int frameTime = 0;
	
	public static final int JUMP = 0;
	public static final int RIGHT = 1;
	public static final int LEFT = 2;
  		
    public Player(String st){
    	MP = 50;
    	
    	VY = 2;
    	VX = 5;
    	
    	LIVES = 3;
    	
    	// PLAYER STATS
    	String [] stats = st.split(",");
	    name = stats[0];
	    HP = Integer.parseInt(stats[1]);
	    w = Integer.parseInt(stats[2]);
	    h = Integer.parseInt(stats[3]);
	    numAttk = Integer.parseInt(stats[4]);
	    
	    // PLAYER ATTACKS
	    attacks = new ArrayList<Attack>();
	    
	    for(int i = 0; i < numAttk; i++){
	      String n = stats[5 + i*4]; //*3
	      int c = Integer.parseInt(stats[6 + i*4]);
	      int d = Integer.parseInt(stats[7 + i*4]);
	      String s = stats[8 + i*4];
	      
	      attacks.add(new Attack(n, c, d, s));
	    }
	    
	    //store original values
	    for(Attack attk : attacks){  
			dmgs.add(attk.dmg);
		}
  	
    }
    
    public ArrayList <Attack> getAttacks(){
    	return attacks;
	}
	
	public ArrayList <Integer> getDmgs(){
		return dmgs;
	}
	
    

    
}

