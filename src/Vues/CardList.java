package Vues;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Game.Game;

public class CardList extends JList implements Observer {

	public Game game;
	public DefaultListModel model;
	public JPanel jpp;
	
	public CardList(Game g,DefaultListModel d,JPanel jp) {
		super(d);
		this.game = g;
		this.jpp = jp;
		this.model = d;
		for(int i=0;i<game.joueur.collection.size();i++) {
			String s = "";
			int r = game.joueur.collection.get(i).rarity_id;
			if(r >= 8) {
				s += "<html><font color=\"red\">";
			}
			else if(r >= 6) {
				s += "<html><font color=\"orange\">";
			}
			else if(r >= 4) {
				s += "<html><font color=\"purple\">";
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
			if(game.joueur.collection.get(i).prestige > 0) {
				s+=game.joueur.collection.get(i).name+star+"(Lvl "+game.joueur.collection.get(i).level+" - P"+game.joueur.collection.get(i).prestige+")";
			}
			else {
				s+=game.joueur.collection.get(i).name+star+"(Lvl "+game.joueur.collection.get(i).level+")";
			}
			this.model.add(i,s);
		}
		
		this.addKeyListener(new java.awt.event.KeyListener() {
            public void keyPressed(java.awt.event.KeyEvent evt) { 
            	 if(evt.getKeyCode() == evt.VK_UP) {
 	            	System.out.print("up");
 	            	if((game.current_card_id != -1) && (game.current_card_id > 0)) {
 				 		game.current_card_id--;
 				 		game.updateVisuals();
 				 	}
 	            }
 	            else if(evt.getKeyCode() == evt.VK_DOWN) {
 	            	System.out.print("up");
 	            	if((game.current_card_id != -1) && (game.current_card_id < game.joueur.collection.size()-1)) {
 				 		game.current_card_id++;
 				 		game.updateVisuals();
 				 	}
 	            }
            }

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		MouseListener mouseListener = new MouseAdapter() {
		      public void mouseClicked(MouseEvent mouseEvent) {
		        JList theList = (JList) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2) {
		          int index = theList.locationToIndex(mouseEvent.getPoint());
		          if (index >= 0) {
		      		  game.current_card_id = index;
		      		  game.updateVisuals();
		          }
		        }
		      }
		 };
		 this.addMouseListener(mouseListener);
		 
		 /*Action doNothing = new AbstractAction() {
		 public void actionPerformed(ActionEvent e) {
			 	if(game.current_card_id != -1) {
			 		game.current_card_id++;
			 	}
		  	}
		 };
		 this.getInputMap().put(KeyStroke.getKeyStroke("F2"),
			                            "doNothing");*/
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
				if(r >= 8) {
					s += "<html><font color=\"red\">";
				}
				else if(r >= 6) {
					s += "<html><font color=\"orange\">";
				}
				else if(r >= 4) {
					s += "<html><font color=\"purple\">";
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
