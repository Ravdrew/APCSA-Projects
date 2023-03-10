package snake;

import java.awt.Graphics2D;
import java.util.ArrayList;

import utilities.GDV5;

public class SnakeRunner extends GDV5{
	private static Grid[][] gridTotal;
	private static ArrayList<Body> snakeBody;
	private static ArrayList<Fruit> fruitList;
	private static int ticks;
	
	
	public SnakeRunner() {
		gridTotal = Grid.makeGrid();
		snakeBody = Body.makeBody();
		fruitList = Fruit.makeFruit();
		for(Fruit f:fruitList) {
			System.out.println("Row: " + f.getRow() + " Col: " + f.getCol());
		}
		ticks = 0;
	}
	
	public static boolean tick() {
		ticks++;
		if(ticks % 15 == 0) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GDV5.setMaxWindowX(900);
		GDV5.setMaxWindowY(600);
		
		SnakeRunner s = new SnakeRunner();
		s.start();
		
	}

	@Override
	public void update() {
		Body.movementCheck(snakeBody);
		Fruit.orderFruit(fruitList, snakeBody);
		if(tick()) {
			Body.movement(snakeBody);
		}
		
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		Grid.drawGrid(win, gridTotal, snakeBody);
		Fruit.drawFruit(win, fruitList);
	}

}
