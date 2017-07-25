package Game;

import java.io.Serializable;
import java.util.ArrayList;

public class SeasonRewards implements Serializable {
	
	public int indice_recompense;
	public ArrayList<Integer> points_needed;
	public ArrayList<Integer> id_recompense;
	public ArrayList<String> descr_recompense;
	
	public SeasonRewards(int season) {
		this.points_needed = new ArrayList<Integer>();
		this.id_recompense = new ArrayList<Integer>();
		this.descr_recompense = new ArrayList<String>();
		this.indice_recompense = 0;
		//on créer les palliers pour les persos à débloquer en saison
		int char_1 = (int)(25000+((season-1)*5000));
		int char_2 = (int)(50000+((season-1)*10000));
		int char_3 = (int)(100000+((season-1)*20000));
		int char_4 = (int)(200000+((season-1)*40000));
		for(int i=1;i<20;i++) {
			this.points_needed.add((int)((char_1/20)*i));
			if(i%3 == 1) {
				this.id_recompense.add(1);
				this.descr_recompense.add("Or");
			}
			else if(i%3 == 2) {
				this.id_recompense.add(2);
				this.descr_recompense.add("Portails");
			}
			else {
				this.id_recompense.add(3);
				this.descr_recompense.add("Grimoires");
			}
		}
		this.points_needed.add(char_1);
		this.descr_recompense.add("Parasite (L)");
		this.id_recompense.add(4);
		for(int j=1;j<10;j++) {
			this.points_needed.add((int)(25000+((char_2/20)*j)));
			if(j%3 == 1) {
				this.id_recompense.add(1);
				this.descr_recompense.add("Or");
			}
			else if(j%3 == 2) {
				this.id_recompense.add(2);
				this.descr_recompense.add("Portails");
			}
			else {
				this.id_recompense.add(3);
				this.descr_recompense.add("Grimoires");
			}
		}
		this.points_needed.add(char_2);
		this.descr_recompense.add("Cavalier sans tête (L)");
		this.id_recompense.add(4);
		for(int k=1;k<10;k++) {
			this.points_needed.add((int)(50000+((char_3/20)*k)));
			if(k%3 == 1) {
				this.id_recompense.add(1);
				this.descr_recompense.add("Or");
			}
			else if(k%3 == 2) {
				this.id_recompense.add(2);
				this.descr_recompense.add("Portails");
			}
			else {
				this.id_recompense.add(3);
				this.descr_recompense.add("Grimoires");
			}
		}
		this.points_needed.add(char_3);
		this.descr_recompense.add("Darkrai (SL)");
		this.id_recompense.add(4);
		for(int l=1;l<10;l++) {
			this.points_needed.add((int)(100000+((char_4/20)*l)));
			if(l%3 == 1) {
				this.id_recompense.add(1);
				this.descr_recompense.add("Or");
			}
			else if(l%3 == 2) {
				this.id_recompense.add(2);
				this.descr_recompense.add("Portails");
			}
			else {
				this.id_recompense.add(3);
				this.descr_recompense.add("Grimoires");
			}
		}
		this.points_needed.add(char_4);
		this.descr_recompense.add("Sha de la Peur (SL)");
		this.id_recompense.add(4);
		this.points_needed.add(char_4/10);
		this.id_recompense.add(1);
		this.descr_recompense.add("Or");
	}
	
