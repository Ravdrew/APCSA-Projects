package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import utilities.GDV5;

public class BPowerUp extends Rectangle{
	
	private boolean alive = false;
	
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
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void movement(BPaddle paddle) {
		this.translate(0, 2);
		if(this.intersects(paddle)){
			this.setLocation(-100, -100);
			this.setAlive(false);
			BScoreboard.setLives(BScoreboard.getLives() + 1);
		}
		if(this.getTopY() > GDV5.getMaxWindowY()){
			this.setAlive(false);
		}
	}
	
	public void draw(Graphics2D win) {
		if(this.isAlive()) {
			win.setColor(Color.pink);
			win.draw(this);
			win.fill(this);
		}
	}
	
	public BPowerUp(){
		super(-100, -100, 15, 15);
	}
}
