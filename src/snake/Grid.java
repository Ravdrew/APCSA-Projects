package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Grid extends Rectangle{
	private String dir = "";
	private Color col = Color.white; //black = empty, red = fruit, white = snake
	private int occupied = 0; //0 = empty, -1 = fruit, 1 = snake
	
	public Grid(int xPos, int yPos) {
		super(xPos, yPos, 26, 26);
	}
	
	public static Grid[][] makeGrid() {
		Grid[][] gridTotal = new Grid[18][30];
		int x;
		int y = 62;
		for(int r = 0; r < 18; r++) {
			x = 2;
			for(int c = 0; c < 30; c++) {
				gridTotal[r][c] = new Grid(x, y);
				x += 30;
			}
			y += 30;
		}
		return gridTotal;
	}
	
	public static void drawGrid(Graphics2D win, Grid[][] grid, ArrayList<Body> snakeBody, ArrayList<EnemyBody> enemyBody) {
		Body headSnake = snakeBody.get(0);
			for(int r = 0; r < grid.length; r++) {
				for(int c = 0; c < grid[0].length; c++) {
					win.setColor(grid[r][c].col);
					for(Body b:snakeBody) {
						if(b.getRow() == r && b.getCol() == c) {
							if(headSnake.isAlive() == false) {
								win.setColor(Color.blue);
								SnakeScoreboard.setState(0);
							}
							else if(SnakeScoreboard.getScore() >= 10) win.setColor(Color.orange);
							else if(headSnake.isAlive()) win.setColor(Color.yellow);
						}
					}
					for(EnemyBody e:enemyBody){
						//System.out.println("Snake Row: " + e.getRow() + " Col: " + e.getCol());
						if(e.getRow() == r && e.getCol() == c) {
							win.setColor(Color.red);
							//System.out.println("yeet");
						}
					}
					win.fill(grid[r][c]);
				}
			}
	}
}
