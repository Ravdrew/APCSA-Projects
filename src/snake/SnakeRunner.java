package snake;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

import utilities.GDV5;
import utilities.SoundDriverHo;

public class SnakeRunner extends GDV5{
	private static Grid[][] gridTotal;
	private static ArrayList<Body> snakeBody;
	private static ArrayList<EnemyBody> enemyBody;
	private static ArrayList<Fruit> fruitList;
	private static int ticks;
	private Images images = new Images();
	private static SoundDriverHo s1;
	private String[] filenames = new String[4];
		
	public static SoundDriverHo getS1() {
		return s1;
	}

	public static void setS1(SoundDriverHo s1) {
		this.s1 = s1;
	}

	public SnakeRunner() {
		gridTotal = Grid.makeGrid();
		snakeBody = Body.makeBody();
		fruitList = Fruit.makeFruit();
		enemyBody = EnemyBody.makeBody();
		filenames[0] = "bang2.wav";
		filenames[1] = "omnom.wav";
		filenames[2] = "yum.wav";
		filenames[3] = "grr.wav";
		//filenames[0] = "hungy.wav";
		s1 = new SoundDriverHo(filenames, this);
		s1.play(0);
		s1.setVolume(0, (float) -15);
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
		
		if(SnakeScoreboard.getState() >= 1){
			if(Body.isWin1()){
				for(int i = snakeBody.size() - 1; i>0; i--){
					snakeBody.remove(i);
				}
				Body.setWin1(false);
			}
			Body.movementCheck(snakeBody);
			Fruit.orderFruit(fruitList, snakeBody, enemyBody);
			Body.movement(snakeBody, enemyBody);
			if(tick()){
				EnemyBody.movement(enemyBody, fruitList);
			}
		}
	
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub

		if(SnakeScoreboard.getState() > -1){
			//win.drawImage(images.pika, 2 + snakeBody.get(0).getCol() * 30, 2 + snakeBody.get(0).getRow() * 30, this);
			Grid.drawGrid(win, gridTotal, snakeBody, enemyBody);
			Fruit.drawFruit(win, fruitList);
			SnakeScoreboard.draw(win);
			if(GDV5.KeysPressed[KeyEvent.VK_ENTER]){
				gridTotal = Grid.makeGrid();
				snakeBody = Body.makeBody();
				fruitList = Fruit.makeFruit();
				enemyBody = EnemyBody.makeBody();
				Body.setWin1(false);
				SnakeScoreboard.setScore(0);
				
				ticks = 0;
				SnakeScoreboard.setState(1);
			}
			win.drawImage(images.pika, 2 + snakeBody.get(0).getCol() * 30, 62 + snakeBody.get(0).getRow() * 30, this);
			
		}
	
		else if(SnakeScoreboard.getState() == -1){
			
			SnakeScoreboard.startAnimation(win);
			if(GDV5.KeysPressed[KeyEvent.VK_SPACE]){
				System.out.println("go");
				SnakeScoreboard.setState(1);
			}
		}
	}

}
