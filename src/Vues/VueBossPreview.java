package Vues;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Game;
import Game.Boss;
import Game.Maths;

public class VueBossPreview extends JLabel implements Observer {
	
	public int indice_stat;
	public JPanel jpp;
	public Game game;
	
	public VueBossPreview(String s,Game g,int i,JPanel j) {
		this.setText(s);
		this.game = g;
		this.indice_stat = i;
		this.jpp = j;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(indice_stat == 1) {
			if(game.boss != null) {
				this.setText("Niveau : "+Maths.format(game.boss.lvl));
			}
			else {
				this.setText("Niveau : -");
			}
		}
		else if(indice_stat == 2) {
			if(game.boss != null) {
				//On regarde si on a débloqué le passif secret
				String s="";
				long l = System.currentTimeMillis()/1000;
				if(l >= 1493589600 && game.boss.id == 1) {
					s += "<br>"+((Boss)game.boss).secret_passive;
				}
				else if(l >= 1494799200 && game.boss.id == 2) {
					s += "<br>"+((Boss)game.boss).secret_passive;
				}
				else if(l >= 1496008800 && game.boss.id == 3) {
					s += "<br>"+((Boss)game.boss).secret_passive;
				}
				else if(l >= 1497218400 && game.boss.id == 4) {
					s += "<br>"+((Boss)game.boss).secret_passive;
				}
				else if(l >= 1498428000 && game.boss.id == 5) {
					s += "<br>"+((Boss)game.boss).secret_passive;
				}
				this.setText(game.boss.nom);
				this.setToolTipText("<html>"+game.boss.passive_descr+s+"</html>");
			}
			else {
				this.setText("");
			}
		}
		else if(indice_stat == 3) {
			if(game.boss != null) {
				this.setText("<html>Vie : "+Maths.format(game.boss.pv)+" / "+Maths.format(game.boss.pv_max)+"<br>Attaque : "+Maths.format(game.boss.atk)+"<br>Défense : "+Maths.format(game.boss.defense)+"</html>");
			}
			else {
				this.setText("Niveau : -");
			}
			this.jpp.repaint();
		}
		else if(indice_stat == 4) {
			if(game.boss != null) {
				this.setText("Coût : "+Maths.format(game.boss.cost_atk));
			}
			else {
				this.setText("Coût : -");
			}
		}
		else if(indice_stat == 5) {
			this.setText("Niveau Max : "+Maths.format(game.max_battle_level));
		}
		else if(indice_stat == 6) {
			if(game.current_season != -1) {
				this.setText("Score de Saison : "+Maths.format(game.season_score)+" ("+(Maths.format(game.season_rewards.points_needed.get(game.season_rewards.indice_recompense) - game.season_score))+" pts avant -> "+game.season_rewards.descr_recompense.get(game.season_rewards.indice_recompense)+")");
			}
			else {
				this.setText("Score de Saison : Hors saison");
			}
		}
	}

}
