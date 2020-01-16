package models;
public class Human {
	public int health;
	public int score;
	public int x, y;
	public int bullets = 10;
	private static Human instance = new Human();
	
	public Human(){}
	
	public static Human getInstance(){
		return instance;
	}
	
	public void setPosition(int a, int b) {
		x = a;
		y = b;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void touchBomb(Bomb bomb) {
		this.health -= bomb.getDamage();
	}
	public void fire(Bomb bomb) {
		if (bullets > 0) {
			bullets--;	
			bomb.hp--;
		}
		else return;
	}
}
