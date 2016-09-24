package Vues;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;

public class SuccessVue extends JLabel implements Observer {
	
	public int indice_stat;
	public Game game;
	
	public SuccessVue(String s,Game g,int ind) {
		super(s);
		this.game = g;
		this.indice_stat = ind;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(indice_stat == 1) {
			if(game.current_success != -1) {
				this.setText("["+game.succes.get(game.current_success).nom+"]");
			}
			else {
				this.setText("-");
			}
		}
		else if(indice_stat == 2) {
			if(game.current_success != -1) {
				this.setText(game.succes.get(game.current_success).descr);
			}
			else {
				this.setText("-");
			}
		}
		else if(indice_stat == 3) {
			if(game.current_success != -1) {
				this.setText("Progrès des hauts-faits : "+game.getSuccessCount()+" / "+game.succes.size());
			}
		}
		
	}

}
