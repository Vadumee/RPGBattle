package Vues;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Game.Game;

public class PanelRoulette extends JPanel {

	public Game game;

	public PanelRoulette(Game g) {
		super();
		this.game = g;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Image img1 = Toolkit.getDefaultToolkit().getImage("Images/logo-vide.jpg");
		Image img2 = Toolkit.getDefaultToolkit().getImage("Images/logo-vide.jpg");
		Image img3 = Toolkit.getDefaultToolkit().getImage("Images/logo-vide.jpg");
		if(game.roul1 == "Gx3") {
			img1 = Toolkit.getDefaultToolkit().getImage("Images/logo-2.jpg");
			
		}
		else if(game.roul1 == "Gx5") {
			img1 = Toolkit.getDefaultToolkit().getImage("Images/logo-3.jpg");
		}
		else if(game.roul1 == "Gx10") {
			img1 = Toolkit.getDefaultToolkit().getImage("Images/logo-4.jpg");
		}
		else if(game.roul1 == "Gx20") {
			img1 = Toolkit.getDefaultToolkit().getImage("Images/logo-5.jpg");
		}
		else if(game.roul1 == "Expx3") {
			img1 = Toolkit.getDefaultToolkit().getImage("Images/logo-1.jpg");
		}
		else if(game.roul1 == "Tck") {
			img1 = Toolkit.getDefaultToolkit().getImage("Images/logo-7.jpg");
		}
		else if(game.roul1 == "R Tck !") {
			img1 = Toolkit.getDefaultToolkit().getImage("Images/logo-8.jpg");
		}
		else if(game.roul1 == "Obj") {
			img1 = Toolkit.getDefaultToolkit().getImage("Images/logo-6.jpg");
		}
		
		if(game.roul2 == "Gx3") {
			img2 = Toolkit.getDefaultToolkit().getImage("Images/logo-2.jpg");
		}
		else if(game.roul2 == "Gx5") {
		    img2 = Toolkit.getDefaultToolkit().getImage("Images/logo-3.jpg");
		}
		else if(game.roul2 == "Gx10") {
			img2 = Toolkit.getDefaultToolkit().getImage("Images/logo-4.jpg");
		}
		else if(game.roul2 == "Gx20") {
			img2 = Toolkit.getDefaultToolkit().getImage("Images/logo-5.jpg");
		}
		else if(game.roul2 == "Expx3") {
			img2 = Toolkit.getDefaultToolkit().getImage("Images/logo-1.jpg");
		}
		else if(game.roul2 == "Tck") {
			img2 = Toolkit.getDefaultToolkit().getImage("Images/logo-7.jpg");
		}
		else if(game.roul2 == "R Tck !") {
			img2 = Toolkit.getDefaultToolkit().getImage("Images/logo-8.jpg");
		}
		else if(game.roul2 == "Obj") {
			img2 = Toolkit.getDefaultToolkit().getImage("Images/logo-6.jpg");
		}
		
		if(game.roul3 == "Gx3") {
			img3 = Toolkit.getDefaultToolkit().getImage("Images/logo-2.jpg");
		}
		else if(game.roul3 == "Gx5") {
			img3 = Toolkit.getDefaultToolkit().getImage("Images/logo-3.jpg");
		}
		else if(game.roul3 == "Gx10") {
			img3 = Toolkit.getDefaultToolkit().getImage("Images/logo-4.jpg");
		}
		else if(game.roul3 == "Gx20") {
			img3 = Toolkit.getDefaultToolkit().getImage("Images/logo-5.jpg");
		}
		else if(game.roul3 == "Expx3") {
			img3 = Toolkit.getDefaultToolkit().getImage("Images/logo-1.jpg");
		}
		else if(game.roul3 == "Tck") {
			img3 = Toolkit.getDefaultToolkit().getImage("Images/logo-7.jpg");
		}
		else if(game.roul3 == "R Tck !") {
			img3 = Toolkit.getDefaultToolkit().getImage("Images/logo-8.jpg");
		}
		else if(game.roul3 == "Obj") {
			img3 = Toolkit.getDefaultToolkit().getImage("Images/logo-6.jpg");
		}
		g.drawImage(img1, 165, 184, this);
		g.drawImage(img2, 449, 184, this);
		g.drawImage(img3, 751, 184, this);
	}
}
