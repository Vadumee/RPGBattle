package Vues;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Game.Game;

public class PanelBossBattle extends JPanel {
	
public Game game;
	
	public PanelBossBattle(Game g) {
		this.game =g;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(game.boss != null) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(game.boss.image_link);
			g.drawImage(img1, 731, 70, this);	
		}
		if(game.indice_fighters[0] != -1) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(game.joueur.collection.get(game.indice_fighters[0]).actual_image);
			g.drawImage(img1, 12, 70, 128, 160, this);
			if(game.joueur.collection.get(game.indice_fighters[0]).dead == true) {
				Image img5 = Toolkit.getDefaultToolkit().getImage("Images/deadcross.png");
				g.drawImage(img5, 12, 86, 128, 128, this);
			}
		}
		if(game.indice_fighters[1] != -1) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(game.joueur.collection.get(game.indice_fighters[1]).actual_image);
			g.drawImage(img1, 12, 281, 128, 160, this);
			if(game.joueur.collection.get(game.indice_fighters[1]).dead == true) {
				Image img5 = Toolkit.getDefaultToolkit().getImage("Images/deadcross.png");
				g.drawImage(img5, 12, 297, 128, 128, this);
			}
		}
		if(game.indice_fighters[2] != -1) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(game.joueur.collection.get(game.indice_fighters[2]).actual_image);
			g.drawImage(img1, 12, 489, 128, 160, this);
			if(game.joueur.collection.get(game.indice_fighters[2]).dead == true) {
				Image img5 = Toolkit.getDefaultToolkit().getImage("Images/deadcross.png");
				g.drawImage(img5, 12, 505, 128, 128, this);
			}
		}
	}

}
