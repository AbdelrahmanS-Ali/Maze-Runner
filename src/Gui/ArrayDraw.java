package Gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.Control;
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

public class ArrayDraw extends JPanel {
	
	private ImageIcon bomb = new ImageIcon("res/Bomb-icon.png");
	private ImageIcon tnt = new ImageIcon("res/TNT-icon.png");
	
	private ImageIcon gift = new ImageIcon("res/Gift-icon.png");
	private ImageIcon gun = new ImageIcon("res/Gun-icon.png");
	
	private ImageIcon tree = new ImageIcon("res/Tree-icon.png");
	private ImageIcon brickWall = new ImageIcon("res/brick-wall-icon.png");
	
	private ImageIcon man = new ImageIcon("res/Man-icon.png");
	private ImageIcon door = new ImageIcon("res/door-icon.png");
	
	private ImageIcon rightMan = new ImageIcon("res/Man-icon.png");
	private ImageIcon leftMan = new ImageIcon("res/left-icon.png");
	private ImageIcon upMan = new ImageIcon("res/up-icon.png");
	private ImageIcon downMan = new ImageIcon("res/down-icon.png");
	
	private ImageIcon spiderman =new ImageIcon("res/spiderman-icon.png");
	private ImageIcon ironman = new ImageIcon("res/ironman-icon.png");
	private ImageIcon hulk = new ImageIcon("res/hulk-icon.png");
	private ImageIcon america = new ImageIcon("res/america-icon.png");
	private ImageIcon ball = new ImageIcon("res/Ball-icon.png");
	
	private int x =200, y = 10 , velx , vely = 0;
	
	private int imageWidth = 24;
	private int iamgeHeigth = 24;
	private int leftStart = 5;
	private int upStart = 10;
	
	
	Control control = new Control();
	private Object[][] maze;
	
	

	
	public void start(){
		control.setDimentions(25, 25);
		maze = control.getMazeComp();
		repaint();
	}
	
	public void drawing() {
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int i = this.leftStart ; 
		int temp = this.leftStart;
		int j = this.upStart ;
		
		
	    for (int m = 0; m < maze.length; m ++){
	        for (int n = 0; n < maze[0].length; n++){
	        	if(maze[m][n] instanceof GreenBomb) {
	        		ImageObserver observer = null;
					g.drawImage(bomb.getImage(), i, j,this.imageWidth, this.iamgeHeigth, observer);
	        		//bomb.paintIcon(this, g, i, j);
	        	}
	            if(maze[m][n] instanceof TreeWall) {
	            	ImageObserver observer = null;
					g.drawImage(tree.getImage(), i, j,this.imageWidth, this.iamgeHeigth, observer);
	            	//tree.paintIcon(this, g, i, j);
	            }
	            if(maze[m][n] instanceof GiftOfHealth) {
	            	ImageObserver observer = null;
					g.drawImage(gift.getImage(), i, j,this.imageWidth, this.iamgeHeigth, observer);
	            	//gift.paintIcon(this, g, i, j);
	            }
	            if(maze[m][n] instanceof GiftOfBullets) {
	            	ImageObserver observer = null;
					g.drawImage(gun.getImage(), i, j,this.imageWidth, this.iamgeHeigth, observer);
	            	//gun.paintIcon(this, g, i, j);
	            }
	            if(maze[m][n] instanceof StoneWall) {
	            	ImageObserver observer = null;
					g.drawImage(brickWall.getImage(), i, j,this.imageWidth, this.iamgeHeigth, observer);
	            	//brickWall.paintIcon(this, g, i, j);
	            }
	            if(maze[m][n] instanceof RedBomb) {
	            	ImageObserver observer = null;
					g.drawImage(tnt.getImage(), i, j,this.imageWidth, this.iamgeHeigth, observer);
	            	//tnt.paintIcon(this, g, i, j);
	            }
	            if(maze[m][n] instanceof Human) {
	         	    ImageObserver observer = null;
					g.drawImage(man.getImage(), i, j,this.imageWidth, this.iamgeHeigth, observer);
	            	//man.paintIcon(this, g, i, j);
	            }
	            if(maze[m][n] instanceof Gate) {
	            	ImageObserver observer = null;
					g.drawImage(door.getImage(), i, j,this.imageWidth, this.iamgeHeigth, observer);
	            	//door.paintIcon(this, g, i, j);
	            }
	            if(maze[m][n] instanceof Bullet){
	            	
	            	ImageObserver observer = null;
					g.drawImage(ball.getImage(), i, j,this.imageWidth, this.iamgeHeigth, observer);
	            }
	            if(maze[m][n] instanceof explosion){
	            	ImageObserver observer = null;
					g.drawImage(ball.getImage(), i, j,this.imageWidth, this.iamgeHeigth, observer);
	            }
	            i = i + this.imageWidth;
	        }
	        j=j+this.iamgeHeigth;
	        i = temp;
	    }
	}
	
