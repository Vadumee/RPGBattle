package Game;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Vues.EventTicketFrame;
import Vues.TicketFrame;

public class EventTicket extends Item {

	public int id_mob_event;

	public EventTicket(int i, int cst, String d, String n, Game g,int id_mob) {
		super(i, cst, d,n, g);
		this.id_mob_event = id_mob;
		// TODO Auto-generated constructor stub
	}

	public void utiliser() {
		super.utiliser();
		boolean usable = false;
		long l = System.currentTimeMillis()/1000;
		if((l >= 1493589600 && this.id_mob_event == 5) || (l >= 1494799200 && this.id_mob_event == 6) || (l >= 1496008800 && this.id_mob_event == 7) || (l >= 1497218400 && this.id_mob_event == 8) || this.id_mob_event == 9 || this.id_mob_event <= 4) {
			usable = true;
		}
		if(usable == true) {
			Carte c=null;
			try {
				c = new Carte("cards/6/char_"+id_mob_event+".txt",6);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//on génère le son des cartes ouais
			String sound_name = "Sounds/L-"+c.id+".wav";
			game.joueur.giveExp(1000+(game.joueur.level*70L));
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
		}
		else {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			jop1.showMessageDialog(null, "Le temps n'est pas encore venu pour activer cet artefact, patience...", "Echec de l'invocation", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
