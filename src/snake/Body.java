package snake;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import utilities.GDV5;

public class Body{
	
	private int row;
	private int col;
	private String heading;
	private String prevHeading;
	private static int llBodyRow;
	private static int llBodyCol;
	private static int prevHeadRow;
	private static int prevHeadCol;
	private static boolean pressing;
	private static boolean win1 = false;
	private static boolean win2 = true;
	private boolean alive;

	public static boolean isWin1() {
		return win1;
	}

	public static void setWin1(boolean win1) {
		Body.win1 = win1;
	}

	public static boolean isWin2() {
		return win2;
	}

	public static void setWin2(boolean win2) {
		Body.win2 = win2;
	}

	public String getPrevHeading() {
		return prevHeading;
	}

	public void setPrevHeading(String prevHeading) {
		this.prevHeading = prevHeading;
	}

	public static int getPrevHeadRow() {
		return prevHeadRow;
	}

	public static void setPrevHeadRow(int prevHeadRow) {
		Body.prevHeadRow = prevHeadRow;
	}

	public static int getPrevHeadCol() {
		return prevHeadCol;
	}

	public static void setPrevHeadCol(int prevHeadCol) {
		Body.prevHeadCol = prevHeadCol;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

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
		this.alive = true;
	}
	
	public static void movementCheck(ArrayList<Body> snakeBody) {
		
		Body headSnake = snakeBody.get(0);


		if(GDV5.KeysPressed[KeyEvent.VK_W] && headSnake.prevHeading != "down" && pressing == false) {
			headSnake.setHeading("up");
			headSnake.setPrevHeading("up");
		}
		else if(GDV5.KeysPressed[KeyEvent.VK_S] && headSnake.prevHeading != "up" && pressing == false) {
			headSnake.setHeading("down");
			headSnake.setPrevHeading("down");
		}
		else if(GDV5.KeysPressed[KeyEvent.VK_A] && headSnake.prevHeading != "right" && pressing == false) {
			headSnake.setHeading("left");
			headSnake.setPrevHeading("left");
		}
		else if(GDV5.KeysPressed[KeyEvent.VK_D] && headSnake.prevHeading != "left" && pressing == false) {
			headSnake.setHeading("right");
			headSnake.setPrevHeading("right");
		}	

		if(GDV5.KeysPressed[KeyEvent.VK_W] || GDV5.KeysPressed[KeyEvent.VK_S] || GDV5.KeysPressed[KeyEvent.VK_A] || GDV5.KeysPressed[KeyEvent.VK_D]){
			pressing = true;
		}
		else{
			pressing = false;
		}
		
	}
	
	public static void movement(ArrayList<Body> snakeBody, ArrayList<EnemyBody> enemyBody) {
		Body headSnake = snakeBody.get(0);
		if(headSnake.alive){
			llBodyRow = snakeBody.get(snakeBody.size() - 1).getRow();
			llBodyCol = snakeBody.get(snakeBody.size() - 1).getCol();

			if(headSnake.getHeading() != "none"){
				//System.out.println(true);
				for(int i = snakeBody.size() - 1; i > 0; i--) {
					snakeBody.get(i).setRow(snakeBody.get(i-1).getRow());
					snakeBody.get(i).setCol(snakeBody.get(i-1).getCol());
				}

			}
			
			if(headSnake.getHeading().equals("left")) {
				prevHeadCol = headSnake.getCol();
				prevHeadRow = headSnake.getCol();
				headSnake.setCol(headSnake.getCol() - 1);
				headSnake.setHeading("none");
				for(int i = 1; i < snakeBody.size(); i++){
					Body b = snakeBody.get(i);
					if(headSnake.getCol() == b.getCol() && headSnake.getRow() == b.getRow()){
						headSnake.alive = false;
					}
				}
			}
			else if(headSnake.getHeading().equals("right")) {
				prevHeadCol = headSnake.getCol();
				prevHeadRow = headSnake.getCol();
				headSnake.setCol(headSnake.getCol() + 1);
				headSnake.setHeading("none");
				for(int i = 1; i < snakeBody.size(); i++){
					Body b = snakeBody.get(i);
					if(headSnake.getCol() == b.getCol() && headSnake.getRow() == b.getRow()){
						headSnake.alive = false;
					}
				}
			}
			else if(headSnake.getHeading().equals("up")) {
				prevHeadCol = headSnake.getCol();
				prevHeadRow = headSnake.getCol();
				headSnake.setRow(headSnake.getRow() - 1);
				headSnake.setHeading("none");
				for(int i = 1; i < snakeBody.size(); i++){
					Body b = snakeBody.get(i);
					if(headSnake.getCol() == b.getCol() && headSnake.getRow() == b.getRow()){
						headSnake.alive = false;
					}
				}
			}
			else if(headSnake.getHeading().equals("down")) {
				prevHeadCol = headSnake.getCol();
				prevHeadRow = headSnake.getCol();
				headSnake.setRow(headSnake.getRow() + 1);
				headSnake.setHeading("none");
				for(int i = 1; i < snakeBody.size(); i++){
					Body b = snakeBody.get(i);
					if(headSnake.getCol() == b.getCol() && headSnake.getRow() == b.getRow()){
						headSnake.alive = false;
					}
				}
			}

			if(headSnake.getCol() < 0 || headSnake.getCol() > 29 || headSnake.getRow() > 17 || headSnake.getRow() < 0){
				headSnake.alive = false;
				//System.out.println(headSnake.alive);
			}

			for(EnemyBody e:enemyBody){
				if(headSnake.getCol() == e.getCol() && headSnake.getRow() == e.getRow()){
					headSnake.alive = false;
				}
			}
			
			if(SnakeScoreboard.getScore() == 10){
				win1 = true;
				SnakeScoreboard.setState(2);
				SnakeScoreboard.setGoal(25);
			}

			if(SnakeScoreboard.getScore() >= 25){
				headSnake.alive = false;
			}

		}
		
	}
	
	public static ArrayList<Body> makeBody() {
		ArrayList<Body> snakeBody = new ArrayList<Body>();
		Body b = new Body(7, 13);
		prevHeadCol = 13;
		prevHeadRow = 7;
		snakeBody.add(b);
	
		
		return snakeBody;
	}
	
	public static void appendBody(ArrayList<Body> snakeBody) {
		Body b = new Body(llBodyRow, llBodyCol);
		
		snakeBody.add(b);
	}
	
	
}
