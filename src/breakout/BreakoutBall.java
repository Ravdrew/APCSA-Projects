package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import utilities.GDV5;

public class BreakoutBall extends Rectangle{
	
	private int ySpeed = this.getStartSpeed();
	private int xSpeed = this.getStartSpeed();
	private int hitLocation = 0;
	private Color ballColor = Color.white;
	
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
	
	public int getXSpeed() {
		return this.xSpeed;
	}
	
	public int getYSpeed() {
		return this.ySpeed;
	}
	
	public int getHitLocation() {
		return this.hitLocation;
	}
	
	public int getStartSpeed() {
		return 10;
	}
	
	public void setHitLocation(int hitLocation) {
		this.hitLocation = hitLocation;
	}
	
	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	
	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	public boolean scoredLeft() {
		return this.getCenterX() < 0;
	}
	
	public boolean scoredRight() {
		return this.getCenterX() > GDV5.getMaxWindowX();
	}
	
	public Color getBallColor() {
		return this.ballColor;
	}
	
	public void setBallColor(Color ballColor) {
		this.ballColor = ballColor;
	}
	
	public void movement(Brick[] bricks) {
		if(this.getBotY() > GDV5.getMaxWindowY() || this.getTopY() < 0) {
			ySpeed *= -1;
			//this.setHitLocation(0);
		}
		if(this.getRightX() > GDV5.getMaxWindowX() || this.getLeftX() < 0) {
			xSpeed *= -1;
		}
		
		for(Brick b:bricks) {
			if(b.isAlive()) {
				if(this.intersects(b)) {
					b.setAlive(false);
					ySpeed *= -1;
				}
			}
		}
		
		this.translate(xSpeed, ySpeed);
		System.out.println(xSpeed + " " + ySpeed);
	}
	
	public void draw(Graphics2D win) {
		win.setColor(this.getBallColor());
		win.draw(this);
		win.fill(this);
	}
	
	public BreakoutBall(){
		super(GDV5.getMaxWindowX()/2 - 10, GDV5.getMaxWindowY()/2 - 10, 15, 15);
	}
	
}
