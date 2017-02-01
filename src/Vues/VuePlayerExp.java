package Vues;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;

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
			this.setText(this.game.joueur.level+" ("+this.game.joueur.exp+" / "+this.game.joueur.exp_max+")");
		}
		
	}

}

