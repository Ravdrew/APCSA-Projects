package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import utilities.GDV5;

public class ScoreBoard {
	
	Font scoreFont = new Font(Font.MONOSPACED, Font.BOLD, 100);
	Font textFont = new Font(Font.MONOSPACED, Font.BOLD, 70);
	Font smallFont = new Font(Font.MONOSPACED, Font.BOLD, 50);
	Font boostFont = new Font(Font.MONOSPACED, Font.BOLD, 40);
	
	private int leftScore = 0;
	private int rightScore = 0;
	private int gameState = 0;
	private int animationFlashCount = 0;
	
	public void setLeftScore(int leftScore){
		this.leftScore = leftScore;
	}
	
	public void setRightScore(int rightScore) {
		this.rightScore = rightScore;
	}
	
	public void setGameState(int gameState) {
		this.gameState = gameState;
	}
	
	public int getLeftScore() {
		return this.leftScore;
	}
	
	public int getRightScore() {
		return this.rightScore;
	}
	
	public int checkWinner() { //-1 left wins, 0 no win, 1 right wings
		if(this.getLeftScore() >= 5) {
			return -1;
		}
		if(this.getRightScore() >= 5) {
			return 1;
		}
		return 0;
	}
	
	public int getGameState() {
		return this.gameState;
	}
	
	public void showBall(Graphics2D win, Ball ball) {
		win.setColor(ball.getBallColor());
		win.draw(ball);
		win.fill(ball);
	}
	
	public void showLeftPaddleAssets(Graphics2D win, Paddle leftPaddle) {
		win.setFont(boostFont);
		win.setColor(leftPaddle.getColor());
		win.draw(leftPaddle);
		win.fill(leftPaddle);
		win.drawString(Integer.toString(leftPaddle.getBoostQuantity()), 40, 580);
	}
	
	public void showRightPaddleAssets(Graphics2D win, Paddle rightPaddle) {
		win.setFont(boostFont);
		win.setColor(rightPaddle.getColor());
		win.draw(rightPaddle);
		win.fill(rightPaddle);
		win.drawString(Integer.toString(rightPaddle.getBoostQuantity()), 1090, 580);
	}
	
	public void showScoreBoard(Graphics2D win) {
		win.setColor(Color.white);
		win.setFont(scoreFont);
		win.drawString(Integer.toString(this.getLeftScore()) + "  :  " + Integer.toString(this.getRightScore()), 390, 322);
	}
	
	public void showVictor(Graphics2D win, Ball ball, Paddle leftPaddle, Paddle rightPaddle) {
		if(this.checkWinner() == -1) {
			ball.move((int)(GDV5.getMaxWindowX()/2 - ball.getWidth()/2), (int)(GDV5.getMaxWindowY()/2 - ball.getHeight()/2));
			win.setFont(textFont);
			win.setColor(Color.red);
			win.drawString("Player 1 Wins!", 310, 220);
			win.setColor(Color.yellow);
			win.drawString("Press Enter to Play Again", 90, 395);
			if(GDV5.KeysPressed[KeyEvent.VK_ENTER]) {
				leftPaddle.move(15, GDV5.getMaxWindowY()/2 - 95/2);
				rightPaddle.move(GDV5.getMaxWindowX() - 30, GDV5.getMaxWindowY()/2 - 95/2);
				this.setLeftScore(0);
				this.setRightScore(0);
				this.setGameState(0);
			}
		}
		else if(this.checkWinner() == 1) {
			ball.move((int)(GDV5.getMaxWindowX()/2 - ball.getWidth()/2), (int)(GDV5.getMaxWindowY()/2 - ball.getHeight()/2));
			win.setFont(textFont);
			win.setColor(Color.blue);
			win.drawString("Player 2 Wins!", 310, 220);
			win.setColor(Color.yellow);
			win.drawString("Press Enter to Play Again", 90, 395);
			if(GDV5.KeysPressed[KeyEvent.VK_ENTER]) {
				leftPaddle.move(15, GDV5.getMaxWindowY()/2 - 95/2);
				rightPaddle.move(GDV5.getMaxWindowX() - 30, GDV5.getMaxWindowY()/2 - 95/2);
				this.setLeftScore(0);
				this.setRightScore(0);
				this.setGameState(0);
			}
		}	
	}
	
	public void startAnimation(Graphics2D win){
		win.setColor(Color.white);
		win.setFont(scoreFont);
		if(this.animationFlashCount >= 0 && this.animationFlashCount <= 150) {
			win.drawString("P O N G", 400, 200);
		}
		if(this.animationFlashCount >= 300 && this.animationFlashCount <= 450) {
			win.drawString("P O N G", 400, 200);
		}
		if(this.animationFlashCount >= 600 && this.animationFlashCount <= 750) {
			win.drawString("P O N G", 400, 200);
		}
		if(this.animationFlashCount >= 900) {
			win.setColor(Color.yellow);
			win.drawString("P O N G", 390, 200);
			win.setColor(Color.white);
			win.setFont(smallFont);
			win.drawString("1 - Singleplayer", 50, 300);
			win.drawString("2 - Multiplayer", 695, 300);
			win.drawString("By Andres Garcia", 368, 560);
			
			win.setFont(boostFont);
			win.setColor(Color.red);
			win.drawString("W - Move Up", 50, 385);
			win.drawString("S - Move Down", 50, 435);
			win.drawString("A - Boost/Smash", 50, 485);
			
			win.setColor(Color.blue);
			win.drawString("Up - Move Up", 695, 385);
			win.drawString("Down - Move Down", 695, 435);
			win.drawString("Right - Boost/Smash", 695, 485);
		}
		
		
		
		animationFlashCount++;
	}
	
	public void singlePlayerDifficultyScreen(Graphics2D win, Paddle paddle){
		win.setFont(boostFont);
		win.setColor(Color.yellow);
		win.drawString("3 - Easy", 200, 270);
		win.drawString("4 - Medium", 200, 320);
		win.drawString("5 - Hard", 200, 370);

		if(GDV5.KeysPressed[KeyEvent.VK_3]) {
			paddle.setSinglePlayerDifficulty(1);
			this.setGameState(1);
		}
		else if(GDV5.KeysPressed[KeyEvent.VK_4]) {
			paddle.setSinglePlayerDifficulty(2);
			this.setGameState(1);
		}
		else if(GDV5.KeysPressed[KeyEvent.VK_5]) {
			paddle.setSinglePlayerDifficulty(3);
			this.setGameState(1);
		}
	}

}
