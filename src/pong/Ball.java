package pong;

import java.awt.Color;
import java.awt.Rectangle;

import utilities.GDV5;

public class Ball extends Rectangle{
	
	private int ySpeed = this.getStartSpeed();
	private int xSpeed = this.getStartSpeed();
	private int hitLocation = 0;
	private Color ballColor = Color.yellow;
	
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
		return 4;
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
	
	public void movement(Paddle leftPaddle, Paddle rightPaddle, ScoreBoard scoreBoard) {
		if(this.getBotY() > GDV5.getMaxWindowY() || this.getTopY() < 0) {
			ySpeed *= -1;
			this.setHitLocation(0);
		}
		if(this.getCenterX() > GDV5.getMaxWindowX()) {
			this.setBallColor(Color.yellow);
			scoreBoard.setLeftScore(scoreBoard.getLeftScore() + 1);
			leftPaddle.setBoostQuantity(100);
			rightPaddle.setBoostQuantity(100);
			this.setLocation((int)(GDV5.getMaxWindowX()/2 - this.getWidth()/2), (int)(GDV5.getMaxWindowY()/2 - this.getHeight()/2));
			this.setHitLocation(0);
			xSpeed = -1 * this.getStartSpeed();
			if(ySpeed > this.getStartSpeed()) ySpeed = this.getStartSpeed();
			else ySpeed = -1 * this.getStartSpeed();
		}
		if(this.getCenterX() < 0) {
			this.setBallColor(Color.yellow);
			scoreBoard.setRightScore(scoreBoard.getRightScore() + 1);
			leftPaddle.setBoostQuantity(100);
			rightPaddle.setBoostQuantity(100);
			this.setLocation((int)(GDV5.getMaxWindowX()/2 - this.getWidth()/2), (int)(GDV5.getMaxWindowY()/2 - this.getHeight()/2));
			this.setHitLocation(0);
			xSpeed = this.getStartSpeed();
			if(ySpeed > this.getStartSpeed()) ySpeed = this.getStartSpeed();
			else ySpeed = -1 * this.getStartSpeed();

		}
		
		if(this.intersects(leftPaddle) && this.getHitLocation() != -1){
			this.setBallColor(Color.yellow);
			this.setHitLocation(-1);
			if(leftPaddle.getPaddleSpeed() > leftPaddle.getStartPaddleSpeed()) {
				this.setBallColor(Color.red);
				this.setXSpeed(this.getXSpeed() - 1);
			}
			//System.out.println(this.getXSpeed());
			int leftCollisionType = GDV5.collisionDirection(leftPaddle, this, this.getXSpeed(), this.getYSpeed());
			
			if (leftCollisionType == 1 || leftCollisionType == 3) this.setYSpeed(this.getYSpeed() * -1);
			else if(leftCollisionType == 0){
				this.setXSpeed(this.getXSpeed() * -1);
			}
			
		}
		if(this.intersects(rightPaddle) && this.getHitLocation() != 1){
			this.setBallColor(Color.yellow);
			this.setHitLocation(1);
			if(rightPaddle.getPaddleSpeed() > rightPaddle.getStartPaddleSpeed()) {
				this.setBallColor(Color.blue);
				this.setXSpeed(this.getXSpeed() + 1);
			}
			//System.out.println(this.getXSpeed());
			int rightCollisionType = GDV5.collisionDirection(rightPaddle, this, this.getXSpeed(), this.getYSpeed());
			
			if(rightCollisionType == 1 || rightCollisionType == 3) this.setYSpeed(this.getYSpeed() * -1);
			else if(rightCollisionType == 2) this.setXSpeed(this.getXSpeed() * -1);
		}
		
		this.translate(xSpeed, ySpeed);
	}
	
	public Ball(){
		super(GDV5.getMaxWindowX()/2 - 10, GDV5.getMaxWindowY()/2 - 10, 20, 20);
	}
	
}