	public void checkRewards(Game g) {
		//on vérifie que le score nécessaire est atteint
		while(g.season_score >= this.points_needed.get(this.indice_recompense)) {
			if(this.id_recompense.get(this.indice_recompense) == 1) {
				if(this.indice_recompense < 20) {
					g.joueur.gold += ((500000L+(g.current_season*100000L)))*(1+( (double)((GameV2)g).talents[21].lvl*0.5 ));
				}
				else if(this.indice_recompense < 30) {
					g.joueur.gold += ((1500000L+(g.current_season*500000L)))*(1+( (double)((GameV2)g).talents[21].lvl*0.5 ));
				}
				else if(this.indice_recompense < 40) {
					g.joueur.gold += ((5000000L+(g.current_season*2000000L)))*(1+( (double)((GameV2)g).talents[21].lvl*0.5 ));
				}
				else if(this.indice_recompense >= 40) {
					g.joueur.gold += ((10000000L+(g.current_season*4000000L)))*(1+( (double)((GameV2)g).talents[21].lvl*0.5 ));
				}
			}
			else if(this.id_recompense.get(this.indice_recompense) == 2) {
				if(this.indice_recompense < 20) {
					for(int i=0;i<20;i++) {
						Ticket t = new Ticket(1,10000,"Un portail basique de la roue de la RNG, donnant des serviteurs communs ou supérieurs.","Portail de créature commun",g);
						g.ajouterObjet(t);
						if(i%2 == 0) {
							Ticket t2 = new Ticket(2,75000,"Un portail permettant d'obtenir des serviteurs rares ou supérieurs à la roue de la RNG.","Portail de créature rare",g);
							g.ajouterObjet(t2);
						}
						if(i%10 == 0) {
							Ticket t3 = new Ticket(3,800000,"Un portail de brutasse qui permet d'avoir plus de chance d'obtenir des serviteurs super rares.","Portail dimentionnel de créatures brutales",g);
							g.ajouterObjet(t3);
						}
					}
				}
				else if(this.indice_recompense < 30) {
					for(int i=0;i<50;i++) {
						Ticket t = new Ticket(1,10000,"Un portail basique de la roue de la RNG, donnant des serviteurs communs ou supérieurs.","Portail de créature commun",g);
						g.ajouterObjet(t);
						if(i%2 == 0) {
							Ticket t2 = new Ticket(2,75000,"Un portail permettant d'obtenir des serviteurs rares ou supérieurs à la roue de la RNG.","Portail de créature rare",g);
							g.ajouterObjet(t2);
						}
						if(i%10 == 0) {
							Ticket t3 = new Ticket(3,800000,"Un portail de brutasse qui permet d'avoir plus de chance d'obtenir des serviteurs super rares.","Portail dimentionnel de créatures brutales",g);
							g.ajouterObjet(t3);
						}
					}
				}
				else if(this.indice_recompense < 40) {
					for(int i=0;i<100;i++) {
						Ticket t = new Ticket(1,10000,"Un portail basique de la roue de la RNG, donnant des serviteurs communs ou supérieurs.","Portail de créature commun",g);
						g.ajouterObjet(t);
						if(i%2 == 0) {
							Ticket t2 = new Ticket(2,75000,"Un portail permettant d'obtenir des serviteurs rares ou supérieurs à la roue de la RNG.","Portail de créature rare",g);
							g.ajouterObjet(t2);
						}
						if(i%5 == 0) {
							Ticket t3 = new Ticket(3,800000,"Un portail de brutasse qui permet d'avoir plus de chance d'obtenir des serviteurs super rares.","Portail dimentionnel de créatures brutales",g);
							g.ajouterObjet(t3);
						}
						if(i%20 == 0) {
							Ticket t4 = new Ticket(4,10000000,"Un portail ayant de grandes chances d'octroyer un serviteur légendaire.","Portail dimentionnel de brutasse légendaire",g);
							g.ajouterObjet(t4);
						}
					}
				}
			}
			else if(this.id_recompense.get(this.indice_recompense) == 3) {
				if(this.indice_recompense < 20) {
					for(int i=0;i<8;i++) {
						GrimoireGenerator gr = new GrimoireGenerator(5, 250000, "Un objet créant un grimoire pour un serviteur commun.", "Collection de grimoires poussiereux", g, 1);
						g.ajouterObjet(gr);
						if(i%4 == 0) {
							GrimoireGenerator gr2 = new GrimoireGenerator(6, 1000000, "Un objet créant un grimoire pour un serviteur rare.", "Collection de grimoires de qualité", g, 2);
							g.ajouterObjet(gr2);
						}
						if(i%8 == 0) {
							GrimoireGenerator gr3 = new GrimoireGenerator(7, 4000000, "Un objet créant un grimoire pour un serviteur super rare et hyper rare.", "Collection de grimoires runiques", g, 3);
							g.ajouterObjet(gr3);
						}
					}
				}
				else if(this.indice_recompense < 30) {
					for(int i=0;i<10;i++) {
						GrimoireGenerator gr = new GrimoireGenerator(5, 250000, "Un objet créant un grimoire pour un serviteur commun.", "Collection de grimoires poussiereux", g, 1);
						g.ajouterObjet(gr);
						if(i%2 == 0) {
							GrimoireGenerator gr2 = new GrimoireGenerator(6, 1000000, "Un objet créant un grimoire pour un serviteur rare.", "Collection de grimoires de qualité", g, 2);
							g.ajouterObjet(gr2);
						}
						if(i%10 == 0) {
							GrimoireGenerator gr3 = new GrimoireGenerator(7, 4000000, "Un objet créant un grimoire pour un serviteur super rare et hyper rare.", "Collection de grimoires runiques", g, 3);
							g.ajouterObjet(gr3);
						}
					}
				}
				else if(this.indice_recompense < 40) {
					for(int i=0;i<16;i++) {
						GrimoireGenerator gr = new GrimoireGenerator(5, 250000, "Un objet créant un grimoire pour un serviteur commun.", "Collection de grimoires poussiereux", g, 1);
						g.ajouterObjet(gr);
						if(i%2 == 0) {
							GrimoireGenerator gr2 = new GrimoireGenerator(6, 1000000, "Un objet créant un grimoire pour un serviteur rare.", "Collection de grimoires de qualité", g, 2);
							g.ajouterObjet(gr2);
						}
						if(i%4 == 0) {
							GrimoireGenerator gr3 = new GrimoireGenerator(7, 4000000, "Un objet créant un grimoire pour un serviteur super rare et hyper rare.", "Collection de grimoires runiques", g, 3);
							g.ajouterObjet(gr3);
						}
						if(i%16 == 0) {
							GrimoireGenerator gr4 = new GrimoireGenerator(8, 15000000, "Un objet créant un grimoire pour un serviteur légendaire.", "Collection de grimoires mythiques", g, 4);
							g.ajouterObjet(gr4);
						}
					}
				}
			}
			else if(this.id_recompense.get(this.indice_recompense) == 4) {
				System.out.println(this.points_needed);
				if(this.indice_recompense == 19) {
					EventTicket et = new EventTicket(20, 1000000, "Contient en exemplaire unique un serviteur de saison", "Cellule évolutive stabilisée", g,5);
					g.ajouterObjet(et);
				}
				else if(this.indice_recompense == 29) {
					EventTicket et = new EventTicket(20, 1000000, "Contient en exemplaire unique un serviteur de saison", "Coeur de la colère", g,6);
					g.ajouterObjet(et);
				}
				else if(this.indice_recompense == 39) {
					EventTicket et = new EventTicket(20, 1000000, "Contient en exemplaire unique un serviteur de saison", "Coeur des ténèbres", g,7);
					g.ajouterObjet(et);
				}
				else if(this.indice_recompense == 49) {
					EventTicket et = new EventTicket(20, 1000000, "Contient en exemplaire unique un serviteur de saison", "Coeur de la peur", g,8);
					g.ajouterObjet(et);
				}
			}
			
			if(this.indice_recompense < 50) {
				this.indice_recompense++;
			}
			else {
				this.points_needed.set(50, this.points_needed.get(50)+(int)(10000+((g.current_season-1)*2000)));
			}
			
		}
	}

}
