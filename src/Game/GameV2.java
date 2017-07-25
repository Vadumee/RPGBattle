package Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GameV2 extends Game {
	
	public long arcane_cristals;
	public long void_cristals;
	public long void_used;
	public int fight_level;
	public long fight_exp;
	public long fight_max_exp;
	public Talent[] talents;
	
	public GameV2() {
		super();
		this.arcane_cristals = 0;
		this.void_cristals = 0;
		this.fight_level = 0;
		this.fight_exp = 0;
		this.fight_max_exp = 500;
		this.talents = new Talent[23];
		for(int i=0;i<23;i++) {
			if(i < 8) {
				this.talents[i] = new Talent(i+1,1);
			}
			else if(i < 16) {
				this.talents[i] = new Talent(i+1,10);
			}
			else if(i == 16 || i == 17) {
				this.talents[i] = new Talent(i+1,100);
			}
			else {
				this.talents[i] = new Talent(i+1,50);
			}
		}
	}
	
	public GameV2(Game g) {
		super();
		this.joueur = g.joueur;
		this.inventaire = g.inventaire;
		this.magasin = g.magasin;
		this.captured = g.captured;
		this.inventaire_runes = g.inventaire_runes;
		this.mobs_captured = g.mobs_captured;
		this.timer = 90;
		this.time = g.time;

		this.roul1 = "X";
		this.roul2 = "X";
		this.roul3 = "X";

		this.auto_select = false;

		this.current_card_id = -1;
		this.current_item_selected = -1;
		this.current_item_magasin = -1;
		this.current_favorite_id = -1;
		this.current_rune_selected = -1;
		this.current_card_runed = -1;
		this.current_success = -1;

		this.time_last_save = g.time_last_save;
		this.quit = false; 

		this.succes = g.succes;

		//à mettre dans event après
		this.indice_battle = g.indice_battle;
		this.boss = g.boss;
		this.indice_fighters = g.indice_fighters;
		this.max_battle_level = g.max_battle_level;
		this.round_count = g.round_count;
		this.season_score = g.season_score;
		this.current_season = g.current_season;
		this.season_rewards = g.season_rewards;
		this.endseason = g.endseason;

		//stats event
		this.values = g.values;
		//
		this.arcane_cristals = 0;
		this.void_cristals = 0;
		this.fight_level = 0;
		this.fight_exp = 0;
		this.fight_max_exp = 500;
		this.talents = new Talent[23];
		for(int i=0;i<23;i++) {
			if(i < 8) {
				this.talents[i] = new Talent(i+1,1);
			}
			else if(i < 16) {
				this.talents[i] = new Talent(i+1,10);
			}
			else if(i == 16 || i == 17) {
				this.talents[i] = new Talent(i+1,100);
			}
			else {
				this.talents[i] = new Talent(i+1,50);
			}
		}
	}
	
	public void convertRunes() {
		for(int i=0;i<this.inventaire_runes.size();i++) {
			Rune r = this.inventaire_runes.get(i);
			this.inventaire_runes.set(i, new UpgradableRune(r));
		}
		for(int j=0;j<this.joueur.collection.size();j++) {
			for(int k=0;k<this.joueur.collection.get(j).runes.length;k++) {
				if(this.joueur.collection.get(j).runes[k] != null) {
					this.joueur.collection.get(j).runes[k] = new UpgradableRune(this.joueur.collection.get(j).runes[k]);
				}
			}
		}
	}
	
	public void calculatePlayerMaxEnergy() {
		this.joueur.max_energy = 40 + ((this.joueur.level-1) * 5);
		this.joueur.max_energy += (this.talents[6].lvl*2);
	}
	
	public void ajouterObjet(Item i) {
		super.ajouterObjet(i);
	}
	
	public void rollRoulette() throws InterruptedException {
		super.rollRoulette();
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
		xp = (long)(xp * (1 + ((double)this.talents[20].lvl*0.5)));
		int cap = ((this.joueur.level - (this.joueur.level%10))/10)+1;
		long gd = (200 + (((this.joueur.level-1) * 80L)+(this.talents[7].lvl*150L*this.joueur.level))*cap);
		gd = (long)(gd * (1 + ((double)this.talents[21].lvl*0.5)));
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
			this.values.set(0, this.values.get(0) + (gd * 20L));
			this.joueur.giveExp(xp);
		}
		else if((this.roul1 == "Gx10") && (this.roul2 == "Gx10") && (this.roul3 == "Gx10")) {
			this.joueur.gold += (gd * 10L);
			this.values.set(0, this.values.get(0) + (gd * 10L));
			this.joueur.giveExp(xp);
		}
		else if((this.roul1 == "Gx5") && (this.roul2 == "Gx5") && (this.roul3 == "Gx5")) {
			this.joueur.gold += (gd * 5L);
			this.values.set(0, this.values.get(0) + (gd * 5L));
			this.joueur.giveExp(xp);
		}
		else if((this.roul1 == "Gx3") && (this.roul2 == "Gx3") && (this.roul3 == "Gx3")) {
			this.joueur.gold += (gd * 3L);
			this.values.set(0, this.values.get(0) + (gd * 3L));
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
			this.values.set(0, this.values.get(0) + (gd));
			this.joueur.giveExp(xp);
		}
		this.calculatePlayerMaxEnergy();
		//on regarde si on a le hf de niveau
		this.checkLevelSuccess();
		//
		this.checkGoldSuccess();
		this.setChanged();
		this.notifyObservers(2);
	}
	
	public void loadData(Game gam) {
		super.loadData(gam);
		long time_load = System.currentTimeMillis()/1000;
		long diff = time_load - gam.time_last_save;
		if(diff > 0) {
			this.joueur = gam.joueur;
			this.endseason = 2500328800L; 
			this.inventaire = gam.inventaire;
			this.magasin = gam.magasin;
			this.inventaire_runes = gam.inventaire_runes;
			//à remplacer après
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

			this.magasin = new ArrayList<Item>();
			//on créer le magasin
			this.magasin.add(new Ticket(1,10000,"Un portail basique de la roue de la RNG, donnant des serviteurs communs ou supérieurs.","Portail de créature commun",gam));
			this.magasin.add(new Ticket(2,75000,"Un portail permettant d'obtenir des serviteurs rares ou supérieurs à la roue de la RNG.","Portail de créature rare",gam));
			this.magasin.add(new Ticket(3,800000,"Un portail de brutasse qui permet d'avoir plus de chance d'obtenir des serviteurs super rares.","Portail dimentionnel de créatures brutales",gam));
			this.magasin.add(new Ticket(4,10000000,"Un portail ayant de grandes chances d'octroyer un serviteur légendaire.","Portail dimentionnel de brutasse légendaire",gam));
			this.magasin.add(new GrimoireGenerator(5, 250000, "Un objet créant un grimoire pour un serviteur commun.", "Collection de grimoires poussiereux", gam, 1));
			this.magasin.add(new GrimoireGenerator(6, 1000000, "Un objet créant un grimoire pour un serviteur rare.", "Collection de grimoires de qualité", gam, 2));
			this.magasin.add(new GrimoireGenerator(7, 4000000, "Un objet créant un grimoire pour un serviteur super rare et hyper rare.", "Collection de grimoires runiques", gam, 3));
			this.magasin.add(new GrimoireGenerator(8, 15000000, "Un objet créant un grimoire pour un serviteur légendaire.", "Collection de grimoires mythiques", gam, 4));
			this.magasin.add(new RuneGenerator(9, 400000, "Un objet créant une rune aléatoire de qualité inférieure.","Rune fissurée non identifiée", gam,1));
			this.magasin.add(new RuneGenerator(10, 3000000, "Un objet créant une rune aléatoire de qualité normale.","Rune de qualité non identifiée", gam,2));
			this.magasin.add(new RuneGenerator(11, 10000000, "Un objet créant une rune aléatoire de qualité supérieure.","Rune fabuleuse non identifiée", gam,3));
			this.magasin.add(new RuneGenerator(12, 25000000, "Un objet créant une rune aléatoire de qualité légendaire.","Rune légendaire non identifiée", gam,4));
			//
			this.arcane_cristals = ((GameV2)gam).arcane_cristals;
			this.void_cristals = ((GameV2)gam).void_cristals;
			this.void_used = ((GameV2)gam).void_used;
			this.fight_level = ((GameV2)gam).fight_level;
			this.fight_exp = ((GameV2)gam).fight_exp;
			this.fight_max_exp = ((GameV2)gam).fight_max_exp;
			this.talents = ((GameV2)gam).talents;
			this.verifyEndSeason();
			this.beginSeason();
		}
	}
	
	public void updateTalents() {
		this.setChanged();
		this.notifyObservers(88);
	}
	
	public void eraseUsedItems() {
		super.eraseUsedItems();
	}
	
	public void checkBossSucess() {
		super.checkBossSucess();
	}
	
	public void checkMobsSucess(int id) {
		super.checkMobsSucess(id);
	}
	
	public void checkLevelSuccess() {
		super.checkLevelSuccess();
	}
	
	public void checkGoldSuccess() {
		super.checkGoldSuccess();
	}
	
	public int getSuccessCount() {
		return super.getSuccessCount();
	}
	
	public void updateTime() {
		super.updateTime();
	}
	
	public void verifyEndSeason() {
		super.verifyEndSeason();
	}
	
	public Carte generateCardNormal() throws IOException {
		return super.generateCardNormal();
	}
	
	public Carte generateCardRare() throws IOException {
		return super.generateCardRare();
	}
	
	public Carte generateCardEpic() throws IOException {
		return super.generateCardEpic();
	}
	
	public Carte generateCardLegendary() throws IOException {
		return super.generateCardLegendary();
	}
	
	public Grimoire getGrimoire(int rarity, int mob_id) {
		return super.getGrimoire(rarity, mob_id);
	}
	
	public int searchDust(int nb) {
		return searchDust(nb);
	}
	
	public void deleteDust(int type, int cost) {
		super.deleteDust(type, cost);
	}
	
	public void getCaptured() {
		super.getCaptured();
	}
	
	public void triParRarete() {
		super.triParRarete();
	}
	
	public boolean canEquipRune(Rune r, int ind) {
		return super.canEquipRune(r, ind);
	}
	
	public void fightThePower() {
		super.fightThePower();
	}
	
	public void attackBoss(int indice) {
		super.attackBoss(indice);
	}
	
	public void healAllies() {
		super.healAllies();
	}
	
	public void beginSeason() {
		super.beginSeason();
	}
	
	public void endSeason() {
		super.endSeason();
	}
	
	public void dezDeMasse(int i) {
		super.dezDeMasse(i);
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
		dust = (int)(dust * (1 + ((double)this.talents[22].lvl*0.25)));
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
	
	public void getCristalsBack() {
		this.void_cristals += this.void_used;
		this.void_used = 0;
		for(int i=0;i<this.talents.length;i++) {
			this.talents[i].reset();
		}
		this.updateTalents();
	}
	
	public long getVoidCristals() {
		long voidc = 0;
		//niveau de joueur
		int incr = 0;
		for(int i=0;i<this.joueur.level;i++) {
			incr++;
			if(incr == 10) {
				incr = 0;
				long mult = 1 + (long)((i - (i%50))/50);
				voidc += mult;
			}
		}
		//niveau de progress
		int incr2 = 0;
		for(int j=0;j<this.max_battle_level;j+=2) {
			incr2++;
			if(incr2 == 2) {
				incr2 = 0;
				long mult = 1 + (long)((j - (j%10))/10);
				voidc += mult;
			}
		}
		//niveau d'ascension
		for(int k=0;k<this.fight_level;k++) {
			long mult = 1 + (long)((k - (k%20))/20);
			voidc += mult;
		}
		return voidc;
	}
	
	public void giveFightExp(long exp) {
		if((this.fight_exp + exp) >= this.fight_max_exp) {
			long reste = exp - (this.fight_max_exp - this.fight_exp);
			this.fight_exp = 0;
			long mult = 2 + (long)((this.fight_level - (this.fight_level%12))/12);
			this.fight_max_exp += 400L * (this.fight_level+1) * mult; 
			System.out.println(this.fight_max_exp);
			this.fight_level++;
			if(reste >= this.fight_max_exp) {
				this.giveFightExp(reste);
			}
			else {
				this.fight_exp += reste;
			}
		}
		else {
			this.fight_exp += exp;
		}
	}
	
	public void finishTheFight() {
		double mult = 1.0 + (((joueur.level - (joueur.level%10)) / 10)*0.25);
		double mult_diff = (((boss.lvl-1)*0.45));
		long xpmtn = (long)( (300L+ (240L* (boss.lvl-1) ) )*(mult) ) ;
		long mtn =  (long) (1L*( (boss.pv_max * 10) * (mult + mult_diff) ));
		if(boss.lvl == this.max_battle_level) {
			mtn = mtn*2;
			xpmtn = xpmtn*2;
		}
		//on gère le gain de poussières
		long powder = 0;
		if(boss.lvl >= 15) {
			long base = 20;
			powder = 20;
			for(int p=15;p<boss.lvl;p++) {
				base += 5;
				powder += base;
			}
			powder = (int)(powder * (1 + ((double)this.talents[22].lvl*0.25)));
			//on donne selon le type
			if(boss.type_id == 1) {
				this.joueur.fire_dust += powder;
			}
			else if(boss.type_id == 2) {
				this.joueur.leaf_dust += powder;
			}
			else if(boss.type_id == 3) {
				this.joueur.thunder_dust += powder;
			}
			else if(boss.type_id == 4) {
				this.joueur.water_dust += powder;
			}
		}
		//on gère l'empêchement d'or trop abusif
		long to_full_reward = this.joueur.max_energy * 4;
		long energy_used = this.round_count * this.boss.cost_atk;
		int saison_score = 0;
		if(boss.lvl == this.max_battle_level) {
			this.max_battle_level++;
			this.checkBossSucess();
			saison_score = 0;
			this.season_score += (int)((double)boss.pv_max/100);
		}
		else {
			saison_score += (int)((double)boss.pv_max/2000);
		}
		System.out.println("saison "+saison_score);
		//saison_score += 200000;

		if(energy_used < to_full_reward) {
			double reduced = ((double)energy_used / (double)to_full_reward) * ((double)energy_used / (double)to_full_reward);
			mtn = (long)(mtn * reduced);
			xpmtn = (long)(xpmtn * reduced);
			saison_score = (int)((double)saison_score*reduced);
		}
		//
		xpmtn = (long)(xpmtn * (1 + ((double)this.talents[20].lvl*0.5)));
		mtn = (long)(mtn * (1 + ((double)this.talents[21].lvl*0.5)));
		this.joueur.giveExp(xpmtn);
		this.calculatePlayerMaxEnergy();
		this.joueur.gold += mtn;
		this.values.set(0, this.values.get(0) + (mtn));
		this.checkGoldSuccess();
		this.checkLevelSuccess();
		for(int i=0;i<4;i++) {
			double alea = Math.random()*4;
			int alea1 = 1+(int)(alea - (alea%1));
			Rune g = new Rune(5,alea1,boss.lvl-1,boss.id,(200000)+(50000*boss.lvl));
			this.inventaire_runes.add(g);
			if(this.succes.get(33).completed == false) {
				this.succes.get(33).completed = true;
				JOptionPane jop1;
				jop1 = new JOptionPane();
				jop1.showMessageDialog(null, "Vous avez obtenu le haut-fait ["+this.succes.get(33).nom+"].", "Haut Fait Débloqué !", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if(boss.id == 5 && boss.lvl >= 25) {
			if(this.succes.get(53).completed == false) {
				this.succes.get(53).completed = true;
				JOptionPane jop1;
				jop1 = new JOptionPane();
				jop1.showMessageDialog(null, "Vous avez obtenu le haut-fait ["+this.succes.get(53).nom+"].", "Haut Fait Débloqué !", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		//boss = null;
		if(this.current_season != -1) {
			this.season_score += saison_score;
			this.season_rewards.checkRewards(this);
		}
		this.round_count = 0;
		this.eraseCard();
		
		long multexp = 1+(long)((boss.lvl-(boss.lvl%15))/15);
		long exp_won = 0;
		if(boss.lvl >= (this.max_battle_level-5)) { 
			exp_won = 100L * (boss.lvl * boss.lvl) * multexp;
		}
		else if(boss.lvl >= (this.max_battle_level-20)) {
			exp_won = (long)(100L * multexp * ( (((double)this.max_battle_level-(double)boss.lvl)*0.05)*((double)this.max_battle_level-(double)boss.lvl)*0.05) );
		}
		this.giveFightExp(exp_won);
		boss = null;
		this.calculatePlayerMaxEnergy();
		this.updateVisuals();
	}
	
	public void sacrificeMob() {
		super.sacrificeMob();
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
					//on vérifie les succès consécration et rite initiatique
					if(this.succes.get(51).completed == false && (this.joueur.collection.get(i).id_rarity == 1) && (this.joueur.collection.get(i).level == 50)) {
						this.succes.get(51).completed = true;
						JOptionPane jop3;
						jop3 = new JOptionPane();
						jop3.showMessageDialog(null, "Vous avez obtenu le haut-fait ["+this.succes.get(51).nom+"].", "Haut Fait Débloqué !", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(this.succes.get(52).completed == false && (this.joueur.collection.get(i).id_rarity < 4) && (this.joueur.collection.get(i).level == 130)) {
						this.succes.get(52).completed = true;
						JOptionPane jop3;
						jop3 = new JOptionPane();
						jop3.showMessageDialog(null, "Vous avez obtenu le haut-fait ["+this.succes.get(52).nom+"].", "Haut Fait Débloqué !", JOptionPane.INFORMATION_MESSAGE);
					}
					//
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
		dust = (int)(dust * (1 + ((double)this.talents[22].lvl*0.25)));
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
		this.current_card_runed = -1;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void recalculateAllStats() {
		for(int i=0;i<this.joueur.collection.size();i++) {
			this.calculateStatFight(this.joueur.collection.get(i));
		}
	}
	
	public void ascend() {
		long vc = this.getVoidCristals();
		this.void_cristals += vc;
		this.inventaire = new ArrayList<Item>();
		this.current_card_id = -1;
		this.current_card_runed = -1;
		this.current_favorite_id = -1;
		this.current_item_magasin = -1;
		this.current_item_selected = -1;
		this.current_rune_selected = -1;
		this.current_success = -1;
		this.boss = null;
		this.fight_level = 0;
		this.fight_exp = 0;
		this.fight_max_exp = 500;
		this.indice_battle = -1;
		this.indice_fighters = new int[3];
		for(int i=0;i<3;i++) {
			this.indice_fighters[i] = -1;
		}
		this.round_count = 0;
		this.max_battle_level = 10;
		this.quit = false;
		this.inventaire_runes = new ArrayList<Rune>();
		this.roul1 = "X";
		this.roul2 = "X";
		this.roul3 = "X";
		//On regarde si le joueur possède des serviteurs de saison, si c'est le cas, on créer des portails temporels
		for(int j=0;j<this.joueur.collection.size();j++) {
			if(this.joueur.collection.get(j).id_rarity == 6) {
				this.inventaire.add(new TemporalTicket(21, 10000000, "Contient un serviteur de saison de vos précédentes aventures.","Portail temporel : "+this.joueur.collection.get(j).name,(Game)this,this.joueur.collection.get(j).file_path));
			}
		}
		//
		this.joueur = new Player();
		this.eraseCard();
	}
	
	//Méthode de cartes 
	public String getStatsFight(Carte c) {
		String s ="";
		String signe2 = "";
		String signe3 = "";
		String signe4 = "";
		String signe5 = "";
		String signe6 = "";
		int hprune = 0;
		int atkrune = 0;
		int defrune = 0;
		int provrune = 0;
		int agirune = 0;
		int vitrune = 0;
		for(int i=0;i<c.runes.length;i++) {
			if(c.runes[i] != null) {
				hprune += ((UpgradableRune)c.runes[i]).fight_bonus[0];
				atkrune += ((UpgradableRune)c.runes[i]).fight_bonus[1];
				defrune += ((UpgradableRune)c.runes[i]).fight_bonus[2];
				provrune += ((UpgradableRune)c.runes[i]).fight_bonus[3];
				agirune += ((UpgradableRune)c.runes[i]).fight_bonus[4];
				vitrune += ((UpgradableRune)c.runes[i]).fight_bonus[5];
			}
		}
		hprune = (int)((double)hprune * (1+(this.talents[13].lvl*0.02)+(this.talents[19].lvl*0.1)));
		atkrune = (int)((double)atkrune * (1+(this.talents[8].lvl*0.02)+(this.talents[16].lvl*0.1)));
		defrune = (int)((double)defrune * (1+(this.talents[9].lvl*0.02)+(this.talents[16].lvl*0.1)));
		provrune = (int)((double)provrune * (1+(this.talents[10].lvl*0.02)+(this.talents[18].lvl*0.1)));
		agirune = (int)((double)agirune * (1+(this.talents[11].lvl*0.02)+(this.talents[17].lvl*0.1)));
		vitrune = (int)((double)vitrune * (1+(this.talents[12].lvl*0.02)+(this.talents[17].lvl*0.1)));
		if(atkrune < 0) {
			signe2 = "-";
		}
		else {
			signe2 = "+";
		}
		if(defrune < 0) {
			signe3 = "-";
		}
		else {
			signe3 = "+";
		}
		if(provrune < 0) {
			signe4 = "-";
		}
		else {
			signe4 = "+";
		}
		if(agirune < 0) {
			signe5 = "-";
		}
		else {
			signe5 = "+";
		}
		if(vitrune < 0) {
			signe6 = "-";
		}
		else {
			signe6 = "+";
		}
		String color = "";
		//System.out.println((this.current_hp_fight / this.hp_fight));
		double percent = ((double)c.current_hp_fight / (double)c.hp_fight);
		if(percent > 0.4) {
			color = "<font color=\"green\">";
		}
		else if(percent > 0.1) {
			color = "<font color=\"orange\">";
		}
		else {
			color = "<font color=\"red\">";
		}
		s += "<html>HP : "+color+Maths.format(c.current_hp_fight)+" / "+Maths.format(c.hp_fight)+"</font>";
		s += "<br>ATK : "+Maths.format(c.atk_fight)+" ("+signe2+" "+Maths.format(Math.abs(atkrune))+")";
		s += "<br>DEF : "+Maths.format(c.def_fight)+" ("+signe3+" "+Maths.format(Math.abs(defrune))+")";
		s += "<br>PROV : "+Maths.format(c.prov_fight)+" ("+signe4+" "+Maths.format(Math.abs(provrune))+")";
		s += "<br>AGI : "+Maths.format(c.agi_fight)+" ("+signe5+" "+Maths.format(Math.abs(agirune))+")";
		s += "<br>VIT : "+Maths.format(c.vit_fight)+" ("+signe6+" "+Maths.format(Math.abs(vitrune))+")";
		s += "</html>";
		return s;
	}
	
	public String getStats(Carte c) {
		String s ="";
		String signe1 = "";
		String signe2 = "";
		String signe3 = "";
		String signe4 = "";
		String signe5 = "";
		String signe6 = "";
		int hprune = 0;
		int atkrune = 0;
		int defrune = 0;
		int provrune = 0;
		int agirune = 0;
		int vitrune = 0;
		for(int i=0;i<c.runes.length;i++) {
			if(c.runes[i] != null) {
				hprune += ((UpgradableRune)c.runes[i]).fight_bonus[0];
				atkrune += ((UpgradableRune)c.runes[i]).fight_bonus[1];
				defrune += ((UpgradableRune)c.runes[i]).fight_bonus[2];
				provrune += ((UpgradableRune)c.runes[i]).fight_bonus[3];
				agirune += ((UpgradableRune)c.runes[i]).fight_bonus[4];
				vitrune += ((UpgradableRune)c.runes[i]).fight_bonus[5];
			}
		}
		hprune = (int)((double)hprune * (1+(this.talents[13].lvl*0.02)+(this.talents[19].lvl*0.1)));
		atkrune = (int)((double)atkrune * (1+(this.talents[8].lvl*0.02)+(this.talents[16].lvl*0.1)));
		defrune = (int)((double)defrune * (1+(this.talents[9].lvl*0.02)+(this.talents[16].lvl*0.1)));
		provrune = (int)((double)provrune * (1+(this.talents[10].lvl*0.02)+(this.talents[18].lvl*0.1)));
		agirune = (int)((double)agirune * (1+(this.talents[11].lvl*0.02)+(this.talents[17].lvl*0.1)));
		vitrune = (int)((double)vitrune * (1+(this.talents[12].lvl*0.02)+(this.talents[17].lvl*0.1)));
		if(hprune < 0) {
			signe1 = "-";
		}
		else {
			signe1 = "+";
		}
		if(atkrune < 0) {
			signe2 = "-";
		}
		else {
			signe2 = "+";
		}
		if(defrune < 0) {
			signe3 = "-";
		}
		else {
			signe3 = "+";
		}
		if(provrune < 0) {
			signe4 = "-";
		}
		else {
			signe4 = "+";
		}
		if(agirune < 0) {
			signe5 = "-";
		}
		else {
			signe5 = "+";
		}
		if(vitrune < 0) {
			signe6 = "-";
		}
		else {
			signe6 = "+";
		}
		s += "<html><br>--- Statistiques ---";
		s += "<br>HP : "+Maths.format(c.hp_fight)+" ("+signe1+" "+Maths.format(Math.abs(hprune))+")";
		s += "<br>ATK : "+Maths.format(c.atk_fight)+" ("+signe2+" "+Maths.format(Math.abs(atkrune))+")";
		s += "<br>DEF : "+Maths.format(c.def_fight)+" ("+signe3+" "+Maths.format(Math.abs(defrune))+")";
		s += "<br>PROV : "+Maths.format(c.prov_fight)+" ("+signe4+" "+Maths.format(Math.abs(provrune))+")";
		s += "<br>AGI : "+Maths.format(c.agi_fight)+" ("+signe5+" "+Maths.format(Math.abs(agirune))+")";
		s += "<br>VIT : "+Maths.format(c.vit_fight)+" ("+signe6+" "+Maths.format(Math.abs(vitrune))+")";
		s += "</html>";
		return s;
	}
	
	public void calculateStatFight(Carte c) {
		c.hp_fight = (c.hp + (this.talents[5].lvl*80) + (this.talents[5].lvl*2*c.level) + (this.talents[13].lvl*400) + (this.talents[19].lvl*640));
		if(c.dead == false) {
			c.current_hp_fight = (c.hp + (this.talents[5].lvl*80) + (this.talents[5].lvl*2*c.level) + (this.talents[13].lvl*400) + (this.talents[19].lvl*640));
		}
		c.atk_fight = (c.atk + (this.talents[0].lvl*10) + (int)(((double)this.talents[0].lvl/4)*c.level) + (this.talents[8].lvl*50) + (this.talents[16].lvl*80));
		c.def_fight = (c.def + (this.talents[1].lvl*10) + (int)(((double)this.talents[1].lvl/4)*c.level) + (this.talents[9].lvl*50) + (this.talents[16].lvl*80));
		c.prov_fight = (c.prov + (this.talents[2].lvl*10) + (int)(((double)this.talents[2].lvl/4)*c.level) + (this.talents[10].lvl*50) + (this.talents[18].lvl*80));
		c.agi_fight = (c.agi + (this.talents[3].lvl*10) + (int)(((double)this.talents[3].lvl/4)*c.level) + (this.talents[11].lvl*50) + (this.talents[17].lvl*80));
		c.vit_fight = (c.vit + (this.talents[4].lvl*1) + (int)(((double)this.talents[4].lvl/40)*c.level) + (this.talents[12].lvl*5) + (this.talents[17].lvl*8));
		for(int i=0;i<c.runes.length;i++) {
			if(c.runes[i] != null) {
				if(c.dead == false) {
					c.current_hp_fight += ((UpgradableRune)c.runes[i]).fight_bonus[0];
				}
				c.hp_fight += ((UpgradableRune)c.runes[i]).fight_bonus[0];
				c.atk_fight += ((UpgradableRune)c.runes[i]).fight_bonus[1];
				c.def_fight += ((UpgradableRune)c.runes[i]).fight_bonus[2];
				c.prov_fight += ((UpgradableRune)c.runes[i]).fight_bonus[3];
				c.agi_fight += ((UpgradableRune)c.runes[i]).fight_bonus[4];
				c.vit_fight += ((UpgradableRune)c.runes[i]).fight_bonus[5];
			}
		}
		//percentage from ascension
		if(c.dead == false) {
			c.current_hp_fight = (int)((double)c.current_hp_fight * (1+(this.talents[13].lvl*0.02)+(this.talents[19].lvl*0.1)));
		}
		c.hp_fight = (int)((double)c.hp_fight * (1+(this.talents[13].lvl*0.02)+(this.talents[19].lvl*0.1)));
		c.atk_fight = (int)((double)c.atk_fight * (1+(this.talents[8].lvl*0.02)+(this.talents[16].lvl*0.1)));
		c.def_fight = (int)((double)c.def_fight * (1+(this.talents[9].lvl*0.02)+(this.talents[16].lvl*0.1)));
		c.prov_fight = (int)((double)c.prov_fight * (1+(this.talents[10].lvl*0.02)+(this.talents[18].lvl*0.1)));
		c.agi_fight = (int)((double)c.agi_fight * (1+(this.talents[11].lvl*0.02)+(this.talents[17].lvl*0.1)));
		c.vit_fight = (int)((double)c.vit_fight * (1+(this.talents[12].lvl*0.02)+(this.talents[17].lvl*0.1)));
	}
}
