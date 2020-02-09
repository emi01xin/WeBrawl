// WeBrawl
// Sheng Fang, Emily Wang, Grace Tsai

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.sound.sampled.*;

class Menu extends JFrame implements ActionListener{
	private WeBrawl game;
	JButton playBtn = new JButton("PLAY");
	JButton optionsBtn = new JButton("OPTIONS");
	Clip menuBgm;

	public Menu(WeBrawl g){
		super("WeBrawl");
		setSize(1500, 800);
		game = g;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		playBtn.addActionListener(this);
		optionsBtn.addActionListener(this);
		
		ImageIcon back = new ImageIcon("bg/menuscreen.png");
		JLabel backLabel = new JLabel(back);

		JLayeredPane mPage = new JLayeredPane();
		mPage.setLayout(null);

		backLabel.setSize(1500, 800);
		backLabel.setLocation(0, 0);
		mPage.add(backLabel, 1);

		// PLAY
		playBtn.setSize(200, 50);
		playBtn.setLocation(390, 440);
		playBtn.setOpaque(false);
		mPage.add(playBtn, 2);

		optionsBtn.setSize(300, 50);
		optionsBtn.setLocation(740, 440);
		optionsBtn.setOpaque(false);
		mPage.add(optionsBtn, 2);
		
		playSound();

		add(mPage);
		setVisible(true);

	}
	
	//Taken from "how to play a simple audio file"
	//https://stackoverflow.com/questions/42955509/how-to-play-a-simple-audio-file-java
	public void playSound(){
		try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("menu bgm.au"));
        menuBgm = AudioSystem.getClip();
        menuBgm.open(audioInputStream);

        menuBgm.loop(Clip.LOOP_CONTINUOUSLY);
		}
    	catch (Exception e) {
        	e.printStackTrace();
    	}
	}

    public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();

		if (source == playBtn){
			new CharSelect1(game, this);
			setVisible(false);
  	 	}
  	 	
  	 	if (source == optionsBtn){
			new OptionsMenu(game);
			setVisible(false);
  	 	}
    }
}

class OptionsMenu extends JFrame implements ActionListener{
	private WeBrawl game;
	
	JButton menuBtn = new JButton("MENU");

	public OptionsMenu(WeBrawl g){
		super("WeBrawl");
		setSize(1500, 800);
		game = g;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBtn.addActionListener(this);

		ImageIcon back = new ImageIcon("bg/optionsmenu.png");
		JLabel backLabel = new JLabel(back);
		
		JLayeredPane mPage = new JLayeredPane();
		mPage.setLayout(null);
		
		backLabel.setSize(1500, 800);
		backLabel.setLocation(0, 0);
		mPage.add(backLabel, 1);
		
		menuBtn.setSize(100, 50);
		menuBtn.setLocation(1330, 50);
		menuBtn.setOpaque(false);
		mPage.add(menuBtn, 2);
		
		add(mPage);
		setVisible(true);

	}

    public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();

		if (source == menuBtn){
			new Menu(game);
			setVisible(false);
  	 	}
  	 	
    }
    	
}

class CharSelect1 extends JFrame implements ActionListener{
	private WeBrawl game;
	private Menu menu;
	
	JButton char1Btn = new JButton("BILLD");
	JButton char2Btn = new JButton("HAPPY CAT");
	JButton char3Btn = new JButton("PUSHEEN");
	JButton char4Btn = new JButton("TUZKI");
	JButton char5Btn = new JButton("USAGYUUUN");

	public CharSelect1(WeBrawl g, Menu m){
		super("WeBrawl");
		setSize(1500, 800);
		
		game = g;
		menu = m;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		char1Btn.addActionListener(this);
		char2Btn.addActionListener(this);
		char3Btn.addActionListener(this);
		char4Btn.addActionListener(this);
		char5Btn.addActionListener(this);

		ImageIcon back = new ImageIcon("bg/selectplayer1.png");
		JLabel backLabel = new JLabel(back);

		ImageIcon char1 = new ImageIcon("billd/billdstand001.png");
		JLabel char1Label = new JLabel(char1);

		ImageIcon char2 = new ImageIcon("happycat/happycatstand001.png");
		JLabel char2Label = new JLabel(char2);

		ImageIcon char3 = new ImageIcon("pusheen/pusheenstand001.png");
		JLabel char3Label = new JLabel(char3);

		ImageIcon char4 = new ImageIcon("tuzki/tuzkistand001.png");
		JLabel char4Label = new JLabel(char4);

		ImageIcon char5 = new ImageIcon("usagyuuun/usagyuuunstand001.png");
		JLabel char5Label = new JLabel(char5);
		
		JLayeredPane cPage = new JLayeredPane();
		cPage.setLayout(null);
		
		// BACKGROUND
		backLabel.setSize(1500, 800);
		backLabel.setLocation(0, 0);
		cPage.add(backLabel, 1);

		// BILLD
		char1Btn.setSize(160, 160);
		char1Btn.setLocation(280, 370);
		char1Btn.setOpaque(false);
		cPage.add(char1Btn, 2);

		// HAPPY CAT
		char2Btn.setSize(160, 160);
		char2Btn.setLocation(470, 370);
		char2Btn.setOpaque(false);
		cPage.add(char2Btn, 2);

		// PUSHEEN
		char3Btn.setSize(160, 160);
		char3Btn.setLocation(660, 370);
		char3Btn.setOpaque(false);
		cPage.add(char3Btn, 2);

		// TUZKI
		char4Btn.setSize(160, 160);
		char4Btn.setLocation(860, 370);
		char4Btn.setOpaque(false);
		cPage.add(char4Btn, 2);

		// USAGYUUUN
		char5Btn.setSize(160, 160);
		char5Btn.setLocation(1050, 370);
		char5Btn.setOpaque(false);
		cPage.add(char5Btn, 2);
		
		add(cPage);
		setVisible(true);

	}

