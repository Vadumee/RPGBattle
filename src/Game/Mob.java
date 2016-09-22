package Game;

import java.io.Serializable;

public class Mob implements Serializable {

	public String nom;
	public int id;
	public long pv_max;
	public long pv;
	public int atk;
	public int defense;
	public int lvl;
	public String passive_descr;
	public String image_link;
	public String sound_link;
	public int type_id;
	public int cost_atk;
	
	public Mob(int l) {
		this.lvl = l;
		if((this.lvl%5) == 1) {
			this.nom = "Gamagori Ira";
			this.id = 1;
			this.sound_link = "Sounds/event_battle_1.wav";
			this.type_id = 2;
			this.passive_descr = "Douleur partagée : Chaque attaque renvoie la moitié de sa défense en dégats à l'aggresseur.";
		}
		else if((this.lvl%5) == 2) {
			this.nom = "Nonon Jakusure";
			this.id = 2;
			this.type_id = 3;
			this.sound_link = "Sounds/event_battle_1.wav";
			this.passive_descr = "Son insupportable : Les soins sont réduits de moitiés, mais les dégats augentés de 20%.";
		}
		else if((this.lvl%5) == 3) {
			this.nom = "Sanageyama Uzu";
			this.id = 3;
			this.type_id = 3;
			this.sound_link = "Sounds/event_battle_1.wav";
			this.passive_descr = "MEN DOU KOTE ! : Les 3ème attaques de Sanageyama ignorent la moitié de la défense et sont inésquivables.";
		}
		else if((this.lvl%5) == 4) {
			this.nom = "Satsuki";
			this.id = 4;
			this.type_id = 4;
			this.sound_link = "Sounds/event_battle_2.wav";
			this.passive_descr = "Domination : Chaque attaque de Satsuki inflige 40% de ses dégats en brut sur les cibles secondaires.";
		}
		else if((this.lvl%5) == 0) {
			this.nom = "Corrupted Ryuko";
			this.id = 5;
			this.type_id = 1;
			this.sound_link = "Sounds/event_battle_3.wav";
			this.passive_descr = "Surpuissance : Ses dégats augmentent de 2% par tour passé, inflige 50% plus de dégats si la personne attaquée et celle ayant le plus de provocation.";
		}
		this.image_link = "images/event_boss_"+this.id+".jpg";
		this.pv_max = 200000;
		this.atk = 1000;
		this.defense = 200;
		this.cost_atk = 10;
		//on définit les stats
		int aug_pv = 20000;
		int aug_atk = 130;
		int aug_def = 35;
		for(int i=0;i<this.lvl-1;i++) {
			this.pv_max += aug_pv;
			this.atk += aug_atk;
			this.defense += aug_def;
			aug_pv += 12000;
			aug_atk += 25;
			aug_def += 4;
			this.cost_atk += 2;
		}
		this.pv = this.pv_max;
	} 
	
	public void attack(Game g) {
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
		// on fait différentes choses selon le passif du boss
		if(this.id == 1 || this.id == 2) {
			int damage_to_player = (int) (this.atk * ((double)this.atk / ((double)g.joueur.collection.get(g.indice_fighters[target]).def_fight + (double)this.atk)));
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
			int damage_to_player = (int) (this.atk * ((double)this.atk / ((double)g.joueur.collection.get(g.indice_fighters[target]).def_fight + (double)this.atk)));
			damage_to_player = (int) (damage_to_player * change);
			double chance_dodge = ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight / ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight + 1500));
			boolean dodge = false;
			if(Math.random() <= chance_dodge) {
				dodge = true;
			}
			if((g.round_count%3) == 0) {
				dodge = false;
				damage_to_player = (int) (this.atk * ((double)this.atk / ((double)(g.joueur.collection.get(g.indice_fighters[target]).def_fight/2) + (double)this.atk)));
				damage_to_player = (int) (damage_to_player * change);
			}
			
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
		else if(this.id == 4) {
			int damage_to_player = (int) (this.atk * ((double)this.atk / ((double)g.joueur.collection.get(g.indice_fighters[target]).def_fight + (double)this.atk)));
			damage_to_player = (int) (damage_to_player * change);
			double chance_dodge = ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight / ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight + 1500));
			boolean dodge = false;
			if(Math.random() <= chance_dodge) {
				dodge = true;
			}
			
			if(dodge == false) {
				for(int i=0;i<3;i++) {
					if((g.indice_fighters[i] != -1) && (i!=target)) {
						int dmg_wave = (int)(0.4*(double)this.atk);
						if(g.joueur.collection.get(g.indice_fighters[i]).dead == false) {
							if(g.joueur.collection.get(g.indice_fighters[i]).current_hp_fight - dmg_wave <= 0) {
								g.joueur.collection.get(g.indice_fighters[i]).current_hp_fight = 0;
								g.joueur.collection.get(g.indice_fighters[i]).dead = true;
							}
							else {
								g.joueur.collection.get(g.indice_fighters[i]).current_hp_fight -= dmg_wave;
							}
						}
					}
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
		else if(this.id == 5) {
			change += (0.02*((double)g.round_count));
			if(change > 2) {
				change = 2;
			}
			int damage_to_player = (int) (this.atk * ((double)this.atk / ((double)g.joueur.collection.get(g.indice_fighters[target]).def_fight + (double)this.atk)));
			damage_to_player = (int) (damage_to_player * change);
			if(target == tank_id) {
				damage_to_player = (int)(damage_to_player * 1.5);
			}
			double chance_dodge = ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight / ((double)g.joueur.collection.get(g.indice_fighters[target]).vit_fight + 1500));
			boolean dodge = false;
			if(Math.random() <= chance_dodge) {
				dodge = true;
			}
			
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
	}
}
