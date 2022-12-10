package pong;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import utilities.GDV5;

public class Paddle extends Rectangle {
	
	private int upKey;
	private int downKey;
	private int speedKey;
	private int paddleSpeed = 5;
	private int autonPaddleSpeed;
	private int boostQuantity = 100;
	private int singlePlayerDifficulty;
	private Color color;
	
	public double getRightX() {
		return this.getCenterX() + this.getWidth()/2;
	}
	
	public double getLeftX() {
		return this.getX();
	}
	
	public double getTopY() {
		return this.getY();
	}
	
	public double getBotY() {
		return this.getCenterY() + this.getHeight()/2;
	}
	
	public int getStartPaddleSpeed() {
		return 5;
	}
	
	public int getPaddleSpeed() {
		return this.paddleSpeed;
	}
	
	public void setPaddleSpeed(int speed) {
		this.paddleSpeed = speed;
	}
	
	public int getBoostQuantity() {
		return this.boostQuantity;
	}
	
	public void setBoostQuantity(int boostQuantity) {
		this.boostQuantity = boostQuantity;
	}
	
	public int getSinglePlayerDifficulty() {
		return this.singlePlayerDifficulty;
	}
	
	public void setSinglePlayerDifficulty(int singlePlayerDifficulty) {
		this.singlePlayerDifficulty = singlePlayerDifficulty;
	}
	
	
	public int getAutonPaddleSpeed() {
		return autonPaddleSpeed;
	}

	public void setAutonPaddleSpeed(int autonPaddleSpeed) {
		this.autonPaddleSpeed = autonPaddleSpeed;
	}

	public void movement() {
		if(GDV5.KeysPressed[speedKey] && this.getBoostQuantity() > 0) {
			this.setPaddleSpeed(this.getStartPaddleSpeed() + 3);
			boostQuantity -= 1;
		}
		else {
			this.setPaddleSpeed(this.getStartPaddleSpeed());
		}
		
		if(GDV5.KeysPressed[upKey] && this.getTopY() > 0) {
			this.translate(0, -1 * this.getPaddleSpeed());
		}
		if(GDV5.KeysPressed[downKey] && this.getBotY() < GDV5.getMaxWindowY()) {
			this.translate(0, this.getPaddleSpeed());
		}
	}
	
	public void autonMovement(Ball ball) {
		if(this.getSinglePlayerDifficulty() == 1) this.setAutonPaddleSpeed(3);
		else if(this.getSinglePlayerDifficulty() == 2) this.setAutonPaddleSpeed(4);
		else if(this.getSinglePlayerDifficulty() == 3) this.setAutonPaddleSpeed(5);
		
		if(ball.getCenterX() > GDV5.getMaxWindowX() / 2) {
			if(ball.getCenterY() > this.getCenterY() && this.getBotY() < GDV5.getMaxWindowY()) {
				this.translate(0, this.getAutonPaddleSpeed());
			}
			else if(ball.getCenterY() < this.getCenterY() && this.getTopY() > 0) {
				this.translate(0, -1 * this.getAutonPaddleSpeed());
			}
		}
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public Paddle(int xPos, int yPos, int upKey, int downKey, int speedKey, Color color){
		super(xPos, yPos, 15, 95);
		this.upKey = upKey;
		this.downKey = downKey;
		this.speedKey = speedKey;
		this.color = color;
	}
}
