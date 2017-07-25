package Vues;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;
import Game.GameV2;

public class VueCarteBossPreview extends JLabel implements Observer {
	
	public Game game;
	public int indice;
	public int indice_fighter;
	
	public VueCarteBossPreview(String s, Game g, int i,int f) {
		super(s);
		this.game = g;
		this.indice = i;
		this.indice_fighter = f;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(indice == 1) {
			if(game.indice_fighters[this.indice_fighter-1] != -1) {
				this.setText(game.joueur.collection.get(game.indice_fighters[this.indice_fighter-1]).name);
			}
			else {
				this.setText("");
			}
		}
		else if(indice == 2) {
			if(game.indice_fighters[this.indice_fighter-1] != -1) {
				this.setText((((GameV2)game).getStatsFight(game.joueur.collection.get(game.indice_fighters[this.indice_fighter-1]))));
			}
			else {
				this.setText("");
			}
		}
	}

}
