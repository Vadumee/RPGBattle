package Game;

import javax.swing.JOptionPane;

public class ItemGenerator extends Item{

	public ItemGenerator(int i, int cst, String d, String n, Game g) {
		super(i, cst, d, n, g);
		// TODO Auto-generated constructor stub
	}
	
	public void utiliser() {
		super.utiliser();
		JOptionPane jopt = new JOptionPane();
		Item item = null;
		long mnt = 0;
		double rnd = Math.random()*100;
		boolean goldess = false;
		int cap = ((game.joueur.level - (game.joueur.level%10))/10)+1;
		if(rnd <= 40) {
			goldess = true;
			rnd = Math.random()*100;
			int lvl = game.joueur.level;
			if(rnd <= 50) {
				mnt = 500 + ((200L*lvl)*cap);
			}
			else if(rnd > 50 && rnd <= 80) {
				mnt = 1500 + ((600L*lvl)*cap);
			}
			else if(rnd > 80 && rnd <= 93) {
				mnt = 4500 + ((1800L*lvl)*cap);
			}
			else {
				mnt = 13500 + ((4500L*lvl)*cap);
			}
			game.joueur.gold += mnt;
		}
		else if(rnd > 40 && rnd <= 70) {
			rnd = Math.random()*100;
			int ene = 0;
			if(rnd <= 50) {
				ene = (int)(game.joueur.max_energy*0.2);
				item = new Potion(14,25000,"Une potion redonnant "+ene+" d'énergie.","Breuvage de l'âme inférieur",game,ene);
			}
			else if(rnd > 50 && rnd <= 82) {
				ene = (int)(game.joueur.max_energy*0.5);
				item = new Potion(15,25000,"Une potion redonnant "+ene+" d'énergie.","Breuvage de l'âme",game,ene);
			}
			else {
				ene = (int)(game.joueur.max_energy);
				item = new Potion(16,25000,"Une potion redonnant "+ene+" d'énergie.","Breuvage de l'âme supérieur",game,ene);
			}
		}
		else if(rnd > 70 && rnd <= 88) {
			rnd = Math.random()*100;
			int ene = 0;
			if(rnd <= 60) {
				item = new Ticket(1,10000,"Un portail basique de la roue de la RNG, donnant des serviteurs communs ou supérieurs.","Portail de créature commun",game);
			}
			else if(rnd > 60 && rnd <= 90) {
				item = new Ticket(2,75000,"Un portail permettant d'obtenir des serviteurs rares ou supérieurs à la roue de la RNG.","Portail de créature rare",game);
			}
			else if(rnd > 90 && rnd <= 98){
				item = new Ticket(3,800000,"Un portail de brutasse qui permet d'avoir plus de chance d'obtenir des serviteurs super rares.","Portail dimentionnel de créatures brutales",game);
			}
			else {
				item = new Ticket(4,10000000,"Un portail ayant de grandes chances d'octroyer un serviteur légendaire.","Portail dimentionnel de brutasse légendaire",game);
			}
		}
		else {
			rnd = Math.random()*100;
			if(rnd <= 50) {
				item = new GrimoireGenerator(5, 250000, "Un objet créant un grimoire pour un serviteur commun.", "Collection de grimoires poussiereux", game, 1);
			}
			else if(rnd > 50 && rnd <= 80) {
				item = new GrimoireGenerator(6, 1000000, "Un objet créant un grimoire pour un serviteur rare.", "Collection de grimoires de qualité", game, 2);
			}
			else if(rnd > 80 && rnd <= 90){
				item = new GrimoireGenerator(7, 4000000, "Un objet créant un grimoire pour un serviteur super rare et hyper rare.", "Collection de grimoires runiques", game, 3);
			}
			else {
				item = new GrimoireGenerator(8, 15000000, "Un objet créant un grimoire pour un serviteur légendaire.", "Collection de grimoires mythiques", game, 4);
			}
		}
		if(goldess == false) {
			jopt.showMessageDialog(null, "Vous avez trouvé l'objet suivant : "+item.nom+" !", "Ouverture de coffre", JOptionPane.INFORMATION_MESSAGE);
			game.ajouterObjet(item);
		}
		else {
			jopt.showMessageDialog(null, "Vous avez trouvé "+mnt+" pièces d'or !", "Ouverture de coffre", JOptionPane.INFORMATION_MESSAGE);
		}
		this.quantity -= 1;
		game.eraseUsedItems();
	}

}
