package Boutons;

import javax.swing.JButton;
import javax.swing.JSpinner;

public class ButtonSellItem extends JButton {

	public JSpinner spinner;
	
	public ButtonSellItem(String s, JSpinner js) {
		super(s);
		this.spinner = js;
	}
}
