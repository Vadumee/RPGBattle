package Game;

import java.io.Serializable;

public class Rune implements Serializable {

	public String nom;
	public int id;
	public long cost;
	public int prestige;
	public String image_link;
	public int[] bonus;
	public int rarete;

	public Rune(int idrar,int idr, int p, int idb, int cout) {
		//Selon le type de rune, on ne genere pas la meme chose (noms, stats, etc...)
		this.prestige = p;
		this.rarete = idrar;
		this.cost = cout;
		this.bonus = new int[6];
		for(int i=0;i<6;i++) {
			this.bonus[i] = 0;
		}
		int minstat = 0;
		int variation = 0;
		if(idrar == 1) {
			minstat = 35;
			variation = 9;
		}
		else if(idrar == 2) {
			minstat = 56;
			variation = 19;
		}
		else if(idrar == 3) {
			minstat = 94;
			variation = 27;
		}
		else if(idrar == 4) {
			minstat = 133;
			variation = 20;
		}
		else {
			double modif = 0.0;
			if(p <= 20) {
				modif = (0.04*prestige);
			}
			else {
				int reste = prestige -20;
				int add = 1+(int)(reste - (reste%5))/5;
				modif = 0.8 + (reste * (0.04+(0.012*add)));
			}
			minstat = (int)(225 * (1+modif));
			variation = (int)(30 * (1+modif));
		}
		if(idr == 1) {
			this.generateOneStatRune(idrar, minstat, variation, idb);
		}
		else if(idr == 2) {
			this.generateTwoStatRune(idrar, minstat, variation, idb);
		}
		else if(idr == 3) {
			this.generateThreeStatRune(idrar, minstat, variation, idb);
		}
		else {
			this.generateSacrificialRune(idrar, minstat, variation, idb);
		}
	}

	public void generateOneStatRune(int idrar, int ms, int v, int idb) {
		//On gere d'abord la stat choisie
		double stat_choice = Math.random()*6;
		int choice = (int)(stat_choice - (stat_choice % 1));
		//on gere maintenant la valeur
		double var = Math.random()*v;
		int value = ms + (int)(var - (var%1));
		if(choice == 0) {
			value = value * 8;
		}
		else if(choice == 5) {
			value = 1+(int)(value / 10);
		}
		this.bonus[choice] = value;
		//System.out.println(choice);
		

		//Selon la stat qui est choisie, et le tier, le nom n'est pas le même
		String name = "";
		String n1 = "";
		String n2 = "";
		if(idrar == 5) {
			this.image_link = "images/gem5.jpg";
			if(idb == 1) {
				name = "Griffe répugnante ";
			}
			else if(idb == 2) {
				name = "Anneau du cavalier ";
			}
			else if(idb == 3) {
				name = "Voile fantômatique ";
			}
			else if(idb == 4) {
				name = "Terreur en bouteille ";
			}
			else if(idb == 5) {
				name = "Linceuil noir ";
			}
			//on gere maintenant le suffixe
			if(choice == 0) {
				name += "de vigueur";
			}
			else if(choice == 1) {
				name += "de force";
			}
			else if(choice == 2) {
				name += "de robustesse";
			}
			else if(choice == 3) {
				name += "d'intimidation";
			}
			else if(choice == 4) {
				name += "de finesse";
			}
			else if(choice == 5) {
				name += "de vivacité";
			}
			this.nom = name;
		}
		else {
			this.image_link = "images/gem1_"+idrar+"_"+choice+".jpg";
			n1 = "Joyau ";
			if(idrar == 1) {
				n2 = "fissuré";
			}
			else if(idrar == 2) {
				n2 = "de qualité";
			}
			else if(idrar == 3) {
				n2 = "fabuleux";
			}
			else if(idrar == 4) {
				n2 = "légendaire";
			}
			String n3 = "";
			if(choice == 0) {
				n3 = " des sources";
			}
			else if(choice == 1) {
				n3 = " ardent";
			}
			else if(choice == 2) {
				n3 = " d'obsidienne";
			}
			else if(choice == 3) {
				n3 = " fulminant";
			}
			else if(choice == 4) {
				n3 = " de foudre";
			}
			else if(choice == 5) {
				n3 = " des vents";
			}
			this.nom = n1+n2+n3;
		}
	}

