package Vues;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;
import Game.Maths;

public class VuePlayerExp extends JLabel implements Observer{

	public int indice_stat;
	public Game game;
	
	public VuePlayerExp(String s,Game g,int stat) {
		this.setText(s);
		this.game = g;
		this.indice_stat = stat;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(indice_stat == 1) {
			this.setText(Maths.format(this.game.joueur.level)+" ("+Maths.format(this.game.joueur.exp)+" / "+Maths.format(this.game.joueur.exp_max)+")");
		}
		
	}

}

