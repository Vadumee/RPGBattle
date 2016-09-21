package Vues;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;

public class VueRune extends JLabel implements Observer{

	public int indice_stat;
	public int indice_rune;
	public int type;
	public Game game;
	
	public VueRune(String s,Game g,int stat,int ind_rune,int t) {
		this.setText(s);
		this.game = g;
		this.indice_rune = ind_rune;
		this.indice_stat = stat;
		this.type = t;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(type == 1) {
			if(indice_stat == 1) {
				if(game.current_rune_selected == -1) {
					this.setText("-");
				}
				else {
					this.setText(game.inventaire_runes.get(game.current_rune_selected).getNom());
				}
			}
			else if(indice_stat == 2) {
				if(game.current_rune_selected == -1) {
					this.setText("-");
				}
				else {
					this.setText(game.inventaire_runes.get(game.current_rune_selected).getDescr());
				}
			}
		}
		else if(type == 2) {
			if(indice_stat == 1) {
				if(game.current_card_runed != -1 && game.joueur.collection.get(game.current_card_runed).runes[indice_rune] != null) {
					this.setText(game.joueur.collection.get(game.current_card_runed).runes[indice_rune].getNom());
				}
				else {
					this.setText("-");
				}
			}
			else if(indice_stat == 2) {
				if(game.current_card_runed != -1 && game.joueur.collection.get(game.current_card_runed).runes[indice_rune] != null) {
					this.setText(game.joueur.collection.get(game.current_card_runed).runes[indice_rune].getDescr());
				}
				else {
					this.setText("-");
				}
			}
		}
	}

}
