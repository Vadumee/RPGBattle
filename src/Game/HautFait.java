package Game;

import java.io.Serializable;

public class HautFait implements Serializable {
	
	public String nom;
	public String descr;
	public long montant;
	public boolean completed;
	
	public HautFait(String n, String d, long mt) {
		this.completed = false;
		this.nom = n;
		this.descr = d;
		this.montant = mt;
	}

}
