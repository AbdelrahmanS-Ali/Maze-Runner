package controller;
import java.awt.Point;
import java.awt.Toolkit;

import Gui.ArrayDraw;
import mazeGeneration.mazeGenerate;
import models.Bullet;
import models.Gate;
import models.GiftOfBullets;
import models.GiftOfHealth;
import models.GreenBomb;
import models.Human;
import models.RedBomb;
import models.Road;
import models.StoneWall;
import models.TreeWall;
import models.explosion;



public class Control {

	public int rows;
	public int columnes;
	private int score = 30;
	private int life = 3;
	private int bullets = 6;
	private int[][] maze; 
	private Object[][] mazeComp;
	public Point person = new Point();
	private boolean cheak = false;
	mazeGenerate m = new mazeGenerate();
	
	
	
	public void setDimentions(int rows,int columnes){
		this.rows = rows;
		this.columnes = columnes;
		
		createMaze();
		createMazeComp();
	}
	
	public void createMaze(){
		maze = m.getMazeMap(rows, columnes);
	
		for(int i=0;i<maze.length;i++){
			for(int j=0;j<maze[0].length;j++){
				if(maze[i][j] == 2){
					person.setLocation(j, i);
				}
			}
		}
		
	}
	
	public void createMazeComp() {
		mazeComp = new Object[rows][columnes];
		
		for(int i=0;i<rows;i++){
			for(int j=0;j<columnes;j++){
				if(maze[i][j] == 0){ 
					Road road = new Road();
					mazeComp[i][j] = road;
				}
				else if(maze[i][j] == 1){
					StoneWall stone = new StoneWall();
					mazeComp[i][j] = stone; 
				}
				else if(maze[i][j] == 2){
					Human human = new Human();
					mazeComp[i][j] = human;
					human.setPosition(j, i);
				}
				else if(maze[i][j] == 3){
					TreeWall tree = new TreeWall();
					mazeComp[i][j] = tree;
				}
				else if(maze[i][j] == 4){
					RedBomb rBomb = new RedBomb();
					mazeComp[i][j] = rBomb;
				}
				else if(maze[i][j] == 5){
					GreenBomb gBomb = new GreenBomb();
					mazeComp[i][j] = gBomb;
					
				}
				else if(maze[i][j] == 6){
					GiftOfHealth healthGift = new GiftOfHealth();
					mazeComp[i][j] = healthGift;
				}
				else if(maze[i][j] == 7){
					GiftOfBullets bulletGift = new GiftOfBullets();
					mazeComp[i][j] = bulletGift;
				}
				else if(maze[i][j] == 8){
					Gate gate = new Gate();
					mazeComp[i][j] = gate;
				}
			}
		}
	}
	
	public int getScore(){
		return this.score;
	}
	
	public int getLife(){
		return this.life;
	}
	
	public int getBullets(){
		return this.bullets;
	}
	
	public Object[][] getMazeComp(){
		
		
		return mazeComp;
	}
	
	public void clickRight(){
		
		if(person.getX() < columnes-1){
			Object o = mazeComp[(int) person.getY()][(int) (person.getX()+1)];
			if(!(o instanceof StoneWall || o instanceof TreeWall)){
				person.x++;
				update(person.x, person.y);
			}
		}
	}
	
	public void clickLeft(){
		if(person.getX() > 0){
			Object o = mazeComp[(int) person.getY()][(int) (person.getX()-1)];
			if(!(o instanceof StoneWall || o instanceof TreeWall)){
				person.x--;
				update(person.x, person.y);
			}
		}
	}
	
	public void clickTop(){
		if(person.getY() > 0){
			Object o = mazeComp[(int) person.getY()-1][(int) (person.getX())];
			if(!(o instanceof StoneWall || o instanceof TreeWall)){
				person.y--;
				update(person.x, person.y);
			}
		}
	}
	
	public void clickBottom(){
		if(person.getY() < rows-1){
			Object o = mazeComp[(int) person.getY()+1][(int) (person.getX())];
			if(!(o instanceof StoneWall || o instanceof TreeWall)){
				person.y++;
				update(person.x, person.y);
			}
		}
	}
	
	public void update(int x,int y){
	
		int preX = 0,preY = 0;
		for(int i=0;i<mazeComp.length;i++){
			for(int j=0;j<mazeComp[0].length;j++){
				if(mazeComp[i][j] instanceof Human){
					Human h = (Human) mazeComp[i][j];
					preX = h.getX();
					preY = h.getY();
				}
			}
		}
		
		
		
		if(mazeComp[y][x] instanceof GreenBomb){
			GreenBomb gBomb = (GreenBomb) mazeComp[y][x];
			this.score = gBomb.touchHuman(this.score);
			Toolkit.getDefaultToolkit().beep();
		}
		else if(mazeComp[y][x] instanceof RedBomb){
			RedBomb rBomb = (RedBomb) mazeComp[y][x];
			this.score = rBomb.touchHuman(this.score);
			Toolkit.getDefaultToolkit().beep();
		}
		else if(mazeComp[y][x] instanceof GiftOfHealth){
			GiftOfHealth healthGift = (GiftOfHealth) mazeComp[y][x];
			this.life++;
		}
		else if(mazeComp[y][x] instanceof GiftOfBullets){
			GiftOfBullets bulletGift = (GiftOfBullets) mazeComp[y][x];
			this.bullets++;
		}
		else if(mazeComp[y][x] instanceof Gate){
			Gate gate = (Gate) mazeComp[y][x];
			
		}
		else if(mazeComp[y][x] instanceof Road){
			Road road = (Road) mazeComp[y][x];
		
		}
		
		
		Human h = (Human) mazeComp[preY][preX];
		h.setPosition(x, y);
		mazeComp[y][x] = null;
		mazeComp[y][x] = h;
		
		mazeComp[preY][preX] = null;
		mazeComp[preY][preX] = new Road();
	}
	
	public void setMazeObj(Object[][] m){
		this.mazeComp = m;
	}
}