	public void generateTwoStatRune(int idrar, int ms, int v, int idb) {
		//On gere d'abord la stat choisie
		double stat_choice = Math.random()*6;
		int choice = (int)(stat_choice - (stat_choice % 1));
		//on gere la stat deux
		double stat_deux = Math.random()*6;
		int deux_choice = (int)(stat_deux - (stat_deux%1));
		while(deux_choice == choice) {
			stat_deux = Math.random()*6;
			deux_choice = (int)(stat_deux - (stat_deux%1));
		}
		//on gere maintenant les valeurs
		double var = Math.random()*v;
		int value = ms + (int)(var - (var%1));
		double var2 = Math.random()*v;
		int value2 = ms + (int)(var2 - (var2%1));
		if(choice == 0) {
			value = value * 8;
		}
		else if(choice == 5) {
			value = (int)(value / 10);
		}
		if(deux_choice == 0) {
			value2 = value2 * 8;
		}
		else if(deux_choice == 5) {
			value2 = 1+(int)(value2 / 10);
		}
		this.bonus[choice] = (int)(value*0.5);
		this.bonus[deux_choice] = (int)(value2*0.5);
		
		this.image_link = "images/gem_blank.jpg";
		
		//System.out.println(choice+"  "+deux_choice);

		//Selon la stat qui est choisie, et le tier, le nom n'est pas le même
		String name = "";
		String n1 = "";
		String n2 = "";
		if(idrar == 5) {
			this.image_link = "images/gem5.jpg";
			if(idb == 1) {
				name = "Spore virulante ";
			}
			else if(idb == 2) {
				name = "Souvenirs de la croisade ";
			}
			else if(idb == 3) {
				name = "Bulle d'oure-monde ";
			}
			else if(idb == 4) {
				name = "Crocs de sha ";
			}
			else if(idb == 5) {
				name = "Essance de mauvais rêve ";
			}
			//on gere maintenant le suffixe
			if(choice == 0) {
				name += "de vigueur";
			}
			else if(choice == 1) {
				name += "de force";
			}
			else if(choice == 2) {
				name += "de robustesse";
			}
			else if(choice == 3) {
				name += "d'intimidation";
			}
			else if(choice == 4) {
				name += "de finesse";
			}
			else if(choice == 5) {
				name += "de vivacité";
			}
			this.nom = name;
		}
		else {
			
			int indice = 0;
			n1 = "Mot de ";
			if(idrar == 1) {
				n2 = "renforcement :";
			}
			else if(idrar == 2) {
				n2 = "pouvoir :";
			}
			else if(idrar == 3) {
				n2 = "puissance :";
			}
			else if(idrar == 4) {
				n2 = "surpuissance :";
			}
			String n3 = "";
			if((choice == 0 && deux_choice == 1) || (choice == 1 && deux_choice == 0)) {
				n3 = " Infatigable soldat";
				indice = 1;
			}
			else if((choice == 0 && deux_choice == 2) || (choice == 2 && deux_choice == 0)) {
				n3 = " Colosse";
				indice = 2;
			}
			else if((choice == 0 && deux_choice == 3) || (choice == 3 && deux_choice == 0)) {
				n3 = " Mastodonte";
				indice = 3;
			}
			else if((choice == 0 && deux_choice == 4) || (choice == 4 && deux_choice == 0)) {
				n3 = " Chasseur";
				indice = 4;
			}
			else if((choice == 0 && deux_choice == 5) || (choice == 5 && deux_choice == 0)) {
				n3 = " Zéphir";
				indice = 5;
			}
			else if((choice == 1 && deux_choice == 2) || (choice == 2 && deux_choice == 1)) {
				n3 = " Templier";
				indice = 6;
			}
			else if((choice == 1 && deux_choice == 3) || (choice == 3 && deux_choice == 1)) {
				n3 = " Barbare";
				indice = 7;
			}
			else if((choice == 1 && deux_choice == 4) || (choice == 4 && deux_choice == 1)) {
				n3 = " Traqueur";
				indice = 8;
			}
			else if((choice == 1 && deux_choice == 5) || (choice == 5 && deux_choice == 1)) {
				n3 = " Berzerker";
				indice = 9;
			}
			else if((choice == 2 && deux_choice == 3) || (choice == 3 && deux_choice == 2)) {
				n3 = " Protecteur";
				indice = 10;
			}
			else if((choice == 2 && deux_choice == 4) || (choice == 4 && deux_choice == 2)) {
				n3 = " Dueliste";
				indice = 11;
			}
			else if((choice == 2 && deux_choice == 5) || (choice == 5 && deux_choice == 2)) {
				n3 = " Tacticien";
				indice = 12;
			}
			else if((choice == 3 && deux_choice == 4) || (choice == 4 && deux_choice == 3)) {
				n3 = " Crapule";
				indice = 13;
			}
			else if((choice == 3 && deux_choice == 5) || (choice == 5 && deux_choice == 3)) {
				n3 = " Harceleur";
				indice = 14;
			}
			else if((choice == 4 && deux_choice == 5) || (choice == 5 && deux_choice == 4)) {
				n3 = " Assassin";
				indice = 15;
			}
			this.image_link = "images/gem2_"+idrar+"_"+indice+".jpg";
			this.nom = n1+n2+n3;
		}

	}

