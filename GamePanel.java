// WeBrawl
// Sheng Fang, Emily Wang, Grace Tsai

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.sound.sampled.*;

class GamePanel extends JPanel{
	private boolean [] keys;

	static ArrayList <Player> pList = new ArrayList <Player> ();
	
	ArrayList <Rectangle> plats = new ArrayList <Rectangle> ();
	ArrayList <Image> platformPics = new ArrayList <Image> ();
	ArrayList <Rectangle> spikes = new ArrayList <Rectangle> ();
	
	ArrayList <Rectangle> itemRects = new ArrayList <Rectangle> ();
	ArrayList <String> items = new ArrayList <String> ();
	
	ArrayList <ArrayList <Image>> pics1 = new ArrayList <ArrayList <Image>> ();
	ArrayList <ArrayList <Image>> pics2 = new ArrayList <ArrayList <Image>> ();
	
	ArrayList <Image> numbers = new ArrayList <Image> ();
	
	Rectangle cr1, cr2;
	Player ch1, ch2;
	Bullet b1, b2;
	
	// TIMERS
	int reload1, reload2, actTime1, actTime2, effectTime;
	
	// ITEMS
	int itemTime = 550;
	int itemVY = 6;
	
	// BULLET 
	int bv1 = 10, bv2 = 10;
	int dir1, dir2;
	
	// TIME MODE
	int time = 60;
	int timeDigit1, timeDigit2;
	int timerDelay = 100;
	boolean timeMode;
	
	// ARENA	
	String map;
	int arena;
	
	boolean hitRight;
	boolean hitLeft;
	
	boolean hitUp;
	boolean hitDown;
	
	int platDirH = 1, platDirV = 1;
	int platX1, platX2, platX3;			// store plat x & y value
	int platY1, platY2, platY3;			// store plat x & y value
	
	// SPRITE ANIMATION
	int STAND = 0, RIGHT = 1, LEFT = 2, JUMP = 3;
	int ATKR = 4, ATKL = 5, SHOOTR = 6, SHOOTL = 7, SPECIAL = 8;
	int KO = 9;
	
	// SOUND EFFECTS
	Clip jumpSfx;
	Clip shootSfx;
	Clip attackSfx;
	Clip powerUpSfx;
	Clip specialAttackSfx;
	
	Clip spaceBgm;
	Clip waterBgm;
	Clip simpleBgm;
	Clip iceBgm;
	
	int bgmNum = 0;
	
	int jumpSfxDelay1;
	int jumpSfxDelay2;
		
	int shootSfxDelay1 = 60;
	int shootSfxDelay2 = 60;
	
	// STATS
	Rectangle HPBarRect1, HPRect1, MPBarRect1, MPRect1;;
	Rectangle HPBarRect2, HPRect2, MPBarRect2, MPRect2;
	
	// IMAGES
	Image shieldPic;
	Image HPotionPic, MPotionPic, strengthPic, speedPic;
	Image back, bg1, bg2, bg3, bg4;
	Image spikePic;
	
	Image barBorderPic1, barBorderPic2, barBorderPic3, barBorderPic4;
	Image HPTxt1Pic, HPTxt2Pic, MPTxt1Pic, MPTxt2Pic;
	
	Image p1heart1, p1heart2, p1heart3;
	Image p2heart1, p2heart2, p2heart3;
	
	Image player1Icon, player2Icon;
	
	WeBrawl game;
	
