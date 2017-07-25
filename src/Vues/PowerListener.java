package Vues;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Game.Game;
import Game.GameV2;

public class PowerListener implements MouseListener {
	
	public Game game;
	public PanelPower panel;
	
	public PowerListener(Game g,PanelPower pp) {
		this.game = g;
		this.panel = pp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int id = this.panel.indice;
		if(((GameV2)game).void_cristals >= ((GameV2)game).talents[id-1].cost) {
			((GameV2)game).void_cristals -= ((GameV2)game).talents[id-1].cost;
			((GameV2)game).void_used += ((GameV2)game).talents[id-1].cost;
			((GameV2)game).talents[id-1].upgrade();
			((GameV2)game).recalculateAllStats();
		}
		this.panel.repaint();
		game.updateVisuals();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
