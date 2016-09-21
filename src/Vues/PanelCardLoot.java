package Vues;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Game.Carte;
import Game.Game;

public class PanelCardLoot extends JPanel {
	
	public Carte carte;
	public int importance;

	public PanelCardLoot(Carte c,int imp) {
		super();
		this.carte = c;
		this.importance = imp;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(importance == 1) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(carte.actual_image);
			g.drawImage(img1, 0, 0, 320,400, this);
		}
		else {
			Image img1 = Toolkit.getDefaultToolkit().getImage(carte.actual_image);
			g.drawImage(img1, 0, 0, 160,200, this);
		}
	}
}
