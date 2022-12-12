package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BParticle extends Rectangle{
	
	private Color particleColor;
	private static boolean alive = false;

	public static boolean isAlive() {
		return alive;
	}
	
	public static void setAlive(boolean p_alive) {
		alive = p_alive;
	}
	
	public Color getParticleColor() {
		return particleColor;
	}
	
	public void setBrickColor(Color particleColor) {
		this.particleColor = particleColor;
	}
	
	public static void moveShine(BParticle[] particles, BBrick brick) {
		alive = true;
		particles[0].setLocation((int)brick.getCenterX() - 3, (int)brick.getCenterY() - 3);
		particles[1].setLocation((int)brick.getCenterX() - 3, (int)brick.getCenterY() - 3);
		particles[2].setLocation((int)brick.getCenterX() - 3, (int)brick.getCenterY() - 3);
		particles[3].setLocation((int)brick.getCenterX() - 3, (int)brick.getCenterY() - 3);
	}
	
	public static void movement(BParticle[] particles) {
		particles[0].translate(-2, -2);
		particles[1].translate(2, -2);
		particles[2].translate(-2, 2);
		particles[3].translate(2, 2);
	}
	
	public static BParticle[] makeParticles() {
		BParticle[] p = new BParticle[4];	
		
		p[0] = new BParticle(0, 0);
		p[1] = new BParticle(10, 0);
		p[2] = new BParticle(0, 10);
		p[3] = new BParticle(10, 10);
				
		return p;
	}
	
	public static void draw(Graphics2D win, BParticle[] particles) {
		if(alive) {
			for (BParticle p:particles) {
				win.setColor(BBrick.getLastHitColor());
				win.draw(p);
				win.fill(p);
			}	
		}
	}
	
	public BParticle(int xPos, int yPos){
		super(xPos, yPos, 5, 5);
	}
}
