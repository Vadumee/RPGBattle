package Vues;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import Game.Game;

public class MagasinList extends JList implements Observer {

	public Game game;
	public DefaultListModel model;
	
	public MagasinList(Game g,DefaultListModel d) {
		super(d);
		this.game = g;
		this.model = d;
		for(int i=0;i<game.magasin.size();i++) {
			String s = "";
			s+=game.magasin.get(i).nom+" ("+game.magasin.get(i).cost+" Or/u)";
			this.model.add(i,s);
		}
		
		MouseListener mouseListener = new MouseAdapter() {
		      public void mouseClicked(MouseEvent mouseEvent) {
		        JList theList = (JList) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2) {
		          int index = theList.locationToIndex(mouseEvent.getPoint());
		          if (index >= 0) {
		      		  game.current_item_magasin = index;
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
		else if(code == 2) {
			this.model.removeAllElements();
			int indice = 0;
			for(int i=0;i<game.magasin.size();i++) {
				String s = "";
				s+=game.magasin.get(i).nom+" ("+game.magasin.get(i).cost+" Or/u)";
				this.model.add(i,s);
			}
		}
}
}