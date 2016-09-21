package Vues;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;

public class VueStatPersonnage extends JLabel implements Observer{

	public int indice_stat;
	public Game game;
	
	public VueStatPersonnage(String s,Game g,int stat) {
		this.setText(s);
		this.game = g;
		this.indice_stat = stat;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(indice_stat == 1) {
			this.setText(this.game.joueur.level+" ("+this.game.joueur.exp+" / "+this.game.joueur.exp_max+")");
		}
		else if(indice_stat == 2) {
			int echantillon = new File("cards/"+1+"/").list().length;
			echantillon += new File("cards/"+2+"/").list().length;
			echantillon += new File("cards/"+3+"/").list().length;
			echantillon += new File("cards/"+4+"/").list().length;
			echantillon += new File("cards/"+5+"/").list().length;
			this.game.getCaptured();
			this.setText(this.game.mobs_captured+" / "+echantillon);
		}
		else if(indice_stat == 3) {
			this.setText(this.game.joueur.gold+"");
		}
		else if(indice_stat == 4) {
			this.setText(this.game.joueur.energy+" / "+this.game.joueur.max_energy);
		}
		else if(indice_stat == 5) {
			this.setText("Coût : "+(2+(this.game.joueur.level/2)));
		}
		else if(indice_stat == 6) {
			this.setText("Possières des flammes : "+game.joueur.fire_dust);
		}
		else if(indice_stat == 7) {
			this.setText("Possières verdoyantes : "+game.joueur.leaf_dust);
		}
		else if(indice_stat == 8) {
			this.setText("Possières de foudre : "+game.joueur.thunder_dust);
		}
		else if(indice_stat == 9) {
			this.setText("Possières abyssales : "+game.joueur.water_dust);
		}
	}

}

