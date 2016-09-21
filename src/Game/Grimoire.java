package Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Grimoire extends Item{
	
	public int id_char;
	public int rarity_increase;
	public int id_serviteur;
	public int tier;

	public Grimoire(int i, int cst, String d, String n, Game g, int id, int rar, int ti) {
		super(i, cst, d, n, g);
		this.id_char = id;
		String s = "";
		this.tier = ti;
		this.rarity_increase = rar;
		if(rarity_increase == 4) {
			s = "HR";
		}
		else if(rarity_increase == 5) {
			s = "HR+";
		}
		else if(rarity_increase == 6) {
			s = "L";
		}
		else if(rarity_increase == 7) {
			s = "SL";
		}
		else if(rarity_increase == 8) {
			s = "M";
		}
		else if(rarity_increase == 9) {
			s = "SM";
		}
		this.nom = "Grimoire d'amélioration "+s+" : ";
		File file = new File("cards/"+tier+"/char_"+id+".txt"); 
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String nomc = reader.readLine();
			this.nom += nomc;
			String st = "";
			for(int j=0;j<12;j++) {
				st = reader.readLine();
			}
			this.id_serviteur = Integer.parseInt(st);
			this.description = "Un grimoire ancien permettant d'augmenter la rareté maximale de "+nomc+" à "+s;
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void utiliser() {
		super.utiliser();
		JOptionPane jop1;
		jop1 = new JOptionPane();
		jop1.showMessageDialog(null, "Pour utilsier un grimoire, allez dans l'onglet collection, et choisissez la créature correspondante, ayant la rareté inférieure au grimoire (N->R->SR->HR->HR+->L->SL->M->SM)", "Message informatif", JOptionPane.INFORMATION_MESSAGE);
	}
}