	public void generateThreeStatRune(int idrar, int ms, int v, int idb) {
		//On gere d'abord la stat choisie
		double stat_choice = Math.random()*6;
		int choice = (int)(stat_choice - (stat_choice % 1));
		//on gere la stat deux
		double stat_deux = Math.random()*6;
		int deux_choice = (int)(stat_deux - (stat_deux%1));
		while(deux_choice == choice) {
			stat_deux = Math.random()*6;
			deux_choice = (int)(stat_deux - (stat_deux%1));
		}
		//on gere la stat trois
		double stat_trois = Math.random()*6;
		int trois_choice = (int)(stat_trois - (stat_trois%1));
		while((trois_choice == choice) || (trois_choice == deux_choice)) {
			stat_trois = Math.random()*6;
			trois_choice = (int)(stat_trois - (stat_trois%1));
		}
		//on gere maintenant les valeurs
		double var = Math.random()*v;
		int value = ms + (int)(var - (var%1));
		double var2 = Math.random()*v;
		int value2 = ms + (int)(var2 - (var2%1));
		double var3 = Math.random()*v;
		int value3 = ms + (int)(var3 - (var3%1));
		if(choice == 0) {
			value = value * 8;
		}
		else if(choice == 5) {
			value = (int)(value / 10);
		}
		if(deux_choice == 0) {
			value2 = value2 * 8;
		}
		else if(deux_choice == 5) {
			value2 = (int)(value2 / 10);
		}
		if(trois_choice == 0) {
			value3 = value3 * 8;
		}
		else if(trois_choice == 5) {
			value3 = 1+(int)(value3 / 10);
		}
		this.bonus[choice] = (int)(value*0.33);
		this.bonus[deux_choice] = (int)(value2*0.33);
		this.bonus[trois_choice] = (int)(value3*0.33);
		
		this.image_link = "images/gem_blank.jpg";
		
		//System.out.println(choice+"  "+deux_choice+"  "+trois_choice);

		//Selon la stat qui est choisie, et le tier, le nom n'est pas le même
		String name = "";
		String n1 = "";
		String n2 = "";
		if(idrar == 5) {
			this.image_link = "images/gem5.jpg";
			if(idb == 1) {
				name = "Plaque de soldat détériorée";
			}
			else if(idb == 2) {
				name = "Epée maudite du cavalier sans tête";
			}
			else if(idb == 3) {
				name = "Coeur de trou noir";
			}
			else if(idb == 4) {
				name = "Epée de l'orgeuil déchu";
			}
			else if(idb == 5) {
				name = "Lames du crépuscule";
			}
			this.nom = name;
		}
		else {
			this.image_link = "images/gem3_"+idrar+"_"+(choice+1)+".jpg";
			n1 = "Rune chromatique ";
			if(idrar == 1) {
				n2 = "fissurée";
			}
			else if(idrar == 2) {
				n2 = "de qualité";
			}
			else if(idrar == 3) {
				n2 = "fabuleuse";
			}
			else if(idrar == 4) {
				n2 = "légendaire";
			}
			String n3 = "";
			if(choice == 0) {
				n3 = " symbiotique";
			}
			else if(choice == 1) {
				n3 = " de surpuissance";
			}
			else if(choice == 2) {
				n3 = " de durcissement";
			}
			else if(choice == 3) {
				n3 = " de clonage";
			}
			else if(choice == 4) {
				n3 = " de déphasement";
			}
			else if(choice == 5) {
				n3 = " de téléportation";
			}
			this.nom = n1+n2+n3;
		}
	}

