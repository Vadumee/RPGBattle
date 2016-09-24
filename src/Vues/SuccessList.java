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

public class SuccessList extends JList implements Observer {
	
	public Game game;
	public DefaultListModel model;
	
	public SuccessList(Game g,DefaultListModel d) {
		super(d);
		this.game = g;
		this.model = d;
		for(int i=0;i<game.succes.size();i++) {
			boolean comp = game.succes.get(i).completed;
			String s = "";
			if(comp == false) {
				s += "<html><font color=\"red\">";
			}
			else {
				s += "<html><font color=\"green\">";
			}
			s+="["+game.succes.get(i).nom+"]";
			s+="</font></html>";
			this.model.add(i,s);
		}
		
		MouseListener mouseListener = new MouseAdapter() {
		      public void mouseClicked(MouseEvent mouseEvent) {
		        JList theList = (JList) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2) {
		          int index = theList.locationToIndex(mouseEvent.getPoint());
		          if (index >= 0) {
		      		  game.current_success = index;
		      		  game.updateVisuals();
		          }
		        }
		      }
		 };
		 this.addMouseListener(mouseListener);
	}

	@Override
	public void update(Observable o, Object arg) {
		int code = 0;
		if(arg != null){
			code = (int)arg;
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
			for(int i=0;i<game.succes.size();i++) {
				boolean comp = game.succes.get(i).completed;
				String s = "";
				if(comp == false) {
					s += "<html><font color=\"red\">";
				}
				else {
					s += "<html><font color=\"green\">";
				}
				s+="["+game.succes.get(i).nom+"]";
				s+="</font></html>";
				this.model.add(i,s);
			}
		}
		
	}

}
