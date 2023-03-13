package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import utilities.GDV5;

public class Fruit extends Rectangle{
	
	private int row;
	private int col;
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Fruit(int row, int col) {
		super(5 + col * 30, 65 + row * 30, 20, 20);
		this.row = row;
		this.col = col;
	}
	
	public static ArrayList<Fruit> makeFruit(){
		ArrayList<Fruit> returningFruit = new ArrayList<Fruit>();
		Fruit a = new Fruit((int) (Math.random() * 18), (int) (Math.random() * 30));
		Fruit b = new Fruit((int) (Math.random() * 18), (int) (Math.random() * 30));
		Fruit c = new Fruit((int) (Math.random() * 18), (int) (Math.random() * 30));
		
		returningFruit.add(a);
		returningFruit.add(b);
		returningFruit.add(c);
		
		return returningFruit;
	}
	
	public static void orderFruit(ArrayList<Fruit> fruitList, ArrayList<Body> snakeBody, ArrayList<EnemyBody> enemyBody) {

		Body headSnake = snakeBody.get(0);
		EnemyBody headEnemy = enemyBody.get(0);
		
		for(Fruit f:fruitList) {
			//System.out.println("Snake Row: " + headSnake.getRow() + " Col: " + headSnake.getCol());
			if(headSnake.getRow() == f.getRow() && headSnake.getCol() == f.getCol()) {
				
				f.setRow((int) (Math.random() * 18));
				f.setCol((int) (Math.random() * 30));
				f.move(5 + f.getCol() * 30, 65 + f.getRow() * 30);
				
				SnakeRunner.getS1().play(1 + (int) (Math.random() * 2));
				Body.appendBody(snakeBody);
				SnakeScoreboard.setScore(SnakeScoreboard.getScore() + 1);
			}
			if(headEnemy.getRow() == f.getRow() && headEnemy.getCol() == f.getCol()) {
				System.out.println("om");
				f.setRow((int) (Math.random() * 18));
				f.setCol((int) (Math.random() * 30));
				f.move(5 + f.getCol() * 30, 65 + f.getRow() * 30);
				EnemyBody.appendBody(enemyBody);
			}
		}
		
	}
	
	public static void drawFruit(Graphics2D win, ArrayList<Fruit> fruitList) {
		for(Fruit f:fruitList) {
			win.setColor(Color.green);
			win.fill(f);
		}
	}

	public String toString(){
		return "Row: " + row + " Col: " + col;
	}
	
	
}
