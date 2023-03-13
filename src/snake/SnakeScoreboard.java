package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import utilities.GDV5;

public class SnakeScoreboard {
    private static Font smallFont = new Font(Font.MONOSPACED, Font.BOLD, 50);
	private static Font smallMidFont = new Font(Font.MONOSPACED, Font.BOLD, 40);
	private static Font verySmallFont = new Font(Font.MONOSPACED, Font.BOLD, 30);

    private static int state = -1;

    private static int score = 0;

    private static int goal = 10;

    private static int animationFlashCount = 0;

    public static int getGoal() {
        return goal;
    }

    public static void setGoal(int goal) {
        SnakeScoreboard.goal = goal;
    }

    public static int getState() {
        return state;
    }

    public static void setState(int state) {
        SnakeScoreboard.state = state;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        SnakeScoreboard.score = score;
    }

    public static void draw(Graphics2D win) {

		
		win.setColor(Color.white);
		win.setFont(smallFont);
		  
		/*win.drawString(Integer.toString(level), 25, 45);
		win.drawString(Integer.toString(lives), 265, 45);
		win.drawString(scoreString, 50, 90);*/
        win.setColor(Color.white);
        win.drawString(Integer.toString(state), 80, 46);
        win.setColor(Color.green);
        win.drawString(Integer.toString(goal), 770, 46);
        win.setColor(Color.yellow);
		win.drawString(Integer.toString(score), 670, 46);

        if(SnakeScoreboard.getState() == 0){
            win.setColor(Color.red);
            win.setFont(verySmallFont);
            win.drawString("Press ENTER to Play again!", 150, 40);
            if(GDV5.KeysPressed[KeyEvent.VK_ENTER]){

				SnakeScoreboard.setState(1);
			}
        }
    }

    public static void startAnimation(Graphics2D win){
		win.setColor(Color.white);
		win.setFont(smallFont);
		if(animationFlashCount >= 0 && animationFlashCount <= 1000) {
			win.drawString("S N A K E", 300, 250);
		}
		if(animationFlashCount >= 2000 && animationFlashCount <= 3000) {
			win.drawString("S N A K E", 300, 250);
		}
		if(animationFlashCount >= 4000 && animationFlashCount <= 5000) {
			win.drawString("S N A K E", 300, 250);
		}
        if(animationFlashCount >= 5300 && animationFlashCount <= 6300) {
			win.drawString("S N A K E", 300, 250);
		}
        if(animationFlashCount >= 8000 && animationFlashCount <= 9300) {
			win.drawString("S N A K E", 300, 250);
		}
		if(animationFlashCount >= 13000) {
			win.setColor(Color.yellow);
			win.drawString("S N A K E", 300, 250);
			win.setFont(smallMidFont);
			win.setColor(Color.white);
			win.drawString("By Andres Garcia", 243, 295);
			
			win.setFont(verySmallFont);
			win.setColor(Color.blue);
			win.drawString("Use WASD To Move", 290, 370);
			
			win.setColor(Color.red);
			win.setFont(smallMidFont);
			win.drawString("Press SPACE To Play!", 210, 450);
		}
		
		animationFlashCount++;
	}

}
