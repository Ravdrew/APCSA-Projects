package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.Console;

import utilities.GDV5;

public class PongRunner extends GDV5{

	Ball ball = new Ball();
	Paddle leftPaddle = new Paddle(15, GDV5.getMaxWindowY()/2 - 95/2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, Color.red);
	Paddle rightPaddle = new Paddle (GDV5.getMaxWindowX() - 30, GDV5.getMaxWindowY()/2 - 95/2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, Color.blue);
	ScoreBoard scoreBoard = new ScoreBoard();
	
	Font scoreFont = new Font(Font.MONOSPACED, Font.BOLD, 100);
	Font textFont = new Font(Font.MONOSPACED, Font.BOLD, 70);
	Font boostFont = new Font(Font.MONOSPACED, Font.BOLD, 40);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PongRunner one = new PongRunner();
		one.start();
		//add single, add color/sound when smash, flywheel control

	}
	
	@Override
	public void update() { //60 fps
		if(scoreBoard.getGameState() == 0) {
			if(GDV5.KeysPressed[KeyEvent.VK_1]) scoreBoard.setGameState(-1);
			else if(GDV5.KeysPressed[KeyEvent.VK_2]) scoreBoard.setGameState(2);
		}
		if(scoreBoard.getGameState() == 2) {
			if(scoreBoard.checkWinner() == 0) {
				ball.movement(leftPaddle, rightPaddle, scoreBoard);
				leftPaddle.movement();
				rightPaddle.movement();
			}
		}
		if(scoreBoard.getGameState() == 1) {
			if(scoreBoard.checkWinner() == 0) {
				ball.movement(leftPaddle, rightPaddle, scoreBoard);
				leftPaddle.movement();
				rightPaddle.autonMovement(ball);
			}
		}
	}

	@Override
	public void draw(Graphics2D win) { //at the processor speed - approx 3000 times per second (dont put game code here)
		// TODO Auto-generated method stub
		
		if(scoreBoard.getGameState() == 0) {
			scoreBoard.startAnimation(win);
		}
		
		else if(scoreBoard.getGameState() == -1) {
			scoreBoard.singlePlayerDifficultyScreen(win, rightPaddle);
		}
		
		else if(scoreBoard.getGameState() > 0) {
			scoreBoard.showScoreBoard(win);
			
			scoreBoard.showVictor(win, ball, leftPaddle, rightPaddle);
			
			scoreBoard.showBall(win, ball);
			
			scoreBoard.showLeftPaddleAssets(win, leftPaddle);
			
			scoreBoard.showRightPaddleAssets(win, rightPaddle);
		}
		
		
		
		
	}

}
