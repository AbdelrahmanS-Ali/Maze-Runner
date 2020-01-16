package models;
public class RedBomb extends Bomb{

	private int x = 2;
	public RedBomb(){
		this.damage = 100;
		this.hp = 1;
	}
	
	public int touchHuman(int score){
		score = score - 30;
		return score;
	}
	
	public Object getObj(){
		this.x--;
		if(this.x > 0){
			return this;
		}
		else{
			return new explosion();
		}
	}
}
