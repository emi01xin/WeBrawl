// WeBrawl
// Sheng Fang, Emily Wang, Grace Tsai

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WeBrawl extends JFrame implements ActionListener, KeyListener{
	
	Timer myTimer;
	GamePanel game = new GamePanel(this);
	
	boolean timeMode;
	boolean liveMode;

    public WeBrawl(){
		super("WeBrawl");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500, 800);
		
		myTimer = new Timer(10, this);	 										// Trigger every 10 ms
		add(game);
		
		new Menu(this);
    }
    
    public void start(){
		myTimer.start();
		addKeyListener(this);
		setResizable(false);
	}
	
	public void playSound(int n){
		game.playSound(n);
		System.out.println(n);
	}
	
	public boolean isTimed(){
		return timeMode;
	}
	
	public boolean isLives(){
		return liveMode;
	}
	
	public void selectChar1(int n){
		game.choosePlayer1(n);
	}
	
	public void selectChar2(int n){
		game.choosePlayer2(n);
	}
	
	public void selectMap(int n){
		game.makeArena(n);
	}
	
	public void resetGame(){
		game.reset();
	}
																		
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		
		if (game != null){
			if(game.gameOver() == false){				
				setVisible(true);
				
				game.move1();
				game.move2();
				
				game.setMovePlats();
				
				game.action();
				
				game.gravity();
				
				game.acceleration(game.ch1, game.cr1);
				game.acceleration(game.ch2, game.cr2);
				
				game.playerLives(game.cr1, game.ch1);
				game.playerLives(game.cr2, game.ch2);
				
				game.genItems();
				
				game.getItem(game.cr1, game.ch1);
				game.getItem(game.cr2, game.ch2);
				
				game.effectTimer(game.ch1);
				game.effectTimer(game.ch2);
				
				game.specialAttacks(game.ch1);
				game.specialAttacks(game.ch2);
				
				//TIMED MODE
				if(timeMode == true){
					game.timer();
					game.timeMode = true;
				}
		
				game.showGameOver();
				
			}
			
			//GAMEOVER	
			if(game.gameOver() == true){
				setVisible(false);
			}
			
			game.repaint();
		}

	}
	
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
    	game.setKey(e.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent e) {
		game.setKey(e.getKeyCode(), false);
    }
	
    public static void main(String[] arguments) {
		WeBrawl frame = new WeBrawl();
    }
}