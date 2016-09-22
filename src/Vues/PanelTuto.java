package Vues;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PanelTuto extends JPanel {
	
	public int indice;
	public ArrayList<String> images;
	
	public PanelTuto(int i,ArrayList<String> img) {
		super();
		this.indice = i;
		this.images = img;
	}
	
	public void paint(Graphics g) {
		Image img1 = Toolkit.getDefaultToolkit().getImage(this.images.get(indice));
		g.drawImage(img1, 0, 0, 660, 440, this);
	}

}
