package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import utilities.GDV5;

public class BreakoutPaddle extends Rectangle implements MouseMotionListener {
	
	private int leftKey;
	private int rightKey;
	private int paddleSpeed = 5;
	private Color color = Color.CYAN;
	
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
		if(GDV5.KeysPressed[rightKey] && this.getRightX() < GDV5.getMaxWindowX()) {
			this.translate(this.getPaddleSpeed(), 0);
		}
		if(GDV5.KeysPressed[leftKey] && this.getLeftX() > 0) {
			this.translate(-this.getPaddleSpeed(), 0);
		}
	}
	
	public void draw(Graphics2D win) {
		win.setColor(this.getPaddleColor());
		win.draw(this);
		win.fill(this);
	}
	
	public BreakoutPaddle(int xPos, int yPos, int rightKey, int leftKey){
		super(xPos, yPos, 140, 10);
		this.rightKey = rightKey;
		this.leftKey = leftKey;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		this.move(e.getX(), e.getY());
		//e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
