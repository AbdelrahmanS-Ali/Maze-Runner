package models;
public class GreenBomb extends Bomb {

	public GreenBomb(){
		this.damage = 50;
		this.hp = 1;
	}
	public int touchHuman(int score){
		score = score - 20;
		return score;
	}
}
