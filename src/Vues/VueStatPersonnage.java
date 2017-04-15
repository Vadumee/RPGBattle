package Vues;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;
import Game.Maths;

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
			this.setText(Maths.format(this.game.joueur.level)+" ("+Maths.format(this.game.joueur.exp)+" / "+Maths.format(this.game.joueur.exp_max)+")");
		}
		else if(indice_stat == 2) {
			int echantillon = new File("cards/"+1+"/").list().length;
			echantillon += new File("cards/"+2+"/").list().length;
			echantillon += new File("cards/"+3+"/").list().length;
			echantillon += new File("cards/"+4+"/").list().length;
			echantillon += new File("cards/"+5+"/").list().length;
			echantillon += new File("cards/"+6+"/").list().length;
			this.game.getCaptured();
			this.setText(this.game.mobs_captured+" / "+echantillon);
		}
		else if(indice_stat == 3) {
			this.setText(Maths.format(this.game.joueur.gold)+"");
		}
		else if(indice_stat == 4) {
			this.setText(Maths.format(this.game.joueur.energy)+" / "+Maths.format(this.game.joueur.max_energy));
		}
		else if(indice_stat == 5) {
			this.setText("Coût : "+Maths.format((2+(this.game.joueur.level/2))));
		}
		else if(indice_stat == 6) {
			this.setText("Possières des flammes : "+Maths.format(game.joueur.fire_dust));
		}
		else if(indice_stat == 7) {
			this.setText("Possières verdoyantes : "+Maths.format(game.joueur.leaf_dust));
		}
		else if(indice_stat == 8) {
			this.setText("Possières de foudre : "+Maths.format(game.joueur.thunder_dust));
		}
		else if(indice_stat == 9) {
			this.setText("Possières abyssales : "+Maths.format(game.joueur.water_dust));
		}
	}

}