    public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();

  	 	if (source == char1Btn){
			game.selectChar1(0);
			new CharSelect2(game, menu, 0);
			setVisible(false);
  	 	}

  	 	if (source == char2Btn){
			game.selectChar1(1);
			new CharSelect2(game, menu, 1);
			setVisible(false);
  	 	}
  	 	
  	 	if (source == char3Btn){
			game.selectChar1(2);
			new CharSelect2(game, menu, 2);
			setVisible(false);
  	 	}

  	 	if (source == char4Btn){
			game.selectChar1(3);
			new CharSelect2(game, menu, 3);
			setVisible(false);
  	 	}
  	 	
  	 	if (source == char5Btn){
			game.selectChar1(4);
			new CharSelect2(game, menu, 4);
			setVisible(false);
  	 	}
    }
}

class CharSelect2 extends JFrame implements ActionListener{
	private WeBrawl game;
	private Menu menu;
	
	JButton char1Btn = new JButton("BILLD");
	JButton char2Btn = new JButton("HAPPY CAT");
	JButton char3Btn = new JButton("PUSHEEN");
	JButton char4Btn = new JButton("TUZKI");
	JButton char5Btn = new JButton("USAGYUUUN");

	public CharSelect2(WeBrawl g, Menu m, int n){
		super("WeBrawl");
		setSize(1500, 800);
		
		game = g;
		menu = m;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		char1Btn.addActionListener(this);
		char2Btn.addActionListener(this);
		char3Btn.addActionListener(this);
		char4Btn.addActionListener(this);
		char5Btn.addActionListener(this);

		ImageIcon char1 = new ImageIcon("billd/billdstand001.png");
		JLabel char1Label = new JLabel(char1);

		ImageIcon char2 = new ImageIcon("happycat/happycatstand001.png");
		JLabel char2Label = new JLabel(char2);

		ImageIcon char3 = new ImageIcon("pusheen/pusheenstand001.png");
		JLabel char3Label = new JLabel(char3);

		ImageIcon char4 = new ImageIcon("tuzki/tuzkistand001.png");
		JLabel char4Label = new JLabel(char4);

		ImageIcon char5 = new ImageIcon("usagyuuun/usagyuuunstand001.png");
		JLabel char5Label = new JLabel(char5);
		
		ImageIcon back1 = new ImageIcon("bg/selectplayer2_1.png");
		ImageIcon back2 = new ImageIcon("bg/selectplayer2_2.png");
		ImageIcon back3 = new ImageIcon("bg/selectplayer2_3.png");
		ImageIcon back4 = new ImageIcon("bg/selectplayer2_4.png");
		ImageIcon back5 = new ImageIcon("bg/selectplayer2_5.png");

		JLayeredPane cPage = new JLayeredPane();
		cPage.setLayout(null);
		
		// BILLD							
		if (n != 0){										// player 2 cannot have the same character as player 1
			char1Btn.setSize(160, 160);
			char1Btn.setLocation(280, 370);
			char1Btn.setOpaque(false);
			char1Btn.setContentAreaFilled(false);
			char1Btn.setBorderPainted(false);
			char1Btn.setText("");
			cPage.add(char1Btn, 2);
		}
		
		if (n == 0){
			JLabel backLabel = new JLabel(back1);
			
			backLabel.setSize(1500, 800);
			backLabel.setLocation(0, 0);
			cPage.add(backLabel, 1);
		}

		// HAPPY CAT
		if (n != 1){
			char2Btn.setSize(160, 160);
			char2Btn.setLocation(470, 370);
			char2Btn.setOpaque(false);
			cPage.add(char2Btn, 2);
		}
		
		if (n == 1){
			JLabel backLabel = new JLabel(back2);
			
			backLabel.setSize(1500, 800);
			backLabel.setLocation(0, 0);
			cPage.add(backLabel, 1);
		}
		
		// PUSHEEN
		if (n != 2){
			char3Btn.setSize(160, 160);
			char3Btn.setLocation(660, 370);
			char3Btn.setOpaque(false);
			cPage.add(char3Btn, 2);
		}
		
		if (n == 2){
			JLabel backLabel = new JLabel(back3);
			
			backLabel.setSize(1500, 800);
			backLabel.setLocation(0, 0);
			cPage.add(backLabel, 1);
		}

		// TUZKI
		if (n != 3){
			char4Btn.setSize(160, 160);
			char4Btn.setLocation(860, 370);
			char4Btn.setOpaque(false);
			cPage.add(char4Btn, 2);
		}
		
		if (n == 3){
			JLabel backLabel = new JLabel(back4);
			
			backLabel.setSize(1500, 800);
			backLabel.setLocation(0, 0);
			cPage.add(backLabel, 1);
		}

		// USAGYUUUN
		if (n != 4){
			char5Btn.setSize(160, 160);
			char5Btn.setLocation(1050, 370);
			char5Btn.setOpaque(false);
			cPage.add(char5Btn, 2);
		}
		
		if (n == 4){
			JLabel backLabel = new JLabel(back5);
			
			backLabel.setSize(1500, 800);
			backLabel.setLocation(0, 0);
			cPage.add(backLabel, 1);
		}
			
		add(cPage);
		setVisible(true);

	}

