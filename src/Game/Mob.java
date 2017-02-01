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
			this.sound_link = "Sounds/event_battle_2.wav";
			this.passive_descr = "Son insupportable : Les soins sont réduits de moitiés, mais les dégats augentés de 20%.";
		}
		else if((this.lvl%5) == 3) {
			this.nom = "Sanageyama Uzu";
			this.id = 3;
			this.type_id = 3;
			this.sound_link = "Sounds/event_battle_3.wav";
			this.passive_descr = "MEN DOU KOTE ! : Les 3ème attaques de Sanageyama ignorent la moitié de la défense et sont inésquivables.";
		}
		else if((this.lvl%5) == 4) {
			this.nom = "Satsuki";
			this.id = 4;
			this.type_id = 4;
			this.sound_link = "Sounds/event_battle_4.wav";
			this.passive_descr = "Domination : Chaque attaque de Satsuki inflige 40% de ses dégats en brut sur les cibles secondaires.";
		}
		else if((this.lvl%5) == 0) {
			this.nom = "Corrupted Ryuko";
			this.id = 5;
			this.type_id = 1;
			this.sound_link = "Sounds/event_battle_5.wav";
			this.passive_descr = "Surpuissance : Ses dégats augmentent de 1% par tour passé (max 100%), inflige 75% plus de dégats si la personne attaquée et celle ayant le plus de provocation.";
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
		// on fait différentes choses selon le passif du boss
		// défault attack
		
		/*
		 
		 ////
			int effective_def = g.joueur.collection.get(g.indice_fighters[target]).def_fight;
			if(prov_target > 0.45) {
				effective_def += (int)((double)g.joueur.collection.get(g.indice_fighters[target]).prov_fight*0.2);
			}
			if(g.indice_fighters[target] == g.current_favorite_id) {
				effective_def = (int)((double)effective_def * 1.1);
			}
			
			double effect = 1.0;
			if(diff_energy > 0) {
				effect += (diff_energy/10) * 0.1;
			}
			else if(diff_energy < 0) {
				effective_def = (int)((double)effective_def * (double)(1+(0.1*diff_energy)));
			}
			int damage_to_player = (int) (((double)this.atk*effect) - effective_def);
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
			
			/*
		 */
	}
}
