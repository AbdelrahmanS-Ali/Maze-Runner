package models;
public class TreeWall extends Wall {
	 public TreeWall() {
		 this.destroyable = true;
	 }
	 
	 public int touchHuman(int score){
			score = score - 10;
			return score;
	}
}