    public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();

  	 	if (source == char1Btn){
			setVisible(false);
			game.selectChar2(0);
			new MapSelect(game, menu);
  	 	}

  	 	if (source == char2Btn){
			setVisible(false);
			game.selectChar2(1);
			new MapSelect(game, menu);
  	 	}
  	 	
  	 	if (source == char3Btn){
			setVisible(false);
			game.selectChar2(2);
			new MapSelect(game, menu);
  	 	}

  	 	if (source == char4Btn){
			setVisible(false);
			game.selectChar2(3);
			new MapSelect(game, menu);
  	 	}
  	 	
  	 	if (source == char5Btn){
  	 		setVisible(false);
			game.selectChar2(4);
			new MapSelect(game, menu);
  	 	}
  	 	
    }

}

class MapSelect extends JFrame implements ActionListener{

	private WeBrawl game;
	private Menu menu;
	
	JButton map1 = new JButton("map1");
	JButton map2 = new JButton("map2");
	JButton map3 = new JButton("map3");
	JButton map4 = new JButton("map4");
	
	int mapNum;
	
	public MapSelect(WeBrawl g, Menu m){
		super("WeBrawl");
		setSize(1500, 800);
		
		game = g;
		menu = m;

		map1.addActionListener(this);
		map2.addActionListener(this);
		map3.addActionListener(this);
		map4.addActionListener(this);
		
		ImageIcon back = new ImageIcon("bg/selectmaps.png");
		JLabel backLabel = new JLabel(back);

		JLayeredPane mPage = new JLayeredPane();
		mPage.setLayout(null);
		
		backLabel.setSize(1500, 800);
		backLabel.setLocation(0, 0);
		mPage.add(backLabel, 1);

		map1.setSize(200, 200);
		map1.setLocation(290, 340);
		map1.setOpaque(false);
		mPage.add(map1, 2);

		map2.setSize(200, 200);
		map2.setLocation(520, 340);
		map2.setOpaque(false);
		mPage.add(map2, 2);

		map3.setSize(200, 200);
		map3.setLocation(760, 340);
		map3.setOpaque(false);
		mPage.add(map3, 2);

		map4.setSize(200, 200);
		map4.setLocation(990, 340);
		map4.setOpaque(false);
		mPage.add(map4, 2);

		add(mPage);
		setVisible(true);

	}

    public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();

		if (source == map1){
			setVisible(false);
			game.selectMap(1);
			mapNum = 1;
			
			new SelectionScreen(game, this, menu);
  	 	}
  	 	if (source == map2){
			setVisible(false);
			game.selectMap(2);
			
			mapNum = 2;
			new SelectionScreen(game, this, menu);
  	 	}
  	 	if (source == map3){
			setVisible(false);
			game.selectMap(3);
			
			mapNum = 3;
  	 		new SelectionScreen(game, this, menu);
  	 	}
  	 	if (source == map4){
			setVisible(false);
			game.selectMap(4);
			
			mapNum = 4;
  	 		new SelectionScreen(game, this, menu);
  	 	}
    }
}


class SelectionScreen extends JFrame implements ActionListener{
	private WeBrawl game;
	private Menu menu;
	private MapSelect mapSelect;
	
	JButton timeBtn = new JButton("TIMED");
	JButton livesBtn = new JButton("LIVES");
	
