package Vues;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Game.Game;

public class PanelCard extends JPanel{
	
	public Game game;

	public PanelCard(Game g) {
		super();
		this.game = g;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(game.current_card_id != -1) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(game.joueur.collection.get(game.current_card_id).actual_image);
			g.drawImage(img1, 320, 60, this);
		}
	}
	
}
