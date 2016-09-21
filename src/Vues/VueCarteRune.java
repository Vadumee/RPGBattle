package Vues;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;

public class VueCarteRune extends JLabel implements Observer {

	public Game game;
	
	public VueCarteRune(String s,Game g,int stat) {
		this.setText(s);
		this.game = g;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(game.current_card_runed != -1) {
			this.setText(game.joueur.collection.get(game.current_card_runed).getStats());
		}
		else {
			this.setText("-");
		}
	}
}