	public void up() {
		this.setUpImage();
		control.clickTop();
		this.maze = control.getMazeComp();
		repaint();
	}
	
	public void down() {
		this.setDownImage();
		control.clickBottom();
		this.maze = control.getMazeComp();
		repaint();
	}
	
	public void right() {
	
		this.setRightImage();
		control.clickRight();
		this.maze = control.getMazeComp();
		
		repaint();
	}
	
	public void left() {
		this.setLeftImage();
		control.clickLeft();
		this.maze = control.getMazeComp();
		repaint();
	}
	
	public void fireR(){
		this.setRightImage();
		this.maze = control.getMazeComp();
		
		int x = (int) control.person.getX();
		int y = (int) control.person.getY();
		int xend = 0,yend = 0;
		if(x < control.columnes-1){
			
			for(int i=x+1;i<control.columnes;i++){
				
				if(maze[y][i] instanceof RedBomb){
					
					RedBomb r = (RedBomb) maze[y][i];
			
					if(i == x+1){
						maze[y][i] = null;
						maze[y][i] = r.getObj();
						
						repaint();
					}
					else{
						maze[y][i] = null;
						maze[y][i] = r.getObj();
						maze[y][i-1] = null;
						maze[y][i-1] = new Road();
					
						repaint();
					}
					xend=i;
					yend=y;
					
					break;
				}
				else if(maze[y][i] instanceof GreenBomb){
					if(i == x+1){
						maze[y][i] = null;
						maze[y][i] = new explosion();
						
						repaint();
					}
					else{
						maze[y][i] = null;
						maze[y][i] = new explosion();
						maze[y][i-1] = null;
						maze[y][i-1] = new Road();
					
						repaint();
					}
					xend=i;
					yend=y;
					break;
				}
				else if(maze[y][i] instanceof GiftOfBullets || maze[y][i] instanceof GiftOfHealth){
					if(i == x+1){
						maze[y][i] = null;
						maze[y][i] = new explosion();
						
						repaint();
					}
					else{
						maze[y][i] = null;
						maze[y][i] = new explosion();
						maze[y][i-1] = null;
						maze[y][i-1] = new Road();
					
						repaint();
					}
					xend=i;
					yend=y;
					break;
				}
				else if(maze[y][i] instanceof TreeWall){
					if(i == x+1){
						maze[y][i] = null;
						maze[y][i] = new explosion();
						
						repaint();
					}
					else{
						maze[y][i] = null;
						maze[y][i] = new explosion();
						maze[y][i-1] = null;
						maze[y][i-1] = new Road();
					
						repaint();

					}
					xend=i;
					yend=y;
					break;
				}
				else if(maze[y][i] instanceof StoneWall || maze[y][i] instanceof Gate){
					xend=i;
					yend=y;
					break;
				}
			}
			
			if(maze[yend][xend] instanceof explosion){
				maze[yend][xend] = null;
				maze[yend][xend] = new Road();
				repaint();
			}
			else if(!(maze[yend][xend-1] instanceof Human)){
				maze[yend][xend-1] = null;
				maze[yend][xend-1] = new Road();
				repaint();
			}
			
			control.setMazeObj(maze);
		}
	}
	
