package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import utilities.GDV5;

public class BreakoutRunner extends GDV5 {
	private static BBrick[] bricks;
	private static BParticle[] particles;
	private BBall ball = new BBall();
	private BPaddle paddle = new BPaddle(GDV5.getMaxWindowX()/2 - 40/2, GDV5.getMaxWindowY() - 35);
	private BPowerUp powerUp = new BPowerUp();
	
	public BreakoutRunner() {
		super();
		particles = BParticle.makeParticles();
		bricks = BBrick.makeBricks();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//super();
		GDV5.setMaxWindowX(488);
		GDV5.setMaxWindowY(760);
		BreakoutRunner b = new BreakoutRunner();
		b.start();
	}
	
	
	@Override
	public void update() {
		if(BScoreboard.getState() > -1 && BScoreboard.getState() != 2) {
			BParticle.movement(particles);
			ball.movement(bricks, paddle, powerUp, particles);
			paddle.movement();
			powerUp.movement(paddle);
		}
		else if(BScoreboard.getState() == -1){
			if(GDV5.KeysPressed[KeyEvent.VK_SPACE]) {
				BScoreboard.setState(0);
			}
		}
	}
	
	
	@Override
	public void draw(Graphics2D win) {
		if(BScoreboard.getState() > -1) {
			BScoreboard.updateState(win, ball);
			for (BBrick b:bricks) {
				b.draw(win);
			}	
			
			BParticle.draw(win, particles);
			
			ball.draw(win);
			paddle.draw(win);
			powerUp.draw(win);
			BScoreboard.draw(win);
		}
		else if(BScoreboard.getState() == -1) {
			BScoreboard.startAnimation(win);
		}
	}

	public static BBrick[] getBricks() {
		return bricks;
	}
	
}
