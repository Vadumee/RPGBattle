package Vues;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;
import Game.Maths;

public class VueItem extends JLabel implements Observer {
	
	public int indice_stat;
	public Game game;
	
	public VueItem(String s,Game g,int stat) {
		this.setText(s);
		this.game = g;
		this.indice_stat = stat;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(indice_stat == 1) {
			if(game.current_item_selected != -1) {
				this.setText(game.inventaire.get(game.current_item_selected).nom);
			}
			else {
				this.setText("Nom de l'objet");
			}
		}
		else if(indice_stat == 2) {
			if(game.current_item_selected != -1) {
				this.setText(game.inventaire.get(game.current_item_selected).description);
			}
			else {
				this.setText("Description : - ");
			}
		}
		else if(indice_stat == 3) {
			if(arg1 != null) {
				int i = (int)arg1;
				if(i == 3) {
					if(game.current_item_selected != -1 && game.inventaire.size() > 0) {
						if(game.inventaire.get(game.current_item_selected).quantity == 0) {
							game.current_item_selected = -1;
						}
					}
				}
			}
			if(game.current_item_selected != -1) {
				this.setText("Quantité : "+Maths.format(game.inventaire.get(game.current_item_selected).quantity));
			}
			else {
				this.setText("Quantité : -");
			}	
		}
	}

}