	public void generateSacrificialRune(int idrar, int ms, int v, int idb) {
		//On gere d'abord la stat choisie
		double stat_choice = Math.random()*6;
		int choice = (int)(stat_choice - (stat_choice % 1));
		//on gere la stat malus
		double stat_malus = Math.random()*6;
		int malus_choice = (int)(stat_malus - (stat_malus%1));
		while(malus_choice == choice) {
			stat_malus = Math.random()*6;
			malus_choice = (int)(stat_malus - (stat_malus%1));
		}
		//on gere maintenant les valeurs
		double var = Math.random()*v;
		int value = ms + (int)(var - (var%1));
		double var2 = Math.random()*v;
		int value2 = ms + (int)(var2 - (var2%1));
		if(choice == 0) {
			value = value * 8;
		}
		else if(choice == 5) {
			value = 1+(int)(value / 10);
		}
		if(malus_choice == 0) {
			value2 = value2 * 8;
		}
		else if(malus_choice == 5) {
			value2 = 1+(int)(value2 / 10);
		}
		this.bonus[choice] = (int)(value*1.5);
		this.bonus[malus_choice] = (int)(value2*(-0.75));
		
		//System.out.println(choice+"  "+malus_choice);

		//Selon la stat qui est choisie, et le tier, le nom n'est pas le même
		String name = "";
		String n1 = "";
		String n2 = "";
		if(idrar == 5) {
			this.image_link = "images/gem5.jpg";
			if(idb == 1) {
				name = "Neurotoxines instables ";
			}
			else if(idb == 2) {
				name = "Coeur gangréné ";
			}
			else if(idb == 3) {
				name = "Tome de sortilèges cauchemardesques ";
			}
			else if(idb == 4) {
				name = "Don d'Y'Shaarj ";
			}
			else if(idb == 5) {
				name = "Cuirasse de paranoïa ";
			}
			//on gere maintenant le suffixe
			if(choice == 0) {
				name += "de vigueur";
			}
			else if(choice == 1) {
				name += "de force";
			}
			else if(choice == 2) {
				name += "de robustesse";
			}
			else if(choice == 3) {
				name += "d'intimidation";
			}
			else if(choice == 4) {
				name += "de finesse";
			}
			else if(choice == 5) {
				name += "de vivacité";
			}
			this.nom = name;
		}
		else {
			this.image_link = "images/gem4_"+idrar+"_"+choice+".jpg";
			n1 = "Rune de sacrifice ";
			if(idrar == 1) {
				n2 = "mineure";
			}
			else if(idrar == 2) {
				n2 = "de qualité";
			}
			else if(idrar == 3) {
				n2 = "fabuleuse";
			}
			else if(idrar == 4) {
				n2 = "légendaire";
			}
			String n3 = "";
			if(choice == 0) {
				n3 = " du titan";
			}
			else if(choice == 1) {
				n3 = " du dragon";
			}
			else if(choice == 2) {
				n3 = " du golem";
			}
			else if(choice == 3) {
				n3 = " du démon";
			}
			else if(choice == 4) {
				n3 = " de la gorgone";
			}
			else if(choice == 5) {
				n3 = " du mantide";
			}
			this.nom = n1+n2+n3;
		}
	}

	public String getNom() {
		String s = "";
		if(rarete == 5) {
			s += "<html><font color=\"purple\">";
		}
		else if(rarete == 4) {
			s += "<html><font color=\"purple\">";
		}
		else if(rarete == 3) {
			s += "<html><font color=\"blue\">";
		}
		else if(rarete == 2) {
			s += "<html><font color=\"green\">";
		}
		else {
			s += "<html><font color=\"black\">";
		}
		s+=this.nom;
		if(this.prestige > 0 ){
			s+= " (ilvl "+(this.prestige*2)+")";
		}
		s+="</font></html>";
		return s;
	}

	public String getDescr() {
		String s = "<html>";
		for(int i=0;i<bonus.length;i++) {
			if(bonus[i] != 0) {
				if(bonus[i] > 0) {
					s+= "<font color=\"green\">+ "+bonus[i];
				}
				else {
					s+= "<font color=\"red\">- "+Math.abs(bonus[i]);
				}
				if(i == 0) {
					s+= " HP";
				}
				else if(i == 1) {
					s+= " ATK";
				}
				else if(i == 2) {
					s+= " DEF";
				}
				else if(i == 3) {
					s+= " PROV";
				}
				else if(i == 4) {
					s+= " AGI";
				}
				else {
					s+= " VIT";
				}
				s+="</font><br>";
			}
		}
		s+="</html>";
		return s;
	}

}
