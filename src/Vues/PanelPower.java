package Vues;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Game.Game;
import Game.GameV2;

public class PanelPower extends JPanel implements Observer {

	public Game game;
	public int indice;

	public PanelPower(Game g, int ind) {
		super();
		this.game = g;
		this.indice = ind;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Image img1 = Toolkit.getDefaultToolkit().getImage("images/power_"+this.indice+".jpg");
		g.drawImage(img1, 0, 0, this);
		this.setToolTipText( ((GameV2)this.game).talents[this.indice-1].getDescription());
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		int code = 0;
		if(arg1 != null){
			code = (int)arg1;
		}
		//correspond au code de reset de talents
		if(code == 88) {
			this.repaint();
		}
	}
}
