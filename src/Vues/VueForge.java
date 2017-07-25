package Vues;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;
import Game.GameV2;
import Game.Maths;
import Game.Rune;
import Game.UpgradableRune;

public class VueForge extends JLabel implements Observer{

	public int indice;
	public Game game;
	
	public VueForge(String s,Game g,int ind) {
		this.setText(s);
		this.game = g;
		this.indice = ind;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(indice == 1) {
			String text = "<html><p>Cristaux Arcaniques : <font color=\"blue\">"+Maths.format(((GameV2)game).arcane_cristals)+"</font></p></html>";
			this.setText(text);
		}
		else if(indice == 2) {
			if(game.current_rune_selected != -1) {
				this.setText(((UpgradableRune)game.inventaire_runes.get(game.current_rune_selected)).getForgeNom());
			}
			else {
				this.setText("");
			}
		}
		else if(indice == 3) {
			if(game.current_rune_selected != -1) {
				Rune r = game.inventaire_runes.get(game.current_rune_selected);
				this.setText(((UpgradableRune)r).getUpgradeCost());
			}
			else {
				this.setText("");
			}
		}
		else if(indice == 4) {
			if(game.current_rune_selected != -1) {
				this.setText(((UpgradableRune)game.inventaire_runes.get(game.current_rune_selected)).getDescr());
			}
			else {
				this.setText("");
			}
		}
		else if(indice == 5) {
			if(game.current_rune_selected != -1) {
				this.setText(((UpgradableRune)game.inventaire_runes.get(game.current_rune_selected)).getDescrAfter());
			}
			else {
				this.setText("");
			}
		}
	}
	
	public void updateFromPanel() {
		if(indice == 1) {
			String text = "<html><p>Cristaux Arcaniques : <font color=\"blue\">"+Maths.format(((GameV2)game).arcane_cristals)+"</font></p></html>";
			this.setText(text);
		}
		else if(indice == 2) {
			if(game.current_rune_selected != -1) {
				this.setText(((UpgradableRune)game.inventaire_runes.get(game.current_rune_selected)).getForgeNom());
			}
			else {
				this.setText("");
			}
		}
		else if(indice == 3) {
			if(game.current_rune_selected != -1) {
				Rune r = game.inventaire_runes.get(game.current_rune_selected);
				this.setText(((UpgradableRune)r).getUpgradeCost());
			}
			else {
				this.setText("");
			}
		}
		else if(indice == 4) {
			if(game.current_rune_selected != -1) {
				this.setText(((UpgradableRune)game.inventaire_runes.get(game.current_rune_selected)).getDescr());
			}
			else {
				this.setText("");
			}
		}
		else if(indice == 5) {
			if(game.current_rune_selected != -1) {
				this.setText(((UpgradableRune)game.inventaire_runes.get(game.current_rune_selected)).getDescrAfter());
			}
			else {
				this.setText("");
			}
		}
	}

}
