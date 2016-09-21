package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

public class BoxAutoSelect implements ActionListener{

	public Game game;
	
	public BoxAutoSelect(Game g) {
		this.game = g;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(((JCheckBox)arg0.getSource()).isSelected() == true) {
			game.auto_select = true;
		}
		else {
			game.auto_select = false;
		}
	}
	
}
