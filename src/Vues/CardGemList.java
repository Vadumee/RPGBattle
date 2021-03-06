package Vues;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import Game.Game;

public class CardGemList extends JList implements Observer {

	public Game game;
	public DefaultListModel model;
	public JPanel jpp;
	
	public CardGemList(Game g,DefaultListModel d,JPanel jp) {
		super(d);
		this.game = g;
		this.jpp = jp;
		this.model = d;
		for(int i=0;i<game.joueur.collection.size();i++) {
			String s = "";
			int r = game.joueur.collection.get(i).rarity_id;
			if(r >= 9) {
				s += "<html><font color=\"#BC1818\">";
			}
			else if(r >= 8) {
				s += "<html><font color=\"#FF0000\">";
			}
			else if(r >= 7) {
				s += "<html><font color=\"#FF8B00\">";
			}
			else if(r >= 6) {
				s += "<html><font color=\"#FFC300\">";
			}
			else if(r >= 5) {
				s += "<html><font color=\"#74238E\">";
			}
			else if(r >= 4) {
				s += "<html><font color=\"#AD42CF\">";
			}
			else if(r >= 3) {
				s += "<html><font color=\"blue\">";
			}
			else if(r >= 2) {
				s += "<html><font color=\"green\">";
			}
			else {
				s += "<html><font color=\"black\">";
			}
			String star = "";
			for(int j=0;j<r;j++) {
				star += "*";
			}
			s+=game.joueur.collection.get(i).name+star+"(Lvl "+game.joueur.collection.get(i).level+")";
			this.model.add(i,s);
		}
		
		MouseListener mouseListener = new MouseAdapter() {
		      public void mouseClicked(MouseEvent mouseEvent) {
		        JList theList = (JList) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2) {
		          int index = theList.locationToIndex(mouseEvent.getPoint());
		          if (index >= 0) {
		      		  game.current_card_runed = index;
		      		  game.updateVisuals();
		          }
		        }
		      }
		 };
		 this.addMouseListener(mouseListener);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		int code = 0;
		this.jpp.repaint();
		if(arg1 != null){
			code = (int)arg1;
		}
		if(code == 0) {
			int ind = this.getSelectedIndex();
			if((ind >= 0)) {
				this.model.setElementAt(model.get(ind), ind);
			}
		}
		else if((code == 2) || (code == 3)) {
			this.model.removeAllElements();
			int indice = 0;
			for(int i=0;i<game.joueur.collection.size();i++) {
				String s = "";
				int r = game.joueur.collection.get(i).rarity_id;
				if(r >= 9) {
					s += "<html><font color=\"#BC1818\">";
				}
				else if(r >= 8) {
					s += "<html><font color=\"#FF0000\">";
				}
				else if(r >= 7) {
					s += "<html><font color=\"#FF8B00\">";
				}
				else if(r >= 6) {
					s += "<html><font color=\"#FFC300\">";
				}
				else if(r >= 5) {
					s += "<html><font color=\"#74238E\">";
				}
				else if(r >= 4) {
					s += "<html><font color=\"#AD42CF\">";
				}
				else if(r >= 3) {
					s += "<html><font color=\"blue\">";
				}
				else if(r >= 2) {
					s += "<html><font color=\"green\">";
				}
				else {
					s += "<html><font color=\"black\">";
				}
				String star = "";
				for(int j=0;j<r;j++) {
					star += "*";
				}
				s+=game.joueur.collection.get(i).name+star+"(Lvl "+game.joueur.collection.get(i).level+")";
				this.model.add(i,s);
			}
		}
	}
}
