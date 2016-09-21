package Vues;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Game.Game;

public class PanelFavorite extends JPanel {

	public Game game;

	public PanelFavorite(Game g) {
		super();
		this.game = g;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(game.current_favorite_id != -1) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(game.joueur.collection.get(game.current_favorite_id).actual_image);
			g.drawImage(img1, 630, 180, this);
		}
	}
}
