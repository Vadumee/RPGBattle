package Game;

import java.util.ArrayList;

public class Boss extends Mob {
	
	public int[] status;
	public String secret_passive;

	public Boss(int l) {
		super(l);
		this.status = new int[20];
		this.lvl = l;
		if((this.lvl%5) == 1) {
			this.nom = "Parasite";
			this.id = 1;
			this.sound_link = "Sounds/event_battle_1.wav";
			this.type_id = 2;
			this.passive_descr = "Adaptation - Son type change pour prendre celui qui contre le plus ses ennemis, si tous ses adversaires ont un type différent, il prend le type contrant celui qui possède le plus d'attaque.";
			this.secret_passive = "Dévorer - S'il tue une cible, il regagne des pv's équivalant à 100% des dégats infligés à la cible.";
		}
		else if((this.lvl%5) == 2) {
			this.nom = "Cavalier sans tête";
			this.id = 2;
			this.type_id = 3;
			this.sound_link = "Sounds/event_battle_2.wav";
			this.passive_descr = "IL A PERDU LA TETE ! - Lorsque le cavalier sans tête effectue des attaques consécutives sur une même créature, il inflige 50% dégats supplémentaire ignorés par l'armure.";
			this.secret_passive = "Vengeance incarnée - Lorsqu'il subit un coup critique, il renvoie 40% des dégats subis en brut à tous les champions.";
		}
		else if((this.lvl%5) == 3) {
			this.nom = "Darkrai";
			this.id = 3;
			this.type_id = 4;
			this.sound_link = "Sounds/event_battle_3.wav";
			this.passive_descr = "Tenèbres concentrées - Darkrai inflige le double de la différence entre l'attaque et la défense de la cible en dégats bruts.";
			this.secret_passive = "Trou Noir - Les attaques de Darkai ont 5% de chance de mettre la cible à 10% de pv, s'il a moins de 25% de pv, les chances passent à 15%.";
		}
		else if((this.lvl%5) == 4) {
			this.nom = "Sha de la Peur";
			this.id = 4;
			this.type_id = 1;
			this.sound_link = "Sounds/event_battle_4.wav";
			this.passive_descr = "Terreur - Les attaques du sha de la peur font 5% des pv max de la cible en dégats supplémentaire, augmente de 0.2% par % de pv qui manque au sha.";
			this.secret_passive = "Peur matérialisée - Les attaques de vos alliés ont 10% de chance de frapper votre soigneur.";
		}
		else if((this.lvl%5) == 0) {
			this.nom = "Nocturne, éternel cauchemar";
			this.id = 5;
			this.type_id = 4;
			this.sound_link = "Sounds/event_battle_5.wav";
			this.passive_descr = "Eternel cauchemar - Les attaques de nocturnes ont 30% de chance d'appeurer sa cible au prochain tour, l'empêchant d'agir, de plus, toutes les 3 attaques,<br>Nocturne inflige 75% de ses dégats brut à tout le monde.";
			this.secret_passive = "Aucune échappatoire - Les cibles appeurées prennent 200% dégats supplémentaire. A partir du niveau 25 et s'il à moins de 25% de ses pv's,<br>Nocturne gagne 150% de défense et les effets sont doublés.";
		}
		this.image_link = "images/event_boss_"+this.id+".jpg";
		this.pv_max = 200000;
		this.atk = 1000;
		this.defense = 200;
		this.cost_atk = 10;
		//on définit les stats
		int aug_pv = 20000;
		int aug_atk = 110;
		int aug_def = 35;
		for(int i=0;i<this.lvl-1;i++) {
			this.pv_max += aug_pv;
			this.atk += aug_atk;
			this.defense += aug_def;
			aug_pv += 12000;
			aug_atk += 20;
			aug_def += 4;
			this.cost_atk += 2;
		}
		this.pv = this.pv_max;
	}
	
