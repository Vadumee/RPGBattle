package Vues;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import Game.Game;

public class PanelForge extends JPanel {
	
	public VueForge vf;
	public VueForge vf2;
	public VueForge vf3;
	public VueForge vf4;
	public Game game;
	
	public PanelForge(Game g, VueForge v, VueForge v2, VueForge v3, VueForge v4) {
		super();
		this.game = g;
		this.vf = v;
		this.vf2 = v2;
		this.vf3 = v3;
		this.vf4 = v4;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Image img1 = Toolkit.getDefaultToolkit().getImage("Images/forge_fond.png");
		g.drawImage(img1, 0, 0, this);
		if(game.current_rune_selected != -1) {
			Image img2 = Toolkit.getDefaultToolkit().getImage(game.inventaire_runes.get(game.current_rune_selected).image_link);
			g.drawImage(img2, 290, 80,60, 60, this);
		}
		else {
			Image img2 = Toolkit.getDefaultToolkit().getImage("Images/gem_blank.jpg");
			g.drawImage(img2, 290, 80, 60, 60, this);
		}
		this.vf.updateFromPanel();
		this.vf2.updateFromPanel();
		this.vf3.updateFromPanel();
		this.vf4.updateFromPanel();
	}

}