	public void fireL(){
		this.setLeftImage();
		this.maze = control.getMazeComp();
		
		int x = (int) control.person.getX();
		int y = (int) control.person.getY();
		int xend = 0,yend = 0;
		
		if(x > 0){
			for(int i=x-1;i>=0;i--){
				
				 if(maze[y][i] instanceof RedBomb){
					
					RedBomb r = (RedBomb) maze[y][i];
					
					if(i == x-1){
						maze[y][i] = null;
						maze[y][i] = r.getObj();
						repaint();
					}
					else{
						maze[y][i] = null;
						maze[y][i] = r.getObj();
						maze[y][i+1] = null;
						maze[y][i+1] = new Road();
						repaint();
					}
					xend=i;
					yend=y;
					
					break;
				}
				else if(maze[y][i] instanceof GreenBomb){
					if(i == x-1){
						maze[y][i] = null;
						maze[y][i] = new explosion();
						repaint();
					}
					else{
						maze[y][i] = null;
						maze[y][i] = new explosion();
						maze[y][i+1] = null;
						maze[y][i+1] = new Road();
						repaint();
					}
					xend=i;
					yend=y;
					
					break;
				}
				else if(maze[y][i] instanceof GiftOfBullets || maze[y][i] instanceof GiftOfHealth){
					if(i == x-1){
						maze[y][i] = null;
						maze[y][i] = new explosion();
						repaint();
					}
					else{
						maze[y][i] = null;
						maze[y][i] = new explosion();
						maze[y][i+1] = null;
						maze[y][i+1] = new Road();
						repaint();
					}
					xend=i;
					yend=y;
					
					break;
				}
				else if(maze[y][i] instanceof TreeWall){
					if(i == x-1){
						maze[y][i] = null;
						maze[y][i] = new explosion();
						repaint();
					}
					else{
						maze[y][i] = null;
						maze[y][i] = new explosion();
						maze[y][i+1] = null;
						maze[y][i+1] = new Road();
						repaint();
					}
					xend=i;
					yend=y;
					
					break;
				}
				else if(maze[y][i] instanceof StoneWall || maze[y][i] instanceof Gate){
					xend=i;
					yend=y;
					break;
				}
			}
			if(maze[yend][xend] instanceof explosion){
				maze[yend][xend] = null;
				maze[yend][xend] = new Road();
				repaint();
			}
			else if(!(maze[yend][xend+1] instanceof Human)) {
				maze[yend][xend+1] = null;
				maze[yend][xend+1] = new Road();
				repaint();
			}
			control.setMazeObj(maze);
		}
	}
	
	public void fireU(){
		this.setUpImage();
		this.maze = control.getMazeComp();
		
		int x = (int) control.person.getX();
		int y = (int) control.person.getY();
		int xend = 0,yend = 0;
		if(y > 0){
			for(int i=y-1;i>=0;i--){
				if(maze[i][x] instanceof RedBomb){
					
					RedBomb r = (RedBomb) maze[i][x];
					
					if(i == y-1){
						maze[i][x] = null;
						maze[i][x] = r.getObj();
						repaint();
					}
					else{
						maze[i][x] = null;
						maze[i][x] = r.getObj();
						maze[i+1][x] = null;
						maze[i+1][x] = new Road();
						repaint();
					}
					xend=i;
					yend=x;
					
					break;
				}
				else if(maze[i][x] instanceof GreenBomb){
					if(i == y-1){
						maze[i][x] = null;
						maze[i][x] = new explosion();
						repaint();
					}
					else{
						maze[i][x] = null;
						maze[i][x] = new explosion();
						maze[i+1][x] = null;
						maze[i+1][x] = new Road();
						repaint();
					}
					xend=i;
					yend=x;
					
					break;
				}
				else if(maze[i][x] instanceof GiftOfBullets || maze[i][x] instanceof GiftOfHealth){
					if(i == y-1){
						maze[i][x] = null;
						maze[i][x] = new explosion();
						repaint();
					}
					else{
						maze[i][x] = null;
						maze[i][x] = new explosion();
						maze[i+1][x] = null;
						maze[i+1][x] = new Road();
						repaint();
					}
					xend=i;
					yend=x;
					
					break;
				}
				else if(maze[i][x] instanceof TreeWall){
					if(i == y-1){
						maze[i][x] = null;
						maze[i][x] = new explosion();
						repaint();
					}
					else{
						maze[i][x] = null;
						maze[i][x] = new explosion();
						maze[i+1][x] = null;
						maze[i+1][x] = new Road();
						repaint();
					}
					xend=i;
					yend=x;
					
					break;
				}
				else if(maze[i][x] instanceof StoneWall || maze[i][x] instanceof Gate){
					xend=i;
					yend=x;
					break;
				}
			}
			if(maze[xend][yend] instanceof explosion){
				maze[xend][yend] = null;
				maze[xend][yend] = new Road();
				repaint();
			}
			else if(!(maze[xend+1][yend] instanceof Human)) {
				maze[xend+1][yend] = null;
				maze[xend+1][yend] = new Road();
				repaint();
			}
			control.setMazeObj(maze);
		}
	}

