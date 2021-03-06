package Game;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Observable;

import javax.swing.JOptionPane;

public class Game extends Observable implements Serializable {

	public Player joueur;
	public ArrayList<Item> inventaire;
	public ArrayList<Item> magasin;
	public ArrayList<Integer> captured;
	public ArrayList<Rune> inventaire_runes;
	public int mobs_captured;
	public int timer = 90;
	public int time;

	public String roul1 = "X";
	public String roul2 = "X";
	public String roul3 = "X";

	public boolean auto_select = false;

	public int current_card_id = -1;
	public int current_item_selected = -1;
	public int current_item_magasin = -1;
	public int current_favorite_id = -1;
	public int current_rune_selected = -1;
	public int current_card_runed = -1;
	public int current_success = -1;

	public long time_last_save = 0;
	public boolean quit; 

	public ArrayList<HautFait> succes;

	//� mettre dans event apr�s
	public int indice_battle = -1;
	public Mob boss;
	public int[] indice_fighters;
	public int max_battle_level = 1;
	public int round_count;
	public int season_score;
	public int current_season;
	public SeasonRewards season_rewards;
	public long endseason;
	//

	//stats event
	public ArrayList<Long> values;
	//

	public Game() {
		this.time = 0;
		//� remplacer par event apr�s
		this.boss = null;
		this.indice_fighters = new int[3];
		for(int i=0;i<3;i++) {
			this.indice_fighters[i] = -1;
		}
		this.round_count = 0;
		this.season_score = 0;
		this.current_season = -1;
		this.season_rewards = new SeasonRewards(this.current_season);
		//� modifier lors de saisons diff�rentes//
		this.beginSeason();
		//////////////////////////////////////////
		//
		this.mobs_captured = 0;
		this.quit = false;
		this.joueur = new Player();
		this.inventaire = new ArrayList<Item>();
		this.magasin = new ArrayList<Item>();
		this.inventaire_runes = new ArrayList<Rune>();
		//on initialise le tableau des captur�s
		this.captured = new ArrayList<Integer>();
		int echantillon = new File("cards/"+1+"/").list().length;
		echantillon += new File("cards/"+2+"/").list().length;
		echantillon += new File("cards/"+3+"/").list().length;
		echantillon += new File("cards/"+4+"/").list().length;
		echantillon += new File("cards/"+5+"/").list().length;
		echantillon += new File("cards/"+6+"/").list().length;
		for(int i=0;i<echantillon;i++) {
			this.captured.add(0);
		}

		//on cr�er le magasin
		this.magasin.add(new Ticket(1,10000,"Un portail basique de la roue de la RNG, donnant des serviteurs communs ou sup�rieurs.","Portail de cr�ature commun",this));
		this.magasin.add(new Ticket(2,75000,"Un portail permettant d'obtenir des serviteurs rares ou sup�rieurs � la roue de la RNG.","Portail de cr�ature rare",this));
		this.magasin.add(new Ticket(3,800000,"Un portail de brutasse qui permet d'avoir plus de chance d'obtenir des serviteurs super rares.","Portail dimentionnel de cr�atures brutales",this));
		this.magasin.add(new Ticket(4,10000000,"Un portail ayant de grandes chances d'octroyer un serviteur l�gendaire.","Portail dimentionnel de brutasse l�gendaire",this));
		this.magasin.add(new GrimoireGenerator(5, 250000, "Un objet cr�ant un grimoire pour un serviteur commun.", "Collection de grimoires poussiereux", this, 1));
		this.magasin.add(new GrimoireGenerator(6, 1000000, "Un objet cr�ant un grimoire pour un serviteur rare.", "Collection de grimoires de qualit�", this, 2));
		this.magasin.add(new GrimoireGenerator(7, 4000000, "Un objet cr�ant un grimoire pour un serviteur super rare et hyper rare.", "Collection de grimoires runiques", this, 3));
		this.magasin.add(new GrimoireGenerator(8, 15000000, "Un objet cr�ant un grimoire pour un serviteur l�gendaire.", "Collection de grimoires mythiques", this, 4));
		this.magasin.add(new RuneGenerator(9, 400000, "Un objet cr�ant une rune al�atoire de qualit� inf�rieure.","Rune fissur�e non identifi�e", this,1));
		this.magasin.add(new RuneGenerator(10, 3000000, "Un objet cr�ant une rune al�atoire de qualit� normale.","Rune de qualit� non identifi�e", this,2));
		this.magasin.add(new RuneGenerator(11, 10000000, "Un objet cr�ant une rune al�atoire de qualit� sup�rieure.","Rune fabuleuse non identifi�e", this,3));
		this.magasin.add(new RuneGenerator(12, 25000000, "Un objet cr�ant une rune al�atoire de qualit� l�gendaire.","Rune l�gendaire non identifi�e", this,4));
		//

		//on cr�er les hauts-faits
		this.values = new ArrayList<Long>();
		for(int v=0;v<5;v++) {
			this.values.add(0L);
		}
		this.succes = new ArrayList<HautFait>();
		this.succes.add(new HautFait("La menue monnaie","Obtenir au total 100.000 pi�ces d'or de n'importe quelle source.",100000));
		this.succes.add(new HautFait("Millionaire !","Obtenir au total 1.000.000 pi�ces d'or de n'importe quelle source.",1000000));
		this.succes.add(new HautFait("La richesse n'attend pas","Obtenir au total 10.000.000 pi�ces d'or de n'importe quelle source.",10000000));
		this.succes.add(new HautFait("Une montagne d'or","Obtenir au total 100.000.000 pi�ces d'or de n'importe quelle source.",100000000));
		this.succes.add(new HautFait("Piscou en personne","Obtenir au total 1.000.000.000 pi�ces d'or de n'importe quelle source.",1000000000));
		this.succes.add(new HautFait("Commer�ant universel","Obtenir au total 100.000.000.000 pi�ces d'or de n'importe quelle source.",100000000L*100L));
		this.succes.add(new HautFait("Apprenti Invocateur","Atteindre le niveau 10.",10));
		this.succes.add(new HautFait("Compagnon","Atteindre le niveau 30.",30));
		this.succes.add(new HautFait("Chef de troupes","Atteindre le niveau 50.",50));
		this.succes.add(new HautFait("Archimage","Atteindre le niveau 70.",70));
		this.succes.add(new HautFait("Grand Leader","Atteindre le niveau 100.",100));
		this.succes.add(new HautFait("La PUISSAAAAAAAAAANCE !","Atteindre le niveau 200.",200));
		this.succes.add(new HautFait("Admettons !","Obtenir un portail de serviteur l�gendaire dans un coffre � butin de brutasse.",1));
		this.succes.add(new HautFait("Sacr� pactole","R�cuperer un tr�sor de 10.000.000 or dans un coffre � butin de brutasse.",10000000));
		this.succes.add(new HautFait("Et si vous �chouez ? - Impossible.","Atteindre la conqu�te d'une saison.",1));
		this.succes.add(new HautFait("Ascension","Faire transcender un serviteur.",1));
		this.succes.add(new HautFait("Quel g�chis.","R�cuperer 25 cartes communes.",25));
		this.succes.add(new HautFait("Tellement banal","R�cuperer 100 cartes communes.",100));
		this.succes.add(new HautFait("Bon �a suffit","R�cuperer 250 cartes communes.",250));
		this.succes.add(new HautFait("Ah j'ai encore rat� ce l�gendaire !","R�cuperer 1000 cartes communes.",1000));
		this.succes.add(new HautFait("La raret� fait du prix � la chose","R�cuperer 10 cartes rares.",10));
		this.succes.add(new HautFait("Chance n�gligeable","R�cuperer 25 cartes rares.",25));
		this.succes.add(new HautFait("Beaucoup de gris","R�cuperer 100 cartes rares.",100));
		this.succes.add(new HautFait("Toutes ces cartes en argent","R�cuperer 250 cartes rares.",250));
		this.succes.add(new HautFait("Plut�t cool","R�cuperer 5 cartes super rares.",5));
		this.succes.add(new HautFait("Ce r�ve bleu","R�cuperer 15 cartes super rares.",15));
		this.succes.add(new HautFait("Super chance","R�cuperer 50 cartes super rares.",50));
		this.succes.add(new HautFait("Super sayant","R�cuperer 150 cartes super rares.",150));
		this.succes.add(new HautFait("WOAH ! LEGENDAIRRRRRRRE !","R�cuperer 1 carte l�gendaire.",1));
		this.succes.add(new HautFait("Par� pour les raids !","R�cuperer 5 cartes l�gendaires.",5));
		this.succes.add(new HautFait("Fabuleux","R�cuperer 15 cartes l�gendaires.",15));
		this.succes.add(new HautFait("L�gende vivante","R�cuperer 50 cartes l�gendaires.",50));
		this.succes.add(new HautFait("Massacre","Infliger 20.000 d�gats � un boss en un seul coup.",20000));
		this.succes.add(new HautFait("Mon pr�cieux !","Trouver une gemme de raid.",1));
		this.succes.add(new HautFait("Vainqueur","Battre un boss de niveau 5.",5));
		this.succes.add(new HautFait("Porte-paix","Battre un boss de niveau 10.",10));
		this.succes.add(new HautFait("Je suis une machine de guerre !","Battre un boss de niveau 20.",20));
		this.succes.add(new HautFait("WHO THE HELL DO YOU THINK I AM ???","Battre un boss de niveau 40.",40));
		this.succes.add(new HautFait("C'est pas tr�s halal","D�truire sp�cifiquement (pas de masse) un sanglier :'(.",1));
		this.succes.add(new HautFait("Aucun r�gne n'est �ternel, mon fils.","D�truire sp�cifiquement un serviteur Super Mythique.",1));
		this.succes.add(new HautFait("Petite collection","Avoir 10 serviteurs diff�rents.",10));
		this.succes.add(new HautFait("Equipe de choc","Avoir 20 serviteurs diff�rents.",20));
		this.succes.add(new HautFait("Chef de guilde","Avoir 30 serviteurs diff�rents.",30));
		this.succes.add(new HautFait("Voyageur interdimentionnel","Avoir 40 serviteurs diff�rents.",40));
		//ajouts 1.0.3
		this.succes.add(new HautFait("Arm�e de combat","Avoir 50 serviteurs diff�rents.",50));
		this.succes.add(new HautFait("Imperator","Avoir 60 serviteurs diff�rents.",60));
		this.succes.add(new HautFait("Professeur Chen","Avoir 70 serviteurs diff�rents.",70));
		this.succes.add(new HautFait("Seigneur de bataille","Avoir 80 serviteurs diff�rents.",80));
		this.succes.add(new HautFait("Grand seigneur","Avoir 90 serviteurs diff�rents.",90));
		this.succes.add(new HautFait("Overlord","Avoir 100 serviteurs diff�rents.",100));
		this.succes.add(new HautFait("La proph�tie","Ouvrir un portail contenant 5 fois le m�me serviteur.",1));
		this.succes.add(new HautFait("Rite Initiatique","Am�liorer un serviteur normal au niveau max.",1));
		this.succes.add(new HautFait("Cons�cration","Am�liorer un serviteur non-l�gendaire � son niveau ultime.",1));
		this.succes.add(new HautFait("Vainqueur de la nuit","Vaincre la forme v�ritable de Nocturne.",1));
		//

		//test

		//
	}

