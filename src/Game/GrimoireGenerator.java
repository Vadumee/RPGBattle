package Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GrimoireGenerator extends Item {

	public int quality;
	
	public GrimoireGenerator(int i, int cst, String d, String n, Game g, int qual) {
		super(i, cst, d, n, g);
		this.quality = qual;
	}
	
	public void utiliser() {
		super.utiliser();
		if(quality == 1) {
			this.generateNormalGrimoire();
		}
		else if(quality == 2) {
			this.generateUncommonGrimoire();
		}
		else if(quality == 3) {
			this.generateRareGrimoire();
		}
		else if(quality == 4) {
			this.generateEpicGrimoire();
		}
		this.quantity -= 1;
		this.game.eraseUsedItems();
	}
	
	public void generateNormalGrimoire() {
		double chance = Math.random()*100;
		int rar = 0;
		if(chance <= 10) {
			rar = 9;
		}
		else if(chance > 10 && chance <= 20) {
			rar = 8;
		}
		else if(chance > 20 && chance <= 35) {
			rar = 7;
		}
		else if(chance > 35 && chance <= 55) {
			rar = 6;
		}
		else if(chance > 55 && chance <= 75) {
			rar = 5;
		}
		else {
			rar = 4;
		}
		int nb = new File("cards/"+1+"/").list().length;
		double idp = Math.random()*nb;
		int idper = 1+(int)(idp - (idp%1));
		
		Grimoire g = new Grimoire(5,1,"Permet d'améliorer la rareté maximale d'un serviteur","Grimoire",this.game,idper,rar,1);
		game.ajouterObjet(g);
	}
	
	public void generateUncommonGrimoire() {
		double chance = Math.random()*100;
		int rar = 0;
		if(chance <= 15) {
			rar = 9;
		}
		else if(chance > 15 && chance <= 35) {
			rar = 8;
		}
		else if(chance > 35 && chance <= 55) {
			rar = 7;
		}
		else if(chance > 55 && chance <= 75) {
			rar = 6;
		}
		else {
			rar = 5;
		}
		int nb = new File("cards/"+2+"/").list().length;
		double idp = Math.random()*nb;
		int idper = 1+(int)(idp - (idp%1));
		
		Grimoire g = new Grimoire(5,1,"Permet d'améliorer la rareté maximale d'un serviteur","Grimoire",this.game,idper,rar,2);
		game.ajouterObjet(g);
	}
	
	public void generateRareGrimoire() {
		double chance = Math.random()*100;
		int rar = 0;
		if(chance <= 20) {
			rar = 9;
		}
		else if(chance > 20 && chance <= 45) {
			rar = 8;
		}
		else if(chance > 45 && chance <= 70) {
			rar = 7;
		}
		else {
			rar = 6;
		}
		int nb = new File("cards/"+3+"/").list().length;
		double idp = Math.random()*nb;
		int idper = 1+(int)(idp - (idp%1));
		
		Grimoire g = new Grimoire(5,1,"Permet d'améliorer la rareté maximale d'un serviteur","Grimoire",this.game,idper,rar,3);
		game.ajouterObjet(g);
	}
	
	public void generateEpicGrimoire() {
		double chance = Math.random()*100;
		int rar = 0;
		if(chance <= 50) {
			rar = 9;
		}
		else {
			rar = 8;
		}
		int nb = new File("cards/"+4+"/").list().length;
		double idp = Math.random()*nb;
		int idper = 1+(int)(idp - (idp%1));
		
		Grimoire g = new Grimoire(5,1,"Permet d'améliorer la rareté maximale d'un serviteur","Grimoire",this.game,idper,rar,4);
		game.ajouterObjet(g);
	}

}
