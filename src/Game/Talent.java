package Game;

import java.io.Serializable;

public class Talent implements Serializable {

	public int lvl;
	public long cost;
	public long base_cost;
	public long spended;
	public int id;
	public String name;
	public String descr;
	public String nextlevel;
	
	public Talent(int i,long c) {
		this.lvl = 0;
		this.base_cost = c;
		this.cost = c;
		this.spended = 0;
		this.id = i;
		//s'occuper des noms et description
		if(this.id == 1) {
			this.name = "Tier 1 - Force naturelle";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'ATK de vos serviteurs de "+(10L*this.lvl)+" + "+((double)this.lvl/4)+" par niveau du serviteur.";
			this.nextlevel = "Prochain niveau : Augmente l'ATK de vos serviteurs de "+(10L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/4)+" par niveau du serviteur.";
		}
		else if(this.id == 2) {
			this.name = "Tier 1 - Durcissement";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la DEF de vos serviteurs de "+(10L*this.lvl)+" + "+((double)this.lvl/4)+" par niveau du serviteur.";
			this.nextlevel = "Prochain niveau : Augmente la DEF de vos serviteurs de "+(10L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/4)+" par niveau du serviteur.";
		}
		else if(this.id == 3) {
			this.name = "Tier 1 - Regard menaçant";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la PROV de vos serviteurs de "+(10L*this.lvl)+" + "+((double)this.lvl/4)+" par niveau du serviteur.";
			this.nextlevel = "Prochain niveau : Augmente la PROV de vos serviteurs de "+(10L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/4)+" par niveau du serviteur.";
		}
		else if(this.id == 4) {
			this.name = "Tier 1 - Souplesse";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'AGI de vos serviteurs de "+(10L*this.lvl)+" + "+((double)this.lvl/4)+" par niveau du serviteur.";
			this.nextlevel = "Prochain niveau : Augmente l'AGI de vos serviteurs de "+(10L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/4)+" par niveau du serviteur.";
		}
		else if(this.id == 5) {
			this.name = "Tier 1 - Rapidité";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la VIT de vos serviteurs de "+(1L*this.lvl)+" + "+((double)this.lvl/40)+" par niveau du serviteur.";
			this.nextlevel = "Prochain niveau : Augmente la VIT de vos serviteurs de "+(1L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/40)+" par niveau du serviteur.";
		}
		else if(this.id == 6) {
			this.name = "Tier 1 - Vigueur";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la Santé de vos serviteurs de "+(80L*this.lvl)+" + "+((double)this.lvl*2)+" par niveau du serviteur.";
			this.nextlevel = "Prochain niveau : Augmente la Santé de vos serviteurs de "+(80L*(this.lvl+1))+" + "+(((double)(this.lvl+1))*2)+" par niveau du serviteur.";
		}
		else if(this.id == 7) {
			this.name = "Tier 1 - Energétique";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'énergie du joueur de "+(this.lvl*2L)+".";
			this.nextlevel = "Prochain niveau : Augmente l'énergie du joueur de "+((this.lvl+1)*2L)+".";
		}
		else if(this.id == 8) {
			this.name = "Tier 1 - Roulroul";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente le gain d'or à la roulette de "+(this.lvl*150)+" par niveau de joueur.";
			this.nextlevel = "Prochain niveau : Augmente le gain d'or à la roulette de "+((this.lvl+1)*150)+" par niveau de joueur.";
		}
		else if(this.id == 9) {
			this.name = "Tier 2 - Vétéran";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'ATK de vos serviteurs de "+(50L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'ATK de vos serviteurs de "+(50L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
		}
		else if(this.id == 10) {
			this.name = "Tier 2 - Impénétrable";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la DEF de vos serviteurs de "+(50L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente la DEF de vos serviteurs de "+(50L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
		}
		else if(this.id == 11) {
			this.name = "Tier 2 - Dur à cuir";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la PROV de vos serviteurs de "+(50L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente la PROV de vos serviteurs de "+(50L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
		}
		else if(this.id == 12) {
			this.name = "Tier 2 - Marchenuit";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'AGI de vos serviteurs de "+(50L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'AGI de vos serviteurs de "+(50L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
		}
		else if(this.id == 13) {
			this.name = "Tier 2 - Ultrarapide";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la VIT de vos serviteurs de "+(5L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente la VIT de vos serviteurs de "+(5L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
		}
		else if(this.id == 14) {
			this.name = "Tier 2 - Increvable";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la Santé de vos serviteurs de "+(400L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente la Santé de vos serviteurs de "+(400L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
		}
		else if(this.id == 15) {
			this.name = "Tier 2 - Orfèvre";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente les cristaux arcaniques récupérés par destruction de "+(20L*this.lvl)+"% pour les runes mythiques.";
			this.nextlevel = "Prochain niveau : Augmente les cristaux arcaniques récupérés par destruction de "+(20L*(this.lvl+1))+"% pour les runes mythiques.";
		}
		else if(this.id == 16) {
			this.name = "Tier 2 - Chasse aux trésors";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'argent gagné dans les coffres de brutasse de "+(25L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'argent gagné dans les coffres de brutasse de "+(25L*(this.lvl+1))+"%.";
		}
		else if(this.id == 17) {
			this.name = "Tier 3 - Grand bolosseur";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'ATK et la DEF de vos serviteurs de "+(80L*this.lvl)+" et de "+(10L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'ATK et la DEF de vos serviteurs de "+(80L*(this.lvl+1))+" et de "+(10L*(this.lvl+1))+"%.";
		}
		else if(this.id == 18) {
			this.name = "Tier 3 - Nin-ja-ja-ja...";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'AGI de vos serviteurs de "+(80L*this.lvl)+" + "+(10L*this.lvl)+"% et la VIT "+(8L*this.lvl)+" + "+(10L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'AGI de vos serviteurs de "+(80L*(this.lvl+1))+" + "+(10L*(this.lvl+1))+"% et la VIT "+(8L*(this.lvl+1))+" + "+(10L*(this.lvl+1))+"%.";		
		}
		else if(this.id == 19) {
			this.name = "Tier 3 - WHO THE HELL DO YOU THINK I AM ?";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la PROV de vos serviteurs de "+(80L*this.lvl)+" + "+(10L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente la PROV de vos serviteurs de "+(80L*(this.lvl+1))+" + "+(10L*(this.lvl+1))+"%.";
		}
		else if(this.id == 20) {
			this.name = "Tier 3 - Immortel";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la Santé de vos serviteurs de "+(640L*this.lvl)+" + "+(10L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente la Santé de vos serviteurs de "+(640L*(this.lvl+1))+" + "+(10L*(this.lvl+1))+"%.";
		}
		else if(this.id == 21) {
			this.name = "Tier 3 - Invocateur de génie";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'expérience gagnée de "+(this.lvl*50L)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'expérience gagnée de "+((this.lvl+1)*50L)+"%.";
		}
		else if(this.id == 22) {
			this.name = "Tier 3 - Richesse suprême";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'or gagné de "+(this.lvl*50L)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'or gagné de "+((this.lvl+1)*50L)+"%.";
		}
		else if(this.id == 23) {
			this.name = "Tier 3 - Puissance élémentaire";
			this.descr = "Coût : "+this.cost+" cristaux - Augmente le gains de poussières élémentaires de "+(this.lvl*25L)+"%.";
			this.nextlevel = "Prochain niveau : Augmente le gains de poussières élémentaires de "+((this.lvl+1)*25L)+"%.";
		}
	}
	
	public void upgrade() {
		this.spended += this.cost;
		this.lvl++;
		this.cost += this.base_cost * (this.lvl);
		//update descriptif et nom
		if(this.id == 1) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'ATK de vos serviteurs de "+(10L*this.lvl)+" + "+((double)this.lvl/4)+" par niveau du serviteur.";
			this.nextlevel = "Prochain niveau : Augmente l'ATK de vos serviteurs de "+(10L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/4)+" par niveau du serviteur.";
		}
		else if(this.id == 2) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la DEF de vos serviteurs de "+(10L*this.lvl)+" + "+((double)this.lvl/4)+" par niveau du serviteur.";
			this.nextlevel = "Prochain niveau : Augmente la DEF de vos serviteurs de "+(10L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/4)+" par niveau du serviteur.";
		}
		else if(this.id == 3) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la PROV de vos serviteurs de "+(10L*this.lvl)+" + "+((double)this.lvl/4)+" par niveau du serviteur.";
			this.nextlevel = "Prochain niveau : Augmente la PROV de vos serviteurs de "+(10L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/4)+" par niveau du serviteur.";
		}
		else if(this.id == 4) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'AGI de vos serviteurs de "+(10L*this.lvl)+" + "+((double)this.lvl/4)+" par niveau du serviteur.";
			this.nextlevel = "Prochain niveau : Augmente l'AGI de vos serviteurs de "+(10L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/4)+" par niveau du serviteur.";
		}
		else if(this.id == 5) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la VIT de vos serviteurs de "+(1L*this.lvl)+" + "+((double)this.lvl/40)+" par niveau du serviteur.";
			this.nextlevel = "Prochain niveau : Augmente la VIT de vos serviteurs de "+(1L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/40)+" par niveau du serviteur.";
		}
		else if(this.id == 6) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la Santé de vos serviteurs de "+(80L*this.lvl)+" + "+((double)this.lvl*2)+" par niveau du serviteur.";
			this.nextlevel = "Prochain niveau : Augmente la Santé de vos serviteurs de "+(80L*(this.lvl+1))+" + "+(((double)(this.lvl+1))*2)+" par niveau du serviteur.";
		}
		else if(this.id == 7) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'énergie du joueur de "+(this.lvl*2L)+".";
			this.nextlevel = "Prochain niveau : Augmente l'énergie du joueur de "+((this.lvl+1)*2L)+".";
		}
		else if(this.id == 8) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente le gain d'or à la roulette de "+(this.lvl*150)+" par niveau de joueur.";
			this.nextlevel = "Prochain niveau : Augmente le gain d'or à la roulette de "+((this.lvl+1)*150)+" par niveau de joueur.";
		}
		else if(this.id == 9) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'ATK de vos serviteurs de "+(50L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'ATK de vos serviteurs de "+(50L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
		}
		else if(this.id == 10) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la DEF de vos serviteurs de "+(50L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente la DEF de vos serviteurs de "+(50L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
		}
		else if(this.id == 11) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la PROV de vos serviteurs de "+(50L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente la PROV de vos serviteurs de "+(50L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
		}
		else if(this.id == 12) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'AGI de vos serviteurs de "+(50L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'AGI de vos serviteurs de "+(50L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
		}
		else if(this.id == 13) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la VIT de vos serviteurs de "+(5L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente la VIT de vos serviteurs de "+(5L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
		}
		else if(this.id == 14) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la Santé de vos serviteurs de "+(400L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente la Santé de vos serviteurs de "+(400L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
		}
		else if(this.id == 15) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente les cristaux arcaniques récupérés par destruction de "+(20L*this.lvl)+"% pour les runes mythiques.";
			this.nextlevel = "Prochain niveau : Augmente les cristaux arcaniques récupérés par destruction de "+(20L*(this.lvl+1))+"% pour les runes mythiques.";
		}
		else if(this.id == 16) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'argent gagné dans les coffres de brutasse de "+(25L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'argent gagné dans les coffres de brutasse de "+(25L*(this.lvl+1))+"%.";
		}
		else if(this.id == 17) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'ATK et la DEF de vos serviteurs de "+(80L*this.lvl)+" et de "+(10L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'ATK et la DEF de vos serviteurs de "+(80L*(this.lvl+1))+" et de "+(10L*(this.lvl+1))+"%.";
		}
		else if(this.id == 18) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'AGI de vos serviteurs de "+(80L*this.lvl)+" + "+(10L*this.lvl)+"% et la VIT "+(8L*this.lvl)+" + "+(10L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'AGI de vos serviteurs de "+(80L*(this.lvl+1))+" + "+(10L*(this.lvl+1))+"% et la VIT "+(8L*(this.lvl+1))+" + "+(10L*(this.lvl+1))+"%.";		
		}
		else if(this.id == 19) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la PROV de vos serviteurs de "+(80L*this.lvl)+" + "+(10L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente la PROV de vos serviteurs de "+(80L*(this.lvl+1))+" + "+(10L*(this.lvl+1))+"%.";
		}
		else if(this.id == 20) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente la Santé de vos serviteurs de "+(640L*this.lvl)+" + "+(10L*this.lvl)+"%.";
			this.nextlevel = "Prochain niveau : Augmente la Santé de vos serviteurs de "+(640L*(this.lvl+1))+" + "+(10L*(this.lvl+1))+"%.";
		}
		else if(this.id == 21) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'expérience gagnée de "+(this.lvl*50L)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'expérience gagnée de "+((this.lvl+1)*50L)+"%.";
		}
		else if(this.id == 22) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente l'or gagné de "+(this.lvl*50L)+"%.";
			this.nextlevel = "Prochain niveau : Augmente l'or gagné de "+((this.lvl+1)*50L)+"%.";
		}
		else if(this.id == 23) {
			this.descr = "Coût : "+this.cost+" cristaux - Augmente le gains de poussières élémentaires de "+(this.lvl*25L)+"%.";
			this.nextlevel = "Prochain niveau : Augmente le gains de poussières élémentaires de "+((this.lvl+1)*25L)+"%.";
		}
	}
	
	public void reset() {
		this.lvl = 0;
		this.cost = this.base_cost;
		this.spended = 0;
		//s'occuper des noms et description
				if(this.id == 1) {
					this.name = "Tier 1 - Force naturelle";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente l'ATK de vos serviteurs de "+(10L*this.lvl)+" + "+((double)this.lvl/4)+" par niveau du serviteur.";
					this.nextlevel = "Prochain niveau : Augmente l'ATK de vos serviteurs de "+(10L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/4)+" par niveau du serviteur.";
				}
				else if(this.id == 2) {
					this.name = "Tier 1 - Durcissement";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente la DEF de vos serviteurs de "+(10L*this.lvl)+" + "+((double)this.lvl/4)+" par niveau du serviteur.";
					this.nextlevel = "Prochain niveau : Augmente la DEF de vos serviteurs de "+(10L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/4)+" par niveau du serviteur.";
				}
				else if(this.id == 3) {
					this.name = "Tier 1 - Regard menaçant";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente la PROV de vos serviteurs de "+(10L*this.lvl)+" + "+((double)this.lvl/4)+" par niveau du serviteur.";
					this.nextlevel = "Prochain niveau : Augmente la PROV de vos serviteurs de "+(10L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/4)+" par niveau du serviteur.";
				}
				else if(this.id == 4) {
					this.name = "Tier 1 - Souplesse";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente l'AGI de vos serviteurs de "+(10L*this.lvl)+" + "+((double)this.lvl/4)+" par niveau du serviteur.";
					this.nextlevel = "Prochain niveau : Augmente l'AGI de vos serviteurs de "+(10L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/4)+" par niveau du serviteur.";
				}
				else if(this.id == 5) {
					this.name = "Tier 1 - Rapidité";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente la VIT de vos serviteurs de "+(1L*this.lvl)+" + "+((double)this.lvl/40)+" par niveau du serviteur.";
					this.nextlevel = "Prochain niveau : Augmente la VIT de vos serviteurs de "+(1L*(this.lvl+1))+" + "+(((double)(this.lvl+1))/40)+" par niveau du serviteur.";
				}
				else if(this.id == 6) {
					this.name = "Tier 1 - Vigueur";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente la Santé de vos serviteurs de "+(80L*this.lvl)+" + "+((double)this.lvl*2)+" par niveau du serviteur.";
					this.nextlevel = "Prochain niveau : Augmente la Santé de vos serviteurs de "+(80L*(this.lvl+1))+" + "+(((double)(this.lvl+1))*2)+" par niveau du serviteur.";
				}
				else if(this.id == 7) {
					this.name = "Tier 1 - Energétique";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente l'énergie du joueur de "+(this.lvl*2L)+".";
					this.nextlevel = "Prochain niveau : Augmente l'énergie du joueur de "+((this.lvl+1)*2L)+".";
				}
				else if(this.id == 8) {
					this.name = "Tier 1 - Roulroul";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente le gain d'or à la roulette de "+(this.lvl*150)+" par niveau de joueur.";
					this.nextlevel = "Prochain niveau : Augmente le gain d'or à la roulette de "+((this.lvl+1)*150)+" par niveau de joueur.";
				}
				else if(this.id == 9) {
					this.name = "Tier 2 - Vétéran";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente l'ATK de vos serviteurs de "+(50L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
					this.nextlevel = "Prochain niveau : Augmente l'ATK de vos serviteurs de "+(50L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
				}
				else if(this.id == 10) {
					this.name = "Tier 2 - Impénétrable";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente la DEF de vos serviteurs de "+(50L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
					this.nextlevel = "Prochain niveau : Augmente la DEF de vos serviteurs de "+(50L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
				}
				else if(this.id == 11) {
					this.name = "Tier 2 - Dur à cuir";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente la PROV de vos serviteurs de "+(50L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
					this.nextlevel = "Prochain niveau : Augmente la PROV de vos serviteurs de "+(50L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
				}
				else if(this.id == 12) {
					this.name = "Tier 2 - Marchenuit";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente l'AGI de vos serviteurs de "+(50L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
					this.nextlevel = "Prochain niveau : Augmente l'AGI de vos serviteurs de "+(50L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
				}
				else if(this.id == 13) {
					this.name = "Tier 2 - Ultrarapide";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente la VIT de vos serviteurs de "+(5L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
					this.nextlevel = "Prochain niveau : Augmente la VIT de vos serviteurs de "+(5L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
				}
				else if(this.id == 14) {
					this.name = "Tier 2 - Increvable";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente la Santé de vos serviteurs de "+(400L*this.lvl)+" et de "+(2L*this.lvl)+"%.";
					this.nextlevel = "Prochain niveau : Augmente la Santé de vos serviteurs de "+(400L*(this.lvl+1))+" et de "+(2L*(this.lvl+1))+"%.";
				}
				else if(this.id == 15) {
					this.name = "Tier 2 - Orfèvre";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente les cristaux arcaniques récupérés par destruction de "+(20L*this.lvl)+"% pour les runes mythiques.";
					this.nextlevel = "Prochain niveau : Augmente les cristaux arcaniques récupérés par destruction de "+(20L*(this.lvl+1))+"% pour les runes mythiques.";
				}
				else if(this.id == 16) {
					this.name = "Tier 2 - Chasse aux trésors";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente l'argent gagné dans les coffres de brutasse de "+(25L*this.lvl)+"%.";
					this.nextlevel = "Prochain niveau : Augmente l'argent gagné dans les coffres de brutasse de "+(25L*(this.lvl+1))+"%.";
				}
				else if(this.id == 17) {
					this.name = "Tier 3 - Grand bolosseur";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente l'ATK et la DEF de vos serviteurs de "+(80L*this.lvl)+" et de "+(10L*this.lvl)+"%.";
					this.nextlevel = "Prochain niveau : Augmente l'ATK et la DEF de vos serviteurs de "+(80L*(this.lvl+1))+" et de "+(10L*(this.lvl+1))+"%.";
				}
				else if(this.id == 18) {
					this.name = "Tier 3 - Nin-ja-ja-ja...";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente l'AGI de vos serviteurs de "+(80L*this.lvl)+" + "+(10L*this.lvl)+"% et la VIT "+(8L*this.lvl)+" + "+(10L*this.lvl)+"%.";
					this.nextlevel = "Prochain niveau : Augmente l'AGI de vos serviteurs de "+(80L*(this.lvl+1))+" + "+(10L*(this.lvl+1))+"% et la VIT "+(8L*(this.lvl+1))+" + "+(10L*(this.lvl+1))+"%.";		
				}
				else if(this.id == 19) {
					this.name = "Tier 3 - WHO THE HELL DO YOU THINK I AM ?";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente la PROV de vos serviteurs de "+(80L*this.lvl)+" + "+(10L*this.lvl)+"%.";
					this.nextlevel = "Prochain niveau : Augmente la PROV de vos serviteurs de "+(80L*(this.lvl+1))+" + "+(10L*(this.lvl+1))+"%.";
				}
				else if(this.id == 20) {
					this.name = "Tier 3 - Immortel";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente la Santé de vos serviteurs de "+(640L*this.lvl)+" + "+(10L*this.lvl)+"%.";
					this.nextlevel = "Prochain niveau : Augmente la Santé de vos serviteurs de "+(640L*(this.lvl+1))+" + "+(10L*(this.lvl+1))+"%.";
				}
				else if(this.id == 21) {
					this.name = "Tier 3 - Invocateur de génie";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente l'expérience gagnée de "+(this.lvl*50L)+"%.";
					this.nextlevel = "Prochain niveau : Augmente l'expérience gagnée de "+((this.lvl+1)*50L)+"%.";
				}
				else if(this.id == 22) {
					this.name = "Tier 3 - Richesse suprême";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente l'or gagné de "+(this.lvl*50L)+"%.";
					this.nextlevel = "Prochain niveau : Augmente l'or gagné de "+((this.lvl+1)*50L)+"%.";
				}
				else if(this.id == 23) {
					this.name = "Tier 3 - Puissance élémentaire";
					this.descr = "Coût : "+this.cost+" cristaux - Augmente le gains de poussières élémentaires de "+(this.lvl*25L)+"%.";
					this.nextlevel = "Prochain niveau : Augmente le gains de poussières élémentaires de "+((this.lvl+1)*25L)+"%.";
				}
	}
	
	public String getDescription() {
		 String s = "<html>";
		 s += this.name+"<br><br>";
		 s += this.descr+"<br><br>";
		 s += this.nextlevel+"</html>";
		 return s;
	}
}
