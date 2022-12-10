package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import pong.Paddle;
import utilities.GDV5;

public class BreakoutRunner extends GDV5 {
	Brick[] bricks;
	BreakoutBall ball = new BreakoutBall();
	BreakoutPaddle paddle = new BreakoutPaddle(GDV5.getMaxWindowX()/2 - 140/2, GDV5.getMaxWindowY() - 15, KeyEvent.VK_A, KeyEvent.VK_D);
	
	public BreakoutRunner() {
		super();
		bricks = Brick.makeBricks();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//super();
		BreakoutRunner b = new BreakoutRunner();
		b.start();
	}
	
	
	@Override
	public void update() {
		ball.movement(bricks);
		
	}
	
	
	@Override
	public void draw(Graphics2D win) {
		for (Brick b:bricks) {
			b.draw(win);
		}	
		
		ball.draw(win);
		paddle.draw(win);
	}

}
