package Boutons;

import javax.swing.JButton;
import javax.swing.JSpinner;

public class ButtonFightBoss extends JButton {
	
	public JSpinner js;
	
	public ButtonFightBoss(String s,JSpinner j) {
		super(s);
		this.js = j;
	}

}
