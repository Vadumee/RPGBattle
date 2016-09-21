package Vues;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Game;

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
				this.setText("Niveau : "+game.boss.lvl);
			}
			else {
				this.setText("Niveau : -");
			}
		}
		else if(indice_stat == 2) {
			if(game.boss != null) {
				this.setText(game.boss.nom);
				this.setToolTipText(game.boss.passive_descr);
			}
			else {
				this.setText("");
			}
		}
		else if(indice_stat == 3) {
			if(game.boss != null) {
				this.setText("<html>Vie : "+game.boss.pv+" / "+game.boss.pv_max+"<br>Attaque : "+game.boss.atk+"<br>Défense : "+game.boss.defense+"</html>");
			}
			else {
				this.setText("Niveau : -");
			}
			this.jpp.repaint();
		}
		else if(indice_stat == 4) {
			if(game.boss != null) {
				this.setText("Coût : "+game.boss.cost_atk);
			}
			else {
				this.setText("Coût : -");
			}
		}
		else if(indice_stat == 5) {
			this.setText("Niveau Max : "+game.max_battle_level);
		}
		else if(indice_stat == 6) {
			if(game.current_season != -1) {
				this.setText("Score de Saison : "+game.season_score+" ("+(game.season_rewards.points_needed.get(game.season_rewards.indice_recompense) - game.season_score)+" pts avant -> "+game.season_rewards.descr_recompense.get(game.season_rewards.indice_recompense)+")");
			}
			else {
				this.setText("Score de Saison : Hors saison");
			}
		}
	}

}
