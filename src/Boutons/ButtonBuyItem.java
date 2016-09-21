package Boutons;

import javax.swing.JButton;
import javax.swing.JSpinner;

public class ButtonBuyItem extends JButton {
	
	public JSpinner spinner;
	
	public ButtonBuyItem(String s,JSpinner js) {
		super(s);
		this.spinner = js;
	}
	
}
