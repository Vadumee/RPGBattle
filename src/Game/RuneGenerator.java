package Game;

public class RuneGenerator extends Item{
	
	public int rarity;

	public RuneGenerator(int i, int cst, String d, String n, Game g,int rar) {
		super(i, cst, d, n, g);
		this.rarity = rar;
	}
	
	public void utiliser() {
		super.utiliser();
		double alea = Math.random()*4;
		int alea1 = 1+(int)(alea - (alea%1));
		Rune r = new UpgradableRune(rarity,alea1,0,0,10000);
		game.inventaire_runes.add(r);
		this.quantity -= 1;
		this.game.eraseUsedItems();
	}

}