	public void fireD(){
		this.setDownImage();
		this.maze = control.getMazeComp();
		
		int x = (int) control.person.getX();
		int y = (int) control.person.getY();
		int xend = 0,yend = 0;
		if(y < control.rows-1){
			for(int i=y+1;i<control.rows;i++){
				
				 if(maze[i][x] instanceof RedBomb){
					
					RedBomb r = (RedBomb) maze[i][x];
					
					if(i == y+1){
						maze[i][x] = null;
						maze[i][x] = r.getObj();
						repaint();
					}
					else{
						maze[i][x] = null;
						maze[i][x] = r.getObj();
						maze[i-1][x] = null;
						maze[i-1][x] = new Road();
						repaint();
					}
					xend=i;
					yend=x;
					
					break;
				}
				else if(maze[i][x] instanceof GreenBomb){
					if(i == y+1){
						maze[i][x] = null;
						maze[i][x] = new explosion();
						repaint();
					}
					else{
						maze[i][x] = null;
						maze[i][x] = new explosion();
						maze[i-1][x] = null;
						maze[i-1][x] = new Road();
						repaint();
					}
					
					xend=i;
					yend=x;
					
					break;
				}
				else if(maze[i][x] instanceof GiftOfBullets || maze[i][x] instanceof GiftOfHealth){
					if(i == y+1){
						maze[i][x] = null;
						maze[i][x] = new explosion();
						repaint();
					}
					else{
						maze[i][x] = null;
						maze[i][x] = new explosion();
						maze[i-1][x] = null;
						maze[i-1][x] = new Road();
						repaint();
					}
					
					xend=i;
					yend=x;
					
					break;
				}
				else if(maze[i][x] instanceof TreeWall){
					if(i == y+1){
						maze[i][x] = null;
						maze[i][x] = new explosion();
						repaint();
					}
					else{
						maze[i][x] = null;
						maze[i][x] = new explosion();
						maze[i-1][x] = null;
						maze[i-1][x] = new Road();
						repaint();
					}
					
					xend=i;
					yend=x;
					
					break;
				}
				else if(maze[i][x] instanceof StoneWall || maze[i][x] instanceof Gate){
					xend=i;
					yend=x;
					break;
				}
			}
			if(maze[xend][yend] instanceof explosion){
				maze[xend][yend] = null;
				maze[xend][yend] = new Road();
				repaint();
			}
			else if(!(maze[xend-1][yend] instanceof Human)) {
				maze[xend-1][yend] = null;
				maze[xend-1][yend] = new Road();
				repaint();
			}
			control.setMazeObj(maze);
		}
	}
	
	private void setLeftImage(){
		this.man = this.leftMan;
	}
	
	private void setUpImage(){
		this.man = this.upMan;
	}
	
	private void setDownImage(){
		this.man = this.downMan;
	}
	
	private void setRightImage(){
		this.man = this.rightMan;
	}
	
}
