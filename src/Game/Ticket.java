package Game;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Vues.TicketFrame;

public class Ticket extends Item {

	public Ticket(int i, int cst, String d,String n, Game g) {
		super(i, cst, d,n, g);

	}

	public void utiliser() {
		super.utiliser();
		JOptionPane jop1;
		jop1 = new JOptionPane();
		String[] liste = {"Carte 1","Carte 2","Carte 3","Carte 4","Carte 5","Annuler"};
		int option = 1;
		if(game.auto_select == false) {
			option = jop1.showOptionDialog(null, "Quelle carte voulez-vous choisir ?", "RNGesus is watching you !", JOptionPane.WARNING_MESSAGE,0,null,liste,liste[5]);		
		}
		if(option != 5) {
			ArrayList<Carte> cartes = new ArrayList<Carte>();
			if(this.id == 1) {
				for(int i=0;i<5;i++) {
					Carte c = null;
					try {
						c = this.game.generateCardNormal();
						cartes.add(c);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			else if(this.id == 2) {
				for(int i=0;i<5;i++) {
					Carte c = null;
					try {
						c = this.game.generateCardRare();
						cartes.add(c);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			else if(this.id == 3) {
				for(int i=0;i<5;i++) {
					Carte c = null;
					try {
						c = this.game.generateCardEpic();
						cartes.add(c);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			else if(this.id == 4) {
				for(int i=0;i<5;i++) {
					Carte c = null;
					try {
						c = this.game.generateCardLegendary();
						cartes.add(c);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}

			if(option != 1) {
				Carte rr = cartes.get(0);
				cartes.set(0,cartes.get(option));
				cartes.set(option, rr);
			}
			//on génère le son des cartes ouais
			String sound_name = this.getSound(cartes);
			//on gère le gain d'exp lors de la découverte de cartes rares et +
			if(this.id == 1 && cartes.get(0).rarity_id == 2) {
				game.joueur.giveExp(25);
			}
			else if(this.id <= 3 && cartes.get(0).rarity_id == 3) {
				game.joueur.giveExp(200+(game.joueur.level*8L));
			}
			else if(this.id <= 3 && cartes.get(0).max_rarity_id == 7) {
				game.joueur.giveExp(500+(game.joueur.level*20L));
			}
			else if(this.id <= 4 && cartes.get(0).max_rarity_id == 9) {
				game.joueur.giveExp(1000+(game.joueur.level*70L));
			}
			this.game.joueur.collection.add(cartes.get(0));
			this.game.captured.set(cartes.get(0).id-1, 1);
			this.game.getCaptured();
			//on vérifie si le haut-fait est gagné
			game.checkMobsSucess(cartes.get(0).rarity_id);
			//
			try {
				TicketFrame fr = new TicketFrame(cartes,sound_name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.quantity -= 1;
			this.game.eraseUsedItems();
		}

	}

	public String getSound(ArrayList<Carte> cartes) {
		String sound_name = "";
		int rarete = cartes.get(0).max_rarity_id;
		boolean rekt = false;
		double rektchance = Math.random();
		for(int i=1;i<cartes.size();i++) {
			if((cartes.get(i).max_rarity_id) >= (cartes.get(0).max_rarity_id + 2)) {
				if(rektchance < 0.5) {
					rekt = true;
				}
				sound_name = "Sounds/rekt-2.wav";
			}
			else if((cartes.get(i).max_rarity_id) >= (cartes.get(0).max_rarity_id + 1)) { 
				if(rektchance < 0.5) {
					rekt = true;
				}
				sound_name = "Sounds/rekt-1.wav";
			}
		}
		if(rarete >= 6) {
			rekt = false;
		}
		if(rekt == false) {
			double sound_select = Math.random();
			if(rarete >= 6) {
				sound_name = "Sounds/L-"+cartes.get(0).id+".wav";
			}
			else if(rarete == 5) {
				if(sound_select <= 0.25) {
					sound_name = "Sounds/HRP-1.wav";
				}
				else if(sound_select > 0.25 && sound_select <= 0.50) {
					sound_name = "Sounds/HRP-2.wav";
				}
				else if(sound_select > 0.5 && sound_select <= 0.75) {
					sound_name = "Sounds/HRP-3.wav";
				}
				else {
					sound_name = "Sounds/HRP-4.wav";
				}
			}
			else if(rarete == 4) {
				if(sound_select <= 0.25) {
					sound_name = "Sounds/HR-1.wav";
				}
				else if(sound_select > 0.25 && sound_select <= 0.50) {
					sound_name = "Sounds/HR-2.wav";
				}
				else if(sound_select > 0.50 && sound_select <= 0.75) {
					sound_name = "Sounds/HR-3.wav";
				}
				else {
					sound_name = "Sounds/HR-4.wav";
				}
			}
			else {
				if(sound_select <= 0.5) {
					sound_name = "Sounds/SR-1.wav";
				}
				else {
					sound_name = "Sounds/SR-2.wav";
				}
			}
		}	
		return sound_name;
	}

}
