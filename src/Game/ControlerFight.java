package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import Boutons.ButtonBossHit;
import Boutons.ButtonRoulette;
import Vues.BossFrame;
import Vues.TicketFrame;

public class ControlerFight implements ActionListener {

	public Game game;
	public WavPlayer wp;

	public ControlerFight(Game g,WavPlayer w) {
		this.game = g;
		this.wp = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof ButtonBossHit) {
			boolean fightable = false;
			for(int i=0;i<game.indice_fighters.length;i++) {
				if(game.indice_fighters[i] != -1) {
					if((game.joueur.collection.get(game.indice_fighters[i]).dead == false) && (game.joueur.energy >= game.boss.cost_atk)) {
						fightable = true;
					}
				}
			}
			if(fightable == true) {
				game.fightThePower();
			}
			game.updateVisuals();
		}
		else {
			wp.stop();
			wp.close();
			//faire la mort du boss
			if(game.boss.pv <= 0) {
				game.finishTheFight();
			}
			//
			((BossFrame) SwingUtilities.getRoot((JButton)e.getSource())).dispose();
		}
		
	}

}
