package breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import utilities.GDV5;

public class BScoreboard {
	private static Font smallFont = new Font(Font.MONOSPACED, Font.BOLD, 50);
	private static Font smallMidFont = new Font(Font.MONOSPACED, Font.BOLD, 40);
	private static Font verySmallFont = new Font(Font.MONOSPACED, Font.BOLD, 30);
	
	private static int state = -1;
	
	private static int lives = 3;
	private static int level = 1;
	
	private static int score = 0;
	private static String scoreString = "000";
	
	private static int highScore = 0;
	private static String highScoreString = "000";
	
	private static int animationFlashCount = 0;
	
	private static boolean pressed = false;
	
	public static String getScoreString() {
		return scoreString;
	}

	public static void setScoreString(String p_scoreString) {
		scoreString = p_scoreString;
	}

	public static String getHighScoreString() {
		return highScoreString;
	}

	public static void setHighScoreString(String p_highScoreString) {
		highScoreString = p_highScoreString;
	}
	
	public static int getState() {
		return state;
	}
	
	public static void setState(int p_state) {
		state = p_state;
	}
	
	public static int getLives() {
		return lives;
	}
	
	public static void setLives(int p_lives) {
		lives = p_lives;
	}
	
	public static int getLevel() {
		return level;
	}
	
	public static void setLevel(int p_level) {
		level = p_level;
	}
	
	public static void setHighScore(int p_highScore){
		highScore = p_highScore;
	}
	
	public static int getHighScore() {
		return highScore;
	}
	
	public static void setScore(int p_score){
		score = p_score;
	}
	
	public static int getScore() {
		return score;
	}
	
	public static void updateState(Graphics2D win, BBall ball) { //0 = no win, 1 = win 1st stage, -1 = home, -2 = end
		if(GDV5.KeysPressed[KeyEvent.VK_D]) setScore(896);
		if(GDV5.KeysPressed[KeyEvent.VK_A]) setScore(448);
		
		if(getScore() >= 896 || getLives() <= 0) {
			showEnd(win, ball);
			
		}
		else if(getScore() >= 448 && getState() < 1) {
			ball.setYSpeedMod(0);
			ball.setXSpeed(ball.getStartSpeed());
			ball.setYSpeed(ball.getStartSpeed());
			ball.setLocation((int)(GDV5.getMaxWindowX()/2 - ball.getWidth()/2), (int)(GDV5.getMaxWindowY()/2 - ball.getHeight()/2 - 100));
			BBrick.restoreBlocks(BreakoutRunner.getBricks());
			setState(1);
			setLevel(2);
			ball.setBricksHit(0);
			ball.setXSpeed(ball.getStartSpeed());
			ball.setYSpeed(ball.getStartSpeed());
		}
		
		System.out.println(getState());
	}
	
	public static void draw(Graphics2D win) {
		if(getScore() < 10) {
			setScoreString("00" + getScore());
		}
		else if(getScore() < 100) {
			setScoreString("0" + getScore());
		}
		else {
			setScoreString("" + getScore());
		}
		
		if(getHighScore() < 10) {
			setHighScoreString("00" + getHighScore());
		}
		else if(highScore < 100) {
			setHighScoreString("0" + getHighScore());
		}
		else {
			setHighScoreString("" + getHighScore());
		}
		
		win.setColor(Color.white);
		win.setFont(smallFont);
		
		win.drawString(Integer.toString(level), 25, 45);
		win.drawString(Integer.toString(lives), 265, 45);
		win.drawString(scoreString, 50, 90);
		win.drawString(highScoreString, 290, 90);
	}
	
	public static void showEnd(Graphics2D win, BBall ball) {
		setState(2);
		if(getScore() > getHighScore()) setHighScore(getScore());
		ball.setLocation((int)(GDV5.getMaxWindowX()/2 - ball.getWidth()/2), (int)(GDV5.getMaxWindowY()/2 - ball.getHeight()/2 - 100));
		win.setFont(verySmallFont);
		win.setColor(Color.PINK);
		win.drawString("Press Enter to Play Again", 13, 395);
		if(GDV5.KeysPressed[KeyEvent.VK_ENTER]) {
			BBrick.restoreBlocks(BreakoutRunner.getBricks());
			ball.setBricksHit(0);
			setLives(3);
			setLevel(1);
			setState(0);
			setScore(0);
			ball.setXSpeed(ball.getStartSpeed());
			ball.setYSpeed(ball.getStartSpeed());
		}
	}
	
	public static void startAnimation(Graphics2D win){
		win.setColor(Color.white);
		win.setFont(smallFont);
		if(animationFlashCount >= 0 && animationFlashCount <= 600) {
			win.drawString("B R E A K O U T", 15, 250);
		}
		if(animationFlashCount >= 1200 && animationFlashCount <= 1800) {
			win.drawString("B R E A K O U T", 15, 250);
		}
		if(animationFlashCount >= 2400 && animationFlashCount <= 3000) {
			win.drawString("B R E A K O U T", 15, 250);
		}
		if(animationFlashCount >= 3600) {
			win.setColor(Color.yellow);
			win.drawString("B R E A K O U T", 15, 250);
			win.setFont(smallMidFont);
			win.setColor(Color.white);
			win.drawString("By Andres Garcia", 50, 295);
			
			win.setFont(verySmallFont);
			win.setColor(Color.blue);
			win.drawString("Use MOUSE To Move", 80, 370);
			
			win.setColor(Color.red);
			win.setFont(smallMidFont);
			win.drawString("Press SPACE To Play!", 7, 450);
		}
		
		animationFlashCount++;
	}
}
