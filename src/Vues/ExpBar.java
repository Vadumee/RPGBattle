package Vues;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Game.Game;
import Game.GameV2;

public class ExpBar extends JPanel implements Observer {

	public Game game;
	
	public ExpBar(Game g) {
		super();
		this.game = g;
	}

	public void paint(Graphics g) {
		super.paint(g);
		int size = this.getWidth();
		double fract = ((double)( ((GameV2)game).fight_exp)) / (double)( ((GameV2)game).fight_max_exp);
		int fillrect = (int)(size * fract);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, fillrect,this.getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(fillrect, 0, this.getWidth()-fillrect, this.getHeight());
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.repaint();
	}
}
