package snake;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import utilities.GDV5;

public class Body{
	
	private int row;
	private int col;
	private String heading;
	private static int llBodyRow;
	private static int llBodyCol;
	
	
	public int getLlBodyRow() {
		return llBodyRow;
	}

	public void setLlBodyRow(int llBodyRow) {
		this.llBodyRow = llBodyRow;
	}

	public int getLlBodyCol() {
		return llBodyCol;
	}

	public void setLlBodyCol(int llBodyCol) {
		this.llBodyCol = llBodyCol;
	}

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

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public Body(int row, int col) {
		this.row = row;
		this.col = col;
		this.heading = "right";
	}
	
	public static void movementCheck(ArrayList<Body> snakeBody) {
		
		Body headSnake = snakeBody.get(0);
		if(GDV5.KeysPressed[KeyEvent.VK_W] && headSnake.heading != "down") {
			headSnake.setHeading("up");
		}
		else if(GDV5.KeysPressed[KeyEvent.VK_S] && headSnake.heading != "up") {
			headSnake.setHeading("down");
		}
		else if(GDV5.KeysPressed[KeyEvent.VK_A] && headSnake.heading != "right") {
			headSnake.setHeading("left");
		}
		else if(GDV5.KeysPressed[KeyEvent.VK_D] && headSnake.heading != "left") {
			headSnake.setHeading("right");
		}	
		
	}
	
	public static void movement(ArrayList<Body> snakeBody) {
		llBodyRow = snakeBody.get(snakeBody.size() - 1).getRow();
		llBodyCol = snakeBody.get(snakeBody.size() - 1).getCol();

		
		for(int i = snakeBody.size() - 1; i > 0; i--) {
			snakeBody.get(i).setRow(snakeBody.get(i-1).getRow());
			snakeBody.get(i).setCol(snakeBody.get(i-1).getCol());
		}
		
		Body headSnake = snakeBody.get(0);
		
		
		if(headSnake.getHeading().equals("left")) {
			headSnake.setCol(headSnake.getCol() - 1);
		}
		else if(headSnake.getHeading().equals("right")) {
			headSnake.setCol(headSnake.getCol() + 1);
		}
		else if(headSnake.getHeading().equals("up")) {
			headSnake.setRow(headSnake.getRow() - 1);
		}
		else if(headSnake.getHeading().equals("down")) {
			headSnake.setRow(headSnake.getRow() + 1);
		}
		
	}
	
	public static ArrayList<Body> makeBody() {
		ArrayList<Body> snakeBody = new ArrayList<Body>();
		Body b = new Body(7, 13);
		snakeBody.add(b);
	
		
		return snakeBody;
	}
	
	public static void appendBody(ArrayList<Body> snakeBody) {
		Body b = new Body(llBodyRow, llBodyCol);
		
		snakeBody.add(b);
	}
	
	
}
