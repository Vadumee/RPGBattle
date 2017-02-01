package Game;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Carte implements Serializable {

	public String name;
	public String titre;
	public String description;
	public String actual_image;
	public int level;
	public int max_level;
	public long exp;
	public long max_exp;
	
	public String rarity;
	public int rarity_id;
	public String max_rarity;
	public int max_rarity_id;
	public int ameliored;
	public int type_id;
	public String type;
	public boolean dead;
	
	public int total_stat;
	public int base_total_stat;
	public int total_stat_increase;
	public int hp;
	public int atk;
	public int def;
	public int prov;
	public int agi;
	public int vit;
	
	public Rune[] runes;
	
	//stats combats
	public int hp_fight;
	public int current_hp_fight;
	public int atk_fight;
	public int def_fight;
	public int prov_fight;
	public int agi_fight;
	public int vit_fight;
	
	public double percent_hp;
	public double percent_atk;
	public double percent_def;
	public double percent_prov;
	public double percent_agi;
	public double percent_vit;
	
	public int prestige;
	public double bonus_prestige;
	
	public String link_image;
	public int id;
	public int id_rarity;
	public int id_state;
	
	public String file_path;
	
	public Carte(String fichier,int rarity) throws IOException {
		this.file_path = fichier;
		ArrayList<String> list = new ArrayList<String>();
		this.id_rarity = rarity;
		this.runes = new Rune[3];
		for(int run=0;run<3;run++) {
			this.runes[run] = null;
		}
		File file = new File(fichier);
		this.dead = false;
		BufferedReader reader = new BufferedReader(new FileReader(file)); 
		for(int lp = 0;lp < 14;lp++) {
			list.add(reader.readLine()); 
		}
		reader.close();
		this.prestige = 0;
		this.bonus_prestige = 0.0;
		
		//on gère les noms, tout ça
		this.name = list.get(0);
		this.titre = list.get(1);
		this.description = list.get(2);
		
		//on gère ce qui est rareté
		int rar = Integer.parseInt(list.get(3));
		int max_rar = Integer.parseInt(list.get(4));
		this.rarity_id = rar;
		this.level = 1;
		this.max_level = 40 + (rar * 10);
		this.ameliored = 0;
		this.max_rarity_id = max_rar;
		this.total_stat = Integer.parseInt(list.get(5));
		this.base_total_stat = Integer.parseInt(list.get(5));
		this.total_stat_increase = (int)(this.total_stat*0.04);
		
		//On gère le texte de rareté
		if(this.rarity_id == 1) {
			this.rarity = "N";
		}
		else if(this.rarity_id == 2) {
			this.rarity = "R";
		}
		else if(this.rarity_id == 3) {
			this.rarity = "SR";
		}
		else if(this.rarity_id == 4) {
			this.rarity = "HR";
		}
		else if(this.rarity_id == 5) {
			this.rarity = "HR+";
		}
		else if(this.rarity_id == 6) {
			this.rarity = "L";
		}
		else if(this.rarity_id == 7) {
			this.rarity = "SL";
		}
		else if(this.rarity_id == 8) {
			this.rarity = "M";
		}
		else if(this.rarity_id == 9) {
			this.rarity = "SM";
		}
		
		if(this.max_rarity_id == 3) {
			this.max_rarity = "SR";
		}
		else if(this.max_rarity_id == 4) {
			this.max_rarity = "HR";
		}
		else if(this.max_rarity_id == 5) {
			this.max_rarity = "HR+";
		}
		else if(this.max_rarity_id == 6) {
			this.max_rarity = "L";
		}
		else if(this.max_rarity_id == 7) {
			this.max_rarity = "SL";
		}
		else if(this.max_rarity_id == 8) {
			this.max_rarity = "M";
		}
		else if(this.max_rarity_id == 9) {
			this.max_rarity = "SM";
		}
		
		this.id = Integer.parseInt(list.get(12));
		this.type_id = Integer.parseInt(list.get(13));
		if(this.type_id == 1) {
			this.type = "Feu";
		}
		else if(this.type_id == 2) {
			this.type = "Plante";
		}
		else if(this.type_id == 3) {
			this.type = "Electricité";
		}
		else {
			this.type = "Eau";
		}
		
		this.exp = 0;
		this.max_exp = 150 + (50*this.rarity_id);
		
		//on gère les stats
		this.percent_hp = Double.parseDouble(list.get(6));
		this.percent_atk = Double.parseDouble(list.get(7));
		this.percent_def = Double.parseDouble(list.get(8));
		this.percent_prov = Double.parseDouble(list.get(9));
		this.percent_agi = Double.parseDouble(list.get(10));
		this.percent_vit = Double.parseDouble(list.get(11));
		
		this.actual_image = "cards_images/"+this.rarity_id+"/"+this.id+".jpg";
		
		//special RNGesus
		if(this.id == 61) {
			double[] tab = new double[6];
			for(int j=0;j<tab.length;j++) {
				tab[j] = 0.0;
			}
			double reste = 0.7;
			for(int i=0;i<6;i++) {
				if(i != 5) {
					double rnd = 0.0;
					if(reste > 0.35) {
						rnd = Math.random()*0.35;
					}
					else {
						rnd = Math.random()*reste;
					}
					//on essaie de répartir les stats dans un ordre aléatoire, pour pas avoir le pull de hp le plus souvant meilleur !
					double alea = Math.random()*6;
					int indice = (int)(alea-(alea%1));
					while(tab[indice] != 0.0) {
						alea = Math.random()*6;
						indice = (int)(alea-(alea%1));
					}
					tab[indice] = rnd;
					reste -= rnd;
				}
				else {
					double rnd = reste;
					for(int k=0;k<tab.length;k++) {
						if(tab[k] == 0.0) {
							tab[k] = rnd;
						}
					}
				}
			}
			//on applique les stats
			this.percent_hp += tab[0];
			this.percent_atk += tab[1];
			this.percent_def += tab[2];
			this.percent_prov += tab[3];
			this.percent_agi += tab[4];
			this.percent_vit += tab[5];
			//on met une image aléatoire parce que kappa
			int echantillon = new File("cards_images/"+rarity_id+"/").list().length;
			double alea = Math.random()*echantillon;
			int nbid = (int)(alea-(alea%1));
			
			String filename = "";
			File folder = new File("cards_images/"+rarity_id+"/");
			File[] listOfFiles = folder.listFiles();
			
			this.actual_image = "cards_images/"+this.rarity_id+"/"+listOfFiles[nbid].getName();
		}
		
		this.calculateStat();
		this.calculateStatFight();
	}
	
	public void calculateStat() {
		this.hp = 8* (int)(this.percent_hp*this.total_stat);
		this.atk = (int)(this.percent_atk*this.total_stat);
		this.def = (int)(this.percent_def*this.total_stat);
		this.prov = (int)(this.percent_prov*this.total_stat);
		this.agi = (int)(this.percent_agi*this.total_stat);
		this.vit = (int)((160+(10*this.rarity_id))+((this.percent_vit*this.total_stat)/10));
	}
	
	public void prestige() {
		this.prestige++;
		this.bonus_prestige+=0.2;
		if(id_rarity == 1) {
			this.max_level = 50;
			this.rarity_id = 1;
		}
		else if(id_rarity == 2) {
			this.max_level = 60;
			this.rarity_id = 2;
		}
		else if(id_rarity == 3) {
			this.max_level = 70;
			this.rarity_id = 3;
		}
		else if(id_rarity == 4) {
			this.max_level = 80;
			this.rarity_id = 4;
		}
		else if(id_rarity == 5){
			this.max_level = 80;
			this.rarity_id = 4;
		}
		else {
			File file = new File(file_path);
			String max_r = "";
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(file));
				for(int lp = 0;lp < 4;lp++) {
					 max_r = reader.readLine();
				}
				this.rarity_id = Integer.parseInt(max_r);
				this.max_level = 50 + ((rarity_id-1)*10);
				reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		}
		
		this.level = 1;
		this.updateRarity();
		this.exp = 0;
		this.total_stat = (int)((this.base_total_stat * (1+ ((this.rarity_id-1)*0.05) ))*(1+this.bonus_prestige));
		this.total_stat_increase = (int)(this.total_stat*0.04);
		int rar = this.rarity_id;
		this.actual_image = "cards_images/"+rar+"/"+this.id+".jpg";
		if(this.id == 61) {
			int echantillon = new File("cards_images/"+rarity_id+"/").list().length;
			double alea = Math.random()*echantillon;
			int nbid = (int)(alea-(alea%1));
			
			String filename = "";
			File folder = new File("cards_images/"+rarity_id+"/");
			File[] listOfFiles = folder.listFiles();
			
			this.actual_image = "cards_images/"+this.rarity_id+"/"+listOfFiles[nbid].getName();
		}
		this.max_exp = (150 + (50L*this.rarity_id)) * (1+this.incremental(this.prestige));
		//on regarde si c'est toxée
		if(this.id == 54) {
			this.name = "Arthur";
			this.titre = "bon ça suffit maintenant...";
		}
		//
		this.calculateStat();
		this.calculateStatFight();
	}
	
	public void useGrimoire() {
		if(this.max_rarity_id < 9) {
			this.max_rarity_id++;
			this.updateRarity();
		}
	}
	
	public int incremental(int inc) {
		int a = 0;
		for(int i=1;i<=inc;i++) {
			a += i;
		}
		return a;
	}
	
	public void regenerate() {
			int regen = (int) ((0.04*(double)this.hp_fight)+((double)this.prov_fight*0.3));
			if(this.current_hp_fight + regen >= this.hp_fight) {
				this.current_hp_fight = this.hp_fight;
				this.dead = false;
			}
			else {
				this.current_hp_fight += regen;
			}
	}
	
	public void increaseRarity() {
		if((this.rarity_id < this.max_rarity_id) && (this.level == this.max_level)) {
			this.rarity_id++;
			this.updateRarity();
			this.total_stat = (int)((this.base_total_stat * (1+ ((this.rarity_id-1)*0.05) ))*(1+this.bonus_prestige));
			this.total_stat_increase = (int)(this.total_stat * 0.04);
			this.exp = 0;
			this.level = 1;
			this.max_level += 10;
			int rar = this.rarity_id;
			this.actual_image = "cards_images/"+rar+"/"+this.id+".jpg";
			if((this.id == 61) && (this.rarity_id < 9)) {
				int echantillon = new File("cards_images/"+rarity_id+"/").list().length;
				double alea = Math.random()*echantillon;
				int nbid = (int)(alea-(alea%1));
				
				String filename = "";
				File folder = new File("cards_images/"+rarity_id+"/");
				File[] listOfFiles = folder.listFiles();
				
				this.actual_image = "cards_images/"+this.rarity_id+"/"+listOfFiles[nbid].getName();
			}
			this.max_exp = (int)((150 + (50L*this.rarity_id)) * (1+this.incremental(this.prestige)));
			//on regarde si c'est toxée
			if(this.rarity_id >= 6 && this.id == 54) {
				this.name = "Toxée";
				this.titre = "le meurtrier";
			}
			//
			this.calculateStat();
			this.calculateStatFight();
		}
	}
	
	public void giveExp(long i) {
		if(this.level < this.max_level) {
			if((this.exp + i) >= this.max_exp) {
				long reste = i - (this.max_exp - this.exp);
				this.exp = 0;
				this.max_exp += (int)( (25+((this.rarity_id*5L)*this.level))* (1+this.incremental(this.prestige)) );
				this.level++;
				if((this.level%2) == 1) {
					if(this.rarity_id >= 8) {
						this.total_stat_increase += 2;
					}
					else if(this.rarity_id >= 6) {
						if((this.level%4) == 1) {
							this.total_stat_increase += 2;
						}
						else {
							this.total_stat_increase += 1;
						}
					}
					else if(this.rarity_id >= 4) {
						if((this.level%8) == 1) {
							this.total_stat_increase += 2;
						}
						else {
							this.total_stat_increase += 1;
						}
					}
					else {
						this.total_stat_increase++;
					}
				}
				this.total_stat += (int)(this.total_stat_increase*(1+this.bonus_prestige));
				this.calculateStat();
				this.giveExp(reste);
				this.calculateStatFight();
			}
			else {
				this.exp += i;
			}
		}
	}
	
	public void updateRarity() {
		//On gère le texte de rareté
				if(this.rarity_id == 1) {
					this.rarity = "N";
				}
				else if(this.rarity_id == 2) {
					this.rarity = "R";
				}
				else if(this.rarity_id == 3) {
					this.rarity = "SR";
				}
				else if(this.rarity_id == 4) {
					this.rarity = "HR";
				}
				else if(this.rarity_id == 5) {
					this.rarity = "HR+";
				}
				else if(this.rarity_id == 6) {
					this.rarity = "L";
				}
				else if(this.rarity_id == 7) {
					this.rarity = "SL";
				}
				else if(this.rarity_id == 8) {
					this.rarity = "M";
				}
				else if(this.rarity_id == 9) {
					this.rarity = "SM";
				}
				
				if(this.max_rarity_id == 3) {
					this.max_rarity = "SR";
				}
				else if(this.max_rarity_id == 4) {
					this.max_rarity = "HR";
				}
				else if(this.max_rarity_id == 5) {
					this.max_rarity = "HR+";
				}
				else if(this.max_rarity_id == 6) {
					this.max_rarity = "L";
				}
				else if(this.max_rarity_id == 7) {
					this.max_rarity = "SL";
				}
				else if(this.max_rarity_id == 8) {
					this.max_rarity = "M";
				}
				else if(this.max_rarity_id == 9) {
					this.max_rarity = "SM";
				}
	}
	
	public void calculateStatFight() {
		this.hp_fight = this.hp;
		if(this.dead == false) {
			this.current_hp_fight = this.hp;
		}
		this.atk_fight = this.atk;
		this.def_fight = this.def;
		this.prov_fight = this.prov;
		this.agi_fight = this.agi;
		this.vit_fight = this.vit;
		for(int i=0;i<this.runes.length;i++) {
			if(this.runes[i] != null) {
				if(this.dead == false) {
					this.current_hp_fight += this.runes[i].bonus[0];
				}
				this.hp_fight += this.runes[i].bonus[0];
				this.atk_fight += this.runes[i].bonus[1];
				this.def_fight += this.runes[i].bonus[2];
				this.prov_fight += this.runes[i].bonus[3];
				this.agi_fight += this.runes[i].bonus[4];
				this.vit_fight += this.runes[i].bonus[5];
			}
		}
	}
	
	public String toString() {
		String s = "";
		s += "Nom : "+this.name+", "+this.titre+"\n";
		s += "Rareté : "+this.rarity+"\n";
		s += "Rareté Maximale : "+this.max_rarity+"\n";
		s += "Niveau "+this.level+" / "+this.max_level+"\n";
		s += "Type : "+this.type+"\n";
		s += "Exp : "+this.exp+" / "+this.max_exp+"\n";
		s += "|| Statistiques ||\n";
		s += " - HP : "+this.hp_fight+"\n";
		s += " - ATK : "+this.atk_fight+"\n";
		s += " - DEF : "+this.def_fight+"\n";
		s += " - PROV : "+this.prov_fight+"\n";
		s += " - AGI : "+this.agi_fight+"\n";
		s += " - VIT : "+this.vit_fight+"\n";
		return s;
	}
	
	public String getStatsFight() {
		String s ="";
		String signe2 = "";
		String signe3 = "";
		String signe4 = "";
		String signe5 = "";
		String signe6 = "";
		if(this.atk > this.atk_fight) {
			signe2 = "-";
		}
		else {
			signe2 = "+";
		}
		if(this.def > this.def_fight) {
			signe3 = "-";
		}
		else {
			signe3 = "+";
		}
		if(this.prov > this.prov_fight) {
			signe4 = "-";
		}
		else {
			signe4 = "+";
		}
		if(this.agi > this.agi_fight) {
			signe5 = "-";
		}
		else {
			signe5 = "+";
		}
		if(this.vit > this.vit_fight) {
			signe6 = "-";
		}
		else {
			signe6 = "+";
		}
		String color = "";
		//System.out.println((this.current_hp_fight / this.hp_fight));
		double percent = ((double)this.current_hp_fight / (double)this.hp_fight);
		if(percent > 0.4) {
			color = "<font color=\"green\">";
		}
		else if(percent > 0.1) {
			color = "<font color=\"orange\">";
		}
		else {
			color = "<font color=\"red\">";
		}
		s += "<html>HP : "+color+this.current_hp_fight+" / "+this.hp_fight+"</font>";
		s += "<br>ATK : "+this.atk_fight+" ("+signe2+" "+Math.abs(this.atk_fight-this.atk)+")";
		s += "<br>DEF : "+this.def_fight+" ("+signe3+" "+Math.abs(this.def_fight-this.def)+")";
		s += "<br>PROV : "+this.prov_fight+" ("+signe4+" "+Math.abs(this.prov_fight-this.prov)+")";
		s += "<br>AGI : "+this.agi_fight+" ("+signe5+" "+Math.abs(this.agi_fight-this.agi)+")";
		s += "<br>VIT : "+this.vit_fight+" ("+signe6+" "+Math.abs(this.vit_fight-this.vit)+")";
		s += "</html>";
		return s;
	}
	
	public String getStats() {
		String s ="";
		String signe1 = "";
		String signe2 = "";
		String signe3 = "";
		String signe4 = "";
		String signe5 = "";
		String signe6 = "";
		if(this.hp > this.hp_fight) {
			signe1 = "-";
		}
		else {
			signe1 = "+";
		}
		if(this.atk > this.atk_fight) {
			signe2 = "-";
		}
		else {
			signe2 = "+";
		}
		if(this.def > this.def_fight) {
			signe3 = "-";
		}
		else {
			signe3 = "+";
		}
		if(this.prov > this.prov_fight) {
			signe4 = "-";
		}
		else {
			signe4 = "+";
		}
		if(this.agi > this.agi_fight) {
			signe5 = "-";
		}
		else {
			signe5 = "+";
		}
		if(this.vit > this.vit_fight) {
			signe6 = "-";
		}
		else {
			signe6 = "+";
		}
		s += "<html><br>--- Statistiques ---";
		s += "<br>HP : "+this.hp_fight+" ("+signe1+" "+Math.abs(this.hp_fight-this.hp)+")";
		s += "<br>ATK : "+this.atk_fight+" ("+signe2+" "+Math.abs(this.atk_fight-this.atk)+")";
		s += "<br>DEF : "+this.def_fight+" ("+signe3+" "+Math.abs(this.def_fight-this.def)+")";
		s += "<br>PROV : "+this.prov_fight+" ("+signe4+" "+Math.abs(this.prov_fight-this.prov)+")";
		s += "<br>AGI : "+this.agi_fight+" ("+signe5+" "+Math.abs(this.agi_fight-this.agi)+")";
		s += "<br>VIT : "+this.vit_fight+" ("+signe6+" "+Math.abs(this.vit_fight-this.vit)+")";
		s += "</html>";
		return s;
	}
	
	public long getUpgradeCost() {
		long cst = 50000+(25000L*this.prestige);
		for(int i=0;i<this.rarity_id-1;i++) {
			cst = cst * 3;
		}
		return cst;
	}
	
}
