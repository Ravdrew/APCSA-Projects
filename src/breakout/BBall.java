package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import utilities.GDV5;

public class BBall extends Rectangle{
	
	private int ySpeed = this.getStartSpeed();
	private int xSpeed = this.getStartSpeed();
	private boolean oneHit = false;
	private int ySpeedMod = 0;
	private int hitLocation = 0;
	private Color ballColor = Color.white;
	private int bricksHit = 0;
	
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
		return 3;
	}
	
	public int getYSpeedMod() {
		return ySpeedMod;
	}
	
	public boolean isOneHit() {
		return oneHit;
	}
	
	public void setOneHit(boolean oneHit) {
		this.oneHit = oneHit;
	}
	
	public void setYSpeedMod(int ySpeedMod) {
		this.ySpeedMod = ySpeedMod;
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
	
	public int getBricksHit() {
		return bricksHit;
	}

	public void setBricksHit(int bricksHit) {
		this.bricksHit = bricksHit;
	}

	public void movement(BBrick[] bricks, BPaddle paddle, BPowerUp powerUp, BParticle[] particles) {
		
		for(BBrick b:bricks) {
			if(b.isAlive()) {
				if(this.intersects(b)) {
					BParticle.moveShine(particles, b);
					if(Math.random() <= 0.05 && powerUp.isAlive() == false) {
						powerUp.setAlive(true);
						powerUp.setLocation((int) (b.getCenterX() - powerUp.getWidth()/2), (int) (b.getCenterY() - powerUp.getHeight()/2));
					}
					
					this.setBricksHit(this.getBricksHit() + 1);
					if(b.getBrickColor() == Color.yellow) {
						BBrick.setLastHitColor(Color.yellow);
						BScoreboard.setScore(BScoreboard.getScore() + 1);
					}
					else if(b.getBrickColor() == Color.green) {
						BBrick.setLastHitColor(Color.green);
						BScoreboard.setScore(BScoreboard.getScore() + 3);
					}
					else if(b.getBrickColor() == Color.orange) {
						BBrick.setLastHitColor(Color.orange);
						if(this.getYSpeedMod() < 4) this.setYSpeedMod(4);
						BScoreboard.setScore(BScoreboard.getScore() + 5);
					}
					else if(b.getBrickColor() == Color.red) {
						BBrick.setLastHitColor(Color.red);
						this.setYSpeedMod(6);
						BScoreboard.setScore(BScoreboard.getScore() + 7);
					}
					
					if(this.getYSpeedMod() < 2 && this.getBricksHit() >= 12){
						this.setYSpeedMod(2);
					}
					else if(this.getYSpeedMod() < 1 && this.getBricksHit() >= 4) {
						this.setYSpeedMod(1);
					}
					
					this.setOneHit(false);
					b.setAlive(false);
					this.setYSpeed((this.getStartSpeed() + this.getYSpeedMod()) * GDV5.sgn(this.getYSpeed()) * -1);
				}
			}
		}

		
		if(this.getTopY() < 0) {
			this.setOneHit(false);
			this.setYSpeed(this.getYSpeed() * -1);
			//this.setHitLocation(0);
		}
		if(this.getRightX() > GDV5.getMaxWindowX() || this.getLeftX() < 0) {
			this.setOneHit(false);
			this.setXSpeed(this.getXSpeed() * -1);
		}
		if(this.getBotY() > GDV5.getMaxWindowY()) {
			this.setOneHit(false);
			this.setLocation((int)(GDV5.getMaxWindowX()/2 - this.getWidth()/2), (int)(GDV5.getMaxWindowY()/2 - this.getHeight()/2 - 100));
			BScoreboard.setLives(BScoreboard.getLives() - 1);
		}
		if(this.intersects(paddle)&& oneHit == false) {
			if(this.getCenterX() <= paddle.getCenterX() - 12) {
				this.setOneHit(true);
				this.setXSpeed(-1 * (this.getStartSpeed() + 2));
				this.setYSpeed((this.getStartSpeed() + this.getYSpeedMod() - 2) * GDV5.sgn(this.getYSpeed()) * -1);
			}
			else if(this.getCenterX() >= paddle.getCenterX() + 12) {
				this.setOneHit(true);
				this.setXSpeed(this.getStartSpeed() + 2);
				this.setYSpeed((this.getStartSpeed() + this.getYSpeedMod() - 2) * GDV5.sgn(this.getYSpeed()) * -1);
			}
			else {
				this.setOneHit(true);
				this.setXSpeed(this.getStartSpeed() * GDV5.sgn(this.getXSpeed()));
				this.setYSpeed((this.getStartSpeed() + this.getYSpeedMod()) * GDV5.sgn(this.getYSpeed()) * -1);
			}
		}
		
		//System.out.println(this.getYSpeed());
		this.translate(this.getXSpeed(), this.getYSpeed());
	}
	
	public void draw(Graphics2D win) {
		win.setColor(this.getBallColor());
		win.draw(this);
		win.fill(this);
	}
	
	public BBall(){
		super(GDV5.getMaxWindowX()/2 - 4, GDV5.getMaxWindowY()/2 - 4 - 100, 9, 6);
	}
	
}
