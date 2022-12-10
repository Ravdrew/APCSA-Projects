package breakout;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import utilities.GDV5;

public class Brick extends Rectangle {
	private Color brickColor;
	private boolean alive;

	public boolean isAlive() {
		return alive;
	}
	
	public Brick(int xPos, int yPos, Color color){
		super(xPos, yPos, 50, 15);
		this.alive = true;
		this.brickColor = color;
	}
	
	public static Brick[] makeBricks() {
		int spacing = 52;
		int x = 3;
		int y = 30;
		Color[] colors = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue};
		int curr_color = 0;
		
		Brick[] b = new Brick[115];
		for(int i = 0; i < b.length; i++) {
			if(i % 23 == 0 && i > 0) {
				x = 3;
				y += 16;
				curr_color++;
			}
			b[i] = new Brick(x, y, colors[curr_color]);
			x += spacing;
		}
		return b;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void draw(Graphics2D win) {
		if(alive == true) {
			win.setColor(brickColor);
			win.fill(this);
		}
	}

}
