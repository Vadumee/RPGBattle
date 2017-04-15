package Vues;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;
import Game.Maths;

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
				//on s'occupe du suivi de haut-fait
				String suivi = "";
				if(game.current_success >= 0 && game.current_success <= 5) {
					long cur = game.values.get(0);
					long obj = game.succes.get(game.current_success).montant;
					if(cur > obj) {
						cur = obj;
					}
					suivi += " ("+Maths.format(cur)+" / "+Maths.format(obj)+")";
				}
				else if(game.current_success >= 6 && game.current_success <= 11) {
					long cur = game.joueur.level;
					long obj = game.succes.get(game.current_success).montant;
					if(cur > obj) {
						cur = obj;
					}
					suivi += " ("+Maths.format(cur)+" / "+Maths.format(obj)+")";
				}
				else if(game.current_success >= 16 && game.current_success <= 19) {
					long cur = game.values.get(1);
					long obj = game.succes.get(game.current_success).montant;
					if(cur > obj) {
						cur = obj;
					}
					suivi += " ("+Maths.format(cur)+" / "+Maths.format(obj)+")";
				}
				else if(game.current_success >= 20 && game.current_success <= 23) {
					long cur = game.values.get(2);
					long obj = game.succes.get(game.current_success).montant;
					if(cur > obj) {
						cur = obj;
					}
					suivi += " ("+Maths.format(cur)+" / "+Maths.format(obj)+")";
				}
				else if(game.current_success >= 24 && game.current_success <= 27) {
					long cur = game.values.get(3);
					long obj = game.succes.get(game.current_success).montant;
					if(cur > obj) {
						cur = obj;
					}
					suivi += " ("+Maths.format(cur)+" / "+Maths.format(obj)+")";
				}
				else if(game.current_success >= 28 && game.current_success <= 31) {
					long cur = game.values.get(4);
					long obj = game.succes.get(game.current_success).montant;
					if(cur > obj) {
						cur = obj;
					}
					suivi += " ("+Maths.format(cur)+" / "+Maths.format(obj)+")";
				}
				else if(game.current_success >= 34 && game.current_success <= 37) {
					long cur = game.max_battle_level-1;
					long obj = game.succes.get(game.current_success).montant;
					if(cur > obj) {
						cur = obj;
					}
					suivi += " ("+Maths.format(cur)+" / "+Maths.format(obj)+")";
				}
				else if(game.current_success >= 40 && game.current_success <= 49) {
					long cur = game.mobs_captured;
					long obj = game.succes.get(game.current_success).montant;
					if(cur > obj) {
						cur = obj;
					}
					suivi += " ("+Maths.format(cur)+" / "+Maths.format(obj)+")";
				}
				//
				this.setText(game.succes.get(game.current_success).descr+suivi);
			}
			else {
				this.setText("-");
			}
		}
		else if(indice_stat == 3) {
			this.setText("Progrès des hauts-faits : "+game.getSuccessCount()+" / "+game.succes.size());
		}
		
	}

}
