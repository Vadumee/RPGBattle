package Game;

import java.io.Serializable;

public class Item implements Serializable {

	public int id;
	public int quantity;
	public int cost;
	public String nom;
	public String description;
	public Game game;
	public boolean to_delete = false;
	
	public Item(int i, int cst, String d,String n, Game g) {
		this.id = i;
		this.cost = cst;
		this.description = d;
		this.quantity = 1;
		this.nom = n;
		this.game = g;
	}
	
	public void utiliser() {
		
	}
}
