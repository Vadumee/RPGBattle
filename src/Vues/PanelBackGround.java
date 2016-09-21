package Vues;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Game.Carte;
import Game.WavPlayer;

public class PanelBackGround extends JPanel {
	
	public String son;
	public Carte carte;
	
	public JButton bouton;
	public JLabel label;
	public PanelCardLoot plc;
	
	public PanelBackGround(Carte c,String s) {
		super();
		this.son = s;
		this.carte = c;
	}
	
	public void add(JButton jb, JLabel jl, PanelCardLoot p) {
		bouton = jb;
		label = jl;
		plc = p;
	}
	
	public void update() {
		bouton.repaint();
		label.setText(label.getText());
		label.setOpaque(true);;
		label.repaint();
		plc.repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Image img1 = Toolkit.getDefaultToolkit().getImage("Images/season_background.jpg");
		g.drawImage(img1, 0, 0, this);
		
		this.update();
	}

}