    public GamePanel(WeBrawl g) {
    	keys = new boolean[KeyEvent.KEY_LAST+1];
    	
    	game = g;

    	loadPlayers();
    	
		b1 = new Bullet(this);
		b2 = new Bullet(this);
		
		// IMAGES
		// bgs
		bg1 = new ImageIcon("bg/bg1.png").getImage();		
		bg2 = new ImageIcon("bg/bg2.png").getImage();
		bg3 = new ImageIcon("bg/bg3.png").getImage();		
		bg4 = new ImageIcon("bg/bg4.png").getImage();
	
		// items				
		shieldPic = new ImageIcon("items/shield.png").getImage();
		shieldPic = shieldPic.getScaledInstance(45, 60, Image.SCALE_SMOOTH);

		HPotionPic = new ImageIcon("items/item1.png").getImage();
		HPotionPic = HPotionPic.getScaledInstance(40, 55, Image.SCALE_SMOOTH);

		MPotionPic = new ImageIcon("items/item2.png").getImage();
		MPotionPic = MPotionPic.getScaledInstance(30, 60, Image.SCALE_SMOOTH);

		strengthPic = new ImageIcon("items/item3.png").getImage();
		strengthPic = strengthPic.getScaledInstance(60, 30, Image.SCALE_SMOOTH);

        speedPic = new ImageIcon("items/item4.png").getImage();
		speedPic = speedPic.getScaledInstance(60, 45, Image.SCALE_SMOOTH);
		
		spikePic = new ImageIcon("spikes.png").getImage();
		spikePic = spikePic.getScaledInstance(50, 20, Image.SCALE_SMOOTH);
		
		// display
		barBorderPic1 = new ImageIcon("barBorder.png").getImage();
		barBorderPic1 = barBorderPic1.getScaledInstance(400, 30, Image.SCALE_SMOOTH);
		
		barBorderPic2 = new ImageIcon("barBorder.png").getImage();
		barBorderPic2 = barBorderPic2.getScaledInstance(400, 30, Image.SCALE_SMOOTH);
		
		barBorderPic3 = new ImageIcon("barBorder.png").getImage();
		barBorderPic3 = barBorderPic3.getScaledInstance(400, 30, Image.SCALE_SMOOTH);
		
		barBorderPic4 = new ImageIcon("barBorder.png").getImage();
		barBorderPic4 = barBorderPic4.getScaledInstance(400, 30, Image.SCALE_SMOOTH);
		
		HPTxt1Pic = new ImageIcon("hp.png").getImage();
		HPTxt1Pic = HPTxt1Pic.getScaledInstance(50, 30, Image.SCALE_SMOOTH);
	
		MPTxt1Pic = new ImageIcon("mp.png").getImage();
		MPTxt1Pic = MPTxt1Pic.getScaledInstance(50, 30, Image.SCALE_SMOOTH);
		
		HPTxt2Pic = new ImageIcon("hp.png").getImage();
		HPTxt2Pic = HPTxt2Pic.getScaledInstance(50, 30, Image.SCALE_SMOOTH);
	
		MPTxt2Pic = new ImageIcon("mp.png").getImage();
		MPTxt2Pic = MPTxt2Pic.getScaledInstance(50, 30, Image.SCALE_SMOOTH);
		
		p1heart1 = new ImageIcon("heart.png").getImage();
		p1heart2 = new ImageIcon("heart.png").getImage();
		p1heart3 = new ImageIcon("heart.png").getImage();
		
		p2heart1 = new ImageIcon("heart.png").getImage();
		p2heart2 = new ImageIcon("heart.png").getImage();
		p2heart3 = new ImageIcon("heart.png").getImage();
		
		// LOADING AUDIO
  		try {
        	AudioInputStream jumpSfxFile = AudioSystem.getAudioInputStream(this.getClass().getResource("jump sfx.au"));
        	AudioInputStream shootSfxFile = AudioSystem.getAudioInputStream(this.getClass().getResource("shoot sfx.au"));
        	AudioInputStream spaceBgmFile = AudioSystem.getAudioInputStream(this.getClass().getResource("space bgm.au"));
        	AudioInputStream waterBgmFile = AudioSystem.getAudioInputStream(this.getClass().getResource("water bgm.au"));
        	AudioInputStream simpleBgmFile = AudioSystem.getAudioInputStream(this.getClass().getResource("simple bgm.au"));
        	AudioInputStream attackSfxFile = AudioSystem.getAudioInputStream(this.getClass().getResource("attack sfx.au"));	
        	AudioInputStream powerUpSfxFile = AudioSystem.getAudioInputStream(this.getClass().getResource("power up sfx.au"));
        	AudioInputStream specialAttackSfxFile = AudioSystem.getAudioInputStream(this.getClass().getResource("special attack sfx.au"));
        	AudioInputStream iceBgmFile = AudioSystem.getAudioInputStream(this.getClass().getResource("ice bgm.au"));
        	
        	jumpSfx = AudioSystem.getClip();
        	jumpSfx.open(jumpSfxFile);
			
			shootSfx = AudioSystem.getClip();
			shootSfx.open(shootSfxFile);
			
			spaceBgm = AudioSystem.getClip();
			spaceBgm.open(spaceBgmFile);
			
			waterBgm = AudioSystem.getClip();
			waterBgm.open(waterBgmFile);
			
			simpleBgm = AudioSystem.getClip();
			simpleBgm.open(simpleBgmFile);
			
			attackSfx = AudioSystem.getClip();
			attackSfx.open(attackSfxFile);
			
			powerUpSfx = AudioSystem.getClip();
			powerUpSfx.open(powerUpSfxFile);
				
			specialAttackSfx = AudioSystem.getClip();
			specialAttackSfx.open(specialAttackSfxFile);
			
			iceBgm = AudioSystem.getClip();
			iceBgm.open(iceBgmFile);
		}	
			
    	catch (Exception e) {
        	e.printStackTrace();
    	}
		
    }
    
    public void setKey(int k, boolean v) {					
    	keys[k] = v;
    }
	
	public void gravity(){
		double g = 1;					// set gravity for different arenas
		
		if(map == "Simple"){
			back = bg1;					// set bg for different arenas
			g = 0.4;
		}
		else if(map == "Water"){
			back = bg2;
			g = 0.1;
		}
		else if(map == "Space"){
			back = bg3;
			g = 0.05;
		}
		else if(map == "Ice"){
			back = bg4;
			g = 0.3;
		}
		
		// PLAYER ONE
		if(collide(cr1, ch1) == false){		// gravity on players			
			cr1.y += ch1.VY;
			ch1.VY += g;
		}
		
		// PLAYER TWO
		if(collide(cr2, ch2) == false){
			cr2.y += ch2.VY;
			ch2.VY += g;
		}
		
		//ITEMS
		if(collideItems() == false){				// gravity on items
			for(Rectangle item : itemRects){
				item.y += itemVY;
				itemVY += g;
			}
		}
	}
	
    public static void loadPlayers(){					// load character txt file containing their stats
	    pList = new ArrayList <Player>();
	    
	    try{
	      Scanner inFile = new Scanner(new File("characters.txt"));
	      int n = inFile.nextInt();
	      inFile.nextLine();  
	      
	      for(int i = 0; i < n; i++){
	        String stats = inFile.nextLine();
	        pList.add(new Player(stats));
	      }
	    }
	    
	    catch(IOException ex){
	      System.out.println("File not found");
	    }
  	}
  	
  	// get sprites for player 1
  	public ArrayList <Image> makeMove1(String name, String action, int start, int end){
	    ArrayList <Image> moves = new ArrayList <Image> ();

	    for(int i = start; i < end + 1; i ++){
	    	Image move = new ImageIcon(name + "/" + name + action + "00" + i + ".png").getImage();
			moves.add(move.getScaledInstance(ch1.w, ch1.h, Image.SCALE_SMOOTH));
	    }

	    return moves;
	}
	
  	// get sprites for player 2
	public ArrayList <Image> makeMove2(String name, String action, int start, int end){
	    ArrayList <Image> moves = new ArrayList <Image> ();

	    for(int i = start; i < end + 1; i ++){
	    	Image move = new ImageIcon(name + "/" + name + action + "00" + i + ".png").getImage();
			moves.add(move.getScaledInstance(ch2.w, ch2.h, Image.SCALE_SMOOTH));
	    }

	    return moves;
	}
  	
