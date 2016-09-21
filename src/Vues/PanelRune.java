package Vues;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Game.Game;

public class PanelRune extends JPanel {
	
	public Game game;

	public PanelRune(Game g) {
		super();
		this.game = g;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(game.current_card_runed != -1) {
			if(game.joueur.collection.get(game.current_card_runed).runes[0] != null) {
				Image img1 = Toolkit.getDefaultToolkit().getImage(game.joueur.collection.get(game.current_card_runed).runes[0].image_link);
				g.drawImage(img1, 329, 13, this);
			}
			else {
				Image img1 = Toolkit.getDefaultToolkit().getImage("images/gem_blank.jpg");
				g.drawImage(img1, 329, 13, this);
			}
			
			if(game.joueur.collection.get(game.current_card_runed).runes[1] != null) {
				Image img2 = Toolkit.getDefaultToolkit().getImage(game.joueur.collection.get(game.current_card_runed).runes[1].image_link);
				g.drawImage(img2, 329, 209, this);
			}
			else {
				Image img2 = Toolkit.getDefaultToolkit().getImage("images/gem_blank.jpg");
				g.drawImage(img2, 329, 209, this);
			}
			
			if(game.joueur.collection.get(game.current_card_runed).runes[2] != null) {
				Image img3 = Toolkit.getDefaultToolkit().getImage(game.joueur.collection.get(game.current_card_runed).runes[2].image_link);
				g.drawImage(img3, 329, 408, this);
			}
			else {
				Image img3 = Toolkit.getDefaultToolkit().getImage("images/gem_blank.jpg");
				g.drawImage(img3, 329, 408, this);
			}
		}
		else {
			Image img1 = Toolkit.getDefaultToolkit().getImage("images/gem_blank.jpg");
			g.drawImage(img1, 329, 13, this);
			
			Image img2 = Toolkit.getDefaultToolkit().getImage("images/gem_blank.jpg");
			g.drawImage(img2, 329, 209, this);
			
			Image img3 = Toolkit.getDefaultToolkit().getImage("images/gem_blank.jpg");
			g.drawImage(img3, 329, 408, this);
		}
	}

}
