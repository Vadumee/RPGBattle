package Vues;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;

public class VueShop extends JLabel implements Observer {

	public int indice_stat;
	public Game game;
	
	public VueShop(String s,Game g,int stat) {
		this.setText(s);
		this.game = g;
		this.indice_stat = stat;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(indice_stat == 1) {
			if(game.current_item_magasin != -1) {
				this.setText("Description : "+game.magasin.get(game.current_item_magasin).description);
			}
			else {
				this.setText("Description : -");
			}
		}
	}
}
