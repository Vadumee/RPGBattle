package Vues;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;
import Game.GameV2;
import Game.Maths;

public class VueStatCard extends JLabel implements Observer{

	public int indice_stat;
	public Game game;
	
	public VueStatCard(String s,Game g,int stat) {
		this.setText(s);
		this.game = g;
		this.indice_stat = stat;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(indice_stat == 1) {
			if(game.current_card_id == -1) {
				this.setText("Nom de Carte");
			}
			else {
				this.setText(game.joueur.collection.get(game.current_card_id).name+", "+game.joueur.collection.get(game.current_card_id).titre);
			}
		}
		else if(indice_stat == 2) {
			if(game.current_card_id == -1) {
				this.setText("Rareté : -");
			}
			else {
				this.setText("Rareté : "+game.joueur.collection.get(game.current_card_id).rarity+" -> "+game.joueur.collection.get(game.current_card_id).max_rarity);
			}
		}
		else if(indice_stat == 3) {
			if(game.current_card_id == -1) {
				this.setText("Niveau de carte : -");
			}
			else {
				if(game.joueur.collection.get(game.current_card_id).prestige > 0) {
					this.setText("Niveau de carte : "+game.joueur.collection.get(game.current_card_id).level+" / "+game.joueur.collection.get(game.current_card_id).max_level+" (Prestige "+Maths.format(game.joueur.collection.get(game.current_card_id).prestige)+")");
				}
				else {
					this.setText("Niveau de carte : "+game.joueur.collection.get(game.current_card_id).level+" / "+game.joueur.collection.get(game.current_card_id).max_level);
				}
			}
		}
		else if(indice_stat == 4) {
			if(game.current_card_id == -1) {
				this.setText("Type : -");
			}
			else {
				this.setText("Type : "+game.joueur.collection.get(game.current_card_id).type);
			}
		}
		else if(indice_stat == 5) {
			if(game.current_card_id == -1) {
				this.setText("Stats");
			}
			else {
				this.setText( ((GameV2)game).getStats(game.joueur.collection.get(game.current_card_id)));
				//this.setText("<html><br>--- Statistiques ---"+"<br>HP : "+game.joueur.collection.get(game.current_card_id).hp+""+"<br>ATK : "+game.joueur.collection.get(game.current_card_id).atk+""+"<br>DEF : "+game.joueur.collection.get(game.current_card_id).def+""+"<br>PROV : "+game.joueur.collection.get(game.current_card_id).prov+""+"<br>AGI : "+game.joueur.collection.get(game.current_card_id).agi+""+"<br>VIT : "+game.joueur.collection.get(game.current_card_id).vit+"</html>");
			}
		}
		else if(indice_stat == 6) {
			if(game.current_card_id == -1) {
				this.setText("Coût : -");
			}
			else {
				int cost = game.joueur.collection.get(game.current_card_id).rarity_id*3;
				this.setText("Coût : "+cost+" Or / Exp");
			}
		}
		else if(indice_stat == 7) {
			if(game.current_card_id == -1) {
				this.setText("Exp : -");
			}
			else {
				this.setText("Exp : "+Maths.format(game.joueur.collection.get(game.current_card_id).exp)+" / "+Maths.format(game.joueur.collection.get(game.current_card_id).max_exp)); 
			}
		}
		else if(indice_stat == 8) {
			if(game.current_card_id == -1) {
				this.setText("Flavor Text : -");
			}
			else {
				this.setText("<html>"+game.joueur.collection.get(game.current_card_id).description+"</html>");
			}
		}
	}

}

