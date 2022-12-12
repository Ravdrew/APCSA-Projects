package breakout;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import utilities.GDV5;

public class BBrick extends Rectangle {
	private Color brickColor;
	private boolean alive;
	private static Color lastHitColor;
	
	public static Color getLastHitColor() {
		return lastHitColor;
	}

	public static void setLastHitColor(Color lastHitColor) {
		BBrick.lastHitColor = lastHitColor;
	}

	public boolean isAlive() {
		return alive;
	}
	
	public Color getBrickColor() {
		return brickColor;
	}
	
	public void setBrickColor(Color brickColor) {
		this.brickColor = brickColor;
	}
	
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public BBrick(int xPos, int yPos, Color color){
		super(xPos, yPos, 32, 9);
		this.alive = true;
		this.brickColor = color;
	}
	
	public static BBrick[] makeBricks() { //14 //8
		int spacing = 35;
		int x = 0;
		int y = 100;
		Color[] colors = {Color.red, Color.orange, Color.green, Color.yellow};
		int curr_color = 0;
		
		BBrick[] b = new BBrick[112];
		for(int i = 0; i < b.length; i++) {
			if(i % 14 == 0 && i > 0) {
				x = 0;
				y += 12;
			}
			if(i % 28 == 0 && i > 0) curr_color++;
			
			b[i] = new BBrick(x, y, colors[curr_color]);
			x += spacing;
		}
		return b;
	}
	
	public static void restoreBlocks(BBrick[] bricks) {
		for (BBrick b:bricks) {
			b.setAlive(true);
		}	
	}
	
	public void draw(Graphics2D win) {
		if(alive == true) {
			win.setColor(brickColor);
			win.fill(this);
		}
	}

}
