package snake;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import utilities.GDV5;

public class EnemyBody {
	
	private int row;
	private int col;
	private String heading;
	private String prevHeading;
	private static int llBodyRow;
	private static int llBodyCol;
	private int prevHeadRow;
	private int prevHeadCol;
	private boolean pressing;
	private boolean alive;
	private static int stun;

	public String getPrevHeading() {
		return prevHeading;
	}

	public void setPrevHeading(String prevHeading) {
		this.prevHeading = prevHeading;
	}

	public int getPrevHeadRow() {
		return prevHeadRow;
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

	public EnemyBody(int row, int col) {
		this.row = row;
		this.col = col;
		this.heading = "";
		this.alive = true;

	}
	
	public static void movement(ArrayList<EnemyBody> enemyBody, ArrayList<Fruit> fruitList) {
		EnemyBody headEnemy = enemyBody.get(0);
		Fruit closest = fruitList.get(0);

		for(int i = enemyBody.size() - 1; i > 0; i--) {
			enemyBody.get(i).setRow(enemyBody.get(i-1).getRow());
			enemyBody.get(i).setCol(enemyBody.get(i-1).getCol());
		}

		for(Fruit f:fruitList){
			int dist = Math.abs(headEnemy.getRow() - f.getRow()) + Math.abs(headEnemy.getCol() - f.getCol());
			int smallDist = Math.abs(headEnemy.getRow() - closest.getRow()) + Math.abs(headEnemy.getCol() - closest.getCol());
			if(dist < smallDist) closest = f;
			
		}

		System.out.println(closest);
		int rowDist = Math.abs(headEnemy.getRow() - closest.getRow());
		int colDist = Math.abs(headEnemy.getRow() - closest.getCol());

		if(headEnemy.getRow() == closest.getRow()){
			if(headEnemy.getCol() > closest.getCol()){
				headEnemy.heading = "left";
			}
			else{
				headEnemy.heading = "right";
			}
		}
		else if(headEnemy.getCol() == closest.getCol()){
			if(headEnemy.getRow() > closest.getRow()){
				headEnemy.heading = "up";
			}
			else{
				headEnemy.heading = "down";
			}
		}
		else{
			if(rowDist > colDist){
				if(headEnemy.getCol() > closest.getCol()){
					headEnemy.heading = "left";
				}
				else{
					headEnemy.heading = "right";
				}
			}
			else{
				if(headEnemy.getRow() > closest.getRow()){
					headEnemy.heading = "up";
				}
				else{
					headEnemy.heading = "down";
				}
			}
		}

		if(headEnemy.getHeading().equals("left")) {
			headEnemy.setCol(headEnemy.getCol() - 1);
		}
		else if(headEnemy.getHeading().equals("right")) {
			headEnemy.setCol(headEnemy.getCol() + 1);
		}
		else if(headEnemy.getHeading().equals("up")) {
			headEnemy.setRow(headEnemy.getRow() - 1);
		}
		else if(headEnemy.getHeading().equals("down")) {
			headEnemy.setRow(headEnemy.getRow() + 1);
		}

		llBodyRow = enemyBody.get(enemyBody.size() - 1).getRow();
		llBodyCol = enemyBody.get(enemyBody.size() - 1).getCol();
		
	}
	
	public static ArrayList<EnemyBody> makeBody() {
		ArrayList<EnemyBody> EnemyBody = new ArrayList<EnemyBody>();
		EnemyBody b = new EnemyBody((int) (Math.random() * 18), (int) (Math.random() * 30)); //EnemyBody((int) (Math.random() * 18), (int) (Math.random() * 30));
		EnemyBody.add(b);
	
		return EnemyBody;
	}
	
	public static void appendBody(ArrayList<EnemyBody> EnemyBody) {
		EnemyBody b = new EnemyBody(llBodyRow, llBodyCol);
		
		EnemyBody.add(b);
	}
}
