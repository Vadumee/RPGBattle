package Game;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Vues.EventTicketFrame;
import Vues.TicketFrame;

public class TemporalTicket extends Item {

	public String filepath;

	public TemporalTicket(int i, int cst, String d, String n, Game g,String f) {
		super(i, cst, d,n, g);
		this.filepath = f;
		// TODO Auto-generated constructor stub
	}

	public void utiliser() {
		super.utiliser();
		boolean usable = false;
		long l = System.currentTimeMillis()/1000;
		if(game.max_battle_level >= 5) {
			usable = true;
		}
		if(usable == true) {
			Carte c=null;
			try {
				c = new Carte(this.filepath,6);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//on génère le son des cartes ouais
			String sound_name = "Sounds/L-"+c.id+".wav";
			long gain = 1000+(game.joueur.level*70L);
			gain = (long)(gain * (1 + ((double)((GameV2)game).talents[20].lvl*0.5)));
			game.joueur.giveExp(gain);
			((GameV2)game).calculatePlayerMaxEnergy();
			this.game.joueur.collection.add(c);
			this.game.captured.set(c.id-1, 1);
			this.game.getCaptured();
			//on vérifie si le haut-fait est gagné
			game.checkMobsSucess(c.rarity_id);
			game.checkLevelSuccess();
			//
			try {
				EventTicketFrame fr = new EventTicketFrame(c,sound_name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.quantity -= 1;
			this.game.eraseUsedItems();
			((GameV2)game).calculateStatFight(game.joueur.collection.get(game.joueur.collection.size()-1));
		}
		else {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			jop1.showMessageDialog(null, "Il vous faut vaincre les 5 premiers boss pour dévérouiller les portails temporels...", "Echec de l'invocation", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
