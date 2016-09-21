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

public class ItemList extends JList implements Observer {

	public Game game;
	public DefaultListModel model;
	
	public ItemList(Game g,DefaultListModel d) {
		super(d);
		this.game = g;
		this.model = d;
		for(int i=0;i<game.inventaire.size();i++) {
			String s = "";
			s+=game.inventaire.get(i).nom+" (x"+game.inventaire.get(i).quantity+")";
			this.model.add(i,s);
		}
		
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
				s+=game.inventaire.get(i).nom+" (x"+game.inventaire.get(i).quantity+")";
				this.model.add(i,s);
			}
		}
}
}
