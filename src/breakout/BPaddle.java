package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import utilities.GDV5;

public class BPaddle extends Rectangle {

	private int paddleSpeed = 5;
	private Color color = Color.BLUE;
	
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

	public int getPaddleSpeed() {
		return this.paddleSpeed;
	}
	
	public void setPaddleSpeed(int speed) {
		this.paddleSpeed = speed;
	}
		
	public Color getPaddleColor() {
		return this.color;
	}

	public void movement() {
		this.translate(GDV5.getMouseX() - (int) this.getCenterX(), 0);
	}
	
	public void draw(Graphics2D win) {
		win.setColor(this.getPaddleColor());
		win.draw(this);
		win.fill(this);
	}
	
	public BPaddle(int xPos, int yPos){
		super(xPos, yPos, 40, 11);
	}
}
