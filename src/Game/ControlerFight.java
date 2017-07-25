package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
		WavPlayer noc = new WavPlayer(new File(game.boss.sound_link));
		if(e.getSource() instanceof ButtonBossHit) {
			boolean fightable = false;
			boolean wiped = true;
			for(int i=0;i<game.indice_fighters.length;i++) {
				if(game.indice_fighters[i] != -1) {
					if((game.joueur.collection.get(game.indice_fighters[i]).dead == false) && (game.joueur.energy >= game.boss.cost_atk)) {
						fightable = true;
					}
				}
			}
			if(fightable == true) {
				game.fightThePower();
			}// nocturne
			if(((Boss)game.boss).status[3] == 1) {
				for(int i=0;i<game.indice_fighters.length;i++) {
					if(game.joueur.collection.get(game.indice_fighters[i]).dead == false) {
						wiped = false;
						((Boss)game.boss).status[4] = 0;
					}
				}
				if(wiped == true) {
					if(((Boss)game.boss).status[4] == 0) {
						((Boss)game.boss).status[4] = 1;
						//on choisit un son au hasard
						double rnd = 1+Math.random()*10;
						int choice = (int)(rnd - (rnd%1));
						noc = new WavPlayer(new File("Sounds/noc-voice"+choice+".wav"));
						//
						noc.open();
						noc.play();
					}
				}
			}
			//
			game.updateVisuals();
		}
		else {
			noc.stop();
			noc.close();
			wp.stop();
			wp.close();
			//faire la mort du boss
			if(game.boss.pv <= 0) {
				((GameV2)game).finishTheFight();
			}
			//
			((BossFrame) SwingUtilities.getRoot((JButton)e.getSource())).dispose();
		}

	}

}