  	public void choosePlayer1(int n){
		ch1 = pList.get(n);
	    String ch1Name = ch1.name.toLowerCase();

	   	cr1 = new Rectangle(850, 200, ch1.w, ch1.h);
	   	
	   	player1Icon = new ImageIcon(ch1Name + "/" + ch1Name + "icon.png").getImage();
		player1Icon = player1Icon.getScaledInstance(70, 70, Image.SCALE_SMOOTH);

		pics1.add(makeMove1(ch1Name, "stand", 1, 1));	// STAND
	    pics1.add(makeMove1(ch1Name, "walk", 1, 3));	// RIGHT
		pics1.add(makeMove1(ch1Name, "walk", 4, 6));	// LEFT
		pics1.add(makeMove1(ch1Name, "jump", 1, 2));	// JUMP

		pics1.add(makeMove1(ch1Name, "attack", 1, 2));	// ATTACK RIGHT
		pics1.add(makeMove1(ch1Name, "attack", 3, 4));	// ATTACK LEFT
		pics1.add(makeMove1(ch1Name, "shoot", 1, 2));	// SHOOT RIGHT
		pics1.add(makeMove1(ch1Name, "shoot", 3, 4));	// SHOOT LEFT
		pics1.add(makeMove1(ch1Name, "special", 1, 2));	// SPECIAL

		pics1.add(makeMove1(ch1Name, "ko", 2, 8));		// KO
	}

	public void choosePlayer2(int n){
	    ch2 = pList.get(n);
	    String ch2Name = ch2.name.toLowerCase();

	    cr2 = new Rectangle(50, 300, ch2.w, ch2.h);
	    
	    player2Icon = new ImageIcon(ch2Name + "/" + ch2Name + "icon.png").getImage();
		player2Icon = player2Icon.getScaledInstance(70, 70, Image.SCALE_SMOOTH);

		pics2.add(makeMove2(ch2Name, "stand", 1, 1));
	   	pics2.add(makeMove2(ch2Name, "walk", 1, 3));
		pics2.add(makeMove2(ch2Name, "walk", 4, 6));
		pics2.add(makeMove2(ch2Name, "jump", 1, 2));

		pics2.add(makeMove2(ch2Name, "attack", 1, 2));
		pics2.add(makeMove2(ch2Name, "attack", 3, 4));
		pics2.add(makeMove2(ch2Name, "shoot", 1, 2));
		pics2.add(makeMove2(ch2Name, "shoot", 3, 4));
		pics2.add(makeMove2(ch2Name, "special", 1, 2));

		pics2.add(makeMove2(ch2Name, "ko", 1, 2));

		displayStats();
	}
	
	public void displayStats(){
		HPBarRect1 = new Rectangle (1000, 30, ch1.HP * 4, 25);
		HPRect1 = new Rectangle (1000, 30, 400, 25);
		
		MPBarRect1 = new Rectangle(1000, 70, ch1.MP * 4, 25);
		MPRect1 = new Rectangle (1000, 70, 400, 25);
		
		HPBarRect2 = new Rectangle (100, 30, ch2.HP * 4, 25);
		HPRect2 = new Rectangle (100, 30, 400, 25);

		MPBarRect2 = new Rectangle(100, 70, ch2.MP * 4, 25);
		MPRect2 = new Rectangle (100, 70, 400, 25);

		for (int i = 0; i < 10; i ++){			//number animations for timer
	    	Image num = new ImageIcon("text/numbers00" + i + ".png").getImage();
	    	numbers.add(num.getScaledInstance(40, 50, Image.SCALE_SMOOTH));
	    }

	}
	
