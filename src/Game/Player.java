package Game;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

	public int level;
	public long exp;
	public long exp_max;
	public long gold;
	public ArrayList<Carte> collection;
	public int energy;
	public int max_energy;
	
	public int fire_dust;
	public int thunder_dust;
	public int leaf_dust;
	public int water_dust;
	
	public Player() {
		this.level = 1;
		this.exp = 0;
		this.exp_max = 100;
		this.gold = 1000;
		this.collection = new ArrayList<Carte>();
		this.energy = 40;
		this.max_energy = 40;
		this.fire_dust = 0;
		this.thunder_dust = 0;
		this.leaf_dust = 0;
		this.water_dust = 0;
	}
	
	public int giveExp(long xp) {
		if((this.exp + xp) >= this.exp_max) {
			long reste = xp - (this.exp_max - this.exp);
			this.exp = 0;
			this.level++;
			this.exp_max += (15*(this.level-1));
			this.max_energy += 5;
			this.energy = this.max_energy;
			this.giveExp(reste);
		}
		else {
			this.exp += xp;
		}
		return this.level;
	}
}
