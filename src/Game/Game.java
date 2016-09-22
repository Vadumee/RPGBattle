package Game;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
	
	public long time_last_save = 0;
	public boolean quit; 
	
	//à mettre dans event après
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

	public Game() {
		this.time = 0;
		//à remplacer par event après
		this.boss = null;
		this.indice_fighters = new int[3];
		for(int i=0;i<3;i++) {
			this.indice_fighters[i] = -1;
		}
		this.round_count = 0;
		this.season_score = 0;
		this.current_season = -1;
		this.season_rewards = new SeasonRewards(this.current_season);
		//à modifier lors de saisons différentes//
		this.beginSeason();
		//////////////////////////////////////////
		//
		this.mobs_captured = 0;
		this.quit = false;
		this.joueur = new Player();
		this.inventaire = new ArrayList<Item>();
		this.magasin = new ArrayList<Item>();
		this.inventaire_runes = new ArrayList<Rune>();
		//on initialise le tableau des capturés
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
		
		//on créer le magasin
		this.magasin.add(new Ticket(1,10000,"Un portail basique de la roue de la RNG, donnant des serviteurs communs ou supérieurs.","Portail de créature commun",this));
		this.magasin.add(new Ticket(2,75000,"Un portail permettant d'obtenir des serviteurs rares ou supérieurs à la roue de la RNG.","Portail de créature rare",this));
		this.magasin.add(new Ticket(3,800000,"Un portail de brutasse qui permet d'avoir plus de chance d'obtenir des serviteurs super rares.","Portail dimentionnel de créatures brutales",this));
		this.magasin.add(new Ticket(4,10000000,"Un portail ayant de grandes chances d'octroyer un serviteur légendaire.","Portail dimentionnel de brutasse légendaire",this));
		this.magasin.add(new GrimoireGenerator(5, 250000, "Un objet créant un grimoire pour un serviteur commun.", "Collection de grimoires poussiereux", this, 1));
		this.magasin.add(new GrimoireGenerator(6, 1000000, "Un objet créant un grimoire pour un serviteur rare.", "Collection de grimoires de qualité", this, 2));
		this.magasin.add(new GrimoireGenerator(7, 4000000, "Un objet créant un grimoire pour un serviteur super rare et hyper rare.", "Collection de grimoires runiques", this, 3));
		this.magasin.add(new GrimoireGenerator(8, 15000000, "Un objet créant un grimoire pour un serviteur légendaire.", "Collection de grimoires mythiques", this, 4));
		this.magasin.add(new RuneGenerator(9, 400000, "Un objet créant une rune aléatoire de qualité inférieure.","Rune fissurée non identifiée", this,1));
		this.magasin.add(new RuneGenerator(10, 3000000, "Un objet créant une rune aléatoire de qualité normale.","Rune de qualité non identifiée", this,2));
		this.magasin.add(new RuneGenerator(11, 10000000, "Un objet créant une rune aléatoire de qualité supérieure.","Rune fabuleuse non identifiée", this,3));
		this.magasin.add(new RuneGenerator(12, 25000000, "Un objet créant une rune aléatoire de qualité légendaire.","Rune légendaire non identifiée", this,4));
		//
	}
	
	public void loadData(Game gam) {
		long time_load = System.currentTimeMillis()/1000;
		long diff = time_load - gam.time_last_save;
		if(diff > 0) {
			this.joueur = gam.joueur;
			this.inventaire = gam.inventaire;
			this.magasin = gam.magasin;
			this.inventaire_runes = gam.inventaire_runes;
			//à remplacer après
			this.boss = gam.boss;
			this.indice_battle = gam.indice_battle;
			this.indice_fighters = gam.indice_fighters;
			this.round_count = gam.round_count;
			this.season_score = gam.season_score;
			this.season_rewards = gam.season_rewards;
			//il faudra gérer le temps
			this.current_season = 1;
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
			this.time_last_save = gam.time_last_save;
			this.quit = false;
			int nb = (int)(diff/90);
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
			//il faut vérifier s'il n'y a pas eu de nouveaux mobs entre les versions
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
			this.verifyEndSeason();
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
				this.joueur.collection.get(this.indice_fighters[i]).regenerate();
			}
		}
		
		//on vérifie que la saison n'est pas terminée
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
		//on vérifie que la saison n'est pas terminée
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
		String[] liste = {"Gx3","Gx5","Gx10","Gx20","Expx3","Port","R Port !","Obj"};
		double rnd1 = Math.random()*8; 
		int r1 = (int)(rnd1 - (rnd1%1));
		double rnd2 = Math.random()*8; 
		int r2 = (int)(rnd2 - (rnd2%1));
		double r = Math.random()*100;
		if(r2 != r1) {
			if(r <= 40) {
				r2 = r1;
			}
		}
		double rnd3 = Math.random()*8; 
		int r3 = (int)(rnd1 - (rnd3%1));
		r = Math.random()*100;
		if(r3 != r1) {
			if(r <= 20) {
				r3 = r1;
			}
		}
		
		this.roul1 = liste[r1];
		this.roul2 = liste[r2];
		this.roul3 = liste[r3];
		
		this.joueur.energy -= (2 + (this.joueur.level/2));
		
		//on s'occupe du résultat
		int reward_level = this.joueur.level;
		long xp = 2 + (this.joueur.level / 2) * 1L;
		int cap = ((this.joueur.level - (this.joueur.level%10))/10)+1;
		long gd = 200 + (((this.joueur.level-1) * 80L)*cap);
		if((this.roul1 == "R Port !") && (this.roul2 == "R Port !") && (this.roul3 == "R Port !")) {
			this.ajouterObjet(this.magasin.get(1));
			this.joueur.giveExp(xp);
		}
		else if((this.roul1 == "Port") && (this.roul2 == "Port") && (this.roul3 == "Port")) {
			this.ajouterObjet(this.magasin.get(0));
			this.joueur.giveExp(xp);
		}
		else if((this.roul1 == "Gx20") && (this.roul2 == "Gx20") && (this.roul3 == "Gx20")) {
			this.joueur.gold += (gd * 20L);
			this.joueur.giveExp(xp);
		}
		else if((this.roul1 == "Gx10") && (this.roul2 == "Gx10") && (this.roul3 == "Gx10")) {
			this.joueur.gold += (gd * 10L);
			this.joueur.giveExp(xp);
		}
		else if((this.roul1 == "Gx5") && (this.roul2 == "Gx5") && (this.roul3 == "Gx5")) {
			this.joueur.gold += (gd * 5L);
			this.joueur.giveExp(xp);
		}
		else if((this.roul1 == "Gx3") && (this.roul2 == "Gx3") && (this.roul3 == "Gx3")) {
			this.joueur.gold += (gd * 3L);
			this.joueur.giveExp(xp);
		}
		else if((this.roul1 == "Expx3") && (this.roul2 == "Expx3") && (this.roul3 == "Expx3")) {
			this.joueur.giveExp(xp * 3L);
		}
		else if((this.roul1 == "Obj") && (this.roul2 == "Obj") && (this.roul3 == "Obj")) {
			this.joueur.giveExp(xp);
			this.ajouterObjet(new ItemGenerator(17,1,"Un coffre contenant un objet mystère !","Coffre de butin brutal",this));
		}
		else {
			this.joueur.gold += (gd);
			this.joueur.giveExp(xp);
		}
		
		this.setChanged();
		this.notifyObservers(2);
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
		//on gere le give d'exp
		double xpay = 100.0;
		int i = 0;
		while(i<this.joueur.collection.size()) {
			if(i != this.current_card_id) {
				if(this.joueur.collection.get(i).id == this.joueur.collection.get(this.current_card_id).id) {
					int diff = this.joueur.collection.get(i).rarity_id - this.joueur.collection.get(this.current_card_id).rarity_id;
					for(int j=0;j<diff;j++) {
						xpay = xpay / 2;
					}
					xpay = xpay / 100;
					this.joueur.collection.get(i).giveExp((long)(1L*xpay * this.joueur.collection.get(i).max_exp));
					i = this.joueur.collection.size();
				}
			}
			i++;
		}
		//dust
		int dust = 0;
		if((this.joueur.collection.get(this.current_card_id).rarity_id == 9) && (joueur.collection.get(current_card_id).level == joueur.collection.get(current_card_id).max_level)) {
			dust = 1000;
		}
		else if((this.joueur.collection.get(this.current_card_id).rarity_id == 8) && (joueur.collection.get(current_card_id).level == joueur.collection.get(current_card_id).max_level)) {
			dust = 500;
		}
		else if((this.joueur.collection.get(this.current_card_id).rarity_id == 7) && (joueur.collection.get(current_card_id).level == joueur.collection.get(current_card_id).max_level)) {
			dust = 250;
		}
		else if((this.joueur.collection.get(this.current_card_id).rarity_id == 6) && (joueur.collection.get(current_card_id).level == joueur.collection.get(current_card_id).max_level)) {
			dust = 100;
		}
		else if((this.joueur.collection.get(this.current_card_id).rarity_id == 5) && (joueur.collection.get(current_card_id).level == joueur.collection.get(current_card_id).max_level)) {
			dust = 40;
		}
		if(this.joueur.collection.get(current_card_id).type_id == 1) {
			this.joueur.fire_dust += dust;
		}
		else if(this.joueur.collection.get(current_card_id).type_id == 2) {
			this.joueur.leaf_dust += dust;
		}
		else if(this.joueur.collection.get(current_card_id).type_id == 3) {
			this.joueur.thunder_dust += dust;
		}
		else {
			this.joueur.water_dust += dust;
		}
		this.setChanged();
		this.notifyObservers();
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
					//on cherche a quel endroit les combattant se retrouvent après un tri par rareté
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
				if(this.joueur.collection.get(this.current_card_runed).rarity_id >= 3) {
					can = true;
				}
			}
			else if(r.rarete == 3) {
				if(this.joueur.collection.get(this.current_card_runed).rarity_id >= 5) {
					can = true;
				}
			}
			else if(r.rarete == 4) {
				if(this.joueur.collection.get(this.current_card_runed).rarity_id >= 7) {
					can = true;
				}
			}
			else {
				if(this.joueur.collection.get(this.current_card_runed).rarity_id == 9) {
					can = true;
				}
			}
		}
		return can;
	}
	
	public void fightThePower() {
		this.round_count++;
		this.joueur.energy -= this.boss.cost_atk;
		//on gère l'attaque des deux 
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
		this.joueur.giveExp(300L+(150L*(boss.lvl-1)));
		this.joueur.gold += 1L*(500000+(300000L*(boss.lvl-1)));
		for(int i=0;i<4;i++) {
			double alea = Math.random()*4;
			int alea1 = 1+(int)(alea - (alea%1));
			Rune g = new Rune(5,alea1,boss.lvl-1,boss.id,(1000000)+(400000*boss.lvl));
			this.inventaire_runes.add(g);
			
		}
		
		if(boss.lvl == this.max_battle_level) {
			this.max_battle_level++;
			this.season_score += (int)((double)boss.pv_max/100);
		}
		else {
			this.season_score += (int)((double)boss.pv_max/2000);
		}
		boss = null;
		if(this.current_season != -1) {
			this.season_rewards.checkRewards(this);
		}
		this.round_count = 0;
		this.eraseCard();
	}
	
	public void attackBoss(int indice) {
		//il faut d'abords initialiser les stats
		int dmg_player = (int) (this.joueur.collection.get(this.indice_fighters[indice]).atk_fight*((double)this.joueur.collection.get(this.indice_fighters[indice]).atk_fight / ((double)this.joueur.collection.get(this.indice_fighters[indice]).atk_fight + (double)this.boss.defense)));
		double chance_critical = (double)this.joueur.collection.get(this.indice_fighters[indice]).agi_fight / (double)(this.joueur.collection.get(this.indice_fighters[indice]).agi_fight + 12000);
		if(Math.random() <= chance_critical) {
			dmg_player = dmg_player * 2;
		}
		//on doit aussi gérer le type
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
		//on gere le passif de jakuzure
		if(this.boss.id == 2) {
			dmg_player = (int) ((double)dmg_player * 1.2);
		}
		//
		
		//System.out.println("fighter "+indice+" : "+dmg_player);
		//
		dmg_player = (int) (dmg_player * change);
		if(this.boss.pv - dmg_player <= 0) {
			this.boss.pv = 0;
		}
		else {
			this.boss.pv -= dmg_player;
		}
		
		//on gere le passif de gamagori
		if(this.boss.id == 1) {
			int retail = (int)(this.boss.defense / 2);
			if(this.joueur.collection.get(this.indice_fighters[indice]).current_hp_fight - retail <= 0) {
				this.joueur.collection.get(this.indice_fighters[indice]).current_hp_fight = 0;
				this.joueur.collection.get(this.indice_fighters[indice]).dead = true;
			}
			else {
				this.joueur.collection.get(this.indice_fighters[indice]).current_hp_fight -= retail;
			}
		}
		//
	}
	
	public void healAllies() {
		Carte c = this.joueur.collection.get(this.indice_fighters[2]);
		int multheal = (int)((c.vit_fight-(c.vit_fight/100)))/200;
		int heal_value = (int)((c.atk_fight+c.agi_fight)*0.1);
		int heal_total = heal_value*(1+multheal);
		double chance_critical = this.joueur.collection.get(this.indice_fighters[2]).agi_fight / (this.joueur.collection.get(this.indice_fighters[2]).agi_fight + 12000);
		
		//on gere le passif de jakuzure
		if(this.boss.id == 2) {
			heal_total = (int) ((double)heal_total * 0.5);
		}
		//
		
		//on heal chaque combattant à tour de rôle
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
	
	public void beginSeason() {
		long l = System.currentTimeMillis()/1000;
		if(l < 1483097561) {
			this.current_season = 1;
			this.season_score = 0;
			this.season_rewards = new SeasonRewards(this.current_season);
			//on gère le temps
			this.endseason = 1483097561;
		}
	}
	
	public void endSeason() {
		if(this.current_season != -1) {
			this.current_season = -1;
			JOptionPane jop1;
			jop1 = new JOptionPane();
			//on vérifie si on a bien la conquête de saison
			boolean conquest = true;
			if(this.max_battle_level < 21) {
				conquest = false;
			}
			if(this.season_score < 200000) {
				conquest = false;
			}
			//on boucle pour voir si un des légendaires de saison est en SM
			boolean found = false;
			for(int i=0;i<this.joueur.collection.size();i++) {
				if((this.joueur.collection.get(i).id == 41 || this.joueur.collection.get(i).id == 42 || this.joueur.collection.get(i).id == 43) && this.joueur.collection.get(i).rarity_id == 9) {
					found = true;
				}
			}
			if(conquest == true) {
				EventTicket et = new EventTicket(20, 1000000, "Contient en exemplaire unique un serviteur de saison", "Coffret de conquérant de l'académie Honnoji", this,4);
				this.ajouterObjet(et);
				jop1.showMessageDialog(null, "La saison est terminée, vous ne pouvez plus obtenir de récompenses saisonnière, mais vous pouvez continuer les combats ! Votre acharnement vous a permi d'obtenir la conquête de saison, vous donnant ainsi un serviteur Mythique exclusif !", "Fin de saison", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				jop1.showMessageDialog(null, "La saison est terminée, vous ne pouvez plus obtenir de récompenses saisonnière, mais vous pouvez continuer les combats !", "Fin de saison", JOptionPane.INFORMATION_MESSAGE);
			}
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	public void dezDeMasse(int i) {
		double xpay = 100.0;
		int diff = this.joueur.collection.get(this.current_card_id).rarity_id - this.joueur.collection.get(i).rarity_id;
		for(int j=0;j<diff;j++) {
			xpay = xpay / 2;
		}
		xpay = xpay / 100;
		this.joueur.collection.get(this.current_card_id).giveExp((long)(1L*xpay * this.joueur.collection.get(this.current_card_id).max_exp));
	
		//dust
		int dust = 0;
		if((this.joueur.collection.get(i).rarity_id == 9) && (this.joueur.collection.get(i).level == this.joueur.collection.get(i).max_level)) {
			dust = 1000;
		}
		else if((this.joueur.collection.get(i).rarity_id == 8) && (this.joueur.collection.get(i).level == this.joueur.collection.get(i).max_level)) {
			dust = 500;
		}
		else if((this.joueur.collection.get(i).rarity_id == 7) && (this.joueur.collection.get(i).level == this.joueur.collection.get(i).max_level)) {
			dust = 250;
		}
		else if((this.joueur.collection.get(i).rarity_id == 6) && (this.joueur.collection.get(i).level == this.joueur.collection.get(i).max_level)) {
			dust = 100;
		}
		else if((this.joueur.collection.get(i).rarity_id == 5) && (this.joueur.collection.get(i).level == this.joueur.collection.get(i).max_level)) {
			dust = 40;
		}
		if(this.joueur.collection.get(i).type_id == 1) {
			this.joueur.fire_dust += dust;
		}
		else if(this.joueur.collection.get(i).type_id == 2) {
			this.joueur.leaf_dust += dust;
		}
		else if(this.joueur.collection.get(i).type_id == 3) {
			this.joueur.thunder_dust += dust;
		}
		else {
			this.joueur.water_dust += dust;
		}
	}
}
