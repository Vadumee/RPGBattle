package Game;

public class Potion extends Item {
	
	public int energy_give;

	public Potion(int i, int cst, String d,String n, Game g, int xg) {
		super(i, cst, d,n, g);
		this.energy_give = xg;
		this.nom += ("("+energy_give+")");
	}
	
	public void utiliser() {
		super.utiliser();
		if((game.joueur.energy + energy_give) >= game.joueur.max_energy) {
			game.joueur.energy = game.joueur.max_energy;
		}
		else {
			game.joueur.energy += energy_give;
		}
		this.quantity -= 1;
		game.eraseUsedItems();
	}

}