	public void checkBossSucess() {
		for(int i=34;i<38;i++) {
			if(this.succes.get(i).completed == false && (this.max_battle_level-1) >= this.succes.get(i).montant) {
				this.succes.get(i).completed = true;
				JOptionPane jop1;
				jop1 = new JOptionPane();
				jop1.showMessageDialog(null, "Vous avez obtenu le haut-fait ["+this.succes.get(i).nom+"].", "Haut Fait D�bloqu� !", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void checkMobsSucess(int id) {
		//on check d'abords le nombre de cartes diff�rentes
		String msg = "";
		boolean show = false;
		for(int i=40;i<50;i++) {
			if(this.succes.get(i).completed == false && ((this.mobs_captured) >= this.succes.get(i).montant)) {
				show = true;
				this.succes.get(i).completed = true;
				msg = "["+this.succes.get(i).nom+"]";
			}
		}
		if(show == true) {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			jop1.showMessageDialog(null, "Vous avez obtenu le haut-fait "+msg+".", "Haut Fait D�bloqu� !", JOptionPane.INFORMATION_MESSAGE);
		}

		//on s'occupe maintenant du nombre de chaque type
		msg = "";
		show = false;
		int ind = 16;
		int search = 1;
		if(id == 1) {
			this.values.set(1, this.values.get(1)+1);
		}
		else if(id == 2) {
			this.values.set(2, this.values.get(2)+1);
			ind+=4;
			search = 2;
		}
		else if(id == 3) {
			this.values.set(3, this.values.get(3)+1);
			ind+=8;
			search = 3;
		}
		else {
			this.values.set(4, this.values.get(4)+1);
			ind += 12;
			search = 4;
		}
		//on peut maintenant v�rifier les achievements
		for(int i=ind;i<ind+4;i++) {
			if(this.succes.get(i).completed == false && this.values.get(search) >= this.succes.get(i).montant) {
				show = true;
				this.succes.get(i).completed = true;
				msg = "["+this.succes.get(i).nom+"]";
			}
		}
		if(show == true) {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			jop1.showMessageDialog(null, "Vous avez obtenu le haut-fait "+msg+".", "Haut Fait D�bloqu� !", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void checkLevelSuccess() {
		String msg = "";
		boolean show = false;
		for(int i=6;i<12;i++) {
			if(this.succes.get(i).completed == false && (this.joueur.level >= this.succes.get(i).montant)) {
				this.succes.get(i).completed = true;
				msg = "["+this.succes.get(i).nom+"]";
				show = true;
			}
		}
		if(show == true) {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			jop1.showMessageDialog(null, "Vous avez obtenu le haut-fait "+msg+".", "Haut Fait D�bloqu� !", JOptionPane.INFORMATION_MESSAGE);

		}
	}

	public void checkGoldSuccess() {
		String msg = "";
		boolean show = false;
		for(int i=0;i<6;i++) {
			if(this.succes.get(i).completed == false && (this.values.get(0) >= this.succes.get(i).montant)) {
				this.succes.get(i).completed = true;
				msg = "["+this.succes.get(i).nom+"]";
				show = true;
			}
		}
		if(show == true) {
			JOptionPane jop1;
			jop1 = new JOptionPane();
			jop1.showMessageDialog(null, "Vous avez obtenu le haut-fait "+msg+".", "Haut Fait D�bloqu� !", JOptionPane.INFORMATION_MESSAGE);

		}
	}

	public int getSuccessCount() {
		int p = 0;
		for(int i=0;i<this.succes.size();i++) {
			if(this.succes.get(i).completed == true) {
				p++;
			}
		}
		return p;
	}

	public void loadData(Game gam) {
		long time_load = System.currentTimeMillis()/1000;
		long diff = time_load - gam.time_last_save;
		if(diff > 0) {
			this.joueur = gam.joueur;
			this.endseason = 2500328800L; 
			this.inventaire = gam.inventaire;
			this.magasin = gam.magasin;
			this.inventaire_runes = gam.inventaire_runes;
			//� remplacer apr�s
			this.boss = gam.boss;
			this.indice_battle = gam.indice_battle;
			this.current_season = gam.current_season;
			this.indice_fighters = gam.indice_fighters;
			this.round_count = gam.round_count;
			this.season_score = gam.season_score;
			this.season_rewards = gam.season_rewards;
			this.max_battle_level = gam.max_battle_level;

			ArrayList<HautFait> base_s = this.succes;
			this.succes = gam.succes;

			//on charge les haufs-faits manquants
			int diffhf = base_s.size() - this.succes.size();
			if(diffhf > 0) {
				for(int hf=base_s.size()-diffhf;hf<base_s.size();hf++) {
					this.succes.add(base_s.get(hf));
				}
			}

			this.values = gam.values;
			//
			this.timer = 90;
			this.time = 0;
			this.roul1 = "X";
			this.roul2 = "X";
			this.roul3 = "X";
			this.current_card_id = -1;
			this.mobs_captured = gam.mobs_captured;
			this.captured = gam.captured;
			this.current_favorite_id = gam.current_favorite_id;
			this.getCaptured();
			this.current_item_magasin = -1;
			this.current_item_selected = -1;
			this.current_success = -1;
			this.time_last_save = gam.time_last_save;
			this.quit = false;
			int nb = (int)(diff/90);
			for(int reg=0;reg<this.indice_fighters.length;reg++) {
				if(this.indice_fighters[reg] != -1) {
					for(int regtp=0;regtp<nb;regtp++) {
						if((this.joueur.collection.get(this.indice_fighters[reg]).dead == true) || (this.boss == null)) {
							this.joueur.collection.get(this.indice_fighters[reg]).regenerate();
						}
					}
				}
			}
			if(nb >= 20) {
				this.joueur.energy = this.joueur.max_energy;
			}
			else {
				int give = nb * (int)(this.joueur.max_energy * 0.05);
				if(this.joueur.energy + give >= this.joueur.max_energy) {
					this.joueur.energy = this.joueur.max_energy;
				}
				else {
					this.joueur.energy += give;
				}
			}
			//il faut v�rifier s'il n'y a pas eu de nouveaux mobs entre les versions
			int echantillon = new File("cards/"+1+"/").list().length;
			echantillon += new File("cards/"+2+"/").list().length;
			echantillon += new File("cards/"+3+"/").list().length;
			echantillon += new File("cards/"+4+"/").list().length;
			echantillon += new File("cards/"+5+"/").list().length;
			echantillon += new File("cards/"+6+"/").list().length;
			if(this.captured.size() < echantillon) {
				int dif = echantillon - this.captured.size();
				for(int j=0;j<dif;j++) {
					this.captured.add(0);
				}
			}

			this.magasin = new ArrayList<Item>();
			//on cr�er le magasin
			this.magasin.add(new Ticket(1,10000,"Un portail basique de la roue de la RNG, donnant des serviteurs communs ou sup�rieurs.","Portail de cr�ature commun",gam));
			this.magasin.add(new Ticket(2,75000,"Un portail permettant d'obtenir des serviteurs rares ou sup�rieurs � la roue de la RNG.","Portail de cr�ature rare",gam));
			this.magasin.add(new Ticket(3,800000,"Un portail de brutasse qui permet d'avoir plus de chance d'obtenir des serviteurs super rares.","Portail dimentionnel de cr�atures brutales",gam));
			this.magasin.add(new Ticket(4,10000000,"Un portail ayant de grandes chances d'octroyer un serviteur l�gendaire.","Portail dimentionnel de brutasse l�gendaire",gam));
			this.magasin.add(new GrimoireGenerator(5, 250000, "Un objet cr�ant un grimoire pour un serviteur commun.", "Collection de grimoires poussiereux", gam, 1));
			this.magasin.add(new GrimoireGenerator(6, 1000000, "Un objet cr�ant un grimoire pour un serviteur rare.", "Collection de grimoires de qualit�", gam, 2));
			this.magasin.add(new GrimoireGenerator(7, 4000000, "Un objet cr�ant un grimoire pour un serviteur super rare et hyper rare.", "Collection de grimoires runiques", gam, 3));
			this.magasin.add(new GrimoireGenerator(8, 15000000, "Un objet cr�ant un grimoire pour un serviteur l�gendaire.", "Collection de grimoires mythiques", gam, 4));
			this.magasin.add(new RuneGenerator(9, 400000, "Un objet cr�ant une rune al�atoire de qualit� inf�rieure.","Rune fissur�e non identifi�e", gam,1));
			this.magasin.add(new RuneGenerator(10, 3000000, "Un objet cr�ant une rune al�atoire de qualit� normale.","Rune de qualit� non identifi�e", gam,2));
			this.magasin.add(new RuneGenerator(11, 10000000, "Un objet cr�ant une rune al�atoire de qualit� sup�rieure.","Rune fabuleuse non identifi�e", gam,3));
			this.magasin.add(new RuneGenerator(12, 25000000, "Un objet cr�ant une rune al�atoire de qualit� l�gendaire.","Rune l�gendaire non identifi�e", gam,4));
			//
			
			this.verifyEndSeason();
			this.beginSeason();
		}
	}

	public void updateVisuals() {
		this.setChanged();
		this.notifyObservers();
	}

	public void eraseCard() {
		this.setChanged();
		this.notifyObservers(3);
	}

	public void updateTime() {
		this.time = 0;
		int ener = (int)(this.joueur.max_energy * 0.05);
		if((this.joueur.energy + ener) > this.joueur.max_energy) {
			this.joueur.energy = this.joueur.max_energy;
		}
		else {
			this.joueur.energy += ener;
		}
		for(int i=0;i<this.indice_fighters.length;i++) {
			if(this.indice_fighters[i] != -1) {
				if(this.joueur.collection.get(this.indice_fighters[i]).dead == true || this.boss == null) {
					this.joueur.collection.get(this.indice_fighters[i]).regenerate();
				}
			}
		}

		//on v�rifie que la saison n'est pas termin�e
		long time_s = System.currentTimeMillis()/1000;
		//System.out.println("time season : "+this.endseason+"\nactual time : "+time_s);
		if(time_s > this.endseason) {
			this.endSeason();
		}
		//

		this.setChanged();
		this.notifyObservers();
	}

	public void verifyEndSeason() {
		//on v�rifie que la saison n'est pas termin�e
		long time_s = System.currentTimeMillis()/1000;
		if(time_s > this.endseason) {
			this.endSeason();
		}
		//
	}


	public Carte generateCardNormal() throws IOException {
		Carte c = null;
		double chance = Math.random()*1000;
		int rarity = 0;
		if(chance <= 1) {
			rarity = 5;
		}
		else if(chance > 1 && chance <= 3){
			rarity = 4;
		}
		else if(chance > 3 && chance <= 35) {
			rarity = 3;
		}
		else if(chance > 35 && chance <= 120) {
			rarity = 2;
		}
		else {
			rarity = 1;
		}
		int echantillon = new File("cards/"+rarity+"/").list().length;
		double rand = (Math.random()*echantillon);
		int id = 1 + (int)(rand-(rand%1));
		c = new Carte("cards/"+rarity+"/char_"+id+".txt",rarity);
		return c;
	}

	public Carte generateCardRare() throws IOException {
		Carte c = null;
		double chance = Math.random()*1000;
		int rarity = 0;
		if(chance <= 3) {
			rarity = 5;
		}
		else if(chance > 3 && chance <= 9){
			rarity = 4;
		}
		else if(chance > 9 && chance <= 75) {
			rarity = 3;
		}
		else {
			rarity = 2;
		}
		int echantillon = new File("cards/"+rarity+"/").list().length;
		double rand = (Math.random()*echantillon);
		int id = 1 + (int)(rand-(rand%1));
		c = new Carte("cards/"+rarity+"/char_"+id+".txt",rarity);
		return c;
	}

	public Carte generateCardEpic() throws IOException {
		Carte c = null;
		double chance = Math.random()*1000;
		int rarity = 0;
		if(chance <= 15) {
			rarity = 5;
		}
		else if(chance > 15 && chance <= 40){
			rarity = 4;
		}
		else if(chance > 40 && chance <= 340) {
			rarity = 3;
		}
		else {
			rarity = 2;
		}
		int echantillon = new File("cards/"+rarity+"/").list().length;
		double rand = (Math.random()*echantillon);
		int id = 1 + (int)(rand-(rand%1));
		c = new Carte("cards/"+rarity+"/char_"+id+".txt",rarity);
		return c;
	}

	public Carte generateCardLegendary() throws IOException {
		Carte c = null;
		double chance = Math.random()*1000;
		int rarity = 0;
		if(chance <= 100) {
			rarity = 5;
		}
		else if(chance > 100 && chance <= 300){
			rarity = 4;
		}
		else {
			rarity = 3;
		}
		int echantillon = new File("cards/"+rarity+"/").list().length;
		double rand = (Math.random()*echantillon);
		int id = 1 + (int)(rand-(rand%1));
		c = new Carte("cards/"+rarity+"/char_"+id+".txt",rarity);
		return c;
	}

	public void rollRoulette() throws InterruptedException {
		//dans GameV2
	}

	public Grimoire getGrimoire(int rarity, int mob_id) {
		Grimoire grim = null;
		for(int i=0;i<this.inventaire.size();i++) {
			if(this.inventaire.get(i) instanceof Grimoire) {
				Grimoire gg = (Grimoire)this.inventaire.get(i);
				if((gg.rarity_increase == (rarity + 1)) && (gg.id_serviteur == mob_id)) {
					grim = gg;
				}
			}
		}
		return grim;
	}

	public void eraseUsedItems() {
		int indice = 0;
		while(indice < this.inventaire.size()) {
			if(this.inventaire.get(indice).quantity == 0) {
				this.inventaire.remove(indice);
				this.current_item_selected = -1;
			}
			else {
				indice++;
			}
		}
		//this.current_item_selected = -1;
		//this.setChanged();
		//this.notifyObservers(2);
	}

	public void sacrificeMob() {
		//GameV2
	}

	public int searchDust(int nb) {
		int sacrifiable = 0;
		if(nb == 1) {
			sacrifiable = this.joueur.fire_dust;
		}
		else if(nb == 2) {
			sacrifiable = this.joueur.leaf_dust;
		}
		else if(nb == 3) {
			sacrifiable = this.joueur.thunder_dust;
		}
		else {
			sacrifiable = this.joueur.water_dust;
		}
		return sacrifiable;
	}

	public void deleteDust(int type, int cost) {
		if(type == 1) {
			this.joueur.fire_dust -= cost;
		}
		else if(type == 1) {
			this.joueur.leaf_dust -= cost;
		}
		else if(type == 1) {
			this.joueur.thunder_dust -= cost;
		}
		else {
			this.joueur.water_dust -= cost;
		}
	}

	public void getCaptured() {
		int cap = 0;
		for(int i=0;i<this.captured.size();i++) {
			if(this.captured.get(i) == 1) {
				cap++;
			}
		}
		this.mobs_captured = cap;
	}

	public void ajouterObjet(Item i) {
		boolean trouve = false;
		int idt = 0;
		if((i instanceof Grimoire)) {
			for(int j=0;j<this.inventaire.size();j++) {
				if(this.inventaire.get(j) instanceof Grimoire) {
					if((this.inventaire.get(j).id == i.id) && (((Grimoire)this.inventaire.get(j)).id_serviteur == ((Grimoire)i).id_serviteur) && (((Grimoire)this.inventaire.get(j)).rarity_increase == ((Grimoire)i).rarity_increase) ) {
						trouve = true;
						idt = j;
					}
				}
			}
		}
		else if(i instanceof GrimoireGenerator) {
			for(int j=0;j<this.inventaire.size();j++) {
				if(this.inventaire.get(j) instanceof GrimoireGenerator) {
					if((this.inventaire.get(j).id == i.id) && (((GrimoireGenerator)this.inventaire.get(j)).id == ((GrimoireGenerator)i).id) && (((GrimoireGenerator)this.inventaire.get(j)).quality == ((GrimoireGenerator)i).quality) ) {
						trouve = true;
						idt = j;
					}
				}
			}
		}
		else if(i instanceof RuneGenerator) {
			for(int j=0;j<this.inventaire.size();j++) {
				if(this.inventaire.get(j) instanceof RuneGenerator) {
					if((this.inventaire.get(j).id == i.id) && (((RuneGenerator)this.inventaire.get(j)).id == ((RuneGenerator)i).id) && (((RuneGenerator)this.inventaire.get(j)).rarity == ((RuneGenerator)i).rarity) ) {
						trouve = true;
						idt = j;
					}
				}
			}
		}
		else if(i instanceof EventTicket) {
			trouve = false;
		}
		else if(i instanceof TemporalTicket) {
			trouve = false;
		}
		else if(i instanceof Potion) {
			for(int j=0;j<this.inventaire.size();j++) {
				if(this.inventaire.get(j) instanceof Potion) {
					if((this.inventaire.get(j).id == i.id) && (((Potion)this.inventaire.get(j)).id == ((Potion)i).id) && (((Potion)this.inventaire.get(j)).energy_give == ((Potion)i).energy_give) ) {
						trouve = true;
						idt = j;
					}
				}
			}
		}
		else {
			for(int j=0;j<this.inventaire.size();j++) {
				if(this.inventaire.get(j).id == i.id) {
					trouve = true;
					idt = j;
				}
			}
		}
		if(trouve == false) {
			i.quantity = 1;
			this.inventaire.add(i);
		}
		else {
			this.inventaire.get(idt).quantity++;
		}
	}

	public void triParRarete() {
		boolean favorite_deplaced = false;
		int rartab = 9;
		int nbrar = 0;
		int added = 0;
		// pour pas perdre les id de combat
		int[] choose = new int[3];
		for(int p=0;p<choose.length;p++) {
			choose[p] = -1;
		}
		int[] tpos = new int[3];
		for(int m=0;m<tpos.length;m++) {
			tpos[m] = 0;
		}
		//
		ArrayList<Carte> copie = new ArrayList<Carte>();
		while(rartab > 0) {
			ArrayList<Carte> tabtrans = new ArrayList<Carte>();
			for(int i=0;i<this.joueur.collection.size();i++) {
				if(this.joueur.collection.get(i).rarity_id == rartab) {
					nbrar++;
				}
			}
			if(nbrar > 0) {
				while(nbrar != 0) {
					int best_lvl=0;
					int best_ind=0;
					for(int j=0;j<this.joueur.collection.size();j++) {
						if(this.joueur.collection.get(j).rarity_id == rartab) {
							if(this.joueur.collection.get(j).level > best_lvl) {
								best_lvl = this.joueur.collection.get(j).level;
								best_ind = j;
							}
						}
					}
					if(favorite_deplaced == false) {
						if(this.current_favorite_id == best_ind) {
							this.current_favorite_id = added;
							favorite_deplaced = true;
						}
						else {
							if(this.current_favorite_id > best_ind) {
								this.current_favorite_id--;
							}
						}
					}
					added++;
					tabtrans.add(this.joueur.collection.get(best_ind));
					//on cherche a quel endroit les combattant se retrouvent apr�s un tri par raret�
					for(int m=0;m<this.indice_fighters.length;m++) {
						if(this.indice_fighters[m] != -1) {
							if((this.indice_fighters[m] == (best_ind+tpos[m])) && (choose[m] == -1)) {
								choose[m] = added-1;
							}
							else if((this.indice_fighters[m] > (best_ind+tpos[m])) && (choose[m] == -1)) {
								tpos[m] += 1;
							}
						}
					}
					//
					this.joueur.collection.remove(best_ind);
					nbrar--;
				}
				for(int k=0;k<tabtrans.size();k++) {
					copie.add(tabtrans.get(k));
				}
			}
			rartab--;
		}
		for(int w=0;w<choose.length;w++) {
			this.indice_fighters[w] = choose[w];
		}
		for(int kk=0;kk<copie.size();kk++) {
			this.joueur.collection.add(copie.get(kk));
		}
	}

	public boolean canEquipRune(Rune r, int ind) {
		boolean can = false;
		if(this.joueur.collection.get(this.current_card_runed).prestige > 0) {
			can = true;
		}
		else {
			if(r.rarete == 1) {
				can = true;
			}
			else if(r.rarete == 2) {
				if((this.joueur.collection.get(this.current_card_runed).rarity_id >= 2) && (this.joueur.level >= 10)) {
					can = true;
				}
			}
			else if(r.rarete == 3) {
				if((this.joueur.collection.get(this.current_card_runed).rarity_id >= 4) && (this.joueur.level >= 25)) {
					can = true;
				}
			}
			else if(r.rarete == 4) {
				if((this.joueur.collection.get(this.current_card_runed).rarity_id >= 5) && (this.joueur.level >= 40)) {
					can = true;
				}
			}
			else {
				if((this.joueur.collection.get(this.current_card_runed).rarity_id >= 6) && (this.joueur.level >= 60)) {
					can = true;
				}
			}
		}
		return can;
	}

	public void fightThePower() {
		this.round_count++;
		this.joueur.energy -= this.boss.cost_atk;
		//on g�re l'attaque des deux 
		if(this.indice_fighters[0] != -1) {
			if(this.joueur.collection.get(this.indice_fighters[0]).dead == false){
				this.attackBoss(0);
			}
		}
		if(this.indice_fighters[1] != -1) {
			if(this.joueur.collection.get(this.indice_fighters[1]).dead == false){
				this.attackBoss(1);
			}
		}
		if(boss.pv > 0) {
			this.boss.attack(this);
		}
		if(this.indice_fighters[2] != -1) {
			if(this.joueur.collection.get(this.indice_fighters[2]).dead == false){
				this.healAllies();
			}
		}
		//
	}

	public void finishTheFight() {
		//GameV2
	}

	public void attackBoss(int indice) {
		//il faut d'abords initialiser les stats
		int effective_def = (this.boss.defense/2);
		long diff_energy = this.joueur.max_energy - (this.boss.cost_atk*10);
		double effect = 1.0;
		if(diff_energy > 0) {
			effect += (diff_energy/10) * 0.1;
		}
		else if(diff_energy < 0) {
			effective_def = (int)((double)effective_def * (double)(1+(0.1*diff_energy)));
		}
		int dmg_player = (int) ( ((double)this.joueur.collection.get(this.indice_fighters[indice]).atk_fight * effect)-effective_def);
		double chance_critical = (double)this.joueur.collection.get(this.indice_fighters[indice]).agi_fight / (double)(this.joueur.collection.get(this.indice_fighters[indice]).agi_fight + 12000);
		boolean critical = false;
		if(Math.random() <= chance_critical) {
			dmg_player = dmg_player * 2;
			critical = true;
		}
		//on doit aussi g�rer le type
		double change = 1.0;
		int type_p = this.joueur.collection.get(this.indice_fighters[indice]).type_id;
		if(type_p == 1) {
			if(this.boss.type_id == 4) {
				change = 0.5;
			}
			else if(this.boss.type_id == 2) {
				change = 2;
			}
		}
		else if(type_p == 4) {
			if(this.boss.type_id == 3) {
				change = 0.5;
			}
			else if(this.boss.type_id == 1) {
				change = 2;
			}
		}
		else {
			if(this.boss.type_id == (type_p-1)) {
				change = 0.5;
			}
			else if(this.boss.type_id == (type_p+1)) {
				change = 2;
			}
		}

		if(this.indice_fighters[indice] == this.current_favorite_id) {
			dmg_player = (int)((double)dmg_player * 1.1);
		}

		//passif secret du boss 2
		if(this.boss.id == 2) {
			if(critical == true) {
				int retail = (int)((double)this.joueur.collection.get(this.indice_fighters[indice]).atk_fight*0.4*change);
				for(int i=0;i<this.indice_fighters.length;i++) {
					if(this.indice_fighters[i] != -1) {
						if(this.joueur.collection.get(this.indice_fighters[i]).dead != true) {
							if(this.joueur.collection.get(this.indice_fighters[i]).current_hp_fight-retail < 0) {
								this.joueur.collection.get(this.indice_fighters[i]).current_hp_fight = 0;
								this.joueur.collection.get(this.indice_fighters[i]).dead = true;
							}
							else {
								this.joueur.collection.get(this.indice_fighters[i]).current_hp_fight -= retail;

							}
						}
					}
				}
			}
		}
		//

		//System.out.println("fighter "+indice+" : "+dmg_player);
		//
		int dmg_mad = this.joueur.collection.get(this.indice_fighters[indice]).atk_fight;

		if(dmg_player >= 20000) {
			if(this.succes.get(32).completed == false) {
				this.succes.get(32).completed = true;
				JOptionPane jop1;
				jop1 = new JOptionPane();
				jop1.showMessageDialog(null, "Vous avez obtenu le haut-fait ["+this.succes.get(32).nom+"].", "Haut Fait D�bloqu� !", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		//passif secret du boss 4
		boolean mad = false;
		double rnd = Math.random();
		if(rnd < 0.1) {
			mad = true;
		}
		if(mad == true && this.boss.id == 4) {
			change = 1.0;
			type_p = this.joueur.collection.get(this.indice_fighters[indice]).type_id;
			if(type_p == 1) {
				if(this.joueur.collection.get(this.indice_fighters[2]).type_id == 4) {
					change = 0.5;
				}
				else if(this.joueur.collection.get(this.indice_fighters[2]).type_id == 2) {
					change = 2;
				}
			}
			else if(type_p == 4) {
				if(this.joueur.collection.get(this.indice_fighters[2]).type_id == 3) {
					change = 0.5;
				}
				else if(this.joueur.collection.get(this.indice_fighters[2]).type_id == 1) {
					change = 2;
				}
			}
			else {
				if(this.joueur.collection.get(this.indice_fighters[2]).type_id == (type_p-1)) {
					change = 0.5;
				}
				else if(this.joueur.collection.get(this.indice_fighters[2]).type_id == (type_p+1)) {
					change = 2;
				}
			}
			dmg_mad = (int)((double)dmg_mad * change);
			if(this.joueur.collection.get(this.indice_fighters[2]).current_hp_fight - dmg_mad <= 0) {
				this.joueur.collection.get(this.indice_fighters[2]).current_hp_fight = 0;
				this.joueur.collection.get(this.indice_fighters[2]).dead = true;
			}
			else {
				this.joueur.collection.get(this.indice_fighters[2]).current_hp_fight -= dmg_mad;
			}
		} // fin passif boss 4
		else {
			dmg_player = (int) (dmg_player * change);
			if(this.boss.pv - dmg_player <= 0) {
				if(this.boss.id == 5 && ((Boss)this.boss).status[indice] == 1) {

				}else {
					this.boss.pv = 0;
				}
			}
			else {
				if(this.boss.id == 5 && ((Boss)this.boss).status[indice] == 1) {

				}else {
					this.boss.pv -= dmg_player;
					//phase 2 de nocturne
					if(this.boss.lvl >= 25 && this.boss.id == 5) {
						if(((double)this.boss.pv / (double)this.boss.pv_max) <= 0.25 && (((Boss)this.boss).status[3] == 0)) {
							this.boss.defense = (int)((double)this.boss.defense * 2.5);
							this.boss.image_link = "images/event_boss_5-2.jpg";
							((Boss)this.boss).status[3] = 1;
							WavPlayer wp = new WavPlayer(new File("Sounds/boss5_phase2.wav"));
							wp.open();
							wp.play();
						}
					}
				}
				//
			}
		}
	}

	public void healAllies() {
		Carte c = this.joueur.collection.get(this.indice_fighters[2]);
		int multheal = 1 + (int)(0.5*(c.vit_fight-(c.vit_fight/100))/100);
		int heal_value = (int)((c.atk_fight+c.agi_fight)*0.1);
		int heal_total = heal_value*(1+multheal);
		double chance_critical = this.joueur.collection.get(this.indice_fighters[2]).agi_fight / (this.joueur.collection.get(this.indice_fighters[2]).agi_fight + 12000);

		if(this.indice_fighters[2] == this.current_favorite_id) {
			heal_total = (int)((double)heal_total * 1.1);
		}

		//passif peur boss 5
		if(this.boss.id == 5 && ((Boss)this.boss).status[2] == 1) {

		}
		else {
			//on heal chaque combattant � tour de r�le
			for(int i=0;i<this.indice_fighters.length;i++) {
				if(this.indice_fighters[i] != -1) {
					if(this.joueur.collection.get(this.indice_fighters[i]).dead != true) {
						int life_given = heal_total;
						if(Math.random() <= chance_critical) {
							life_given = life_given * 2;
						}
						//System.out.println("healing to "+i+" : "+life_given);
						if(this.joueur.collection.get(this.indice_fighters[i]).current_hp_fight+life_given >= this.joueur.collection.get(this.indice_fighters[i]).hp_fight) {
							this.joueur.collection.get(this.indice_fighters[i]).current_hp_fight = this.joueur.collection.get(this.indice_fighters[i]).hp_fight;
						}
						else {
							this.joueur.collection.get(this.indice_fighters[i]).current_hp_fight += life_given;
						}
					}
				}
			}
		}

		//passif boss 5
		for(int i=0;i<3;i++) {
			((Boss)this.boss).status[i] = 0;
		}
	}

	public void beginSeason() {
		long l = System.currentTimeMillis()/1000;
		if(l < 2500328800L) {
			if(this.current_season != 2) {
				this.current_season = 2;
				this.season_score = 0;
				this.boss = null;
				this.max_battle_level = 1;
				this.season_rewards = new SeasonRewards(this.current_season);
				//on g�re le temps
				this.endseason = 2500328800L;
			}
		}
		else {
			this.current_season = -1;
		}
	}

	public void endSeason() {
		if(this.current_season == 2) {
			this.current_season = -1;
			JOptionPane jop1;
			jop1 = new JOptionPane();
			//on v�rifie si on a bien la conqu�te de saison
			boolean conquest = true;
			if(this.max_battle_level < 21) {
				conquest = false;
			}
			if(this.season_score < 300000) {
				conquest = false;
			}
			//on boucle pour voir si un des l�gendaires de saison est en SM
			boolean found = false;
			for(int i=0;i<this.joueur.collection.size();i++) {
				if((this.joueur.collection.get(i).id == 62 || this.joueur.collection.get(i).id == 63 || this.joueur.collection.get(i).id == 64 || this.joueur.collection.get(i).id == 65) && this.joueur.collection.get(i).rarity_id == 9) {
					found = true;
				}
			}
			if(conquest == true && found == true) {
				String s ="";
				if(this.succes.get(14).completed == false) {
					this.succes.get(14).completed = true;
					s = " Vous d�bloquez de plus le haut-fait ["+this.succes.get(14).nom+"]";
				}
				EventTicket et = new EventTicket(20, 1000000, "Contient en exemplaire unique un serviteur de saison", "Coeur de l'Eternel cauchemar", this,9);
				this.ajouterObjet(et);
				jop1.showMessageDialog(null, "La saison est termin�e, vous ne pouvez plus obtenir de r�compenses saisonni�re, mais vous pouvez continuer les combats ! Votre acharnement vous a permi d'obtenir la conqu�te de saison, vous donnant ainsi un serviteur Mythique exclusif !"+s, "Fin de saison", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				jop1.showMessageDialog(null, "La saison est termin�e, vous ne pouvez plus obtenir de r�compenses saisonni�re, mais vous pouvez continuer les combats !", "Fin de saison", JOptionPane.INFORMATION_MESSAGE);
			}
			this.setChanged();
			this.notifyObservers();
		}
	}

	public void dezDeMasse(int i) {
		//GameV2
	}
}