	public void playSound(int n){
		bgmNum = n;
		
		if(n == 1){
			simpleBgm.loop(Clip.LOOP_CONTINUOUSLY);
		}
		if(n == 2){
			waterBgm.loop(Clip.LOOP_CONTINUOUSLY);
		}
		if(n == 3){
			spaceBgm.loop(Clip.LOOP_CONTINUOUSLY);
		}
		if(n == 4){
			iceBgm.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	public void makeArena(int n){
		arena = n;
		
		// SIMPLE ARENA
		if (n == 1){
			plats.add(new Rectangle(100, 550, 1300, 50));		// add Rect for platforms	
			plats.add(new Rectangle(50, 300, 250, 20));
			plats.add(new Rectangle(1200, 300, 250, 20));
			
			spikes.add(new Rectangle(300, 535, 50, 20));		// add Rect for spikes
			spikes.add(new Rectangle(1100, 535, 50, 20));
			
			platX1 = plats.get(1).x;							// stored x values of platform for moving 
			platX2 = plats.get(2).x;
			
			map = "Simple";										// map name
		}
		
		// WATER ARENA
		if (n == 2){
			plats.add(new Rectangle(20, 600, 200, 30));
			plats.add(new Rectangle(200, 430, 50, 200));
			plats.add(new Rectangle(400, 430, 150, 30));
			plats.add(new Rectangle(500, 150, 160, 30));
			plats.add(new Rectangle(600, 650, 100, 30));
			
			plats.add(new Rectangle(850, 400, 160, 30));
			
			plats.add(new Rectangle(1000, 500, 80, 300));
			plats.add(new Rectangle(1200, 0, 80, 500));
			
			plats.add(new Rectangle(1300, 700, 150, 30));
			
			spikes.add(new Rectangle(410, 415, 50, 20));
			spikes.add(new Rectangle(1400, 685, 50, 20));
			
			platX1 = plats.get(3).x;
			
			map = "Water";
		}
		
		// SPACE ARENA
		if (n == 3){
			
			plats.add(new Rectangle(100, 300, 60, 60));
			plats.add(new Rectangle(50, 650, 100, 100));
			plats.add(new Rectangle(160, 470, 200, 150));
			plats.add(new Rectangle(290, 640, 30, 30));
			plats.add(new Rectangle(400, 600, 100, 100));
			plats.add(new Rectangle(550, 430, 100, 80));
			plats.add(new Rectangle(700, 200, 120, 100));
			plats.add(new Rectangle(800, 500, 130, 130));
			plats.add(new Rectangle(900, 400, 30, 30));
			plats.add(new Rectangle(1000, 300, 150, 80));
			plats.add(new Rectangle(1100, 750, 80, 80));
			plats.add(new Rectangle(1130, 680, 130, 40));
			plats.add(new Rectangle(1200, 100, 100, 100));
			plats.add(new Rectangle(1350, 500, 100, 100));
			
			spikes.add(new Rectangle(450, 585, 50, 20));
			spikes.add(new Rectangle (800, 485, 50, 20));
			spikes.add(new Rectangle (1350, 485, 50, 20));
			
			platX1 = plats.get(0).x;
			platX2 = plats.get(5).x;
			platX3 = plats.get(11).x;
			
			map = "Space";
		}
		
		// ICE ARENA
		if (n == 4){
			plats.add(new Rectangle(0, 600, 200, 200));
			plats.add(new Rectangle(400, 600, 1200, 300));
			
			plats.add(new Rectangle(600, 350, 300, 30));
			
			spikes.add(new Rectangle(1000, 585, 140, 20));
			
			platX1 = plats.get(2).x;
			
			map = "Ice";
		}
		
		for (Rectangle p : plats){		
			Image platformPic = new ImageIcon("map" + n + "platform1.png").getImage();
			platformPics.add(platformPic.getScaledInstance(p.width, p.height, Image.SCALE_SMOOTH));
		}
	}
	
	public void setMovePlats(){
		
		// SIMPLE ARENA
		if(arena == 1){
			movePlats(plats.get(1), platX1);
			movePlats(plats.get(2), platX2);
		}
		
		// WATER ARENA
		if(arena == 2){
			movePlats(plats.get(3), platX1);
		}
		
		// SPACE ARENA
		if(arena == 3){
			movePlats(plats.get(0), platX1);
			movePlats(plats.get(5), platX2);
			movePlats(plats.get(11), platX3);
		}
		
		//ICE ARENA
		if(arena == 4){
			movePlats(plats.get(2), platX1);
		}
	}
	
	public void movePlats(Rectangle r, int platX){					// if p.x reaches the boundary, change direction		
		int boundaryR = platX + r.width + 30;									
		int boundaryL = platX - r.width -30;
		
		r.x += 1 * platDirH;										// move platform by 1 left (dir = -1) or right (dir = 1)
	
		if (r.x == boundaryR || r.x == 1500 || r.x + r.width == 1500){
			hitRight = true;										
		}
		
		if (r.x == boundaryL || r.x == 0 || r.x + r.width == 0){
			hitLeft = true;
		}
	
		if (hitRight == true){
			platDirH =- 1;
			hitRight = false;
		}
		if (hitLeft == true){
			platDirH = 1;
			hitLeft = false;
		}
		
	}
	
	// Timer for timed mode
	public void timer(){
    	if (time <= 0){
    		gameOver();
    	}

    	if (timerDelay == 0 && time > 0){
    		time --;
    		timerDelay = 100;
    	}

		if (timerDelay != 0){
			timerDelay --;
		}
		
		timeDigit1 = time/10;
		timeDigit2 = time%10; 
    }
    
	public void genItems(){
		String[] itemNames = {"Invincibility", "HPotion", "MPotion", "Strength", "Speed", "Shield" };

		int x = (int)(Math.random()*1500);				// coordinates for item on screen
		int y = (int)(Math.random()*800);

		int i = 0;

		Rectangle item = new Rectangle(x, y, 40, 40);
		
		if(itemTime > 0){								// item respawn timer
			itemTime -= 1;
			System.out.println(itemTime);
		}

		if(itemTime == 0 && items.size() < 1){			// add item to list if intersects platform
			for(Rectangle p : plats){
				if(p.intersects(item)){
					
					powerUpSfx.loop(1);					// play sound effect

					item.y = p.y - (item.height - 2);	// item goes to top of platform 
					itemRects.add(item);
					
					i = (int)(Math.random()*5);			// randomly choose an item
					items.add(itemNames[i]);
				}
			}
		}
	}
	
	public boolean getItem(Rectangle r, Player ch){
		Rectangle screenRect = new Rectangle(0, 0, 1500, 800);
		
		for (int i = itemRects.size() - 1; i >= 0; i --){
			for (int j = items.size() - 1; j >= 0; j --){
				Rectangle itemRect = itemRects.get(i);
				String item = items.get(j);

				if (r.intersects(itemRect)){				// use item when player intersects Rect				
					useItem(itemRect, item, ch);			

					itemRects.remove(itemRect);				// remove item Rect from list
					items.remove(item);						// remove item String from list
					
					itemTime = 500;							// reset the timer spawn time

					return true;
				}
				
				// ITEM FALLS OFF SCREEN
				if(itemRect.intersects(screenRect) == false){// remove item if it falls off screen
					itemRects.remove(itemRect);
					items.remove(item);
					
					itemTime = 500;							// reset the timer spawn time
					return false;
				}
			}
		}
		return false;
	}
	
	public void useItem(Rectangle itemRect, String item, Player ch){
		ArrayList <Attack> attacks = ch.getAttacks();
		
		if (item.equals("HPotion")){				//increase HP
			ch.HP = Math.min(ch.HP + 50, 100);
		}

		if (item.equals("MPotion")){				//increase MP
			ch.MP = Math.min(ch.MP + 50, 50);
		}

		else{
			ch.powerUp = item;
		}
		
		// POWER UPS
		if(ch.powerUp == "Strength"  && ch.isEffected == false){			// Strength doubles the user's attk dmg
			ch.isEffected = true;											// until the effect timer runs out
			effectTime = 450;
			
			for(Attack attk : attacks){
				attk.dmg *= 2;
			}
		}
		
		if(ch.powerUp == "Speed" && ch.isEffected == false){				// Speed doubles the user's velocity
			ch.isEffected = true;											// until timer runs out
			effectTime = 450;
			
			ch.VX *= 2;
		}
		
		if(ch.powerUp == "Shield" && ch.isEffected == false){				// Shield has 4 uses to protect the user
			ch.isEffected = true;											// from enemy attacks or until the timer 
			effectTime = 450;												// runs out
			
			ch.SHIELD = 4;
		}
		
		if(ch.powerUp == "Invincibility" && ch.isEffected == false){		// User is uneffected by enemy attacks and speed 
			ch.isEffected = true;											// is increased until timer runs out
			effectTime = 450;
			
			ch.VX *= 1.5;	
			
			ch.invincibility = true;
		}
	}
	
	public void effectTimer(Player ch){
		ArrayList <Attack> attacks = ch.getAttacks();
		
		if(effectTime > 0){				// timer for item effects
			effectTime -= 1;
			
			if(map == "Ice"){			//no speed boosters for ice level
				ch.VX = 5;
			}
		}
		
		if(effectTime == 0){			// reset stats
			ch.isEffected = false;
			ch.VX = 5;
			
			ch.SHIELD = 0;
			ch.invincibility = false;
			
			for(int i = 0; i < attacks.size(); i++){
				attacks.get(i).dmg = ch.getDmgs().get(i);
			}
		}
	}
	
	public void action(){
		int MELEE = 0, SHOOT = 1, SPECIAL = 2;
		
		if (actTime1 > 0){			// time before next attack
			actTime1 -= 1;
		}
		
		if (actTime2 > 0){
			actTime2 -= 1;
		}
			
		// PLAYER ONE MELEE ATTACK 
		if(keys[KeyEvent.VK_DOWN] && actTime1 == 0){
			Attack attk1 = (ch1.getAttacks()).get(MELEE);
			
			if(cr1.intersects(cr2)){
				if(ch1.MP >= attk1.cost){		//Checks MP values - attacks if enough, no action if not
					attack(ch2, ch1, attk1);
					
					attackSfx.loop(1);			// play sound effect
					actTime1 = 20;
					
				}
			}
		}
		
		// PLAYER ONE SPECIAL ATTACK 
		else if(keys[KeyEvent.VK_X] && actTime1 == 0){
			Attack attk1SP = (ch1.getAttacks()).get(SPECIAL);
			
			if(cr1.intersects(cr2)){
				if(ch1.MP >= attk1SP.cost){	
					
					attack(ch2, ch1, attk1SP);			
					
					specialAttackSfx.loop(1);			// play sound effect
					ch2.spEffectTime = 300;				// set special effect timer tp 300
					actTime1 = 20;						// time before next attack
				}
			}
		}
		
		// PLAYER TWO MELEE ATTACK 
		if(keys[KeyEvent.VK_S] && actTime2 == 0){
			Attack attk2 = (ch2.getAttacks()).get(MELEE);
			
			if(cr2.intersects(cr1)){
				if(ch2.MP >= attk2.cost){		//Checks MP values - attacks if enough, no action if not
					attack(ch1, ch2, attk2);
					
					attackSfx.loop(1);			// play sound effect
					actTime2 = 20;
				}
			}
		}
		
		// PLAYER TWO SPECIAL ATTACK 
		else if(keys[KeyEvent.VK_E] && actTime2 == 0){
			Attack attk2SP = (ch2.getAttacks()).get(SPECIAL);
			
			if(cr2.intersects(cr1)){
				if(ch2.MP >= attk2SP.cost){		//Checks MP values - attacks if enough, no action if not
						
					attack(ch1, ch2, attk2SP);
					
					specialAttackSfx.loop(1);	// play sound effect
					ch1.spEffectTime = 300;
					actTime2 = 20;
				}
			}
		}
	}
	
	public void attack(Player foe, Player player, Attack attk){
    	
	    //ENERGY
	    player.MP = Math.max(0, player.MP - attk.cost);
	    
	    //SHIELD
	    if(foe.powerUp == "Shield" && foe.SHIELD != 0){
	     	foe.SHIELD -= 1;
	    }
	    
	    //SPECIAL ATTACKS
		
		if((attk.special).equals("stun") && foe.stun == false){
			foe.stun = true;
		}
		
		else if((attk.special).equals("disable") && foe.disable == false){
			foe.disable = true;
		}
		
		else if((attk.special).equals("poison") && foe.poison == false){
			foe.poison = true;
			
			int ranNum = (int)(Math.random()*20) + 10;			// random time for poison
			foe.poisonTime = ranNum;
		}
	    
	    //ATTACK
	    if(foe.SHIELD == 0 && foe.invincibility == false){		// foe loses HP without shield or invincibility
    		foe.HP = Math.max(0, foe.HP - attk.dmg);
	    }
	    
	}
	
	public void specialAttacks(Player ch){
		ArrayList <Attack> attacks = ch.getAttacks();
		
		if(ch.spEffectTime > 0){								// timer for special effects
			ch.spEffectTime -= 1;
		}
		
		if(ch.disable = true && ch.spEffectTime > 0){			// disable
			for(Attack a : attacks){							// player attack dmg is reduced to 2 until timer runs out
				a.dmg = 2;
			}
		}
		
		if(ch.poison = true && ch.poisonTime > 0){				// poison			
			if(ch.poisonDelay == 0){							// player loses 1 HP every second 
	    		ch.HP --;
	    		
	    		ch.poisonTime --;
	    		ch.poisonDelay = 60;
	    	}
		}
		
		if(ch.poisonDelay != 0){								// poison timer
			ch.poisonDelay --;									
		}
		
		if(ch.spEffectTime == 0){								// reset effect variables when effect timer runs out									
			ch.stun = false;
			ch.poison = false;
			ch.disable = false;
			for(int i = 0; i < attacks.size(); i++){			// reset attack dmg
				attacks.get(i).dmg = ch.getDmgs().get(i);
			}
		}
	}
	
	public void acceleration(Player ch, Rectangle cr){
		if(map == "Ice"){			// adds acceleration for ice level
			ch.VX += ch.a;			
			cr.x += ch.VX * ch.a;
		}
	}
	
	// Controls the location and frame of player
	public void move1(){
		
		// RELOAD TIME
		if (reload1 > 0){
			reload1 -= 1;
		}

		// CROSSES THE SCREEN
		
	    if (cr1.x > getWidth()){
	    	cr1.x = 0;
	    }
	    if (cr1.x < 0){
	    	cr1.x = getWidth();
	    }
	    
	    // UPPER BOUNDARY
	    if(cr1.y <= 0){
	    	cr1.y = 0;
	    }
		
		// MOVE RIGHT
		if (keys[KeyEvent.VK_RIGHT]){
			ch1.action = RIGHT;
			ch1.animateTime = 10;

			dir1 = 1;										
			
			if(map != "Ice"){
				if(ch1.stun == false){							// move if not stunned
					cr1.x += ch1.VX;
				}
			}
			else{
				if(ch1.stun == false && ch1.a < 1.5){			// ice level has acceleration for harder controls
					ch1.a += 0.01;
				}
			}

			if (ch1.frame >= 2){								// frame animation
				ch1.frame = 0;
			}

			else{
				if (ch1.frameTime == 0){
					ch1.frame ++;
					ch1.frameTime = 10;
				}

				else{
					ch1.frameTime --;
				}
			}
		}
		
		// MOVE LEFT
		if (keys[KeyEvent.VK_LEFT]){
			ch1.action = LEFT;
			ch1.animateTime = 10;

			dir1 = 2;
			
			if(map != "Ice"){
				if(ch1.stun == false){								// move if not stunned
					cr1.x -= ch1.VX;
				}
			}
			else{
				if(ch1.stun == false && ch1.a > -1.5){				// ice level has acceleration for harder controls
					ch1.a -= 0.01;
				}
			}
			
			if (ch1.frame >= 2){
				ch1.frame = 0;
			}

			else{
				if (ch1.frameTime == 0){
					ch1.frame ++;
					ch1.frameTime = 10;
				}

				else{
					ch1.frameTime --;
				}
			}
		}
	
		//JUMP
		
		if(ch1.jumpTime > 0){
			ch1.jumpTime --;
		}
		
		if(keys[KeyEvent.VK_UP] && ch1.jumpTime == 0 ){
			jumpSfx.loop(1);					// play sound effect
			
			ch1.jumpTime = 5;
			
			ch1.action = JUMP;
			ch1.animateTime = 0;
			
			if(ch1.stun == false && (ch1.jumps <= 2 || map == "Water")){		// can only jump twice and if not stunned
				ch1.jumps ++;													// can jump indefinitely in water level
				
				if(map == "Simple"){
					ch1.VY = -8.5;
				}
				else if(map == "Water"){
					ch1.VY = -3;
				}
				else if(map == "Space"){
					ch1.VY = -3;
				}
				else if(map == "Ice"){
					ch1.VY = -7;
				}												
			}
			
			if (ch1.frame >= 1){
				ch1.frame = 0;
			}

			else{
				ch1.frame ++;
			}
		}
		
		// ATTACK

		if (keys[KeyEvent.VK_DOWN]){
			if (dir1 == 1){
	    		ch1.action = ATKR;
	    		ch1.animateTime = 10;
	    	}

	    	if (dir1 == 2){
	    		ch1.action = ATKL;
	    		ch1.animateTime = 10;
	    	}

			if (ch1.frame >= 1){
				ch1.frame = 0;
			}

			else{
				if (ch1.frameTime == 0){
					ch1.frame ++;
					ch1.frameTime = 10;
				}

				else{
					ch1.frameTime --;
				}
			}
		}

	    // SHOOT
	    
	    if (keys[KeyEvent.VK_SPACE] && reload1 == 0 && ch1.MP > 0){
	    	if (dir1 == 1){
	    		ch1.action = SHOOTR;
	    		ch1.animateTime = 10;

	    		b1.createBulletsR(cr1.x, cr1.y + cr1.height/2);
	    		
	    		shootSfx.loop(1);			// play sound effect
	    		
	    		reload1 = 30;
	    		ch1.MP -= 5;
	    	}

	    	if (dir1 == 2){
	    		ch1.action = SHOOTL;
	    		ch1.animateTime = 10;

	    		b1.createBulletsL(cr1.x, cr1.y + cr1.height/2);
	    		
	    		shootSfx.loop(1);
	    		
	    		reload1 = 30;
	    		ch1.MP -= 5;
	    	}

	    	if (ch1.frame >= 1){
				ch1.frame = 0;
			}

			else{
				ch1.frame ++;
			}
	    }

	    b1.shoot(cr2, ch1, ch2);

	    // ANIMATION
	    
	   	if (collide(cr1, ch1) == true && ch1.animateTime == 0){
			ch1.frame = 0;
			ch1.action = STAND;
			
			ch1.jumps = 0;			// reset jumps to 0 when standing
		}

	    if (ch1.animateTime > 0){
	    	ch1.animateTime --;
	    }

	}

	public void move2(){
		
		//RELOAD TIME 
		
		if (reload2 > 0){
			reload2 -= 1;
		}

	    // CROSSES THE SCREEN
	    
	    if (cr2.x > getWidth()){
	    	cr2.x = 0;
	    }
	    if (cr2.x < 0){
	    	cr2.x = getWidth();
	    }
	    
	    // UPPER BOUNDARY
	    
	    if(cr2.y <= 0){
	    	cr2.y = 0;
	    }

		// MOVE RIGHT
		
		if (keys[KeyEvent.VK_D] ){
			ch2.action = RIGHT;
			ch2.animateTime = 10;

			dir2 = 1;
			
			if(map != "Ice"){
				if(ch2.stun == false){							// move if not stunned
					cr2.x += ch2.VX;
				}
			}
			else{
				if(ch2.stun == false && ch2.a < 1.5){			// ice level has acceleration for harder controls
					ch2.a += 0.01;
				}
			}

			if (ch2.frame >= 2){
				ch2.frame = 0;
			}

			else{
				if (ch2.frameTime == 0){
					ch2.frame ++;
					ch2.frameTime = 10;
				}

				else{
					ch2.frameTime --;
				}
			}
		}
		
		// MOVE LEFT
		
		if (keys[KeyEvent.VK_A] ){
			ch2.action = LEFT;
			ch2.animateTime = 10;

			dir2 = 2;
			
			if(map != "Ice"){
				if(ch2.stun == false){								// move if not stunned
					cr2.x -= ch2.VX;
				}
			}
			else{
				if(ch2.stun == false && ch2.a > -1.5){				// ice level has acceleration for harder controls
					ch2.a -= 0.01;
				}
			}
			
			if (ch2.frame >= 2){
				ch2.frame = 0;
			}

			else{
				if (ch2.frameTime == 0){
					ch2.frame ++;
					ch2.frameTime = 10;
				}

				else{
					ch2.frameTime --;
				}
			}
		}
		
		//JUMP
		
		if(ch2.jumpTime > 0){				// time before next jump
			ch2.jumpTime --;
		}
		
		if (jumpSfxDelay2 > 0){				// timer for sound effect
			jumpSfxDelay2--;
		}
		
		if (keys[KeyEvent.VK_W]  && ch2.jumpTime == 0){
			
			if (jumpSfxDelay2 == 0){		// play sound
				jumpSfx.loop(1);
				jumpSfxDelay2 = 40;
			}
			
			ch2.jumpTime = 5;
			
			ch2.action = JUMP;
			ch2.animateTime = 0;
			
			if(ch2.stun == false && (ch2.jumps <= 2 || map == "Water")){		// can only jump twice and if not stunned
				ch2.jumps ++;													// can jump indefintely in water level
				
				if(map == "Simple"){
					ch2.VY = -8.5;
				}
				else if(map == "Water"){
					ch2.VY = -3;
				}
				else if(map == "Space"){
					ch2.VY = -3;
				}		
				else if(map == "Ice"){
					ch2.VY = -7;
				}
			}
			
			if (ch2.frame >= 1){
				ch2.frame = 0;
			}

			else{
				ch2.frame ++;
			}
		}

		// ATTACK
		
		if (keys[KeyEvent.VK_S]){
			if (dir2 == 1){
	    		ch2.action = ATKR;
	    		ch2.animateTime = 10;
	    	}

	    	if (dir2 == 2){
	    		ch2.action = ATKL;
	    		ch2.animateTime = 10;
	    	}

	    	if (ch2.frame >= 1){
				ch2.frame = 0;
			}

			else{
				if (ch2.frameTime == 0){
					ch2.frame ++;
					ch2.frameTime = 10;
				}

				else{
					ch2.frameTime --;
				}
			}
		}

	    // SHOOT
	    
	    if (keys[KeyEvent.VK_SHIFT] && reload2 == 0 && ch2.MP > 0){
	    	if (dir2 == 1){
	    		ch2.action = SHOOTR;
	    		ch2.animateTime = 10;

	    		b2.createBulletsR(cr2.x, cr2.y + cr2.height/2);
	    		
	    		shootSfx.loop(1);			// play sound
	    		reload2 = 30;
	    	}

	    	if (dir2 == 2){
	    		ch2.action = SHOOTL;
	    		ch2.animateTime = 10;

	    		b2.createBulletsL(cr2.x, cr2.y + cr2.height/2);
	    		
	    		shootSfx.loop(1);
	    		reload2 = 30;
	    	}

	    	ch2.MP -= 5;

	    	if (ch2.frame >= 1){
				ch2.frame = 0;
			}

			else{
				ch2.frame ++;
			}
	    }

	    b2.shoot(cr1, ch2, ch1);

	    // ANIMATION
	    
	   	if (collide(cr2, ch2) == true && ch2.animateTime == 0){
			ch2.frame = 0;
			ch2.action = STAND;
			
			ch2.jumps = 0;		// reset jumps to 0 when standing
		}

	    if (ch2.animateTime > 0){
	    	ch2.animateTime --;
	    }
	}
	
	public boolean collide(Rectangle cr, Player ch){
		Rectangle bottomRect = new Rectangle(cr.x, cr.y + (cr.height - 15), cr.width, 15);		// new rect for players' feet
		Rectangle topRect = new Rectangle(cr.x, cr.y, cr.width, 15);							// new rect for players' head
		
		// PLATFORM COLLISION
		for(Rectangle p : plats){
			if(p.intersects(cr)){
				
				Rectangle topPlat = new Rectangle(p.x, p.y, p.width, 10);						// top of platform
				Rectangle bottomPlat = new Rectangle(p.x, p.y - 10, p.width, p.height - 10);	// bottom of platform
				
				if(bottomRect.intersects(topPlat)){		// can only reach top of platform				
					ch.VY = 0;							// stops falling 								
					cr.y = p.y - (cr.height);			// Snap to top of platform
					return true;
				}
				else if(topRect.intersects(bottomPlat)){// player hits bottom of plat with head
					cr.y += 5;							
				}
				if(cr.intersects(bottomPlat)){			// hits side/bottom of platform, cannot go through platform
					if(ch.action == LEFT){				// if player is facing left, is pushed back right
						cr.x += 5;
					}
					else if(ch.action == RIGHT){		// if player is facing right, is pushed back left
						cr.x -= 5;
					}
					return true;
				}
			}
		}
		
		// SPIKE COLLISION
		for(Rectangle s : spikes){
			if(s.intersects(cr)){
				cr.y = s.y - (cr.height);			// Snap to top of spike
				ch.VY = -4;							// player jumps
				ch.HP -= 5;							// player loses 5 hp
				return true;
			}
		}
		
		return false;
	}
	
	// checks for item collision with platform
	public boolean collideItems(){
		for(Rectangle p : plats){
			for(Rectangle item : itemRects){
				if(p.intersects(item)){
					return true;
				}
			}
		}
		return false;
	}

	// If player falls off screen, player is out of bounds
	public boolean outOfBounds(Rectangle r){
		Rectangle screenRect = new Rectangle (0, 0, getWidth(), getHeight());
		if(r.y > getHeight()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean gameOver(){	
		
		// LIVES MODE
		if(game.liveMode == true && game.timeMode == false){
			if(ch1.LIVES == 0 || ch2.LIVES == 0){	// gameover when player loses all lives
	    		return true;
	    	}
	    	else{
	    		return false;
	    	}
		}
		
		// TIMED MODE
		else{
			if(time == 0){							// time limit of 1 minute
				return true;
			}
			else{
				return false;
			}
		}
    }
    
	public void playerLives(Rectangle r, Player c){	
		
		if(outOfBounds(r) == true){							//Player falls off screen
			
			//LIVES MODE
			if(game.liveMode == true){
				if (c.LIVES != 0){							// respawn if player has more lives
					c.LIVES --;
					c.deaths ++;
					
					respawn(r, c);
				}
			}
			
			//TIMED MODE
			if(game.timeMode == true){						
				c.deaths ++;
				respawn(r, c);
			}
		}
		
		// Player loses all HP
		else if(c.HP == 0){
			
			// LIVES MODE
			if(game.liveMode == true){
				if(c.LIVES != 0){
					c.LIVES --;
					c.deaths ++;
					
					respawn(r, c);
				}
			}
			
			// TIMED MODE
			if(game.timeMode == true){
				c.deaths ++;
				respawn(r, c);
			}
			
		}
	}
	
	public void showGameOver(){						
		if(gameOver() == true){
    		new GameOver(game, this);
    	}
	}

	public void respawn(Rectangle r, Player ch){
		if(map == "Ice"){		// respawn coordinates 
			r.x = 1300;
			r.y = 100;
		}
		else{
			r.x = 117;			
    		r.y = 100;		
		}
				
    	ch.HP = 100;			// resets hp and mp
    	ch.MP = 50;
    	ch.a = 0;				// resets acceleration
    	
    	ch.stun = false;		// resets effect stats
    	ch.poison = false;
    	ch.disable = false;
	}
	
	public void reset(){
		
		//RESET P1 STATS
		ch1.LIVES = 3;
		ch1.HP = 100;
		ch1.MP = 50;
		ch1.a = 0;
		
		cr1.x = 800;
		cr1.y = 200;
		
		//RESET P2 STATS
		ch2.LIVES = 3;
		ch2.HP = 100;
		ch2.MP = 50;
		ch2.a = 0;
		
		cr2.x = 50;
		cr2.y = 300;
		
		//RESET KEYS
		keys = new boolean[KeyEvent.KEY_LAST+1];
		
		//RESET TIMER
		time = 60;
		timerDelay = 60;
		
		//RESET ITEMS
		items.clear();
		itemRects.clear();
		itemTime = 550;
	}
	
	public void paintComponent(Graphics g){
        
        // BG
    	g.drawImage(back, 0, 0, this);
    	
    	// SPIKES
		for (Rectangle s : spikes){
			g.drawImage(spikePic, s.x, s.y - 3, this);
		}

		//PLATFORMS
		for (Rectangle p : plats){
			if(map == "Ice"){
				g.setColor(Color.white);
			}
			else{
				g.setColor(Color.black);	
			}
			g.fillRect(p.x, p.y, p.width, p.height);
		}
		
 		//for (int i = 0; i < platformPics.size(); i ++){
 		//	g.drawImage(platformPics.get(i), plats.get(i).x, plats.get(i).y, this);
 		//}

    	// TIMER
    	if (timeMode == true){
    		Image digit1 = numbers.get(timeDigit1);
			g.drawImage(digit1, getWidth()/2 - 20, 30, this);
			
			Image digit2 = numbers.get(timeDigit2);
			g.drawImage(digit2, getWidth()/2 + 20, 30, this);
    	}	

    	// PLAYER ONE
    	ArrayList <Image> moves1 = pics1.get(ch1.action);
		Image pic1 = moves1.get(ch1.frame);

	    g.drawImage(pic1, cr1.x, cr1.y, this);

		// PLAYER TWO
		ArrayList <Image> moves2 = pics2.get(ch2.action);
		Image pic2 = moves2.get(ch2.frame);

		g.drawImage(pic2, cr2.x, cr2.y, this);

    	// PLAYER ONE BULLETS
		b1.drawBullets(g);

		// PLAYER TWO BULLETS
		b2.drawBullets(g);

		// PLAYER ICONS
		g.drawImage(player1Icon, 20, 30, this);
		g.drawImage(player2Icon, 1410, 30, this);
		
		// HP BAR 1
		g.setColor(new Color(243, 125, 107));
		g.fillRect(HPRect1.x, HPRect1.y, ch1.HP*4, HPRect1.height);

		g.drawImage(barBorderPic1, HPRect1.x, HPRect1.y, this);
		g.drawImage(HPTxt1Pic, HPRect1.x - 60, HPRect1.y, this);
		
		// HP BAR 2
		g.setColor(new Color(243, 125, 107));
		g.fillRect(HPRect2.x, HPRect2.y, ch2.HP*4, HPRect2.height);

		g.drawImage(barBorderPic2, HPRect2.x, HPRect2.y, this);
		g.drawImage(HPTxt2Pic, HPRect2.x + 410, HPRect2.y, this);
		
		// MP BAR 1
		g.setColor(new Color(115, 138, 152));
		g.fillRect(MPRect1.x, MPRect1.y, ch1.MP*8, MPRect1.height);

		g.drawImage(barBorderPic3, MPRect1.x, MPRect1.y, this);
		g.drawImage(MPTxt1Pic, MPRect1.x - 60, MPRect1.y, this);
		
		// MP BAR 2
		g.setColor(new Color(115, 138, 152));
 		g.fillRect(MPRect2.x, MPRect2.y, ch2.MP*8, MPRect2.height);
 		
 		g.drawImage(barBorderPic4, MPRect2.x, MPRect2.y, this);
		g.drawImage(MPTxt2Pic, MPRect2.x + 410, MPRect2.y, this);

 		// LIVES
 		if (timeMode == false){
	 		if (ch1.LIVES > 0){
	 			g.drawImage(p1heart1, 100, 110, this);
	 		}
	 		
	 		if (ch1.LIVES > 1){
	 			g.drawImage(p1heart2, 140, 110, this);
	 		}
	 		
	 		if (ch1.LIVES > 2){
	 			g.drawImage(p1heart3, 180, 110, this);
	 		}

			if (ch2.LIVES > 0){
	 			g.drawImage(p2heart1, 1365, 110, this);
	 		}
	 		
	 		if (ch2.LIVES > 1){
	 			g.drawImage(p2heart2, 1325, 110, this);
	 		}
	 		
	 		if (ch2.LIVES > 2){
	 			g.drawImage(p2heart3, 1285, 110, this);
	 		}
				
		}
 		// PLAYER ONE SHIELD
 		if (ch1.SHIELD != 0){
 			g.drawImage(shieldPic, cr1.x + 6, cr1.y + 32, this);
 		}

 		// PLAYER TWO SHIELD
 		if (ch2.SHIELD != 0){
 			g.drawImage(shieldPic, cr2.x + 6, cr2.y + 32, this);
 		}
 		
 		//ITEMS
		for (Rectangle item : itemRects){
			for (String itemName : items){
				if (itemName.equals("HPotion")){
	 				g.drawImage(HPotionPic, item.x, item.y - 40, this);
				}
				
				if (itemName.equals("MPotion")){
	 				g.drawImage(MPotionPic, item.x, item.y - 40, this);
				}
				
				if (itemName.equals("Speed")){
	 				g.drawImage(speedPic, item.x, item.y - 30, this);
				}
				
				if (itemName.equals("Strength")){
	 				g.drawImage(strengthPic, item.x, item.y - 5, this);
				}
				
				if (itemName.equals("Shield")){
	 				g.drawImage(shieldPic, item.x, item.y - 40, this);
				}
			}
		}

    }
}

