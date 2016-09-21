package Vues;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Game.Game;

public class PanelBossPreview extends JPanel {
	
	public Game game;
	
	public PanelBossPreview(Game g) {
		this.game =g;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(game.boss != null) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(game.boss.image_link);
			g.drawImage(img1, 715, 41, this);	
		}
		if(game.indice_fighters[0] != -1) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(game.joueur.collection.get(game.indice_fighters[0]).actual_image);
			g.drawImage(img1, 94, 42, 128, 160, this);
			if(game.joueur.collection.get(game.indice_fighters[0]).dead == true) {
				Image img5 = Toolkit.getDefaultToolkit().getImage("Images/deadcross.png");
				g.drawImage(img5, 94, 58, 128, 128, this);
			}
		}
		if(game.indice_fighters[1] != -1) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(game.joueur.collection.get(game.indice_fighters[1]).actual_image);
			g.drawImage(img1, 94, 235, 128, 160, this);
			if(game.joueur.collection.get(game.indice_fighters[1]).dead == true) {
				Image img5 = Toolkit.getDefaultToolkit().getImage("Images/deadcross.png");
				g.drawImage(img5, 94, 251, 128, 128, this);
			}
		}
		if(game.indice_fighters[2] != -1) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(game.joueur.collection.get(game.indice_fighters[2]).actual_image);
			g.drawImage(img1, 94, 430, 128, 160, this);
			if(game.joueur.collection.get(game.indice_fighters[2]).dead == true) {
				Image img5 = Toolkit.getDefaultToolkit().getImage("Images/deadcross.png");
				g.drawImage(img5, 94, 446, 128, 128, this);
			}
		}
		Image img4 = Toolkit.getDefaultToolkit().getImage("Images/dps.png");
		g.drawImage(img4, 14, 72, 70, 100, this);
		Image img5 = Toolkit.getDefaultToolkit().getImage("Images/dps.png");
		g.drawImage(img5, 14, 265, 70, 100, this);
		Image img6 = Toolkit.getDefaultToolkit().getImage("Images/heal.png");
		g.drawImage(img6, 14, 485, 70, 70, this);
		
	}

}
