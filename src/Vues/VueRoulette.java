package Vues;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;

public class VueRoulette extends JLabel implements Observer{

	public int indice_stat;
	public Game game;
	
	public VueRoulette(String s,Game g,int stat) {
		this.setText(s);
		this.game = g;
		this.indice_stat = stat;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if((indice_stat == 1)) {
			this.setText(this.game.roul1+"");
		}
		else if((indice_stat == 2)) {
			this.setText(this.game.roul2+"");
		}
		else if((indice_stat == 3)) {
			this.setText(this.game.roul3+"");
		}
	}

}

