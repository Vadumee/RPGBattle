package Vues;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import Game.Game;
import Game.Maths;

public class ItemList extends JList implements Observer {

	public Game game;
	public DefaultListModel model;
	
	public ItemList(Game g,DefaultListModel d) {
		super(d);
		this.game = g;
		this.model = d;
		for(int i=0;i<game.inventaire.size();i++) {
			String s = "";
			s+=game.inventaire.get(i).nom+" (x"+Maths.format(game.inventaire.get(i).quantity)+")";
			this.model.add(i,s);
		}
		
		this.addKeyListener(new java.awt.event.KeyListener() {
            public void keyPressed(java.awt.event.KeyEvent evt) { 
            	 if(evt.getKeyCode() == evt.VK_UP) {
 	            	System.out.print("up");
 	            	if((game.current_item_selected != -1) && (game.current_item_selected > 0)) {
 	            		game.current_item_selected--;
 				 		game.updateVisuals();
 				 	}
 	            }
 	            else if(evt.getKeyCode() == evt.VK_DOWN) {
 	            	System.out.print("up");
 	            	if((game.current_item_selected != -1) && (game.current_item_selected < game.inventaire.size()-1)) {
 	            		game.current_item_selected++;
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
		      		  game.current_item_selected = index;
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
			for(int i=0;i<game.inventaire.size();i++) {
				String s = "";
				s+=game.inventaire.get(i).nom+" (x"+Maths.format(game.inventaire.get(i).quantity)+")";
				this.model.add(i,s);
			}
		}
}
}
