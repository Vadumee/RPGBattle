package Vues;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Game.Game;
import Game.GameV2;
import Game.Maths;
import Game.Rune;
import Game.UpgradableRune;

public class VueAscension extends JLabel implements Observer{

	public int indice;
	public int talent;
	public Game game;
	
	public VueAscension(String s,Game g,int ind,int t) {
		this.setText(s);
		this.game = g;
		this.indice = ind;
		this.talent = t;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(indice == 1) {
			String text = "<html><p>Niveau d'ascension : <font color=\"purple\">"+Maths.format(((GameV2)game).fight_level)+"</font> - Exp : <font color=\"blue\">"+Maths.format(((GameV2)game).fight_exp)+"</font> / "+Maths.format(((GameV2)game).fight_max_exp)+"</p></html>";
			this.setText(text);
		}
		else if(indice == 2) {
			String text = "<html><p>Cristaux du vide : <font color=\"purple\">"+Maths.format(((GameV2)game).void_cristals)+"</p></html>";
			this.setText(text);
		}
		else if(indice == 3) {
			String text = "<html><p>Cristaux dépensés : <font color=\"purple\">"+Maths.format(((GameV2)game).void_used)+"</p></html>";
			this.setText(text);
		}
		else if(indice == 4) {
			this.setText( "Lvl : "+((GameV2)game).talents[this.talent-1].lvl );
		}
	}

}