	public void attack(Game g) {
		super.attack(g);
		//on s'occupe d'abords de gérer la provocation
		int prov_total = 0;
		int prov_0 = 0;
		int prov_1 = 0;
		int prov_2 = 0;
		int tank_id = 0;
		if(g.indice_fighters[0] != -1) {
			if(g.joueur.collection.get(g.indice_fighters[0]).dead == false) {
				prov_0 = g.joueur.collection.get(g.indice_fighters[0]).prov_fight;
				prov_total += g.joueur.collection.get(g.indice_fighters[0]).prov_fight;
			}
		}
		if(g.indice_fighters[1] != -1) {
			if(g.joueur.collection.get(g.indice_fighters[1]).dead == false) {
				prov_1 = g.joueur.collection.get(g.indice_fighters[1]).prov_fight;
				prov_total += g.joueur.collection.get(g.indice_fighters[1]).prov_fight;
			}
			if(prov_1 > prov_0) {
				tank_id = 1;
			}
		}
		if(g.indice_fighters[2] != -1) {
			if(g.joueur.collection.get(g.indice_fighters[2]).dead == false) {
				prov_2 = g.joueur.collection.get(g.indice_fighters[2]).prov_fight;
				prov_total += g.joueur.collection.get(g.indice_fighters[2]).prov_fight;
			}
			if(prov_2 > prov_0 && prov_2 > prov_1) {
				tank_id = 2;
			}
		}
		double[] chance_hit = new double[3];
		if(prov_total != 0) {
			chance_hit[0] = ((double)prov_0 / (double)prov_total);
			chance_hit[1] = chance_hit[0]+((double)prov_1 / (double)prov_total);
			chance_hit[2] = chance_hit[1]+((double)prov_2 / (double)prov_total);
		}
		else {
			chance_hit[0] = 0.0;
			chance_hit[1] = 0.0;
			chance_hit[2] = 0.0;
		}
		int target = -1;
		double select_target = Math.random();
		if(select_target <= chance_hit[0]) {
			target = 0;
		}
		else if(select_target <= chance_hit[1]) {
			target = 1;
		}
		else {
			target = 2;
		}
		int type_p = g.joueur.collection.get(g.indice_fighters[target]).type_id;
		
		//on gère le passif d'adaptation du boss 1
		if(g.round_count == 1) {
			if(this.id == 1) {
			//on essaie d'abords de voir s'il y a un type dominant
			int dominant_type = -1;
			for(int d=1;d<5;d++) {
				for(int dd=0;dd<3;dd++) {
					int st = 0;
					if(g.joueur.collection.get(g.indice_fighters[dd]).type_id == d) {
						st++;
					}
					if(st >= 2) {
						dominant_type = d;
					}
				}
			}
			if(dominant_type == -1) {
				int top_attack = 0;
				for(int dd=0;dd<3;dd++) {
					if(g.joueur.collection.get(g.indice_fighters[dd]).atk_fight > top_attack) {
						top_attack = g.joueur.collection.get(g.indice_fighters[dd]).atk_fight;
						dominant_type = g.joueur.collection.get(g.indice_fighters[dd]).type_id;
					}
				}
			}
			//on s'occupe maintenant de changer le type du boss en fonction du type à counter
			if(dominant_type == 1) {
				this.type_id = 4;
				//System.out.println("type séléctionné : eau");
			}
			else if(dominant_type == 2) {
				this.type_id = 1;
				//System.out.println("type séléctionné : feu");
			}
			else if(dominant_type == 3) {
				this.type_id = 2;
				//System.out.println("type séléctionné : plante");
			}
			else {
				this.type_id = 3;
				//System.out.println("type séléctionné : éléctricité");
			}
			}
		}
		//
		
		double change = 1.0;
		if(this.type_id == 1) {
			if(type_p == 4) {
				change = 1.0;
			}
			else if(type_p == 2) {
				change = 2;
			}
		}
		else if(this.type_id == 4) {
			if(type_p == 3) {
				change = 1.0;
			}
			else if(type_p == 1) {
				change = 2;
			}
		}
		else {
			if(type_p == (this.type_id-1)) {
				change = 1.0;
			}
			else if(type_p == (this.type_id+1)) {
				change = 2;
			}
		}
		long diff_energy = g.joueur.max_energy - (this.cost_atk*12);
		double prov_target = (double)g.joueur.collection.get(g.indice_fighters[target]).prov_fight / (double)prov_total;

		//On différencie selon les passifs de boss
		
		if(this.id == 1) {
			////
			int effective_def = g.joueur.collection.get(g.indice_fighters[target]).def_fight;
			if(prov_target > 0.45) {
				effective_def += (int)((double)g.joueur.collection.get(g.indice_fighters[target]).prov_fight*0.2);
			}
			if(g.indice_fighters[target] == g.current_favorite_id) {
				effective_def = (int)((double)effective_def * 1.1);
			}
			
			double effect = 1.0;
			if(diff_energy < 0) {
				effect += (-diff_energy/10) * 0.1;
			}
			else if(diff_energy > 0) {
				effective_def = (int)((double)effective_def * (double)(1+(0.1*diff_energy)));
			}
			long damage_to_player = (long) (((double)this.atk*effect) - effective_def);
			if(damage_to_player < (this.atk / 10)) {
				damage_to_player = (this.atk / 10);
			}
			////
			
			damage_to_player = (int) (damage_to_player * change);
			double chance_dodge = ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight / ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight + 1500));
			boolean dodge = false;
			if(Math.random() <= chance_dodge) {
				dodge = true;
			}
			//System.out.println("boss to "+target+" : "+damage_to_player);
			if(dodge == false) {
				if(g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight - damage_to_player <= 0) {
					g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight = 0;
					g.joueur.collection.get(g.indice_fighters[target]).dead = true;
					long healing = damage_to_player * 1L;
					if(this.pv + healing > this.pv_max) {
						this.pv = this.pv_max;
					}
					else {
						this.pv += healing;
					}
				}
				else {
					g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight -= damage_to_player;
				}
			}
		}
		else if(this.id == 2) {
			if((target+1) == this.status[0]) {
				this.status[1]++;
			}
			else {
				this.status[1] = 0;
				this.status[0] = (target+1);
			}
			
			int effective_def = g.joueur.collection.get(g.indice_fighters[target]).def_fight;
			if(prov_target > 0.45) {
				effective_def += (int)((double)g.joueur.collection.get(g.indice_fighters[target]).prov_fight*0.2);
			}
			if(g.indice_fighters[target] == g.current_favorite_id) {
				effective_def = (int)((double)effective_def * 1.1);
			}
			
			double effect = 1.0;
			if(diff_energy < 0) {
				effect += (-diff_energy/10) * 0.1;
			}
			else if(diff_energy > 0) {
				effective_def = (int)((double)effective_def * (double)(1+(0.1*diff_energy)));
			}
			long damage_to_player = (long) (((double)this.atk*effect) - effective_def);
			if(damage_to_player < (this.atk / 10)) {
				damage_to_player = (this.atk / 10);
			}
			int dmg_supp = 0;
			if(this.status[1] > 0) {
				dmg_supp = (int)(this.atk*(0.5*(this.status[1]-1)));
				damage_to_player += dmg_supp;
			}
			////
			
			damage_to_player = (int) (damage_to_player * change);
			double chance_dodge = ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight / ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight + 1500));
			boolean dodge = false;
			if(Math.random() <= chance_dodge) {
				dodge = true;
			}
			//System.out.println("boss to "+target+" : "+damage_to_player);
			if(dodge == false) {
				if(g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight - damage_to_player <= 0) {
					g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight = 0;
					g.joueur.collection.get(g.indice_fighters[target]).dead = true;
				}
				else {
					g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight -= damage_to_player;
				}
			}
		}
		else if(this.id == 3) {
			int effective_def = g.joueur.collection.get(g.indice_fighters[target]).def_fight;
			if(prov_target > 0.45) {
				effective_def += (int)((double)g.joueur.collection.get(g.indice_fighters[target]).prov_fight*0.2);
			}
			if(g.indice_fighters[target] == g.current_favorite_id) {
				effective_def = (int)((double)effective_def * 1.1);
			}
			
			double effect = 1.0;
			if(diff_energy < 0) {
				effect += (-diff_energy/10) * 0.1;
			}
			else if(diff_energy > 0) {
				effective_def = (int)((double)effective_def * (double)(1+(0.1*diff_energy)));
			}
			long damage_to_player = (long) (((double)this.atk*effect) - effective_def);
			if(damage_to_player < (this.atk / 10)) {
				damage_to_player = (this.atk / 10);
			}
			////
			//dégats supp entre attaque et def
			if(g.joueur.collection.get(g.indice_fighters[target]).atk_fight > g.joueur.collection.get(g.indice_fighters[target]).def_fight) {
				int dmg_supp = (g.joueur.collection.get(g.indice_fighters[target]).atk_fight - g.joueur.collection.get(g.indice_fighters[target]).def_fight)*2;
				damage_to_player += dmg_supp;
			}
			double black_hole = 0.05;
			if(((double)this.pv/(double)this.pv_max) < 0.25) {
				black_hole = 0.15;
			}
			
			damage_to_player = (int) (damage_to_player * change);
			double chance_dodge = ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight / ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight + 1500));
			boolean dodge = false;
			if(Math.random() <= chance_dodge) {
				dodge = true;
			}
			//System.out.println("boss to "+target+" : "+damage_to_player);
			if(dodge == false) {
				if(g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight - damage_to_player <= 0) {
					g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight = 0;
					g.joueur.collection.get(g.indice_fighters[target]).dead = true;
				}
				else {
					g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight -= damage_to_player;
					//on gère le passif secret du trou noir
					if(((double)g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight / (double)g.joueur.collection.get(g.indice_fighters[target]).hp_fight) > 0.1) {
						double rnd = Math.random();
						if(rnd < black_hole) {
							g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight = (int)((double)g.joueur.collection.get(g.indice_fighters[target]).hp_fight * 0.1);
						}
					}
				}
			}
		}
		else if(this.id == 4) {
			int effective_def = g.joueur.collection.get(g.indice_fighters[target]).def_fight;
			if(prov_target > 0.45) {
				effective_def += (int)((double)g.joueur.collection.get(g.indice_fighters[target]).prov_fight*0.2);
			}
			if(g.indice_fighters[target] == g.current_favorite_id) {
				effective_def = (int)((double)effective_def * 1.1);
			}
			
			double effect = 1.0;
			if(diff_energy < 0) {
				effect += (-diff_energy/10) * 0.1;
			}
			else if(diff_energy > 0) {
				effective_def = (int)((double)effective_def * (double)(1+(0.1*diff_energy)));
			}
			long damage_to_player = (long) (((double)this.atk*effect) - effective_def);
			if(damage_to_player < (this.atk / 10)) {
				damage_to_player = (this.atk / 10);
			}
			////
			//passif pourcentage
			double percent_life_dmg = 0.05;
			percent_life_dmg += (( 1-((double)this.pv / (double)this.pv_max) ) / 5);
			int bonus_dmg = (int)((double)g.joueur.collection.get(g.indice_fighters[target]).hp_fight * percent_life_dmg);
			//
			
			damage_to_player = (int) (damage_to_player * change);
			
			damage_to_player += bonus_dmg;
			
			double chance_dodge = ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight / ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight + 1500));
			boolean dodge = false;
			if(Math.random() <= chance_dodge) {
				dodge = true;
			}
			//System.out.println("boss to "+target+" : "+damage_to_player);
			if(dodge == false) {
				if(g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight - damage_to_player <= 0) {
					g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight = 0;
					g.joueur.collection.get(g.indice_fighters[target]).dead = true;
				}
				else {
					g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight -= damage_to_player;
				}
			}
		}
		else if(this.id == 5) {
			int effective_def = g.joueur.collection.get(g.indice_fighters[target]).def_fight;
			if(prov_target > 0.45) {
				effective_def += (int)((double)g.joueur.collection.get(g.indice_fighters[target]).prov_fight*0.2);
			}
			if(g.indice_fighters[target] == g.current_favorite_id) {
				effective_def = (int)((double)effective_def * 1.1);
			}
			
			double effect = 1.0;
			if(diff_energy < 0) {
				effect += (-diff_energy/10) * 0.1;
			}
			else if(diff_energy > 0) {
				effective_def = (int)((double)effective_def * (double)(1+(0.1*diff_energy)));
			}
			long damage_to_player = (long) (((double)this.atk*effect) - effective_def);
			long brut_damage = (long)((double)this.atk*effect);
			if(damage_to_player < (this.atk / 10)) {
				damage_to_player = (this.atk / 10);
			}
			
			damage_to_player = (int) (damage_to_player * change);
			double chance_dodge = ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight / ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight + 1500));
			boolean dodge = false;
			if(Math.random() <= chance_dodge) {
				dodge = true;
			}
			//System.out.println("boss to "+target+" : "+damage_to_player);
			if(dodge == false) {
				double fear = Math.random();
				long damage_aoe = (long)((double)brut_damage*0.75);
				this.status[10]++;
				if(this.status[10] == 3) {
					this.status[10] = 0;
					//dégats d'aoe
					if(this.status[3] == 1) {
						damage_aoe = (long)((double)brut_damage*1.5);
					}
					//System.out.println("damage aoe = "+damage_aoe);
					for(int i=0;i<3;i++) {
						if(g.joueur.collection.get(g.indice_fighters[i]).current_hp_fight - damage_aoe < 0) {
							g.joueur.collection.get(g.indice_fighters[i]).current_hp_fight = 0;
							g.joueur.collection.get(g.indice_fighters[i]).dead = true;
						}
						else {
							g.joueur.collection.get(g.indice_fighters[i]).current_hp_fight -= damage_aoe;
						}
					}
				}
				if(fear < 0.3) {
					this.status[target] = 1;
				}
				if(this.status[target] == 1) {
					double bonus = 3.0;
					if(this.status[3] == 1) {
						bonus = 5.0;
					}
					damage_to_player = (int)((double)damage_to_player*bonus);
					
				}
				

				if(g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight - damage_to_player <= 0) {
					g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight = 0;
					g.joueur.collection.get(g.indice_fighters[target]).dead = true;
				}
				else {
					g.joueur.collection.get(g.indice_fighters[target]).current_hp_fight -= damage_to_player;
				}
			}
		}
		
		
				
	}
	

}
