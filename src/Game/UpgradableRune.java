package Game;

public class UpgradableRune extends Rune {
	
	public int upgrade_level;
	public double chance_upgrade;
	public long upgrade_cost;
	public long base_bonus[];
	public long fight_bonus[];

	public UpgradableRune(int idrar, int idr, int p, int idb, int cout) {
		super(idrar, idr, p, idb, cout);
		// TODO Auto-generated constructor stub
		this.upgrade_level = 0;
		this.chance_upgrade = 1.0;
		this.upgrade_cost = 200 + (p*30);
		this.base_bonus = new long[6];
		this.fight_bonus = new long[6];
		for(int j=0;j<6;j++) {
			this.fight_bonus[j] = this.bonus[j];
		}
		for(int i=0;i<6;i++) {
			this.base_bonus[i] = this.bonus[i];
		}
	}
	
	public UpgradableRune(Rune r) {
		super(1,1,1,1,1);
		this.nom = r.nom;
		this.id = r.id;
		this.cost = r.cost;
		this.prestige = r.prestige;
		this.image_link = r.image_link;
		this.bonus = r.bonus;
		this.rarete = r.rarete;
		this.upgrade_level = 0;
		this.chance_upgrade = 1.0;
		this.upgrade_cost = 200 + (this.prestige*30);
		this.base_bonus = new long[6];
		this.fight_bonus = new long[6];
		for(int j=0;j<6;j++) {
			this.fight_bonus[j] = this.bonus[j];
		}
		for(int i=0;i<6;i++) {
			this.base_bonus[i] = this.bonus[i];
		}
	}
	
	public String getDescr() {
		super.getDescr();
		String s = "<html>";
		for(int i=0;i<bonus.length;i++) {
			if(bonus[i] != 0) {
				if(bonus[i] > 0) {
					s+= "<font color=\"green\">+ "+Maths.format(fight_bonus[i]);
				}
				else if(bonus[i] < 0){
					s+= "<font color=\"red\">- "+Maths.format(Math.abs(fight_bonus[i]));
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
	
	public String getNom() {
		String we = super.getNom();
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
		if(this.upgrade_level > 0) {
			s+=" + "+this.upgrade_level;
		}
		if(this.prestige > 0 ){ 
			s+= " (ilvl "+(this.prestige*2)+")";
		}
		s+="</font></html>";
		return s;
	}
	
	public boolean isupgraded() {
		boolean b = false;
		if(Math.random() < this.chance_upgrade) {
			b = true;
		}
		return b;
	}
	
	public void upgradeRune() {
		this.upgrade_cost += (200 + (30*this.prestige));
		int upgrade_percent = 10 + (1*this.upgrade_level);
		for(int i=0;i<6;i++) {
			long up = (this.base_bonus[i]*upgrade_percent)/200;
			this.fight_bonus[i] += up;
		}
		if(this.upgrade_level < 5) {
			this.chance_upgrade -= 0.07;
		}
		else if(this.upgrade_level < 10) {
			this.chance_upgrade -= 0.06;
		}
		else if(this.upgrade_level < 15) {
			this.chance_upgrade -= 0.05;
		}
		else {
			this.chance_upgrade -= 0.04;
		}
		this.upgrade_level++;
		if(this.chance_upgrade < 0.03) {
			this.chance_upgrade = 0.03;
		}
	}
	
	public String getForgeNom() {
		String we = super.getNom();
		String s = "";
		if(rarete == 5) {
			s += "<html><font color=\"BEA2CE\">";
		}
		else if(rarete == 4) {
			s += "<html><font color=\"BEA2CE\">";
		}
		else if(rarete == 3) {
			s += "<html><font color=\"5BCBED\">";
		}
		else if(rarete == 2) {
			s += "<html><font color=\"#B7F997\">";
		}
		else {
			s += "<html><font color=\"white\">";
		}
		s+=this.nom;
		if(this.upgrade_level > 0) {
			s+=" + "+this.upgrade_level;
		}
		if(this.prestige > 0 ){ 
			s+= " (ilvl "+(this.prestige*2)+")";
		}
		s+="</font></html>";
		return s;
	}
	
	public String getUpgradeCost() {
		String s = "";
		String color = "";
		if(this.chance_upgrade > 0.65) {
			color = "<font color=\"#B7F997\">";
		}
		else if(this.chance_upgrade > 0.35) {
			color = "<font color=\"#F6F997\">";
		}
		else if(this.chance_upgrade > 0.15) {
			color = "<font color=\"#F9CB97\">";
		}
		else {
			color = "<font color=\"#ED5B5B\">";
		}
		s += "<html><p><font color=\"white\">Taux de réussite : </font>"+color+Math.round(this.chance_upgrade*100)+"</font><font color=\"white\"> %  -    Coût : </font><font color=\"#81BEF7\">"+Maths.format(this.upgrade_cost)+"</font></p></html>";
		return s;
	}
	
	public String getDescrAfter() {
		String s = "<html>";
		int upgrade_percent = 10 + (1*this.upgrade_level);
		
		for(int i=0;i<bonus.length;i++) {
			if(bonus[i] != 0) {
				if(bonus[i] > 0) {
					s+= "<font color=\"green\">+ "+Maths.format(this.fight_bonus[i]+((this.base_bonus[i]*upgrade_percent)/200));
				}
				else if(bonus[i] < 0){
					s+= "<font color=\"red\">- "+Maths.format(Math.abs(this.fight_bonus[i]+((this.base_bonus[i]*upgrade_percent)/200)));
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
		return s;
	}
}
