package Vues;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;

public class VueBattleEnergy extends JLabel implements Observer {
	
	public Game game;
	
	public VueBattleEnergy(String s,Game g) {
		this.setText(s);
		this.game = g;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		this.setText("Energie : "+game.joueur.energy+" / "+game.joueur.max_energy);
	}


}