	JButton map1 = new JButton("map1");
	JButton map2 = new JButton("map2");
	JButton map3 = new JButton("map3");
	JButton map4 = new JButton("map4");

	public SelectionScreen(WeBrawl g, MapSelect m, Menu me){
		super("WeBrawl");
		setSize(1500, 800);
		
		game = g;
		menu = me;
		mapSelect = m;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		timeBtn.addActionListener(this);
		livesBtn.addActionListener(this);
		
		map1.addActionListener(this);
		map2.addActionListener(this);
		map3.addActionListener(this);
		map4.addActionListener(this);

		ImageIcon back = new ImageIcon("bg/selectmodes.png");
		JLabel backLabel = new JLabel(back);

		JLayeredPane sPage = new JLayeredPane();
		sPage.setLayout(null);

		backLabel.setSize(1500, 800);
		backLabel.setLocation(0, 0);
		sPage.add(backLabel, 1);

		// TIMED
		timeBtn.setSize(200, 60);
		timeBtn.setLocation(790, 380);
		timeBtn.setOpaque(false);
		sPage.add(timeBtn, 2);

		// LIVES
		livesBtn.setSize(200, 60);
		livesBtn.setLocation(470, 380);
		livesBtn.setOpaque(false);
		sPage.add(livesBtn, 2);
		
		add(sPage);
		setVisible(true);

	}

    public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		
		// TIMER MODE
		if (source == timeBtn){
			setVisible(false);
			
			menu.menuBgm.stop();
			game.playSound(mapSelect.mapNum);
			
			game.timeMode = true;
			game.liveMode = false;
			
			game.start();
  	 	}
  	 	// LIVES MODE
  	 	if (source == livesBtn){
			setVisible(false);
			
			menu.menuBgm.stop();
			game.playSound(mapSelect.mapNum);
			
			game.timeMode = false;
			game.liveMode = true;
			
			game.start();
  	 	}
  	 	
    }
}

class GameOver extends JFrame implements ActionListener{
	private WeBrawl game;
	private GamePanel panel;
	
	JButton reBtn = new JButton("REMATCH");
	JButton newBtn = new JButton("NEW GAME");
	
	Clip gameOverBgm;

	public GameOver(WeBrawl g, GamePanel p){
		super("WeBrawl");
		setSize(1500, 800);
		
		game = g;
		panel = p;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		reBtn.addActionListener(this);
		newBtn.addActionListener(this);

		ImageIcon back = new ImageIcon("bg/gameoverscreen.png");
		
		JLabel backLabel = new JLabel(back);

		JLayeredPane gPage = new JLayeredPane();
		gPage.setLayout(null);

		backLabel.setSize(1500, 800);
		backLabel.setLocation(0, 0);
		gPage.add(backLabel, 1);

		// REMATCH
		reBtn.setSize(260, 80);
		reBtn.setLocation(450, 420);
		reBtn.setOpaque(false);
		gPage.add(reBtn, 2);

		// NEW GAME
		newBtn.setSize(250, 80);
		newBtn.setLocation(800, 420);
		newBtn.setOpaque(false);
		gPage.add(newBtn, 2);
		
		add(gPage);
		setVisible(true);
		
		// MUSIC
		panel.simpleBgm.stop();
		panel.waterBgm.stop();
		panel.spaceBgm.stop();
		panel.iceBgm.stop();
	
		playSound();

	}
	
	public void playSound(){
    	try {
	        AudioInputStream gameOverBgmFile = AudioSystem.getAudioInputStream(this.getClass().getResource("game over bgm.au"));
	        gameOverBgm = AudioSystem.getClip();
	        gameOverBgm.open(gameOverBgmFile);
	
	        gameOverBgm.loop(Clip.LOOP_CONTINUOUSLY);
		}
    	catch (Exception e) {
        	e.printStackTrace();
    	}
    }

    public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		
		//NEW GAME
		if (source == newBtn){
			gameOverBgm.stop();
			
			setVisible(false);
			new WeBrawl();
  	 	}
  	 	
  	 	//REMATCH
  	 	if (source == reBtn){
  	 		gameOverBgm.stop();
  	 		
			setVisible(false);
			
			if (panel.bgmNum == 1){
				panel.simpleBgm.loop(Clip.LOOP_CONTINUOUSLY);	
			}
			if (panel.bgmNum == 2){
				panel.waterBgm.loop(Clip.LOOP_CONTINUOUSLY);
			}
			if (panel.bgmNum == 3){
				panel.spaceBgm.loop(Clip.LOOP_CONTINUOUSLY);
			}
			if (panel.bgmNum == 4){
				panel.iceBgm.loop(Clip.LOOP_CONTINUOUSLY);
			}
			
			game.resetGame();
  	 	}
    }
}
